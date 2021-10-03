package com.teama.ebook.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teama.api.util.HttpURLConnUtil;
import com.teama.ebook.dao.EbookDAO;

@Service("ebookAPIService")
public class EbookAPIServiceImpl implements EbookAPIService {
	@Autowired
	private EbookDAO ebookDAO;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> searchEbook(String searchType, String searchValue, int pageNo) {
		Map<String, Object> map = null;
		Map<String, Object> searchInfo = Map.of(
				"pageNo", pageNo,
				"searchType", searchType,
				"searchValue", searchValue);
		
		 try {
			JSONObject resultObj = ebookSearch(searchInfo);
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(resultObj.toString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject ebookSearch(Map<String, Object> map) throws Exception {
		String serviceKey = "e1105e7ff1c474e3dd03dffcdf49ade50be0ba92155bd191bdbf3be98af7d545";
		String url = "http://seoji.nl.go.kr/landingPage/SearchApi.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cert_key", serviceKey);
		params.put("result_style", "json");
		params.put("page_size", "10");
		params.put("page_no", String.valueOf(map.get("pageNo")));
		
		if (map.get("searchValue") != null) {
			params.put((String)map.get("searchType"), (String) map.get("searchValue"));
		}

		String response = HttpURLConnUtil.doGetRequest(url, null, params);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(response);
		List<Map<String, Object>> docsMap = (List<Map<String, Object>>) jsonObject.get("docs");
		List<Map<String, Object>> kakaoList = null;
		for (int i = 0; i < docsMap.size(); i++) {
			//System.out.println(docsMap.get(i).get("EA_ISBN").equals(""));
			if (!docsMap.get(i).get("EA_ISBN").equals("")) {
				if (docsMap.get(i).get("TITLE").equals("") || docsMap.get(i).get("TITLE_URL").equals("")) {
					{
						kakaoList = ebookSearchKakao((String) docsMap.get(i).get("EA_ISBN"));
						//System.out.println("kakaoList = " + kakaoList);
						if (kakaoList != null && kakaoList.size() != 0) {
							//System.out.println("카카오 추가 리스트 " + kakaoList);
							if (kakaoList.get(0).get("title") != null) {
								//System.out.println("기존 타이틀 " + docsMap.get(i).get("TITLE"));
								//System.out.println("변경 타이틀 " + kakaoList.get(0).get("title"));
								docsMap.get(i).put("TITLE", kakaoList.get(0).get("title"));
							}
							if (kakaoList.get(0).get("thumbnail") != null) {
								//System.out.println("기존 사진 " + docsMap.get(i).get("TITLE_URL"));
								//System.out.println("변경 사진 " + kakaoList.get(0).get("thumbnail"));
								docsMap.get(i).put("TITLE_URL", kakaoList.get(0).get("thumbnail"));
							}
							if (kakaoList.get(0).get("contents") != null) {
								docsMap.get(i).put("BOOK_SUMMARY", kakaoList.get(0).get("contents"));
							}
							if (kakaoList.get(0).get("datetime") != null) {
								docsMap.get(i).put("datetime", kakaoList.get(0).get("datetime"));
							}
							if (kakaoList.get(0).get("translators") != null) {
								docsMap.get(i).put("translators", kakaoList.get(0).get("translators"));
							}
							if (kakaoList.get(0).get("price") != null) {
								docsMap.get(i).put("price", kakaoList.get(0).get("price"));
							}
							if (kakaoList.get(0).get("sale_price") != null) {
								docsMap.get(i).put("sale_price", kakaoList.get(0).get("sale_price"));
							}
							if (kakaoList.get(0).get("url") != null) {
								@SuppressWarnings("rawtypes")
								Map<String, Object> ebookDetail = new HashMap();
								ebookDetail = ebookCrawler((String) kakaoList.get(0).get("url"));
								System.out.println(ebookDetail);
								docsMap.get(i).put("book_introduction", ebookDetail.get("0"));
								docsMap.get(i).put("author_introduction", ebookDetail.get("1"));
								docsMap.get(i).put("book_tb_cnt", ebookDetail.get("2"));
								docsMap.get(i).put("book_detail", ebookDetail.get("3"));
								docsMap.get(i).put("librarian_appreciation", ebookDetail.get("4"));
							}
							
						}
					}
				}
			}
			if (docsMap.get(i).get("TITLE_URL").equals("")) {
				docsMap.get(i).put("TITLE_URL", "./resources/img/thumbnail.gif");
				//System.out.println("docsMAp " + i + " : " + docsMap.get(i).get("TITLE_URL"));
			}
			if(docsMap.get(i).get("EA_ISBN").equals("") && docsMap.get(i).get("TITLE").equals("")){
				System.out.println("미등록");				
			}else {
				System.out.println("등록");
				//ebookAdd(docsMap.get(i));
			}
		}
		jsonObject.put("docs", docsMap);
		System.out.println("jsonObject---------------------");
		System.out.println(jsonObject);
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ebookSearchKakao(String isbn) throws Exception {
		String serviceKey = "KakaoAK b6e603937314371fe828d320341a4c07";
		String url = "https://dapi.kakao.com/v3/search/book";
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> properties = new HashMap<String, String>();
		params.put("sort", URLEncoder.encode("accuracy", "UTF-8"));
		params.put("query", URLEncoder.encode(isbn, "UTF-8"));
		params.put("target", URLEncoder.encode("isbn", "UTF-8"));
		properties.put("Authorization", serviceKey);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(HttpURLConnUtil.doGetRequest(url, properties, params));
		//List<Map<String, Object>> kakao = (List<Map<String, Object>>) jsonObject.get("documents");
		//System.out.println("카카오 documents" + kakao);
		// kakao.get(0).get("title");
		return (List<Map<String, Object>>) jsonObject.get("documents");
	}
	
	public Map<String, Object> ebookCrawler(String url) throws Exception{
		Document doc = Jsoup.connect(url).get();

		Elements elem = doc.select("div.info_section");
		Elements content = elem.select("p.desc");
		//System.out.println(content);
//		System.out.println(elem.size());
		Map<String, Object> detail = new HashMap<String, Object>();
		for(int i = 0; i < content.size(); i++) {
			detail.put(String.valueOf(i), content.get(i).text());			
		}
		//System.out.println(detail.get("0"));
		//System.out.println(detail.get("1"));
		//System.out.println(detail.get("2"));
		//System.out.println(detail.get("3"));
		//System.out.println(detail.get("4"));
		return detail;		
	}
	
	public int ebookAdd(Map<String, Object> map) {		
		return ebookDAO.ebookAdd(map);
	}
}

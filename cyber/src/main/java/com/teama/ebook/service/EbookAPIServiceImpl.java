package com.teama.ebook.service;

import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.teama.api.util.HttpURLConnUtil;
import com.teama.ebook.dao.EbookDAO;
import com.teama.ebook.dto.EbookDTO;

@Service("ebookAPIService")
public class EbookAPIServiceImpl implements EbookAPIService {
	@Autowired
	private EbookDAO ebookDAO;

	public List<EbookDTO> searchEbook(String searchType, String searchValue, int pageNo) {
		List<EbookDTO> bookInfo = null;
		Map<String, Object> searchInfo = Map.of("pageNo", pageNo, "searchType", searchType, "searchValue", searchValue);
		try {
			bookInfo = ebookSearch(searchInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookInfo;
	}

	@SuppressWarnings("unchecked")
	public List<EbookDTO> ebookSearch(Map<String, Object> map) throws Exception {
		String serviceKey = "e1105e7ff1c474e3dd03dffcdf49ade50be0ba92155bd191bdbf3be98af7d545";
		String url = "http://seoji.nl.go.kr/landingPage/SearchApi.do";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cert_key", serviceKey);
		params.put("result_style", "json");
		params.put("page_size", "10");
		params.put("page_no", String.valueOf(map.get("pageNo")));

		if (map.get("searchValue") != null) {
			params.put((String) map.get("searchType"), (String) map.get("searchValue"));
		}
		JSONParser parser = new JSONParser();
		String bookInfo = HttpURLConnUtil.doGetRequest(url, null, params);
		if(bookInfo!=null) {
			JSONObject jsonObject = (JSONObject) parser.parse(bookInfo);
			
			List<Map<String, Object>> docsMap = (List<Map<String, Object>>) jsonObject.get("docs");
			
			List<EbookDTO> info = new ArrayList<EbookDTO>();
			for (int i = 0; i < docsMap.size(); i++) {
				EbookDTO docs = new EbookDTO();
				if (!docsMap.get(i).get("EA_ISBN").equals("")) {
					docs.setIsbn((String) docsMap.get(i).get("EA_ISBN"));
					docs.setTitle((String) docsMap.get(i).get("TITLE"));
					docs.setTitle_url((String) docsMap.get(i).get("TITLE_URL"));
					docs.setAuthor((String) docsMap.get(i).get("AUTHOR"));
					docs.setPublisher((String) docsMap.get(i).get("PUBLISHER"));
					docs.setDatetime((String) docsMap.get(i).get("INPUT_DATE"));
					docs.setPrice((String) docsMap.get(i).get("PRE_PRICE"));
					docs.setPage((String) docsMap.get(i).get("PAGE"));
					docs.setBook_size((String) docsMap.get(i).get("BOOK_SIZE"));
					info.add(docs);
					System.out.println("pageNO = " + map.get("pageNo"));
					System.out.println("등록");
				} else {
					System.out.println("ISBN 없음 미등록");
				}
			}
			
			jsonObject.remove("docs");
			jsonObject.put("docs", info);
			return info;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<EbookDTO> ebookSearchKakao(String isbn) throws Exception {
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
		List<EbookDTO> kakao = (List<EbookDTO>) jsonObject.get("documents");
		return kakao;
	}

	public Map<String, Object> ebookCrawler(String url) throws Exception {
		Document doc = Jsoup.connect(url).get();

		Elements elem = doc.select("div.info_section");
		Elements content = elem.select("p.desc");
		Map<String, Object> detail = new HashMap<String, Object>();
		for (int i = 0; i < content.size(); i++) {
			detail.put(String.valueOf(i), content.get(i).text());
		}
		// System.out.println(detail.get("0"));
		// System.out.println(detail.get("1"));
		// System.out.println(detail.get("2"));
		// System.out.println(detail.get("3"));
		// System.out.println(detail.get("4"));
		return detail;
	}

	public int ebookAdd(Map<String, Object> map) {
		return ebookDAO.ebookAdd(map);
	}
}

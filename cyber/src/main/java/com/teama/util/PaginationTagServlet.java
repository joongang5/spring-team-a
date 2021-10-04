package com.teama.util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

public class PaginationTagServlet {

	private PaginationInfo paginationInfo;
	private String type;
	private String jsFunction;

	public String getHtml() {
		PaginationManager paginationManager = new DefaultPaginationManager();
		PaginationRenderer paginationRenderer = paginationManager.getRendererType(type);

		return paginationRenderer.renderPagination(paginationInfo, jsFunction);
	}

	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public void setType(String type) {
		this.type = type;
	}
}

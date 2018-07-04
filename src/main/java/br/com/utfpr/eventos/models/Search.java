package br.com.utfpr.eventos.models;

import java.io.Serializable;

public class Search implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

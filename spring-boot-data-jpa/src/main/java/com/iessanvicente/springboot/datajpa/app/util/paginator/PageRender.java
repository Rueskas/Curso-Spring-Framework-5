package com.iessanvicente.springboot.datajpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageRender<T> {
	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int elementosPorPagina;
	private int paginaActual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.elementosPorPagina = page.getSize();
		this.totalPaginas = page.getTotalPages();
		this.paginaActual = page.getNumber()+1;
		paginas = new ArrayList<>();
		
		
		int desde, hasta;
		if(totalPaginas <= elementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if(paginaActual <= elementosPorPagina/2) {
				desde = 1;
				hasta = elementosPorPagina;
			} else if (paginaActual >= totalPaginas-elementosPorPagina/2) {
				desde = totalPaginas - elementosPorPagina+1;
				hasta = elementosPorPagina;
			} else {
				desde = paginaActual - elementosPorPagina/2;
				hasta = elementosPorPagina;
			}
		}
		
		for(int i = 0 ; i < hasta; i++) {
			paginas.add(new PageItem(desde+i, paginaActual == desde+i));
		}
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean hasNext() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
}

package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("inicio", tiempoInicio);
		Thread.sleep(new Random().nextInt(1000));
		/*
		 * 
		 * response.sendRedirect(request.getContextPath().concat("/login));
		 * return false;
		 */
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long tiempoInicio = (long) request.getAttribute("inicio");
		long tiempoFin = System.currentTimeMillis();
		long tiempoTranscurrido = tiempoFin - tiempoInicio;
		if(modelAndView!=null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
		logger.info("TiempoTranscurridoInterceptor: preHandle() saliendo...");
		logger.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos");
	}
}

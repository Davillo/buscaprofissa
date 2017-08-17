package br.com.buscaprofissa.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



import br.com.buscaprofissa.config.JPAConfig;
import br.com.buscaprofissa.config.SecurityConfig;
import br.com.buscaprofissa.config.ServiceConfig;
import br.com.buscaprofissa.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new  Class<?>[] {JPAConfig.class,ServiceConfig.class,SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] {characterEncodingFilter};
		
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}

package br.com.buscaprofissa.config.init;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncondingFilter = servletContext.addFilter("encondingFilter", 
				new CharacterEncodingFilter());
			
		characterEncondingFilter.setInitParameter("encoding", "UTF-8");
		characterEncondingFilter.setInitParameter("forceEncoding", "true");
		characterEncondingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
	
}
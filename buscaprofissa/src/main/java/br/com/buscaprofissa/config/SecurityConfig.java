package br.com.buscaprofissa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import br.com.buscaprofissa.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/images/**")
		.antMatchers("/sourcebusca/**")
		.antMatchers("/sourceindex/**")
		.antMatchers("/sourcetemplate/**")
		.antMatchers("/categorias")
		.antMatchers("/index")
		.antMatchers("/fotos/**")
		.antMatchers("/buscar/**")
		.antMatchers("http://localhost:9090/buscaprofissa/fotos/**")
		.antMatchers("/novaSenha")
		.antMatchers("/recuperarSenha")
		.antMatchers("/sobre")
		.antMatchers("/RecuperarSenhaCodigo")
		.antMatchers("/cadastro");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
	.exceptionHandling().accessDeniedPage("/500")
	.and()
	.sessionManagement()
	.maximumSessions(1)
	.expiredUrl("/login");
		
	CharacterEncodingFilter filter = new CharacterEncodingFilter();
	filter.setEncoding("UTF-8"); 
	filter.setForceEncoding(true); 
	http.addFilterBefore(filter, CsrfFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
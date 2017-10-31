package br.com.buscaprofissa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = {CadastroUsuarioService.class,FotoStorage.class} )
public class ServiceConfig {
	

	
}

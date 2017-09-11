package br.com.buscaprofissa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import br.com.buscaprofissa.service.CadastroUsuarioService;
import br.com.buscaprofissa.storage.FotoStorage;
import br.com.buscaprofissa.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorageLocal(){
		return new FotoStorageLocal();
	}
	
}

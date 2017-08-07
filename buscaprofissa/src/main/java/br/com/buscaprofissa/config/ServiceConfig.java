package br.com.buscaprofissa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import br.com.buscaprofissa.service.CadastroUsuarioService;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {

}

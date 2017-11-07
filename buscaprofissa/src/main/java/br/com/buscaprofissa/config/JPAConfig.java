package br.com.buscaprofissa.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.Usuarios;

@Configuration
@EnableJpaRepositories(basePackageClasses = Usuarios.class,enableDefaultTransactions=false)
@EnableTransactionManagement
public class JPAConfig {

	/*@Bean
	public DataSource dataSourceLocal() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/BuscaProfissaDB");
	}*/
	
	@Bean
	public DataSource dataSourceProd() {

	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setUrl("jdbc:mysql://mysql.buscaprofissa.net.br/buscaprofissa01?useSSL=false");
	    dataSource.setUsername("buscaprofissa01");
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setPassword("mysqlbusca123");
	    dataSource.setInitialSize(5);
	   
	    return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(true); // não cria tabelas automaticamente
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Usuario.class.getPackage().getName());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	
	
}
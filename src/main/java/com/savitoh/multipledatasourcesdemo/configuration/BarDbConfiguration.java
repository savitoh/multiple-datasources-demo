package com.savitoh.multipledatasourcesdemo.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile({"dev", "test"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.savitoh.multipledatasourcesdemo.unidadejudicial.repository"},
                       entityManagerFactoryRef = "barEntityManagerFactory",
                       transactionManagerRef= "barTransactionManager")
public class BarDbConfiguration {

    
    @Bean(name = "barDataSourceProperties")
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "barDataSource")
    @ConfigurationProperties(prefix = "bar.datasource.configuration")
    public DataSource dataSource(@Autowired
                                 @Qualifier("barDataSourceProperties")
                                 DataSourceProperties dataSourceProperties) {
        /*return DataSourceBuilder.create().build();*/
        return dataSourceProperties.initializeDataSourceBuilder()
                                   .type(BasicDataSource.class).build();
    }

    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("barDataSource") DataSource dataSource
    ) {
        return
                builder
                    .dataSource(dataSource)
                    .packages("com.savitoh.multipledatasourcesdemo.unidadejudicial.model")
                    .persistenceUnit("bar")
                    .build();
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager barTransactionManager (
            @Qualifier("barEntityManagerFactory") EntityManagerFactory
                    barEntityManagerFactory
    ) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }
}

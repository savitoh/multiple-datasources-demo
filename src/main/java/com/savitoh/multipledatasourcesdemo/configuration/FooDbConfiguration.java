package com.savitoh.multipledatasourcesdemo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile({"dev", "test"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.savitoh.multipledatasourcesdemo.cartorio.repository"},
                       entityManagerFactoryRef = "fooEntityManagerFactory",
                       transactionManagerRef = "fooTransactionManager")
public class FooDbConfiguration {

    @Primary
    @Bean(name = "fooDataSourceProperties")
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "fooDataSource")
    @ConfigurationProperties(prefix = "foo.datasource.configuration")
    public HikariDataSource  dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder()
                                     .type(HikariDataSource.class)
                                     .build();
    }

    @Primary
    @Bean(name = "fooEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(EntityManagerFactoryBuilder builder,
                         @Qualifier("fooDataSource") 
                         HikariDataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("com.savitoh.multipledatasourcesdemo.cartorio.model")
                      .persistenceUnit("foo")
                      .build();
    }

    @Primary
    @Bean(name = "fooTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("fooEntityManagerFactory") LocalContainerEntityManagerFactoryBean
                    localEntityManagerFactory
    ) {
        return new JpaTransactionManager(localEntityManagerFactory.getObject());
    }
}

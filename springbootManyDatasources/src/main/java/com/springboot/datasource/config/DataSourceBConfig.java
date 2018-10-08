package com.springboot.datasource.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@MapperScan(basePackages="com.springboot.datasource.dao.datasourceB", sqlSessionFactoryRef="datasourceBSqlSessionFactory")
@Configuration
public class DataSourceBConfig {

	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasourceb")
    public DataSource slaveDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("datasourceBSqlSessionFactory")
    public SqlSessionFactory datasourceBSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slaveDatasource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/datasourceb/*"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.datasource.entity");
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean("dataSourceBTransactionManager")
    public DataSourceTransactionManager dataSourceBTransactionManager() {
        return new DataSourceTransactionManager(slaveDatasource());
    }
}

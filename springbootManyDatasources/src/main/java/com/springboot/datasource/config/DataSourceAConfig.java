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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@MapperScan(basePackages ="com.springboot.datasource.dao.datasourceA", sqlSessionFactoryRef = "datasourceASqlSessionFactory")
@Configuration
public class DataSourceAConfig {

	@Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasourcea")
    public DataSource DatasourceA() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("datasourceASqlSessionFactory")
    public SqlSessionFactory datasourceASqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DatasourceA());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/datasourcea/*"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.springboot.datasource.entity");
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean("DataSourceATransactionManager")
    public DataSourceTransactionManager DataSourceATransactionManager() {
        return new DataSourceTransactionManager(DatasourceA());
    }
}

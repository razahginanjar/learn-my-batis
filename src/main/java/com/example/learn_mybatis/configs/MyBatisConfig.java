package com.example.learn_mybatis.configs;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class MyBatisConfig {

    private String PATH;

    public MyBatisConfig(@Value(value = "${mybatis.mapper-locations}") String PATH) {
        this.PATH = PATH;
    }

//   @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/07112024_sql_exam?useUnicode=true&characterEncoding=UTF-8");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("Razahraden24&");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

//    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PATH));
        sessionFactoryBean.setTypeAliasesPackage("com.example.learn_mybatis.entities.Student");
        return sessionFactoryBean.getObject();
    }

//    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

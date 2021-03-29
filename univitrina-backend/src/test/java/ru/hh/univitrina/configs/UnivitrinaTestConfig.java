package ru.hh.univitrina.configs;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;
import ru.hh.univitrina.config.CommonConfig;

@Configuration
@Import({
    NabTestConfig.class,
    NabHibernateTestBaseConfig.class,
    CommonConfig.class
})
public class UnivitrinaTestConfig {
  @Bean
  PropertiesFactoryBean serviceProperties() {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("service-test.properties"));
    return propertiesFactoryBean;
  }

  @Bean
  String datacenter() {
    return "test";
  }
}

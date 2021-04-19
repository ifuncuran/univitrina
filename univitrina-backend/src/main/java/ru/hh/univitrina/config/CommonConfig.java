package ru.hh.univitrina.config;

import javax.inject.Inject;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.hibernate.datasource.RoutingDataSource;
import ru.hh.univitrina.client.HhApiClient;

@Configuration
@Import({
    NabHibernateCommonConfig.class,
    HhApiClient.class
})
public class CommonConfig {
  private final FileSettings fileSettings;

  @Inject
  public CommonConfig(FileSettings fileSettings) {
    this.fileSettings = fileSettings;
  }

  @Bean
  DataSource routingDataSource(DataSource hhServiceMasterDataSource) {
    return new RoutingDataSource(hhServiceMasterDataSource);
  }

  @Bean
  DataSource hhServiceMasterDataSource(DataSourceFactory dataSourceFactory) {
    return dataSourceFactory.create(DataSourceType.MASTER, false, fileSettings);
  }

  @Bean
  MappingConfig mappingConfig() {
    return new MappingConfig();
  }
}

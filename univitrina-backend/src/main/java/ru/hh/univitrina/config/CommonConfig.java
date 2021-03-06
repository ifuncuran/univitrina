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
import ru.hh.univitrina.dao.UniversityDao;
import ru.hh.univitrina.dao.ProfessionDao;
import ru.hh.univitrina.dao.SpecialtyDao;
import ru.hh.univitrina.resource.AreaResource;
import ru.hh.univitrina.resource.ProfessionResource;
import ru.hh.univitrina.resource.SpecialtyResource;
import ru.hh.univitrina.resource.SuggestResource;
import ru.hh.univitrina.resource.UniversityResource;
import ru.hh.univitrina.service.AreaService;
import ru.hh.univitrina.service.ProfessionService;
import ru.hh.univitrina.service.SpecialtyService;
import ru.hh.univitrina.service.UniversityService;

@Configuration
@Import({
    NabHibernateCommonConfig.class,
    HhApiClient.class,
    UniversityDao.class,
    ProfessionDao.class,
    SpecialtyDao.class,
    UniversityService.class,
    ProfessionService.class,
    SpecialtyService.class,
    AreaService.class,
    SuggestResource.class,
    ProfessionResource.class,
    SpecialtyResource.class,
    UniversityResource.class,
    AreaResource.class,
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
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.univitrina.entity");
    return mappingConfig;
  }
}

package ru.hh.univitrina;

import javax.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.nab.hibernate.transaction.TransactionalScope;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.ResourceHelper;
import ru.hh.nab.testbase.extensions.NabJunitWebConfig;
import ru.hh.nab.testbase.extensions.NabTestServer;
import ru.hh.nab.testbase.extensions.OverrideNabApplication;
import ru.hh.univitrina.configs.UnivitrinaTestConfig;

@NabJunitWebConfig(UnivitrinaTestConfig.class)
public abstract class UnivitrinaTestBase {

  @Inject
  protected TransactionalScope transactionalScope;

  @Inject
  private SessionFactory sessionFactory;

  @NabTestServer(overrideApplication = BaseNabApplication.class)
  public ResourceHelper resourceHelper;


  public static class BaseNabApplication implements OverrideNabApplication {
    @Override
    public NabApplication getNabApplication() {
      return UnivitrinaWebapp.buildApplication();
    }
  }
}

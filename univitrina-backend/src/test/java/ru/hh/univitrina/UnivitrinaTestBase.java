package ru.hh.univitrina;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
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

  @AfterEach
  public void clearData() {
      transactionalScope.write(() -> {
        getSession().createNativeQuery("TRUNCATE university RESTART IDENTITY CASCADE").executeUpdate();
        getSession().createNativeQuery("TRUNCATE specialty RESTART IDENTITY CASCADE").executeUpdate();
        getSession().createNativeQuery("TRUNCATE training_direction RESTART IDENTITY CASCADE").executeUpdate();
        getSession().createNativeQuery("TRUNCATE section RESTART IDENTITY CASCADE").executeUpdate();
        getSession().createNativeQuery("TRUNCATE profession RESTART IDENTITY CASCADE").executeUpdate();
      });
  }

  protected Session getSession(){
    return sessionFactory.getCurrentSession();
  }

  protected void saveObjects(Object... objects) {
    transactionalScope.write(() -> {
      for (Object o : objects) {
        getSession().saveOrUpdate(o);
      }
    });
  }

  @NabTestServer(overrideApplication = BaseNabApplication.class)
  public ResourceHelper resourceHelper;


  public static class BaseNabApplication implements OverrideNabApplication {
    @Override
    public NabApplication getNabApplication() {
      return UnivitrinaWebapp.buildApplication();
    }
  }
}

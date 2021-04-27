package ru.hh.univitrina.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDao {

  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> T getById(Class<T> clazz, Integer id) {
    return getSession().get(clazz, id);
  }

  public void save(Object o) {
    getSession().save(o);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}

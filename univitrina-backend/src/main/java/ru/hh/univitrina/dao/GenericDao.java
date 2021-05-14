package ru.hh.univitrina.dao;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDao {

  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> Optional<T> getById(Class<T> clazz, Integer id) {
    T value = getSession().get(clazz, id);
    return value == null ? Optional.empty() : Optional.of(value);
  }

  public void save(Object o) {
    getSession().save(o);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}

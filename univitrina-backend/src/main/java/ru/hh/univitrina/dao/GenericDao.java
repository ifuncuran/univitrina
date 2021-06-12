package ru.hh.univitrina.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDao {

  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> Optional<T> getById(Class<T> clazz, Integer id) {
    T value = getSession().get(clazz, id);
    return value == null ? Optional.empty() : Optional.of(value);
  }

  public <T> List<T> getAll(Class<T> clazz) {
    Session session = getSession();
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<T> criteria = cb.createQuery(clazz);
    criteria.from(clazz);
    return session.createQuery(criteria).getResultList();
  }

  public void save(Object o) {
    getSession().save(o);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}

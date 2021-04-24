package ru.hh.univitrina.dao;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.univitrina.entity.Specialty;

public class SpecialtyDao extends GenericDao {

  @Inject
  public SpecialtyDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<Specialty> getSearchSuggestion(String prefix, Integer limit) {
    return getSession()
        .createQuery("SELECT sp FROM Specialty sp " +
                "WHERE LOWER(sp.name) LIKE LOWER(:prefix) " +
                "ORDER BY sp.name",
            Specialty.class)
        .setParameter("prefix", prefix + "%")
        .setMaxResults(limit)
        .list();
  }
}

package ru.hh.univitrina.dao;

import org.hibernate.SessionFactory;
import ru.hh.univitrina.entity.Profession;

import javax.inject.Inject;
import java.util.List;

public class ProfessionDao extends GenericDao {

  @Inject
  public ProfessionDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<Profession> getSearchSuggestion(String prefix, Integer limit) {
    return getSession()
        .createQuery("SELECT p FROM Profession p " +
                "WHERE LOWER(p.name) LIKE LOWER(:prefix) " +
                "ORDER BY p.name",
            Profession.class)
        .setParameter("prefix", prefix + "%")
        .setMaxResults(limit)
        .list();
  }

  public List<Profession> getFilteredByHhapiId(Integer page, Integer perPage, String hhapiId) {
    return getSession()
        .createQuery("SELECT p FROM Profession p " +
                "WHERE p.hhapiId = :hhapiId " +
                "ORDER BY p.name",
            Profession.class)
        .setParameter("hhapiId", hhapiId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }

  public List<Profession> getFilteredBySpecialty(Integer page, Integer perPage, Integer specialtyId) {
    return getSession()
        .createQuery("SELECT p FROM Profession p " +
                "JOIN FETCH p.specialtySet sp " +
                "WHERE sp.id = :specialtyId " +
                "ORDER BY p.name",
            Profession.class)
        .setParameter("specialtyId", specialtyId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }

}

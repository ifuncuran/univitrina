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

  public Profession getProfessionById(Integer professionId) {
    return getSession()
            .createQuery("SELECT p FROM Profession p " +
                    "WHERE p.id = :id",
                Profession.class)
            .setParameter("id", professionId)
            .getSingleResult();
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

  public List<Profession> getFilteredByProfessionNameAndSpecialtyId(String professionName, Integer specialtyId) {
    return getSession()
        .createQuery("SELECT p FROM Profession p " +
                "JOIN FETCH p.specialtySet sp " +
                "WHERE sp.id = :specialtyId " +
                "AND p.name LIKE :professionName " +
                "ORDER BY p.name",
            Profession.class)
        .setParameter("specialtyId", specialtyId)
        .setParameter("professionName", "%" + professionName + "%")
        .list();
  }

  public List<Profession> getFilteredByProfessionName(String professionName) {
    return getSession()
            .createQuery("SELECT p FROM Profession p " +
                            "WHERE p.name LIKE :professionName " +
                            "ORDER BY p.name",
                    Profession.class)
            .setParameter("professionName", "%" + professionName + "%")
            .list();
  }

  public List<Profession> getFilteredBySpecialtyId(Integer specialtyId) {
    return getSession()
            .createQuery("SELECT p FROM Profession p " +
                            "JOIN FETCH p.specialtySet sp " +
                            "WHERE sp.id = :specialtyId " +
                            "ORDER BY p.name",
                    Profession.class)
            .setParameter("specialtyId", specialtyId)
            .list();
  }

  public List<Profession> getAll() {
    return getSession()
            .createQuery("SELECT p FROM Profession p " +
                            "ORDER BY p.name",
                    Profession.class)
            .list();
  }

}

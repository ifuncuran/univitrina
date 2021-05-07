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

  public Specialty getBySpecialityCode(String specialityCode) {
    return getSession()
      .createQuery("SELECT s FROM Specialty s " +
                      "WHERE s.code = :specialityCode",
              Specialty.class)
      .setParameter("specialityCode", specialityCode)
      .getSingleResult();
  }

  public List<Specialty> getFilteredByTrainingDirection(Integer page, Integer perPage, Integer trainingDirectionId) {
    return getSession()
      .createQuery("SELECT s FROM Specialty s " +
                      "JOIN FETCH s.trainingDirection td " +
                      "WHERE td.id = :trainingDirectionId " +
                      "ORDER BY s.name",
              Specialty.class)
      .setParameter("trainingDirectionId", trainingDirectionId)
      .setFirstResult(page * perPage)
      .setMaxResults(perPage)
      .list();
  }

  public List<Specialty> getFilteredByProfession(Integer page, Integer perPage, Integer professionId) {
    return getSession()
      .createQuery("SELECT s FROM Specialty s " +
                      "JOIN FETCH s.professionSet ps " +
                      "WHERE ps.id = :professionId " +
                      "ORDER BY s.name",
              Specialty.class)
      .setParameter("professionId", professionId)
      .setFirstResult(page * perPage)
      .setMaxResults(perPage)
      .list();
  }

  public List<Specialty> getFilteredByUniversity(Integer page, Integer perPage, Integer universityId) {
    return getSession()
      .createQuery("SELECT s FROM Specialty s " +
                      "JOIN FETCH s.universitySet us " +
                      "WHERE us.id = :universityId " +
                      "ORDER BY s.name",
              Specialty.class)
      .setParameter("universityId", universityId)
      .setFirstResult(page * perPage)
      .setMaxResults(perPage)
      .list();
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

package ru.hh.univitrina.dao;

import org.hibernate.SessionFactory;
import ru.hh.univitrina.entity.University;

import javax.inject.Inject;
import java.util.List;

public class UniversityDao extends GenericDao {

  @Inject
  public UniversityDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<University> getFilteredByArea(Integer page, Integer perPage, Integer areaId) {
    return getSession()
        .createQuery("SELECT u FROM University u " +
                "WHERE u.areaId = :areaId " +
                "ORDER BY u.name",
            University.class)
        .setParameter("areaId", areaId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }

  public List<University> getFilteredBySpecialty(Integer page, Integer perPage, Integer specialtyId) {
    return getSession()
        .createQuery("SELECT u FROM University u " +
                "JOIN FETCH u.specialtySet sp " +
                "WHERE sp.id = :specialtyId " +
                "ORDER BY u.name",
            University.class)
        .setParameter("specialtyId", specialtyId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }

  public List<University> getFilteredByTrainingDirection(Integer page, Integer perPage, Integer trainingDirectionId) {
    return getSession()
        .createQuery("SELECT u FROM University u " +
                "JOIN FETCH u.specialtySet sp " +
                "JOIN FETCH sp.trainingDirection td " +
                "WHERE td.id = :trainingDirectionId " +
                "ORDER BY u.name",
            University.class)
        .setParameter("trainingDirectionId", trainingDirectionId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }

  public List<University> getFilteredBySection(Integer page, Integer perPage, Integer sectionId) {
    return getSession()
        .createQuery("SELECT u FROM University u " +
                "JOIN FETCH u.specialtySet sp " +
                "JOIN FETCH sp.trainingDirection td " +
                "JOIN FETCH td.section sect " +
                "WHERE sect.id = :sectionId " +
                "ORDER BY u.name",
            University.class)
        .setParameter("sectionId", sectionId)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .list();
  }
}

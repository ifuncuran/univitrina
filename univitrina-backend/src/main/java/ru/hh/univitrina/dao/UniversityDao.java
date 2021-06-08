package ru.hh.univitrina.dao;

import org.hibernate.SessionFactory;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.University;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

  public List<University> getSearchSuggestion(String prefix, Integer limit) {
    return getSession()
        .createQuery("SELECT u FROM University u " +
                "WHERE LOWER(u.name) LIKE LOWER(:prefix) " +
                "ORDER BY u.name",
            University.class)
        .setParameter("prefix", prefix + "%")
        .setMaxResults(limit)
        .list();
  }

  public List<University> getSearchByCriteria(Integer areaId, Integer specialtyId, String text, Integer page, Integer perPage) {
    CriteriaBuilder cb = getSession().getCriteriaBuilder();
    CriteriaQuery<University> universityCriteria = cb.createQuery(University.class);
    Root<University> universityRoot = universityCriteria.from(University.class);

    universityCriteria.select(universityRoot);

    List<Predicate> predicates = new ArrayList<>();
    if (areaId != null) {
      predicates.add(cb.equal(universityRoot.get("areaId"), areaId.toString()));
    }
    if (specialtyId != null) {
      Join<University, Specialty> specialtyJoin = universityRoot.join("specialtySet");
      predicates.add(cb.equal(specialtyJoin.get("id"), specialtyId));
    }
    if (text != null && !text.equals("")) {
      predicates.add(cb.like(cb.lower(universityRoot.get("name")), "%" + text.toLowerCase() + "%"));
    }

    universityCriteria.where(predicates.toArray(new Predicate[0]));

    return getSession()
        .createQuery(universityCriteria)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .getResultList();
  }
}

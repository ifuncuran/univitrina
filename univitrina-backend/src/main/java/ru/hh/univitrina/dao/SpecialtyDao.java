package ru.hh.univitrina.dao;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import ru.hh.univitrina.entity.Profession;
import ru.hh.univitrina.entity.Section;
import ru.hh.univitrina.entity.Specialty;
import ru.hh.univitrina.entity.TrainingDirection;
import ru.hh.univitrina.entity.University;

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

  public List<Specialty> getFilteredBySection(Integer page, Integer perPage, Integer sectionId) {
    return getSession()
        .createQuery("SELECT s FROM Specialty s " +
                "JOIN FETCH s.trainingDirection td " +
                "JOIN FETCH td.section sect " +
                "WHERE sect.id = :sectionId " +
                "ORDER BY s.name",
            Specialty.class)
        .setParameter("sectionId", sectionId)
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

  public List<Specialty> getSearchByCriteria(String text, Integer trainingDirectionId,
                                              Integer sectionId, Integer professionId, Integer universityId,
                                              Integer page, Integer perPage){
    CriteriaBuilder cb = getSession().getCriteriaBuilder();
    CriteriaQuery<Specialty> specialtyCriteria = cb.createQuery(Specialty.class);
    Root<Specialty> specialtyRoot = specialtyCriteria.from(Specialty.class);

    specialtyCriteria.select(specialtyRoot);

    List<Predicate> predicates = new ArrayList<>();

    if(text != null && !text.equals("")){
      predicates.add(cb.like(cb.lower(specialtyRoot.get("name")), "%" + text.toLowerCase() + "%"));
    }

    if(trainingDirectionId != null){
      Join<Specialty, TrainingDirection> trainingDirectionJoin = specialtyRoot.join("trainingDirection");
      predicates.add(cb.equal(trainingDirectionJoin.get("id"), trainingDirectionId));
    }

    if(sectionId != null){
      Join<Specialty, TrainingDirection> trainingDirectionJoin = specialtyRoot.join("trainingDirection");
      Join<TrainingDirection, Section> sectionJoin = trainingDirectionJoin.join("section");
      predicates.add(cb.equal(sectionJoin.get("id"), sectionId));
    }

    if(professionId != null){
      Join<Specialty, Profession> professionJoin = specialtyRoot.join("professionSet");
      predicates.add(cb.equal(professionJoin.get("id"), professionId));
    }

    if(universityId != null){
      Join<Specialty, University> universityJoin = specialtyRoot.join("universitySet");
      predicates.add(cb.equal(universityJoin.get("id"), universityId));
    }

    specialtyCriteria.where(predicates.toArray(new Predicate[0]));

    return getSession()
        .createQuery(specialtyCriteria)
        .setFirstResult(page * perPage)
        .setMaxResults(perPage)
        .getResultList();
  }
}

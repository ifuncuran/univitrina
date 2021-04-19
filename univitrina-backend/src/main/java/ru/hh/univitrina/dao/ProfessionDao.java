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
}

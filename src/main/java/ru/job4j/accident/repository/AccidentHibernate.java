package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

public class AccidentHibernate implements Store {
    private final SessionFactory sf;

    private static final String GET_ALL_ACCIDENTS
            = "select distinct a from Accident a join a.type join fetch a.rules order by a.id";
    private static final String GET_ALL_TYPES = "from AccidentType";
    private static final String GET_ALL_RULES = "from Rule";
    private static final String FIND_BY_ID
            = "select distinct a from Accident a join a.type join fetch a.rules where a.id = :id";
    private static final String FIND_TYPE_BY_ID = "select t from AccidentType t where t.id = :id";
    private static final String FIND_RULE_BY_ID = "select r from Rule r where r.id = :id";

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public List<Accident> getAll() {
        return getObjects(GET_ALL_ACCIDENTS, Accident.class, sf);
    }

    public List<AccidentType> getTypes() {
        return getObjects(GET_ALL_TYPES, AccidentType.class, sf);
    }

    public List<Rule> getRules() {
        return getObjects(GET_ALL_RULES, Rule.class, sf);
    }

    public void createOrUpdate(Accident accident) {
        tx(session -> session.merge(accident), sf);
    }

    public Optional<Accident> findById(int id) {
        return tx(session -> {
            final Query<Accident> query = session.createQuery(FIND_BY_ID, Accident.class)
                    .setParameter("id", id);
            return query.uniqueResultOptional();
        }, sf);
    }

    public AccidentType findTypeById(int id) {
        return findObjectByID(FIND_TYPE_BY_ID, AccidentType.class, id, sf);
    }

    public Rule findRuleById(int id) {
        return findObjectByID(FIND_RULE_BY_ID, Rule.class, id, sf);
    }
}

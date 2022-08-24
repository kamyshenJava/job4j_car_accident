package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Function;

public interface Store {
    default <T> T tx(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    default <T> T findObjectByID(String sql, Class<T> cls, int id, SessionFactory sf) {
        return tx(session -> {
            final Query<T> query = session.createQuery(sql, cls)
                    .setParameter("id", id);
            return query.uniqueResult();
        }, sf);
    }

    default <T> List<T> getObjects(String sql, Class<T> cls, SessionFactory sf) {
        return tx(session -> {
            final Query<T> query = session.createQuery(sql, cls);
            return query.list();
        }, sf);
    }
}

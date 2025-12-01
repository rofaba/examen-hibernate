package org.example.dao;

import org.example.modelo.Opinion;

import java.util.List;

public class OpinionRepository implements OpinionDAO{

    @Override
    public Opinion findById(Integer id) {
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Opinion.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public List<Opinion> findAll() {
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Opinion", Opinion.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Opinion opinion) {
        var tx = (org.hibernate.Transaction) null;
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.saveOrUpdate(session.merge(opinion));

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Opinion opinion) {
        var tx = (org.hibernate.Transaction) null;
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(opinion);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        }
    }

    @Override
    public List<Opinion> findByPeliculaId(Integer peliculaId) {
            try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
                String hql = "FROM Opinion WHERE pelicula.id = :peliculaId";
                return session.createQuery(hql, Opinion.class)
                        .setParameter("peliculaId", peliculaId)
                        .list();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
}

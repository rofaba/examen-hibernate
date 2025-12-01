package org.example.dao;

import org.example.modelo.Pelicula;

import java.util.List;

public class PeliculaRepository implements PeliculaDAO {

    public List<Pelicula> findAll() {
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Pelicula", Pelicula.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Pelicula findById(Integer id) {
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pelicula.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void save(Pelicula pelicula) {
        var tx = (org.hibernate.Transaction) null;
        try (var session = org.example.config.HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(pelicula);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

}

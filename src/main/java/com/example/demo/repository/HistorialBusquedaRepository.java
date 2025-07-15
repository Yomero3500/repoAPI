//HistorialBusquedaRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.HistorialBusqueda;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class HistorialBusquedaRepository {
    public HistorialBusqueda save(HistorialBusqueda busqueda) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (busqueda.getIdBusqueda() == null) {
                em.persist(busqueda);
            } else {
                busqueda = em.merge(busqueda);
            }
            em.getTransaction().commit();
            return busqueda;
        } finally {
            em.close();
        }
    }

    public List<HistorialBusqueda> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT h FROM HistorialBusqueda h", HistorialBusqueda.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<HistorialBusqueda> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            HistorialBusqueda busqueda = em.find(HistorialBusqueda.class, id);
            return Optional.ofNullable(busqueda);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            HistorialBusqueda busqueda = em.find(HistorialBusqueda.class, id);
            if (busqueda != null) {
                em.remove(busqueda);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<HistorialBusqueda> findByUsuarioIdUsuario(Integer idUsuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT h FROM HistorialBusqueda h WHERE h.usuario.idUsuario = :idUsuario " +
                                    "ORDER BY h.fechaBusqueda DESC", HistorialBusqueda.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<HistorialBusqueda> findByTerminoBusquedaContainingIgnoreCase(String termino) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT h FROM HistorialBusqueda h WHERE LOWER(h.terminoBusqueda) LIKE LOWER(:termino)",
                            HistorialBusqueda.class)
                    .setParameter("termino", "%" + termino + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteByUsuarioIdUsuario(Integer idUsuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM HistorialBusqueda h WHERE h.usuario.idUsuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
//FavoritoRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.Favorito;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class FavoritoRepository {
    public Favorito save(Favorito favorito) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (favorito.getIdFavorito() == null) {
                em.persist(favorito);
            } else {
                favorito = em.merge(favorito);
            }
            em.getTransaction().commit();
            return favorito;
        } finally {
            em.close();
        }
    }

    public List<Favorito> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Favorito f", Favorito.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Favorito> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Favorito favorito = em.find(Favorito.class, id);
            return Optional.ofNullable(favorito);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            Favorito favorito = em.find(Favorito.class, id);
            if (favorito != null) {
                em.remove(favorito);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Favorito> findByUsuarioIdUsuario(Integer idUsuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT f FROM Favorito f WHERE f.usuario.idUsuario = :idUsuario", Favorito.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Favorito> findByEquipoIdEquipo(Integer idEquipo) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT f FROM Favorito f WHERE f.equipo.idEquipo = :idEquipo", Favorito.class)
                    .setParameter("idEquipo", idEquipo)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public boolean existsByUsuarioIdUsuarioAndEquipoIdEquipo(Integer idUsuario, Integer idEquipo) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(f) FROM Favorito f WHERE f.usuario.idUsuario = :idUsuario AND f.equipo.idEquipo = :idEquipo", Long.class)
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idEquipo", idEquipo)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }
}
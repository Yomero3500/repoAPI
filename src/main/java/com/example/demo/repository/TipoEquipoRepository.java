//TipoEquipoRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.TipoEquipo;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TipoEquipoRepository {
    public TipoEquipo save(TipoEquipo tipoEquipo) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (tipoEquipo.getIdTipo() == null) {
                em.persist(tipoEquipo);
            } else {
                tipoEquipo = em.merge(tipoEquipo);
            }
            em.getTransaction().commit();
            return tipoEquipo;
        } finally {
            em.close();
        }
    }

    public List<TipoEquipo> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM TipoEquipo t", TipoEquipo.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<TipoEquipo> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            TipoEquipo tipoEquipo = em.find(TipoEquipo.class, id);
            return Optional.ofNullable(tipoEquipo);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            TipoEquipo tipoEquipo = em.find(TipoEquipo.class, id);
            if (tipoEquipo != null) {
                em.remove(tipoEquipo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
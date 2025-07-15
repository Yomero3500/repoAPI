// MarcaRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.Marca;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MarcaRepository {
    public Marca save(Marca marca) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (marca.getIdMarca() == null) {
                em.persist(marca);
            } else {
                marca = em.merge(marca);
            }
            em.getTransaction().commit();
            return marca;
        } finally {
            em.close();
        }
    }

    public List<Marca> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Marca> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Marca marca = em.find(Marca.class, id);
            return Optional.ofNullable(marca);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            Marca marca = em.find(Marca.class, id);
            if (marca != null) {
                em.remove(marca);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
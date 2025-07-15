//ResenaRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.Resena;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ResenaRepository {
    public Resena save(Resena resena) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (resena.getIdResena() == null) {
                em.persist(resena);
            } else {
                resena = em.merge(resena);
            }
            em.getTransaction().commit();
            return resena;
        } finally {
            em.close();
        }
    }

    public List<Resena> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Resena r", Resena.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Resena> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Resena resena = em.find(Resena.class, id);
            return Optional.ofNullable(resena);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            Resena resena = em.find(Resena.class, id);
            if (resena != null) {
                em.remove(resena);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Resena> findByProveedorIdProveedor(Integer idProveedor) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT r FROM Resena r WHERE r.proveedor.idProveedor = :idProveedor", Resena.class)
                    .setParameter("idProveedor", idProveedor)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Resena> findByUsuarioIdUsuario(Integer idUsuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT r FROM Resena r WHERE r.usuario.idUsuario = :idUsuario", Resena.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
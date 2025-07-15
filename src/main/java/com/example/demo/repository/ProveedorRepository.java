//ProveedorRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.Proveedor;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProveedorRepository {
    public Proveedor save(Proveedor proveedor) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (proveedor.getIdProveedor() == null) {
                em.persist(proveedor);
            } else {
                proveedor = em.merge(proveedor);
            }
            em.getTransaction().commit();
            return proveedor;
        } finally {
            em.close();
        }
    }

    public List<Proveedor> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Proveedor> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Proveedor proveedor = em.find(Proveedor.class, id);
            return Optional.ofNullable(proveedor);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            Proveedor proveedor = em.find(Proveedor.class, id);
            if (proveedor != null) {
                em.remove(proveedor);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Optional<Proveedor> findByCorreoContacto(String correoContacto) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            List<Proveedor> result = em.createQuery(
                            "SELECT p FROM Proveedor p WHERE p.correoContacto = :correo", Proveedor.class)
                    .setParameter("correo", correoContacto)
                    .getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            em.close();
        }
    }
}
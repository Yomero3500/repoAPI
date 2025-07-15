//UsuarioRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.Usuario;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    public Usuario save(Usuario usuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (usuario.getIdUsuario() == null) {
                em.persist(usuario);
            } else {
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
            return usuario;
        } finally {
            em.close();
        }
    }

    public List<Usuario> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Usuario> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            Usuario usuario = em.find(Usuario.class, id);
            return Optional.ofNullable(usuario);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Optional<Usuario> findByCorreo(String correo) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            List<Usuario> result = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo", correo)
                    .getResultList();
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } finally {
            em.close();
        }
    }
}
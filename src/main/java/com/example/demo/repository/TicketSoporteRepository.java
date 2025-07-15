//TicketSoporteRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.TicketSoporte;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TicketSoporteRepository {
    public TicketSoporte save(TicketSoporte ticket) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (ticket.getIdTicket() == null) {
                em.persist(ticket);
            } else {
                ticket = em.merge(ticket);
            }
            em.getTransaction().commit();
            return ticket;
        } finally {
            em.close();
        }
    }

    public List<TicketSoporte> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM TicketSoporte t", TicketSoporte.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<TicketSoporte> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            TicketSoporte ticket = em.find(TicketSoporte.class, id);
            return Optional.ofNullable(ticket);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            TicketSoporte ticket = em.find(TicketSoporte.class, id);
            if (ticket != null) {
                em.remove(ticket);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<TicketSoporte> findByUsuarioIdUsuario(Integer idUsuario) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT t FROM TicketSoporte t WHERE t.usuario.idUsuario = :idUsuario", TicketSoporte.class)
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<TicketSoporte> findByEstado(TicketSoporte.Estado estado) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT t FROM TicketSoporte t WHERE t.estado = :estado", TicketSoporte.class)
                    .setParameter("estado", estado)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
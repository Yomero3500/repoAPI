//EquipoMedicoRepository.java
package com.example.demo.repository;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.model.EquipoMedico;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EquipoMedicoRepository {
    public EquipoMedico save(EquipoMedico equipoMedico) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            if (equipoMedico.getIdEquipo() == null) {
                em.persist(equipoMedico);
            } else {
                equipoMedico = em.merge(equipoMedico);
            }
            em.getTransaction().commit();
            return equipoMedico;
        } finally {
            em.close();
        }
    }

    public List<EquipoMedico> findAll() {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM EquipoMedico e", EquipoMedico.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<EquipoMedico> findById(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            EquipoMedico equipo = em.find(EquipoMedico.class, id);
            return Optional.ofNullable(equipo);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = DatabaseConfig.createEntityManager();
        try {
            em.getTransaction().begin();
            EquipoMedico equipo = em.find(EquipoMedico.class, id);
            if (equipo != null) {
                em.remove(equipo);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
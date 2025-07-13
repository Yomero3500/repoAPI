package com.example.demo.repository;

import com.example.demo.model.HistorialBusqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HistorialBusquedaRepository extends JpaRepository<HistorialBusqueda, Integer> {
    List<HistorialBusqueda> findByUsuarioIdUsuario(Integer idUsuario);
    List<HistorialBusqueda> findByTerminoBusquedaContainingIgnoreCase(String termino);
    List<HistorialBusqueda> findByUsuarioIdUsuarioOrderByFechaBusquedaDesc(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("DELETE FROM HistorialBusqueda h WHERE h.usuario.idUsuario = :idUsuario")
    void deleteByUsuarioIdUsuario(Integer idUsuario);
}
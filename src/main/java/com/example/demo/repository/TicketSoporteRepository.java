package com.example.demo.repository;

import com.example.demo.model.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Integer> {
    List<TicketSoporte> findByUsuarioIdUsuario(Integer idUsuario);
    List<TicketSoporte> findByEstado(TicketSoporte.Estado estado);
}
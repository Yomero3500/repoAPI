package com.example.demo.repository;

import com.example.demo.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    // Cambia findByProveedorId -> findByProveedorIdProveedor
    List<Resena> findByProveedorIdProveedor(Integer idProveedor);

    // Cambia findByUsuarioId -> findByUsuarioIdUsuario
    List<Resena> findByUsuarioIdUsuario(Integer idUsuario);
}
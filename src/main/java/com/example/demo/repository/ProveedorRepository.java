package com.example.demo.repository;

import com.example.demo.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    // Método adicional para validar correo único
    Proveedor findByCorreoContacto(String correoContacto);
}
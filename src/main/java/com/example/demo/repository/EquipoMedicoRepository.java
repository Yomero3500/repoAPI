package com.example.demo.repository;

import com.example.demo.model.EquipoMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoMedicoRepository extends JpaRepository<EquipoMedico, Integer> {

}
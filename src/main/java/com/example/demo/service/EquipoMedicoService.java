//EquipoMedicoService.java
package com.example.demo.service;

import com.example.demo.model.EquipoMedico;
import com.example.demo.repository.EquipoMedicoRepository;
import java.util.List;
import java.util.Optional;

public class EquipoMedicoService {
    private final EquipoMedicoRepository equipoMedicoRepository;

    public EquipoMedicoService(EquipoMedicoRepository equipoMedicoRepository) {
        this.equipoMedicoRepository = equipoMedicoRepository;
    }

    public EquipoMedico saveEquipoMedico(EquipoMedico equipoMedico) {
        return equipoMedicoRepository.save(equipoMedico);
    }

    public List<EquipoMedico> getAllEquiposMedicos() {
        return equipoMedicoRepository.findAll();
    }

    public Optional<EquipoMedico> getEquipoMedicoById(Integer id) {
        return equipoMedicoRepository.findById(id);
    }

    public void deleteEquipoMedico(Integer id) {
        equipoMedicoRepository.delete(id);
    }
}
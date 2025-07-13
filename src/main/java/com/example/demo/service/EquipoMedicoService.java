package com.example.demo.service;

import com.example.demo.model.EquipoMedico;
import com.example.demo.repository.EquipoMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoMedicoService {
    @Autowired
    private EquipoMedicoRepository equipoMedicoRepository;

    public EquipoMedico saveEquipoMedico(EquipoMedico equipoMedico) {
        return equipoMedicoRepository.save(equipoMedico);
    }

    public List<EquipoMedico> getAllEquiposMedicos() {
        return equipoMedicoRepository.findAll();
    }

    public EquipoMedico getEquipoMedicoById(Integer id) {
        return equipoMedicoRepository.findById(id).orElse(null);
    }

    public void deleteEquipoMedico(Integer id) {
        equipoMedicoRepository.deleteById(id);
    }

}
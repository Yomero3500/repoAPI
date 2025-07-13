package com.example.demo.controller;

import com.example.demo.model.EquipoMedico;
import com.example.demo.service.EquipoMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos-medicos")
public class EquipoMedicoController {
    @Autowired
    private EquipoMedicoService equipoMedicoService;

    @PostMapping
    public EquipoMedico createEquipoMedico(@RequestBody EquipoMedico equipoMedico) {
        return equipoMedicoService.saveEquipoMedico(equipoMedico);
    }

    @GetMapping
    public List<EquipoMedico> getAllEquiposMedicos() {
        return equipoMedicoService.getAllEquiposMedicos();
    }

    @GetMapping("/{id}")
    public EquipoMedico getEquipoMedicoById(@PathVariable Integer id) {
        return equipoMedicoService.getEquipoMedicoById(id);
    }

    @PutMapping("/{id}")
    public EquipoMedico updateEquipoMedico(@PathVariable Integer id, @RequestBody EquipoMedico equipoMedico) {
        equipoMedico.setIdEquipo(id);
        return equipoMedicoService.saveEquipoMedico(equipoMedico);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipoMedico(@PathVariable Integer id) {
        equipoMedicoService.deleteEquipoMedico(id);
    }

}
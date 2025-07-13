package com.example.demo.controller;

import com.example.demo.model.TipoEquipo;
import com.example.demo.service.TipoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-equipo")
public class TipoEquipoController {
    @Autowired
    private TipoEquipoService tipoEquipoService;

    // Crear un tipo de equipo
    @PostMapping
    public TipoEquipo createTipoEquipo(@RequestBody TipoEquipo tipoEquipo) {
        return tipoEquipoService.saveTipoEquipo(tipoEquipo);
    }

    // Obtener todos los tipos de equipo
    @GetMapping
    public List<TipoEquipo> getAllTiposEquipo() {
        return tipoEquipoService.getAllTiposEquipo();
    }

    // Obtener un tipo de equipo por ID
    @GetMapping("/{id}")
    public TipoEquipo getTipoEquipoById(@PathVariable Integer id) {
        return tipoEquipoService.getTipoEquipoById(id);
    }

    // Actualizar un tipo de equipo
    @PutMapping("/{id}")
    public TipoEquipo updateTipoEquipo(@PathVariable Integer id, @RequestBody TipoEquipo tipoEquipo) {
        tipoEquipo.setIdTipo(id); // Asegura que el ID coincida
        return tipoEquipoService.saveTipoEquipo(tipoEquipo);
    }

    // Eliminar un tipo de equipo
    @DeleteMapping("/{id}")
    public void deleteTipoEquipo(@PathVariable Integer id) {
        tipoEquipoService.deleteTipoEquipo(id);
    }
}
package com.example.demo.controller;

import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    // Crear marca
    @PostMapping
    public Marca createMarca(@RequestBody Marca marca) {
        return marcaService.saveMarca(marca);
    }

    // Obtener todas las marcas
    @GetMapping
    public List<Marca> getAllMarcas() {
        return marcaService.getAllMarcas();
    }

    // Obtener marca por ID
    @GetMapping("/{id}")
    public Marca getMarcaById(@PathVariable Integer id) {
        return marcaService.getMarcaById(id);
    }

    // Actualizar marca
    @PutMapping("/{id}")
    public Marca updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setIdMarca(id); // Asegurar que el ID coincida
        return marcaService.saveMarca(marca);
    }

    // Eliminar marca
    @DeleteMapping("/{id}")
    public void deleteMarca(@PathVariable Integer id) {
        marcaService.deleteMarca(id);
    }
}
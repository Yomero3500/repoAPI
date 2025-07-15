// MarcaService.java
package com.example.demo.service;

import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Marca saveMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> getMarcaById(Integer id) {
        return marcaRepository.findById(id);
    }

    public void deleteMarca(Integer id) {
        marcaRepository.delete(id);
    }
}
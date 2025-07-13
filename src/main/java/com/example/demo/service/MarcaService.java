package com.example.demo.service;

import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    // Crear y actualizar marca
    public Marca saveMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    // Obtener todas las marcas
    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    // Obtener marca por ID
    public Marca getMarcaById(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    // Eliminar marca
    public void deleteMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}
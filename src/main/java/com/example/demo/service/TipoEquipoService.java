package com.example.demo.service;

import com.example.demo.model.TipoEquipo;
import com.example.demo.repository.TipoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEquipoService {
    @Autowired
    private TipoEquipoRepository tipoEquipoRepository;

    // Crear o actualizar un tipo de equipo
    public TipoEquipo saveTipoEquipo(TipoEquipo tipoEquipo) {
        return tipoEquipoRepository.save(tipoEquipo);
    }

    // Obtener todos los tipos de equipo
    public List<TipoEquipo> getAllTiposEquipo() {
        return tipoEquipoRepository.findAll();
    }

    // Obtener un tipo de equipo por ID
    public TipoEquipo getTipoEquipoById(Integer id) {
        return tipoEquipoRepository.findById(id).orElse(null);
    }

    // Eliminar un tipo de equipo
    public void deleteTipoEquipo(Integer id) {
        tipoEquipoRepository.deleteById(id);
    }
}
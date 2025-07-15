//TipoEquipoService.java
package com.example.demo.service;

import com.example.demo.model.TipoEquipo;
import com.example.demo.repository.TipoEquipoRepository;
import java.util.List;
import java.util.Optional;

public class TipoEquipoService {
    private final TipoEquipoRepository tipoEquipoRepository;

    public TipoEquipoService(TipoEquipoRepository tipoEquipoRepository) {
        this.tipoEquipoRepository = tipoEquipoRepository;
    }

    public TipoEquipo saveTipoEquipo(TipoEquipo tipoEquipo) {
        return tipoEquipoRepository.save(tipoEquipo);
    }

    public List<TipoEquipo> getAllTiposEquipo() {
        return tipoEquipoRepository.findAll();
    }

    public Optional<TipoEquipo> getTipoEquipoById(Integer id) {
        return tipoEquipoRepository.findById(id);
    }

    public void deleteTipoEquipo(Integer id) {
        tipoEquipoRepository.delete(id);
    }
}
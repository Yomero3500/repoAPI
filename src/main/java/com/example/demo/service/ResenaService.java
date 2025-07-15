//ResenaService.java
package com.example.demo.service;

import com.example.demo.model.Resena;
import com.example.demo.repository.ResenaRepository;
import java.util.List;
import java.util.Optional;

public class ResenaService {
    private final ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    public Resena saveResena(Resena resena) {
        // Validación de calificación (1-5)
        if (resena.getCalificacion() < 1 || resena.getCalificacion() > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
        return resenaRepository.save(resena);
    }

    public List<Resena> getAllResenas() {
        return resenaRepository.findAll();
    }

    public Optional<Resena> getResenaById(Integer id) {
        return resenaRepository.findById(id);
    }

    public void deleteResena(Integer id) {
        resenaRepository.delete(id);
    }

    public List<Resena> getResenasByProveedor(Integer idProveedor) {
        return resenaRepository.findByProveedorIdProveedor(idProveedor);
    }

    public List<Resena> getResenasByUsuario(Integer idUsuario) {
        return resenaRepository.findByUsuarioIdUsuario(idUsuario);
    }
}
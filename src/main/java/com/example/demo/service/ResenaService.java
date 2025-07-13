package com.example.demo.service;

import com.example.demo.model.Resena;
import com.example.demo.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

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

    public Resena getResenaById(Integer id) {
        return resenaRepository.findById(id).orElse(null);
    }

    public void deleteResena(Integer id) {
        resenaRepository.deleteById(id);
    }

    public List<Resena> getResenasByProveedor(Integer idProveedor) {
        return resenaRepository.findByProveedorIdProveedor(idProveedor);  // Cambiado a findByProveedorIdProveedor
    }

    public List<Resena> getResenasByUsuario(Integer idUsuario) {
        return resenaRepository.findByUsuarioIdUsuario(idUsuario);  // Cambiado a findByUsuarioIdUsuario
    }
}
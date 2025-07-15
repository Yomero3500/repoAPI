//FavoritoService.java
package com.example.demo.service;

import com.example.demo.model.Favorito;
import com.example.demo.repository.FavoritoRepository;
import java.util.List;
import java.util.Optional;

public class FavoritoService {
    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public Favorito saveFavorito(Favorito favorito) {
        // Validaciones manuales
        if (favorito.getUsuario() == null || favorito.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        if (favorito.getEquipo() == null || favorito.getEquipo().getIdEquipo() == null) {
            throw new IllegalArgumentException("El ID de equipo es requerido");
        }

        // Validar que no exista ya la combinación usuario-equipo
        if (favoritoRepository.existsByUsuarioIdUsuarioAndEquipoIdEquipo(
                favorito.getUsuario().getIdUsuario(),
                favorito.getEquipo().getIdEquipo())) {
            throw new IllegalArgumentException("Este equipo ya está en los favoritos del usuario");
        }

        try {
            return favoritoRepository.save(favorito);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar favorito: Verifica que el usuario y equipo existan");
        }
    }

    public List<Favorito> getAllFavoritos() {
        return favoritoRepository.findAll();
    }

    public Optional<Favorito> getFavoritoById(Integer id) {
        return favoritoRepository.findById(id);
    }

    public void deleteFavorito(Integer id) {
        if (!favoritoRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("El favorito con ID " + id + " no existe");
        }
        favoritoRepository.delete(id);
    }

    public List<Favorito> getFavoritosByUsuario(Integer idUsuario) {
        return favoritoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Favorito> getFavoritosByEquipo(Integer idEquipo) {
        return favoritoRepository.findByEquipoIdEquipo(idEquipo);
    }
}
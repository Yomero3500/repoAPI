package com.example.demo.controller;

import com.example.demo.model.Favorito;
import com.example.demo.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<?> createFavorito(@RequestBody Favorito favorito) {
        try {
            Favorito savedFavorito = favoritoService.saveFavorito(favorito);
            return ResponseEntity.ok(savedFavorito);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Favorito> getAllFavoritos() {
        return favoritoService.getAllFavoritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFavoritoById(@PathVariable Integer id) {
        Favorito favorito = favoritoService.getFavoritoById(id);
        if (favorito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(favorito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorito(@PathVariable Integer id) {
        try {
            favoritoService.deleteFavorito(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Favorito> getFavoritosByUsuario(@PathVariable Integer idUsuario) {
        return favoritoService.getFavoritosByUsuario(idUsuario);
    }

    @GetMapping("/equipo/{idEquipo}")
    public List<Favorito> getFavoritosByEquipo(@PathVariable Integer idEquipo) {
        return favoritoService.getFavoritosByEquipo(idEquipo);
    }
}
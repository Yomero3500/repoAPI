//FavoritoController.java
package com.example.demo.controller;

import com.example.demo.model.Favorito;
import com.example.demo.service.FavoritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public class FavoritoController {
    private final FavoritoService favoritoService;
    private final ObjectMapper objectMapper;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
        this.objectMapper = new ObjectMapper();
    }

    public void createFavorito(Context ctx) {
        try {
            Favorito favorito = objectMapper.readValue(ctx.body(), Favorito.class);
            Favorito savedFavorito = favoritoService.saveFavorito(favorito);
            ctx.status(HttpStatus.CREATED).json(savedFavorito);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear favorito: " + e.getMessage());
        }
    }

    public void getAllFavoritos(Context ctx) {
        ctx.json(favoritoService.getAllFavoritos());
    }

    public void getFavoritoById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Favorito> favorito = favoritoService.getFavoritoById(id);

            if (favorito.isPresent()) {
                ctx.json(favorito.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Favorito no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de favorito inválido");
        }
    }

    public void deleteFavorito(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            favoritoService.deleteFavorito(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            // Este catch capturará tanto IllegalArgumentException como NumberFormatException
            if (e instanceof NumberFormatException) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("ID de favorito inválido");
            } else {
                ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
            }
        }
    }

    public void getFavoritosByUsuario(Context ctx) {
        try {
            Integer idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));
            List<Favorito> favoritos = favoritoService.getFavoritosByUsuario(idUsuario);
            ctx.json(favoritos);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }

    public void getFavoritosByEquipo(Context ctx) {
        try {
            Integer idEquipo = Integer.parseInt(ctx.pathParam("idEquipo"));
            List<Favorito> favoritos = favoritoService.getFavoritosByEquipo(idEquipo);
            ctx.json(favoritos);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de equipo inválido");
        }
    }
}
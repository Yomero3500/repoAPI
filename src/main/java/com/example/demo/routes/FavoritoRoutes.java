package com.example.demo.routes;

import com.example.demo.controller.FavoritoController;
import io.javalin.Javalin;

public class FavoritoRoutes {
    private final FavoritoController favoritoController;

    public FavoritoRoutes(FavoritoController favoritoController) {
        this.favoritoController = favoritoController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/favoritos", favoritoController::createFavorito);
            app.get("/api/favoritos", favoritoController::getAllFavoritos);
            app.get("/api/favoritos/{id}", favoritoController::getFavoritoById);
            app.delete("/api/favoritos/{id}", favoritoController::deleteFavorito);
            app.get("/api/favoritos/usuario/{idUsuario}", favoritoController::getFavoritosByUsuario);
            app.get("/api/favoritos/equipo/{idEquipo}", favoritoController::getFavoritosByEquipo);
        });
    }
}
package com.example.demo.routes;

import com.example.demo.controller.TipoEquipoController;
import io.javalin.Javalin;

public class TipoEquipoRoutes {
    private final TipoEquipoController tipoEquipoController;

    public TipoEquipoRoutes(TipoEquipoController tipoEquipoController) {
        this.tipoEquipoController = tipoEquipoController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/tipos-equipo", tipoEquipoController::createTipoEquipo);
            app.get("/api/tipos-equipo", tipoEquipoController::getAllTiposEquipo);
            app.get("/api/tipos-equipo/{id}", tipoEquipoController::getTipoEquipoById);
            app.put("/api/tipos-equipo/{id}", tipoEquipoController::updateTipoEquipo);
            app.delete("/api/tipos-equipo/{id}", tipoEquipoController::deleteTipoEquipo);
        });
    }
}
package com.example.demo.routes;

import com.example.demo.controller.EquipoMedicoController;
import io.javalin.Javalin;

public class EquipoMedicoRoutes {
    private final EquipoMedicoController equipoMedicoController;

    public EquipoMedicoRoutes(EquipoMedicoController equipoMedicoController) {
        this.equipoMedicoController = equipoMedicoController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/equipos-medicos", equipoMedicoController::createEquipoMedico);
            app.get("/api/equipos-medicos", equipoMedicoController::getAllEquiposMedicos);
            app.get("/api/equipos-medicos/{id}", equipoMedicoController::getEquipoMedicoById);
            app.put("/api/equipos-medicos/{id}", equipoMedicoController::updateEquipoMedico);
            app.delete("/api/equipos-medicos/{id}", equipoMedicoController::deleteEquipoMedico);
        });
    }
}
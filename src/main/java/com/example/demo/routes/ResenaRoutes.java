package com.example.demo.routes;

import com.example.demo.controller.ResenaController;
import io.javalin.Javalin;

public class ResenaRoutes {
    private final ResenaController resenaController;

    public ResenaRoutes(ResenaController resenaController) {
        this.resenaController = resenaController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/resenas", resenaController::createResena);
            app.get("/api/resenas", resenaController::getAllResenas);
            app.get("/api/resenas/{id}", resenaController::getResenaById);
            app.put("/api/resenas/{id}", resenaController::updateResena);
            app.delete("/api/resenas/{id}", resenaController::deleteResena);
            app.get("/api/resenas/proveedor/{idProveedor}", resenaController::getResenasByProveedor);
            app.get("/api/resenas/usuario/{idUsuario}", resenaController::getResenasByUsuario);
        });
    }
}
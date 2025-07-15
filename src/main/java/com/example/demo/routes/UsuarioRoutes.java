package com.example.demo.routes;

import com.example.demo.controller.UsuarioController;
import io.javalin.Javalin;

public class UsuarioRoutes {
    private final UsuarioController usuarioController;

    public UsuarioRoutes(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/usuarios", usuarioController::createUsuario);
            app.get("/api/usuarios", usuarioController::getAllUsuarios);
            app.get("/api/usuarios/{id}", usuarioController::getUsuarioById);
            app.put("/api/usuarios/{id}", usuarioController::updateUsuario);
            app.delete("/api/usuarios/{id}", usuarioController::deleteUsuario);
        });
    }
}
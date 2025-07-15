package com.example.demo.routes;

import com.example.demo.controller.ProveedorController;
import io.javalin.Javalin;

public class ProveedorRoutes {
    private final ProveedorController proveedorController;

    public ProveedorRoutes(ProveedorController proveedorController) {
        this.proveedorController = proveedorController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/proveedores", proveedorController::createProveedor);
            app.get("/api/proveedores", proveedorController::getAllProveedores);
            app.get("/api/proveedores/{id}", proveedorController::getProveedorById);
            app.put("/api/proveedores/{id}", proveedorController::updateProveedor);
            app.delete("/api/proveedores/{id}", proveedorController::deleteProveedor);
            app.patch("/api/proveedores/{id}/validar", proveedorController::validarProveedor);
        });
    }
}
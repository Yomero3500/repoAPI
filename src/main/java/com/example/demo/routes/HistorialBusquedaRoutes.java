package com.example.demo.routes;

import com.example.demo.controller.HistorialBusquedaController;
import io.javalin.Javalin;

public class HistorialBusquedaRoutes {
    private final HistorialBusquedaController historialController;

    public HistorialBusquedaRoutes(HistorialBusquedaController historialController) {
        this.historialController = historialController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/historial-busquedas", historialController::registrarBusqueda);
            app.get("/api/historial-busquedas", historialController::obtenerTodoHistorial);
            app.get("/api/historial-busquedas/{id}", historialController::obtenerBusquedaPorId);
            app.delete("/api/historial-busquedas/{id}", historialController::eliminarBusqueda);
            app.get("/api/historial-busquedas/usuario/{idUsuario}", historialController::obtenerHistorialUsuario);
            app.get("/api/historial-busquedas/buscar", historialController::buscarPorTermino);
            app.delete("/api/historial-busquedas/usuario/{idUsuario}", historialController::limpiarHistorialUsuario);
        });
    }
}
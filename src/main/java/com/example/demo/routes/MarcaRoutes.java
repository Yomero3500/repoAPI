// MarcaRoutes.java
package com.example.demo.routes;

import com.example.demo.controller.MarcaController;
import io.javalin.Javalin;

public class MarcaRoutes {
    private final MarcaController marcaController;

    public MarcaRoutes(MarcaController marcaController) {
        this.marcaController = marcaController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.get("/api/marcas", ctx -> marcaController.getAllMarcas(ctx));
            app.get("/api/marcas/{id}", ctx -> marcaController.getMarcaById(ctx));
            app.post("/api/marcas", ctx -> marcaController.createMarca(ctx));
            app.put("/api/marcas/{id}", ctx -> marcaController.updateMarca(ctx));
            app.delete("/api/marcas/{id}", ctx -> marcaController.deleteMarca(ctx));
        });
    }
}
package com.example.demo.routes;

import com.example.demo.controller.TicketSoporteController;
import io.javalin.Javalin;

public class TicketSoporteRoutes {
    private final TicketSoporteController ticketSoporteController;

    public TicketSoporteRoutes(TicketSoporteController ticketSoporteController) {
        this.ticketSoporteController = ticketSoporteController;
    }

    public void configureRoutes(Javalin app) {
        app.routes(() -> {
            app.post("/api/soporte", ticketSoporteController::createTicket);
            app.get("/api/soporte", ticketSoporteController::getAllTickets);
            app.get("/api/soporte/{id}", ticketSoporteController::getTicketById);
            app.delete("/api/soporte/{id}", ticketSoporteController::deleteTicket);
            app.get("/api/soporte/usuario/{idUsuario}", ticketSoporteController::getTicketsByUsuario);
            app.get("/api/soporte/estado/{estado}", ticketSoporteController::getTicketsByEstado);
            app.patch("/api/soporte/{id}/estado", ticketSoporteController::updateEstadoTicket);
        });
    }
}
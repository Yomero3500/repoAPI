//TicketSoporteController.java
package com.example.demo.controller;

import com.example.demo.model.TicketSoporte;
import com.example.demo.service.TicketSoporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public class TicketSoporteController {
    private final TicketSoporteService ticketSoporteService;
    private final ObjectMapper objectMapper;

    public TicketSoporteController(TicketSoporteService ticketSoporteService) {
        this.ticketSoporteService = ticketSoporteService;
        this.objectMapper = new ObjectMapper();
    }

    public void createTicket(Context ctx) {
        try {
            TicketSoporte ticket = objectMapper.readValue(ctx.body(), TicketSoporte.class);
            TicketSoporte savedTicket = ticketSoporteService.saveTicket(ticket);
            ctx.status(HttpStatus.CREATED).json(savedTicket);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear el ticket: " + e.getMessage());
        }
    }

    public void getAllTickets(Context ctx) {
        ctx.json(ticketSoporteService.getAllTickets());
    }

    public void getTicketById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<TicketSoporte> ticket = ticketSoporteService.getTicketById(id);

            if (ticket.isPresent()) {
                ctx.json(ticket.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Ticket no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de ticket inválido");
        }
    }

    public void deleteTicket(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            ticketSoporteService.deleteTicket(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de ticket inválido");
        }
    }

    public void getTicketsByUsuario(Context ctx) {
        try {
            Integer idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));
            List<TicketSoporte> tickets = ticketSoporteService.getTicketsByUsuario(idUsuario);
            ctx.json(tickets);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }

    public void getTicketsByEstado(Context ctx) {
        try {
            String estadoStr = ctx.pathParam("estado");
            TicketSoporte.Estado estado = TicketSoporte.Estado.valueOf(estadoStr);
            List<TicketSoporte> tickets = ticketSoporteService.getTicketsByEstado(estado);
            ctx.json(tickets);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Estado de ticket inválido. Valores permitidos: pendiente, en_proceso, resuelto");
        }
    }

    public void updateEstadoTicket(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            String estadoStr = ctx.queryParam("estado");
            TicketSoporte.Estado estado = TicketSoporte.Estado.valueOf(estadoStr);

            Optional<TicketSoporte> updatedTicket = ticketSoporteService.updateEstadoTicket(id, estado);

            if (updatedTicket.isPresent()) {
                ctx.json(updatedTicket.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Ticket no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de ticket inválido");
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Estado de ticket inválido. Valores permitidos: pendiente, en_proceso, resuelto");
        } catch (NullPointerException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Parámetro 'estado' es requerido");
        }
    }
}
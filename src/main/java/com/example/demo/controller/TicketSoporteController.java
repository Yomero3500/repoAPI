package com.example.demo.controller;

import com.example.demo.model.TicketSoporte;
import com.example.demo.service.TicketSoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class TicketSoporteController {
    @Autowired
    private TicketSoporteService ticketSoporteService;

    @PostMapping
    public TicketSoporte createTicket(@RequestBody TicketSoporte ticket) {
        return ticketSoporteService.saveTicket(ticket);
    }

    @GetMapping
    public List<TicketSoporte> getAllTickets() {
        return ticketSoporteService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketSoporte getTicketById(@PathVariable Integer id) {
        return ticketSoporteService.getTicketById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketSoporteService.deleteTicket(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<TicketSoporte> getTicketsByUsuario(@PathVariable Integer idUsuario) {
        return ticketSoporteService.getTicketsByUsuario(idUsuario);
    }

    @GetMapping("/estado/{estado}")
    public List<TicketSoporte> getTicketsByEstado(@PathVariable TicketSoporte.Estado estado) {
        return ticketSoporteService.getTicketsByEstado(estado);
    }

    @PatchMapping("/{id}/estado")
    public TicketSoporte updateEstadoTicket(
            @PathVariable Integer id,
            @RequestParam TicketSoporte.Estado estado) {
        return ticketSoporteService.updateEstadoTicket(id, estado);
    }
}
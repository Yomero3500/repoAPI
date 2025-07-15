//TicketSoporteService.java
package com.example.demo.service;

import com.example.demo.model.TicketSoporte;
import com.example.demo.repository.TicketSoporteRepository;
import java.util.List;
import java.util.Optional;

public class TicketSoporteService {
    private final TicketSoporteRepository ticketSoporteRepository;

    public TicketSoporteService(TicketSoporteRepository ticketSoporteRepository) {
        this.ticketSoporteRepository = ticketSoporteRepository;
    }

    public TicketSoporte saveTicket(TicketSoporte ticket) {
        return ticketSoporteRepository.save(ticket);
    }

    public List<TicketSoporte> getAllTickets() {
        return ticketSoporteRepository.findAll();
    }

    public Optional<TicketSoporte> getTicketById(Integer id) {
        return ticketSoporteRepository.findById(id);
    }

    public void deleteTicket(Integer id) {
        ticketSoporteRepository.delete(id);
    }

    public List<TicketSoporte> getTicketsByUsuario(Integer idUsuario) {
        return ticketSoporteRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<TicketSoporte> getTicketsByEstado(TicketSoporte.Estado estado) {
        return ticketSoporteRepository.findByEstado(estado);
    }

    public Optional<TicketSoporte> updateEstadoTicket(Integer id, TicketSoporte.Estado nuevoEstado) {
        Optional<TicketSoporte> ticketOpt = ticketSoporteRepository.findById(id);
        if (ticketOpt.isPresent()) {
            TicketSoporte ticket = ticketOpt.get();
            ticket.setEstado(nuevoEstado);
            return Optional.of(ticketSoporteRepository.save(ticket));
        }
        return Optional.empty();
    }
}
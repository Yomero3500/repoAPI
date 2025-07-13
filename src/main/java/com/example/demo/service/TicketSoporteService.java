package com.example.demo.service;

import com.example.demo.model.TicketSoporte;
import com.example.demo.repository.TicketSoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketSoporteService {
    @Autowired
    private TicketSoporteRepository ticketSoporteRepository;

    public TicketSoporte saveTicket(TicketSoporte ticket) {
        return ticketSoporteRepository.save(ticket);
    }

    public List<TicketSoporte> getAllTickets() {
        return ticketSoporteRepository.findAll();
    }

    public TicketSoporte getTicketById(Integer id) {
        return ticketSoporteRepository.findById(id).orElse(null);
    }

    public void deleteTicket(Integer id) {
        ticketSoporteRepository.deleteById(id);
    }

    public List<TicketSoporte> getTicketsByUsuario(Integer idUsuario) {
        return ticketSoporteRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<TicketSoporte> getTicketsByEstado(TicketSoporte.Estado estado) {
        return ticketSoporteRepository.findByEstado(estado);
    }

    public TicketSoporte updateEstadoTicket(Integer id, TicketSoporte.Estado nuevoEstado) {
        TicketSoporte ticket = ticketSoporteRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setEstado(nuevoEstado);
            return ticketSoporteRepository.save(ticket);
        }
        return null;
    }
}
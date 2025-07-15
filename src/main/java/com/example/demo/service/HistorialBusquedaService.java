//HistorialBusquedaService.java
package com.example.demo.service;

import com.example.demo.model.HistorialBusqueda;
import com.example.demo.repository.HistorialBusquedaRepository;
import java.util.List;
import java.util.Optional;

public class HistorialBusquedaService {
    private final HistorialBusquedaRepository historialRepository;

    public HistorialBusquedaService(HistorialBusquedaRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public HistorialBusqueda guardarBusqueda(HistorialBusqueda busqueda) {
        if (busqueda.getUsuario() == null || busqueda.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("El usuario es requerido");
        }
        if (busqueda.getTerminoBusqueda() == null || busqueda.getTerminoBusqueda().trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return historialRepository.save(busqueda);
    }

    public List<HistorialBusqueda> obtenerTodasBusquedas() {
        return historialRepository.findAll();
    }

    public Optional<HistorialBusqueda> obtenerBusquedaPorId(Integer id) {
        return historialRepository.findById(id);
    }

    public void eliminarBusqueda(Integer id) {
        if (!historialRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("La búsqueda con ID " + id + " no existe");
        }
        historialRepository.delete(id);
    }

    public List<HistorialBusqueda> obtenerBusquedasPorUsuario(Integer idUsuario) {
        return historialRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<HistorialBusqueda> buscarPorTermino(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return historialRepository.findByTerminoBusquedaContainingIgnoreCase(termino);
    }

    public void limpiarHistorialUsuario(Integer idUsuario) {
        historialRepository.deleteByUsuarioIdUsuario(idUsuario);
    }
}
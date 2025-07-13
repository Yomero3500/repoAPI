package com.example.demo.service;

import com.example.demo.model.HistorialBusqueda;
import com.example.demo.repository.HistorialBusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialBusquedaService {
    @Autowired
    private HistorialBusquedaRepository historialRepository;

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

    public HistorialBusqueda obtenerBusquedaPorId(Integer id) {
        return historialRepository.findById(id).orElse(null);
    }

    public void eliminarBusqueda(Integer id) {
        if (!historialRepository.existsById(id)) {
            throw new IllegalArgumentException("La búsqueda con ID " + id + " no existe");
        }
        historialRepository.deleteById(id);
    }

    public List<HistorialBusqueda> obtenerBusquedasPorUsuario(Integer idUsuario) {
        return historialRepository.findByUsuarioIdUsuarioOrderByFechaBusquedaDesc(idUsuario);
    }

    public List<HistorialBusqueda> buscarPorTermino(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return historialRepository.findByTerminoBusquedaContainingIgnoreCase(termino);
    }

    @Transactional
    public void limpiarHistorialUsuario(Integer idUsuario) {
        historialRepository.deleteByUsuarioIdUsuario(idUsuario);
    }
}
package com.example.demo.controller;

import com.example.demo.model.HistorialBusqueda;
import com.example.demo.service.HistorialBusquedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-busquedas")
public class HistorialBusquedaController {
    @Autowired
    private HistorialBusquedaService historialService;

    @PostMapping
    public ResponseEntity<HistorialBusqueda> registrarBusqueda(@RequestBody HistorialBusqueda busqueda) {
        return ResponseEntity.ok(historialService.guardarBusqueda(busqueda));
    }

    @GetMapping
    public List<HistorialBusqueda> obtenerTodoHistorial() {
        return historialService.obtenerTodasBusquedas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialBusqueda> obtenerBusquedaPorId(@PathVariable Integer id) {
        HistorialBusqueda busqueda = historialService.obtenerBusquedaPorId(id);
        return busqueda != null ? ResponseEntity.ok(busqueda) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBusqueda(@PathVariable Integer id) {
        historialService.eliminarBusqueda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialBusqueda> obtenerHistorialUsuario(@PathVariable Integer idUsuario) {
        return historialService.obtenerBusquedasPorUsuario(idUsuario);
    }

    @GetMapping("/buscar")
    public List<HistorialBusqueda> buscarPorTermino(@RequestParam String termino) {
        return historialService.buscarPorTermino(termino);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Void> limpiarHistorialUsuario(@PathVariable Integer idUsuario) {
        historialService.limpiarHistorialUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
//HistorialBusquedaController.java
package com.example.demo.controller;

import com.example.demo.model.HistorialBusqueda;
import com.example.demo.service.HistorialBusquedaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public class HistorialBusquedaController {
    private final HistorialBusquedaService historialService;
    private final ObjectMapper objectMapper;

    public HistorialBusquedaController(HistorialBusquedaService historialService) {
        this.historialService = historialService;
        this.objectMapper = new ObjectMapper();
    }

    public void registrarBusqueda(Context ctx) {
        try {
            HistorialBusqueda busqueda = objectMapper.readValue(ctx.body(), HistorialBusqueda.class);
            HistorialBusqueda savedBusqueda = historialService.guardarBusqueda(busqueda);
            ctx.status(HttpStatus.CREATED).json(savedBusqueda);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al registrar búsqueda: " + e.getMessage());
        }
    }

    public void obtenerTodoHistorial(Context ctx) {
        ctx.json(historialService.obtenerTodasBusquedas());
    }

    public void obtenerBusquedaPorId(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<HistorialBusqueda> busqueda = historialService.obtenerBusquedaPorId(id);

            if (busqueda.isPresent()) {
                ctx.json(busqueda.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Búsqueda no encontrada con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de búsqueda inválido");
        }
    }

    public void eliminarBusqueda(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            historialService.eliminarBusqueda(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            if (e instanceof NumberFormatException) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("ID de búsqueda inválido");
            } else {
                ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
            }
        }
    }

    public void obtenerHistorialUsuario(Context ctx) {
        try {
            Integer idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));
            List<HistorialBusqueda> busquedas = historialService.obtenerBusquedasPorUsuario(idUsuario);
            ctx.json(busquedas);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }

    public void buscarPorTermino(Context ctx) {
        try {
            String termino = ctx.queryParam("termino");
            if (termino == null || termino.trim().isEmpty()) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("El parámetro 'termino' es requerido");
                return;
            }
            List<HistorialBusqueda> resultados = historialService.buscarPorTermino(termino);
            ctx.json(resultados);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
        }
    }

    public void limpiarHistorialUsuario(Context ctx) {
        try {
            Integer idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));
            historialService.limpiarHistorialUsuario(idUsuario);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }
}
//TipoEquipoController.java
package com.example.demo.controller;

import com.example.demo.model.TipoEquipo;
import com.example.demo.service.TipoEquipoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Optional;

public class TipoEquipoController {
    private final TipoEquipoService tipoEquipoService;
    private final ObjectMapper objectMapper;

    public TipoEquipoController(TipoEquipoService tipoEquipoService) {
        this.tipoEquipoService = tipoEquipoService;
        this.objectMapper = new ObjectMapper();
    }

    public void createTipoEquipo(Context ctx) {
        try {
            TipoEquipo tipoEquipo = objectMapper.readValue(ctx.body(), TipoEquipo.class);
            TipoEquipo savedTipo = tipoEquipoService.saveTipoEquipo(tipoEquipo);
            ctx.status(HttpStatus.CREATED).json(savedTipo);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear el tipo de equipo: " + e.getMessage());
        }
    }

    public void getAllTiposEquipo(Context ctx) {
        ctx.json(tipoEquipoService.getAllTiposEquipo());
    }

    public void getTipoEquipoById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<TipoEquipo> tipoEquipo = tipoEquipoService.getTipoEquipoById(id);

            if (tipoEquipo.isPresent()) {
                ctx.json(tipoEquipo.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Tipo de equipo no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de tipo de equipo inválido");
        }
    }

    public void updateTipoEquipo(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            TipoEquipo tipoEquipo = objectMapper.readValue(ctx.body(), TipoEquipo.class);
            tipoEquipo.setIdTipo(id);

            TipoEquipo updatedTipo = tipoEquipoService.saveTipoEquipo(tipoEquipo);
            ctx.json(updatedTipo);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de tipo de equipo inválido");
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al actualizar el tipo de equipo: " + e.getMessage());
        }
    }

    public void deleteTipoEquipo(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            tipoEquipoService.deleteTipoEquipo(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de tipo de equipo inválido");
        }
    }
}
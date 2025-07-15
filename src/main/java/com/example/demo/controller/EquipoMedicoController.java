//EquipoMedicoController.java
package com.example.demo.controller;

import com.example.demo.model.EquipoMedico;
import com.example.demo.service.EquipoMedicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Optional;

public class EquipoMedicoController {
    private final EquipoMedicoService equipoMedicoService;
    private final ObjectMapper objectMapper;

    public EquipoMedicoController(EquipoMedicoService equipoMedicoService) {
        this.equipoMedicoService = equipoMedicoService;
        this.objectMapper = new ObjectMapper();
    }

    public void createEquipoMedico(Context ctx) {
        try {
            EquipoMedico equipoMedico = objectMapper.readValue(ctx.body(), EquipoMedico.class);
            EquipoMedico savedEquipo = equipoMedicoService.saveEquipoMedico(equipoMedico);
            ctx.status(HttpStatus.CREATED).json(savedEquipo);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear el equipo médico: " + e.getMessage());
        }
    }

    public void getAllEquiposMedicos(Context ctx) {
        ctx.json(equipoMedicoService.getAllEquiposMedicos());
    }

    public void getEquipoMedicoById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<EquipoMedico> equipo = equipoMedicoService.getEquipoMedicoById(id);

            if (equipo.isPresent()) {
                ctx.json(equipo.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Equipo médico no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de equipo médico inválido");
        }
    }

    public void updateEquipoMedico(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            EquipoMedico equipoMedico = objectMapper.readValue(ctx.body(), EquipoMedico.class);
            equipoMedico.setIdEquipo(id);

            EquipoMedico updatedEquipo = equipoMedicoService.saveEquipoMedico(equipoMedico);
            ctx.json(updatedEquipo);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de equipo médico inválido");
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al actualizar el equipo médico: " + e.getMessage());
        }
    }

    public void deleteEquipoMedico(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            equipoMedicoService.deleteEquipoMedico(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de equipo médico inválido");
        }
    }
}
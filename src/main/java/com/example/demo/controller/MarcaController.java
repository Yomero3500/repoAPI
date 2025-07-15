// MarcaController.java
package com.example.demo.controller;

import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Optional;

public class MarcaController {
    private final MarcaService marcaService;
    private final ObjectMapper objectMapper;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
        this.objectMapper = new ObjectMapper();
    }

    public void getAllMarcas(Context ctx) {
        ctx.json(marcaService.getAllMarcas());
    }

    public void getMarcaById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Marca> marca = marcaService.getMarcaById(id);

            if (marca.isPresent()) {
                ctx.json(marca.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Marca no encontrada con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de marca inválido");
        }
    }

    public void createMarca(Context ctx) {
        try {
            Marca marca = objectMapper.readValue(ctx.body(), Marca.class);
            Marca savedMarca = marcaService.saveMarca(marca);
            ctx.status(HttpStatus.CREATED)
                    .json(savedMarca);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear la marca: " + e.getMessage());
        }
    }

    public void updateMarca(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Marca marca = objectMapper.readValue(ctx.body(), Marca.class);
            marca.setIdMarca(id); // Asegurar que el ID coincida

            // Verificar si existe antes de actualizar
            Optional<Marca> existingMarca = marcaService.getMarcaById(id);
            if (existingMarca.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Marca no encontrada con ID: " + id);
                return;
            }

            Marca updatedMarca = marcaService.saveMarca(marca);
            ctx.json(updatedMarca);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de marca inválido");
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al actualizar la marca: " + e.getMessage());
        }
    }

    public void deleteMarca(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));

            // Verificar si existe antes de eliminar
            Optional<Marca> existingMarca = marcaService.getMarcaById(id);
            if (existingMarca.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Marca no encontrada con ID: " + id);
                return;
            }

            marcaService.deleteMarca(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de marca inválido");
        }
    }
}
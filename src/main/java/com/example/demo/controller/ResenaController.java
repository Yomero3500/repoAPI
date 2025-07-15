//ResenaController.java
package com.example.demo.controller;

import com.example.demo.model.Resena;
import com.example.demo.service.ResenaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public class ResenaController {
    private final ResenaService resenaService;
    private final ObjectMapper objectMapper;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
        this.objectMapper = new ObjectMapper();
    }

    public void createResena(Context ctx) {
        try {
            Resena resena = objectMapper.readValue(ctx.body(), Resena.class);
            Resena savedResena = resenaService.saveResena(resena);
            ctx.status(HttpStatus.CREATED).json(savedResena);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear la reseña: " + e.getMessage());
        }
    }

    public void getAllResenas(Context ctx) {
        ctx.json(resenaService.getAllResenas());
    }

    public void getResenaById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Resena> resena = resenaService.getResenaById(id);

            if (resena.isPresent()) {
                ctx.json(resena.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Reseña no encontrada con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de reseña inválido");
        }
    }

    public void updateResena(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Resena resena = objectMapper.readValue(ctx.body(), Resena.class);
            resena.setIdResena(id);

            Resena updatedResena = resenaService.saveResena(resena);
            ctx.json(updatedResena);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST).json(e.getMessage());
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("ID de reseña inválido");
            } else {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("Error al actualizar la reseña: " + e.getMessage());
            }
        }
    }

    public void deleteResena(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            resenaService.deleteResena(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de reseña inválido");
        }
    }

    public void getResenasByProveedor(Context ctx) {
        try {
            Integer idProveedor = Integer.parseInt(ctx.pathParam("idProveedor"));
            List<Resena> resenas = resenaService.getResenasByProveedor(idProveedor);
            ctx.json(resenas);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de proveedor inválido");
        }
    }

    public void getResenasByUsuario(Context ctx) {
        try {
            Integer idUsuario = Integer.parseInt(ctx.pathParam("idUsuario"));
            List<Resena> resenas = resenaService.getResenasByUsuario(idUsuario);
            ctx.json(resenas);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }
}
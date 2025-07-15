//UsuarioController.java
package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Optional;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.objectMapper = new ObjectMapper();
    }

    public void createUsuario(Context ctx) {
        try {
            Usuario usuario = objectMapper.readValue(ctx.body(), Usuario.class);

            if (usuarioService.existsByCorreo(usuario.getCorreo())) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("El correo ya está registrado");
                return;
            }

            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            ctx.status(HttpStatus.CREATED)
                    .json(savedUsuario);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear el usuario: " + e.getMessage());
        }
    }

    public void getAllUsuarios(Context ctx) {
        ctx.json(usuarioService.getAllUsuarios());
    }

    public void getUsuarioById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

            if (usuario.isPresent()) {
                ctx.json(usuario.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Usuario no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }

    public void updateUsuario(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = objectMapper.readValue(ctx.body(), Usuario.class);
            usuario.setIdUsuario(id);

            Usuario updatedUsuario = usuarioService.saveUsuario(usuario);
            ctx.json(updatedUsuario);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public void deleteUsuario(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            usuarioService.deleteUsuario(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de usuario inválido");
        }
    }
}
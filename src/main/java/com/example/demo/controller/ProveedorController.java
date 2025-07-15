//ProveedorController.java
package com.example.demo.controller;

import com.example.demo.model.Proveedor;
import com.example.demo.service.ProveedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Optional;

public class ProveedorController {
    private final ProveedorService proveedorService;
    private final ObjectMapper objectMapper;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
        this.objectMapper = new ObjectMapper();
    }

    public void createProveedor(Context ctx) {
        try {
            Proveedor proveedor = objectMapper.readValue(ctx.body(), Proveedor.class);

            if (proveedorService.existsByCorreoContacto(proveedor.getCorreoContacto())) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json("El correo ya está registrado");
                return;
            }

            Proveedor savedProveedor = proveedorService.saveProveedor(proveedor);
            ctx.status(HttpStatus.CREATED)
                    .json(savedProveedor);
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al crear el proveedor: " + e.getMessage());
        }
    }

    public void getAllProveedores(Context ctx) {
        ctx.json(proveedorService.getAllProveedores());
    }

    public void getProveedorById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Proveedor> proveedor = proveedorService.getProveedorById(id);

            if (proveedor.isPresent()) {
                ctx.json(proveedor.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Proveedor no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de proveedor inválido");
        }
    }

    public void updateProveedor(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Proveedor proveedor = objectMapper.readValue(ctx.body(), Proveedor.class);
            proveedor.setIdProveedor(id);

            Proveedor updatedProveedor = proveedorService.saveProveedor(proveedor);
            ctx.json(updatedProveedor);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de proveedor inválido");
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("Error al actualizar el proveedor: " + e.getMessage());
        }
    }

    public void deleteProveedor(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            proveedorService.deleteProveedor(id);
            ctx.status(HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de proveedor inválido");
        }
    }

    public void validarProveedor(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Proveedor> proveedor = proveedorService.validarProveedor(id);

            if (proveedor.isPresent()) {
                ctx.json(proveedor.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json("Proveedor no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json("ID de proveedor inválido");
        }
    }
}
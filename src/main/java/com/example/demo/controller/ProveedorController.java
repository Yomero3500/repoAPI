package com.example.demo.controller;

import com.example.demo.model.Proveedor;
import com.example.demo.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    // Crear proveedor (con validación de correo único)
    @PostMapping
    public ResponseEntity<?> createProveedor(@RequestBody Proveedor proveedor) {
        if (proveedorService.existsByCorreoContacto(proveedor.getCorreoContacto())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }
        Proveedor savedProveedor = proveedorService.saveProveedor(proveedor);
        return ResponseEntity.ok(savedProveedor);
    }

    // Obtener todos los proveedores
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }

    // Obtener proveedor por ID
    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Integer id) {
        return proveedorService.getProveedorById(id);
    }

    // Actualizar proveedor
    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        return proveedorService.saveProveedor(proveedor);
    }

    // Eliminar proveedor
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
    }

    // Endpoint adicional para validar proveedor
    @PatchMapping("/{id}/validar")
    public Proveedor validarProveedor(@PathVariable Integer id) {
        return proveedorService.validarProveedor(id);
    }
}
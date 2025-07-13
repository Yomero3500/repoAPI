package com.example.demo.controller;

import com.example.demo.model.Resena;
import com.example.demo.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public ResponseEntity<?> createResena(@RequestBody Resena resena) {
        try {
            Resena savedResena = resenaService.saveResena(resena);
            return ResponseEntity.ok(savedResena);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Resena> getAllResenas() {
        return resenaService.getAllResenas();
    }

    @GetMapping("/{id}")
    public Resena getResenaById(@PathVariable Integer id) {
        return resenaService.getResenaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        try {
            resena.setIdResena(id);
            Resena updatedResena = resenaService.saveResena(resena);
            return ResponseEntity.ok(updatedResena);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteResena(@PathVariable Integer id) {
        resenaService.deleteResena(id);
    }

    @GetMapping("/proveedor/{idProveedor}")
    public List<Resena> getResenasByProveedor(@PathVariable Integer idProveedor) {
        return resenaService.getResenasByProveedor(idProveedor);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Resena> getResenasByUsuario(@PathVariable Integer idUsuario) {
        return resenaService.getResenasByUsuario(idUsuario);
    }
}
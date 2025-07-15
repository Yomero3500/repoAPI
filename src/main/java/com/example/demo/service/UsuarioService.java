//UsuarioService.java
package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.delete(id);
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).isPresent();
    }
}
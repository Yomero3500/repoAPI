//ProveedorService.java
package com.example.demo.service;

import com.example.demo.model.Proveedor;
import com.example.demo.repository.ProveedorRepository;
import java.util.List;
import java.util.Optional;

public class ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> getProveedorById(Integer id) {
        return proveedorRepository.findById(id);
    }

    public void deleteProveedor(Integer id) {
        proveedorRepository.delete(id);
    }

    public boolean existsByCorreoContacto(String correoContacto) {
        return proveedorRepository.findByCorreoContacto(correoContacto).isPresent();
    }

    public Optional<Proveedor> validarProveedor(Integer id) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedor = proveedorOpt.get();
            proveedor.setValidado(true);
            return Optional.of(proveedorRepository.save(proveedor));
        }
        return Optional.empty();
    }
}
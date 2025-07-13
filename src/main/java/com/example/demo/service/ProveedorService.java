package com.example.demo.service;

import com.example.demo.model.Proveedor;
import com.example.demo.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    // Guardar o actualizar proveedor
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Obtener todos los proveedores
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    // Obtener proveedor por ID
    public Proveedor getProveedorById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    // Eliminar proveedor
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }

    // Validar correo único
    public boolean existsByCorreoContacto(String correoContacto) {
        return proveedorRepository.findByCorreoContacto(correoContacto) != null;
    }

    // Método adicional para marcar como validado
    public Proveedor validarProveedor(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedor.setValidado(true);
            return proveedorRepository.save(proveedor);
        }
        return null;
    }
}
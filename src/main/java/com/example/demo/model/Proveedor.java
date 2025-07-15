//Proveedor.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre_empresa", nullable = false, length = 150)
    private String nombreEmpresa;

    @Column(name = "nombre_contacto", nullable = false, length = 100)
    private String nombreContacto;

    @Column(name = "correo_contacto", nullable = false, unique = true, length = 100)
    private String correoContacto;

    @Column(name = "telefono_contacto", length = 15)
    private String telefonoContacto;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "sitio_web", length = 255)
    private String sitioWeb;

    @Column(name = "validado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean validado;

    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaRegistro;

    // Getters y Setters
    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
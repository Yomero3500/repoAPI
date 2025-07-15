//HistorialBusqueda.java
package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "historial_busquedas")
public class HistorialBusqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_busqueda")
    private Integer idBusqueda;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "termino_busqueda", length = 100)
    private String terminoBusqueda;

    @Column(name = "fecha_busqueda", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaBusqueda;

    // Getters y Setters
    public Integer getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(Integer idBusqueda) {
        this.idBusqueda = idBusqueda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTerminoBusqueda() {
        return terminoBusqueda;
    }

    public void setTerminoBusqueda(String terminoBusqueda) {
        this.terminoBusqueda = terminoBusqueda;
    }

    public Timestamp getFechaBusqueda() {
        return fechaBusqueda;
    }

    public void setFechaBusqueda(Timestamp fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }
}
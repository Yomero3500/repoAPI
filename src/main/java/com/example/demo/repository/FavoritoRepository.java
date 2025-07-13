//FavoritoRepository.java
package com.example.demo.repository;

import com.example.demo.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuarioIdUsuario(Integer idUsuario);
    List<Favorito> findByEquipoIdEquipo(Integer idEquipo);
    boolean existsByUsuarioIdUsuarioAndEquipoIdEquipo(Integer idUsuario, Integer idEquipo);
}
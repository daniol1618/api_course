package com.tr.api.repository;

import com.tr.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM USUARIO WHERE ID = :idUsuario", nativeQuery = true)
    Optional<Usuario> getUsuarioById(Integer idUsuario);
}

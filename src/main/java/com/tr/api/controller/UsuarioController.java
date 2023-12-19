package com.tr.api.controller;

import com.tr.api.model.Usuario;
import com.tr.api.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(value = "/obtener/")
    public ResponseEntity<Usuario> getUsuario(@RequestParam Integer idUsuario) {
        Optional<Usuario> usuarioEncontrado = usuarioService.getElementById(idUsuario);

        if (usuarioEncontrado.isPresent()) {
            return new ResponseEntity<>(usuarioEncontrado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST

    //PUT

    //DELETE

    //PATCH

    //OTHERS...


}

package com.tr.api.controller;

import com.tr.api.exceptions.CustomExceptions;
import com.tr.api.model.Usuario;
import com.tr.api.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Obtiene el usuario por Id
     *
     * @param id id del Usuario
     * @return un ResponseEntity con la Entidad Usuario y el codigo HTTP
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        try {
            Optional<Usuario> usuarioEncontrado = usuarioService.getElementById(id);
            return usuarioEncontrado
                    .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (CustomExceptions customException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todos los usuarios
     *
     * @return un ResponseEntity con la lista de Usuarios y el codigo HTTP
     */
    @GetMapping(value = "")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.getElements();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (CustomExceptions customException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crea un nuevo Usuario con un Payload JSON
     *
     * @param nuevoUsuario el JSON del nuevo usuario
     * @return un ResponseEntity con la Entidad Usuario y el codigo HTTP
     */
    @PostMapping(value = "")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        try {
            Optional<Usuario> usuarioGuardado = usuarioService.saveElement(nuevoUsuario);

            if (usuarioGuardado.isPresent()) {
                return new ResponseEntity<>(usuarioGuardado.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (CustomExceptions customException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un Usuario
     *
     * @param id                 el Id del Usuario a modificar como parametro en la URL
     * @param usuarioActualizado El Payload JSON con el usuario actualizado
     * @return un ResponseEntity con la Entidad Usuario y el codigo HTTP
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        if (!id.equals(usuarioActualizado.getIdUsuario())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            usuarioService.updateElement(usuarioActualizado);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (CustomExceptions customException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un Usuario
     *
     * @param id el Id del Usuario tomado desde la URL
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteElementById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomExceptions customException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.tr.api.service.impl;

import com.tr.api.exceptions.CustomExceptions;
import com.tr.api.model.Usuario;
import com.tr.api.repository.IUsuarioRepository;
import com.tr.api.service.IServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IServices<Usuario> {

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> getElementById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> getElements() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> saveElement(Usuario usuario) {
        try {
            return Optional.of(usuarioRepository.save(usuario));
        } catch (Exception e) {
            throw new CustomExceptions("Error almacenando el Usuario: " + e.getMessage());
        }
    }

    @Override
    public void updateElement(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getIdUsuario())) {
            usuarioRepository.save(usuario);
        } else {
            throw new CustomExceptions("Usuario no encontrado con id: " + usuario.getIdUsuario());
        }
    }

    @Override
    public void deleteElementById(Integer idElement) {
        if (usuarioRepository.existsById(idElement)) {
            usuarioRepository.deleteById(idElement);
        } else {
            throw new CustomExceptions("No se encontró ningún Usuario con el id: " + idElement);
        }
    }
}

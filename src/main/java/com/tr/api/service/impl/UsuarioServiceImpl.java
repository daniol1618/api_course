package com.tr.api.service.impl;

import com.tr.api.exceptions.CustomExceptions;
import com.tr.api.model.Usuario;
import com.tr.api.repository.IUsuarioRepository;
import com.tr.api.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> getElementById(Integer id) {
        return Optional.ofNullable(usuarioRepository.getUsuarioById(id))
                .orElseThrow(() -> new CustomExceptions("Usuario no encontrado con id: " + id));
    }

    @Override
    public void saveElement(Object element) {

    }

    @Override
    public void updateElement(Object element) {

    }

    @Override
    public void deleteElement(Object element) {

    }
}

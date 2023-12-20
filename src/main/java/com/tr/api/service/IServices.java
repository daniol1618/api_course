package com.tr.api.service;

import com.tr.api.model.Usuario;

import java.util.Optional;

public interface IServices<T> {
    Optional<T> getElementById(Integer id);

    Optional<T> saveElement(Usuario usuario);

    void updateElement(T element);

    void deleteElement(T element);
}

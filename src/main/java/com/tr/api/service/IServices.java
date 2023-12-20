package com.tr.api.service;

import java.util.List;
import java.util.Optional;

public interface IServices<T> {
    Optional<T> getElementById(Integer id);

    List<T> getElements();

    Optional<T> saveElement(T element);

    void updateElement(T element);

    void deleteElementById(Integer idElement);
}

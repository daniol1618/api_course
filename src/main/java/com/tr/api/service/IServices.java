package com.tr.api.service;

import java.util.Optional;

public interface IServices<T> {
    Optional<T> getElementById(Integer id);

    void saveElement(T element);

    void updateElement(T element);

    void deleteElement(T element);
}

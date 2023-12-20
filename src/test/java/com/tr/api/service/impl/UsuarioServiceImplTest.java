package com.tr.api.service.impl;

import com.tr.api.exceptions.CustomExceptions;
import com.tr.api.model.Usuario;
import com.tr.api.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getElementById_ExistingId_ReturnsUsuario() {
        // Arrange
        Integer userId = 1;
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(userId);
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = underTest.getElementById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getIdUsuario());
    }

    @Test
    void getElementById_NonExistingId_ReturnsEmptyOptional() {
        // Arrange
        Integer userId = 999; // Non-existing ID
        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = underTest.getElementById(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getElements_ReturnsListOfUsuarios() {
        // Arrange
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario());
        usuarios.add(new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = underTest.getElements();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void saveElement_ValidUsuario_ReturnsSavedUsuario() {
        // Arrange
        Usuario usuarioToSave = new Usuario();
        when(usuarioRepository.save(usuarioToSave)).thenReturn(usuarioToSave);

        // Act
        Optional<Usuario> result = underTest.saveElement(usuarioToSave);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(usuarioToSave, result.get());
    }

    @Test
    void saveElement_ErrorDuringSaving_ThrowsCustomException() {
        // Arrange
        Usuario usuarioToSave = new Usuario();
        when(usuarioRepository.save(usuarioToSave)).thenThrow(new RuntimeException("Error saving"));

        // Act & Assert
        assertThrows(CustomExceptions.class, () -> underTest.saveElement(usuarioToSave));
    }

    // Test similar scenarios for updateElement, deleteElementById, including edge cases and exceptions
}
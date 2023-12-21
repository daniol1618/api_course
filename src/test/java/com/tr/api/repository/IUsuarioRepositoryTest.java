package com.tr.api.repository;

import com.tr.api.model.Ciudad;
import com.tr.api.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class IUsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository underTest;

    @Test
    void whenSaveUsuario_ThenReturnSameUsuario() {
        //Arrange
        Usuario usuario = Usuario.builder()
                .nombre("Patricio")
                .email("patricio_estrella@gmail.com")
                .ciudad(Ciudad.BOGOTA)
                .build();

        //Act
        Usuario usuarioRetornado = underTest.save(usuario);

        //Assert
        assertThat(usuarioRetornado).isNotNull();
        assertThat(usuarioRetornado.getIdUsuario()).isNotNull(); // Assuming idUsuario is set after save
        assertThat(usuario.getNombre()).isEqualTo(usuarioRetornado.getNombre());
        assertThat(usuario.getEmail()).isEqualTo(usuarioRetornado.getEmail());
        assertThat(usuario.getCiudad()).isEqualTo(usuarioRetornado.getCiudad());

        Optional<Usuario> usuarioRetornadoOptional = Optional.of(underTest.save(usuario));
        assertThat(usuarioRetornadoOptional).isPresent();
    }

    @Test
    public void saveUsuarios_returnsUsuarios() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(null, "Martha Doe", "martha@example.com", Ciudad.BOGOTA),
                new Usuario(null, "Jane Castro", "jane@example.com", Ciudad.CARACAS),
                new Usuario(null, "Patricia Ramirez", "patricia@example.com", Ciudad.MADRID),
                new Usuario(null, "Caroline Smith", "carlines@example.com", Ciudad.BUENOS_AIRES),
                new Usuario(null, "Fernando Villavicencio", "fernando@example.com", Ciudad.CARTAGENA)
                // Add more sample Usuario objects as needed
        );

        underTest.saveAll(usuarios);

        List<Usuario> savedUsuarios = underTest.findAll();

        assertThat(savedUsuarios).isNotEmpty().hasSize(5);

        // Assert individual fields for each saved Usuario
        assertThat(savedUsuarios.get(0).getIdUsuario()).isNotNull();
        assertThat(savedUsuarios.get(0).getNombre()).isEqualTo("Martha Doe");
        assertThat(savedUsuarios.get(0).getEmail()).isEqualTo("martha@example.com");
        assertThat(savedUsuarios.get(0).getCiudad()).isEqualTo(Ciudad.BOGOTA);

        // Add similar assertions for other saved Usuarios
    }


}

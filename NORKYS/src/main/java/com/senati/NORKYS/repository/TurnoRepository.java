package com.senati.NORKYS.repository;

// Importamos la clase Turno, que representa la tabla 'turno' en la base de datos
import com.senati.NORKYS.model.Turno;

// Importamos JpaRepository para tener acceso a todos los métodos CRUD (crear, leer, actualizar, borrar)
import org.springframework.data.jpa.repository.JpaRepository;

// Importamos Repository para que Spring sepa que esta interfaz maneja la persistencia de datos
import org.springframework.stereotype.Repository;

// Le indicamos a Spring que esta interfaz es un repositorio
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // Al extender JpaRepository, automáticamente obtenemos:
    // - findAll() -> trae todos los turnos
    // - findById(Long id) -> busca un turno por su id
    // - save(Turno turno) -> guarda o actualiza un turno
    // - deleteById(Long id) -> borra un turno por su id
    //
    // Podemos agregar consultas personalizadas más adelante si queremos, por ejemplo:
    // Optional<Turno> findByNombre(String nombre);
}


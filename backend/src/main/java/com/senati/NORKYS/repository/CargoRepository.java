package com.senati.NORKYS.repository;
// Importamos la clase Cargo, que es nuestra entidad que representa la tabla 'cargo'
import com.senati.NORKYS.model.Cargo;
// Importamos JpaRepository que nos da todos los métodos para manejar la BD (CRUD, búsquedas, etc.)
import org.springframework.data.jpa.repository.JpaRepository;
// Importamos Repository para que Spring sepa que esta interfaz es un repositorio
import org.springframework.stereotype.Repository;

// Le decimos a Spring que esta interfaz es un repositorio, para manejar la persistencia de datos
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{
    // Al extender JpaRepository, automáticamente tenemos:
    // - findAll() -> trae todos los cargos
    // - findById(Long id) -> busca un cargo por su id
    // - save(Cargo cargo) -> guarda o actualiza un cargo
    // - deleteById(Long id) -> borra un cargo por su id
    //
    // No necesitamos escribir SQL explícito, JPA lo hace por nosotros
    // Si queremos, más adelante podemos agregar métodos personalizados, ejemplo:
    // Optional<Cargo> findByNombre(String nombre);
}

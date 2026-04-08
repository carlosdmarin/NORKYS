//Esta carpeta recibe las peticiones HTTP (frontend y backend)
package com.senati.NORKYS.controller; //Definimos el paquete donde esta mi clase

import com.senati.NORKYS.model.Empleado; //Importamos la clase empleado (nuestra entidad en la DB)
import com.senati.NORKYS.repository.AsistenciaRepository;
import com.senati.NORKYS.repository.EmpleadoRepository;
import com.senati.NORKYS.service.EmpleadoService; //Importamos la clase service, la logica
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired; //Esto sirve para que Spring inyecte dependencias autocmaticamente
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; //Importamos todas las anotaciones REST
import com.senati.NORKYS.repository.CargoRepository; //Traemos los repositorys para buscar los datos en la DB
import com.senati.NORKYS.repository.TurnoRepository; //Lo mismo que arriba
import java.time.LocalDate; // También necesitas esto para LocalDate.now(, que sirve para manejar las fechas

import java.util.List; //Importamos la interfaz List ya que vamos a devolver una lista de empleados

@RestController // Le decimos a Spring que esta clase es un controlador REST, manejará peticiones HTTP y devolvera JSON
@RequestMapping("/api/empleados") // Definimos la ruta base de nuestra API: todas las rutas aquí empezarán con /api/empleados
public class EmpleadoController { // Definimos la clase pública que será nuestro controlador, aca empieza nuestro controlador

    @Autowired // Spring inyecta automáticamente la dependencia del servicio
    private EmpleadoService empleadoService; // Variable del servicio para acceder a la lógica de negocio de empleados

    @GetMapping // Este método responderá a peticiones GET (obtener datos)
    public List<Empleado> listar() { // Método para listar todos los empleados
        return empleadoService.listarEmpleados(); // Retorna la lista de empleados desde el servicio
    }

    @Autowired // Spring inyecta automáticamente la dependencia del repositorio Cargo
    private CargoRepository cargoRepository; // Repositorio para acceder a los cargos en la base de datos

    @Autowired // Spring inyecta automáticamente la dependencia del repositorio Turno
    private TurnoRepository turnoRepository; // Repositorio para acceder a los turnos en la base de datos

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @PostMapping("/registrar") // Este método responderá a peticiones POST en /registrar para registrar un empleado
    public Empleado registrarEmpleado(@RequestBody Empleado empleado) {//El JSON que mandan se convierte en objeto Empleado
        // Si la fecha de ingreso no está definida, se asigna la fecha actual
        if (empleado.getFechaIngreso() == null) {
            empleado.setFechaIngreso(LocalDate.now());
        }

        // Buscar y asignar el Cargo desde la base de datos usando el ID que viene en el objeto empleado
        //Los pasos por que pasan son
        //	1.	Verificas que venga cargo
        //  2.	Verificas que tenga id
        // 	3.	Buscas ese cargo en la DB
        //	4.	Lo asignas al empleado
        if (empleado.getCargo() != null && empleado.getCargo().getId() != null) {
            empleado.setCargo(
                    cargoRepository.findById(empleado.getCargo().getId())
                            .orElse(null) // Si no encuentra el cargo, se asigna null
            );
        }

        // Buscar y asignar el Turno desde la base de datos usando el ID que viene en el objeto empleado
        if (empleado.getTurno() != null && empleado.getTurno().getId() != null) {
            empleado.setTurno(
                    turnoRepository.findById(empleado.getTurno().getId())
                            .orElse(null) // Si no encuentra el turno, se asigna null
            );
        }

        // Llamamos al servicio para registrar el empleado y devolvemos el objeto registrado
        return empleadoService.registarEmpleado(empleado);
    }
//  ACTUALIZAMOS EL EMPLEADO
    @PutMapping("/{id}") // Actualiza un empleado por su ID
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {

        // Actualizar Cargo si viene en el request
        if (empleado.getCargo() != null && empleado.getCargo().getId() != null) {
            empleado.setCargo(
                    cargoRepository.findById(empleado.getCargo().getId())
                            .orElse(null)
            );
        }

        // Actualizar Turno si viene en el request
        if (empleado.getTurno() != null && empleado.getTurno().getId() != null) {
            empleado.setTurno(
                    turnoRepository.findById(empleado.getTurno().getId())
                            .orElse(null)
            );
        }

        // Llamamos al servicio para actualizar los datos del empleado
        return empleadoService.actualizarEmpleado(id, empleado);
    }
    //Eliminamos el empleado y sus asistencias
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        // Paso 1: Borrar TODAS las asistencias ligadas a ese empleado
        asistenciaRepository.deleteByEmpleadoId(id);

        // Paso 2: Ahora sí, borrar al empleado gay
        empleadoRepository.deleteById(id);

        return ResponseEntity.ok("Empleado y su historial eliminados");
    }



}

package com.senati.NORKYS.service; //Estamos en la capa de la logica de negocio ;)


import com.senati.NORKYS.model.Empleado; //Importamos la entidad Empleado
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired; //Esto es para inyecte todas las dependecias automaticamente
import org.springframework.stereotype.Service; //Esta clase es la logica del negocio
import com.senati.NORKYS.repository.EmpleadoRepository; //Importamos el repository ya que el se comunica con la DB

import java.util.List; 

@Service
@Transactional
public class EmpleadoService {
    @Autowired //Inyecta todas las dependencias que vamos a ultizar
    private EmpleadoRepository empleadoRepository; //Declaramos la variable del repository y tendremos acceso a todos los metodos
    public List<Empleado> listarEmpleados() {// Definimos un metodo publico, su funcion es obtener todos los empleados
        return empleadoRepository.findAll(); //Es un metodo de JpaRepository y trae todos los registros de la tabla empleado
    }

    //Registrar empleados
    public Empleado registarEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    // Actualizar empleado
    public Empleado actualizarEmpleado(Long id, Empleado datosEmpleado) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));

        // Actualizamos los campos que queremos cambiar
        empleadoExistente.setTelefono(datosEmpleado.getTelefono());
        empleadoExistente.setFechaIngreso(datosEmpleado.getFechaIngreso());
        empleadoExistente.setCargo(datosEmpleado.getCargo());
        empleadoExistente.setTurno(datosEmpleado.getTurno());

        return empleadoRepository.save(empleadoExistente);
    }

    //Eliminamos el empleado
    public void eliminarEmpleado(Long id){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));

        empleadoRepository.delete(empleado);
    }

    //Agregamos este método nuevo para obtener empleados con turno
    public List<Empleado> listarEmpleadosConTurno() {
        return empleadoRepository.findAllWithTurno();
    }

}//Fin



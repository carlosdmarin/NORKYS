package com.senati.NORKYS.controller;

// IMPORTAMOS
import com.senati.NORKYS.model.Asistencia;
import com.senati.NORKYS.model.Empleado;
import com.senati.NORKYS.repository.EmpleadoRepository;
import com.senati.NORKYS.service.AsistenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    //Cargamos otra vez los empleados
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }
    @PostMapping("/registrar")
    public List<Asistencia> registrar(@RequestBody List<Asistencia> asistencias) {

        LocalDate hoy = LocalDate.now();

        asistencias.forEach(a -> {

            a.setFecha(hoy);

            if (a.getEmpleado() != null && a.getEmpleado().getId() != null) {

                Empleado emp = empleadoRepository
                        .findById(a.getEmpleado().getId())
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

                a.setEmpleado(emp);
            }
        });

        return asistenciaService.guardarAsistencias(asistencias);
    }

    @GetMapping("/historial")
    public ResponseEntity<List<Asistencia>> getAsistenciaPorFecha(@RequestParam("fecha") String fecha){
        LocalDate fechaConsulta = LocalDate.parse(fecha);
        List<Asistencia> asistencias = asistenciaService.findByFecha(fechaConsulta);
        return ResponseEntity.ok(asistencias);
    }
}


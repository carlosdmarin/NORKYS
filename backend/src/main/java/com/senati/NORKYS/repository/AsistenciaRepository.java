package com.senati.NORKYS.repository;


//IMPORTAMOS
import com.senati.NORKYS.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AsistenciaRepository extends JpaRepository <Asistencia, Long> {
    void deleteByEmpleadoId(Long empleadoId);

    //Esto busca asistencias por fecha
    List<Asistencia> findByFecha(LocalDate fecha);
}

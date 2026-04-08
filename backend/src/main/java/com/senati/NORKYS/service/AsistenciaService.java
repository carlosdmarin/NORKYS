package com.senati.NORKYS.service;


import com.senati.NORKYS.model.Asistencia;
import com.senati.NORKYS.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public List<Asistencia> guardarAsistencias(List<Asistencia> lista) {
        return asistenciaRepository.saveAll(lista);
    }

    //Metodo para para buscar por fecha
    public List<Asistencia> findByFecha(LocalDate fecha){
        return asistenciaRepository.findByFecha(fecha);
    }

}

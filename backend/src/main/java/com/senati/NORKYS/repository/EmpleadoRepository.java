package com.senati.NORKYS.repository; //Esta capa se encarga de entrar a la DB

import com.senati.NORKYS.model.Empleado;//Importamos la entidad empleado
import org.springframework.data.jpa.repository.JpaRepository; //Importamos repository con sus metodos
import org.springframework.stereotype.Repository; //Marca esta interfaz como accseso a la base de datos

@Repository // Indica que esta interfaz es un componente de persistencia.
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> { //Aca creamos una interfas no una clase

}
package com.senati.NORKYS.model;

//Importo
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table (name = "asistencia",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_empleado","fecha"})
        })
public class Asistencia {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_asistencia")
    private Long id;

    //RELACION CON EMPLEADO
    @ManyToOne
    @JoinColumn (name = "id_empleado", nullable = false)
    private Empleado empleado;


    @Column (nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private Estado estado;

    public enum Estado {
        Asistio,
        Tardanza,
        Falta
    }


    //GETTERS Y SETTERS

    public Long getId () {return id;}

    public Empleado getEmpleado () {return empleado;}
    public void setEmpleado (Empleado empleado) {this.empleado = empleado;}

    public LocalDate getFecha () {return fecha;}
    public void setFecha (LocalDate fecha) {this.fecha = fecha;}
    
    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado) {this.estado = estado;}
}

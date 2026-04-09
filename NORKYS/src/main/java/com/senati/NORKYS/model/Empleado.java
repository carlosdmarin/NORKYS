package com.senati.NORKYS.model;
// Importamos las anotaciones de JPA para mapear esta clase a la base de datos
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity //Esto dice que es una tabla o entidad en la DB
@Table(name = "empleado") // nombre de mi entidad en mi DB
public class Empleado {
    @Id //esto marca que sera una clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esto genera genera solo, es el autoicrementado en Mysql
    @Column(name = "id_empleado") //Es el nombre de la Columna en la DB
    private Long id;//En esto almacenamos los id de los empleados y permite guardar numeros grandes (long)


    //ATRUBUTOS EN LA DB
    //El private significa:No se puede tocar o cambiar ese dato directamente desde otra clase
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido") //Es el nombre de la columna en la DB
    private String apellido;

    @Column(name = "dni") //Es el nombre de la columna en  la DB
    private String dni;

    @Column(name = "telefono")
    private String telefono;


    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    //Referencia  a cargo
    @ManyToOne (fetch = FetchType.EAGER) // trae un empleado de la base de datos con su cargo
    @JoinColumn (name = "id_cargo") //Apunta a la culumna de la DB
    private Cargo cargo;


    //Referencia a turno
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_turno") //Apunta a la culumna de la DB
    private Turno turno;


    //GETTERS Y SETTERS
    //Son métodos para leer y modificar datos de un objeto
    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;}


        public String getNombre () {return nombre;} //devuelve el nombre del empleado
        public void setNombre (String nombre){this.nombre = nombre;} //Permite cambiar el nombre del empleado

        public String getApellido () {return apellido;} //devuele el apellido
        public void setApellido (String apellido){this.apellido = apellido;}

        public String getDni () {return dni;}
        public void setDni (String dni){this.dni = dni;}

        public String getTelefono () {return telefono;}
        public void setTelefono (String telefono){this.telefono = telefono;}

    // Devuelve la fecha de ingreso del empleado
    public LocalDate getFechaIngreso() { return fechaIngreso; }

    // Permite establecer o cambiar la fecha de ingreso del empleado
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    // Devuelve el cargo asociado al empleado (relación ManyToOne con Cargo)
    public Cargo getCargo() { return cargo; }

    // Permite asignar o cambiar el cargo del empleado
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    // Devuelve el turno asociado al empleado (relación ManyToOne con Turno)
    public Turno getTurno() { return turno; }

    // Permite asignar o cambiar el turno del empleado
    public void setTurno(Turno turno) { this.turno = turno; }

    }


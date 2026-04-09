package com.senati.NORKYS.model;

// Importamos las anotaciones de JPA para mapear esta clase a la base de datos
import jakarta.persistence.*;

// Le decimos a Spring/JPA que esta clase representa una entidad en la base de datos
@Entity

// Indicamos el nombre de la tabla en la base de datos que corresponde a esta entidad
@Table(name = "cargo")
public class Cargo {

    // Marcamos este atributo como la clave primaria de la tabla
    @Id

    // Configuramos que el valor se genere automáticamente (auto_increment en MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Definimos el nombre de la columna en la tabla
    @Column(name = "id_cargo")
    private Long id;

    // Definimos otra columna llamada 'nombre_cargo' que guardará el nombre del cargo
    @Column(name = "nombre_cargo")
    private String nombre;

    // Getters y Setters
    // Permiten acceder y modificar los valores de los atributos desde otras clases

    // Devuelve el ID del cargo
    public Long getId() { return id; }

    // Permite cambiar el ID del cargo (aunque normalmente lo maneja la BD)
    public void setId(Long id) { this.id = id; }

    // Devuelve el nombre del cargo
    public String getNombre() { return nombre; }

    // Permite cambiar el nombre del cargo
    public void setNombre(String nombre) { this.nombre = nombre; }
}
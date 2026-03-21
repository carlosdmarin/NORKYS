# Sistema de Control de registros de Empleados y Asistencias 

## Descripcion del negocio
Nombre: Norkys SAC <br>
Giro: Restaurante de comida rápida (pollería) <br>
Tamaño: Mediana empresa con múltiples locales <br>
Contexto: Cadena de pollerías muy reconocida en el Perú, enfocada en la venta de pollo a la brasa, acompañamientos y combos familiares. Maneja gran cantidad de clientes diariamente, especialmente en horarios pico, lo que requiere una buena organización del personal y control de asistencia. <br>
Justificacion: Se necesita un sistema digital para registrar empleados y controlar su asistencia diaria, reemplazando métodos manuales, reduciendo errores y permitiendo una mejor gestión del personal en cada local.


## Identificar el problema y solución
Problema: El control de asistencia de los empleados se realiza de forma manual o desorganizada, lo que genera errores en los registros, dificultad para verificar horarios de entrada y salida, y poca claridad en la asistencia diaria del personal en cada local. <br>
Solucion tecnologica: Desarrollar un sistema web utilizando Java Spring Boot y MySQL que permita registrar empleados y controlar su asistencia diaria, almacenando horarios de entrada y salida, mostrando reportes en tiempo real y facilitando la gestión eficiente del personal.


## Requerimientos Funcionales
| Codigo | Descripcion |
|---|---|
| RF01 | El sistema debe permitir registrar un nuevo empleado con nombre, apellido, DNI, telefono y cargo |
| RF02 | El sistema debe permitir registrar la asistencia diaria de los empleados indicando fecha, hora de entrada y hora de salida |
| RF03 | El sistema debe permitir editar o actualizar los datos de un empleado |
| RF04 | El sistema debe mostrar el listado de todos los empleados registrados |
| RF05 | El sistema debe mostrar el historial de asistencia de cada empleado |
| RF06 | El sistema debe permitir identificar el estado de asistencia (asistio, falta, tardanza) |
| RF07 | El sistema debe permitir generar reportes de asistencia por fecha o rango de fechas |



## Requerimientos No Funcionales
| Codigo | Tipo | Descripcion |
|---|---|---|
| RNF01 | Rendimiento | El sistema debe cargar cada módulo (dashboard, registro de empleados y asistencia) en menos de 3 segundos para garantizar una experiencia fluida |
| RNF02 | Usabilidad | La interfaz debe ser intuitiva, clara y fácil de usar para el personal administrativo sin necesidad de capacitación previa |
| RNF03 | Seguridad | El sistema debe permitir el acceso solo a usuarios autorizados mediante autenticación con usuario y contraseña |
| RNF04 | Disponibilidad | El sistema debe estar disponible durante el horario laboral para garantizar el registro continuo de asistencia |
| RNF05 | Escalabilidad | El sistema debe permitir la gestión de múltiples empleados y adaptarse al crecimiento de nuevos locales |



## Stack completo
1. Trello             = Gestión del proyecto (Kanban)
2. Draw.io            = Diagrama ER + Diagrama de Clases
3. Figma              = Wireframe + Diseño UI/UX
4. MySQL Workbench    = Diseñar y administrar BD
5. IntelliJ           = Frontend (HTML,CSS,JS) + Backend (Spring Boot)
6. XAMPP              = Servidor Tomcat para correr la app


## Tecnologias utilizadas
- Java 17
- Spring Boot 3
- MySQL 8
- HTML5, CSS3, JavaScript
- IntelliJ IDEA
- XAMPP (Tomcat)
- MySQL Workbench
- Figma (diseño UI/UX)
- Draw.io (diagramas)


## Base de datos
 
El sistema cuenta con 4 tablas principales:
 
| Tabla | Descripcion |
|---|---|
| CARGO | Define los diferentes roles o puestos de trabajo dentro de la empresa (ejemplo: administrador, cajero, etc.) |
| TURNO | Define los horarios de trabajo asignados a los empleados (mañana, tarde, noche) |
| EMPLEADO | Contiene la información de los empleados registrados en el sistema |
| ASISTENCIA | Registra la asistencia diaria de los empleados, incluyendo hora de entrada, salida y estado |


  
### Diagrama Entidad-Relacion (DER)

 
### Modelo Relacional (MR)





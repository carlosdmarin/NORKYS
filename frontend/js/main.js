// LOADER DE PANTALLA
window.addEventListener("load", () => {
  const loader = document.querySelector(".loader");

  setTimeout(() => {
    loader.classList.add("hidden");
  }, 1500); // tiempo del loader (1.5 segundos)
});

// CAMBIO DE SECCIONES
function mostrarSeccion(id, e) {
  e.preventDefault();

  document.querySelectorAll(".contenido").forEach((sec) => {
    sec.classList.remove("activo");
  });

  document.getElementById(id).classList.add("activo");

  if (id === "ver-empleados") {
    cargarEmpleados();
  }
}

//SIDEBAR MÓVIL
const btn = document.querySelector(".hamburguer");
const sidebar = document.querySelector(".sidebar");
const overlay = document.querySelector(".overlay");

// Abrir menú
btn.addEventListener("click", () => {
  sidebar.classList.add("active");
  overlay.classList.add("active");
});

// Cerrar menú
overlay.addEventListener("click", () => {
  sidebar.classList.remove("active");
  overlay.classList.remove("active");
});

// tablas empleado
function cargarEmpleados() {
  fetch("http://localhost:8080/api/empleados")
    .then((response) => response.json())
    .then((data) => {
      const tabla = document.getElementById("tabla-empleados");
      tabla.innerHTML = "";

      data.forEach((emp) => {
        tabla.innerHTML += `
    <tr>
        <td>${emp.id}</td>
        <td>${emp.nombre}</td>
        <td>${emp.apellido}</td>
        <td>${emp.dni}</td>
        <td class="telefono">${emp.telefono}</td>
        <td class="fechaIngreso">${emp.fechaIngreso}</td>
        <td class="cargo">${emp.cargo.nombre}</td>
        <td class="turno">${emp.turno.nombre}</td>
        <td>
            <button class="btn-editar">
             <i class="fa-solid fa-pen"></i>
            </button>
            <button class="btn-guardar" style="display:none;">
            <i class="fa-solid fa-floppy-disk"></i>
            </button>
           <button class="btn-eliminar">
             <i class="fa-solid fa-trash"></i>️
            </button>
        </td>
    </tr>
`;
      });
    })
    .catch((error) => console.error("Error:", error));
}



//ACTUALIZAR EMPLEADOS
      document.addEventListener("DOMContentLoaded", () => {
        const tabla = document.getElementById("tabla-empleados");

        tabla.addEventListener("click", (e) => {
          const target = e.target.closest("button");

          if (!target) return;

          const fila = target.closest("tr");

          // BOTÓN EDITAR
          if (target.classList.contains("btn-editar")) {
            fila
              .querySelectorAll(".telefono, .cargo, .turno, .fechaIngreso")
              .forEach((td) => {
                if (td.classList.contains("cargo")) {
                  td.innerHTML = `
            <select class="editar-cargo">
                <option value="1" ${td.innerText == "Encargado" ? "selected" : ""}>Encargado</option>
                <option value="2" ${td.innerText == "Administrador" ? "selected" : ""}>Administrador</option>
                <option value="3" ${td.innerText == "Cajero" ? "selected" : ""}>Cajero</option>
                <option value="4" ${td.innerText == "Cocina" ? "selected" : ""}>Cocina</option>
                <option value="5" ${td.innerText == "Producción" ? "selected" : ""}>Producción</option>
                <option value="6" ${td.innerText == "Cobrador" ? "selected" : ""}>Cobrador</option>
                <option value="7" ${td.innerText == "Personal de Servicio" ? "selected" : ""}>Personal de Servicio</option>
            </select>
        `;
                } else if (td.classList.contains("turno")) {
                  td.innerHTML = `
            <select class="editar-turno">
                <option value="1" ${td.innerText == "Mañana" ? "selected" : ""}>Mañana</option>
                <option value="2" ${td.innerText == "Tarde" ? "selected" : ""}>Tarde</option>
                <option value="3" ${td.innerText == "Noche" ? "selected" : ""}>Noche</option>
            </select>
        `;
                } else if (td.classList.contains("fechaIngreso")) {
                  const valorActual = td.innerText;
                  td.innerHTML = `<input type="date" class="editar-fecha" value="${valorActual}">`;
                } else {
                  td.setAttribute("contenteditable", "true");
                  td.classList.add("editable");
                }
              });

            fila.querySelector(".btn-editar").style.display = "none";
            fila.querySelector(".btn-guardar").style.display = "inline-block";
          }
          // BOTÓN GUARDAR
          else if (target.classList.contains("btn-guardar")) {
            const empleadoActualizado = {
              id: parseInt(fila.children[0].innerText),
              nombre: fila.children[1].innerText,
              apellido: fila.children[2].innerText,
              dni: fila.children[3].innerText,
              telefono: fila.querySelector(".telefono").innerText,
              fechaIngreso: fila.querySelector(".editar-fecha")
                ? fila.querySelector(".editar-fecha").value
                : fila.querySelector(".fechaIngreso").innerText,

              cargo: {
                id: parseInt(fila.querySelector(".editar-cargo").value),
              },

              turno: {
                id: parseInt(fila.querySelector(".editar-turno").value),
              },
            };

            // Volver a mostrar texto en lugar de select o input
            if (fila.querySelector(".editar-cargo"))
              fila.querySelector(".cargo").innerText =
                fila.querySelector(".editar-cargo").selectedOptions[0].text;

            if (fila.querySelector(".editar-turno"))
              fila.querySelector(".turno").innerText =
                fila.querySelector(".editar-turno").selectedOptions[0].text;

            // REEMPLAZAR input de fecha por texto
            if (fila.querySelector(".editar-fecha"))
              fila.querySelector(".fechaIngreso").innerText =
                fila.querySelector(".editar-fecha").value;

            fila
              .querySelectorAll(".telefono, .cargo, .turno, .fechaIngreso")
              .forEach((td) => {
                td.removeAttribute("contenteditable");
                td.classList.remove("editable");
              });

            fila.querySelector(".btn-editar").style.display = "inline-block";
            fila.querySelector(".btn-guardar").style.display = "none";

            // Enviar a Spring Boot
            fetch(
              `http://localhost:8080/api/empleados/${empleadoActualizado.id}`,
              {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(empleadoActualizado),
              },
            )
              .then((res) => res.json())
              .then((data) => alert(`Empleado ${data.nombre} actualizado`))
              .catch((err) => console.error(err));
          }
          // BOTÓN ELIMINAR
          else if (target.classList.contains("btn-eliminar")) {
            const id = fila.children[0].innerText;

            if (confirm("¿Seguro que quieres eliminar este empleado?")) {
              fetch(`http://localhost:8080/api/empleados/${id}`, {
                method: "DELETE",
              })
                .then(() => {
                  alert("Empleado eliminado correctamente");
                  fila.remove();
                })
                .catch((err) => console.error(err));
            }
          }
        });
      });
    


//Registrar EMPLEAOD 
      const form = document.getElementById("formEmpleado");

      form.addEventListener("submit", function (e) {
        e.preventDefault(); // evita que recargue la página

        const empleado = {
          nombre: document.getElementById("nombre").value,
          apellido: document.getElementById("apellido").value,
          dni: document.getElementById("dni").value,
          telefono: document.getElementById("telefono").value,
          cargo: { id: parseInt(document.getElementById("cargo").value) },
          turno: { id: parseInt(document.getElementById("turno").value) },
        };
        fetch("http://localhost:8080/api/empleados/registrar", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(empleado),
        })
          .then((response) => response.json())
          .then((data) => {
            alert("Empleado registrado: " + data.nombre + " " + data.apellido);
            form.reset();
          })
          .catch((error) => console.error("Error:", error));
      });


//SECIONES
      function toggleSubmenu(e) {
        e.preventDefault();

        const item = e.currentTarget.parentElement;

        item.classList.toggle("active");
      }


// ASISTENCIA
      document.addEventListener("click", (e) => {
        const celda = e.target.closest(".celda-estado");
        if (!celda) return;

        const fila = celda.closest("tr");

        // limpiar TODA la fila
        fila.querySelectorAll(".celda-estado").forEach((td) => {
          td.classList.remove("activo");

          const icono = td.querySelector("i");
          if (icono) icono.classList.remove("activo");
        });

        // activar celda
        celda.classList.add("activo");

        const icono = celda.querySelector("i");
        if (icono) icono.classList.add("activo");
      });
  
// ==========================================
// 1. CARGAR EMPLEADOS (cuando se abre la página)
// ==========================================
fetch("http://localhost:8080/api/asistencia/empleados") 
    .then((response) => response.json())
    .then((empleados) => {
        const tbody = document.querySelector(".tabla-asistencia-pro tbody");
        tbody.innerHTML = "";
        empleados.forEach((emp) => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td class="col-estudiante" data-id="${emp.id}">
                    <img src="" alt="" />
                    <span>${emp.nombre} ${emp.apellido}</span>
                </td>
                <td class="celda-estado"><i class="fa-solid fa-check estado presente"></i></td>
                <td class="celda-estado"><i class="fa-regular fa-clock estado atrasado"></i></td>
                <td class="celda-estado"><i class="fa-solid fa-xmark estado ausente"></i></td>
            `;
            tbody.appendChild(tr);
        });
    })
    .catch((err) => console.error("Error al cargar empleados:", err));

// ==========================================
// 2. GUARDAR ASISTENCIA
// ==========================================
document.querySelector(".btn-guardar-asistencia").addEventListener("click", () => {
    const filas = document.querySelectorAll(".tabla-asistencia-pro tbody tr");
    const asistencias = [];

    filas.forEach((fila) => {
        const idEmpleado = fila.querySelector(".col-estudiante").dataset.id;

        let estado = null;

    
        if (fila.children[1].classList.contains("activo")) {
            estado = "Asistio";  // ← USAMOS MAYÚSCULAS como en el Backend
        } else if (fila.children[2].classList.contains("activo")) {
            estado = "Tardanza";   // ← USAMOS MAYÚSCULAS
        } else if (fila.children[3].classList.contains("activo")) {
            estado = "Falta";   // ← USAMOS MAYÚSCULAS
        }

        if (estado && idEmpleado) {
            asistencias.push({
                empleado: { id: parseInt(idEmpleado) },
                estado: estado,
                horaEntrada: "08:00:00",  // ← MI BACKEND ESPERA ESTO
                horaSalida: "17:00:00"    // ← MI BACKEND ESPERA ESTO
            });
        }
    });

    console.log("Datos a enviar:", asistencias);

    fetch("http://localhost:8080/api/asistencia/registrar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(asistencias),
    })
        .then((res) => {
            if (!res.ok) throw new Error("Error al guardar");
            return res.json();
        })
        .then((data) => {
            alert("✅ Asistencia guardada correctamente");
        })
        .catch((err) => {
            alert("⚠️ Error, puede que ya marcó hoy o faltan datos");
            console.error(err);
        });
});



//===========================================
//HISTORIAL DE ASISTENCIAS
//===========================================

async function cargarAsistencias(fecha) {
    const tablaBody = document.getElementById('tabla-historial');
    
    if (!tablaBody) {
        console.error("No se encontró el elemento tabla-historial");
        return;
    }
    
    try {
        const url = `http://localhost:8080/api/asistencia/historial?fecha=${fecha}`;
        console.log("Llamando a:", url);
        
        const response = await fetch(url);
        console.log("Status response:", response.status);
        
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }
        
        const asistencias = await response.json();
        console.log("Datos recibidos:", asistencias);
        
        //Esto limpia la tabla
        tablaBody.innerHTML = '';
        
        if (asistencias.length === 0) {
            tablaBody.innerHTML = '<tr><td colspan="3">No hay registros para esta fecha</td></tr>';
            return;
        }
        
        // Recorrer cada asistencia y crear fila
        asistencias.forEach(asistencia => {
            const nombreCompleto = `${asistencia.empleado.nombre} ${asistencia.empleado.apellido}`;
            
            let estadoTexto = '';
            let estadoClass = '';
            
            switch(asistencia.estado) {
                case 'Asistio':
                    estadoTexto = 'Asistió';
                    estadoClass = 'estado-asistio';
                    break;
                case 'Tardanza':
                    estadoTexto = 'Tardanza';
                    estadoClass = 'estado-tardanza';
                    break;
                case 'Falta':
                    estadoTexto = 'Falta';
                    estadoClass = 'estado-falta';
                    break;
                default:
                    estadoTexto = asistencia.estado;
                    estadoClass = '';
            }
            
            const fila = `
                <tr>
                    <td>${nombreCompleto}</td>
                    <td>${asistencia.fecha}</td>
                    <td class="${estadoClass}">${estadoTexto}</td>
                </tr>
            `;
            
            tablaBody.innerHTML += fila;
        });
        
        console.log("Tabla actualizada con éxito");
        
   } catch (error) {
        console.error('ERROR DETALLADO:', error.message);
        if (tablaBody) {
            tablaBody.innerHTML = `<tr><td colspan="3">Error: ${error.message}</td></tr>`;
        }
    }
}


//
document.addEventListener('DOMContentLoaded', function() {
    console.log("DOM cargado - Inicializando historial");
    
    const fechaInput = document.getElementById('fecha-historial');
    
    if (!fechaInput) {
        console.error("No se encontró el input fecha-historial");
        return;
    }
    
    // Establecer fecha actual
    const hoy = new Date().toISOString().split('T')[0];
    fechaInput.value = hoy;
    console.log("Fecha actual:", hoy);
    
    // Cargar asistencias del día actual
    cargarAsistencias(hoy);
    
    // Escuchar cambios en el input de fecha
    fechaInput.addEventListener('change', function(e) {
        const fechaSeleccionada = e.target.value;
        console.log("Fecha seleccionada:", fechaSeleccionada);
        cargarAsistencias(fechaSeleccionada);
    });
});




// Función para cargar el dashboard
async function cargarDashboard() {
    try {
        // Llamo a mi API
        const response = await fetch('http://localhost:8080/api/dashboard/estadisticas');
        const data = await response.json();
        
        console.log('Datos recibidos:', data); // Para depuración
        
        //CARD 1: Total de Empleados (SIEMPRE se muestra)
        document.getElementById('totalEmpleados').textContent = data.totalEmpleados;
        
        // Verificamos si ya se tomó asistencia hoy
        if (data.asistenciaTomadaHoy) {
            //CARD 2: Asistieron Hoy
            document.getElementById('asistieron').textContent = data.asistieron;
            
            //CARD 3: Faltas
            document.getElementById('faltaron').textContent = data.faltaron;
            
            //CARD 4: Tardanza
            document.getElementById('llegaronTarde').textContent = data.llegaronTarde;
            
            // Opcional: cambiar estilos para mostrar que hay datos
            document.querySelectorAll('.card-verde, .card-rojo, .card-morado').forEach(card => {
                card.style.opacity = '1';
            });
            
        } else {
            // Si NO se ha tomado asistencia, mostrar 0 o mensaje
            document.getElementById('asistieron').textContent = '?';
            document.getElementById('faltaron').textContent = '?';
            document.getElementById('llegaronTarde').textContent = '?';
            
            //mostrar tooltip o mensaje
            console.log(data.mensaje);
            
            //Se puede mostrar un mensaje flotante
            mostrarMensaje(data.mensaje);
        }
        
    } catch (error) {
        console.error('Error al cargar dashboard:', error);
        // Mostrar error en los cards
        document.getElementById('totalEmpleados').textContent = 'Error';
        document.getElementById('asistieron').textContent = 'Error';
        document.getElementById('faltaron').textContent = 'Error';
        document.getElementById('llegaronTarde').textContent = 'Error';
    }
}

// Función opcional para mostrar mensaje
function mostrarMensaje(mensaje) {
    // Crear un toast o alerta suave
    const toast = document.createElement('div');
    toast.textContent = mensaje;
    toast.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #ff9800;
        color: white;
        padding: 12px 20px;
        border-radius: 8px;
        z-index: 1000;
        animation: fadeInOut 3s ease;
    `;
    document.body.appendChild(toast);
    setTimeout(() => toast.remove(), 3000);
}

// Cargar dashboard cuando la página esté lista
document.addEventListener('DOMContentLoaded', cargarDashboard);

//Actualizar cada 30 segundos
setInterval(cargarDashboard, 30000);
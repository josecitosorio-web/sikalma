<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Editar Atención - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <div class="retorno">
                    <a href="/atencion/gestion">Gestión de Atenciones</a>
                    <span>›</span>
                    <span>Editar Atención #A0${atencion.id}</span>
                </div>
                <h1>Editar Atención</h1>
                <p>Modifica los datos clínicos de la atención registrada</p>
            </div>
            <span class="estado estado-atendido">${atencion.estado}</span>
        </div>

        <div class="formulario-card">

            <c:if test="${not empty error}">
                <div style="display: flex; align-items: center; gap: 12px; background-color: #FFFFFF; color: #842029; padding: 15px 20px; border-radius: 8px; margin-bottom: 25px; border-left: 5px solid #dc3545; box-shadow: 0 2px 12px rgba(0,0,0,0.06); font-size: 0.95rem;">
                    <span style="color: #dc3545; font-size: 1.1rem; font-weight: bold;">⚠️</span>
                    <span style="font-weight: 500;">${error}</span>
                </div>
            </c:if>
            
            <h2>Datos de la Atención — ID: #A0${atencion.id} / Cita origen: #${atencion.cita.id}</h2>

            <form action="/atencion/actualizar" method="post">

                <input type="hidden" name="id" value="${atencion.id}">
                <input type="hidden" name="citaId" value="${atencion.cita.id}">

                <div class="fila-form">
                    <div>
                        <label>Paciente</label>
                        <input type="text" value="${atencion.cita.paciente.nombres}"
                               readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                    <div>
                        <label>Doctor</label>
                        <input type="text" value="${atencion.cita.doctor.nombre}"
                               readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Servicio</label>
                        <input type="text" value="${atencion.cita.servicio.nombre}"
                               readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                    <div>
                        <label>Fecha de atención</label>
                        <input type="date" value="${atencion.cita.fecha}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                </div>

                <!-- Campos editables -->
                <div class="fila-form">
                    <div>
                        <label>Hora de inicio</label>
                        <input type="time" value="${atencion.horaInicio}" name="horaInicio" readonly >
                    </div>
                    <div>
                        <label>Hora de fin</label>
                        <input type="time" name="horaFin" value="${atencion.horaFin}">
                    </div>
                </div>

                <div class="campo">
                    <label>Diagnóstico</label>
                    <input type="text" value="${atencion.diagnostico}" name="diagnostico">
                </div>

                <div class="campo">
                    <label>Tratamiento indicado</label>
                    <input type="text" value="${atencion.tratamiento}" name="tratamiento">
                </div>

                <div class="fila-form">
                    <div>
                        <label>Costo del servicio (S/)</label>
                        <input type="text" value="${atencion.cita.servicio.costo}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                    <div>
                        <label>Estado de la atención</label>
                        <select name="estado">
                            <option value="${atencion.estado}">${atencion.estado}</option>
                            <option  value="En curso">En curso</option>
                            <option value="Completada">Completada</option>
                        </select>
                    </div>
                </div>

                <div class="form-acciones">
                    <a href="/atencion/gestion" class="btn-secundario">Cancelar</a>
                    <button type="submit" class="btn-primario">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>

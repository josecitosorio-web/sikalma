<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Ver Atención - SIKALMA</title>
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
                    <span>Ver Atención #A0${atencion.id}</span>
                </div>
                <h1>Ver Atención</h1>
                <p>Visualiza toda la información de la atención médica</p>
            </div>
        </div>

        <div class="formulario-card">
            <h2>Datos de la Atención — ID: #A0${atencion.id} / Cita origen: #${atencion.cita.id}</h2>

            <form>
                <div class="fila-form">
                    <div>
                        <label>Paciente</label>
                        <input type="text" value="${atencion.cita.paciente.nombres}" readonly>
                    </div>
                    <div>
                        <label>Doctor</label>
                        <input type="text" value="${atencion.cita.doctor.nombre}" readonly>
                    </div>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Servicio</label>
                        <input type="text" value="${atencion.cita.servicio.nombre}" readonly>
                    </div>
                    <div>
                        <label>Fecha de atención</label>
                        <input type="text" value="${atencion.cita.fecha}" readonly>
                    </div>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Hora de inicio</label>
                        <input type="text" value="${atencion.horaInicio}" readonly>
                    </div>
                    <div>
                        <label>Hora de fin</label>
                        <input type="text" value="${atencion.horaFin}" readonly>
                    </div>
                </div>

                <div class="campo">
                    <label>Diagnóstico</label>
                    <textarea rows="3" readonly>${atencion.diagnostico}</textarea>
                </div>

                <div class="campo">
                    <label>Tratamiento indicado</label>
                    <textarea rows="3" readonly>${atencion.tratamiento}</textarea>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Costo del servicio (S/)</label>
                        <input type="text" value="${atencion.cita.servicio.costo}" readonly>
                    </div>
                    <div>
                        <label>Estado de la atención</label>
                        <input type="text" value="${atencion.estado}" readonly>
                    </div>
                </div>

                <div class="form-acciones">
                    <a href="/atencion/gestion" class="btn-secundario">Volver</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>

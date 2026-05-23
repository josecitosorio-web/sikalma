<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="../images/logo-policlinico.png">
    <title>Registrar Atención - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/registrar-atencion.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <div class="retorno">
                    <a href="/cita/g-citas">Gestión de Citas</a>
                    <span>›</span>
                    <a href="/atencion/gestion">Gestión de Atenciones</a>
                    <span>›</span>
                    <span>Registrar Atención</span>
                </div>
                <h1>Registrar Atención</h1>
                <p>Convierte la cita en una atención médica completando los datos clínicos</p>
            </div>
        </div>

        <!-- Cita de origen (datos pre-cargados) -->
        <div class="cita-origen">
            <h3>CITA DE ORIGEN — #00${cita.id}</h3>
            <div class="cita-origen-datos">
                <span><strong>Paciente:</strong>${cita.paciente.nombres}</span>
                <span><strong>Servicio:</strong>${cita.servicio.nombre}</span>
                <span><strong>Doctor:</strong>${cita.doctor.nombre}</span>
                <span><strong>Fecha:</strong>${cita.fecha}</span>
                <span><strong>Hora:</strong>${cita.hora}</span>
                <span><strong>Estado de cita:</strong> <span class="badge badge-confirmado">${cita.estado}</span></span>
            </div>
        </div>

        <div class="formulario-card">

            <c:if test="${not empty error}">
                <div style="display: flex; align-items: center; gap: 12px; background-color: #FFFFFF; color: #842029; padding: 15px 20px; border-radius: 8px; margin-bottom: 25px; border-left: 5px solid #dc3545; box-shadow: 0 2px 12px rgba(0,0,0,0.06); font-size: 0.95rem;">
                    <span style="color: #dc3545; font-size: 1.1rem; font-weight: bold;">⚠️</span>
                    <span style="font-weight: 500;">${error}</span>
                </div>
            </c:if>

            <h2>Datos de la Atención</h2>

            <form action="/atencion/nuevo" method="post">
                <!-- Datos heredados de la cita (bloqueados) -->
                <input type="hidden" name="citaId" value="${cita.id}">

                <div class="fila-form">
                    <div>
                        <label>Paciente</label>
                        <input type="text" value="${cita.paciente.nombres}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                    <div>
                        <label>Doctor que atiende</label>
                        <input type="text" value="${cita.doctor.nombre}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Servicio</label>
                        <input type="text" value="${cita.servicio.nombre}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                    <div>
                        <label>Fecha de atención</label>
                        <input type="date" name="fecha" value="${cita.fecha}" readonly style="background-color:#f5f0f0; color:#9a8a88;">
                    </div>
                </div>

                <!-- Datos propios de la atención -->
                <div class="fila-form">
                    <div>
                        <label>Hora de inicio</label>
                        <input type="time" name="horaInicio" value="${cita.hora}">
                    </div>
                    <div>
                        <label>Hora de fin</label>
                        <input type="time" name="horaFin">
                    </div>
                </div>

                <div class="campo">
                    <label>Diagnóstico</label>
                    <textarea name="diagnostico" rows="3" placeholder="Describa el diagnóstico del paciente..."></textarea>
                </div>

                <div class="campo">
                    <label>Tratamiento indicado</label>
                    <textarea name="tratamiento" rows="3" placeholder="Indique el tratamiento o medicación recetada..."></textarea>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Costo del servicio (S/)</label>
                        <input type="text" value="${cita.servicio.costo}" name="costo">
                    </div>
                    <div>
                        <label>Estado de la atención</label>
                        <select name="estado">
                            <option>En curso</option>
                            <option>Completada</option>
                        </select>
                    </div>
                </div>

                <div class="form-acciones">
                    <a href="/atencion/gestion" class="btn-secundario">Cancelar</a>
                    <button type="submit" class="btn-primario">Registrar Atención</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>

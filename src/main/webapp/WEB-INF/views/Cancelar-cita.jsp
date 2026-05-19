<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Cancelar Cita - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/eliminar.css">
</head>
<body class="modal-eliminar">
    <div class="modal-card">
        <div class="icono-alerta">⚠️</div>
        <h2>¿Cancelar esta cita?</h2>
        <p>
            Estás a punto de cancelar la cita de <strong>${cita.paciente.nombres}</strong>
            programada para el <strong>${cita.fecha} a las ${cita.hora} horas</strong>
            con <strong>Dra. ${cita.doctor.nombre}</strong>.
            <br><br>
            Esta acción no puede deshacerse.
        </p>
        <div class="modal-acciones">
            <a href="/cita/g-citas" class="btn-secundario">Volver</a>
            <a href="/cita/eliminar?id=${cita.id}" class="btn-danger">Sí, cancelar cita</a>
        </div>
    </div>
</body>
</html>

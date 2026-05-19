<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Eliminar Servicio - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/eliminar.css">
</head>
<body class="modal-eliminar">
<div class="modal-card">
    <div class="icono-alerta">⚠️</div>
    <h2>¿Eliminar este servicio?</h2>
    <p>
        Estás a punto de eliminar el servicio <strong>${servicio.nombre}</strong>
        Si existen citas o atenciones asociadas a este servicio,
        podría afectar los registros existentes.
        <br><br>
        Esta acción no puede deshacerse.
    </p>
    <div class="modal-acciones">
        <a href="/servicio/gestion" class="btn-secundario">Volver</a>
        <a href="/servicio/eliminar?id=${servicio.id}" class="btn-danger">Sí, eliminar</a>
    </div>
</div>
</body>
</html>
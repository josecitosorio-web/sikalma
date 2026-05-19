<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Doctor - SIKALMA</title>
    <link rel="stylesheet" href="../css/admin.css">
    <link rel="stylesheet" href="../css/eliminar.css">
</head>
<body class="modal-eliminar">
<div class="modal-card">
    <div class="icono-alerta">⚠️</div>
    <h2>¿Eliminar este doctor?</h2>
    <p>
        Estás a punto de eliminar al Dr(a). <strong>${doctor.nombre}</strong>. Si existen citas o atenciones asociadas, estas podrían quedar sin personal asignado para su atención.
        <br><br>
        Esta acción no puede deshacerse.
    </p>
    <div class="modal-acciones">
        <a href="/doctor/gestion" class="btn-secundario">Volver</a>
        <a href="/doctor/eliminar?id=${doctor.id}" class="btn-danger">Sí, eliminar</a>
    </div>
</div>
</body>
</html>
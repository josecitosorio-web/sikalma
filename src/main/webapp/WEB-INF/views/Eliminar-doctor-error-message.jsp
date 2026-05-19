<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Eliminar Paciente - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/eliminar.css">
</head>
<body class="modal-eliminar">
    <div class="modal-card">
        <div class="icono-alerta">⚠️</div>
        <h2>ERROR</h2>
        <p>
            ${error}
        </p>
        <div class="modal-acciones">
            <a href="/doctor/gestion" class="btn-secundario">Volver</a>
        </div>
    </div>
</body>
</html>

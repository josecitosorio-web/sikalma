<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Doctor - SIKALMA</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <h1>Editar Usuario</h1>
                <p>Actualice la información del usuario</p>
            </div>
        </div>

        <div class="formulario-card" style="position: relative;">

            <c:if test="${not empty error}">
                <div style="display: flex; align-items: center; gap: 12px; background-color: #FFFFFF; color: #842029; padding: 15px 20px; border-radius: 8px; margin-bottom: 25px; border-left: 5px solid #dc3545; box-shadow: 0 2px 12px rgba(0,0,0,0.06); font-size: 0.95rem;">
                    <span style="color: #dc3545; font-size: 1.1rem; font-weight: bold;">⚠️</span>
                    <span style="font-weight: 500;">${error}</span>
                </div>
            </c:if>
            <form action="/usuario/editar" method="post">
                <input type="hidden" name="idUsuario" value="${usuario.id}">
                <input type="hidden" name="idDoctor" value="${usuario.doctor.id}">
                <input type="hidden" name="rol" value="${usuario.rol}">

                <div class="campo">
                    <label>Nombre </label>
                    <input type="text" name="nombre" value="${usuario.doctor.nombre}" readonly>
                </div>

                <div class="campo">
                    <label>Correo Electrónico</label>
                    <input type="email" name="correo" value="${usuario.correo}" readonly>
                </div>

                <div class="fila-form">
                    <div class="campo">
                        <label>Contraseña</label>
                        <input type="password" name="contrasena" value="${usuario.contrasena}" required>
                    </div>
                    
                </div>
                <div class="form-acciones" style="margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px;">
                    <a href="/usuario/gestion" class="btn-secundario">Cancelar</a>
                    <button type="submit" class="btn-primario" style="background-color: #007bff; border: none; padding: 10px 20px; border-radius: 6px;">Actualizar Usuario</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
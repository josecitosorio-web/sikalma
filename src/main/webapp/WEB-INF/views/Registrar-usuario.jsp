<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Doctor - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <h1>Registrar Nuevo Usuario</h1>
                <p>Complete el perfil del nuevo usuario</p>
            </div>
        </div>

        <div class="formulario-card" style="position: relative;">


            <div class="buscador-seccion" style="margin: 20px 0; background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.05);">
                <form action="/usuario/buscar" method="get" style="display: flex; gap: 10px; align-items: center;">
                    <input type="text" name="dni" placeholder="Ingrese DNI del doctor..."
                        style="padding: 10px; border: 1px solid #ddd; border-radius: 5px; width: 300px;" required>
                    <button type="submit" class="btn-primario">Buscar por DNI</button>
                    <a href="/usuario/gestion" class="btn-secundario" style="text-decoration: none;">Limpiar</a>
                </form>
            </div>

            <c:if test="${not empty error}">
                <div style="display: flex; align-items: center; gap: 12px; background-color: #FFFFFF; color: #842029; padding: 15px 20px; border-radius: 8px; margin-bottom: 25px; border-left: 5px solid #dc3545; box-shadow: 0 2px 12px rgba(0,0,0,0.06); font-size: 0.95rem;">
                    <span style="color: #dc3545; font-size: 1.1rem; font-weight: bold;">⚠️</span>
                    <span style="font-weight: 500;">${error}</span>
                </div>
            </c:if>
            <form action="/usuario/registrar" method="post">
                <div class="campo">
                        <label>Doctor</label>
                         <input type="text" name="nombre" value="${doctor.nombre}"  readonly>

                </div>
                <div class="campo">
                    <label>Correo Electronico</label>
                         <input type="text" name="correo" value="${doctor.correo}"  readonly>
                </div>

                <div class="fila-form">
                    <div class="campo">
                        <label>Contraseña</label>
                        <input type="password" name="contrasena" value="${usuario.contrasena}"  placeholder="contraseña" required>
                    </div>

                </div>

                <input type="hidden" name="rol" value="DOCTOR">
                <input type="hidden" name="idDoctor" value="${doctor.id}"> 

                <div class="form-acciones" style="margin-top: 30px; border-top: 1px solid #eee; padding-top: 20px;">
                    <a href="/doctor/gestion" class="btn-secundario">Cancelar</a>
                    <button type="submit" class="btn-primario" style="background-color: #007bff; border: none; padding: 10px 20px; border-radius: 6px;">Registrar Usuario</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
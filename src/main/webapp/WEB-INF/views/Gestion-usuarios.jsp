<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Uusarios - SIKALMA</title>
    <link rel="stylesheet" href="../css/admin.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <h1>Gestión de Usuario</h1>
                <p>Visualice y administre la información de los usuarios registrados</p>
            </div>
            <a href="/usuario/nuevo" class="btn-primario">+ Crear nuevo usuario</a>
        </div>

        <div class="buscador-seccion" style="margin: 20px 0; background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.05);">
            <form action="/usuario/buscar-usuario" method="get" style="display: flex; gap: 10px; align-items: center;">
                <input type="text" name="dni" placeholder="Ingrese DNI del doctor..."
                       style="padding: 10px; border: 1px solid #ddd; border-radius: 5px; width: 300px;" required>
                <button type="submit" class="btn-primario">Buscar por DNI</button>
                <a href="/usuario/gestion" class="btn-secundario" style="text-decoration: none;">Limpiar</a>
            </form>
        </div>

        <div class="tabla-contenedor">
            <table class="tabla-gestion">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Correo</th>
                        <th>Rol</th>
                        <th>Doctor</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${usuarios}">
                        <tr>
                            <td>#U${user.id}</td>
                            <td>${user.correo}</td>
                            <td>${user.rol}</td>
                            <td>${user.doctor.nombre}</td>
                            <td class="acciones-celda">
                                <a href="/usuario/editar?id=${user.id}" class="btn-editar">Editar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
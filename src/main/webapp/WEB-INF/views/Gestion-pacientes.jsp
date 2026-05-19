<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Pacientes - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/gestion-pacientes.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <h1>Gestión de Pacientes</h1>
                <p>Consulta, edita o elimina los registros de los pacientes del policlínico</p>
            </div>
            <a href="/paciente/nuevo" class="btn-primario">+ Nuevo Paciente</a>
        </div>

        <div class="tabla-contenedor">
            <div class="tabla-encabezado">
                <div>
                    <h3>Listado de Pacientes</h3>
                    <p>Total: ${pacientes.size()} registros</p>
                </div>
            </div>

            <form action="/paciente/buscar" method="get">
                <div class="busqueda-barra">
                    <input type="search" name="dni" placeholder="Buscar por DNI...">
                    <button type="submit" class="btn-primario">Buscar</button>
                </div>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre completo</th>
                        <th>DNI</th>
                        <th>Edad</th>
                        <th>Teléfono</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="paciente" items="${pacientes}" >

                        <tr>
                            <td>#P0${paciente.id}</td>
                            <td>${paciente.nombres}</td>
                            <td>${paciente.dni}</td>
                            <td>${paciente.edad}</td>
                            <td>${paciente.telefono}</td>
                            <td class="td-acciones">
                                <a href="/paciente/editar?id=${paciente.id}" class="btn-editar">Editar</a>
                                <a href="/paciente/advertir?id=${paciente.id}" class="btn-eliminar">Eliminar</a>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>

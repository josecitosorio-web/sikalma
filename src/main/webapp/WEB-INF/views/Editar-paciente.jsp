<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Editar Paciente - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <div class="retorno">
                    <a href="/paciente/gestion">Pacientes</a>
                    <span>›</span>
                    <span>Editar Paciente #P0${paciente.id}</span>
                </div>
                <h1>Editar Paciente</h1>
                <p>Modifica los datos del paciente registrado</p>
            </div>
        </div>

        <div class="formulario-card">
            <h2>Datos del Paciente — ID: #P0${paciente.id}</h2>

            <form action="/paciente/editar" method="post" >

                <input type="hidden" name="id" value="${paciente.id}">

                <div class="campo">
                    <label>Nombre completo</label>
                    <input type="text" value="${paciente.nombres}" name="nombres" required>
                </div>

                <div class="campo">
                    <label>DNI</label>
                    <input type="text" value="${paciente.dni}" name="dni"  maxlength="8" pattern="\d{8}" inputmode="numeric" required >
                    <span class="indicacion">Debe contener exactamente 8 dígitos.</span>
                </div>

                <div class="fila-form">
                    <div>
                        <label>Teléfono</label>
                        <input type="text" value="${paciente.telefono}" maxlength="9" pattern="9\d{8}" inputmode="numeric" name="telefono" required >
                    </div>

                    <div>
                        <label>Fecha de nacimiento</label>
                        <input type="date" value="${paciente.fechaNacimiento}" maxlength="9" name="fechaNacimiento" required >
                    </div>
                </div>

                <c:if test="${not empty error}">

                    <div class="error">

                        ${error}

                    </div>

                </c:if>


                <div class="form-acciones">
                    <a href="/paciente/gestion" class="btn-secundario">Cancelar</a>
                    <button type="submit" class="btn-primario">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>

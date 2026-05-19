<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <link rel="stylesheet" href="/css/login.css">
    <title>Iniciar Sesión - SIKALMA</title>
</head>
<body>
    <div class="login-izquierda">
        <img src="/images/logo-clinica-blanco.png" alt="logo SIKALMA">
        <h2>Bienvenido a SIKALMA</h2>
        <p>Sistema de gestión de citas y atenciones del policlínico. Accede con tus credenciales para continuar.</p>
    </div>
    <div class="login-derecha">
        <div class="login">

             <c:if test="${not empty error}">
                <div style="display: flex; align-items: center; gap: 12px; background-color: #FFFFFF; color: #842029; padding: 15px 20px; border-radius: 8px; margin-bottom: 25px; border-left: 5px solid #dc3545; box-shadow: 0 2px 12px rgba(0,0,0,0.06); font-size: 0.95rem;">
                    <span style="color: #dc3545; font-size: 1.1rem; font-weight: bold;">⚠️</span>
                    <span style="font-weight: 500;">${error}</span>
                </div>
            </c:if>

            <h1>Iniciar Sesión</h1>
            <p>Ingresa tus datos para acceder al sistema</p>
            <form action="/usuario/entrar" method="post">
                <div class="campo">
                    <label>Correo</label>
                    <input type="text" name="correo" placeholder="Tu correo electrónico">
                </div>
                <div class="campo">
                    <label>Contraseña</label>
                    <input type="password" name="contrasena" placeholder="Contraseña">
                </div>
                <button type="submit" class="btn-login">Ingresar</button>
            </form>
            <a href="/paginas-estaticas/main.html" class="retorno">← Volver al inicio</a>
        </div>
    </div>
</body>
</html>

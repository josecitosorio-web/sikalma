<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/images/logo-policlinico.png">
    <title>Métricas - SIKALMA</title>
    <link rel="stylesheet" href="/css/admin.css">
    <style>
        .metricas-grid {
            display: flex;
            flex-direction: column;
            gap: 1.5em;
        }
        .metricas-fila {
            display: flex;
            gap: 1.5em;
        }
        .metrica-card {
            flex: 1;
            background-color: white;
            border: 1px solid #f0e9e8;
            border-radius: 0.8em;
            padding: 1.2em;
        }
        .metrica-card h3 {
            font-size: 0.88em;
            color: #5a4e4e;
            margin-bottom: 0.8em;
            padding-bottom: 0.6em;
            border-bottom: 1px solid #f0e9e8;
        }
        .metrica-card img {
            width: 100%;
            height: auto;
            border-radius: 0.4em;
        }
        .metrica-unica {
            max-width: calc(50% - 0.75em);
        }
    </style>
</head>
<body>

    <%@ include file="navbar.jsp" %>

    <main>
        <div class="encabezado">
            <div class="encabezado-texto">
                <h1>Métricas del Sistema</h1>
                <p>Indicadores clave de rendimiento del policlínico SIKALMA</p>
            </div>
        </div>

        <div class="metricas-grid">
            <div class="metricas-fila">
                <div class="metrica-card">
                    <h3>Citas registradas por día</h3>
                    <img src="/images/metrica-cita-registrada.png" alt="gráfico de citas registradas por día">
                </div>
                <div class="metrica-card">
                    <h3>Citas atendidas por día</h3>
                    <img src="/images/metrica-cita-atendida.png" alt="gráfico de citas atendidas por día">
                </div>
            </div>
            <div class="metricas-fila">
                <div class="metrica-card">
                    <h3>Citas canceladas por día</h3>
                    <img src="/images/metrica-cita-cancelada.png" alt="gráfico de citas canceladas por día">
                </div>
                <div class="metrica-card">
                    <h3>Tiempo promedio de espera por día</h3>
                    <img src="/images/metrica-tiempo-espera.png" alt="gráfico de tiempo promedio de espera">
                </div>
            </div>
            <div class="metricas-fila">
                <div class="metrica-card metrica-unica">
                    <h3>Ingresos por citas por día</h3>
                    <img src="/images/metrica-ingresos-dia.png" alt="gráfico de ingresos por citas">
                </div>
            </div>
        </div>
    </main>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="menu">
    <div class="menu-img">
        <img src="/images/logo-policlinico.png" alt="logo del policlinico">
    </div>
    
    <c:if test="${usuario.rol == 'ADMIN'}">

        <div class="perfil">

            <div class="perfi-contenido">

                

            </div>

        </div>

        <ul>
            <li><a href="/metricas/g-metricas" class="${paginaActiva == 'metricas' ? 'activo' : ''}">Métricas</a></li>
            <li><a href="/cita/g-citas" class="${paginaActiva == 'citas' ? 'activo' : ''}">Gestión de Citas</a></li>
            <li><a href="/cita/r-citas" class="${paginaActiva == 'r-citas' ? 'activo' : ''}">Registrar Cita</a></li>
            <li><a href="/servicio/gestion" class="${paginaActiva == 'servicios' ? 'activo' : ''}">Servicios</a></li>
            <li><a href="/paciente/gestion" class="${paginaActiva == 'paciente' ? 'activo' : ''}">Pacientes</a></li>
            <li><a href="/doctor/gestion" class="${paginaActiva == 'personal' ? 'activo' : ''}">Personal</a></li>
            <li><a href="/usuario/gestion" class="${paginaActiva == 'usuario' ? 'activo' : ''}">Usuarios</a></li>
            <li><a href="/usuario/login">Cerrar Sesión</a></li>
        </ul>

    </c:if>

    <c:if test="${usuario.rol == 'DOCTOR'}">

        <div class="perfil">

            <div class="perfi-contenido">

                

            </div>

        </div>
            
        <ul>
            <li><a href="/cita/g-citas" class="${paginaActiva == 'citas' ? 'activo' : ''}">Gestión de Citas</a></li>
            <li><a href="/atencion/gestion" class="${paginaActiva == 'atencion' ? 'activo' : ''}">Gestión de Atenciones</a></li>
            <li><a href="/usuario/login">Cerrar Sesión</a></li>
        </ul>    

    </c:if>



    

</section>
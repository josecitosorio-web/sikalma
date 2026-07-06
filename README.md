# 🏥 SIKALMA — Sistema de Gestión de Citas Médicas

Plataforma web que digitaliza la operación de un policlínico: agenda citas, controla la disponibilidad real de los doctores y cancela/reprograma automáticamente cuando hay ausencias, sin perder trazabilidad de ningún cambio.

## 📌 Resumen

SIKALMA nació como proyecto universitario y evolucionó de una versión inicial en JSP con listas en memoria a una aplicación **Spring Boot + JPA** con arquitectura por capas. El foco no fue solo hacer CRUDs, sino modelar reglas de negocio reales de una clínica: disponibilidad de doctores, estados de citas y auditoría de cada cambio. Desarrollado en equipo (grupo de 6), con roles y flujos diferenciados para administración y personal médico.

## ⚙️ Funcionalidades principales

**Administrador**
- Dashboard de métricas (citas por fecha/estado, ingresos, servicios más solicitados) con Chart.js
- Gestión completa de citas: registrar, confirmar, cancelar, marcar inasistencia
- CRUD de servicios, pacientes, doctores y usuarios del sistema
- Gestión de ausencias por doctor

**Doctor**
- Gestión de sus propias citas
- Registro de atenciones médicas (diagnóstico, tratamiento, servicio brindado)

## 🛠️ Stack tecnológico

`Java 17` · `Spring Boot` · `Spring Data JPA` · `Thymeleaf` · `H2 Database` · `Maven` · `Chart.js`

## 🏗️ Arquitectura

Arquitectura por capas aplicada de forma consistente en cada módulo (Cita, Doctor, Paciente, Servicio, Usuario, etc.):

```
Controller → Service → Repository → Entity (JPA)
                ↑
             Adapter (Entity ⇄ Model de dominio)
```

- **Model**: objeto de dominio puro, sin dependencias de JPA.
- **Entity**: mapea la tabla en base de datos.
- **Adapter**: traduce entre Entity y Model, evitando que la persistencia se filtre a los controllers.
- **Service**: concentra toda la lógica y validaciones de negocio.

Esta separación fue una decisión de diseño explícita durante la migración desde la versión inicial en JSP, para desacoplar persistencia de lógica de negocio.

## ✨ Highlights técnicos

- **Cancelación en cascada**: al registrar la ausencia de un doctor, todas sus citas `Pendiente` de esa fecha se cancelan automáticamente.
- **Reversión inteligente**: si se edita o elimina una ausencia, las citas afectadas vuelven a `Pendiente` automáticamente.
- **Auditoría completa**: cada cambio de estado, fecha, hora o doctor en una cita queda registrado en un historial (estado anterior, estado nuevo, responsable y motivo).
- **Validaciones de dominio real**: control de choques de horario, límite de ausencias mensuales por doctor, y verificación de días laborales configurados por doctor.

## 📋 Reglas de negocio clave

- Una cita puede estar en estado `Pendiente`, `Confirmado`, `Atendido`, `Cancelado` o `No asistió`.
- Un doctor solo puede reportar ausencia en sus días laborales, con fecha futura, sin duplicar fecha y con un **máximo de 3 ausencias por mes**.
- Al cancelarse citas por ausencia (o al revertirse), el sistema registra automáticamente el motivo en el historial (`INDISPONIBILIDAD DEL DOCTOR`, `CAMBIO DE ESTADO`, etc.).
- No se permite doble reserva de un mismo doctor en la misma fecha/hora.

## 🚀 Cómo ejecutar

Requiere JDK 17+.

```bash
git clone https://github.com/josecitosorioweb/sikalma-grupo6.git
cd sikalma-grupo6
./mvnw spring-boot:run
```

App disponible en `http://localhost:8080`. Usa H2 en memoria con datos precargados, no requiere configuración adicional.

**Credenciales de prueba:**

| Rol    | Correo             | Contraseña |
|--------|--------------------|------------|
| ADMIN  | admin@gmail.com    | 123        |
| DOCTOR | cmendoza@gmail.com | 123        |

## 👤 Mi contribución

Proyecto desarrollado en equipo (6 integrantes). Mis compañeros se encargaron de la creación inicial de las clases base (entidades), mientras que yo lideré la mayor parte del desarrollo funcional:

- Diseñé e implementé la **capa de servicios** (lógica de negocio y validaciones) de los distintos módulos del sistema.
- Diseñé e implementé el módulo de **ausencias de doctores**: validaciones, cancelación automática de citas afectadas, reversión al editar/eliminar y registro en historial.
- Lideré la **migración de la capa de persistencia**, de listas en memoria (JSP/JSTL) a Spring Data JPA + Hibernate.
- Implementé la **integración con Chart.js** para el dashboard de métricas.
- Responsable de gran parte de las **correcciones de errores** y ajustes finales del sistema.

## 🔭 Mejoras futuras

- Reemplazar la validación manual de contraseñas por Spring Security + BCrypt
- Sumar pruebas unitarias e integración
- Migrar de H2 a una base de datos persistente (PostgreSQL/MySQL)
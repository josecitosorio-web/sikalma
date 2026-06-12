-- =====================================================
-- SERVICIOS
-- =====================================================

INSERT INTO servicio(nombre_servi, descripcion_servi, costo_servi, estado_servi) VALUES
('Medicina General','Consulta medica integral',60.00,true),
('Pediatria','Atencion médica infantil',70.00,true),
('Ginecologia','Salud integral de la mujer',90.00,true),
('Odontologia','Prevencion y tratamiento dental',80.00,true),
('Traumatologia','Lesiones musculares y oseas',110.00,true),
('Cardiologia','Diagnostico cardiovascular',120.00,true),
('Psicologia','Salud mental y emocional',75.00,true),
('Laboratorio Clinico','Analisis clinicos y exámenes',40.00,true);

-- =====================================================
-- DOCTORES
-- =====================================================

INSERT INTO doctor
(nombre_doc,dni_doc,telefono_doc,correo_doc,fecha_doc,
hora_atencion_inicio,hora_atencion_fin,estado_doc,servicio_id)
VALUES
('Carlos Mendoza','45123678','987654321','cmendoza@sikalma.com','1980-03-15','08:00:00','17:00:00',true,1),

('Ana Torres','32456789','912345678','atorres@sikalma.com','1985-07-22','09:00:00','18:00:00',true,2),

('Jorge Paredes','56789012','923456789','jparedes@sikalma.com','1978-11-08','08:00:00','17:00:00',true,3),

('Lucia Ramos','67890123','934567890','lramos@sikalma.com','1986-02-20','08:00:00','18:00:00',true,4),

('Roberto Salazar','78901234','945678901','rsalazar@sikalma.com','1977-04-14','08:00:00','16:00:00',true,5),

('Maria Gutierrez','89012345','956789012','mgutierrez@sikalma.com','1981-05-30','09:00:00','17:00:00',true,6),

('Paola Castillo','90123456','967890123','pcastillo@sikalma.com','1990-01-25','10:00:00','18:00:00',true,7),

('Diego Flores','91234567','978901234','dflores@sikalma.com','1983-06-03','07:00:00','15:00:00',true,8);

-- =====================================================
-- DIAS LABORALES
-- =====================================================

INSERT INTO doctor_dia(dia_semana,doctor_id) VALUES
(1,1),(2,1),

(1,2),(2,2),(3,2),(4,2),

(2,3),(3,3),(4,3),

(1,4),(2,4),(3,4),(4,4),(5,4),

(1,5),(3,5),(5,5),

(1,6),(2,6),(3,6),(4,6),

(2,7),

(1,8),(2,8);

-- =====================================================
-- PACIENTES
-- =====================================================

INSERT INTO paciente
(nombre_pac,tipo_documento,numero_documento,telefono_pac,fecha_pac)
VALUES
('Luis Quispe','DNI','74123456','987111111','1995-04-10'),
('Carmen Flores','DNI','63245678','987222222','1982-08-23'),
('Pedro Huanca','DNI','52367890','987333333','1970-12-05'),
('Sofia Mamani','DNI','85412369','987444444','2000-02-14'),
('Andres Chavez','DNI','96523470','987555555','1998-06-30'),
('Valeria Rios','DNI','47634581','987666666','1990-09-17'),
('Miguel Condori','DNI','38745692','987777777','1965-03-28'),
('Daniela Zuniga','DNI','29856703','987888888','2003-11-11');

-- =====================================================
-- USUARIOS
-- =====================================================

INSERT INTO usuario(correo_user,contrasena_user,rol_user,doctor_id)
VALUES
('admin@gmail.com','123','ADMIN',NULL),
('cmendoza@gmail.com','123','DOCTOR',1),
('atorres@gmail.com','123','DOCTOR',2),
('jparedes@gmail.com','123','DOCTOR',3),
('lramos@gmail.com','123','DOCTOR',4);

-- =====================================================
-- CITAS
-- =====================================================

INSERT INTO cita
(paciente_id,doctor_id,fecha_cita,hora_cita,estado_cita)
VALUES

(1,1,'2026-06-15','09:00:00','Pendiente'),
(2,2,'2026-06-15','10:00:00','Pendiente'),
(3,3,'2026-06-16','08:30:00','Pendiente'),

(4,4,'2026-06-16','11:00:00','Confirmado'),
(5,5,'2026-06-17','09:00:00','Confirmado'),

(6,6,'2026-06-10','10:00:00','Atendido'),
(7,7,'2026-06-10','11:00:00','Atendido'),
(8,1,'2026-06-11','08:00:00','Atendido'),

(1,2,'2026-06-11','10:00:00','Cancelado'),
(2,3,'2026-06-11','09:00:00','No asistio'),

(3,4,'2026-06-18','10:00:00','Pendiente'),
(4,5,'2026-06-18','09:00:00','Pendiente'),
(5,6,'2026-06-18','11:00:00','Pendiente'),
(6,7,'2026-06-19','10:00:00','Pendiente'),
(7,8,'2026-06-19','08:00:00','Pendiente'),

(8,2,'2026-06-20','09:00:00','Confirmado'),
(1,3,'2026-06-20','10:00:00','Confirmado'),
(2,4,'2026-06-20','11:00:00','Confirmado'),
(3,5,'2026-06-21','09:00:00','Confirmado'),
(4,6,'2026-06-21','10:00:00','Confirmado'),

(5,7,'2026-06-08','11:00:00','Atendido'),
(6,8,'2026-06-08','09:00:00','Atendido'),
(7,2,'2026-06-09','10:00:00','Atendido'),
(8,3,'2026-06-09','11:00:00','Atendido'),

(1,4,'2026-06-07','08:00:00','Cancelado'),
(2,5,'2026-06-07','09:00:00','Cancelado'),

(3,6,'2026-06-06','10:00:00','No asistio'),
(4,7,'2026-06-06','11:00:00','No asistio');
-- =====================================================
-- ATENCIONES
-- =====================================================

INSERT INTO atencion
(cita_id,hr_inicio,hr_fin,diagnostico,tratamiento,estado)
VALUES


(6,'10:00:00','11:00:00',
'Gripe estacional',
'Paracetamol y reposo',
'Completado'),

(7,'11:00:00','12:00:00',
'Estres academico',
'Terapia breve y seguimiento',
'Completado'),

(8,'08:00:00','09:00:00',
'Control general',
'Observacion y recomendaciones',
'Completado'),

(21,'11:00:00','12:00:00',
'Migraña recurrente',
'Analgesicos y control neurologico',
'Completado'),

(22,'09:00:00','10:00:00',
'Ansiedad leve',
'Sesion psicologica y seguimiento',
'Completado'),

(23,'10:00:00','11:00:00',
'Control pediatrico anual',
'Vitaminas y recomendaciones nutricionales',
'Completado'),

(24,'11:00:00','12:00:00',
'Chequeo ginecologico preventivo',
'Examen de rutina y control anual',
'Completado');

-- =====================================================
-- HISTORIAL DE CITAS
-- =====================================================

INSERT INTO Historial_Cita
(
cita_id,
fecha_anterior,
fecha_nueva,
hora_anterior,
hora_nueva,
doctor_anterior,
doctor_nuevo,
estado_anterior,
estado_nuevo,
fecha_registro,
usuario_responsable,
tipo_cambio
)
VALUES

-- CITA 4
(
4,
'2026-06-16',
'2026-06-16',
'10:00:00',
'11:00:00',
'Lucia Ramos',
'Lucia Ramos',
'Pendiente',
'Confirmado',
'2026-06-14T09:15:00',
'admin@gmail.com',
'CAMBIO_ESTADO'
),

-- CITA 5
(
5,
'2026-06-17',
'2026-06-17',
'08:00:00',
'09:00:00',
'Roberto Salazar',
'Roberto Salazar',
'Pendiente',
'Confirmado',
'2026-06-15T08:30:00',
'admin@gmail.com',
'CAMBIO_ESTADO'
),

-- CITA 6
(
6,
'2026-06-10',
'2026-06-10',
'10:00:00',
'10:00:00',
'Maria Gutierrez',
'Maria Gutierrez',
'Confirmado',
'Atendido',
'2026-06-10T10:35:00',
'mgutierrez@sikalma.com',
'ATENCION_COMPLETADA'
),

-- CITA 7
(
7,
'2026-06-10',
'2026-06-10',
'11:00:00',
'11:00:00',
'Paola Castillo',
'Paola Castillo',
'Confirmado',
'Atendido',
'2026-06-10T11:50:00',
'pcastillo@sikalma.com',
'ATENCION_COMPLETADA'
),

-- CITA 8
(
8,
'2026-06-11',
'2026-06-11',
'08:00:00',
'08:00:00',
'Carlos Mendoza',
'Carlos Mendoza',
'Confirmado',
'Atendido',
'2026-06-11T08:45:00',
'cmendoza@gmail.com',
'ATENCION_COMPLETADA'
),

-- CITA 9
(
9,
'2026-06-11',
'2026-06-11',
'10:00:00',
'10:00:00',
'Ana Torres',
'Ana Torres',
'Pendiente',
'Cancelado',
'2026-06-11T09:00:00',
'admin@gmail.com',
'CANCELACION'
),

-- CITA 10
(
10,
'2026-06-11',
'2026-06-11',
'09:00:00',
'09:00:00',
'Jorge Paredes',
'Jorge Paredes',
'Confirmado',
'No asistio',
'2026-06-11T09:45:00',
'admin@gmail.com',
'NO_ASISTENCIA'
),

-- CITA 16
(
16,
'2026-06-20',
'2026-06-20',
'08:00:00',
'09:00:00',
'Ana Torres',
'Ana Torres',
'Pendiente',
'Confirmado',
'2026-06-18T11:00:00',
'admin@gmail.com',
'CAMBIO_ESTADO'
),

-- CITA 17
(
17,
'2026-06-20',
'2026-06-20',
'09:00:00',
'10:00:00',
'Jorge Paredes',
'Jorge Paredes',
'Pendiente',
'Confirmado',
'2026-06-18T12:10:00',
'admin@gmail.com',
'CAMBIO_ESTADO'
),

-- CITA 18
(
18,
'2026-06-20',
'2026-06-20',
'10:00:00',
'11:00:00',
'Lucia Ramos',
'Lucia Ramos',
'Pendiente',
'Confirmado',
'2026-06-18T13:20:00',
'admin@gmail.com',
'CAMBIO_ESTADO'
),

-- CITA 25
(
25,
'2026-06-07',
'2026-06-07',
'08:00:00',
'08:00:00',
'Lucia Ramos',
'Lucia Ramos',
'Pendiente',
'Cancelado',
'2026-06-06T18:00:00',
'admin@gmail.com',
'CANCELACION'
),

-- CITA 26
(
26,
'2026-06-07',
'2026-06-07',
'09:00:00',
'09:00:00',
'Roberto Salazar',
'Roberto Salazar',
'Pendiente',
'Cancelado',
'2026-06-06T18:15:00',
'admin@gmail.com',
'CANCELACION'
),

-- CITA 27
(
27,
'2026-06-06',
'2026-06-06',
'10:00:00',
'10:00:00',
'Maria Gutierrez',
'Maria Gutierrez',
'Confirmado',
'No asistio',
'2026-06-06T10:30:00',
'admin@gmail.com',
'NO_ASISTENCIA'
),

-- CITA 28
(
28,
'2026-06-06',
'2026-06-06',
'11:00:00',
'11:00:00',
'Paola Castillo',
'Paola Castillo',
'Confirmado',
'No asistio',
'2026-06-06T11:30:00',
'admin@gmail.com',
'NO_ASISTENCIA'
);
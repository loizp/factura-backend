-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--- INSERCIÓN DE LOS DATOS DE SEGURIDAD PREDETERMINADOS
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--- Tabla: roles
-------------------------------------------------------------------------------
INSERT INTO roles (role_name)
    VALUES ('USER'),	--- USER: Rol por defecto sin permisos
    	('ROOT'),		--- ROOT: Rol de superusuario con todos los permisos
    	('APICLIENT');	--- APICLIENT: Rol con permisos limitados al microservicio de gestión de documentos
-------------------------------------------------------------------------------
--- Tabla: usuarios
--- Clave: 20f@qture$UNAT18
-------------------------------------------------------------------------------
INSERT INTO usuarios (user_login_name, user_password, user_nombre, user_email, user_status)
    VALUES ('rootfaqture', '$2a$10$XAqYh40D9anIqSbdTgpgBeDY6OD3bcGTjHkGZapq6wAb9k8cHaGDu', 'Usuario ROOT del Servicio', 'admin@faqture.com',true);
-------------------------------------------------------------------------------
--- Tabla: asignaciones_roles'
-------------------------------------------------------------------------------
INSERT INTO asignaciones_roles (user_id, role_id)
    VALUES ('1', '2');
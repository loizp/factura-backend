-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--- CREACIÓN DE TRIGGER Y PROCEDIMIENTOS ALMACENADOS PREDETERMINADOS
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--- Tabla: empresas
--- procedures: cambia status segun el estado las sucursales
-------------------------------------------------------------------------------
-- CREATE OR REPLACE FUNCTION update_status_empresa() RETURNS TRIGGER AS $check_update_status$
--     BEGIN
--         IF (NEW.sucu_status = false) THEN
--             IF ((SELECT count(*) FROM sunqubit.sucursales WHERE sucu_status = false AND empr_id = OLD.empr_id) = (SELECT count(*) FROM sunqubit.sucursales WHERE empr_id = OLD.empr_id)) THEN
--                 UPDATE sunqubit.empresas SET empr_status = false WHERE empr_id = OLD.empr_id AND empr_status = true;
-- 			END IF;
-- 		ELSIF ((SELECT count(*) FROM sunqubit.sucursales WHERE sucu_status = false AND empr_id = OLD.empr_id) != (SELECT count(*) FROM sunqubit.sucursales WHERE empr_id = OLD.empr_id)) THEN
--         	UPDATE sunqubit.empresas SET empr_status = true WHERE empr_id = OLD.empr_id AND empr_status = false;
-- 		END IF;
--         RETURN NULL;
--     END;
-- $check_update_status$ LANGUAGE plpgsql;
-------------------------------------------------------------------------------
--- Tabla: sucursales
--- Trigger: update en status
-------------------------------------------------------------------------------
-- CREATE TRIGGER check_update_status
--     AFTER UPDATE OF sucu_status ON sunqubit.sucursales
--     FOR EACH ROW
--     EXECUTE PROCEDURE update_status_empresa();
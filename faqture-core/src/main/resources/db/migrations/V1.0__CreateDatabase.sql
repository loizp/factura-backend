----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--- CREANDO LA ESTRUCTURA DE LA BASE DE DATOS
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE sunqubit.tipos_leyendas (
                tley_codigo CHAR(4) NOT NULL,
                tley_descripcion VARCHAR(200) NOT NULL,
                CONSTRAINT tipos_leyendas_pk PRIMARY KEY (tley_codigo)
);
COMMENT ON TABLE sunqubit.tipos_leyendas IS 'Tabla segun el catalogo 52';
COMMENT ON COLUMN sunqubit.tipos_leyendas.tley_codigo IS 'Campo PK con el codigo de la leyenda a usar segun el catalogo';
COMMENT ON COLUMN sunqubit.tipos_leyendas.tley_descripcion IS 'Campo con la descripcion del codigo de leyenda';


CREATE TABLE sunqubit.tipos_tributos (
                ttri_codigo CHAR(4) NOT NULL,
                ttri_descripcion VARCHAR(150) NOT NULL,
                ttri_descripcion_corta VARCHAR(20) NOT NULL,
                ttri_categoria CHAR(1) NOT NULL,
                CONSTRAINT tipos_tributos_pk PRIMARY KEY (ttri_codigo)
);
COMMENT ON TABLE sunqubit.tipos_tributos IS 'Tabla que contiene el catalogo 05 de la sunat';
COMMENT ON COLUMN sunqubit.tipos_tributos.ttri_codigo IS 'Campo PK de la tabla con los codigos segun tabla del catalogo 05';
COMMENT ON COLUMN sunqubit.tipos_tributos.ttri_descripcion IS 'Campo descripcion del catalogo';
COMMENT ON COLUMN sunqubit.tipos_tributos.ttri_descripcion_corta IS 'Campo con la descripcion corta del tipo de tributo';
COMMENT ON COLUMN sunqubit.tipos_tributos.ttri_categoria IS 'campo que indica la categoria del tipo de tributo';


CREATE SEQUENCE sunqubit.roles_role_id_seq;

CREATE TABLE sunqubit.roles (
                role_id INTEGER NOT NULL DEFAULT nextval('sunqubit.roles_role_id_seq'),
                role_name VARCHAR(20) NOT NULL,
                CONSTRAINT roles_pk PRIMARY KEY (role_id)
);
COMMENT ON TABLE sunqubit.roles IS 'Tabla que tiene los roles de accesos de los usuarios';
COMMENT ON COLUMN sunqubit.roles.role_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.roles.role_name IS 'Campo con la descripcion del ROL';


ALTER SEQUENCE sunqubit.roles_role_id_seq OWNED BY sunqubit.roles.role_id;

CREATE SEQUENCE sunqubit.usuarios_user_id_seq;

CREATE TABLE sunqubit.usuarios (
                user_id INTEGER NOT NULL DEFAULT nextval('sunqubit.usuarios_user_id_seq'),
                user_login_name VARCHAR(20) NOT NULL,
                user_password VARCHAR(255) NOT NULL,
                user_nombre VARCHAR(200) NOT NULL,
                user_email VARCHAR(200) NOT NULL,
                user_status BOOLEAN DEFAULT True NOT NULL,
                CONSTRAINT usuarios_pk PRIMARY KEY (user_id)
);
COMMENT ON TABLE sunqubit.usuarios IS 'Tabla que contendra los datos de los usuarios que se autenticaran para el uso del servicio';
COMMENT ON COLUMN sunqubit.usuarios.user_id IS 'Campo PK de la tabla usuario';
COMMENT ON COLUMN sunqubit.usuarios.user_login_name IS 'Campo de identificación principal de usuario';
COMMENT ON COLUMN sunqubit.usuarios.user_password IS 'campo que almacena las contraseñas de los usuarios';
COMMENT ON COLUMN sunqubit.usuarios.user_nombre IS 'Campo con el nombre a mostrarse del usuario';
COMMENT ON COLUMN sunqubit.usuarios.user_email IS 'Campo con el email del usuario para contacto';
COMMENT ON COLUMN sunqubit.usuarios.user_status IS 'Campo que valida el estado del usuario activo(True) o inactivo(False)';


ALTER SEQUENCE sunqubit.usuarios_user_id_seq OWNED BY sunqubit.usuarios.user_id;

CREATE UNIQUE INDEX usuarios_idx
 ON sunqubit.usuarios
 ( user_login_name );

CREATE SEQUENCE sunqubit.asignaciones_roles_arol_id_seq;

CREATE TABLE sunqubit.asignaciones_roles (
                arol_id INTEGER NOT NULL DEFAULT nextval('sunqubit.asignaciones_roles_arol_id_seq'),
                role_id INTEGER NOT NULL,
                user_id INTEGER NOT NULL,
                CONSTRAINT asignaciones_roles_pk PRIMARY KEY (arol_id)
);
COMMENT ON TABLE sunqubit.asignaciones_roles IS 'Tabla detalle Muchos a muchos entre usuarios y roles';
COMMENT ON COLUMN sunqubit.asignaciones_roles.arol_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.asignaciones_roles.role_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.asignaciones_roles.user_id IS 'Campo PK de la tabla usuario';


ALTER SEQUENCE sunqubit.asignaciones_roles_arol_id_seq OWNED BY sunqubit.asignaciones_roles.arol_id;

CREATE TABLE sunqubit.paises (
                pais_codigo CHAR(2) NOT NULL,
                pais_nombre VARCHAR(50) NOT NULL,
                CONSTRAINT paises_pk PRIMARY KEY (pais_codigo)
);
COMMENT ON TABLE sunqubit.paises IS 'Tabla de paises segun el catálogo';
COMMENT ON COLUMN sunqubit.paises.pais_codigo IS 'Campo PK con el código de pais estipulado en el catálogo';
COMMENT ON COLUMN sunqubit.paises.pais_nombre IS 'Campo con el nombre del pais';


CREATE TABLE sunqubit.tipos_isc (
                tisc_codigo CHAR(2) NOT NULL,
                tisc_descripcion VARCHAR(200) NOT NULL,
                CONSTRAINT tipos_isc_pk PRIMARY KEY (tisc_codigo)
);
COMMENT ON TABLE sunqubit.tipos_isc IS '01
Sistema al valor (Apéndice IV, lit. A – T.U.O IGV e ISC)
02
Aplicación del Monto Fijo (Apéndice IV, lit. B – T.U.O IGV e ISC)
03
Sistema de Precios de Venta al Público (Apéndice IV, lit. C – T.U.O IGV e ISC)';
COMMENT ON COLUMN sunqubit.tipos_isc.tisc_codigo IS 'Campo PK código del impuesto ISC';
COMMENT ON COLUMN sunqubit.tipos_isc.tisc_descripcion IS 'Campo con la descripción del tipo de impuesto';


CREATE TABLE sunqubit.tipos_afectacion_igv (
                tiai_codigo CHAR(2) NOT NULL,
                tiai_descripcion VARCHAR(100) NOT NULL,
                CONSTRAINT tipos_afectacion_igv_pk PRIMARY KEY (tiai_codigo)
);
COMMENT ON TABLE sunqubit.tipos_afectacion_igv IS 'Catálogo No. 07 Códigos de Tipo de Afectación del IGV
============================
10 Gravado - Operación Onerosa
11 Gravado – Retiro por premio
12 Gravado – Retiro por donación
13 Gravado – Retiro
14 Gravado – Retiro por publicidad
15 Gravado – Bonificaciones
16 Gravado – Retiro por entrega a trabajadores
17 Gravado – IVAP
20 Exonerado - Operación Onerosa
21 Exonerado – Transferencia Gratuita
30 Inafecto - Operación Onerosa
31 Inafecto – Retiro por Bonificación
32 Inafecto – Retiro
33 Inafecto – Retiro por Muestras Médicas
34 Inafecto - Retiro por Convenio Colectivo
35 Inafecto – Retiro por premio
36 Inafecto - Retiro por publicidad
40 Exportación';
COMMENT ON COLUMN sunqubit.tipos_afectacion_igv.tiai_codigo IS 'Campo PK estipulado segun el catálogo Nro 07';
COMMENT ON COLUMN sunqubit.tipos_afectacion_igv.tiai_descripcion IS 'Campo que describe el tipo de afectación del impuesto estipulado en el catálogo Nro 07';


CREATE TABLE sunqubit.unidades_medida (
                unme_codigo VARCHAR(5) NOT NULL,
                unme_descripcion VARCHAR(100) NOT NULL,
                CONSTRAINT unidades_medida_pk PRIMARY KEY (unme_codigo)
);
COMMENT ON TABLE sunqubit.unidades_medida IS 'NIU Unidades
LIT litros
ZZ Servicios';
COMMENT ON COLUMN sunqubit.unidades_medida.unme_codigo IS 'Campo PK con el valor único de identificación de la Unidad de medidad';
COMMENT ON COLUMN sunqubit.unidades_medida.unme_descripcion IS 'Campo con el nombre de la unidad de medidad';


CREATE TABLE sunqubit.tipos_operaciones (
                tiop_codigo CHAR(2) NOT NULL,
                tiop_descripcion VARCHAR(50) NOT NULL,
                CONSTRAINT tipos_operaciones_pk PRIMARY KEY (tiop_codigo)
);
COMMENT ON TABLE sunqubit.tipos_operaciones IS 'Catálogo No. 17: Códigos – Tipo de Operación';
COMMENT ON COLUMN sunqubit.tipos_operaciones.tiop_codigo IS 'Campo PK según el catálogo Nro 17';
COMMENT ON COLUMN sunqubit.tipos_operaciones.tiop_descripcion IS 'Campo con la descripción según el catálogo Nro 17';


CREATE TABLE sunqubit.monedas (
                mone_id VARCHAR(3) NOT NULL,
                mone_descripcion VARCHAR(100) NOT NULL,
                CONSTRAINT monedas_pk PRIMARY KEY (mone_id)
);
COMMENT ON TABLE sunqubit.monedas IS 'Catálogo No. 02: Códigos de Tipo de Monedas.
Moneda del documento, 
PEN - Soles
USD - Dollar';
COMMENT ON COLUMN sunqubit.monedas.mone_id IS 'Campo PK según el catálogo Nro 02';
COMMENT ON COLUMN sunqubit.monedas.mone_descripcion IS '1	NUEVOS SOLES
2	DÓLARES AMERICANOS
9	OTRA MONEDA (ESPECIFICAR)';


CREATE TABLE sunqubit.tipos_documentos_identidad (
                tiid_codigo CHAR(1) NOT NULL,
                tiid_descripcion VARCHAR(100) NOT NULL,
                tiid_descripcion_corta VARCHAR(20),
                CONSTRAINT tipos_documentos_identidad_pk PRIMARY KEY (tiid_codigo)
);
COMMENT ON TABLE sunqubit.tipos_documentos_identidad IS 'Catálogo No. 06: Códigos de Tipos de Documentos de Identidad
===========================
0 DOC.TRIB.NO.DOM.SIN.RUC
1 DOC. NACIONAL DE IDENTIDAD
4 CARNET DE EXTRANJERIA
6 REG. UNICO DE CONTRIBUYENTES
7 PASAPORTE
A CED. DIPLOMATICA DE IDENTIDAD';
COMMENT ON COLUMN sunqubit.tipos_documentos_identidad.tiid_codigo IS 'Campo PK segun tabla del catálogo Nro 06';
COMMENT ON COLUMN sunqubit.tipos_documentos_identidad.tiid_descripcion IS 'Campo que describe el Tipo de documento segun tabla del catálogo Nro 06';
COMMENT ON COLUMN sunqubit.tipos_documentos_identidad.tiid_descripcion_corta IS 'Campo que contiene la descripcion corta de tipo de documento de identificación';


CREATE SEQUENCE sunqubit.contribuyentes_cont_id_seq_1_1;

CREATE TABLE sunqubit.contribuyentes (
                cont_id INTEGER NOT NULL DEFAULT nextval('sunqubit.contribuyentes_cont_id_seq_1_1'),
                tiid_codigo CHAR(1) NOT NULL,
                cont_num_doc VARCHAR(15),
                cont_nombre_legal VARCHAR(200) NOT NULL,
                cont_nombre_comercial VARCHAR(200),
                cont_direccion VARCHAR(200),
                cont_status BOOLEAN DEFAULT true NOT NULL,
                cont_urbanizacion VARCHAR(200),
                ubig_id INTEGER,
                pais_codigo CHAR(2) DEFAULT 'PE' NOT NULL,
                CONSTRAINT contribuyentes_pk PRIMARY KEY (cont_id)
);
COMMENT ON TABLE sunqubit.contribuyentes IS 'Tabla que contiene todos los contribuyentes admitidos por la SUNAT';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_id IS 'Campo PK del contribuyente';
COMMENT ON COLUMN sunqubit.contribuyentes.tiid_codigo IS 'Campo PK segun tabla del catálogo Nro 06';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_num_doc IS 'Campo que contiene el número de documento';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_nombre_legal IS 'Campo que tendra el nombre completo en caso de ser una persona natural o la razon social en caso de ser una persona jurídica';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_nombre_comercial IS 'Campo que contiene el nomb re comercial en caso de tenerlo';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_direccion IS 'Campo que contiene la dirección del contribuyente en caso de contar con ello';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_status IS 'Campo que permite la verificación del estado de actividad de emision en nuestro sistema activo(true) o inactivo(false)';
COMMENT ON COLUMN sunqubit.contribuyentes.cont_urbanizacion IS 'Campo que contiene en caso de contra con la ubicacion de urbanización del contribuyente';
COMMENT ON COLUMN sunqubit.contribuyentes.ubig_id IS 'Campo clave con el id de la ubicacion geografica del contribuyente en caso de tenerlo';
COMMENT ON COLUMN sunqubit.contribuyentes.pais_codigo IS 'Campo clave referencial del pais al que pertenece el contribuyente';


ALTER SEQUENCE sunqubit.contribuyentes_cont_id_seq_1_1 OWNED BY sunqubit.contribuyentes.cont_id;

CREATE SEQUENCE sunqubit.sucursales_sucu_id_seq_1;

CREATE TABLE sunqubit.sucursales (
                sucu_id INTEGER NOT NULL DEFAULT nextval('sunqubit.sucursales_sucu_id_seq_1'),
                sucu_direccion VARCHAR(200) NOT NULL,
                sucu_status BOOLEAN DEFAULT true NOT NULL,
                cont_id INTEGER NOT NULL,
                ubig_id INTEGER,
                pais_codigo CHAR(2) DEFAULT 'PE' NOT NULL,
                CONSTRAINT sucursales_pk PRIMARY KEY (sucu_id)
);
COMMENT ON TABLE sunqubit.sucursales IS 'Contiene las sucursales de las empresas emisoras de documentos';
COMMENT ON COLUMN sunqubit.sucursales.sucu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.sucursales.sucu_direccion IS 'Campo con la dirección de la sucursal';
COMMENT ON COLUMN sunqubit.sucursales.sucu_status IS 'Campo que define si tendra actividad(True) o no(False) la sucursal';
COMMENT ON COLUMN sunqubit.sucursales.cont_id IS 'Campo PK del contribuyente';
COMMENT ON COLUMN sunqubit.sucursales.ubig_id IS 'Campo clave referencial de la ubicacion de la sucursal';
COMMENT ON COLUMN sunqubit.sucursales.pais_codigo IS 'Campo con el codigo de pais de la sucursal por defecto es PERU';


ALTER SEQUENCE sunqubit.sucursales_sucu_id_seq_1 OWNED BY sunqubit.sucursales.sucu_id;

CREATE SEQUENCE sunqubit.ubigeos_ubig_id_seq;

CREATE TABLE sunqubit.ubigeos (
                ubig_id INTEGER NOT NULL DEFAULT nextval('sunqubit.ubigeos_ubig_id_seq'),
                ubig_cod_dpto CHAR(2) NOT NULL,
                ubig_cod_prov CHAR(2) NOT NULL,
                ubig_cod_dist CHAR(2) NOT NULL,
                ubig_descripcion VARCHAR(200) NOT NULL,
                CONSTRAINT ubigeos_pk PRIMARY KEY (ubig_id)
);
COMMENT ON TABLE sunqubit.ubigeos IS 'Tabla que contiene la codificación de ubicación geográfica emitida por el estado';
COMMENT ON COLUMN sunqubit.ubigeos.ubig_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.ubigeos.ubig_cod_dpto IS 'Campo código de ubicación del departamento';
COMMENT ON COLUMN sunqubit.ubigeos.ubig_cod_prov IS 'Campo código de ubicación de la provincia';
COMMENT ON COLUMN sunqubit.ubigeos.ubig_cod_dist IS 'Campo código de ubicación del distrito';
COMMENT ON COLUMN sunqubit.ubigeos.ubig_descripcion IS 'Campo con el nombre de la ubicación geográfica';


ALTER SEQUENCE sunqubit.ubigeos_ubig_id_seq OWNED BY sunqubit.ubigeos.ubig_id;

CREATE TABLE sunqubit.tipos_documentos (
                tido_codigo CHAR(2) NOT NULL,
                tido_descripcion VARCHAR(250) NOT NULL,
                CONSTRAINT tipso_documentos_pk PRIMARY KEY (tido_codigo)
);
COMMENT ON TABLE sunqubit.tipos_documentos IS 'CATALOGO No. 01
==============================
01 FACTURA
03 BOLETA DE VENTA
07 NOTA DE CREDITO
08 NOTA DE DEBITO
09 GUIA DE REMISIÓN REMITENTE
12 TICKET DE MAQUINA REGISTRADORA
13 DOCUMENTO EMITIDO POR BANCOS, INSTITUCIONES FINANCIERAS, CREDITICIAS Y DE SEGUROS QUE SE ENCUENTREN BAJO EL CONTROL DE LA SUPERINTENDENCIA DE BANCA Y SEGUROS
18 DOCUMENTOS EMITIDOS POR LAS AFP
31 GUIA DE REMISIÓN TRANSPORTISTA
56 COMPROBANTE DE PAGO SEAE';
COMMENT ON COLUMN sunqubit.tipos_documentos.tido_codigo IS 'Campo PK que identifica el tipo de documento segun el catálogo 01';
COMMENT ON COLUMN sunqubit.tipos_documentos.tido_descripcion IS 'Campo con la Descripción del tipo de Documento según el catálogo Nro 01';


CREATE SEQUENCE sunqubit.tipos_notas_tino_id_seq;

CREATE TABLE sunqubit.tipos_notas (
                tino_id INTEGER NOT NULL DEFAULT nextval('sunqubit.tipos_notas_tino_id_seq'),
                tino_codigo CHAR(2) NOT NULL,
                tino_descripcion VARCHAR(200) NOT NULL,
                tido_codigo CHAR(2) NOT NULL,
                CONSTRAINT tipos_notas_pk PRIMARY KEY (tino_id)
);
COMMENT ON TABLE sunqubit.tipos_notas IS 'Tabla que contiene los tipos de notas tanto de Crédito o de Débito.

Credito:
01 Anulación de la operación
02 Anulación por error en el RUC
03 Corrección por error en la descripción
04 Descuento global
05 Descuento por ítem
06 Devolución total
07 Devolución por ítem
08 Bonificación
09 Disminución en el valor
10 Otros Conceptos

Debito:
01 Intereses por mora
02 Aumento en el valor
03 Penalidades/ otros conceptos';
COMMENT ON COLUMN sunqubit.tipos_notas.tino_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.tipos_notas.tino_codigo IS 'Campo codigo segun su catalogo';
COMMENT ON COLUMN sunqubit.tipos_notas.tino_descripcion IS 'campo con la descripcion del tipo de nota';
COMMENT ON COLUMN sunqubit.tipos_notas.tido_codigo IS 'Campo PK que identifica el tipo de documento segun el catálogo 01';


ALTER SEQUENCE sunqubit.tipos_notas_tino_id_seq OWNED BY sunqubit.tipos_notas.tino_id;

CREATE SEQUENCE sunqubit.documentos_docu_id_seq;

CREATE TABLE sunqubit.documentos (
                docu_id INTEGER NOT NULL DEFAULT nextval('sunqubit.documentos_docu_id_seq'),
                cont_id INTEGER NOT NULL,
                sucu_emisor_id INTEGER,
                cont_clie_id INTEGER,
                sucu_clie_id INTEGER,
                tiop_codigo CHAR(2),
                docu_fecha_emision TIMESTAMP NOT NULL,
                docu_fecha_vencimiento TIMESTAMP NOT NULL,
                docu_numero VARCHAR(20) NOT NULL,
                tido_codigo CHAR(2) NOT NULL,
                tino_id INTEGER,
                mone_id VARCHAR(3) NOT NULL,
                tley_codigo CHAR(4),
                docu_leyenda VARCHAR(250),
                docu_observacion VARCHAR(250),
                docu_subtotal NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_grabada NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_inafecta NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_exonerada NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_gratuita NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_descuento NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_igv NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_isc NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_otros_tributos NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_otros_cargos NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_total VARCHAR DEFAULT 0.00 NOT NULL,
                docu_vendedor VARCHAR(200),
                docu_clie_email VARCHAR(200),
                docu_sustento_nota VARCHAR(250),
                docu_proc_fecha TIMESTAMP NOT NULL,
                docu_proc_status CHAR(1) DEFAULT 'N' NOT NULL,
                docu_anulado BOOLEAN DEFAULT false NOT NULL,
                docu_enviar_sunat BOOLEAN DEFAULT True NOT NULL,
                docu_link_pdf VARCHAR(200),
                docu_link_xml VARCHAR(200),
                docu_hash_sunat VARCHAR(200),
                docu_link_cdr VARCHAR(200),
                docu_cdr_status VARCHAR(200),
                docu_cdr_nota VARCHAR(200),
                docu_cdr_observacion VARCHAR,
                CONSTRAINT documentos_pk PRIMARY KEY (docu_id)
);
COMMENT ON TABLE sunqubit.documentos IS 'Tabla principal del sistema que contiene los documentos que son captado y enviados a las SUNAT';
COMMENT ON COLUMN sunqubit.documentos.docu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos.cont_id IS 'Campo PK del contribuyente';
COMMENT ON COLUMN sunqubit.documentos.sucu_emisor_id IS 'Campo clave referencia de sucursal en caso de tener sucursal';
COMMENT ON COLUMN sunqubit.documentos.cont_clie_id IS 'Campo Clave referencial con el id del cliente en caso que el documento indique el cliente';
COMMENT ON COLUMN sunqubit.documentos.sucu_clie_id IS 'Campo clave con el id de la sucursal en caso requiera el cliente para la emision de su documento';
COMMENT ON COLUMN sunqubit.documentos.tiop_codigo IS 'Campo clave con la con el tipo de operacion a realizar en el caso de especificarlo';
COMMENT ON COLUMN sunqubit.documentos.docu_fecha_emision IS 'Campo que indica la fecha y la hora de emsión del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_fecha_vencimiento IS 'Campo en caso de fecha y hora de vencimiento y/o pago';
COMMENT ON COLUMN sunqubit.documentos.docu_numero IS 'Campo que identifica al documento segun el correlativo de la empresa emisora
formato
serie - numero
serie: 
F000 factura y sus Notas
B000 Boleta  y sus Notas
R000 Retenciones';
COMMENT ON COLUMN sunqubit.documentos.tido_codigo IS 'Campo clave que especifica que tipo de documento es ya sea Factura, Boleta, etc.';
COMMENT ON COLUMN sunqubit.documentos.tino_id IS 'Campo clave que indica que tipo de nota se esta emitiendo en caso de que el documento sea nota de crédito ó débito';
COMMENT ON COLUMN sunqubit.documentos.mone_id IS 'Campo clave para saber el tipo de moneda usado en el docuemnto';
COMMENT ON COLUMN sunqubit.documentos.tley_codigo IS 'Campo clave de tipos de leyenda en caso de tener';
COMMENT ON COLUMN sunqubit.documentos.docu_leyenda IS 'Campo Leyenda del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_observacion IS 'Campo de tener el documento alguna observación';
COMMENT ON COLUMN sunqubit.documentos.docu_subtotal IS 'Campo con el importe subtotal del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_grabada IS 'Campo que contiene el importe total de documento grabado al IGV';
COMMENT ON COLUMN sunqubit.documentos.docu_inafecta IS 'Campo que contiene el importe total de  inafecto del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_exonerada IS 'Campo que contiene el importe total exonerado del IGV  del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_gratuita IS 'Campo que contiene el importe total de valores gratuitos  del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_descuento IS 'Campo con el valor en caso de tener descuento alguno';
COMMENT ON COLUMN sunqubit.documentos.docu_igv IS 'Campo que contiene el valor total del impuesto General a las Ventas';
COMMENT ON COLUMN sunqubit.documentos.docu_isc IS 'Campo que contiene el valor del Impuesto Selectivo al Consumo';
COMMENT ON COLUMN sunqubit.documentos.docu_otros_tributos IS 'Campo que contiene el valor en caso de estar sometido a otros tributos';
COMMENT ON COLUMN sunqubit.documentos.docu_otros_cargos IS 'Campo con el importe de otros cargos';
COMMENT ON COLUMN sunqubit.documentos.docu_total IS 'Campo con importe total  del documento
 importe total retenido+ igv + inafectos + exonerados + otros cargos + isc + otros tributos.

En caso de Retencion, el valor de ls retencion (3% del valor totales de los documentos)';
COMMENT ON COLUMN sunqubit.documentos.docu_vendedor IS 'Campo en el caso de contar con la identificación del vendedor';
COMMENT ON COLUMN sunqubit.documentos.docu_clie_email IS 'Campo que contiene la direccion de email para el envio del documento al cliente';
COMMENT ON COLUMN sunqubit.documentos.docu_sustento_nota IS 'campo que se aplica al ingresar una nota de débito o crédito que especifica el sustento de la emisión';
COMMENT ON COLUMN sunqubit.documentos.docu_proc_fecha IS 'Campo que especifica la hora y la fecha del procesado';
COMMENT ON COLUMN sunqubit.documentos.docu_proc_status IS '* - Insertando a tablas cabecera y Detalle.
N - Nuevo
B - Bloqueo
P - Proceso-
E - Enviado
X - Error de Envio';
COMMENT ON COLUMN sunqubit.documentos.docu_anulado IS 'Campo que indica si el documento esta anulado(true) o no(false)';
COMMENT ON COLUMN sunqubit.documentos.docu_enviar_sunat IS 'Campo que indica si el documento debe(True) o no(False) ser enviado a la sunat';
COMMENT ON COLUMN sunqubit.documentos.docu_link_pdf IS 'Campo con la ruta del enlace al archivo PDF del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_link_xml IS 'Campo con la ruta del enlace al archivo XML del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_hash_sunat IS 'Campo que contiene el Hash Generado por el Certificado';
COMMENT ON COLUMN sunqubit.documentos.docu_link_cdr IS 'Campo con la ruta del enlace al archivo CDR del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_cdr_status IS 'Campo que contiene el estado del CDR';
COMMENT ON COLUMN sunqubit.documentos.docu_cdr_nota IS 'Campo con la nota del CDR';
COMMENT ON COLUMN sunqubit.documentos.docu_cdr_observacion IS 'Campo con la nota de observación del CDR';


ALTER SEQUENCE sunqubit.documentos_docu_id_seq OWNED BY sunqubit.documentos.docu_id;

CREATE TABLE sunqubit.documentos_referenciados (
                documentos_docu_id INTEGER NOT NULL,
                docu_id INTEGER NOT NULL,
                CONSTRAINT documentos_referenciados_pk PRIMARY KEY (documentos_docu_id)
);
COMMENT ON TABLE sunqubit.documentos_referenciados IS 'tabla que relaciona los documentos de refenrencias';
COMMENT ON COLUMN sunqubit.documentos_referenciados.documentos_docu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos_referenciados.docu_id IS 'Campo PK autoincremental';


CREATE SEQUENCE sunqubit.detalle_documentos_dedo_id_seq;

CREATE TABLE sunqubit.detalle_documentos (
                dedo_id INTEGER NOT NULL DEFAULT nextval('sunqubit.detalle_documentos_dedo_id_seq'),
                docu_id INTEGER NOT NULL,
                dedo_orden INTEGER NOT NULL,
                dedo_codigo_producto VARCHAR(20) NOT NULL,
                dedo_descripcion VARCHAR(200) NOT NULL,
                unme_codigo VARCHAR(5) NOT NULL,
                dedo_cantidad NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                dedo_precio_venta NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                dedo_subtotal NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                dedo_venta_no_onerosa NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                dedo_igv NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                tiai_codigo CHAR(2) NOT NULL,
                tisc_codigo CHAR(2) NOT NULL,
                dedo_descuento NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                CONSTRAINT detalle_documentos_pk PRIMARY KEY (dedo_id)
);
COMMENT ON TABLE sunqubit.detalle_documentos IS 'Tabla que contiene el detalle de los Items de una docuemnto';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.detalle_documentos.docu_id IS 'Campo clave que indica a que docuemtno pertenece el Item';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_orden IS 'Campo con el número de orden del item en el detalle del documento';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_codigo_producto IS 'Campo con el código del producto en caso de tener';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_descripcion IS 'Campo con la descripcion del producto a comprar';
COMMENT ON COLUMN sunqubit.detalle_documentos.unme_codigo IS 'Campo clave que identifica la unidad de medida de la cantidad';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_cantidad IS 'Campo con la cantidad del producto que compra';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_precio_venta IS 'campo con el importe del precio de venta unitario del producto';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_subtotal IS 'Campo con el importe subtotal del item cantidad * precio';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_venta_no_onerosa IS 'Campo con el valor referencial unitario por ítem en operaciones no onerosas ( gratuito)';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_igv IS 'Campo con el importe IGV del Item';
COMMENT ON COLUMN sunqubit.detalle_documentos.tiai_codigo IS 'Campo con el tipo de afectacion

10 Gravado - Operación Onerosa (uso comun)

verificar CATALOGO No. 07';
COMMENT ON COLUMN sunqubit.detalle_documentos.tisc_codigo IS 'Campo PK código del impuesto ISC';
COMMENT ON COLUMN sunqubit.detalle_documentos.dedo_descuento IS 'campo caso de tener un descuento en algun producto';


ALTER SEQUENCE sunqubit.detalle_documentos_dedo_id_seq OWNED BY sunqubit.detalle_documentos.dedo_id;

ALTER TABLE sunqubit.asignaciones_roles ADD CONSTRAINT roles_asignaciones_roles_fk
FOREIGN KEY (role_id)
REFERENCES sunqubit.roles (role_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.asignaciones_roles ADD CONSTRAINT usuarios_asignaciones_roles_fk
FOREIGN KEY (user_id)
REFERENCES sunqubit.usuarios (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.detalle_documentos ADD CONSTRAINT tipos_isc_detalle_documentos_fk
FOREIGN KEY (tisc_codigo)
REFERENCES sunqubit.tipos_isc (tisc_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.detalle_documentos ADD CONSTRAINT tipos_afectacion_igv_detalle_documentos_fk
FOREIGN KEY (tiai_codigo)
REFERENCES sunqubit.tipos_afectacion_igv (tiai_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.detalle_documentos ADD CONSTRAINT unidades_medida_detalle_documentos_fk
FOREIGN KEY (unme_codigo)
REFERENCES sunqubit.unidades_medida (unme_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT moneda_documentos_fk
FOREIGN KEY (mone_id)
REFERENCES sunqubit.monedas (mone_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.contribuyentes ADD CONSTRAINT tipos_documentos_identidad_contribuyentes_fk
FOREIGN KEY (tiid_codigo)
REFERENCES sunqubit.tipos_documentos_identidad (tiid_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.sucursales ADD CONSTRAINT contribuyentes_sucursales_fk
FOREIGN KEY (cont_id)
REFERENCES sunqubit.contribuyentes (cont_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT contribuyentes_documentos_fk
FOREIGN KEY (cont_id)
REFERENCES sunqubit.contribuyentes (cont_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT tipo_documentos_documentos_fk
FOREIGN KEY (tido_codigo)
REFERENCES sunqubit.tipos_documentos (tido_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.tipos_notas ADD CONSTRAINT tipos_documentos_tipos_notas_fk
FOREIGN KEY (tido_codigo)
REFERENCES sunqubit.tipos_documentos (tido_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.detalle_documentos ADD CONSTRAINT documentos_detalle_documentos_fk
FOREIGN KEY (docu_id)
REFERENCES sunqubit.documentos (docu_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos_referenciados ADD CONSTRAINT documentos_documentos_referenciados_fk
FOREIGN KEY (docu_id)
REFERENCES sunqubit.documentos (docu_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos_referenciados ADD CONSTRAINT documentos_documentos_referenciados_fk1
FOREIGN KEY (documentos_docu_id)
REFERENCES sunqubit.documentos (docu_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
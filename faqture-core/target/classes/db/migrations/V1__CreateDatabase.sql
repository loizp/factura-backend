
CREATE TABLE sunqubit.paises (
                pais_codigo CHAR(2) NOT NULL,
                pais_nombre VARCHAR(50) NOT NULL,
                CONSTRAINT paises_pk PRIMARY KEY (pais_codigo)
);
COMMENT ON TABLE sunqubit.paises IS 'Tabla de paises segun el catálogo';
COMMENT ON COLUMN sunqubit.paises.pais_codigo IS 'Campo PK con el código de pais estipulado en el catálogo';
COMMENT ON COLUMN sunqubit.paises.pais_nombre IS 'Campo con el nombre del pais';


CREATE TABLE sunqubit.tipos_notas_debito (
                tnod_codigo CHAR(2) NOT NULL,
                tnod_descripcion VARCHAR(200) NOT NULL,
                CONSTRAINT tipos_notas_debito_pk PRIMARY KEY (tnod_codigo)
);
COMMENT ON TABLE sunqubit.tipos_notas_debito IS '01
Intereses por mora
02
03
Aumento en el valor
Penalidades/ otros conceptos';
COMMENT ON COLUMN sunqubit.tipos_notas_debito.tnod_codigo IS 'Campo PK codigo del tipo de nota de debito';
COMMENT ON COLUMN sunqubit.tipos_notas_debito.tnod_descripcion IS 'Campo con la descripción del tipo de nota de débito';


CREATE TABLE sunqubit.tipos_notas_credito (
                tnoc_codigo CHAR(2) NOT NULL,
                tnoc_descripcion VARCHAR(200) NOT NULL,
                CONSTRAINT tipos_notas_credito_pk PRIMARY KEY (tnoc_codigo)
);
COMMENT ON TABLE sunqubit.tipos_notas_credito IS '01
Anulación de la operación
02
Anulación por error en el RUC
03
Corrección por error en la descripción
04
Descuento global
05
Descuento por ítem
06
Devolución total
07
Devolución por ítem
08
Bonificación
09
Disminución en el valor
10
Otros Conceptos';
COMMENT ON COLUMN sunqubit.tipos_notas_credito.tnoc_codigo IS 'Campo PK codigo de tipo de nota de crédito';
COMMENT ON COLUMN sunqubit.tipos_notas_credito.tnoc_descripcion IS 'Campo con la descripción del tipo de nota de crédito';


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


CREATE SEQUENCE sunqubit.clientes_clie_id_seq_1;

CREATE TABLE sunqubit.clientes (
                clie_id INTEGER NOT NULL DEFAULT nextval('sunqubit.clientes_clie_id_seq_1'),
                clie_numero VARCHAR(15) NOT NULL,
                tiid_codigo CHAR(1) NOT NULL,
                clie_nombres VARCHAR(200) NOT NULL,
                CONSTRAINT clientes_pk PRIMARY KEY (clie_id)
);
COMMENT ON TABLE sunqubit.clientes IS 'Contiene los datos de los clientes a quienes se les emite un documento.';
COMMENT ON COLUMN sunqubit.clientes.clie_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.clientes.clie_numero IS 'Campo único que contiene el número de documento de identificación';
COMMENT ON COLUMN sunqubit.clientes.tiid_codigo IS 'Campo clave del tipo de documento de identificación';
COMMENT ON COLUMN sunqubit.clientes.clie_nombres IS 'Campo que representa al nombre o razón social';


ALTER SEQUENCE sunqubit.clientes_clie_id_seq_1 OWNED BY sunqubit.clientes.clie_id;

CREATE UNIQUE INDEX clientes_idx
 ON sunqubit.clientes
 ( clie_numero );

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


CREATE SEQUENCE sunqubit.empresas_clie_id_seq_1_1;

CREATE TABLE sunqubit.empresas (
                empr_id INTEGER NOT NULL DEFAULT nextval('sunqubit.empresas_clie_id_seq_1_1'),
                empr_ruc CHAR(11) NOT NULL,
                empr_razon_social VARCHAR(200) NOT NULL,
                empr_nombre_comercial VARCHAR(200) NOT NULL,
                empr_direccion VARCHAR(200) NOT NULL,
                tiid_codigo CHAR(1) NOT NULL,
                ubig_id INTEGER NOT NULL,
                pais_codigo CHAR(2) DEFAULT 'PE' NOT NULL,
                CONSTRAINT empresas_pk PRIMARY KEY (empr_id)
);
COMMENT ON TABLE sunqubit.empresas IS 'Tabla que contiene las empresas quienes emiten los documentos a los clientes';
COMMENT ON COLUMN sunqubit.empresas.empr_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.empresas.empr_ruc IS 'Campo que contiene el numero de documento de identificacion de la Empresa emisora teniendo en cuenta que por lo general es el RUC';
COMMENT ON COLUMN sunqubit.empresas.empr_razon_social IS 'Campo que contiene la Razon Social de la empresa emisora de acuerdo a los registros Públicos';
COMMENT ON COLUMN sunqubit.empresas.empr_nombre_comercial IS 'Campo que contiene la Razon Comercial de la empresa emisora para identificacion con sus clientes';
COMMENT ON COLUMN sunqubit.empresas.empr_direccion IS 'Campo que contiene la Dirección de la empresa';
COMMENT ON COLUMN sunqubit.empresas.tiid_codigo IS 'Campo clave del tipo de documento de identificación teniendo en cuenta que la mayoria usa el RUC';
COMMENT ON COLUMN sunqubit.empresas.ubig_id IS 'Campo clave para la ubicación Postal de acuerdo a los registros de ubigeo controlado por el estado';
COMMENT ON COLUMN sunqubit.empresas.pais_codigo IS 'Campo con el codigo de pais de la empresa por defecto es PERU';


ALTER SEQUENCE sunqubit.empresas_clie_id_seq_1_1 OWNED BY sunqubit.empresas.empr_id;

CREATE UNIQUE INDEX empresas_idx
 ON sunqubit.empresas
 ( empr_ruc );

CREATE SEQUENCE sunqubit.sucursales_sucu_id_seq_1;

CREATE TABLE sunqubit.sucursales (
                sucu_id INTEGER NOT NULL DEFAULT nextval('sunqubit.sucursales_sucu_id_seq_1'),
                empr_id INTEGER NOT NULL,
                sucu_direccion VARCHAR(200) NOT NULL,
                ubig_id INTEGER NOT NULL,
                pais_codigo CHAR(2) DEFAULT 'PE' NOT NULL,
                CONSTRAINT sucursales_pk PRIMARY KEY (sucu_id, empr_id)
);
COMMENT ON TABLE sunqubit.sucursales IS 'Contiene las sucursales de las empresas emisoras de documentos';
COMMENT ON COLUMN sunqubit.sucursales.sucu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.sucursales.empr_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.sucursales.sucu_direccion IS 'Campo con la dirección de la sucursal';
COMMENT ON COLUMN sunqubit.sucursales.ubig_id IS 'Campo clave de la ubicación geográfica';
COMMENT ON COLUMN sunqubit.sucursales.pais_codigo IS 'Campo con el codigo de pais de la sucursal por defecto es PERU';


ALTER SEQUENCE sunqubit.sucursales_sucu_id_seq_1 OWNED BY sunqubit.sucursales.sucu_id;

CREATE SEQUENCE sunqubit.documentos_docu_id_seq;

CREATE TABLE sunqubit.documentos (
                docu_id INTEGER NOT NULL DEFAULT nextval('sunqubit.documentos_docu_id_seq'),
                tido_codigo CHAR(2) NOT NULL,
                docu_observacion VARCHAR(250),
                docu_fecha_emision DATE NOT NULL,
                docu_fecha_vencimiento DATE,
                docu_numero VARCHAR(20) NOT NULL,
                mone_id VARCHAR(3) NOT NULL,
                docu_grabada NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_inafecta NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_exonerada NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_gratuita NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_descuento NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_subtotal NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_total VARCHAR DEFAULT 0.00 NOT NULL,
                docu_igv NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_isc NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_otros_tributos NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_otros_cargos NUMERIC(15,2) DEFAULT 0.00 NOT NULL,
                docu_vendedor VARCHAR(200),
                tiop_codigo CHAR(2) NOT NULL,
                docu_proc_fecha TIMESTAMP NOT NULL,
                clie_id INTEGER NOT NULL,
                sucu_id INTEGER NOT NULL,
                empr_id INTEGER NOT NULL,
                docu_sustento_nota VARCHAR(250),
                docu_tipo_nota CHAR(2),
                docu_enviar_sunat BOOLEAN DEFAULT True NOT NULL,
                docu_proc_status CHAR(1) DEFAULT 'N' NOT NULL,
                docu_link_cdr VARCHAR(200),
                docu_link_pdf VARCHAR(200),
                docu_link_xml VARCHAR(200),
                docu_cdr_status VARCHAR(200),
                docu_cdr_nota VARCHAR(200),
                docu_cdr_observacion VARCHAR,
                CONSTRAINT documentos_pk PRIMARY KEY (docu_id)
);
COMMENT ON TABLE sunqubit.documentos IS 'Tabla principal del sistema que contiene los documentos que son captado y enviados a las SUNAT';
COMMENT ON COLUMN sunqubit.documentos.docu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos.tido_codigo IS 'Campo clave que especifica que tipo de documento es ya sea Factura, Boleta, etc.';
COMMENT ON COLUMN sunqubit.documentos.docu_observacion IS 'Campo de tener el documento alguna observación';
COMMENT ON COLUMN sunqubit.documentos.docu_fecha_emision IS 'Campo que indica la fecha de emsión del documento en formato YYYY-MM-DD, ej. 2017-06-21';
COMMENT ON COLUMN sunqubit.documentos.docu_fecha_vencimiento IS 'Campo en caso de fecha de vencimiento';
COMMENT ON COLUMN sunqubit.documentos.docu_numero IS 'Campo que identifica al documento segun el correlativo de la empresa emisora
formato
serie - numero
serie: 
F000 factura y sus Notas
B000 Boleta  y sus Notas
R000 Retenciones';
COMMENT ON COLUMN sunqubit.documentos.mone_id IS 'Campo clave para saber el tipo de moneda usado en el docuemnto';
COMMENT ON COLUMN sunqubit.documentos.docu_grabada IS 'Campo que contiene el importe total de documento grabado al IGV';
COMMENT ON COLUMN sunqubit.documentos.docu_inafecta IS 'Campo que contiene el importe total de  inafecto del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_exonerada IS 'Campo que contiene el importe total exonerado del IGV  del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_gratuita IS 'Campo que contiene el importe total de valores gratuitos  del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_descuento IS 'Campo con el valor en caso de tener descuento alguno';
COMMENT ON COLUMN sunqubit.documentos.docu_subtotal IS 'Campo con el importe subtotal del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_total IS 'Campo con importe total  del documento
 importe total retenido+ igv + inafectos + exonerados + otros cargos + isc + otros tributos.

En caso de Retencion, el valor de ls retencion (3% del valor totales de los documentos)';
COMMENT ON COLUMN sunqubit.documentos.docu_igv IS 'Campo que contiene el valor total del impuesto General a las Ventas';
COMMENT ON COLUMN sunqubit.documentos.docu_isc IS 'Campo que contiene el valor del Impuesto Selectivo al Consumo';
COMMENT ON COLUMN sunqubit.documentos.docu_otros_tributos IS 'Campo que contiene el valor en caso de estar sometido a otros tributos';
COMMENT ON COLUMN sunqubit.documentos.docu_otros_cargos IS 'Campo con el importe de otros cargos';
COMMENT ON COLUMN sunqubit.documentos.docu_vendedor IS 'Campo en el caso de contar con la identificación del vendedor';
COMMENT ON COLUMN sunqubit.documentos.tiop_codigo IS 'Campo clave del tipo de operación';
COMMENT ON COLUMN sunqubit.documentos.docu_proc_fecha IS 'Campo que especifica la hora y la fecha del procesado';
COMMENT ON COLUMN sunqubit.documentos.clie_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos.sucu_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos.empr_id IS 'Campo PK autoincremental';
COMMENT ON COLUMN sunqubit.documentos.docu_sustento_nota IS 'campo que se aplica al ingresar una nota de débito o crédito que especifica el sustento de la emisión';
COMMENT ON COLUMN sunqubit.documentos.docu_tipo_nota IS 'Campo clave que indica que tipo de nota se esta emitiendo en caso de que el documento sea nota de crédito ó débito';
COMMENT ON COLUMN sunqubit.documentos.docu_enviar_sunat IS 'Campo que indica si el documento debe(True) o no(False) ser enviado a la sunat';
COMMENT ON COLUMN sunqubit.documentos.docu_proc_status IS '* - Insertando a tablas cabecera y Detalle.
N - Nuevo
B - Bloqueo
P - Proceso-
E - Enviado
X - Error de Envio';
COMMENT ON COLUMN sunqubit.documentos.docu_link_cdr IS 'Campo con la ruta del enlace al archivo CDR del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_link_pdf IS 'Campo con la ruta del enlace al archivo PDF del documento';
COMMENT ON COLUMN sunqubit.documentos.docu_link_xml IS 'Campo con la ruta del enlace al archivo XML del documento';
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

ALTER TABLE sunqubit.documentos ADD CONSTRAINT tipo_operaciones_documentos_fk
FOREIGN KEY (tiop_codigo)
REFERENCES sunqubit.tipos_operaciones (tiop_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT moneda_documentos_fk
FOREIGN KEY (mone_id)
REFERENCES sunqubit.monedas (mone_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.clientes ADD CONSTRAINT tipo_identificaciones_clientes_fk
FOREIGN KEY (tiid_codigo)
REFERENCES sunqubit.tipos_documentos_identidad (tiid_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.empresas ADD CONSTRAINT tipo_documento_identidad_empresas_fk
FOREIGN KEY (tiid_codigo)
REFERENCES sunqubit.tipos_documentos_identidad (tiid_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT clientes_documentos_fk
FOREIGN KEY (clie_id)
REFERENCES sunqubit.clientes (clie_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT tipo_documentos_documentos_fk
FOREIGN KEY (tido_codigo)
REFERENCES sunqubit.tipos_documentos (tido_codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.sucursales ADD CONSTRAINT empresas_sucursales_fk
FOREIGN KEY (empr_id)
REFERENCES sunqubit.empresas (empr_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE sunqubit.documentos ADD CONSTRAINT sucursales_documentos_fk
FOREIGN KEY (sucu_id, empr_id)
REFERENCES sunqubit.sucursales (sucu_id, empr_id)
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
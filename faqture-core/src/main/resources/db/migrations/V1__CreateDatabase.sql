CREATE TABLE clientes (
    clie_id integer NOT NULL,
    clie_numero character varying(15) NOT NULL,
    tiid_codigo character(1) NOT NULL,
    clie_nombres character varying(200) NOT NULL
);

COMMENT ON COLUMN clientes.clie_nombres IS 'representa al nombre o razón social';

CREATE SEQUENCE clientes_clie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE clientes_clie_id_seq OWNED BY clientes.clie_id;

CREATE TABLE detalle_documentos (
    dedo_id integer NOT NULL,
    docu_id integer NOT NULL,
    dedo_orden integer NOT NULL,
    dedo_codigo_producto character varying(20) NOT NULL,
    dedo_descripcion character varying(200) NOT NULL,
    unme_codigo character varying(5) NOT NULL,
    dedo_cantidad numeric(15,2) NOT NULL,
    dedo_precio_venta numeric(15,2) NOT NULL,
    dedo_subtotal numeric(15,2) NOT NULL,
    dedo_venta_no_onerosa numeric(15,2) NOT NULL,
    dedo_igv numeric(15,2) NOT NULL,
    tiai_codigo character(2) NOT NULL
);

CREATE SEQUENCE detalle_documentos_dedo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE detalle_documentos_dedo_id_seq OWNED BY detalle_documentos.dedo_id;

CREATE TABLE documentos (
    docu_id integer NOT NULL,
    tido_codigo character(2) NOT NULL,
    empr_id integer NOT NULL,
    clie_id integer NOT NULL,
    docu_fecha date NOT NULL,
    docu_numero character varying(20) NOT NULL,
    mone_id integer NOT NULL,
    docu_grabada numeric(15,2) NOT NULL,
    docu_inafecta numeric(15,2) NOT NULL,
    docu_exonerada numeric(15,2) NOT NULL,
    docu_gratuita numeric(15,2) NOT NULL,
    docu_descuento numeric(15,2) NOT NULL,
    docu_subtotal numeric(15,2) NOT NULL,
    docu_total character varying NOT NULL,
    docu_igv numeric(15,2) NOT NULL,
    docu_isc numeric(15,2) NOT NULL,
    docu_otros_tributos numeric(15,2) NOT NULL,
    docu_otros_cargos numeric(15,2) NOT NULL,
    docu_vendedor character varying(200) NOT NULL,
    tiop_codigo character(2) NOT NULL
);


COMMENT ON COLUMN documentos.docu_total IS 'importe total  del documento
 importe total retenido+ igv + inafectos + exonerados + otros cargos + isc + otros tributos.

En caso de Retencion, el valor de ls retencion (3% del valor totales de los documentos)';

CREATE SEQUENCE documentos_docu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE documentos_docu_id_seq OWNED BY documentos.docu_id;

CREATE TABLE empresas (
    empr_id integer NOT NULL,
    empr_ruc character(11) NOT NULL,
    empr_razon_social character varying(200) NOT NULL,
    empr_nombre_comercial character varying(200) NOT NULL,
    empr_direccion character varying(200) NOT NULL,
    tiid_codigo character(1) NOT NULL,
    ubig_id integer NOT NULL
);

CREATE SEQUENCE empresas_clie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE empresas_clie_id_seq OWNED BY empresas.empr_id;

CREATE TABLE moneda (
    mone_id integer NOT NULL,
    mone_descripcion character varying(20) NOT NULL
);

COMMENT ON TABLE moneda IS 'Catálogo No. 02: Códigos de Tipo de Monedas.
PEN Nuevo Sol';

COMMENT ON COLUMN moneda.mone_descripcion IS '1	NUEVOS SOLES
2	DÓLARES AMERICANOS
9	OTRA MONEDA (ESPECIFICAR)';

CREATE TABLE tipo_documentos (
    tido_codigo character(2) NOT NULL,
    tido_descripcion character varying(100) NOT NULL
);

COMMENT ON TABLE tipo_documentos IS 'CATALOGO No. 01
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


CREATE TABLE tipo_documentos_identidad (
    tiid_codigo character(1) NOT NULL,
    tiid_descripcion character varying(100) NOT NULL
);


COMMENT ON TABLE tipo_documentos_identidad IS 'Catálogo No. 06: Códigos de Tipos de Documentos de Identidad
===========================
0 DOC.TRIB.NO.DOM.SIN.RUC
1 DOC. NACIONAL DE IDENTIDAD
4 CARNET DE EXTRANJERIA
6 REG. UNICO DE CONTRIBUYENTES
7 PASAPORTE
A CED. DIPLOMATICA DE IDENTIDAD';

CREATE TABLE tipo_operaciones (
    tiop_codigo character(2) NOT NULL,
    tiop_descripcion character varying(20) NOT NULL
);

COMMENT ON TABLE tipo_operaciones IS 'Catálogo No. 17: Códigos – Tipo de Operación';

CREATE TABLE tipos_afectacion_igv (
    tiai_codigo character(2) NOT NULL,
    tiai_descripcion character varying(100) NOT NULL
);

COMMENT ON TABLE tipos_afectacion_igv IS 'Catálogo No. 07 Códigos de Tipo de Afectación del IGV
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

CREATE TABLE ubigeos (
    ubig_id integer NOT NULL,
    ubig_cod_dpto character(2) NOT NULL,
    ubig_cod_prov character(2) NOT NULL,
    ubig_cod_dist character(2) NOT NULL,
    ubig_descripcion character varying(200) NOT NULL
);

CREATE TABLE unidades_medida (
    unme_codigo character varying(5) NOT NULL,
    unme_descripcion character varying(100) NOT NULL
);

COMMENT ON TABLE unidades_medida IS 'NIU Unidades
ZZ Servicios';

ALTER TABLE ONLY clientes ALTER COLUMN clie_id SET DEFAULT nextval('clientes_clie_id_seq'::regclass);
ALTER TABLE ONLY detalle_documentos ALTER COLUMN dedo_id SET DEFAULT nextval('detalle_documentos_dedo_id_seq'::regclass);
ALTER TABLE ONLY documentos ALTER COLUMN docu_id SET DEFAULT nextval('documentos_docu_id_seq'::regclass);
ALTER TABLE ONLY empresas ALTER COLUMN empr_id SET DEFAULT nextval('empresas_clie_id_seq'::regclass);
SELECT pg_catalog.setval('clientes_clie_id_seq', 1, false);
SELECT pg_catalog.setval('detalle_documentos_dedo_id_seq', 1, false);
SELECT pg_catalog.setval('documentos_docu_id_seq', 1, false);
SELECT pg_catalog.setval('empresas_clie_id_seq', 1, false);
ALTER TABLE ONLY clientes
    ADD CONSTRAINT clientes_pk PRIMARY KEY (clie_id);
ALTER TABLE ONLY detalle_documentos
    ADD CONSTRAINT detalle_documentos_pk PRIMARY KEY (dedo_id);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT documentos_pk PRIMARY KEY (docu_id);
ALTER TABLE ONLY empresas
    ADD CONSTRAINT empresas_pk PRIMARY KEY (empr_id);
ALTER TABLE ONLY moneda
    ADD CONSTRAINT moneda_pk PRIMARY KEY (mone_id);
ALTER TABLE ONLY tipo_documentos_identidad
    ADD CONSTRAINT tipo_documentos_identidad_pk PRIMARY KEY (tiid_codigo);
ALTER TABLE ONLY tipo_documentos
    ADD CONSTRAINT tipo_documentos_pk PRIMARY KEY (tido_codigo);
ALTER TABLE ONLY tipo_operaciones
    ADD CONSTRAINT tipo_operaciones_pk PRIMARY KEY (tiop_codigo);
ALTER TABLE ONLY tipos_afectacion_igv
    ADD CONSTRAINT tipos_afectacion_igv_pk PRIMARY KEY (tiai_codigo);
ALTER TABLE ONLY ubigeos
    ADD CONSTRAINT ubigeos_pk PRIMARY KEY (ubig_id);
ALTER TABLE ONLY unidades_medida
    ADD CONSTRAINT unidades_medida_pk PRIMARY KEY (unme_codigo);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT clientes_documentos_fk FOREIGN KEY (empr_id) REFERENCES empresas(empr_id);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT clientes_documentos_fk1 FOREIGN KEY (clie_id) REFERENCES clientes(clie_id);
ALTER TABLE ONLY detalle_documentos
    ADD CONSTRAINT documentos_detalle_documentos_fk FOREIGN KEY (docu_id) REFERENCES documentos(docu_id);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT moneda_documentos_fk FOREIGN KEY (mone_id) REFERENCES moneda(mone_id);
ALTER TABLE ONLY empresas
    ADD CONSTRAINT tipo_documento_identidad_empresas_fk FOREIGN KEY (tiid_codigo) REFERENCES tipo_documentos_identidad(tiid_codigo);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT tipo_documentos_documentos_fk FOREIGN KEY (tido_codigo) REFERENCES tipo_documentos(tido_codigo);
ALTER TABLE ONLY clientes
    ADD CONSTRAINT tipo_identificaciones_clientes_fk FOREIGN KEY (tiid_codigo) REFERENCES tipo_documentos_identidad(tiid_codigo);
ALTER TABLE ONLY documentos
    ADD CONSTRAINT tipo_operaciones_documentos_fk FOREIGN KEY (tiop_codigo) REFERENCES tipo_operaciones(tiop_codigo);
ALTER TABLE ONLY detalle_documentos
    ADD CONSTRAINT tipos_afectacion_igv_detalle_documentos_fk FOREIGN KEY (tiai_codigo) REFERENCES tipos_afectacion_igv(tiai_codigo);
ALTER TABLE ONLY detalle_documentos
    ADD CONSTRAINT unidades_medida_detalle_documentos_fk FOREIGN KEY (unme_codigo) REFERENCES unidades_medida(unme_codigo);

package com.sunqubit.faqture.beans.utils;

public class ConstantProperty {
	// File Manager
	public static final String[] ARRAY_MONTH = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	public static final String GCSTORE_BUCKET = "faqture-store";
	public static final String CONFIG_FOLDER = "/config/";
	public static final String TOSUNAT_FOLDER = "/sunat/envio/";
	public static final String FROMSUNAT_FOLDER = "/sunat/recepcion/";
	public static final String QRPDF417_FOLDER = "/qr-pdf417/";
	public static final String QRNAME_EXT = "-qr.png";
	public static final String PDF417NAME_EXT = "-pdf417.png";
	public static final String PDF_CONTENTTYPE = "application/pdf";
	public static final String XML_EXT = ".xml";
	public static final String ZIP_EXT = ".zip";
	public static final String PDF_EXT = ".pdf";
	// local AESCipher
	public static final String SALT_SECRET = "F@Qtur@$";
	// Spring Service Security
	public static final String LOGIN_URL = "/login";
	public static final String REFRESH_TOKEN_URL = "/token";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	// JWT Service Security
	public static final String ISSUER_INFO = "https://www.faqture.com/";
	public static final String SUPER_SECRET_KEY = "@utHL0g1n";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}

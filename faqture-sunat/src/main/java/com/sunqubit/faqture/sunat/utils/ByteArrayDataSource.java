package com.sunqubit.faqture.sunat.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayDataSource implements javax.activation.DataSource {
	private byte[] bytes;
	private String contentType;

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public InputStream getInputStream() {
		return new ByteArrayInputStream(bytes);
	}
	
	public OutputStream getOutputStream() throws IOException {
		final ByteArrayDataSource bads = this;
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// devuelve un outputstream igual que los bytes de mi array
		// cuando este está cerrado.
		FilterOutputStream a = new FilterOutputStream(baos);
		baos.close();
		bads.setBytes(baos.toByteArray());
		return a;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
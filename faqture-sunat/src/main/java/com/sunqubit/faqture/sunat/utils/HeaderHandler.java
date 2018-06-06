package com.sunqubit.faqture.sunat.utils;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
	private String rucEmisor;
	private String userEmisor;
	private String passEmisor;

	public HeaderHandler(String rucEmisor, String userEmisor, String passEmisor) {
		this.rucEmisor = rucEmisor;
		this.userEmisor = userEmisor;
		this.passEmisor = passEmisor;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outboundProperty.booleanValue()) {
			SOAPMessage message = context.getMessage();
			try {
				SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				if(header == null) header = envelope.addHeader();
				SOAPElement security = header.addChildElement("Security", "wsse",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
				
				SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
				SOAPElement username = usernameToken.addChildElement("Username", "wsse");
				username.addTextNode(rucEmisor + userEmisor);
				SOAPElement password = usernameToken.addChildElement("Password", "wsse");
				password.addTextNode(passEmisor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outboundProperty;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	public String getRucEmisor() {
		return rucEmisor;
	}

	public void setRucEmisor(String rucEmisor) {
		this.rucEmisor = rucEmisor;
	}

	public String getUserEmisor() {
		return userEmisor;
	}

	public void setUserEmisor(String userEmisor) {
		this.userEmisor = userEmisor;
	}

	public String getPassEmisor() {
		return passEmisor;
	}

	public void setPassEmisor(String passEmisor) {
		this.passEmisor = passEmisor;
	}

}

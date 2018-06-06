package com.sunqubit.faqture.sunat.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class HeaderHandlerResolver implements HandlerResolver {
	
	private String rucEmisor, userEmisor, passEmisor;
	
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();
		HeaderHandler hh = new HeaderHandler(rucEmisor, userEmisor, passEmisor);
		handlerChain.add(hh);
        return handlerChain;
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

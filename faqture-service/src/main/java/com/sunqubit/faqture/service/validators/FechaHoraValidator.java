package com.sunqubit.faqture.service.validators;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class FechaHoraValidator {
	private String sepf = "[/ -._|]";
	private String seph = "[: -._|]";
	private String sepfh = "[Tt ]";
	
	private static final String dia1a28 = "(0?[1-9]|1\\d|2[0-8])";
	private static final String dia29 = "(29)";
	private static final String dia29o30 = "(29|30)";
	private static final String dia31 = "(31)";
	
	private static final String mes1a12 = "(0?[1-9]|1[0-2])";
	private static final String mes2 = "(0?2)";
	private static final String mesNoFeb = "(0?[13-9]|1[0-2])";
	private static final String mes31dias = "(0?[13578]|1[02])";
	
	private String diames29Feb = dia29 + sepf + mes2;
	private String diames1a28 = dia1a28 + sepf + mes1a12;
	private String diames29o30noFeb = dia29o30 + sepf + mesNoFeb;
	private String diames31 = dia31 + sepf + mes31dias;
	
	private String diamesNo29Feb = "(?:" + diames1a28 + "|" + diames29o30noFeb + "|" + diames31 + ")";
	
	private static final String anno1a9999 = "(0{2,3}[1-9]|0{1,2}[1-9]\\d|0?[1-9]\\d{2}|[1-9]\\d{3})";
	private static final String annoMult4no100 = "\\d{1,2}(?:0[48]|[2468][048]|[13579][26])";
	private static final String annoMult400 = "(?:0?[48]|[13579][26]|[2468][048])00";
	
	private static final String annoBisiesto = "(" + annoMult4no100 + "|" + annoMult400 + ")";
	
	private String fechaNo29Feb = diamesNo29Feb + sepf + anno1a9999;
	private String fecha29Feb = diames29Feb + sepf + annoBisiesto;
	private String fechaRegExp = "^(?:" + fechaNo29Feb + "|" + fecha29Feb + ")$";
	
	private static final String hora = "([0-9]|[01]?[0-9]|2[0-3])";
	private static final String horaAMPM = "([1-9]|[0]?[1-9]|1[0-2])";
	private static final String minseg = "([0-5][0-9])";
	private static final String milseg = "[.]([0-9]|[0-9]{1,2}|[0-9]{3})";
	private static final String ampm = "[ ](?:([apAP][mM])|([apAP].[mM].))";
	private String hmm = hora + seph + minseg + seph + minseg;
	private String hmmm = hora + seph + minseg + seph + minseg + milseg;
	private String horaRegExp = "^(?:" + hmm + "|" + hmmm + ")$";
	
	private String fechaHoraRegExp = "^(?:" + "(?:" + fechaNo29Feb + "|" + fecha29Feb + ")" + sepfh + "(?:" + hmm + "|" + hmmm + ")" + ")$";
	
	public FechaHoraValidator() {
		
	}
	
	public FechaHoraValidator(String formato) {
		if(formato != null) {
			formato = formato.trim().replaceAll("(.)\\1+", "$1").toLowerCase();
			
			this.defineSeparadores(formato);
			
			this.generaRegExp(formato);
		}
	}
	
	private void defineSeparadores(String formato) {
		if(!formato.startsWith("h")) {
			if(formato.substring(1, 2).matches(this.sepf)) {
				this.setSepf(formato.substring(1, 2));
				if(formato.length() > 8) {
					if(formato.substring(5, 6).matches(this.sepfh))
						this.setSepfh(formato.substring(5, 6));
					
					if(!formato.substring(7, 8).matches(this.seph))
						this.setSeph(formato.substring(7, 8));
					else
						this.seph = "";
				}
			} else {
				this.sepf = "";
				if(formato.length() > 6) {
					if(formato.substring(3, 4).matches(this.sepfh))
						this.setSepfh(formato.substring(3, 4));
					
					if(!formato.substring(5, 6).matches(this.seph))
						this.setSeph(formato.substring(5, 6));
					else
						this.seph = "";
				}
			}
		} else {
			if(!formato.substring(1, 2).matches(this.seph))
				this.setSeph(formato.substring(1, 2));
			else
				this.seph = "";
		}
	}
	
	private void generaRegExp(String formato) {
		if(formato.startsWith("d") || formato.startsWith("h")) {
			this.diames29Feb = dia29 + this.sepf + mes2;
			this.diames1a28 = dia1a28 + this.sepf + mes1a12;
			this.diames29o30noFeb = dia29o30 + this.sepf + mesNoFeb;
			this.diames31 = dia31 + this.sepf + mes31dias;
			this.diamesNo29Feb = "(?:" + diames1a28 + "|" + diames29o30noFeb + "|" + diames31 + ")";
			this.fechaNo29Feb = diamesNo29Feb + this.sepf + anno1a9999;
			this.fecha29Feb = diames29Feb + this.sepf + annoBisiesto;
		} else {
			this.diames29Feb = mes2 + this.sepf + dia29;
			this.diames1a28 = mes1a12 + this.sepf + dia1a28;
			this.diames29o30noFeb = mesNoFeb + this.sepf + dia29o30;
			this.diames31 = mes31dias + this.sepf + dia31;
			this.diamesNo29Feb = "(?:" + diames1a28 + "|" + diames29o30noFeb + "|" + diames31 + ")";
			this.fechaNo29Feb = anno1a9999 + this.sepf + diamesNo29Feb;
			this.fecha29Feb = annoBisiesto + this.sepf + diames29Feb;
		}
		
		this.fechaRegExp = "^(?:" + fechaNo29Feb + "|" + fecha29Feb + ")$";
		this.hmm = hora + this.seph + minseg + this.seph + minseg;
		this.hmmm = hora + this.seph + minseg + this.seph + minseg + milseg;
		this.horaRegExp = "^(?:" + hmm + "|" + hmmm + ")$";
		this.fechaHoraRegExp = "^(?:" + "(?:" + fechaNo29Feb + "|" + fecha29Feb + ")" + "[ ]" + "(?:" + hmm + "|" + hmmm + ")" + ")$";
	}

	public void setSepf(String sepf) {
		this.sepf = "[" + sepf + "]";
	}
	
	public void setSeph(String seph) {
		this.seph = "[" + seph + "]";
	}
	
	public void setSepfh(String sepfh) {
		this.sepfh = "[" + sepfh + "]";
	}

	public String getFechaRegExp() {
		return fechaRegExp;
	}

	public String getHoraRegExp() {
		return horaRegExp;
	}

	public String getFechaHoraRegExp() {
		return fechaHoraRegExp;
	}
	
	public boolean fechaValida(String fecha) {
		return fecha.matches(this.getFechaRegExp());
	}
	
	public boolean horaValida(String hora) {
		formatoHoraAMPM(hora);
		return hora.matches(this.getHoraRegExp());
	}
	
	public boolean fechaHoraValida(String fechaHora) {
		formatoHoraAMPM(fechaHora);
		return fechaHora.matches(this.getFechaHoraRegExp());
	}
	
	public boolean fechaValida(Date fecha) {
		return fecha.toString().matches(this.getFechaRegExp());
	}
	
	public boolean horaValida(Time hora) {
		formatoHoraAMPM(hora.toString());
		return hora.toString().matches(this.getHoraRegExp());
	}
	
	public boolean fechaHoraValida(Timestamp fechaHora) {
		formatoHoraAMPM(fechaHora.toString());
		return fechaHora.toString().matches(this.getFechaHoraRegExp());
	}
	
	private void formatoHoraAMPM(String fh) {
		if(fh.toLowerCase().endsWith("am") || fh.toLowerCase().endsWith("a.m.") || fh.toLowerCase().endsWith("pm") || fh.toLowerCase().endsWith("p.m.")) {
			this.hmm = horaAMPM + this.seph + minseg + this.seph + minseg + ampm;
			this.hmmm = horaAMPM + this.seph + minseg + this.seph + minseg + milseg + ampm;
			this.horaRegExp = "^(?:" + hmm + "|" + hmmm + ")$";
			this.fechaHoraRegExp = "^(?:" + "(?:" + fechaNo29Feb + "|" + fecha29Feb + ")" + "[ ]" + "(?:" + hmm + "|" + hmmm + ")" + ")$";
		}
	}
}

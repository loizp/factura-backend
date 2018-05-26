package com.sunqubit.faqture.beans.test;

import com.sunqubit.faqture.beans.utils.AESCipher;
import com.sunqubit.faqture.beans.utils.MainProperties;

public class BeansTest {
	public static void main(String[] args) {
		/*
		MainProperties mainProperties = new MainProperties();
		System.out.println(mainProperties.getPropertyValue("sunat.urlservice"));
		*/
		String key="Secur1tyf@qTure";
		String keyt="@utHL0g1n";
		System.out.println(AESCipher.encripta(keyt, key));
	}
}

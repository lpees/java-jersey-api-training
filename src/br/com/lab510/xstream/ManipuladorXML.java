package br.com.lab510.xstream;

import com.thoughtworks.xstream.XStream;

import br.com.lab510.modelos.Request;

public class ManipuladorXML {
	
	private static XStream xStream = new XStream();
	
	public static Request formataXmlParaObj (String conteudo) {
		
		formataEstruturaXML();
		Request objRequest = (Request) xStream.fromXML(conteudo);
		
		return objRequest;
	}
	
	public static void formataEstruturaXML () {
		
		xStream.alias("Request", Request.class);
		xStream.aliasField("transaction-event", Request.class, "transactionEvent");
		
	}
	
}

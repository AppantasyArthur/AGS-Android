package com.alpha.upnp.parser;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class SystemInfoParser {

	private SystemInfoHandler dataHandler;
	private XMLReader xr;
	public SystemInfoParser(){
		
		try{
		
			SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    xr = sp.getXMLReader();  

		    dataHandler = new SystemInfoHandler();   
		    xr.setContentHandler(dataHandler);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public SystemInfoVO parse(String xml){
		
		try{
			xr.parse(new InputSource(new StringReader(xml)));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dataHandler.getData();
		
	}
	
}

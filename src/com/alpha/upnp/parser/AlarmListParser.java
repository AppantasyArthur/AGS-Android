package com.alpha.upnp.parser;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.alpha.setting.alarm.AlarmItemContent;

public class AlarmListParser {

	private AlarmListHandler dataHandler;
	private XMLReader xr;
	public AlarmListParser(){
		
		try{
		
			SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    xr = sp.getXMLReader();  

		    dataHandler = new AlarmListHandler();   
		    xr.setContentHandler(dataHandler);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<AlarmItemContent> parse(String xml){
		
		try{
			xr.parse(new InputSource(new StringReader(xml)));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dataHandler.getData();
		
	}
	
}

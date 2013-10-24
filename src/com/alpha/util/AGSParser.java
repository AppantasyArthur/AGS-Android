package com.alpha.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnp.parser.TrackHanlder;

public class AGSParser {

	public static List<TrackDO> _parseTrack(String xml){
		List<TrackDO> data = null;  
		  // sax stuff   
		  try { 			  
			SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    XMLReader xr = sp.getXMLReader();  

		    TrackHanlder dataHandler = new TrackHanlder();   
		    xr.setContentHandler(dataHandler);   
		    
		    if(true){		
//		    	xml = StringEscapeUtils.unescapeHtml4(xml);
		    	xr.parse(new InputSource(new StringReader(xml))); 
			    data = dataHandler.getData();  
		    } 
		  } catch(ParserConfigurationException pce) {   
		    Log.e("SAX XML", "sax parse error", pce);   
		  } catch(SAXException se) {   
		    Log.e("SAX XML", "sax error", se);   
		  } catch(IOException ioe) {   
		    Log.e("SAX XML", "sax parse io error", ioe);   
		  } catch(Exception e) {
			  e.printStackTrace();
		  }  
		  return data;   
	}
	
}

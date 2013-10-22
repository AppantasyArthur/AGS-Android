package com.alpha.upnp.parser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alpha.setting.alarm.AlarmItemContent;

public class AlarmListHandler extends DefaultHandler {
	
	// this holds the data   
	  private ArrayList<AlarmItemContent> _data;   
	  private Integer position; // = -1;
	  
	  /**  
	   * Returns the data object  
	   *  
	   * @return  
	   */   
	  public ArrayList<AlarmItemContent> getData() {   
	    return _data;   
	  }   
	  
	  /**  
	   * This gets called when the xml document is first opened  
	   *  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void startDocument() throws SAXException {
		  _data = new ArrayList<AlarmItemContent>();
		  position = -1;
	  }   
	  
	  /**  
	   * Called when it's finished handling the document  
	   *  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void endDocument() throws SAXException {   
	  
	  }
	  
	  /**  
	   * This gets called at the start of an element. Here we're also setting the booleans to true if it's at that specific tag. (so we  
	   * know where we are)  
	   *  
	   * @param namespaceURI  
	   * @param localName  
	   * @param qName  
	   * @param atts  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {   
	  
		  //String val = atts.getValue("val");
		  //Log.d("startElement", localName + " : " + val);
		 
		  if(localName.equalsIgnoreCase(AlarmItemContent.TAG)){
			
			  AlarmItemContent vo = new AlarmItemContent();
			  
			  //if(atts.getValue(AlarmItemContent.prtPosition) != null)
				  vo.setPosition( ++position ); // atts.getValue(AlarmItemContent.prtPosition)
			  vo.setState(atts.getValue(AlarmItemContent.prtState)); // ALARMSTATETYPE_E_ON
			  vo.setTime(atts.getValue(AlarmItemContent.prtTime));
			  vo.setUri(atts.getValue(AlarmItemContent.prtURI));
			  vo.setMetaData(atts.getValue(AlarmItemContent.prtMetaData));
			  vo.setVolume(Integer.parseInt(atts.getValue(AlarmItemContent.prtVolume)));
			  vo.setFreaquency(atts.getValue(AlarmItemContent.prtFrequency)); // ALARMFREQUENCYTYPE_E_ONCE
			  
			  _data.add(vo);
			  
		  } 
		  
	  }   
	  
	  /**  
	   * Called at the end of the element. Setting the booleans to false, so we know that we've just left that tag.  
	   *  
	   * @param namespaceURI  
	   * @param localName  
	   * @param qName  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void endElement(String namespaceURI, String localName, String qName) throws SAXException {   
	    
	  }   
	  
	  /**  
	   * Calling when we're within an element. Here we're checking to see if there is any content in the tags that we're interested in  
	   * and populating it in the Config object.  
	   *  
	   * @param ch  
	   * @param start  
	   * @param length  
	   */   
	  @Override   
	  public void characters(char ch[], int start, int length) {   
		  
	  }

}

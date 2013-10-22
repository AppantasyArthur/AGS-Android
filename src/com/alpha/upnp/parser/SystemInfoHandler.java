package com.alpha.upnp.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SystemInfoHandler extends DefaultHandler {
	
	// this holds the data   
	  private SystemInfoVO _data;   
	  
	  /**  
	   * Returns the data object  
	   *  
	   * @return  
	   */   
	  public SystemInfoVO getData() {   
	    return _data;   
	  }   
	  
	  /**  
	   * This gets called when the xml document is first opened  
	   *  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void startDocument() throws SAXException {
		  _data = new SystemInfoVO();
	  }   
	  
	  /**  
	   * Called when it's finished handling the document  
	   *  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void endDocument() throws SAXException {   
	  
	  }
	  
	  private boolean inFV;
	  private boolean inSN;
	 
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
		 
		  if(localName.equalsIgnoreCase(SystemInfoVO.TAG_FIRMWARE_VERSION)){
			  inFV = true;
		  } else if(localName.equalsIgnoreCase(SystemInfoVO.TAG_SERIAL_NUMBER)){
			  inSN = true;
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
	    
		  String chars = new String(ch, start, length);   
		  chars = chars.trim();   
		  
		  if(inFV){
			  _data.setFirmwareVersion(chars);
			  inFV = false;
		  } else if(inSN){
			  _data.setSerialNumber(chars);
			  inSN = false;
		  } 
		  
	  }

}

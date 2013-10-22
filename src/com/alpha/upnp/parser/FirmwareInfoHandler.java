package com.alpha.upnp.parser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FirmwareInfoHandler extends DefaultHandler {
	
	// this holds the data   
	  private ArrayList<FirmwareInfoVO> _data;   
	  
	  /**  
	   * Returns the data object  
	   *  
	   * @return  
	   */   
	  public ArrayList<FirmwareInfoVO> getData() {   
	    return _data;   
	  }   
	  
	  /**  
	   * This gets called when the xml document is first opened  
	   *  
	   * @throws SAXException  
	   */   
	  @Override   
	  public void startDocument() throws SAXException {
		  _data = new ArrayList<FirmwareInfoVO>();
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
		 
		  if(localName.equalsIgnoreCase(FirmwareInfoVO.TAG_FIRMWARE_INFO)){
			
			  FirmwareInfoVO vo = new FirmwareInfoVO();
			  vo.setPath(atts.getValue(FirmwareInfoVO.PRPTY_PATH));
			  vo.setNewVersion(atts.getValue(FirmwareInfoVO.PRPTY_NEW_VERSION));
			  vo.setCurVersion(atts.getValue(FirmwareInfoVO.PRPTY_CUR_VERSION));
			  vo.setType(atts.getValue(FirmwareInfoVO.PRPTY_TYPE));
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

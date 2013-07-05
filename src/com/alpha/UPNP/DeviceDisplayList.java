package com.alpha.UPNP;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.SubscriptionCallback;
import org.teleal.cling.model.gena.CancelReason;
import org.teleal.cling.model.gena.GENASubscription;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.DeviceDetails;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.state.StateVariableValue;
import org.teleal.cling.model.types.DeviceType;
import org.teleal.cling.model.types.UDAServiceId;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import android.content.Context;
import android.util.Log;
import com.FAM.SETTING.Play_IButton_Listner;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter_Queqe_Listner;
import com.FI.SETTING.MusicInfo_Listner;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_Listner;
import com.FS.SETTING.FS_SPEAKER_ExpandableListAdapter_Listner;
import com.alpha.upnpui.FragmentActivity_Main;
import com.appantasy.androidapptemplate.event.lastchange.ItemDO;
import com.appantasy.androidapptemplate.event.lastchange.ItemHandler;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeDO;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeHandler;
import com.tkb.tool.MLog;

public class DeviceDisplayList {
	private Context context;
	private static String TAG = "DeviceDisplayList";
	private MLog mlog = new MLog();
	
	private DeviceDisplay ChooseMediaRenderer;
	private List<DeviceDisplay> DDList;
	private List<DeviceDisplay> MRList;//MediaRenderer List;
	private List<DeviceDisplay> MSList;//MediaServer List;
	private FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner;
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;
	private Play_IButton_Listner PIListner;
	private MusicInfo_Listner MIListner;
	private FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner;
	private SubscriptionCallback Device_StateCallBack;
	
	public DeviceDisplayList(Context context){
		this.context = context;
		this.mlog.LogSwitch = true;
		ChooseMediaRenderer = null;
		DDList = new ArrayList<DeviceDisplay>();
		MRList = new ArrayList<DeviceDisplay>();
		MSList = new ArrayList<DeviceDisplay>();
	}
	public void addDeviceDisplay(DeviceDisplay dd) {
		DeviceType deviceType = dd.getDevice().getType();
		if(MRList.indexOf(dd)!=-1||MSList.indexOf(dd)!=-1||DDList.indexOf(dd)!=-1){
			mlog.info(TAG, "addDeviceDisplay = return");
			return;
		}
		if(deviceType.getType().toString().equals("MediaRenderer")){
			//MediaRenderer List
			MRList.add(dd);
			if(FSELAListner!=null){
				FSELAListner.AddMediaRenderer(dd);
			}
		}else if(deviceType.getType().toString().equals("MediaServer")){
			//MediaServer List
			MSList.add(dd);
			if(FMLBAListner!=null){
				FMLBAListner.AddMediaServer(dd);
			}
		}else{
			DDList.add(dd);
		}
		DeviceDetails DeviceDetails = dd.getDevice().getDetails();
		mlog.info(TAG, "addDeviceDisplay = "+DeviceDetails.getFriendlyName());
	}
	public void removeDeviceDisplay(DeviceDisplay dd) {
		DeviceType deviceType = dd.getDevice().getType();
		if(deviceType.getType().toString().equals("MediaRenderer")){
			if(FSELAListner!=null){
				FSELAListner.RemoveMediaRenderer(dd);
			}
			mlog.info(TAG, "removeDeviceDisplay = MR");
			MRList.remove(dd);
		}else if(deviceType.getType().toString().equals("MediaServer")){
			if(FMLBAListner!=null){
				FMLBAListner.RemoveMediaServer(dd);
			}
			mlog.info(TAG, "removeDeviceDisplay = MS");
			MSList.remove(dd);
		}else{
			DDList.remove(dd);
			mlog.info(TAG, "removeDeviceDisplay = DD");
		}
	}
	public void setChooseMediaRenderer(DeviceDisplay mediaRenderer){
		if(this.ChooseMediaRenderer==mediaRenderer){
			return;			
		}
		this.ChooseMediaRenderer = mediaRenderer;
		//取得upnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		Service StateService = null;	
		//檢查 MR_Device
		if(this.ChooseMediaRenderer.getDevice()!=null){
			//取得device 的 "AVTransport" service
			StateService = this.ChooseMediaRenderer.getDevice().findService( new UDAServiceId("AVTransport"));
			for(int i=0;i<this.ChooseMediaRenderer.getDevice().findServices().length;i++){
				mlog.info(TAG,"status ="+this.ChooseMediaRenderer.getDevice().findServices()[i].toString());
			}
		}else{
			return;
		}	
		//移除舊的Device_StateCallBack
		if(Device_StateCallBack!=null){
			Device_StateCallBack.end();
		}
		if(MIListner!=null){
			MIListner.ClearMusicInfo_State();
		}
		//設定StateCallBack
		Device_StateCallBack = new SubscriptionCallback(StateService){
			@Override
			protected void ended(GENASubscription arg0, CancelReason arg1, UpnpResponse arg2) {
				
				mlog.info(TAG,"status ="+arg2);
			}

			@Override
			protected void established(GENASubscription arg0) {
				mlog.info(TAG,"status = established");
				Map<String, StateVariableValue> values = arg0.getCurrentValues();
				StateVariableValue status = values.get("LastChange");
				 
				for(Map.Entry<String, StateVariableValue>value:values.entrySet()){
					mlog.info(TAG, "even = "+value.getKey()+"  value = "+value.getValue().toString());
				}
			}

			@Override
			protected void eventReceived(GENASubscription arg0) {				
				 Map<String, StateVariableValue> values = arg0.getCurrentValues();
				 StateVariableValue status = values.get("LastChange");
				 if(status==null){
					 return;
				 }
				 mlog.info(TAG, "==========EVEN STAR==========");
				 mlog.info(TAG, "LastChange valeu= "+status.toString());
				 mlog.info(TAG, "==========EVEN END==========");				 
				 LastChangeDO lastChangeDO = _parseLastChangeEvent(status.toString());
				
				 //播放狀態
				 String MR_State = lastChangeDO.getTransportState();				
				 if(MR_State!=null&&!MR_State.equals("")&&PIListner!=null){
					 PIListner.SetPlay_IButton_State(MR_State);
					 mlog.info(TAG, "==========EVEN STAR==========");
					 mlog.info(TAG, "lastChangeDO MR_State= "+MR_State);
					 mlog.info(TAG, "============End=============");					 
				 }	
				 String Item_MetaData = lastChangeDO.getCurrentTrackEmbeddedMetaData();	
				 ItemDO itemDO =null;
				 if(Item_MetaData!=null&&!Item_MetaData.equals("")){
					 mlog.info(TAG, "============Start=============");					 
					 mlog.info(TAG, "Item_MetaData = "+Item_MetaData);
					 itemDO =  _parseItem(Item_MetaData);
					 mlog.info(TAG, "============End=============");
				 }
				 if(itemDO!=null){
					 MIListner.SetMusicInfo_State(itemDO.getTitle(), itemDO.getArtist(), itemDO.getAlbum(), itemDO.getGenre(),itemDO.getAlbumURI());
					 mlog.info(TAG, "============Start=============");
				 	 mlog.info(TAG, "Title = "+itemDO.getTitle());							
					 mlog.info(TAG, "Artist = "+itemDO.getArtist());
					 mlog.info(TAG, "Album = "+itemDO.getAlbum());
					 mlog.info(TAG, "Genre = "+itemDO.getGenre());	
					 mlog.info(TAG, "AlbumURI = "+itemDO.getAlbumURI());										
					 mlog.info(TAG, "============End=============");
				 }

				 mlog.info(TAG, "==========EVEN STAR==========");
				 mlog.info(TAG, "lastChangeDO MR_State= "+MR_State);
				 mlog.info(TAG, "lastChangeDO valeu= "+lastChangeDO.getCurrentTrackEmbeddedMetaData());
				 mlog.info(TAG, "lastChangeDO valeu= "+lastChangeDO.getRelativeTimePosition());
				 mlog.info(TAG, "lastChangeDO valeu= "+lastChangeDO.getCurrentTrackDuration());
				 mlog.info(TAG, "==========EVEN END==========");	
			}

			@Override
			protected void eventsMissed(GENASubscription arg0, int arg1) {				
				mlog.info(TAG,"status = eventsMissed");
			}

			@Override
			protected void failed(GENASubscription arg0, UpnpResponse arg1,	Exception arg2, String arg3) {
				// TODO Auto-generated method stub
				mlog.info(TAG,"status failed="+arg3);
			}
		};		
		upnpServer.getControlPoint().execute(Device_StateCallBack);
		
		//設定QueqeCallBack
		//Queqe 歸零
		if(queqe_listner!=null){
			queqe_listner.ClearQueqeList();
		}
	}
	private LastChangeDO _parseLastChangeEvent(String xml) {   
		LastChangeDO data = null;   

		  // sax stuff   
		  try {   

		    SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    XMLReader xr = sp.getXMLReader();   

		    LastChangeHandler dataHandler = new LastChangeHandler();   
		    xr.setContentHandler(dataHandler);   
		    
		    if(true){		    	
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
	private ItemDO _parseItem(String xml) {   
		ItemDO data = null;   

		  // sax stuff   
		  try { 
			  
			SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    XMLReader xr = sp.getXMLReader();  

		    ItemHandler dataHandler = new ItemHandler();   
		    xr.setContentHandler(dataHandler);   
		    
		    if(true){
		    	xml = xml.replace(" dlna:profileID=\"JPEG_TN\"", "");
		    	xml = xml.replace("pv:", ""); 
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
	public DeviceDisplay getChooseMediaRenderer(){
		return this.ChooseMediaRenderer;
	}
	public List<DeviceDisplay> getDeviceDisplayList(){
		return DDList;
	}
	public List<DeviceDisplay> getMediaRendererList(){
		return MRList;
	}
	public List<DeviceDisplay> getMediaServerList(){
		return MSList;
	}
	public void Stop_Device_StateCallBack(){
		if(this.Device_StateCallBack!=null){
			this.Device_StateCallBack.end();
		}		
	}
	public void setSpeakerListner(FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner){
		this.FSELAListner = FSELAListner;
	}
	public void setMusicListner(FM_Music_ListView_BaseAdapter_Listner FMLBAListner){
		this.FMLBAListner = FMLBAListner;
	}
	public void setPlay_IButton_Listner(Play_IButton_Listner PIListner){
		this.PIListner = PIListner;
	}
	public void setMusicInfo_Listner(MusicInfo_Listner MIListner){
		this.MIListner = MIListner;
	}
	public void setQueqe_Listner(FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner) {
		this.queqe_listner = queqe_listner;
	}
}

package com.alpha.UPNP;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.StringEscapeUtils;
import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.controlpoint.SubscriptionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.gena.CancelReason;
import org.teleal.cling.model.gena.GENASubscription;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
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

import com.FAM.SETTING.PlayMode_IButton_Listner;
import com.FAM.SETTING.Play_IButton_Listner;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter_Queqe_Listner;
import com.FI.SETTING.MusicInfo_Listner;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_Listner;
import com.FS.SETTING.FS_SPEAKER_ExpandableListAdapter_Listner;
import com.FS.SETTING.RunState_TextView_Listner;
import com.FS.SETTING.RunState_TextView_Listner2;
import com.alpha.upnpui.FragmentActivity_Main;
import com.appantasy.androidapptemplate.event.lastchange.GroupHandler;
import com.appantasy.androidapptemplate.event.lastchange.GroupVO;
import com.appantasy.androidapptemplate.event.lastchange.ItemDO;
import com.appantasy.androidapptemplate.event.lastchange.ItemHandler;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeDO;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeHandler;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.appantasy.androidapptemplate.event.lastchange.TrackHanlder;
import com.tkb.tool.MLog;

public class DeviceDisplayList {
	private Context context;
	private static String TAG = "DeviceDisplayList";
	private MLog mlog = new MLog();
	
	private DeviceDisplay ChooseMediaRenderer;
	private List<DeviceDisplay> DDList;
	private List<DeviceDisplay> MRList;//MediaRenderer List;
	private GroupList groupList;//MediaRenderer List FS&FI顯示使用;
	private List<DeviceDisplay> MSList;//MediaServer List;
	private FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner;
	private RunState_TextView_Listner2 runState_TextView_Listner2;
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;
	private Play_IButton_Listner PIListner;
	private Play_IButton_Listner Info_PIListner;
	private PlayMode_IButton_Listner PMIListner;
	private PlayMode_IButton_Listner Info_PMIListner;
	private MusicInfo_Listner MIListner;
	private FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner;
	
	public DeviceDisplayList(Context context){
		this.context = context;
		this.mlog.LogSwitch = true;
		ChooseMediaRenderer = null;
		DDList = new ArrayList<DeviceDisplay>();
		MRList = new ArrayList<DeviceDisplay>();
		MSList = new ArrayList<DeviceDisplay>();
		groupList = new GroupList();
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
			
			//檢查是否有Group
			DeviceType deviceType_f = new DeviceType("schemas-upnp-org", "DeviceManager");
			Device[] devices = dd.getDevice().findDevices(deviceType_f);
			
			EventHandler eventHandler = new EventHandler(dd);
			eventHandler.RegistInfoEvent();	
			dd.setEventHandler(eventHandler);
			
			if(devices!=null&&devices.length>0){
				//有Group
				Device MMDevice = devices[0];
				mlog.info(TAG, "Namespace = "+MMDevice.getType().getNamespace());
				mlog.info(TAG, "Type = "+MMDevice.getType().getType());
				mlog.info(TAG, "Version = "+MMDevice.getType().getVersion());
				dd.setMMDevice(MMDevice);	
				//註冊Group Listner	
				eventHandler.RegistGroupEvent();
				//險查Device目前狀態
				eventHandler.checkMasterORSingle();	
			}else{
				//沒有Group	
				//加入GroupList 是否加入成功
				if(groupList.AddDeviceDisplay(dd)){
					//通知FS 刷新
					if(FSELAListner!=null){
						FSELAListner.SetPositionChange();
					}		
					//通知MI更新
					if(MIListner!=null){
						MIListner.MediaRendererCountChange();
					}
				}
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
			if(groupList.RemoveDeviceDisplay(dd)){
				//通知FS 刷新
				if(FSELAListner!=null){
					FSELAListner.SetPositionChange();
				}	
				//通知MI 刷新
				if(MIListner!=null){
					MIListner.MediaRendererCountChange();
				}
			}
			if(dd.equals(ChooseMediaRenderer)){
				DeviceDisplayList.this.setChooseMediaRenderer(null);
			}			
			MRList.remove(dd);
			mlog.info(TAG, "removeDeviceDisplay = MR");
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
		
		//設定QueqeCallBack
		//Queqe 歸零
		if(queqe_listner!=null){
			queqe_listner.ClearQueqeList();
		}		
		if(MIListner!=null){
			MIListner.ClearMusicInfo_State();
			MIListner.SetPositionChange();
		}
		
		//通知 FS 刷新
		if(FSELAListner!=null){
			FSELAListner.SetPositionChange();
		}
		if(mediaRenderer==null){
			return;
		}
		
		EventHandler eventHandler = mediaRenderer.getEventHandler();		
		if (eventHandler!=null){
			//資料設定
			eventHandler.UpdataALL();
		}
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
	public List<DeviceDisplay> getGroupList(){
		return this.groupList.GetGroupList();
	}
	public Device GetMMDevice(String MMDeviceUDN){
		for(DeviceDisplay deviceDisplay :MRList){
			Device MMDevice = deviceDisplay.getMMDevice();
			mlog.info(TAG, "MMDeviceUDN = "+MMDeviceUDN);
			if(MMDevice!=null){
				mlog.info(TAG, "MMDeviceUDN2 = "+MMDevice.getIdentity().getUdn());
			}
			
			if(MMDevice!=null&&MMDevice.getIdentity().getUdn().toString().equals(MMDeviceUDN)){
				return MMDevice;
			}
		}
		return null;
	}
	public DeviceDisplay GetDeviceDisplayByUDN(String MMDeviceUDN){
		for(DeviceDisplay deviceDisplay :MRList){
			Device device = deviceDisplay.getMMDevice();
			mlog.info(TAG, "MMDeviceUDN = "+MMDeviceUDN);
			if(device!=null){
				mlog.info(TAG, "MMDeviceUDN2 = "+device.getIdentity().getUdn());
			}
			
			if(device!=null&&device.getIdentity().getUdn().toString().equals(MMDeviceUDN)){
				return deviceDisplay;
			}
		}
		return null;
	}
	public void setSpeakerListner(FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner){
		this.FSELAListner = FSELAListner;
	}
	public void setRunState_TextView_Listner2(RunState_TextView_Listner2 runState_TextView_Listner2){
		this.runState_TextView_Listner2 = runState_TextView_Listner2;
	}
	public void setMusicListner(FM_Music_ListView_BaseAdapter_Listner FMLBAListner){
		this.FMLBAListner = FMLBAListner;
	}
	public void setPlay_IButton_Listner(Play_IButton_Listner PIListner){
		this.PIListner = PIListner;
	}
	public void setInfo_Play_IButton_Listner(Play_IButton_Listner Info_PIListner){
		this.Info_PIListner = Info_PIListner;
	}
	public void setPlayMode_IButton_Listner(PlayMode_IButton_Listner PMIListner){
		this.PMIListner = PMIListner;
	}
	
	public void setInfo_PlayMode_IButton_Listner(PlayMode_IButton_Listner Info_PMIListner){
		this.Info_PMIListner = Info_PMIListner;
	}
	public void setMusicInfo_Listner(MusicInfo_Listner MIListner){
		this.MIListner = MIListner;
	}
	public void setQueqe_Listner(FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner) {
		this.queqe_listner = queqe_listner;
	}
	public class EventHandler{
		private DeviceDisplay deviceDisplay;
		private SubscriptionCallback Device_DisplayGrouCallBack;
		private SubscriptionCallback Device_DisplayInfoCallBack;
		private AndroidUpnpService upnpServer;	
		
		public EventHandler(DeviceDisplay deviceDisplay){
			this.deviceDisplay = deviceDisplay;
			//取得upnpServer
			this.upnpServer = ((FragmentActivity_Main)context).GETUPnPService();			
		}		
		//建查Device目前狀態 
		public void checkMasterORSingle(){
			Device MMDevice = this.deviceDisplay.getMMDevice();			
			Service GroupService = MMDevice.findService(new UDAServiceId("Group"));
			if(GroupService!=null){
				Action GetDisplayInfoAction = GroupService.getAction("GetDisplayInfo");				
				if(GetDisplayInfoAction!=null){				
					ActionInvocation ai = new ActionInvocation(GetDisplayInfoAction,null);
					ActionCallback GetDisplayInfoActionCallBack = new ActionCallback(ai){
						@Override
						public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
							//檢查連線失敗不加入陣列
						}
						@Override
						public void success(ActionInvocation arg0) {							
							ActionArgument DisplayInfo = arg0.getAction().getOutputArgument("DisplayInfo");
							ActionArgumentValue actionArgumentValue = arg0.getOutput(DisplayInfo);
							mlog.info(TAG, "actionArgumentValue = "+actionArgumentValue); 
							GroupVO groupVO = _parseGroup(actionArgumentValue.toString());
							mlog.info(TAG, "GetDisplayInfoActionCallBack  = "+actionArgumentValue.toString());
							mlog.info(TAG, "GetDisplayInfoActionCallBack  = "+groupVO.getName());
							mlog.info(TAG, "GetDisplayInfoActionCallBack success");
							deviceDisplay.setGroupVO(groupVO);
							if(!groupVO.isSlave()){
								//不是Slave 加入陣列
								//加入GroupList 是否加入成功
								if(groupList.AddDeviceDisplay(deviceDisplay)){								
									//通知FS 刷新
									if(FSELAListner!=null){									
										FSELAListner.SetPositionChange();								
									}
									//通知MI 刷新
									if(MIListner!=null){
										MIListner.MediaRendererCountChange();
									}
								}
							}							
						}											
					};
					upnpServer.getControlPoint().execute(GetDisplayInfoActionCallBack);	
				}
			}
		}
		//註冊Device Group狀態EVENT
		public void RegistGroupEvent(){
			if(Device_DisplayGrouCallBack!=null){
				Device_DisplayGrouCallBack.end();
			}			
			Device MMDevice = this.deviceDisplay.getMMDevice();			
			Service GroupService = MMDevice.findService(new UDAServiceId("Group"));
			if(GroupService!=null){
				Device_DisplayGrouCallBack = new SubscriptionCallback(GroupService){
					@Override
					protected void ended(GENASubscription arg0, CancelReason arg1, UpnpResponse arg2) {
					}
					@Override
					protected void established(GENASubscription arg0) {
					}
					@Override
					protected void eventReceived(GENASubscription arg0) {	
						Map<String, StateVariableValue> values = arg0.getCurrentValues();
						StateVariableValue status = values.get("DisplayInfo");
						mlog.info(TAG,"FriendlyName = "+EventHandler.this.deviceDisplay.getDevice().getDetails().getFriendlyName());
						for(Map.Entry<String, StateVariableValue>value:values.entrySet()){
							mlog.info(TAG, "even = "+value.getKey()+"  value = "+value.getValue().toString());
						}
						if(status!=null){
							GroupVO groupVO = _parseGroup(status.toString());							
							if(groupVO.isSlave()){
								//even 通知  isSlave
								//移除Slave
								groupList.RemoveDeviceDisplay(EventHandler.this.deviceDisplay);
								//檢查是否為當前選擇
								if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									DeviceDisplayList.this.setChooseMediaRenderer(null);
								}	
								//通知 FS 刷新
								if(FSELAListner!=null){														
									FSELAListner.SetPositionChange();
								}
								//通知 MI 刷新
								if(MIListner!=null){
									MIListner.MediaRendererCountChange();
								}
							}else{
								//even 通知 not isSlave
								//更新GroupEventHandler狀態
								EventHandler.this.deviceDisplay.setGroupVO(groupVO);
								
								//加入GroupList 是否加入成功
								if(groupList.AddDeviceDisplay(EventHandler.this.deviceDisplay)){
									//通知 FS 刷新
									if(FSELAListner!=null){					
										FSELAListner.SetPositionChange();	
									}
									//通知 MI 刷新
									if(MIListner!=null){
										MIListner.MediaRendererCountChange();
									}
								}							
							}
						}					
					}
	
					@Override
					protected void eventsMissed(GENASubscription arg0, int arg1) {					
					}	
					@Override
					protected void failed(GENASubscription arg0, UpnpResponse arg1,	Exception arg2, String arg3) {
					}
				};
				upnpServer.getControlPoint().execute(Device_DisplayGrouCallBack);
			}
		}
		
		
		private String MR_State;
		private String MR_PlayMode;	
		private String metaData_Title;
		private ItemDO item_DO;
		
		private List<TrackDO> trackList;
		 //Play狀態
		private void UpdataPlayMode(){
			if(MR_State!=null&&!MR_State.equals("")&&PIListner!=null){
				//Phone Speaker Play_IButton_Listner&& PAD MAIN Play_IButton_Listner
				 PIListner.SetPlay_IButton_State(MR_State);
				 mlog.info(TAG, "==========EVEN STAR==========");
				 mlog.info(TAG, "lastChangeDO MR_State= "+MR_State);
				 mlog.info(TAG, "============End=============");
				 //Phone Info Play_IButton_Listner
				 if(Info_PIListner!=null){
					 Info_PIListner.SetPlay_IButton_State(MR_State);
				 }							
			 } 				 			 
		}
		 //CurrentPlayMode
		private void UpdataCurrentPlayMode(){
			if(MR_PlayMode!=null&&!MR_PlayMode.equals("")&&PMIListner!=null){
				//Phone Speaker PlayMode_IButton_Listner&& PAD MAIN PlayMode_IButton_Listner
				 PMIListner.SetPlayMode_IButton_State(MR_PlayMode);
				 mlog.info(TAG, "==========EVEN STAR==========");
				 mlog.info(TAG, "lastChangeDO MR_PlayMode= "+MR_PlayMode);
				 mlog.info(TAG, "============End=============");
				//Phone Info PlayMode_IButton_Listner
				 if(Info_PMIListner!=null){
					 Info_PMIListner.SetPlayMode_IButton_State(MR_PlayMode);
				 }
			 }				 		
		}
		//MI_Info
		private void UpdataItem_MetaData(){			
			//info
			if(item_DO!=null){				
				MIListner.SetMusicInfo_State(item_DO.getTitle(), item_DO.getArtist(), item_DO.getAlbum(), item_DO.getGenre(),item_DO.getAlbumURI());
				mlog.info(TAG, "============Start=============");
			 	mlog.info(TAG, "Title = "+item_DO.getTitle());							
				mlog.info(TAG, "Artist = "+item_DO.getArtist());
				mlog.info(TAG, "Album = "+item_DO.getAlbum());
				mlog.info(TAG, "Genre = "+item_DO.getGenre());	
				mlog.info(TAG, "AlbumURI = "+item_DO.getAlbumURI());										
				mlog.info(TAG, "============End=============");
			}			 		 
		}	
		//QueueList
		private void UpdataQueueList(){
			if(trackList!=null&&queqe_listner!=null){
				 mlog.info(TAG, "trackList size = "+trackList.size());	
				 queqe_listner.AddQueqeList(trackList);
			}
		}
		private void UpdateRunState_TextView(){			
			if(runState_TextView_Listner2!=null){
				runState_TextView_Listner2.SetRunState_TextView_State(MR_State, metaData_Title,EventHandler.this.deviceDisplay);
			}
		}
		public void UpdataALL(){
			UpdataPlayMode();
			UpdataCurrentPlayMode();
			UpdataItem_MetaData();
			UpdataQueueList(); 
		}
		
		public String GetTransportState(){
			return MR_State;
		}
		public String GetMetaDataTitle(){			
			if(metaData_Title!=null){		
				return metaData_Title;
			}else{
				return "";
			}
		}
		public void RegistInfoEvent(){		
			if(Device_DisplayInfoCallBack!=null){
				Device_DisplayInfoCallBack.end();
			}			
			Device device = this.deviceDisplay.getDevice();
			//取得 AVTransportService
			Service AVTransportService = device.findService(new UDAServiceId("AVTransport"));
			for(int i=0;i<device.findServices().length;i++){
				mlog.info(TAG,"status ="+device.findServices()[i].toString());				
			}
			
			if(AVTransportService!=null){
				//設定StateCallBack
				Device_DisplayInfoCallBack = new SubscriptionCallback(AVTransportService){
					@Override
					protected void ended(GENASubscription arg0, CancelReason arg1, UpnpResponse arg2) {
						mlog.info(TAG,"status end ="+arg2);
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
						 for(Map.Entry<String, StateVariableValue> value:values.entrySet()){
							 mlog.info(TAG, "==========EVEN STAR==========");
							 mlog.info(TAG, "key= "+value.getKey().toString());
							 mlog.info(TAG, "==========EVEN END==========");
						 }
						 StateVariableValue status = values.get("LastChange");
						 
						 if(status!=null){				 
							 mlog.info(TAG, "==========EVEN STAR==========");
							 mlog.info(TAG, "LastChange valeu= "+status.toString());
							 mlog.info(TAG, "==========EVEN END==========");
							 //儲存資料
							 LastChangeDO lastChangeDO = _parseLastChangeEvent(status.toString());
							 
							 if(lastChangeDO.getTransportState()!=null){
								 MR_State = lastChangeDO.getTransportState();
								 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									 UpdataPlayMode();
								 }
							 }
							 if(lastChangeDO.getCurrentPlayMode()!=null){								 
								 MR_PlayMode = lastChangeDO.getCurrentPlayMode();
								 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									 UpdataCurrentPlayMode();
								 }
							 }						 
							 if(lastChangeDO.getAVTransportURIMetaData()!=null){								 
								 String Item_MetaData = lastChangeDO.getAVTransportURIMetaData();
								 if(Item_MetaData!=null&&!Item_MetaData.equals("")){
										mlog.info(TAG, "============Start=============");					 
										mlog.info(TAG, "Item_MetaData = "+Item_MetaData);
										item_DO =  _parseItem(Item_MetaData);
										metaData_Title = item_DO.getTitle();
										mlog.info(TAG, "============End=============");
										mlog.info(TAG, "============Start=============");	
										mlog.info(TAG, "==item_MetaData=="+item_DO.getTitle());
										mlog.info(TAG, "==item_MetaData=="+item_DO.getGenre());	
										mlog.info(TAG, "==item_MetaData=="+item_DO.getAlbum());	
										mlog.info(TAG, "==item_MetaData=="+item_DO.getAlbumURI());	
										mlog.info(TAG, "============End=============");
									}								 
								 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									 UpdataItem_MetaData();
								 }
							 }								 
							 if(lastChangeDO.getTransportState()!=null||lastChangeDO.getAVTransportURIMetaData()!=null){
								 UpdateRunState_TextView();
							 }							 
						 }
						 //Queue
						 StateVariableValue q_Status = values.get("TracksInQueue");
						 if(q_Status!=null){
							 mlog.info(TAG, "==========EVEN STAR==========");
							 mlog.info(TAG, "Queue valeu= "+q_Status.toString());
							 mlog.info(TAG, "==========EVEN END==========");
							 
							 trackList = _parseTrack(q_Status.toString());
							 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
								 UpdataQueueList(); 
							 }							
						 }				
					}					
					@Override
					protected void eventsMissed(GENASubscription arg0, int arg1) {				
						mlog.info(TAG,"status = eventsMissed");
					}
					@Override
					protected void failed(GENASubscription arg0, UpnpResponse arg1,	Exception arg2, String arg3) {
						mlog.info(TAG,"status failed="+arg3);
					}
				};		
				upnpServer.getControlPoint().execute(Device_DisplayInfoCallBack);
			}
			
		}
	}
	private GroupVO _parseGroup(String xml){
		GroupVO data = null;   

		  // sax stuff   
		  try { 			  
			SAXParserFactory spf = SAXParserFactory.newInstance();   
		    SAXParser sp = spf.newSAXParser();   
		    XMLReader xr = sp.getXMLReader();  

		    GroupHandler dataHandler = new GroupHandler();   
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
	private List<TrackDO> _parseTrack(String xml){
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

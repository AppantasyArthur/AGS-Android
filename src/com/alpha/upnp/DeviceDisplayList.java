package com.alpha.upnp;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

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
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.state.StateVariableValue;
import org.teleal.cling.model.types.DeviceType;
import org.teleal.cling.model.types.ServiceType;
import org.teleal.cling.model.types.UDAServiceId;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.FAM.SETTING.Music_SeekBar_Listner;
import com.FAM.SETTING.PlayMode_IButton_Listner;
import com.FAM.SETTING.Play_IButton_Listner;
import com.FAM.SETTING.Sound_SeekBar_Listner;
import com.FS.SETTING.FS_SPEAKER_ExpandableListAdapter_Listner;
import com.FS.SETTING.RunState_TextView_Listner2;
import com.alpha.musicinfo.MusicInfoQueueListViewBaseAdapterListener;
import com.alpha.musicinfo.MusicInfo_Listner;
import com.alpha.musicsource.FM_Music_ListView_BaseAdapter_Listner;
import com.alpha.setting.about.AboutSettingSystemInfo;
import com.alpha.setting.alarm.musicbrowsing.AlarmSettingMusicBrowsingAdapterListener;
import com.alpha.setting.rendererlist.FSR_Renderers_ListView_BaseAdapter_Renderer_Listner;
import com.alpha.upnp.parser.GroupHandler;
import com.alpha.upnp.parser.GroupVO;
import com.alpha.upnp.parser.ItemDO;
import com.alpha.upnp.parser.ItemHandler;
import com.alpha.upnp.parser.LastChangeDO;
import com.alpha.upnp.parser.LastChangeHandler;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnp.value.FirmwareUpdateServiceValues;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.util.AGSParser;
import com.tkb.UpnpOverride.ProcessBarListner;
import com.tkb.tool.TKBLog;

public class DeviceDisplayList implements Parcelable  {
	
	private Context context;
	private static String tag = "DeviceDisplayList";
	private TKBLog mlog = new TKBLog();
	
	private static DeviceDisplay mrChoosed;
	private static String positionCurrentTrack;
	public static String getPositionCurrentTrack() {
		return positionCurrentTrack;
	}

	//SeekBar Timer
	private Timer timeSeekBarTimer;
	
	private List<DeviceDisplay> DDList;
	private List<DeviceDisplay> MRList;//全部  MediaRenderer List
	private GroupList groupList;//有Group Service 的  MediaRenderer List
	private List<DeviceDisplay> MSList;//MediaServer List
	//Listners
	private FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner;//Speaker
	private RunState_TextView_Listner2 runState_TextView_Listner2;//Speaker 跑馬燈
	private Music_SeekBar_Listner music_SeekBar_Listner;//TiemSeek
	private Music_SeekBar_Listner Info_Music_SeekBar_Listner;//TiemSeek 
	private Sound_SeekBar_Listner sound_SeekBar_Listner;//GroupSoundSeek
	private Sound_SeekBar_Listner Info_Sound_SeekBar_Listner;//GroupSoundSeek
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;//Music
	private Play_IButton_Listner PIListner;//播放器 按鈕
	private Play_IButton_Listner Info_PIListner;//播放器 按鈕
	private PlayMode_IButton_Listner PMIListner;//PlayMode按鈕
	private PlayMode_IButton_Listner Info_PMIListner;//PlayMode按鈕
	private MusicInfo_Listner MIListner;//Info
	private MusicInfoQueueListViewBaseAdapterListener listenerQueue;//Queue
	private ProcessBarListner processBarListner;
	//Setting Listners
	private FSR_Renderers_ListView_BaseAdapter_Renderer_Listner FSRRRLBListner;
	private AlarmSettingMusicBrowsingAdapterListener FSALMLBListner;
	
	public DeviceDisplayList(Context context){
		this.context = context;
		this.mlog.switchLog = true;
		mrChoosed = null;
		DDList = new ArrayList<DeviceDisplay>();
		MRList = new ArrayList<DeviceDisplay>();
		MSList = new ArrayList<DeviceDisplay>();
		groupList = new GroupList();
	}
	
	public void addDeviceDisplay(DeviceDisplay dd) {
		
		DeviceType deviceType = dd.getDevice().getType();
		
		if(MRList.indexOf(dd)!=-1||MSList.indexOf(dd)!=-1||DDList.indexOf(dd)!=-1){
			mlog.info(tag, "addDeviceDisplay = return");
			return;
		}
		
		//==================Device 分類====================
		if(deviceType.getType().toString().equals("MediaRenderer")){
			//MediaRenderer List
			MRList.add(dd);
			//FSRRRLBListner
			if(FSRRRLBListner!=null){
				FSRRRLBListner.RenderersChange();
			}
			//檢查是否有Group
			DeviceType deviceType_f = new DeviceType("schemas-upnp-org", "DeviceManager");
			Device[] devices = dd.getDevice().findDevices(deviceType_f);
			
			EventHandler eventHandler = new EventHandler(dd);
			eventHandler.RegistInfoEvent();	
			dd.setEventHandler(eventHandler);
			
			if(devices!=null&&devices.length>0){				
				//有Group
				Device MMDevice = devices[0];
				mlog.info(tag, "===================================");
				mlog.info(tag, "Name = "+dd.getDevice().getDetails().getFriendlyName());
				mlog.info(tag, "Namespace = "+dd.getDevice().getType().getType());
				mlog.info(tag, "UDN = "+dd.getDevice().getIdentity().getUdn());
				
				mlog.info(tag, "Name = "+MMDevice.getDetails().getFriendlyName());				
				mlog.info(tag, "Namespace = "+MMDevice.getType().getType());
				mlog.info(tag, "UDN = "+MMDevice.getIdentity().getUdn());
				mlog.info(tag, "===================================");
				mlog.info(tag, "Type = "+MMDevice.getType().getType());
				mlog.info(tag, "Version = "+MMDevice.getType().getVersion());
				dd.setMMDevice(MMDevice);	
				//註冊Group Listner	
				eventHandler.subscribeGroupServiceEvent();
				//險查Device目前狀態
				eventHandler.checkMasterORSingle();
				
				// subscribe System Service
				eventHandler.subscribeSystemServiceEvent();
				eventHandler.subscribeFirmwareUpgradeEvent();
				
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
			if(FSALMLBListner!=null){
				FSALMLBListner.addMediaServer(dd);
			}
		}else{
			DDList.add(dd);
		}
		//==================Device 分類====================
		mlog.info(tag, "addDeviceDisplay = "+dd.getDevice().getDetails().getFriendlyName());
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
			if(dd.equals(mrChoosed)){
				DeviceDisplayList.this.setChooseMediaRenderer(null);
			}			
			MRList.remove(dd);
			//FSRRRLBListner
			if(FSRRRLBListner!=null){
				FSRRRLBListner.RenderersChange();
			}
			mlog.info(tag, "removeDeviceDisplay = MR");
		}else if(deviceType.getType().toString().equals("MediaServer")){
			if(FMLBAListner!=null){
				FMLBAListner.RemoveMediaServer(dd);
			}
			if(FSALMLBListner!=null){
				FSALMLBListner.removeMediaServer(dd);
			}
			mlog.info(tag, "removeDeviceDisplay = MS");
			MSList.remove(dd);
		}else{
			DDList.remove(dd);
			mlog.info(tag, "removeDeviceDisplay = DD");
		}
	}
	//設定所選取的 Renderer
	public void setChooseMediaRenderer(DeviceDisplay mediaRenderer){
		if(this.mrChoosed==mediaRenderer){
			return;			
		}
		this.mrChoosed = mediaRenderer;
		
		//timeSeekBarTimer 歸零
		if(timeSeekBarTimer!=null){
			timeSeekBarTimer.cancel();			
			//畫面歸零
			if(music_SeekBar_Listner!=null){
				music_SeekBar_Listner.SetSeek(0l, 0l, "00:00:00", "00:00:00");
			}
			if(Info_Music_SeekBar_Listner!=null){
				Info_Music_SeekBar_Listner.SetSeek(0l, 0l, "00:00:00", "00:00:00");
			}
		}
		//Group SoundSeekBar 歸零
		if(sound_SeekBar_Listner!=null){
			sound_SeekBar_Listner.SetSeek(0);
			if(Info_Sound_SeekBar_Listner!=null){
				Info_Sound_SeekBar_Listner.SetSeek(0);
			}
		}
		
		//設定QueqeCallBack
		//Queqe 歸零
		if(listenerQueue!=null){
			listenerQueue.cleanQueueList();
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
		//==============設定 timeSeekBarTimer===========================
		/*timeSeekBarTimer = new Timer();
		TimerTask timerTask = new TimerTask(){
			private long systemTime;
			@Override
			public void run() {				
				if(DeviceDisplayList.this.ChooseMediaRenderer==null){
					return;
				}
				systemTime = System.currentTimeMillis();
				
				Service AVTransportService = ChooseMediaRenderer.getDevice().findService(new UDAServiceId("AVTransport"));
				if(AVTransportService!=null){
					GetPositionInfo getPositionInfoAction = new GetPositionInfo(AVTransportService){
						@Override
						public void received(ActionInvocation arg0,	PositionInfo arg1) {
							//防止CallBack Delay
							mlog.info(TAG, "systemTime end = "+System.currentTimeMillis());
							mlog.info(TAG, "systemTime star = "+systemTime);
							if((System.currentTimeMillis()-systemTime)>850){
								return;
							}
							Long secondTotal = arg1.getTrackDurationSeconds();
							Long secondRun = arg1.getTrackElapsedSeconds();	
							
							
							String stringTotal = null;
							String stringRun = null;
							
							if(arg1.getTrackDuration().split(":").length>1){
								stringTotal = arg1.getTrackDuration();
							}else{
								stringTotal = "00:00:00";
							}
							
							long hh = secondRun/60/60;
							long mm = secondRun/60-hh*60;
							long ss = secondRun%60;							
						
							stringRun = String.format("%02d",hh)+":"+ String.format("%02d",mm)+":"+ String.format("%02d",ss);
							
							if(music_SeekBar_Listner!=null){
								music_SeekBar_Listner.SetSeek(secondTotal, secondRun, stringTotal, stringRun);
							}
							if(Info_Music_SeekBar_Listner!=null){
								Info_Music_SeekBar_Listner.SetSeek(secondTotal, secondRun, stringTotal, stringRun);
							}
							mlog.info(TAG, "getTrackDurationSeconds = "+arg1.getTrackDurationSeconds());//秒數總時間
							mlog.info(TAG, "getTrackElapsedSeconds = "+arg1.getTrackElapsedSeconds());//秒數播放時間
							mlog.info(TAG, "getTrackDuration = "+arg1.getTrackDuration());//字串總時間
							mlog.info(TAG, "getAbsTime = "+arg1.getAbsTime());//字串播放時間					
						}

						@Override
						public void failure(ActionInvocation arg0,	UpnpResponse arg1, String arg2) {	
							Log.i("PositionInfo", "failure = "+arg2);
						}					
					};
					((FragmentActivity_Main)context).GETUPnPService().getControlPoint().execute(getPositionInfoAction);
				}
			}			
		};
		timeSeekBarTimer.schedule(timerTask, 1000, 1000);//開始執行*/
		//==============timeSeekBarTimer===========================
	}
	
//	public void cancelTimeSeekBarTimer(){
//		if(timeSeekBarTimer!=null){
//			timeSeekBarTimer.cancel();
//		}
//	}
	//=====================Device 取得=============================
	//取得選取Device
	public static DeviceDisplay getChooseMediaRenderer(){
		return mrChoosed;
	}
	//取得 非MediaRenderer、MediaServer 的所有 Device 清單
	public List<DeviceDisplay> getDeviceDisplayList(){
		return DDList;
	}
	//取得 所有MediaRenderer Device 清單
	public List<DeviceDisplay> getMediaRendererList(){
		return MRList;
	}
	//取得 有Group的MediaRenderer Device 清單
	public List<DeviceDisplay> getGroupList(){
		return this.groupList.GetGroupList();
	}
	//取得 所有MediaServer Device 清單
	public List<DeviceDisplay> getMediaServerList(){
		return MSList;
	}
	//以DeviceManager 的UDN 找尋 Renderer
	public Device GetMMDevice(String MMDeviceUDN){
		for(DeviceDisplay deviceDisplay :MRList){
			Device MMDevice = deviceDisplay.getMMDevice();
			mlog.info(tag, "MMDeviceUDN = "+MMDeviceUDN);
			if(MMDevice!=null){
				mlog.info(tag, "MMDeviceUDN2 = "+MMDevice.getIdentity().getUdn());
			}
			if(MMDevice!=null&&MMDevice.getIdentity().getUdn().toString().equals(MMDeviceUDN)){
				return MMDevice;
			}
		}
		return null;
	}
	//以DeviceManager 的UDN 找尋 Renderer
	public DeviceDisplay GetDeviceDisplayByUDN(String MMDeviceUDN){
		for(DeviceDisplay deviceDisplay :MRList){
			Device device = deviceDisplay.getMMDevice();
			mlog.info(tag, "MMDeviceUDN = "+MMDeviceUDN);
			if(device!=null){
				mlog.info(tag, "MMDeviceUDN2 = "+device.getIdentity().getUdn());
			}
			
			if(device!=null&&device.getIdentity().getUdn().toString().equals(MMDeviceUDN)){
				return deviceDisplay;
			}
		}
		return null;
	}
	//=====================Device=============================
	//===========Listner 設定================
	public void setSpeakerListner(FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner){
		this.FSELAListner = FSELAListner;
	}
	public void setRunState_TextView_Listner2(RunState_TextView_Listner2 runState_TextView_Listner2){
		this.runState_TextView_Listner2 = runState_TextView_Listner2;
	}
	public void setMusic_SeekBar_Listner(Music_SeekBar_Listner music_SeekBar_Listner) {
		this.music_SeekBar_Listner = music_SeekBar_Listner;
	}
	public void setInfo_Music_SeekBar_Listner(Music_SeekBar_Listner infoMusic_SeekBar_Listner) {
		this.Info_Music_SeekBar_Listner = infoMusic_SeekBar_Listner;
	}
	public void setSound_SeekBar_Listner(Sound_SeekBar_Listner sound_SeekBar_Listner) {
		this.sound_SeekBar_Listner = sound_SeekBar_Listner;
	}
	public void setInfo_Sound_SeekBar_Listner(Sound_SeekBar_Listner info_Sound_SeekBar_Listner) {
		this.Info_Sound_SeekBar_Listner = info_Sound_SeekBar_Listner;
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
	public void setQueqe_Listner(MusicInfoQueueListViewBaseAdapterListener queqe_listner) {
		this.listenerQueue = queqe_listner;
	}
	public void setProcessBarListner(ProcessBarListner processBarListner){
		this.processBarListner = processBarListner;
	}
	public void setFSR_Renderers_ListView_BaseAdapter_Renderer_Listner(FSR_Renderers_ListView_BaseAdapter_Renderer_Listner FSRRRLBListner){
		this.FSRRRLBListner = FSRRRLBListner;
	}
	public void setFSAl_Music_ListView_BaseAdapter_Listner(AlarmSettingMusicBrowsingAdapterListener FSALMLBListner){
		this.FSALMLBListner = FSALMLBListner;
	}
	
	private AboutSettingSystemInfo updateerSystemInfo;
	public void setSystemInfoChangedListener(AboutSettingSystemInfo updateerSystemInfo) {
		this.updateerSystemInfo = updateerSystemInfo;
	}
	
	private Handler handlerFirmwareUpgrade;
	public void setFirmwareUpgradeHandler(Handler handler) {
		handlerFirmwareUpgrade = handler;
	}
	
	public void CancelAllListner(){
		
		FSELAListner = null;
		runState_TextView_Listner2 = null;
		music_SeekBar_Listner = null;
		Info_Music_SeekBar_Listner = null;
		FMLBAListner = null;
		PIListner = null;
		Info_PIListner = null;
		PMIListner = null;
		Info_PMIListner = null;
		MIListner = null;
		listenerQueue = null;
		
		FSRRRLBListner = null;
		FSALMLBListner = null;
		
		updateerSystemInfo = null;
		handlerFirmwareUpgrade = null;
		
	}
	//===========Listner================
	
	
	// Event Handler 
	public class EventHandler{
		
		private DeviceDisplay deviceDisplay;
		private SubscriptionCallback Device_DisplayGrouCallBack;
		private SubscriptionCallback Device_DisplayInfoCallBack;
		private AndroidUpnpService upnpServer;	
		
		public EventHandler(DeviceDisplay deviceDisplay){
			this.deviceDisplay = deviceDisplay;
			//取得upnpServer
			this.upnpServer = ((MainFragmentActivity)context).getUPnPService();			
		}		
		//檢查Group GetDisplayInfo Event
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
							GroupVO groupVO = _parseGroup(actionArgumentValue.toString());							
							mlog.info(tag, "GetDisplayInfoActionCallBack  = "+actionArgumentValue.toString());
							mlog.info(tag, "Device Name  = "+groupVO.getName());
							mlog.info(tag, "Device UDN  = "+deviceDisplay.getDevice().getIdentity().getUdn());
							mlog.info(tag, "DeviceManager UDN  = "+groupVO.getUdn());							
							mlog.info(tag, "GetDisplayInfoActionCallBack success");
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
		
		private SubscriptionCallback callbackFirmwareUpgradeServiceSubscription;
		@SuppressWarnings("rawtypes")
		public void subscribeFirmwareUpgradeEvent(){
			
			Device deviceMediaRender = this.deviceDisplay.getMMDevice();
			
			String namespaceFirmwareUpgrade = ServiceValues.DEFAULT_NAMESPACE;
			String typeFirmwareUpgrade = FirmwareUpdateServiceValues.SERVICE_NAME;
			ServiceType servicetypeFirmwareUpgrade = new ServiceType(namespaceFirmwareUpgrade, typeFirmwareUpgrade);
			
			Service serviceFirmwareUpgrade = deviceMediaRender.findService(servicetypeFirmwareUpgrade);
			if(serviceFirmwareUpgrade != null){
				
				callbackFirmwareUpgradeServiceSubscription = new SubscriptionCallback(serviceFirmwareUpgrade){

					@Override
					protected void ended(GENASubscription subscription, CancelReason reasonCancel, UpnpResponse responseUPnP) {
						
						Log.d(this.toString(), "subscribeFirmwareUpgradeServiceEvent end - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "CancelReason : " + reasonCancel);
						Log.d(this.toString(), "UpnpResponse : " + responseUPnP);
						
					}

					@Override
					protected void established(GENASubscription subscription) {
						
						Log.d(this.toString(), "subscribeFirmwareUpgradeServiceEvent established - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						
					}

					@Override
					protected void eventReceived(GENASubscription subscription) {
						
						Log.d(this.toString(), "subscribeFirmwareUpgradeServiceEvent eventReceived - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						
						Map<String, StateVariableValue> values = subscription.getCurrentValues();
						if(	values != null ){
							
							Log.d(tag, values.toString());
							
							Message msg = new Message();
							msg.what = 0;
							
							StateVariableValue state = values.get(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_STATE);
							if(state != null){
								String stateText = (String)state.getValue();
								msg.obj = stateText;
							}
							
							StateVariableValue progress = values.get(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_PROGRESS);
							if(progress != null){
								msg.arg1 = (Integer)progress.getValue();	
							}
							
							if(handlerFirmwareUpgrade != null)
								handlerFirmwareUpgrade.sendMessage(msg);
							
						}
						
						//Log.d(TAG, values.toString());
//						if(values.size() == 3){
//							
//							Message msg = new Message();
//							
//							StateVariableValue prgoress = (StateVariableValue)values.get(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_PROGRESS);
//							msg.arg1 = (Integer)prgoress.getValue();
//							msg.what = 0;
//							
//							if(handlerFirmwareUpgrade != null)
//								handlerFirmwareUpgrade.sendMessage(msg);
//							
//						}
						
						
					}

					@Override
					protected void eventsMissed(GENASubscription subscription, int arg1) {
						
						Log.d(this.toString(), "subscribeFirmwareUpgradeServiceEvent eventsMissed - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "othermessage : " + arg1);
						
					}

					@Override
					protected void failed(GENASubscription subscription, UpnpResponse responseUPnP, Exception exception, String arg3) {
						
						Log.d(this.toString(), "subscribeFirmwareUpgradeServiceEvent failed - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "UpnpResponse : " + responseUPnP);
						Log.d(this.toString(), "Exception : " + exception);
						Log.d(this.toString(), "othermessage : " + arg3);
						
					}
					
				};
				
				upnpServer.getControlPoint().execute(callbackFirmwareUpgradeServiceSubscription);
				
			}else{
				// error log
				// show message
			}
			
		}
		
		private SubscriptionCallback callbackSystemServiceSubscription;
		@SuppressWarnings("rawtypes")
		public void subscribeSystemServiceEvent(){
			
			Device deviceMediaRender = this.deviceDisplay.getMMDevice();
			
			String systemserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
			String systemserviceType = SystemServiceValues.SERVICE_NAME;
			ServiceType typeSystemService = new ServiceType(systemserviceNamespace, systemserviceType);
			
			Service serviceSystem = deviceMediaRender.findService(typeSystemService);
			if(serviceSystem != null){
				
				callbackSystemServiceSubscription = new SubscriptionCallback(serviceSystem){

					@Override
					protected void ended(GENASubscription subscription, CancelReason reasonCancel, UpnpResponse responseUPnP) {
						
						Log.d(this.toString(), "subscribeSystemServiceEvent end - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "CancelReason : " + reasonCancel);
						Log.d(this.toString(), "UpnpResponse : " + responseUPnP);
						
					}

					@Override
					protected void established(GENASubscription subscription) {
						
						Log.d(this.toString(), "subscribeSystemServiceEvent established - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						
					}

					@Override
					protected void eventReceived(GENASubscription subscription) {
						
						Log.d(this.toString(), "subscribeSystemServiceEvent eventReceived - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						
						//Map<String, String> events = subscription.getCurrentValues();
						//updateerSystemInfo.onSystmeInfoChanged(events.get(""));
						
					}

					@Override
					protected void eventsMissed(GENASubscription subscription, int arg1) {
						
						Log.d(this.toString(), "subscribeSystemServiceEvent eventsMissed - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "othermessage : " + arg1);
						
					}

					@Override
					protected void failed(GENASubscription subscription, UpnpResponse responseUPnP, Exception exception, String arg3) {
						
						Log.d(this.toString(), "subscribeSystemServiceEvent failed - ");
						Log.d(this.toString(), "GENASubscription : " + subscription);
						Log.d(this.toString(), "UpnpResponse : " + responseUPnP);
						Log.d(this.toString(), "Exception : " + exception);
						Log.d(this.toString(), "othermessage : " + arg3);
						
					}
					
				};
				
				upnpServer.getControlPoint().execute(callbackSystemServiceSubscription);
				
			}else{
				// error log
				// show message
			}
			
		}
		
		//註冊Device Group狀態EVENT
		public void subscribeGroupServiceEvent(){
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
						mlog.info(tag,"FriendlyName = "+EventHandler.this.deviceDisplay.getDevice().getDetails().getFriendlyName());
						for(Map.Entry<String, StateVariableValue>value:values.entrySet()){
//							mlog.info(TAG, "even = "+value.getKey()+"  value = "+value.getValue().toString());
						}
						if(status!=null){
							GroupVO groupVO = _parseGroup(status.toString());
							mlog.info(tag, "groupVO = "+groupVO.getGroup());
							if(groupVO.isSlave()){
								//even 通知  isSlave
								//移除Slave
								groupList.RemoveDeviceDisplay(EventHandler.this.deviceDisplay);
								//檢查是否為當前選擇
								if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
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
								groupList.AddDeviceDisplay(EventHandler.this.deviceDisplay);
								//加入GroupList 是否加入成功
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
		
		//===============Event 刷新 View ==================
		private String mrPlayStatus;
		private String mtPlayMode;	
		private String metadataTitle;
		private ItemDO doMetaData;
		//======SeekBar======
		private Long secondTotal =0l;
		private Long secondRun =0l;		
		private String stringTotal = null;
		private String stringRun = null;
		//===================
		private List<TrackDO> listQueue;
		 //Play狀態
		
		private void updataPlayMode(){
			if(mrPlayStatus!=null&&!mrPlayStatus.equals("")&&PIListner!=null){
				//Phone Speaker Play_IButton_Listner&& PAD MAIN Play_IButton_Listner
				 PIListner.SetPlay_IButton_State(mrPlayStatus);
				 mlog.info(tag, "==========EVEN STAR==========");
				 mlog.info(tag, "lastChangeDO MR_State= "+mrPlayStatus);
				 mlog.info(tag, "============End=============");
				 //Phone Info Play_IButton_Listner
				 if(Info_PIListner!=null){
					 Info_PIListner.SetPlay_IButton_State(mrPlayStatus);
				 }							
			 } 				 			 
		}
		 //CurrentPlayMode
		private void updataCurrentPlayMode(){
			if(mtPlayMode!=null&&!mtPlayMode.equals("")&&PMIListner!=null){
				//Phone Speaker PlayMode_IButton_Listner&& PAD MAIN PlayMode_IButton_Listner
				 PMIListner.SetPlayMode_IButton_State(mtPlayMode);
				 mlog.info(tag, "==========EVEN STAR==========");
				 mlog.info(tag, "lastChangeDO MR_PlayMode= "+mtPlayMode);
				 mlog.info(tag, "============End=============");
				//Phone Info PlayMode_IButton_Listner
				 if(Info_PMIListner!=null){
					 Info_PMIListner.SetPlayMode_IButton_State(mtPlayMode);
				 }
			 }				 		
		}
		//MI_Info
		private void updataItemMetaData(){			
			//info
			if(doMetaData!=null){				
				MIListner.SetMusicInfo_State(doMetaData.getTitle(), doMetaData.getArtist(), doMetaData.getAlbum(), doMetaData.getGenre(),doMetaData.getAlbumURI());
				mlog.info(tag, "============Start=============");
			 	mlog.info(tag, "Title = "+doMetaData.getTitle());							
				mlog.info(tag, "Artist = "+doMetaData.getArtist());
				mlog.info(tag, "Album = "+doMetaData.getAlbum());
				mlog.info(tag, "Genre = "+doMetaData.getGenre());	
				mlog.info(tag, "AlbumURI = "+doMetaData.getAlbumURI());										
				mlog.info(tag, "============End=============");
			}			 		 
		}	
		//QueueList
		private void updataQueueList(){
			if(listQueue != null && listenerQueue != null){
				 mlog.info(tag, "trackList size = "+listQueue.size());	
				 listenerQueue.setQueueList(listQueue);
			}
		}
		private void UpdateRunState_TextView(){			
			if(runState_TextView_Listner2!=null){
				runState_TextView_Listner2.SetRunState_TextView_State(mrPlayStatus, metadataTitle,EventHandler.this.deviceDisplay);
			}
		}
		private void updataSeekBar(){
			
			if(stringTotal==null||stringTotal.equals("")||stringTotal.split(":").length<=1){
				stringTotal = "00:00:00";
			}
			
			long hh = secondRun/60/60;
			long mm = secondRun/60-hh*60;
			long ss = secondRun%60;							
		
			stringRun = String.format("%02d",hh)+":"+ String.format("%02d",mm)+":"+ String.format("%02d",ss);
			
			if(music_SeekBar_Listner!=null){
				music_SeekBar_Listner.SetSeek(secondTotal, secondRun, stringTotal, stringRun);
			}
			if(Info_Music_SeekBar_Listner!=null){
				Info_Music_SeekBar_Listner.SetSeek(secondTotal, secondRun, stringTotal, stringRun);
			}
			mlog.info(tag, "getTrackDurationSeconds = "+secondTotal);//秒數總時間
			mlog.info(tag, "getTrackElapsedSeconds = "+secondRun);//秒數播放時間
			mlog.info(tag, "getTrackDuration = "+stringTotal);//字串總時間
			mlog.info(tag, "getAbsTime = "+stringRun);//字串播放時間	
		}
		
		public void UpdataALL(){
			updataPlayMode();
			updataCurrentPlayMode();
			updataItemMetaData();
			updataQueueList(); 
			updataSeekBar();
		}
		
		public String GetTransportState(){
			return mrPlayStatus;
		}
		public String GetMetaDataTitle(){			
			if(metadataTitle!=null){		
				return metadataTitle;
			}else{
				return "";
			}
		}
		//===============Event 刷新 View ==================
		
		//註冊 AVTransport Event
		public void RegistInfoEvent(){	
			
			if(Device_DisplayInfoCallBack!=null){
				Device_DisplayInfoCallBack.end();
			}			
			Device device = this.deviceDisplay.getDevice();
			//取得 AVTransportService
			Service AVTransportService = device.findService(new UDAServiceId("AVTransport"));
			for(int i=0;i<device.findServices().length;i++){
				mlog.info(tag,"status ="+device.findServices()[i].toString());				
			}
			
			if(AVTransportService!=null){
				//設定StateCallBack
				Device_DisplayInfoCallBack = new SubscriptionCallback(AVTransportService){
					@SuppressWarnings("rawtypes")
					@Override
					protected void ended(GENASubscription arg0, CancelReason arg1, UpnpResponse arg2) {
						mlog.info(tag,"status end ="+arg2);
					}
					@SuppressWarnings({ "rawtypes", "unchecked" })
					@Override
					protected void established(GENASubscription arg0) {
						
						mlog.info(tag,"status = established");
						Map<String, StateVariableValue> values = arg0.getCurrentValues();
						StateVariableValue status = values.get("LastChange");
						 
						for(Map.Entry<String, StateVariableValue>value:values.entrySet()){
							mlog.info(tag, "even = "+value.getKey()+"  value = "+value.getValue().toString());
						}
						
					}
					@SuppressWarnings({ "rawtypes", "static-access" })
					@Override
					protected void eventReceived(GENASubscription arg0) {		
						
						 Map<String, StateVariableValue> values = arg0.getCurrentValues();
						 for(Map.Entry<String, StateVariableValue> value:values.entrySet()){
							 mlog.info(tag, "==========EVEN STAR==========");
							 mlog.info(tag, "key= "+value.getKey().toString());
							 mlog.info(tag, "==========EVEN END==========");
						 }
						 
						 
						 StateVariableValue vvLastChange = values.get("LastChange");
						 if(processBarListner != null){
							 processBarListner.setProcessBarHidden();
						 }
						 
						 if(vvLastChange != null){	
							 
							 mlog.info(tag, "==========EVEN STAR==========");
							 mlog.info(tag, "LastChange valeu= "+vvLastChange.toString());
							 mlog.info(tag, "==========EVEN END==========");
							 
							 //儲存資料
							 LastChangeDO doLastChange = _parseLastChangeEvent(vvLastChange.toString());
							 
							 if(doLastChange.getTransportState()!=null){
								 
								 mrPlayStatus = doLastChange.getTransportState();
								 if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
									 updataPlayMode();
								 }
								 
							 }
							 
							 if(doLastChange.getCurrentPlayMode() != null){
								 
								 mtPlayMode = doLastChange.getCurrentPlayMode();
								 if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
									 updataCurrentPlayMode();
								 }
								 
							 }		
							 
							 if(doLastChange.getAVTransportURIMetaData() != null){
								 
								 String metadataItem = doLastChange.getAVTransportURIMetaData();
								 if(metadataItem != null && !metadataItem.equals("")){
									 
										mlog.info(tag, "============Start=============");					 
										mlog.info(tag, "Item_MetaData = "+metadataItem);
									
										doMetaData =  _parseItem(metadataItem);
										if(doMetaData != null){
										
											metadataTitle = doMetaData.getTitle();
											
											mlog.info(tag, "============End=============");
											mlog.info(tag, "============Start=============");	
											mlog.info(tag, "==item_MetaData Title=="+doMetaData.getTitle());
											mlog.info(tag, "==item_MetaData Genre=="+doMetaData.getGenre());	
											mlog.info(tag, "==item_MetaData Album=="+doMetaData.getAlbum());	
											mlog.info(tag, "==item_MetaData AlbumURI=="+doMetaData.getAlbumURI());	
											mlog.info(tag, "============End=============");
											
										}
										
								 }								 
								 if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
									 updataItemMetaData();
								 }
								 
							 }		
							 
							 if(	doLastChange.getTransportState() != null
								||	doLastChange.getAVTransportURIMetaData() != null){
								 UpdateRunState_TextView();
							 }
							 
							 //SeekBar
							 if(true){
								 
								 //*****請設定 ******
//								 secondTotal //Long  秒數      非毫秒
//								 secondRun //Long	秒數    非毫秒
//								 stringTotal//String 00:00:00
//								 stringRun//String 00:00:00
								 //*****************
								 //判斷是否為選擇的Renderer
								 if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
									 updataSeekBar();//刷新
								 } 
							 }
						 }
						 
						 //Queue
						 StateVariableValue queueMsuicInfo = values.get("TracksInQueue");
						 if(queueMsuicInfo != null){
							 
//							 mlog.info(TAG, "==========EVEN STAR==========");
//							 mlog.info(TAG, "Queue valeu= "+queueMsuicInfo.toString());
//							 mlog.info(TAG, "==========EVEN END==========");
							 
							 listQueue = AGSParser._parseTrack(queueMsuicInfo.toString());
							 if(EventHandler.this.deviceDisplay.equals(mrChoosed)){
								 updataQueueList(); 
							 }
							 
						 }	
						 
						 // CurrentTrack, Start with 1
						 StateVariableValue positionCurrentTrack = values.get("CurrentTrack");
						 if(positionCurrentTrack != null)
							 DeviceDisplayList.this.positionCurrentTrack = positionCurrentTrack.toString();
						 
					}					
					@Override
					protected void eventsMissed(GENASubscription arg0, int arg1) {				
						mlog.info(tag,"status = eventsMissed");
					}
					@Override
					protected void failed(GENASubscription arg0, UpnpResponse arg1,	Exception arg2, String arg3) {
						mlog.info(tag,"status failed="+arg3);
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
		    	xml = xml.replace("&", "&amp;");
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
		    	xml = StringEscapeUtils.unescapeHtml4(xml);
		    	xml = xml.replace("&", "&amp;");
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
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
}

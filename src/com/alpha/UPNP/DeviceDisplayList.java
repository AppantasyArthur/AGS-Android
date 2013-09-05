package com.alpha.UPNP;

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
import org.teleal.cling.model.types.UDAServiceId;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.FAM.SETTING.Music_SeekBar_Listner;
import com.FAM.SETTING.PlayMode_IButton_Listner;
import com.FAM.SETTING.Play_IButton_Listner;
import com.FAM.SETTING.Sound_SeekBar_Listner;
import com.FI.SETTING.FI_Queqe_ListView_BaseAdapter_Queqe_Listner;
import com.FI.SETTING.MusicInfo_Listner;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_Listner;
import com.FS.SETTING.FS_SPEAKER_ExpandableListAdapter_Listner;
import com.FS.SETTING.RunState_TextView_Listner2;
import com.FSAL_Music.SETTING.FSAl_Music_ListView_BaseAdapter_Listner;
import com.FSR.SETTING.FSR_Renderers_ListView_BaseAdapter_Renderer_Listner;
import com.alpha.upnpui.FragmentActivity_Main;
import com.appantasy.androidapptemplate.event.lastchange.GroupHandler;
import com.appantasy.androidapptemplate.event.lastchange.GroupVO;
import com.appantasy.androidapptemplate.event.lastchange.ItemDO;
import com.appantasy.androidapptemplate.event.lastchange.ItemHandler;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeDO;
import com.appantasy.androidapptemplate.event.lastchange.LastChangeHandler;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.appantasy.androidapptemplate.event.lastchange.TrackHanlder;
import com.tkb.UpnpOverride.ProcessBarListner;
import com.tkb.tool.MLog;

public class DeviceDisplayList implements Parcelable  {
	
	private Context context;
	private static String TAG = "DeviceDisplayList";
	private MLog mlog = new MLog();
	
	private DeviceDisplay ChooseMediaRenderer;
	//SeekBar Timer
	private Timer timeSeekBarTimer;
	
	private List<DeviceDisplay> DDList;
	private List<DeviceDisplay> MRList;//����  MediaRenderer List
	private GroupList groupList;//��Group Service ��  MediaRenderer List
	private List<DeviceDisplay> MSList;//MediaServer List
	//Listners
	private FS_SPEAKER_ExpandableListAdapter_Listner FSELAListner;//Speaker
	private RunState_TextView_Listner2 runState_TextView_Listner2;//Speaker �]���O
	private Music_SeekBar_Listner music_SeekBar_Listner;//TiemSeek
	private Music_SeekBar_Listner Info_Music_SeekBar_Listner;//TiemSeek 
	private Sound_SeekBar_Listner sound_SeekBar_Listner;//GroupSoundSeek
	private Sound_SeekBar_Listner Info_Sound_SeekBar_Listner;//GroupSoundSeek
	private FM_Music_ListView_BaseAdapter_Listner FMLBAListner;//Music
	private Play_IButton_Listner PIListner;//���� ���s
	private Play_IButton_Listner Info_PIListner;//���� ���s
	private PlayMode_IButton_Listner PMIListner;//PlayMode���s
	private PlayMode_IButton_Listner Info_PMIListner;//PlayMode���s
	private MusicInfo_Listner MIListner;//Info
	private FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner;//Queue
	private ProcessBarListner processBarListner;
	//Setting Listners
	private FSR_Renderers_ListView_BaseAdapter_Renderer_Listner FSRRRLBListner;
	private FSAl_Music_ListView_BaseAdapter_Listner FSALMLBListner;
	
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
		//==================Device ����====================
		if(deviceType.getType().toString().equals("MediaRenderer")){
			//MediaRenderer List
			MRList.add(dd);
			//FSRRRLBListner
			if(FSRRRLBListner!=null){
				FSRRRLBListner.RenderersChange();
			}
			//�ˬd�O�_��Group
			DeviceType deviceType_f = new DeviceType("schemas-upnp-org", "DeviceManager");
			Device[] devices = dd.getDevice().findDevices(deviceType_f);
			
			EventHandler eventHandler = new EventHandler(dd);
			eventHandler.RegistInfoEvent();	
			dd.setEventHandler(eventHandler);
			
			if(devices!=null&&devices.length>0){				
				//��Group
				Device MMDevice = devices[0];
				mlog.info(TAG, "===================================");
				mlog.info(TAG, "Name = "+dd.getDevice().getDetails().getFriendlyName());
				mlog.info(TAG, "Namespace = "+dd.getDevice().getType().getType());
				mlog.info(TAG, "UDN = "+dd.getDevice().getIdentity().getUdn());
				
				mlog.info(TAG, "Name = "+MMDevice.getDetails().getFriendlyName());				
				mlog.info(TAG, "Namespace = "+MMDevice.getType().getType());
				mlog.info(TAG, "UDN = "+MMDevice.getIdentity().getUdn());
				mlog.info(TAG, "===================================");
				mlog.info(TAG, "Type = "+MMDevice.getType().getType());
				mlog.info(TAG, "Version = "+MMDevice.getType().getVersion());
				dd.setMMDevice(MMDevice);	
				//���UGroup Listner	
				eventHandler.RegistGroupEvent();
				//�I�dDevice�ثe���A
				eventHandler.checkMasterORSingle();	
			}else{
				//�S��Group	
				//�[�JGroupList �O�_�[�J���\
				if(groupList.AddDeviceDisplay(dd)){
					//�q��FS ��s
					if(FSELAListner!=null){
						FSELAListner.SetPositionChange();
					}		
					//�q��MI��s
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
				FSALMLBListner.AddMediaServer(dd);
			}
		}else{
			DDList.add(dd);
		}
		//==================Device ����====================
		mlog.info(TAG, "addDeviceDisplay = "+dd.getDevice().getDetails().getFriendlyName());
	}
	public void removeDeviceDisplay(DeviceDisplay dd) {
		DeviceType deviceType = dd.getDevice().getType();
		if(deviceType.getType().toString().equals("MediaRenderer")){
			if(groupList.RemoveDeviceDisplay(dd)){
				//�q��FS ��s
				if(FSELAListner!=null){
					FSELAListner.SetPositionChange();
				}	
				//�q��MI ��s
				if(MIListner!=null){
					MIListner.MediaRendererCountChange();
				}
			}
			if(dd.equals(ChooseMediaRenderer)){
				DeviceDisplayList.this.setChooseMediaRenderer(null);
			}			
			MRList.remove(dd);
			//FSRRRLBListner
			if(FSRRRLBListner!=null){
				FSRRRLBListner.RenderersChange();
			}
			mlog.info(TAG, "removeDeviceDisplay = MR");
		}else if(deviceType.getType().toString().equals("MediaServer")){
			if(FMLBAListner!=null){
				FMLBAListner.RemoveMediaServer(dd);
			}
			if(FSALMLBListner!=null){
				FSALMLBListner.RemoveMediaServer(dd);
			}
			mlog.info(TAG, "removeDeviceDisplay = MS");
			MSList.remove(dd);
		}else{
			DDList.remove(dd);
			mlog.info(TAG, "removeDeviceDisplay = DD");
		}
	}
	//�]�w�ҿ���� Renderer
	public void setChooseMediaRenderer(DeviceDisplay mediaRenderer){
		if(this.ChooseMediaRenderer==mediaRenderer){
			return;			
		}
		this.ChooseMediaRenderer = mediaRenderer;
		
		//timeSeekBarTimer �k�s
		if(timeSeekBarTimer!=null){
			timeSeekBarTimer.cancel();			
			//�e���k�s
			if(music_SeekBar_Listner!=null){
				music_SeekBar_Listner.SetSeek(0l, 0l, "00:00:00", "00:00:00");
			}
			if(Info_Music_SeekBar_Listner!=null){
				Info_Music_SeekBar_Listner.SetSeek(0l, 0l, "00:00:00", "00:00:00");
			}
		}
		//Group SoundSeekBar �k�s
		if(sound_SeekBar_Listner!=null){
			sound_SeekBar_Listner.SetSeek(0);
			if(Info_Sound_SeekBar_Listner!=null){
				Info_Sound_SeekBar_Listner.SetSeek(0);
			}
		}
		
		//�]�wQueqeCallBack
		//Queqe �k�s
		if(queqe_listner!=null){
			queqe_listner.ClearQueqeList();
		}		
		if(MIListner!=null){
			MIListner.ClearMusicInfo_State();
			MIListner.SetPositionChange();
		}
		
		
		//�q�� FS ��s
		if(FSELAListner!=null){
			FSELAListner.SetPositionChange();
		}
		if(mediaRenderer==null){
			return;
		}
		
		EventHandler eventHandler = mediaRenderer.getEventHandler();		
		if (eventHandler!=null){
			//��Ƴ]�w
			eventHandler.UpdataALL();
		}
		//==============�]�w timeSeekBarTimer===========================
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
							//����CallBack Delay
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
							mlog.info(TAG, "getTrackDurationSeconds = "+arg1.getTrackDurationSeconds());//����`�ɶ�
							mlog.info(TAG, "getTrackElapsedSeconds = "+arg1.getTrackElapsedSeconds());//��Ƽ���ɶ�
							mlog.info(TAG, "getTrackDuration = "+arg1.getTrackDuration());//�r���`�ɶ�
							mlog.info(TAG, "getAbsTime = "+arg1.getAbsTime());//�r�꼽��ɶ�					
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
		timeSeekBarTimer.schedule(timerTask, 1000, 1000);//�}�l����*/
		//==============timeSeekBarTimer===========================
	}
	
//	public void cancelTimeSeekBarTimer(){
//		if(timeSeekBarTimer!=null){
//			timeSeekBarTimer.cancel();
//		}
//	}
	//=====================Device ���o=============================
	//���o���Device
	public DeviceDisplay getChooseMediaRenderer(){
		return this.ChooseMediaRenderer;
	}
	//���o �DMediaRenderer�BMediaServer ���Ҧ� Device �M��
	public List<DeviceDisplay> getDeviceDisplayList(){
		return DDList;
	}
	//���o �Ҧ�MediaRenderer Device �M��
	public List<DeviceDisplay> getMediaRendererList(){
		return MRList;
	}
	//���o ��Group��MediaRenderer Device �M��
	public List<DeviceDisplay> getGroupList(){
		return this.groupList.GetGroupList();
	}
	//���o �Ҧ�MediaServer Device �M��
	public List<DeviceDisplay> getMediaServerList(){
		return MSList;
	}
	//�HDeviceManager ��UDN ��M Renderer
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
	//�HDeviceManager ��UDN ��M Renderer
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
	//=====================Device=============================
	//===========Listner �]�w================
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
	public void setQueqe_Listner(FI_Queqe_ListView_BaseAdapter_Queqe_Listner queqe_listner) {
		this.queqe_listner = queqe_listner;
	}
	public void setProcessBarListner(ProcessBarListner processBarListner){
		this.processBarListner = processBarListner;
	}
	public void setFSR_Renderers_ListView_BaseAdapter_Renderer_Listner(FSR_Renderers_ListView_BaseAdapter_Renderer_Listner FSRRRLBListner){
		this.FSRRRLBListner = FSRRRLBListner;
	}
	public void setFSAl_Music_ListView_BaseAdapter_Listner(FSAl_Music_ListView_BaseAdapter_Listner FSALMLBListner){
		this.FSALMLBListner = FSALMLBListner;
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
		queqe_listner = null;
		
		FSRRRLBListner = null;
		FSALMLBListner = null;
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
			//���oupnpServer
			this.upnpServer = ((FragmentActivity_Main)context).GETUPnPService();			
		}		
		//�ˬdGroup GetDisplayInfo Event
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
							//�ˬd�s�u���Ѥ��[�J�}�C
						}
						@Override
						public void success(ActionInvocation arg0) {							
							ActionArgument DisplayInfo = arg0.getAction().getOutputArgument("DisplayInfo");
							ActionArgumentValue actionArgumentValue = arg0.getOutput(DisplayInfo);
							GroupVO groupVO = _parseGroup(actionArgumentValue.toString());							
							mlog.info(TAG, "GetDisplayInfoActionCallBack  = "+actionArgumentValue.toString());
							mlog.info(TAG, "Device Name  = "+groupVO.getName());
							mlog.info(TAG, "Device UDN  = "+deviceDisplay.getDevice().getIdentity().getUdn());
							mlog.info(TAG, "DeviceManager UDN  = "+groupVO.getUdn());							
							mlog.info(TAG, "GetDisplayInfoActionCallBack success");
							deviceDisplay.setGroupVO(groupVO);
							if(!groupVO.isSlave()){
								//���OSlave �[�J�}�C
								//�[�JGroupList �O�_�[�J���\
								if(groupList.AddDeviceDisplay(deviceDisplay)){								
									//�q��FS ��s
									if(FSELAListner!=null){									
										FSELAListner.SetPositionChange();								
									}
									//�q��MI ��s
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
		//���UDevice Group���AEVENT
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
//							mlog.info(TAG, "even = "+value.getKey()+"  value = "+value.getValue().toString());
						}
						if(status!=null){
							GroupVO groupVO = _parseGroup(status.toString());
							mlog.info(TAG, "groupVO = "+groupVO.getGroup());
							if(groupVO.isSlave()){
								//even �q��  isSlave
								//����Slave
								groupList.RemoveDeviceDisplay(EventHandler.this.deviceDisplay);
								//�ˬd�O�_����e���
								if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									DeviceDisplayList.this.setChooseMediaRenderer(null);
								}	
								//�q�� FS ��s
								if(FSELAListner!=null){														
									FSELAListner.SetPositionChange();
								}
								//�q�� MI ��s
								if(MIListner!=null){
									MIListner.MediaRendererCountChange();
								}
							}else{
								//even �q�� not isSlave
								//��sGroupEventHandler���A
								EventHandler.this.deviceDisplay.setGroupVO(groupVO);
								groupList.AddDeviceDisplay(EventHandler.this.deviceDisplay);
								//�[�JGroupList �O�_�[�J���\
								//�q�� FS ��s
								if(FSELAListner!=null){					
									FSELAListner.SetPositionChange();	
								}
								//�q�� MI ��s
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
		
		//===============Event ��s View ==================
		private String MR_State;
		private String MR_PlayMode;	
		private String metaData_Title;
		private ItemDO item_DO;
		//======SeekBar======
		private Long secondTotal =0l;
		private Long secondRun =0l;		
		private String stringTotal = null;
		private String stringRun = null;
		//===================
		private List<TrackDO> trackList;
		 //Play���A
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
		private void UpdataSeekBar(){
			
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
			mlog.info(TAG, "getTrackDurationSeconds = "+secondTotal);//����`�ɶ�
			mlog.info(TAG, "getTrackElapsedSeconds = "+secondRun);//��Ƽ���ɶ�
			mlog.info(TAG, "getTrackDuration = "+stringTotal);//�r���`�ɶ�
			mlog.info(TAG, "getAbsTime = "+stringRun);//�r�꼽��ɶ�	
		}
		
		public void UpdataALL(){
			UpdataPlayMode();
			UpdataCurrentPlayMode();
			UpdataItem_MetaData();
			UpdataQueueList(); 
			UpdataSeekBar();
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
		//===============Event ��s View ==================
		
		//���U AVTransport Event
		public void RegistInfoEvent(){		
			if(Device_DisplayInfoCallBack!=null){
				Device_DisplayInfoCallBack.end();
			}			
			Device device = this.deviceDisplay.getDevice();
			//���o AVTransportService
			Service AVTransportService = device.findService(new UDAServiceId("AVTransport"));
			for(int i=0;i<device.findServices().length;i++){
				mlog.info(TAG,"status ="+device.findServices()[i].toString());				
			}
			
			if(AVTransportService!=null){
				//�]�wStateCallBack
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
						 if(processBarListner!=null){
							 processBarListner.SetProcessBarNotDispaly();
						 }
						 
						 if(status!=null){				 
							 mlog.info(TAG, "==========EVEN STAR==========");
							 mlog.info(TAG, "LastChange valeu= "+status.toString());
							 mlog.info(TAG, "==========EVEN END==========");
							 //�x�s���
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
										if(item_DO != null){
										
											metaData_Title = item_DO.getTitle();
											mlog.info(TAG, "============End=============");
											mlog.info(TAG, "============Start=============");	
											mlog.info(TAG, "==item_MetaData=="+item_DO.getTitle());
											mlog.info(TAG, "==item_MetaData=="+item_DO.getGenre());	
											mlog.info(TAG, "==item_MetaData=="+item_DO.getAlbum());	
											mlog.info(TAG, "==item_MetaData=="+item_DO.getAlbumURI());	
											mlog.info(TAG, "============End=============");
											
										}
										
									}								 
								 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									 UpdataItem_MetaData();
								 }
							 }								 
							 if(lastChangeDO.getTransportState()!=null||lastChangeDO.getAVTransportURIMetaData()!=null){
								 UpdateRunState_TextView();
							 }
							 //SeekBar
							 if(true){
								 
								 //*****�г]�w ******
//								 secondTotal //Long  ���      �D�@��
//								 secondRun //Long	���    �D�@��
//								 stringTotal//String 00:00:00
//								 stringRun//String 00:00:00
								 //*****************
								 //�P�_�O�_����ܪ�Renderer
								 if(EventHandler.this.deviceDisplay.equals(ChooseMediaRenderer)){
									 UpdataSeekBar();//��s
								 } 
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

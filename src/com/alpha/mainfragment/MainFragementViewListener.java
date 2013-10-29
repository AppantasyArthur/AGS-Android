package com.alpha.mainfragment;

import java.util.ArrayList;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.avtransport.callback.Play;
import org.teleal.cling.support.avtransport.callback.Stop;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.alpha.fragments.MediaRendererMusicInfoFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.DeviceDisplayList;
import com.alpha.upnp.service.AGSAVTransportService;
import com.alpha.upnp.service.AGSRenderingControl;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnp.value.AVTransportServiceValues;
import com.alpha.upnp.value.RenderingControlValues;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.UpnpOverride.ProcessBarListner;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FAM_VIEW_LISTNER
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MainFragementViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MainFragementViewListener";
	private int device_size = 0;
	private FragmentManager fragmentManager;
	
	private SaveQueueListPopupWindow popupWindow;	
	private Handler handlerMusicInfoViewListener = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == AGSHandlerMessages.CLOSE_GENERAL_PROGRESS){
	
				if(popupWindow != null)
					popupWindow.dismiss();
				
			}
			
		}
		
	};
	
	private ProcessBarListner processBarListner;
	public MainFragementViewListener(Context context, int device_size,FragmentManager fragmentManager) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
		this.fragmentManager = fragmentManager;
	}
	public void CreateProcessBarListner(final ProgressBar processBar){
		processBarListner = new ProcessBarListner(){
			@Override
			public void SetProcessBarDisplay() {				
				processBar.post(new Runnable() {
					@Override
					public void run() {
						processBar.setVisibility(View.VISIBLE);						
					}
				});
			}
			@Override
			public void setProcessBarHidden() {
				processBar.post(new Runnable() {
					@Override
					public void run() {
						processBar.setVisibility(View.GONE);
						
					}
				});			
			}		
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setProcessBarListner(processBarListner);
	}
	public ProcessBarListner getProcessBarListner(){
		return processBarListner;
	}
	public void ShowCloseMediaC2_IButton_LISTNER(ImageButton ShowCloseMediaC2_IButton,final RelativeLayout MediaC2_RLayout) {
		ShowCloseMediaC2_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(MediaC2_RLayout.getVisibility()==View.GONE){
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", v, 2);
					MediaC2_RLayout.setVisibility(View.VISIBLE);
				}else{
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_n.png", v, 2);
					MediaC2_RLayout.setVisibility(View.GONE);
				}
			}
		});
	}
	public void Sound_IButton_LISTNER(ImageButton Sound_IButton){
		Sound_IButton.setOnClickListener(new View.OnClickListener() {
			private MainFragementVolumeSettingPopupWindow fam_PopupWindow = new MainFragementVolumeSettingPopupWindow(context);
			@Override
			public void onClick(View view) {
				fam_PopupWindow.showAsDropDown(view);
			}
		});
	}	
	public void Clear_Button_LISTNER(Button Clear_Button,final ImageView ButtonsBG_ImageView){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Clear_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i(tag, "Clear_Button On Click");
					//取得upnpServer
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					//取得MR Device
					DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
					
					ServiceId serviceId = new UDAServiceId("AVTransport");
					Service AVTransportService = null;
					//檢查Device 跟 res
					if(MR_Device!=null){
						//取得device 的 "AVTransport" service
						AVTransportService = MR_Device.getDevice().findService(serviceId);
					}else{
						return;
					}					
					Action action = AVTransportService.getAction("RemoveAllTracksInQueue");				
					if(action!=null){		
						ActionArgumentValue[] values = new ActionArgumentValue[1];
						ActionArgument InstanceID = action.getInputArgument("InstanceID");
						if(InstanceID!=null){
							values[0] =new ActionArgumentValue(InstanceID, "0");						
							
							ActionInvocation ai = new ActionInvocation(action,values);
							
							ActionCallback RemoveAllTracksInQueueActionCallBack = new ActionCallback(ai){
								@Override
								public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
									mlog.info(tag, "RemoveAllTracksInQueueActionCallBack failure = "+arg2);
								}
								@Override
								public void success(ActionInvocation arg0) {									
									mlog.info(tag, "RemoveAllTracksInQueueActionCallBack success");
								}											
							};
							upnpServer.getControlPoint().execute(RemoveAllTracksInQueueActionCallBack);
						}						
					}				
				}
			});
			Clear_Button.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int Tag = (Integer)ButtonsBG_ImageView.getTag();
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						if(Tag==0){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_01.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(1);
						}
						break;
					case MotionEvent.ACTION_UP:
						if(Tag==1){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(0);
						}
						break;
					case MotionEvent.ACTION_CANCEL:
						if(Tag==1){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(0);
						}
						break;
					}
					return false;
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Save_Button_LISTNER(Button Save_Button,final ImageView ButtonsBG_ImageView){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Save_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(popupWindow==null){
						popupWindow = new SaveQueueListPopupWindow(context, handlerMusicInfoViewListener);
					}
					popupWindow.showPopupWindow(v.getRootView(), Gravity.CENTER, 0, 0);
				}
			});
			Save_Button.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int Tag = (Integer)ButtonsBG_ImageView.getTag();
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						if(Tag==0){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_02.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(2);
						}
						break;
					case MotionEvent.ACTION_UP:
						if(Tag==2){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(0);
						}
						break;
					case MotionEvent.ACTION_CANCEL:
						if(Tag==2){
							new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png",ButtonsBG_ImageView, 1);
							ButtonsBG_ImageView.setTag(0);
						}
						break;
					}
					return false;
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Done_Button_LISTNER(Button Done_Button,final Button Edit_Button,final Button Clear_Button,final MediaRendererMusicInfoFragement fragment_Infor){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//檢查
					if(fragment_Infor==null||Clear_Button==null||Edit_Button==null){
						return;
					}
					//隱藏Done 顯示Clear_Button、Edit_Button
					if(!fragment_Infor.SET_FI_ListView_Edite(false)){
						v.setVisibility(View.GONE);
						Clear_Button.setVisibility(View.VISIBLE);
						Edit_Button.setVisibility(View.VISIBLE);
					}
					
				}
			});
			//***************************PAD*********************************
		}
	}
	public void CycleRandom_IButton_LISTNER(final ImageButton Cycle_IButton,final ImageButton Random_IButton){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Cycle_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)v.getTag();
					Log.i(tag, "Tag = "+Tag);
					switch(Tag){
					case 0:
						SetPlayMode(1);
						break;
					case 1:	
						SetPlayMode(2);
						break;
					case 2:
						SetPlayMode(3);
						break;
					case 3:
						SetPlayMode(0);
						break;
					}
				}
			});		
			//***************************PAD*********************************
			PlayMode_IButton_Listner PMI_Listner = new PlayMode_IButton_Listner(){
				@Override
				public void SetPlayMode_IButton_State(final String MR_PlayMode) {
					Cycle_IButton.post(new Runnable(){
						@Override
						public void run() {
							if(MR_PlayMode.equals("NORMAL")){
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", Cycle_IButton, 2);
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
								Cycle_IButton.setTag(0);
							}else if(MR_PlayMode.equals("REPEAT_ALL")){
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/Repeat all.png", Cycle_IButton, 2);
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
								Cycle_IButton.setTag(1);
							}else if(MR_PlayMode.equals("REPEAT_ONE")){
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/Repeat one.png", Cycle_IButton, 2);
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
								Cycle_IButton.setTag(2);	
							}else if(MR_PlayMode.equals("SHUFFLE")||MR_PlayMode.equals("RANDOM")){
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", Cycle_IButton, 2);
								new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_f.png", Random_IButton, 2);
								Cycle_IButton.setTag(3);
							}
							mlog.info(tag, "SetPlay_IButton_State = "+MR_PlayMode);
						}
					});
					
				}
			};
			//注測PlayMode EVEN
			((MainFragmentActivity)context).getDeviceDisplayList().setPlayMode_IButton_Listner(PMI_Listner);
		}
	}
	private void SetPlayMode(int Mode){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		//取得instanceId
		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
		//取得service
		Service AVTransportService = null;	
		//檢查 MR_Device
		if(MR_Device!=null){
			//取得device 的 "AVTransport" service
			AVTransportService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
		}else{
			return;
		}
		Action SetPlayModeAction = AVTransportService.getAction("SetPlayMode");
		if(SetPlayModeAction!=null){
			ActionArgumentValue[] values = new ActionArgumentValue[2];
			//GET ActionArgument 
			ActionArgument InstanceID = SetPlayModeAction.getInputArgument("InstanceID");
			ActionArgument NewPlayMode = SetPlayModeAction.getInputArgument("NewPlayMode");
			if(InstanceID!=null&&NewPlayMode!=null&&Mode<4){
				values[0] =new ActionArgumentValue(InstanceID, "0");
				switch(Mode){
				case 0:
					values[1] =new ActionArgumentValue(NewPlayMode, "NORMAL");
					break;
				case 1:
					values[1] =new ActionArgumentValue(NewPlayMode, "REPEAT_ALL");
					break;
				case 2:
					values[1] =new ActionArgumentValue(NewPlayMode, "REPEAT_ONE");
					break;
				case 3:
					values[1] =new ActionArgumentValue(NewPlayMode, "SHUFFLE");
					break;
				}
				ActionInvocation ai = new ActionInvocation(SetPlayModeAction,values);
				
				ActionCallback SetPlayModeActionCallBack = new ActionCallback(ai){
					@Override
					public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
						mlog.info(tag, "SetPlayModeActionCallBack failure = "+arg2);
					}
					@Override
					public void success(ActionInvocation arg0) {									
						mlog.info(tag, "SetPlayModeActionCallBack success");
					}											
				};
				upnpServer.getControlPoint().execute(SetPlayModeActionCallBack);	
			}
		}
	}
//	public void Random_IButton_LISTNER(final ImageButton Random_IButton){
//		if(DeviceProperty.isPhone()){
//			//***************************PHONE*********************************	
//			//***************************PHONE*********************************	
//		}else{
//			//***************************PAD*********************************
//			Random_IButton.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					int Tag = (Integer)v.getTag();
//					Log.i(TAG, "Tag = "+Tag);
//					switch(Tag){
//					case 0:
//						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_f.png", Random_IButton, 2);
//						Random_IButton.setTag(1);						
//						break;
//					case 1:
//						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
//						Random_IButton.setTag(0);
//						break;					
//					}
//				}
//			});			
//			//***************************PAD*********************************
//		}
//	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Setting_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SETTING")==null){
						Fragment_SETTING fragment_SETTING = new Fragment_SETTING();
						TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction(), fragment_SETTING, "Fragment_SETTING", R.id.FAM_RLayout_SETTING_RLayoutt, R.animator.translate_right_in, R.animator.alpha_out,R.animator.alpha_in, R.animator.translate_right_out);
					}					
				}
			});
			//***************************PAD*********************************
		}
	}
	
	public void Previous_IButton_LISTNER(ImageButton Previous_IButton){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Previous_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//取得upnpServer
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					//取得MR Device
					DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
					//取得instanceId
					UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
					//取得service
					Service AVTransportService = null;	
					//檢查 MR_Device
					if(MR_Device!=null){
						//取得device 的 "AVTransport" service
						AVTransportService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
					}else{
						return;
					}
					Action action = AVTransportService.getAction("Previous");
					
					if(action!=null){
						ActionArgumentValue[] values = new ActionArgumentValue[1];
						//GET ActionArgument 
						ActionArgument InstanceID = action.getInputArgument("InstanceID");						
						//設定值
						if(InstanceID!=null){
							values[0] =new ActionArgumentValue(InstanceID, "0");							
							
							ActionInvocation ai = new ActionInvocation(action,values);
							
							ActionCallback PreviousCallBack = new ActionCallback(ai){
								@Override
								public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
									mlog.info(tag, "PreviousCallBack failure = "+arg2);
									PlayMusic();
								}
								@Override
								public void success(ActionInvocation arg0) {									
									mlog.info(tag, "PreviousCallBack success");
									PlayMusic();
								}											
							};
							upnpServer.getControlPoint().execute(PreviousCallBack);	
						}
					}								
				}
			});
			//***************************PAD*********************************
		}
	}
	
	public void Next_IButton_LISTNER(ImageButton Next_IButton){
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Next_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//取得upnpServer
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					//取得MR Device
					DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
					//取得instanceId
					UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
					//取得service
					Service AVTransportService = null;	
					//檢查 MR_Device
					if(MR_Device!=null){
						//取得device 的 "AVTransport" service
						AVTransportService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
					}else{
						return;
					}
					Action action = AVTransportService.getAction("Next");
					
					if(action!=null){
						ActionArgumentValue[] values = new ActionArgumentValue[1];
						//GET ActionArgument 
						ActionArgument InstanceID = action.getInputArgument("InstanceID");						
						//設定值
						if(InstanceID!=null){
							values[0] =new ActionArgumentValue(InstanceID, "0");							
							
							ActionInvocation ai = new ActionInvocation(action,values);
							
							ActionCallback NextCallBack = new ActionCallback(ai){
								@Override
								public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
									mlog.info(tag, "NextCallBack failure = "+arg2);
									PlayMusic();
								}
								@Override
								public void success(ActionInvocation arg0) {									
									mlog.info(tag, "NextCallBack success");
									PlayMusic();
								}											
							};
							upnpServer.getControlPoint().execute(NextCallBack);	
						}
					}								
				}
			});
			//***************************PAD*********************************
		}
	}
	
	
	public void setPlaybackButtonListener(final ImageButton buttonPlayback) {
		
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			buttonPlayback.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int tag = (Integer)v.getTag();
					switch(tag){
					case 0:
						PlayMusic();
						break;
					case 1:
						StopMusic();
						break;
					}
					
				}
			});
			//***************************PAD*********************************
		}
		PlaybackButtonListener listenerPlayback = new PlaybackButtonListener(){
			
			@Override
			public void setPlaybackState(String stateMediaRenderPlayback) {
				if(stateMediaRenderPlayback.equals("STOPPED")){
					buttonPlayback.post(new Runnable(){
						@Override
						public void run() {
							buttonPlayback.setTag(0);
							new TKBThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", buttonPlayback, 2);	
						}
					});
					
				}else if(stateMediaRenderPlayback.equals("PLAYING")){
					buttonPlayback.post(new Runnable(){
						@Override
						public void run() {
							buttonPlayback.setTag(1);
							new TKBThreadReadStateListInAssets(context, "phone/play_volume/stop_f.png","phone/play_volume/stop_n.png", buttonPlayback, 2);	
						}
					});
				}
				mlog.info(tag, "SetPlay_IButton_State = " + stateMediaRenderPlayback);
			}
		};
		//注測Play EVEN
		((MainFragmentActivity)context).getDeviceDisplayList().setPlaybackButtonListener4Pad(listenerPlayback);
		
	}
	private void StopMusic(){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		//取得instanceId
		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
		//取得service
		Service StopService = null;	
		//檢查 MR_Device
		if(MR_Device!=null){
			//取得device 的 "AVTransport" service
			StopService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
		}else{
			return;
		}
		//檢查StopService
		if(StopService!=null){
			Stop ActionCallback = new Stop(instanceId,StopService){
				@Override
			    public void success(ActionInvocation invocation) {
					mlog.info(tag, "Stop success");
				}
				@Override
				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
					mlog.info(tag, "Stop failure");							
				}
			};
			upnpServer.getControlPoint().execute(ActionCallback);
		}
	}
	
	private void PlayMusic(){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		//取得instanceId
		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
		//取得service
		Service PlayService = null;	
		//檢查 MR_Device
		if(MR_Device!=null){
			//取得device 的 "AVTransport" service
			PlayService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
		}else{
			return;
		}
		//檢查StopService
		if(PlayService!=null){
			Play ActionCallback = new Play(instanceId,PlayService){
				
				@Override
			    public void success(ActionInvocation invocation) {
					mlog.info(tag, "Play success");
				}
				@Override
				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
					mlog.info(tag, "Play failure");							
				}
			};
			upnpServer.getControlPoint().execute(ActionCallback);
		}		
	}
	
	private Handler seekHandler ;
	public void setTimeProgressListener(final TextView viewElapsedTimeText, final SeekBar seekbarPlayback, final TextView viewTotalTimeText){
		
		seekHandler = new Handler(){
			public void handleMessage (Message msg) {
				switch(msg.what){
				case 0:
					viewElapsedTimeText.setText((String)msg.obj);
					break;
				case 1:
					viewTotalTimeText.setText((String)msg.obj);
					break;
				}
			}
		};
		
		if(seekbarPlayback != null){
			
			seekbarPlayback.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					mlog.debug(tag, "onProgressChanged : " + progress);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					mlog.debug(tag, "onStartTrackingTouch");
				}

				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					
					mlog.debug(tag, "onStopTrackingTouch");
					
					Integer secondCurrent = seekBar.getProgress();
					
					long hh = secondCurrent / 60 / 60;
					long mm = secondCurrent / 60 - hh * 60;
					long ss = secondCurrent % 60;
					
					String stringCurrent = String.format("%d",hh)+":"+ String.format("%02d",mm)+":"+ String.format("%02d",ss);
					
					AGSAVTransportService service = new AGSAVTransportService(DeviceDisplayList.getChooseMediaRenderer().getDevice()
							, MainFragmentActivity.getMessageHandler());
					
					Action action = service.getActionSeek();
					if(action != null){
						
						ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
						
						ActionArgument argInstanceID = action.getInputArgument(AVTransportServiceValues.ACTION_SEEK_INPUT_INSTANCE_ID);
						ActionArgumentValue valInstanceID = new ActionArgumentValue(argInstanceID, "0");
						values.add(valInstanceID);
						
						ActionArgument argUnit = action.getInputArgument(AVTransportServiceValues.ACTION_SEEK_INPUT_UNIT);
						ActionArgumentValue valUnit = new ActionArgumentValue(argUnit, "ABS_TIME");
						values.add(valUnit);
						
						ActionArgument argTarget = action.getInputArgument(AVTransportServiceValues.ACTION_SEEK_INPUT_TARGET);
						ActionArgumentValue valTarget = new ActionArgumentValue(argTarget, stringCurrent);
						values.add(valTarget);
						
						service.actDumpAllTracksInQueue(values.toArray(new ActionArgumentValue[values.size()])
								, null);
						
					}
					
				}
				
			});
			
		}
		
		MusicPlaybackSeekBarListener listenerPlaybackSeekBar = new MusicPlaybackSeekBarListener(){
			
			@Override
			public void setSeekTime(Long secondTotal, Long secondCurrent, String stringTotal, String stringCurrent) {
				
				if(secondTotal != null
				&& seekbarPlayback.getMax() != secondTotal.intValue()){
					seekbarPlayback.setMax(secondTotal.intValue());
				}
				if(secondCurrent != null
				&& seekbarPlayback.getProgress() != secondCurrent.intValue()){
					seekbarPlayback.setProgress(secondCurrent.intValue());
				}
				if(stringCurrent != null
				&& !stringCurrent.equals(viewElapsedTimeText.getText().toString())){
					seekHandler.obtainMessage(0, stringCurrent).sendToTarget();
				}
				if(stringTotal != null 
				&& !stringTotal.equals(viewTotalTimeText.getText().toString())){
					seekHandler.obtainMessage(1, stringTotal).sendToTarget();
				}
			}
			
			@Override
			public int getElapsedTime() {
				
				return seekbarPlayback.getProgress();
				
			}
			
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setMusicPlaybackSeekBarListener4Pad(listenerPlaybackSeekBar);
	}
	
	public void setVolumeSeekBarListener(final SeekBar seekbarVolume, final ImageView imageVolumeButton){
		
		seekbarVolume.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
//			int position = 0;
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				mlog.debug(tag, "onProgressChanged : " + progress);
			}					
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {	
				mlog.debug(tag, "onStartTrackingTouch");
//				this.position = seekBar.getProgress();
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				mlog.debug(tag, "onStopTrackingTouch");
//				int stopPosition = seekBar.getProgress();	
//				seekBar.setProgress(position);
//				this.position = 0;
				
				setVolumeImage(seekBar.getProgress(), imageVolumeButton);
				setVolumePosition(seekBar.getProgress());
				
			}
		});
		VolumeSeekBarListner listenerVolumeSeekBar = new VolumeSeekBarListner(){
			@Override
			public void setVolume(int volume) {
				mlog.info(tag, "setVolume = " + volume);
				seekbarVolume.setProgress(volume);				
			}
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setSoundSeekBarListner4Pad(listenerVolumeSeekBar);
	}
	
	private void setVolumeImage(int Vol,ImageView Sound_ImageButton){
		if(Vol ==0){
			new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_mute.png",Sound_ImageButton, 1);
		}else if(Vol>=1&&Vol<=50){
			new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_01.png",Sound_ImageButton, 1);
		}else if(Vol>=51&&Vol<=99){
			new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_02.png",Sound_ImageButton, 1);
		}else{
			new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_03.png",Sound_ImageButton, 1);
		}		
	}
	
	private void setVolumePosition(int position){
		
		AGSRenderingControl service = new AGSRenderingControl(DeviceDisplayList.getChooseMediaRenderer().getDevice()
																, MainFragmentActivity.getMessageHandler());
		
		Action action = service.getActionSetVolume();
		if(action != null){
			
			ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
			
			ActionArgument argInstanceID = action.getInputArgument(RenderingControlValues.ACTION_SET_VOLUME_INPUT_INSTANCE_ID);
			ActionArgumentValue valInstanceID = new ActionArgumentValue(argInstanceID, "0");
			values.add(valInstanceID);
			
			ActionArgument argChannel = action.getInputArgument(RenderingControlValues.ACTION_SET_VOLUME_INPUT_CHANNEL);
			ActionArgumentValue valChannel = new ActionArgumentValue(argChannel, "Master");
			values.add(valChannel);
			
			ActionArgument argVolume = action.getInputArgument(RenderingControlValues.ACTION_SET_VOLUME_INPUT_DESIRED_VOLUME);
			ActionArgumentValue valVolume = new ActionArgumentValue(argVolume, position);
			values.add(valVolume);
			
			service.actSetVolume(values.toArray(new ActionArgumentValue[values.size()])
											, null);
			
		}

	}
	
}

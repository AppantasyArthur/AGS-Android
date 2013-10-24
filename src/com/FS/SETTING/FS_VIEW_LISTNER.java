package com.FS.SETTING;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.avtransport.callback.Play;
import org.teleal.cling.support.avtransport.callback.Stop;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.FAM.SETTING.FAM_PopupWindow;
import com.FAM.SETTING.Music_SeekBar_Listner;
import com.FAM.SETTING.PlayMode_IButton_Listner;
import com.FAM.SETTING.Play_IButton_Listner;
import com.FAM.SETTING.Sound_SeekBar_Listner;
import com.alpha.fragments.MediaRendererListFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;


public class FS_VIEW_LISTNER {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FS_VIEW_LISTNER";
	private int device_size = 0;
	private FragmentManager fragmentManager;
	public FS_VIEW_LISTNER(Context context, int device_size,FragmentManager fragmentManager) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
		this.fragmentManager = fragmentManager;
	}
	public void ShowTITLE4_IButton_LISTNER(ImageButton ShowTITLE4_IButton,final RelativeLayout TITLE4_RLayout) {
		ShowTITLE4_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TITLE4_RLayout.getVisibility()==View.GONE){
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", v, 2);
					TITLE4_RLayout.setVisibility(View.VISIBLE);
				}else{
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_n.png", v, 2);
					TITLE4_RLayout.setVisibility(View.GONE);
				}
			}
		});
	}
	public void NowPlaying_Button_LISTNER(Button NowPlaying_Button){
		NowPlaying_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainFragmentActivity)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	public void Music_Button_LISTNER(Button Music_Button){
		Music_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainFragmentActivity)context).ShowViewContent_ViewFlipperDisplay(2,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	public void Close_Button_LISTNER(Button Close_Button,final MediaRendererListFragement fragment_Speaker) {
		Close_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				fragment_Speaker.ShowViewContent_ViewFlipperDisplay(2, R.animator.translate_top_in, R.animator.translate_bottom_out);
			}
		});
	}
	public void Done_Button_LISTNER(Button Done_Button, final MediaRendererListFragement fragment_Speaker) {
		Done_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				fragment_Speaker.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_top_in, R.animator.translate_bottom_out);
			}
		});
		
	}
	public void Sound_IButton_LISTNER(ImageButton Sound_IButton){
		Sound_IButton.setOnClickListener(new View.OnClickListener() {
			private FAM_PopupWindow fam_PopupWindow = new FAM_PopupWindow(context);
			@Override
			public void onClick(View view) {
				fam_PopupWindow.showAsDropDown(view);
			}
		});
	}
	public void Previous_IButton_LISTNER(ImageButton Previous_IButton){
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
								mlog.info(TAG, "PreviousCallBack failure = "+arg2);
								PlayMusic();
							}
							@Override
							public void success(ActionInvocation arg0) {									
								mlog.info(TAG, "PreviousCallBack success");
								PlayMusic();
							}											
						};
						upnpServer.getControlPoint().execute(PreviousCallBack);	
					}
				}								
			}
		});			
	}
	
	public void Next_IButton_LISTNER(ImageButton Next_IButton){
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
								mlog.info(TAG, "NextCallBack failure = "+arg2);
								PlayMusic();
							}
							@Override
							public void success(ActionInvocation arg0) {									
								mlog.info(TAG, "NextCallBack success");
								PlayMusic();
							}											
						};
						upnpServer.getControlPoint().execute(NextCallBack);	
					}
				}								
			}
		});			
	}	
	
	public void Play_IButton_LISTNER(final ImageButton Play_IButton) {
		Play_IButton.setOnClickListener(new View.OnClickListener() {
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
		Play_IButton_Listner PI_Listner = new Play_IButton_Listner(){
			@Override
			public void SetPlay_IButton_State(String MR_State) {
				if(MR_State.equals("STOPPED")){
					Play_IButton.post(new Runnable(){
						@Override
						public void run() {
							Play_IButton.setTag(0);
							new TKBThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", Play_IButton, 2);	
						}
					});
					
				}else if(MR_State.equals("PLAYING")){
					Play_IButton.post(new Runnable(){
						@Override
						public void run() {
							Play_IButton.setTag(1);
							new TKBThreadReadStateListInAssets(context, "phone/play_volume/stop_f.png","phone/play_volume/stop_n.png", Play_IButton, 2);	
						}
					});
				}
				mlog.info(TAG, "SetPlay_IButton_State = "+MR_State);
			}
		};
		//注測Play EVEN
		((MainFragmentActivity)context).getDeviceDisplayList().setPlay_IButton_Listner(PI_Listner);
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
					mlog.info(TAG, "Stop success");
				}
				@Override
				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
					mlog.info(TAG, "Stop failure");							
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
					mlog.info(TAG, "Play success");
				}
				@Override
				public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
					mlog.info(TAG, "Play failure");							
				}
			};
			upnpServer.getControlPoint().execute(ActionCallback);
		}		
	}
	public void SetTimeSeekLISTNER(final TextView Current_TextView,final SeekBar Music_SeekBar,final TextView Total_TextView){
		final Handler seekHandler = new Handler(){
			public void handleMessage (Message msg) {
				switch(msg.what){
				case 0:
					Current_TextView.setText((String)msg.obj);
					break;
				case 1:
					Total_TextView.setText((String)msg.obj);
					break;
				}
			}
		};
		Music_SeekBar_Listner music_SeekBar_Listner = new Music_SeekBar_Listner(){
			@Override
			public void SetSeek(Long secondTotal, Long secondRun, String stringTotal, String stringRun) {
				if(Music_SeekBar.getMax()!=secondTotal.intValue()){
					Music_SeekBar.setMax(secondTotal.intValue());
				}
				if(Music_SeekBar.getProgress()!=secondRun.intValue()){
					Music_SeekBar.setProgress(secondRun.intValue());
				}
				if(!stringRun.equals(Current_TextView.getText().toString())){
					seekHandler.obtainMessage(0, stringRun).sendToTarget();
				}
				
				if(stringTotal!=null&&!stringTotal.equals(Total_TextView.getText().toString())){
					seekHandler.obtainMessage(1, stringTotal).sendToTarget();
				}
				
				
			}
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setMusic_SeekBar_Listner(music_SeekBar_Listner);
	}
	public void Sound_SeekBarLISTNER(final SeekBar Sound_SeekBar,final ImageView Sound_ImageButton){
		Sound_SeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			int position = 0;
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				setSound_Image(progress,Sound_ImageButton);
			}					
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {				
				this.position = seekBar.getProgress();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int stopPosition = seekBar.getProgress();	
				seekBar.setProgress(position);
				this.position = 0;
				
				SetSoundPosition(stopPosition);
			}
		});
		Sound_SeekBar_Listner sound_SeekBar_Listner = new Sound_SeekBar_Listner(){
			@Override
			public void SetSeek(int volume) {
				mlog.error("SetSeek", "SetSeek = "+volume);
				Sound_SeekBar.setProgress(volume);				
			}
		};
		((MainFragmentActivity)context).getDeviceDisplayList().setSound_SeekBar_Listner(sound_SeekBar_Listner);
	}
	
	private void setSound_Image(int Vol,ImageView Sound_ImageButton){
		if(Vol ==0){
			new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_no.png",Sound_ImageButton, 1);
		}else if(Vol>=1&&Vol<=50){
			new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_02.png",Sound_ImageButton, 1);
		}else if(Vol>=51&&Vol<=99){
			new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_01.png",Sound_ImageButton, 1);
		}else{
			new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume.png",Sound_ImageButton, 1);
		}
	}
	
	private void SetSoundPosition(int position){
		//Group Sound Action
//		DeviceDisplay deviceDisplay = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
//		
//		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
//		if(deviceDisplay!=null){
//			
//		}	
	}
	
	
	public void CycleRandom_IButton_LISTNER(final ImageButton Cycle_IButton,final ImageButton Random_IButton){
		Cycle_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int Tag = (Integer)v.getTag();
				Log.i(TAG, "Tag = "+Tag);
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
		PlayMode_IButton_Listner PMI_Listner = new PlayMode_IButton_Listner(){
			@Override
			public void SetPlayMode_IButton_State(final String MR_PlayMode) {
				Cycle_IButton.post(new Runnable(){
					@Override
					public void run() {
						if(MR_PlayMode.equals("NORMAL")){
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", Cycle_IButton, 2);
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(0);
						}else if(MR_PlayMode.equals("REPEAT_ALL")){
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat all_f.png", Cycle_IButton, 2);
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(1);
						}else if(MR_PlayMode.equals("REPEAT_ONE")){
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat one_f.png", Cycle_IButton, 2);
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(2);	
						}else if(MR_PlayMode.equals("SHUFFLE")||MR_PlayMode.equals("RANDOM")){
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", Cycle_IButton, 2);
							new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle_f.png", Random_IButton, 2);
							Cycle_IButton.setTag(3);
						}
						mlog.info(TAG, "SetPlay_IButton_State = "+MR_PlayMode);
					}
				});
				
			}
		};
		//注測PlayMode EVEN
		((MainFragmentActivity)context).getDeviceDisplayList().setPlayMode_IButton_Listner(PMI_Listner);					
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
						mlog.info(TAG, "SetPlayModeActionCallBack failure = "+arg2);
					}
					@Override
					public void success(ActionInvocation arg0) {									
						mlog.info(TAG, "SetPlayModeActionCallBack success");
					}											
				};
				upnpServer.getControlPoint().execute(SetPlayModeActionCallBack);	
			}
		}
	}
	public void SET_SPEAKER_EListView_Listner(final ExpandableListView fS_SPEAKER_EListView) {
		if(DeviceProperty.isPhone()){
			//***************************PHONE*********************************	
			fS_SPEAKER_EListView.setOnGroupClickListener(new OnGroupClickListener(){
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					//設定GSELECTED					
					((FS_SPEAKER_ExpandableListAdapter_Phone)parent.getExpandableListAdapter()).SET_GView_SELECTED(groupPosition);
					return true;
				}			
			});			
			fS_SPEAKER_EListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
					//設定CSELECTED
					((FS_SPEAKER_ExpandableListAdapter_Phone)parent.getExpandableListAdapter()).SET_CVIEW_SELECTED(groupPosition, childPosition);
					return true;
				}					
			});
			fS_SPEAKER_EListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					if (ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
						//長按 Child Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
						int childPosition = ExpandableListView.getPackedPositionChild(packedPos);												
					}else if(ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
						//長按 Group Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);			
					}
					
					return true;
				}				
			});
			RunState_TextView_Listner2 runState_TextView_Listner2 = new RunState_TextView_Listner2(){
				@Override
				public void SetRunState_TextView_State(String playMode,	String MetaData_Title, DeviceDisplay deviceDisplay) {
					final FS_SPEAKER_ExpandableListAdapter_Phone adapter = (FS_SPEAKER_ExpandableListAdapter_Phone)fS_SPEAKER_EListView.getExpandableListAdapter();	
					//取得deviceDisplay在GroupList中的position
					int groupPosition = adapter.getGroupPosition(deviceDisplay);					
					if(groupPosition>=0){	
						//取得deviceDisplay在畫面上Cell的位置
						int position = fS_SPEAKER_EListView.getFlatListPosition(fS_SPEAKER_EListView.getPackedPositionForGroup(groupPosition));
						mlog.info(TAG, "groupPosition = "+groupPosition);
						mlog.info(TAG, "position = "+position);
						//取得CellView
						View groupView = fS_SPEAKER_EListView.getChildAt(position);						
						if(groupView==null){
							return;
						}
						//取得RunState TextView
						TextView RunState_TextView = (TextView)groupView.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_RunState_TextView);
						
						if(RunState_TextView!=null){	
							
							final RunStateHandler runStateHandler = new RunStateHandler(playMode, MetaData_Title, RunState_TextView);
							//主線 刷新畫面						
							fS_SPEAKER_EListView.post(new Runnable(){
								@Override
								public void run() {
									adapter.setRunState(runStateHandler);
									
								}								
							});
						}
					}
				}
			};
			((MainFragmentActivity)context).getDeviceDisplayList().setRunState_TextView_Listner2(runState_TextView_Listner2);
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			
			fS_SPEAKER_EListView.setOnGroupClickListener(new OnGroupClickListener(){
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					Log.i("dddddd", "View = "+v);
					//設定GSELECTED					
					((FS_SPEAKER_ExpandableListAdapter_Pad)parent.getExpandableListAdapter()).SET_GView_SELECTED(groupPosition);
					return true;
				}			
			});			
			fS_SPEAKER_EListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
					//設定CSELECTED
					((FS_SPEAKER_ExpandableListAdapter_Pad)parent.getExpandableListAdapter()).SET_CVIEW_SELECTED(groupPosition, childPosition);
					return true;
				}					
			});
			fS_SPEAKER_EListView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					if (ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
						//長按 Child Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
						int childPosition = ExpandableListView.getPackedPositionChild(packedPos);												
					}else if(ExpandableListView.getPackedPositionType(arg3) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
						//長按 Group Item
						long packedPos = ((ExpandableListView) arg0).getExpandableListPosition(arg2);
						int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);			
					}
					
					return true;
				}				
			});
			RunState_TextView_Listner2 runState_TextView_Listner2 = new RunState_TextView_Listner2(){
				@Override
				public void SetRunState_TextView_State(String playMode,	String MetaData_Title, DeviceDisplay deviceDisplay) {
					final FS_SPEAKER_ExpandableListAdapter_Pad adapter = (FS_SPEAKER_ExpandableListAdapter_Pad)fS_SPEAKER_EListView.getExpandableListAdapter();	
					//取得deviceDisplay在GroupList中的position
					int groupPosition = adapter.getGroupPosition(deviceDisplay);					
					if(groupPosition>=0){	
						//取得deviceDisplay在畫面上Cell的位置
						int position = fS_SPEAKER_EListView.getFlatListPosition(fS_SPEAKER_EListView.getPackedPositionForGroup(groupPosition));
						mlog.info(TAG, "groupPosition = "+groupPosition);
						mlog.info(TAG, "position = "+position);
						//取得CellView
						View groupView = fS_SPEAKER_EListView.getChildAt(position);						
						if(groupView==null){
							return;
						}
						//取得RunState TextView
						TextView RunState_TextView = (TextView)groupView.findViewById(R.id.FS_SPEAKER_EListView_GCell_RLayout_RunState_TextView);
						
						if(RunState_TextView!=null){	
							
							final RunStateHandler runStateHandler = new RunStateHandler(playMode, MetaData_Title, RunState_TextView);
							//主線 刷新畫面						
							fS_SPEAKER_EListView.post(new Runnable(){
								@Override
								public void run() {
									adapter.setRunState(runStateHandler);
									
								}								
							});
						}
					}
				}
			};
			((MainFragmentActivity)context).getDeviceDisplayList().setRunState_TextView_Listner2(runState_TextView_Listner2);
			//***************************PAD*********************************	
		}		
	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		Setting_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(fragmentManager.findFragmentByTag("Fragment_SETTING")==null){
					Fragment_SETTING fragment_SETTING = new Fragment_SETTING();
					TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction(), fragment_SETTING, "Fragment_SETTING", R.id.pFAM_RLayout_SETTING_FLayoutt, R.animator.translate_right_in, R.animator.alpha_out,R.animator.alpha_in, R.animator.translate_right_out);
				}
			}
		});		
	}
	public void SELECT_Button_LISTNER(Button SELECT_Button, final MediaRendererListFragement fragment_Speaker) {
		SELECT_Button.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				fragment_Speaker.SetALLOptionButtonsSelect();				
			}
		});		
	}
	
	
}

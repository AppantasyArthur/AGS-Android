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
import com.FAM.SETTING.PlayMode_IButton_Listner;
import com.FAM.SETTING.Play_IButton_Listner;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.fragments.Fragment_Speaker;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;


public class FS_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FS_VIEW_LISTNER";
	private int device_size = 0;
	public FS_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void ShowTITLE4_IButton_LISTNER(ImageButton ShowTITLE4_IButton,final RelativeLayout TITLE4_RLayout) {
		ShowTITLE4_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TITLE4_RLayout.getVisibility()==View.GONE){
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", v, 2);
					TITLE4_RLayout.setVisibility(View.VISIBLE);
				}else{
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_n.png", v, 2);
					TITLE4_RLayout.setVisibility(View.GONE);
				}
			}
		});
	}
	public void NowPlaying_Button_LISTNER(Button NowPlaying_Button){
		NowPlaying_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FragmentActivity_Main)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	public void Music_Button_LISTNER(Button Music_Button){
		Music_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((FragmentActivity_Main)context).ShowViewContent_ViewFlipperDisplay(2,R.animator.translate_right_in,R.animator.alpha_out);
			}
		});
	}
	public void Close_Button_LISTNER(Button Close_Button,final Fragment_Speaker fragment_Speaker) {
		Close_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				fragment_Speaker.ShowViewContent_ViewFlipperDisplay(2, R.animator.translate_top_in, R.animator.translate_bottom_out);
			}
		});
	}
	public void Done_Button_LISTNER(Button Done_Button, final Fragment_Speaker fragment_Speaker) {
		Done_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				fragment_Speaker.ShowViewContent_ViewFlipperDisplay(1, R.animator.translate_top_in, R.animator.translate_bottom_out);
			}
		});
		
	}
	public void Previous_IButton_LISTNER(ImageButton Previous_IButton){
		Previous_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//取得upnpServer
				AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
				//取得MR Device
				DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
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
				AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
				//取得MR Device
				DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
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
							new ThreadReadStateListInAssets(context, "pad/PlayBack/play_f.png","pad/PlayBack/play_n.png", Play_IButton, 2);	
						}
					});
					
				}else if(MR_State.equals("PLAYING")){
					Play_IButton.post(new Runnable(){
						@Override
						public void run() {
							Play_IButton.setTag(1);
							new ThreadReadStateListInAssets(context, "pad/PlayBack/stop_f.png","pad/PlayBack/stop_n.png", Play_IButton, 2);	
						}
					});
				}
				mlog.info(TAG, "SetPlay_IButton_State = "+MR_State);
			}
		};
		//注測Play EVEN
		((FragmentActivity_Main)context).GETDeviceDisplayList().setPlay_IButton_Listner(PI_Listner);
	}
	private void StopMusic(){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
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
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
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
							new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", Cycle_IButton, 2);
							new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(0);
						}else if(MR_PlayMode.equals("REPEAT_ALL")){
							new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat all_f.png", Cycle_IButton, 2);
							new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(1);
						}else if(MR_PlayMode.equals("REPEAT_ONE")){
							new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat one_f.png", Cycle_IButton, 2);
							new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", Random_IButton, 2);
							Cycle_IButton.setTag(2);	
						}else if(MR_PlayMode.equals("SHUFFLE")||MR_PlayMode.equals("RANDOM")){
							new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", Cycle_IButton, 2);
							new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle_f.png", Random_IButton, 2);
							Cycle_IButton.setTag(3);
						}
						mlog.info(TAG, "SetPlay_IButton_State = "+MR_PlayMode);
					}
				});
				
			}
		};
		//注測PlayMode EVEN
		((FragmentActivity_Main)context).GETDeviceDisplayList().setPlayMode_IButton_Listner(PMI_Listner);					
	}
	private void SetPlayMode(int Mode){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
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
		if(device_size==6){
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
			((FragmentActivity_Main)context).GETDeviceDisplayList().setRunState_TextView_Listner2(runState_TextView_Listner2);
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
			((FragmentActivity_Main)context).GETDeviceDisplayList().setRunState_TextView_Listner2(runState_TextView_Listner2);
			//***************************PAD*********************************	
		}		
	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		Setting_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intnet = new Intent();
				intnet.setClass(context, FragmentActivity_Setting.class);
				context.startActivity(intnet);
			}
		});		
	}
	public void SELECT_Button_LISTNER(Button SELECT_Button, final Fragment_Speaker fragment_Speaker) {
		SELECT_Button.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				fragment_Speaker.SetALLOptionButtonsSelect();				
			}
		});		
	}
	
	
}

package com.FM.SETTING;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.model.types.UDAServiceType;
import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.avtransport.callback.Play;
import org.teleal.cling.support.avtransport.callback.SetAVTransportURI;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.Res;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.item.Item;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;

public class FM_PopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_PopupWindwo";
	
	//XMPP
	private Item item;
	private Device MS_Device;
	private int device_size = 0;
	public FM_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		if(device_size==6){
			Phone_CreateContentView();
		}else{
			PAD_CreateContentView();
		}
		
		ContentViewListner();
		//設定內容
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		//設定back鍵  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//設定Outside dismiss
		this.setOutsideTouchable(true);
		
		
	}
	private void Phone_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);		
		//Content RLayout
		Tool.fitsViewHeight(250, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(251, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/pop/pop_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout), 3);
		//TITLE TextView
		Tool.fitsViewHeight(27, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTopMargin(7, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		Tool.fitsViewHeight(157, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewWidth(209, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(34, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		OPTION_LLayout.getLayoutParams().height = Tool.getHeight(156);
		CreateOptionButtons(OPTION_LLayout);
		new ThreadReadBitMapInAssets(context, "phone/pop/pop_selecet_bg.png", OPTION_LLayout, 3);
		//CANCEL Button
		Tool.fitsViewHeight(32, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewWidth(209, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewBottomMargin(25, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		new ThreadReadBitMapInAssets(context, "phone/pop/cancer_button.png", this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button), 3);
		mlog.info(TAG, "CreateContentView");
	}
	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(294, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(297, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/pls_pop_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout), 3);
		//TITLE TextView
		Tool.fitsViewHeight(28, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		Tool.fitsViewHeight(181, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewWidth(248, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/pls_pop_list_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView), 3);
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		OPTION_LLayout.getLayoutParams().height = Tool.getHeight(180);		
		CreateOptionButtons(OPTION_LLayout);
		//CANCEL Button
		Tool.fitsViewHeight(40, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewWidth(210, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewBottomMargin(5, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		new ThreadReadStateListInAssets(context, "pad/Playlist/pls_cancel_bt_f.png", "pad/Playlist/pls_cancel_bt_n.png",  this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button), 4);
		mlog.info(TAG, "CreateContentView");
	}
	private void CreateOptionButtons(LinearLayout OPTION_LLayout){		
		Button OPTION_Button_1 = new Button(context);		
		Button OPTION_Button_2 = new Button(context);		
		Button OPTION_Button_3 = new Button(context);		
		Button OPTION_Button_4 = new Button(context);
		OPTION_LLayout.addView(OPTION_Button_1);
		OPTION_LLayout.addView(OPTION_Button_2);
		OPTION_LLayout.addView(OPTION_Button_3);
		OPTION_LLayout.addView(OPTION_Button_4);
		//設定 OPTION_Button 介面
		SetOptionButtonView(OPTION_Button_1,"Play Now",0);
		SetOptionButtonView(OPTION_Button_2,"Play Next",1);
		SetOptionButtonView(OPTION_Button_3,"Replay Queqe",1);
		SetOptionButtonView(OPTION_Button_4,"Add To Queqe",2);
		//設定LISTNER
		SetOPTION_Button_1_LISTNER(OPTION_Button_1);
		SetOPTION_Button_2_LISTNER(OPTION_Button_2);
		SetOPTION_Button_3_LISTNER(OPTION_Button_3);
		SetOPTION_Button_4_LISTNER(OPTION_Button_4);
	}
	
	private void SetOptionButtonView(Button OPTION_Button,String str,int number){
		
		if(device_size==6){
			OPTION_Button.setBackgroundColor(Color.parseColor("#00000000"));
			Tool.fitsViewHeight(39, OPTION_Button);
			Tool.fitsViewTextSize(10, OPTION_Button);
			new ThreadReadStateListInAssets(context, "phone/pop/selecet_center_f.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
		}else{
			switch(number){
			case 0:
				Tool.fitsViewHeight(45, OPTION_Button);	
				new ThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_01.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			case 1:
				Tool.fitsViewHeight(43, OPTION_Button);		
				new ThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_02.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			case 2:
				Tool.fitsViewHeight(46, OPTION_Button);		
				new ThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_03.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			}	
			Tool.fitsViewTextSize(8, OPTION_Button);
		}
		
		OPTION_Button.setText(str);
		OPTION_Button.setPadding(0, 0, 0, 0);
		OPTION_Button.setGravity(Gravity.CENTER);
		OPTION_Button.setTextColor(Color.WHITE);
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FM_PopupWindow.this.dismiss();	
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FM_PopupWindow.this.dismiss();					
			}
		});		
	}
	
	private void SetOPTION_Button_1_LISTNER(Button OPTION_Button_1){
		OPTION_Button_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
				SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
				Browse browse = new Browse(MS_Device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
					@Override
					public void received(ActionInvocation arg0, DIDLContent arg1) {
						if(arg0.getOutput().length<=0||arg1.getItems().size()<=0){
							return;
						}
						//取得upnpServer
						AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
						//取得MR Device
						DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
						mlog.info(TAG, "MR_Device = "+MR_Device);
						//取得MetaData								
						String MetaData = arg0.getOutput()[0].toString();	
						mlog.info(TAG, "MetaData = "+MetaData);
						//取得item res
						Res res = item.getFirstResource();
						//取得instanceId
						UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
						//取得service
						ServiceId serviceId = new UDAServiceId("AVTransport");
						Service AVTransportService = null;
						//檢查Device 跟 res
						if(MR_Device!=null&&res!=null){
							//取得device 的 "AVTransport" service
							AVTransportService = MR_Device.getDevice().findService(serviceId);
						}else{
							return;
						}
						//res顯示內容
						try{
							mlog.info(TAG, "============Start=============");
							mlog.info(TAG, item.getId());							
							mlog.info(TAG, item.getTitle());
							mlog.info(TAG, item.getFirstResource().toString());
							mlog.info(TAG, "RES = "+item.getFirstResource().getValue());
							mlog.info(TAG, "RES = "+item.getFirstResource().getDuration());
							mlog.info(TAG, "RES = "+item.getFirstResource().getProtocolInfo());
							mlog.info(TAG, "RES = "+item.getFirstResource().getSize());
							mlog.info(TAG, "RES = "+item.getProperties().size());									
							for(int i =0;i<item.getProperties().size();i++){
								mlog.info(TAG, "Propertie = "+item.getProperties().get(i).getValue());	
							}							
							mlog.info(TAG, "============End=============");
						}catch(Exception e){
							mlog.info(TAG, e.toString());
						}
						if(AVTransportService!=null){					
							SetAVTransportURI setAVTransportURI = new SetAVTransportURI(instanceId,AVTransportService,item.getFirstResource().getValue().toString(), MetaData){
								@Override
							    public void success(ActionInvocation invocation) {
									for(int i =0;i<invocation.getOutput().length;i++){
										mlog.info(TAG, "OT = "+invocation.getOutputMap().toString());	
									}	
									mlog.info(TAG, "setAVTransportURI success");
									PlayMusic();
								}
								@Override
								public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
									mlog.info(TAG, "setAVTransportURI arg2"+arg2);
									mlog.info(TAG, "setAVTransportURI failure");
								}
							};						
							upnpServer.getControlPoint().execute(setAVTransportURI);
						}
					}
					@Override
					public void updateStatus(Status arg0) {						
					}
					@Override
					public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
						mlog.info(TAG, "Container failure = "+arg1);
					}					
				};
				upnpServer.getControlPoint().execute(browse);
				FM_PopupWindow.this.dismiss();	
			}
		});
	}
	private void PlayMusic(){
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
			Play ActionCallback = new Play(instanceId,StopService){
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
	private void SetOPTION_Button_2_LISTNER(Button OPTION_Button_2){
		OPTION_Button_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FM_PopupWindow.this.dismiss();	
			}
		});
	}
	private void SetOPTION_Button_3_LISTNER(Button OPTION_Button_3){
		OPTION_Button_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FM_PopupWindow.this.dismiss();	
			}
		});
	}
	private void SetOPTION_Button_4_LISTNER(Button OPTION_Button_4){
		OPTION_Button_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
				SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
				Browse browse = new Browse(MS_Device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
					@Override
					public void received(ActionInvocation arg0, DIDLContent arg1) {
						if(arg0.getOutput().length<=0||arg1.getItems().size()<=0){
							return;
						}
						//取得upnpServer
						AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
						//取得MR Device
						DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
						mlog.info(TAG, "MR_Device = "+MR_Device);
						//取得MetaData								
						String MetaData = arg0.getOutput()[0].toString();	
						mlog.info(TAG, "MetaData = "+MetaData);
						//取得item res
						Res res = item.getFirstResource();
						//取得instanceId
						UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
						//取得service
						ServiceId serviceId = new UDAServiceId("AVTransport");
						Service AVTransportService = null;
						//檢查Device 跟 res
						if(MR_Device!=null&&res!=null){
							//取得device 的 "AVTransport" service
							AVTransportService = MR_Device.getDevice().findService(serviceId);
						}else{
							return;
						}
						//res顯示內容
						try{
							mlog.info(TAG, "============Start=============");
							mlog.info(TAG, item.getId());							
							mlog.info(TAG, item.getTitle());
							mlog.info(TAG, item.getFirstResource().toString());
							mlog.info(TAG, "RES = "+item.getFirstResource().getValue());
							mlog.info(TAG, "RES = "+item.getFirstResource().getDuration());
							mlog.info(TAG, "RES = "+item.getFirstResource().getProtocolInfo());
							mlog.info(TAG, "RES = "+item.getFirstResource().getSize());
							mlog.info(TAG, "RES = "+item.getProperties().size());									
							for(int i =0;i<item.getProperties().size();i++){
								mlog.info(TAG, "Propertie = "+item.getProperties().get(i).getValue());	
							}							
							mlog.info(TAG, "============End=============");
						}catch(Exception e){
							mlog.info(TAG, e.toString());
						}
						if(AVTransportService!=null){							
							if(MR_Device.getDevice()!=null){
								Action[] actions = AVTransportService.getActions();
								for(int i =0;i<actions.length;i++){
									mlog.info(TAG, "============action=============" + actions[i].getName());
									for(ActionArgument aaa :actions[i].getInputArguments()){
										mlog.info(TAG, "============ActionArgument============="+aaa.getName());
									}
								}
								Action action = AVTransportService.getAction("AddTrackToQueue");
								if(action!=null){
									ActionArgumentValue[] values = new ActionArgumentValue[5];
									//GET ActionArgument 
									ActionArgument InstanceID = action.getInputArgument("InstanceID");
									ActionArgument TrackURI = action.getInputArgument("TrackURI");
									ActionArgument TrackURIMetaData = action.getInputArgument("TrackURIMetaData");
									ActionArgument TrackNumber = action.getInputArgument("TrackNumber");
									ActionArgument PlayNow = action.getInputArgument("PlayNow");
									//設定值
									if(InstanceID!=null&&TrackURI!=null&&TrackURIMetaData!=null&&TrackNumber!=null&&PlayNow!=null){
										values[0] =new ActionArgumentValue(InstanceID, "0");
										values[1] =new ActionArgumentValue(TrackURI, res.getValue());
										values[2] =new ActionArgumentValue(TrackURIMetaData, MetaData);
										values[3] =new ActionArgumentValue(TrackNumber, -1);
										values[4] =new ActionArgumentValue(PlayNow, false);
										
										ActionInvocation ai = new ActionInvocation(action,values);
										
										ActionCallback AddTrackToQueueActionCallBack = new ActionCallback(ai){
											@Override
											public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
												mlog.info(TAG, "AddTrackToQueueActionCallBack failure = "+arg2);
											}
											@Override
											public void success(ActionInvocation arg0) {									
												mlog.info(TAG, "AddTrackToQueueActionCallBack success");												
												for(ActionArgumentValue aav :arg0.getOutput()){
													mlog.info(TAG, "aav ="+aav.toString());
												}
											}											
										};
										upnpServer.getControlPoint().execute(AddTrackToQueueActionCallBack);	
									}
								}
							}
						}
					}
					@Override
					public void updateStatus(Status arg0) {						
					}
					@Override
					public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
						mlog.info(TAG, "Container failure = "+arg1);
					}					
				};
				upnpServer.getControlPoint().execute(browse);	
			
				//關閉FM_PopupWindow
				FM_PopupWindow.this.dismiss();	
			}
		});
	}
	public void SetItemChooseDevice(Item item , Device device){
		//歸零
		this.item = null;
		this.MS_Device = null;
		//設定
		this.item = item;
		this.MS_Device = device;
	}
}

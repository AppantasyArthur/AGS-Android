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
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
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

public class FM_PopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_PopupWindwo";
	
	//XMPP
	private Item item;
	private Device MS_Device;
	public FM_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		
		CreateContentView();
		ContentViewListner();
		//�]�w���e
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		//�]�wback��  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//�]�wOutside dismiss
		this.setOutsideTouchable(true);
		
		
	}
	private void CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(290, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(295, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		//TITLE TextView
		Tool.fitsViewHeight(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		Tool.fitsViewHeight(200, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		CreateOptionButtons(OPTION_LLayout);
		//CANCEL Button
		Tool.fitsViewHeight(40, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewWidth(210, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewBottomMargin(5, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		
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
		//�]�w OPTION_Button ����
		SetOptionButtonView(OPTION_Button_1,"Play Now");
		SetOptionButtonView(OPTION_Button_2,"Play Next");
		SetOptionButtonView(OPTION_Button_3,"Replay Queqe");
		SetOptionButtonView(OPTION_Button_4,"Add To Queqe");
		//�]�wLISTNER
		SetOPTION_Button_1_LISTNER(OPTION_Button_1);
		SetOPTION_Button_2_LISTNER(OPTION_Button_2);
		SetOPTION_Button_3_LISTNER(OPTION_Button_3);
		SetOPTION_Button_4_LISTNER(OPTION_Button_4);
	}
	
	private void SetOptionButtonView(Button OPTION_Button,String str){
		Tool.fitsViewHeight(50, OPTION_Button);
		Tool.fitsViewTextSize(8, OPTION_Button);
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
						//���oupnpServer
						AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
						//���oMR Device
						DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
						mlog.info(TAG, "MR_Device = "+MR_Device);
						//���oMetaData								
						String MetaData = arg0.getOutput()[0].toString();	
						mlog.info(TAG, "MetaData = "+MetaData);
						//���oitem res
						Res res = item.getFirstResource();
						//���oinstanceId
						UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
						//���oservice
						ServiceId serviceId = new UDAServiceId("AVTransport");
						Service AVTransportService = null;
						//�ˬdDevice �� res
						if(MR_Device!=null&&res!=null){
							//���odevice �� "AVTransport" service
							AVTransportService = MR_Device.getDevice().findService(serviceId);
						}else{
							return;
						}
						//res��ܤ��e
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
		//���oupnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		//���oMR Device
		DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
		//���oinstanceId
		UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
		//���oservice
		Service StopService = null;	
		//�ˬd MR_Device
		if(MR_Device!=null){
			//���odevice �� "AVTransport" service
			StopService = MR_Device.getDevice().findService( new UDAServiceId("AVTransport"));
		}else{
			return;
		}
		//�ˬdStopService
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
						//���oupnpServer
						AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
						//���oMR Device
						DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
						mlog.info(TAG, "MR_Device = "+MR_Device);
						//���oMetaData								
						String MetaData = arg0.getOutput()[0].toString();	
						mlog.info(TAG, "MetaData = "+MetaData);
						//���oitem res
						Res res = item.getFirstResource();
						//���oinstanceId
						UnsignedIntegerFourBytes instanceId = new UnsignedIntegerFourBytes("0");
						//���oservice
						ServiceId serviceId = new UDAServiceId("AVTransport");
						Service AVTransportService = null;
						//�ˬdDevice �� res
						if(MR_Device!=null&&res!=null){
							//���odevice �� "AVTransport" service
							AVTransportService = MR_Device.getDevice().findService(serviceId);
						}else{
							return;
						}
						//res��ܤ��e
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
									//�]�w��
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
			
				//����FM_PopupWindow
				FM_PopupWindow.this.dismiss();	
			}
		});
	}
	public void SetItemChooseDevice(Item item , Device device){
		//�k�s
		this.item = null;
		this.MS_Device = null;
		//�]�w
		this.item = item;
		this.MS_Device = device;
	}
}

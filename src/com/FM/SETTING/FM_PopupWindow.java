package com.FM.SETTING;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
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
		//設定 OPTION_Button 介面
		SetOptionButtonView(OPTION_Button_1,"Play Now");
		SetOptionButtonView(OPTION_Button_2,"Play Next");
		SetOptionButtonView(OPTION_Button_3,"Replay Queqe");
		SetOptionButtonView(OPTION_Button_4,"Add To Queqe");
		//設定LISTNER
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
				//取得upnpServer
				AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
				//取得MR Device
				DeviceDisplay MR_Device = ((FragmentActivity_Main)context).GETDeviceDisplayList().getChooseMediaRenderer();
				//取得MS Device
//				FM_PopupWindow.this.MS_Device
				//取得item
//				FM_PopupWindow.this.item
				
				////////////////////////AddQUEQE 進入點////////////////////////////
				
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

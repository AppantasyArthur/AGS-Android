package com.FM.SETTING;

import java.util.List;

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

import android.app.AlertDialog;
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

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FM_PopupWindwo
public class MusicBrowsingControlOptionPopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MusicBrowsingControlOptionPopupWindow";
	
	//Item
	private Item item;
	private Device MS_Device;
	//TrackDO
	private TrackDO trackDO;
	//TrackDoList
	private List<TrackDO> trackDOList;
	
	private int device_size = 0;
	private int ContentFlag =0;//1 = Item ,2 = TrackDO ,3 = TrackDoList
	public MusicBrowsingControlOptionPopupWindow(Context context){
		super(context);
		this.mlog.switchLog = true;
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		if(DeviceProperty.isPhone()){
			Phone_CreateContentView();
		}else{
			PAD_CreateContentView();
		}
		
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
	private void Phone_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);		
		//Content RLayout
		TKBTool.fitsViewHeight(250, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(251, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/pop_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout), 3);
		//TITLE TextView
		TKBTool.fitsViewHeight(27, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		TKBTool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		TKBTool.fitsViewTopMargin(7, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		TKBTool.fitsViewHeight(157, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewWidth(209, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewTopMargin(34, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		OPTION_LLayout.getLayoutParams().height = TKBTool.getHeight(156);
		CreateOptionButtons(OPTION_LLayout);
		new TKBThreadReadBitMapInAssets(context, "phone/pop/pop_selecet_bg.png", OPTION_LLayout, 3);
		//CANCEL Button
		TKBTool.fitsViewHeight(32, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewWidth(209, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewBottomMargin(25, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/cancer_button.png", this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button), 3);
		mlog.info(tag, "CreateContentView");
	}
	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(294, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(297, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/pls_pop_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout), 3);
		//TITLE TextView
		TKBTool.fitsViewHeight(28, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		TKBTool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		TKBTool.fitsViewHeight(181, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewWidth(248, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewTopMargin(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/pls_pop_list_bg.png", this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView), 3);
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		OPTION_LLayout.getLayoutParams().height = TKBTool.getHeight(180);		
		CreateOptionButtons(OPTION_LLayout);
		//CANCEL Button
		TKBTool.fitsViewHeight(30, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewWidth(190, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		TKBTool.fitsViewBottomMargin(28, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_cancel_bt_f.png", "pad/Playlist/pls_cancel_bt_n.png",  this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button), 4);
		mlog.info(tag, "CreateContentView");
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
		SetOptionButtonView(OPTION_Button_1,"Play Now",0);
		SetOptionButtonView(OPTION_Button_2,"Play Next",1);
		SetOptionButtonView(OPTION_Button_3,"Replace Queue",1);
		SetOptionButtonView(OPTION_Button_4,"Add To Queue",2);
		//�]�wLISTNER
		SetOPTION_Button_1_LISTNER(OPTION_Button_1);
		SetOPTION_Button_2_LISTNER(OPTION_Button_2);
		SetOPTION_Button_3_LISTNER(OPTION_Button_3);
		SetOPTION_Button_4_LISTNER(OPTION_Button_4);
	}
	
	private void SetOptionButtonView(Button OPTION_Button,String str,int number){
		
		if(DeviceProperty.isPhone()){
			OPTION_Button.setBackgroundColor(Color.parseColor("#00000000"));
			TKBTool.fitsViewHeight(39, OPTION_Button);
			TKBTool.fitsViewTextSize(10, OPTION_Button);
			new TKBThreadReadStateListInAssets(context, "phone/pop/selecet_center_f.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
		}else{
			switch(number){
			case 0:
				TKBTool.fitsViewHeight(45, OPTION_Button);	
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_01.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			case 1:
				TKBTool.fitsViewHeight(43, OPTION_Button);		
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_02.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			case 2:
				TKBTool.fitsViewHeight(46, OPTION_Button);		
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_03.png", "phone/playlist/playlist_btn_n.png", OPTION_Button, 4);
				break;
			}	
			TKBTool.fitsViewTextSize(8, OPTION_Button);
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
				MusicBrowsingControlOptionPopupWindow.this.dismiss();	
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MusicBrowsingControlOptionPopupWindow.this.dismiss();					
			}
		});		
	}
	
	private void SetOPTION_Button_1_LISTNER(Button OPTION_Button_1){
		OPTION_Button_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				switch(ContentFlag){
				case 1:
					ItemPlayNow();
					break;
				case 2:
					TrackPlayNow();
					break;
				case 3:
					TrackListPlayNow();
					break;
				}
				
				MusicBrowsingControlOptionPopupWindow.this.dismiss();	
			}
		});
	}
	
	private void ItemPlayNow(){
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
		Browse browse = new Browse(MS_Device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
			@Override
			public void received(ActionInvocation arg0, DIDLContent arg1) {
				if(arg0.getOutput().length<=0||arg1.getItems().size()<=0){
					return;
				}
				//���oupnpServer
				AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
				//���oMR Device
				DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
				mlog.info(tag, "MR_Device = "+MR_Device);
				//���oMetaData								
				String MetaData = arg0.getOutput()[0].toString();	
				mlog.info(tag, "MetaData = "+MetaData);
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
					
					// 1. Instantiate an AlertDialog.Builder with its constructor
					AlertDialog.Builder builder = new AlertDialog.Builder(context);

					// 2. Chain together various setter methods to set the dialog characteristics
					builder.setMessage("Select a render first.")
					       .setTitle("AGS WARNING");

					// 3. Get the AlertDialog from create()
					AlertDialog dialog = builder.create();
					dialog.show();
					
					return;
					
				}
				//res��ܤ��e
				try{
					mlog.info(tag, "============Start=============");
					mlog.info(tag, item.getId());							
					mlog.info(tag, item.getTitle());
					mlog.info(tag, item.getFirstResource().toString());
					mlog.info(tag, "RES = "+item.getFirstResource().getValue());
					mlog.info(tag, "RES = "+item.getFirstResource().getDuration());
					mlog.info(tag, "RES = "+item.getFirstResource().getProtocolInfo());
					mlog.info(tag, "RES = "+item.getFirstResource().getSize());
					mlog.info(tag, "RES = "+item.getProperties().size());									
					for(int i =0;i<item.getProperties().size();i++){
						mlog.info(tag, "Propertie = "+item.getProperties().get(i).getValue());	
					}							
					mlog.info(tag, "============End=============");
				}catch(Exception e){
					mlog.info(tag, e.toString());
				}
				if(AVTransportService!=null){					
					SetAVTransportURI setAVTransportURI = new SetAVTransportURI(instanceId,AVTransportService,item.getFirstResource().getValue().toString(), MetaData){
						@Override
					    public void success(ActionInvocation invocation) {
							for(int i =0;i<invocation.getOutput().length;i++){
								mlog.info(tag, "OT = "+invocation.getOutputMap().toString());	
							}	
							mlog.info(tag, "setAVTransportURI success");
							PlayMusic();
						}
						@Override
						public void failure(ActionInvocation arg0,UpnpResponse arg1, String arg2) {
							mlog.info(tag, "setAVTransportURI arg2"+arg2);
							mlog.info(tag, "setAVTransportURI failure");
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
				mlog.info(tag, "Container failure = "+arg1);
			}					
		};
		upnpServer.getControlPoint().execute(browse);
	}
	private void TrackPlayNow(){
		//���oupnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//���oMR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		//this.trackDO ��e��trackDO
	
	}
	private void TrackListPlayNow(){
		//���oupnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//���oMR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
//		this.trackDOList// ��e��trackDOList
	}
	private void PlayMusic(){
		//���oupnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//���oMR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
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
	private void SetOPTION_Button_2_LISTNER(Button OPTION_Button_2){
		OPTION_Button_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(ContentFlag){
				case 1:
					ItemPlayNext();
					break;
				case 2:
					TrackPlayNext();
					break;
				case 3:
					TrackListPlayNext();
					break;
				}
				MusicBrowsingControlOptionPopupWindow.this.dismiss();	
			}
		});
	}
	private void ItemPlayNext(){
		
	}
	private void TrackPlayNext(){
		
	}
	private void TrackListPlayNext(){
		
	}
	
	private void SetOPTION_Button_3_LISTNER(Button OPTION_Button_3){
		OPTION_Button_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(ContentFlag){
				case 1:
					ItemReplayQueue();
					break;
				case 2:
					TrackReplayQueue();
					break;
				case 3:
					TrackListReplayQueue();
					break;
				}
				MusicBrowsingControlOptionPopupWindow.this.dismiss();	
			}
		});
	}
	private void ItemReplayQueue(){
		
	}
	private void TrackReplayQueue(){
		
	}
	private void TrackListReplayQueue(){
		
	}
	private void SetOPTION_Button_4_LISTNER(Button OPTION_Button_4){
		OPTION_Button_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(ContentFlag){
				case 1:
					ItemAddToQueue();
					break;
				case 2:
					TrackAddToQueue();
					break;
				case 3:
					TrackListAddToQueue();
					break;
				}
				
				
			
				//����FM_PopupWindow
				MusicBrowsingControlOptionPopupWindow.this.dismiss();	
			}
		});
	}
	private void ItemAddToQueue(){
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
		Browse browse = new Browse(MS_Device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
			@Override
			public void received(ActionInvocation arg0, DIDLContent arg1) {
				if(arg0.getOutput().length<=0||arg1.getItems().size()<=0){
					return;
				}
				//���oupnpServer
				AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
				//���oMR Device
				DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
				mlog.info(tag, "MR_Device = "+MR_Device);
				//���oMetaData								
				String MetaData = arg0.getOutput()[0].toString();	
				mlog.info(tag, "MetaData = "+MetaData);
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
					mlog.info(tag, "============Start=============");
					mlog.info(tag, item.getId());							
					mlog.info(tag, item.getTitle());
					mlog.info(tag, item.getFirstResource().toString());
					mlog.info(tag, "RES = "+item.getFirstResource().getValue());
					mlog.info(tag, "RES = "+item.getFirstResource().getDuration());
					mlog.info(tag, "RES = "+item.getFirstResource().getProtocolInfo());
					mlog.info(tag, "RES = "+item.getFirstResource().getSize());
					mlog.info(tag, "RES = "+item.getProperties().size());									
					for(int i =0;i<item.getProperties().size();i++){
						mlog.info(tag, "Propertie = "+item.getProperties().get(i).getValue());	
					}							
					mlog.info(tag, "============End=============");
				}catch(Exception e){
					mlog.info(tag, e.toString());
				}
				if(AVTransportService!=null){							
					if(MR_Device.getDevice()!=null){
						Action[] actions = AVTransportService.getActions();
						for(int i =0;i<actions.length;i++){
							mlog.info(tag, "============action=============" + actions[i].getName());
							for(ActionArgument aaa :actions[i].getInputArguments()){
								mlog.info(tag, "============ActionArgument============="+aaa.getName());
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
										mlog.info(tag, "AddTrackToQueueActionCallBack failure = "+arg2);
									}
									@Override
									public void success(ActionInvocation arg0) {									
										mlog.info(tag, "AddTrackToQueueActionCallBack success");												
										for(ActionArgumentValue aav :arg0.getOutput()){
											mlog.info(tag, "aav ="+aav.toString());
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
				mlog.info(tag, "Container failure = "+arg1);
			}					
		};
		upnpServer.getControlPoint().execute(browse);	
	}
	private void TrackAddToQueue(){
		
	}
	private void TrackListAddToQueue(){
		
	}
	
	public void SetItem(Item item , Device device){
		ContentFlag = 1;
		//�k�s
		this.item = null;
		this.MS_Device = null;
		//�]�w
		this.item = item;
		this.MS_Device = device;
	}
	public void SetTrack(TrackDO trackDO){
		ContentFlag = 2;
		//�k�s
		this.trackDO = null;	
		//�]�w
		this.trackDO = trackDO;
		
	}
	public void SetTrackList(List<TrackDO> trackDOList){
		ContentFlag = 3;
		//�k�s
		this.trackDOList = null;	
		//�]�w
		this.trackDOList = trackDOList;
		
	}
}

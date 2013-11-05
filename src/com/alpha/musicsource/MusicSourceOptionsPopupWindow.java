package com.alpha.musicsource;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
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

import com.alpha.fragments.MusicSourceFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.DeviceDisplayList;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnp.service.AGSAVTransportService;
import com.alpha.upnp.service.AGSActionSuccessCaller;
import com.alpha.upnp.service.AGSContentDirectoryService;
import com.alpha.upnp.value.AVTransportServiceValues;
import com.alpha.upnp.value.ContentDirectoryServiceValues;
import com.alpha.upnp.value.FirmwareUpdateServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FM_PopupWindwo
public class MusicSourceOptionsPopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MusicSourceOptionsPopupWindow";
	
	//Item
	private Item item;
	private Device MS_Device;
	//TrackDO
	private TrackDO trackDO;
	//TrackDoList
	private List<TrackDO> trackDOList;
	// TrackMetaData
	private JSONObject metadataTracks = null;
	
//	private int device_size = 0;
	
	private int ContentFlag =0;//1 = Item ,2 = TrackDO ,3 = TrackDoList

	
	public MusicSourceOptionsPopupWindow(Context context){
		super(context);
		this.mlog.switchLog = true;
		this.context = context;
		
//		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		if(DeviceProperty.isPhone()){
			createPhoneViewContent();
		}else{
			createPadViewContent();
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
	private void createPhoneViewContent() {
		
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
	private void createPadViewContent() {
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
		
		//設定 OPTION_Button 介面
		setOptionButtonViewSetting(OPTION_Button_1,"Play Now",0);
		setOptionButtonViewSetting(OPTION_Button_2,"Play Next",1);
		setOptionButtonViewSetting(OPTION_Button_3,"Replace Queue",1);
		setOptionButtonViewSetting(OPTION_Button_4,"Add To Queue",2);
		
		//設定LISTNER
		setPlayNowButtonListener(OPTION_Button_1);
		
		setPlayNextButtonListener(OPTION_Button_2);
		setReplaceQueueButtonListener(OPTION_Button_3);
		setAdd2QueueButtonListener(OPTION_Button_4);
		
		if(metadataTracks != null){
			
			OPTION_Button_2.setEnabled(false);
			OPTION_Button_4.setEnabled(false);
			
		}
		
	}
	
	private void setOptionButtonViewSetting(Button btnOption,String str,int number){
		
		if(DeviceProperty.isPhone()){
			btnOption.setBackgroundColor(Color.parseColor("#00000000"));
			TKBTool.fitsViewHeight(39, btnOption);
			TKBTool.fitsViewTextSize(10, btnOption);
			new TKBThreadReadStateListInAssets(context, "phone/pop/selecet_center_f.png", "phone/playlist/playlist_btn_n.png", btnOption, 4);
		}else{
			switch(number){
			case 0:
				TKBTool.fitsViewHeight(45, btnOption);	
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_01.png", "phone/playlist/playlist_btn_n.png", btnOption, 4);
				break;
			case 1:
				TKBTool.fitsViewHeight(43, btnOption);		
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_02.png", "phone/playlist/playlist_btn_n.png", btnOption, 4);
				break;
			case 2:
				TKBTool.fitsViewHeight(46, btnOption);		
				new TKBThreadReadStateListInAssets(context, "pad/Playlist/pls_pop_bt_03.png", "phone/playlist/playlist_btn_n.png", btnOption, 4);
				break;
			}	
			TKBTool.fitsViewTextSize(8, btnOption);
		}
		
		btnOption.setText(str);
		btnOption.setPadding(0, 0, 0, 0);
		btnOption.setGravity(Gravity.CENTER);
		btnOption.setTextColor(Color.WHITE);
		
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MusicSourceOptionsPopupWindow.this.dismiss();	
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MusicSourceOptionsPopupWindow.this.dismiss();					
			}
		});		
	}
	
	private void setPlayNowButtonListener(Button btnPlayNow){
		
		btnPlayNow.setOnClickListener(new View.OnClickListener() {
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
					TrackListPlayNow(); // AddDumpedTracksToQueue
					break;
				}
				
				MusicSourceOptionsPopupWindow.this.dismiss();	
			}
		});
		
	}
	
	private void ItemPlayNow(){
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
		Browse browse = new Browse(MS_Device.findService(new UDAServiceType("ContentDirectory")), item.getId(), BrowseFlag.METADATA, "*", 0, 1l, sortCriterion){
			@Override
			public void received(ActionInvocation ai, DIDLContent didl) {
				if(ai.getOutput().length<=0||didl.getItems().size()<=0){
					return;
				}
				//取得upnpServer
				AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
				//取得MR Device
				DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
				mlog.info(tag, "MR_Device = "+MR_Device);
				//取得MetaData								
				String MetaData = ai.getOutput()[0].toString();	
				mlog.info(tag, "MetaData = "+MetaData);
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
				//res顯示內容
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
		//取得upnpServer
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		//取得MR Device
		DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		//this.trackDO 當前的trackDO
	
	}
	private void TrackListPlayNow(){
		goAddDumpedTracksToQueue();
	}
	
	private void PlayMusic(){
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
	private void setPlayNextButtonListener(Button OPTION_Button_2){
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
				MusicSourceOptionsPopupWindow.this.dismiss();	
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void ItemPlayNext(){
	
		AGSContentDirectoryService serviceContentDirectory = new AGSContentDirectoryService(DeviceDisplayList.getChooseMediaRenderer().getDevice(), MusicSourceFragement.getMessageHandler());
		Action actionBrowse = serviceContentDirectory.getActionBrowse();
		
		ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
		
		ActionArgument argObjID = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_OBJECT_ID);
		ActionArgumentValue valObjID = new ActionArgumentValue(argObjID, item.getId());
		values.add(valObjID);
		
		ActionArgument argBrsFlag = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_BROWSE_FLAG);
		ActionArgumentValue valBrsFlag = new ActionArgumentValue(argBrsFlag, BrowseFlag.METADATA);
		values.add(valBrsFlag);
		
		ActionArgument argStrIndex = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_STARTING_INDEX);
		ActionArgumentValue valStrIndex = new ActionArgumentValue(argStrIndex, 0);
		values.add(valStrIndex);
		
		ActionArgument argReqCount = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_REQUESTED_COUNT);
		ActionArgumentValue valReqCount = new ActionArgumentValue(argReqCount, 1l);
		values.add(valReqCount);
		
		SortCriterion[] sortCriterion = new SortCriterion[]{new SortCriterion("+dc:title")};
		ActionArgument argSrtCriteria = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_SORT_CRITERIA);
		ActionArgumentValue valSrtCriteria = new ActionArgumentValue(argSrtCriteria, sortCriterion);
		values.add(valSrtCriteria);
		
		ActionArgument argFilter = actionBrowse.getInputArgument(ContentDirectoryServiceValues.ACTION_BROWSE_INPUT_FILTER);
		ActionArgumentValue valFilter = new ActionArgumentValue(argFilter, "*");
		values.add(valFilter);
		
		serviceContentDirectory.actBrowse(values.toArray(new ActionArgumentValue[values.size()]), new  BrowseMetaDataScuuessCaller());
		
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private class BrowseMetaDataScuuessCaller extends AGSActionSuccessCaller<Object>{
	
		@Override
		public Object call() throws Exception {
	
			AGSAVTransportService serviceAVTransport = new AGSAVTransportService(DeviceDisplayList.getChooseMediaRenderer().getDevice(), MusicSourceFragement.getMessageHandler());
			Action actionDoFirmwareUpgrade = serviceAVTransport.getActionAddTrackToQueue();
			
			ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
			
			ActionArgument argInstanceID = actionDoFirmwareUpgrade.getInputArgument(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE_INPUT_INSTANCE_ID);
			ActionArgumentValue valInstanceID = new ActionArgumentValue(argInstanceID, "0");
			values.add(valInstanceID);
			
			ActionArgument argTrackURI = actionDoFirmwareUpgrade.getInputArgument(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_URI);
			ActionArgumentValue valTrackURI = new ActionArgumentValue(argTrackURI, item.getFirstResource().getValue().toString());
			values.add(valTrackURI);
			
			String MetaData = ai.getOutput()[0].toString();
			ActionArgument argTrackURIMetaData = actionDoFirmwareUpgrade.getInputArgument(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_URI_META_DATA);
			ActionArgumentValue valType = new ActionArgumentValue(argTrackURIMetaData, MetaData);
			values.add(valType);
			
			ActionArgument argTrackNumber = actionDoFirmwareUpgrade.getInputArgument(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE_INPUT_TRACK_NUMBER);
			String position = DeviceDisplayList.getPositionCurrentTrack();
			Integer posInsert = Integer.parseInt(position) - 1;
			ActionArgumentValue valTrackNumber = new ActionArgumentValue(argTrackNumber, posInsert);
			values.add(valTrackNumber);
			
			ActionArgument argPlayNow = actionDoFirmwareUpgrade.getInputArgument(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE_INPUT_PLAY_NOW);
			ActionArgumentValue valPlayNow = new ActionArgumentValue(argPlayNow, false);
			values.add(valPlayNow);
			
			serviceAVTransport.actAddTrackToQueue(values.toArray(new ActionArgumentValue[values.size()]), null);
			
			return null;
			
		}

	}
	
	private void TrackPlayNext(){
		
	}
	private void TrackListPlayNext(){
		
	}
	
	private void setReplaceQueueButtonListener(Button OPTION_Button_3){
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
					TrackListReplayQueue(); // AddDumpedTracksToQueue
					break;
				}
				MusicSourceOptionsPopupWindow.this.dismiss();	
			}
		});
	}
	private void ItemReplayQueue(){
		
	}
	private void TrackReplayQueue(){
		
	}
	private void TrackListReplayQueue(){
		goAddDumpedTracksToQueue();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void goAddDumpedTracksToQueue() {
		
		AGSAVTransportService service = new AGSAVTransportService(DeviceDisplayList.getChooseMediaRenderer().getDevice()
													, MusicSourceFragement.getMessageHandler());
		
		Action action = service.getActionAddDumpedTracksToQueue();
		ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
		
		ActionArgument argInstanceID = action.getInputArgument(AVTransportServiceValues.ACTION_ADD_DUMPED_TRACKS_TO_QUEUE_INPUT_INSTANCE_ID);
		ActionArgumentValue valInstanceID = new ActionArgumentValue(argInstanceID, "0");
		values.add(valInstanceID);
		
		ActionArgument argTracksDIDL = action.getInputArgument(AVTransportServiceValues.ACTION_ADD_DUMPED_TRACKS_TO_QUEUE_INPUT_ALL_TRACKS_DIDL);
		ActionArgumentValue valTracksDIDL = new ActionArgumentValue(argTracksDIDL, metadataTracks.optString("MetaData"));
		values.add(valTracksDIDL);
		
		ActionArgument argApdCurQueue = action.getInputArgument(AVTransportServiceValues.ACTION_ADD_DUMPED_TRACKS_TO_QUEUE_INPUT_APPEND_TO_CURRENT_QUEUE);
		ActionArgumentValue valApdCurQueue = new ActionArgumentValue(argApdCurQueue, false);
		values.add(valApdCurQueue);
		
		ActionArgument argTrkNumber = action.getInputArgument(AVTransportServiceValues.ACTION_ADD_DUMPED_TRACKS_TO_QUEUE_INPUT_TRACK_NUMBER);
		ActionArgumentValue valTrkNumber = new ActionArgumentValue(argTrkNumber, -1);
		values.add(valTrkNumber);
		
		service.actAddDumpedTracksToQueue(values.toArray(new ActionArgumentValue[values.size()]), new AddDumpedTracksToQueueScuuessCaller());
		
	}
	private class AddDumpedTracksToQueueScuuessCaller extends AGSActionSuccessCaller<Object>{
		
		
		
	}
	
	private void setAdd2QueueButtonListener(Button OPTION_Button_4){
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
				
				
			
				//關閉FM_PopupWindow
				MusicSourceOptionsPopupWindow.this.dismiss();	
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
				//取得upnpServer
				AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
				//取得MR Device
				DeviceDisplay MR_Device = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
				mlog.info(tag, "MR_Device = "+MR_Device);
				//取得MetaData								
				String MetaData = arg0.getOutput()[0].toString();	
				mlog.info(tag, "MetaData = "+MetaData);
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
		//歸零
		this.item = null;
		this.MS_Device = null;
		//設定
		this.item = item;
		this.MS_Device = device;
	}
	public void SetTrack(TrackDO trackDO){
		ContentFlag = 2;
		//歸零
		this.trackDO = null;	
		//設定
		this.trackDO = trackDO;
		
	}
	public void SetTrackList(List<TrackDO> trackDOList){
		ContentFlag = 3;
		//歸零
		this.trackDOList = null;	
		//設定
		this.trackDOList = trackDOList;
		
	}
	public void SetTrackMetaData(JSONObject o) {
		ContentFlag = 3;
		this.metadataTracks = o;
	}
}

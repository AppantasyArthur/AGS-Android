package com.FAM.SETTING;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
import org.teleal.cling.model.types.UDAServiceId;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.GroupVO.Group;
import com.alpha.upnp.parser.SoundLastChangeDO;
import com.alpha.upnp.parser.SoundLastChangeHandler;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class FAM_PopupWindow extends PopupWindow {
	
	private View contentView;
	private LinearLayout OPTION_LLayout;
	
	
	private List<SoundHandler> ControlList = new ArrayList<SoundHandler>();
	
	private int device_size = 0;
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FAM_PopupWindow";
	public FAM_PopupWindow(Context context){
		super(context);
		this.mlog.switchLog = true;
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		if(DeviceProperty.isPhone()){
			Phone_CreateContentView();
			//設定內容
			this.setContentView(contentView);
			this.setWidth(TKBTool.getWidth(150));
			this.setHeight(TKBTool.getWidth(140));
		}else{
			PAD_CreateContentView();
			//設定內容
			this.setContentView(contentView);
			this.setWidth(TKBTool.getHeight(200));
			this.setHeight(TKBTool.getHeight(250));
		}	
		
		//設定back鍵  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//設定Outside dismiss
		this.setOutsideTouchable(true);
		
	}
	
	private void Phone_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewWidth(130, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout).getLayoutParams().height = TKBTool.getWidth(120);
		//OPTION_LLayout
		OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FAM_PopupWindow_RLayout_OPTION_LLayout);	
		mlog.info(TAG, "CreateContentView");
	}

	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(220, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout).getLayoutParams().width = TKBTool.getHeight(180);
		//OPTION_LLayout
		OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FAM_PopupWindow_RLayout_OPTION_LLayout);	
		mlog.info(TAG, "CreateContentView");
	}
	private void CreateOptionButtons(LinearLayout OPTION_LLayout,List<DeviceDisplay> deviceList){
		//歸零
		OPTION_LLayout.removeAllViews();

		if(!ControlList.isEmpty()){
			for(SoundHandler handler:ControlList){
				SubscriptionCallback Device_SoundCallBack = handler.Device_SoundCallBack;
				if(Device_SoundCallBack!=null){
					Device_SoundCallBack.end();
				}				
			}
			ControlList.clear();
		}
		
		//加入新的
		for(int i =0;i<deviceList.size();i++){
			//新建ViewHandler			
			ViewHandler viewHandler = new ViewHandler();
			//findView
			View sound_View = LayoutInflater.from(context).inflate(R.layout.fam_popupwindow_soundcell, null);
			viewHandler.SoundCell_RLayout = (RelativeLayout)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout);
			viewHandler.sound_IView = (ImageView)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout_SOUND_IView);
			viewHandler.sound_SeekBar = (SeekBar)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout_SOUND_SeekBar);
			if(DeviceProperty.isPhone()){
				Phone_VIEW_SETTING(viewHandler);		
			}else{
				PAD_VIEW_SETTING(viewHandler);		
			}
				
			
			ControlList.add(new SoundHandler(viewHandler,deviceList.get(i)));
			OPTION_LLayout.addView(sound_View);
		}
	}	
	
	private void Phone_VIEW_SETTING(ViewHandler viewHandler) {
		//SoundCell RLayout
		viewHandler.SoundCell_RLayout.setLayoutParams(new LayoutParams(TKBTool.getWidth(180),TKBTool.getWidth(28)));
		//Sound ImageView
		TKBTool.fitsViewWidth(23,viewHandler.sound_IView);
		viewHandler.sound_IView.getLayoutParams().height = TKBTool.getWidth(23);
		TKBTool.fitsViewLeftMargin(7, viewHandler.sound_IView);		
		//Sound SeekBar
		TKBTool.fitsViewWidth(90, viewHandler.sound_SeekBar);
		viewHandler.sound_SeekBar.getLayoutParams().height = TKBTool.getWidth(23);		
		TKBTool.fitsViewLeftMargin(4, viewHandler.sound_SeekBar);
		viewHandler.sound_SeekBar.setPadding(TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(14), TKBTool.getWidth(16), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		viewHandler.sound_SeekBar.setThumb(myThumb);		
	}

	private void PAD_VIEW_SETTING(ViewHandler viewHandler) {
		//SoundCell RLayout
		viewHandler.SoundCell_RLayout.setLayoutParams(new LayoutParams(TKBTool.getHeight(180),TKBTool.getHeight(50)));
		//Sound ImageView
		TKBTool.fitsViewHeight(37,viewHandler.sound_IView);
		viewHandler.sound_IView.getLayoutParams().width = TKBTool.getHeight(42);
		TKBTool.fitsViewLeftMargin(5, viewHandler.sound_IView);
		//Sound SeekBar
		TKBTool.fitsViewHeight(36, viewHandler.sound_SeekBar);
		viewHandler.sound_SeekBar.getLayoutParams().width = TKBTool.getHeight(110);		
		TKBTool.fitsViewLeftMargin(10, viewHandler.sound_SeekBar);
		viewHandler.sound_SeekBar.setPadding(TKBTool.getHeight(15), TKBTool.getHeight(8), TKBTool.getHeight(15), TKBTool.getHeight(8));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), TKBTool.getHeight(23), TKBTool.getHeight(27), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		viewHandler.sound_SeekBar.setThumb(myThumb);
	}
	
	private class SoundHandler{
		
		private ViewHandler viewHandler;
		private DeviceDisplay deviceDisplay;
		private SubscriptionCallback Device_SoundCallBack;
		
		private AndroidUpnpService upnpServer;	
		
		public SoundHandler(ViewHandler viewHandler,DeviceDisplay deviceDisplay){
			this.upnpServer = ((MainFragmentActivity)context).getUPnPService();		
			this.viewHandler = viewHandler;
			this.deviceDisplay = deviceDisplay;
			CreateSoundCallBack();
			SetSeekBarLISTNER();
		}
		
		private void CreateSoundCallBack() {
			Device device = this.deviceDisplay.getDevice();	
			if(device!=null){
				Service RenderingControlService = device.findService(new UDAServiceId("RenderingControl"));
				if(RenderingControlService!=null){
					Device_SoundCallBack = new SubscriptionCallback(RenderingControlService){
						@Override
						protected void ended(GENASubscription arg0,	CancelReason arg1, UpnpResponse arg2) {
						}

						@Override
						protected void established(GENASubscription arg0) {							
						}

						@Override
						protected void eventReceived(GENASubscription arg0) {							
							Map<String, StateVariableValue> values = arg0.getCurrentValues();
							StateVariableValue status = values.get("LastChange");
							
							mlog.info(TAG, "RenderingControl size = "+status.toString());
							SoundLastChangeDO soundLastChangeDO = _parseVolume(status.toString());
							
							if(soundLastChangeDO!=null&&soundLastChangeDO.getVolume()!=null){
								mlog.info(TAG, "values size = "+soundLastChangeDO.getVolume());
								mlog.info(TAG, "values size = "+soundLastChangeDO.getMute());
								SoundHandler.this.viewHandler.sound_SeekBar.setProgress(soundLastChangeDO.getVolume());
							}							
						}

						@Override
						protected void eventsMissed(GENASubscription arg0,	int arg1) {
						}

						@Override
						protected void failed(GENASubscription arg0, UpnpResponse arg1, Exception arg2, String arg3) {
						
							
						}						
					};
					upnpServer.getControlPoint().execute(Device_SoundCallBack);
				}
			}		
		}
		private SoundLastChangeDO _parseVolume(String xml){
			SoundLastChangeDO data = null;   

			  // sax stuff   
			  try { 			  
				SAXParserFactory spf = SAXParserFactory.newInstance();   
			    SAXParser sp = spf.newSAXParser();   
			    XMLReader xr = sp.getXMLReader();  

			    SoundLastChangeHandler dataHandler = new SoundLastChangeHandler();   
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
		private void SetSeekBarLISTNER() {
			viewHandler.sound_SeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
				int position = 0;
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					setSound_Image(progress);
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
					
					SetSoundPosition(SoundHandler.this.deviceDisplay.getDevice(),stopPosition);
				}
			});
		}
		private void setSound_Image(int Vol){
			if(DeviceProperty.isPhone()){
				if(Vol ==0){
					new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_no.png",viewHandler.sound_IView, 1);
				}else if(Vol>=1&&Vol<=50){
					new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_02.png",viewHandler.sound_IView, 1);
				}else if(Vol>=51&&Vol<=99){
					new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_01.png",viewHandler.sound_IView, 1);
				}else{
					new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume.png",viewHandler.sound_IView, 1);
				}
			}else{
				if(Vol ==0){
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_mute.png",viewHandler.sound_IView, 1);
				}else if(Vol>=1&&Vol<=50){
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_01.png",viewHandler.sound_IView, 1);
				}else if(Vol>=51&&Vol<=99){
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_02.png",viewHandler.sound_IView, 1);
				}else{
					new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_03.png",viewHandler.sound_IView, 1);
				}
			}			
		}
	}
	private void SetSoundPosition(Device device,int position){
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		if(device!=null){
			Service RenderingControlService = device.findService(new UDAServiceId("RenderingControl"));
			if(RenderingControlService!=null){
				Action setVolumeAction = RenderingControlService.getAction("SetVolume");
				if(setVolumeAction!=null){
					ActionArgumentValue[] values = new ActionArgumentValue[3];
					//GET ActionArgument 
					ActionArgument InstanceID = setVolumeAction.getInputArgument("InstanceID");
					ActionArgument Channel = setVolumeAction.getInputArgument("Channel");
					ActionArgument DesiredVolume = setVolumeAction.getInputArgument("DesiredVolume");
					if(InstanceID!=null&&Channel!=null&&DesiredVolume!=null){
						values[0] =new ActionArgumentValue(InstanceID, "0");
						values[1] =new ActionArgumentValue(Channel, "Master");
						values[2] =new ActionArgumentValue(DesiredVolume, ""+position);
						
						ActionInvocation ai = new ActionInvocation(setVolumeAction,values);
						
						ActionCallback SetVolumeActionCallBack = new ActionCallback(ai){
							@Override
							public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
								mlog.info(TAG, "SetVolumeActionCallBack failure = " + arg2);
								mlog.info(TAG, "UPnP failure response = " + arg1);
							}
							@Override
							public void success(ActionInvocation arg0) {									
								mlog.info(TAG, "SetVolumeActionCallBack success");												
								for(ActionArgumentValue aav :arg0.getOutput()){
									mlog.info(TAG, "aav ="+aav.toString());
								}
							}											
						};
						upnpServer.getControlPoint().execute(SetVolumeActionCallBack);	
					}
				}
			}
		}		
	}
	private class ViewHandler{		
		private RelativeLayout SoundCell_RLayout;
		private ImageView sound_IView;
		private SeekBar sound_SeekBar;
	}
	
	@Override
	public void showAsDropDown(View anchor) {
		
		DeviceDisplay ChooseMediaRenderer = ((MainFragmentActivity)context).getDeviceDisplayList().getChooseMediaRenderer();
		if(ChooseMediaRenderer==null){
			//沒有選取
		}else{
			//有選取  顯示畫面
			super.showAsDropDown(anchor);
			
			List<DeviceDisplay> soundDevicesList = new ArrayList<DeviceDisplay>();
			//add 選取的DeviceDisplay
			soundDevicesList.add(ChooseMediaRenderer);
			//add Slave的DeviceDisplay
			Group groups =ChooseMediaRenderer.getGroupVO().getGroup();
			for(int j=1;j<groups.getMembers().size();j++){
				DeviceDisplay searchDeviceDisplay = ((MainFragmentActivity)context).getDeviceDisplayList().GetDeviceDisplayByUDN(groups.getMembers().get(j).getUdn());
				soundDevicesList.add(searchDeviceDisplay);
			}			
			CreateOptionButtons(OPTION_LLayout, soundDevicesList);
		}		
	}
}

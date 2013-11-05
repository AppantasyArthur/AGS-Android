package com.alpha.setting.firmwareupdate;

import java.util.ArrayList;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.fragments.settings.FirmwareUpdateSettingFragment;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.FirmwareInfoParser;
import com.alpha.upnp.parser.FirmwareInfoVO;
import com.alpha.upnp.service.AGSActionSuccessCaller;
import com.alpha.upnp.service.AGSFirmwareUpdateService;
import com.alpha.upnp.value.FirmwareUpdateServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSF_VIEW_LISTNER
public class FirmwareUpdateSettingViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "FirmwareUpdateSettingViewListener";
	private int device_size = 0;
	private View fragmentMainView;
	public FirmwareUpdateSettingViewListener(Context context, int device_size) {
		
		//this.fragmentMainView = fragmentMainView;
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
		
	}

	public void setBackButtonListener(Button buttonBack, final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	
	public void setUpdateAllButtonListener(Button buttonUpdateAll, final DeviceDisplay chooseDeviceDisplay) {
		if(DeviceProperty.isPhone()){
			buttonUpdateAll.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					
					doFirmwareUpgrade(chooseDeviceDisplay);
					
				}
			});	
		}else{ // Pad
			buttonUpdateAll.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					upgradeFirmware(chooseDeviceDisplay);
					
				}
			});	
		}	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doFirmwareUpgrade(final DeviceDisplay chooseDeviceDisplay) {
		
		AGSFirmwareUpdateService s = new AGSFirmwareUpdateService(chooseDeviceDisplay.getDevice(), FirmwareUpdateSettingFragment.getMessageHandler());
		Action actionDoFirmwareUpgrade = s.getActionDoFirmwareUpgrade();
		
		ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
		
		ActionArgument argNewVersion = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_NEWVERSION);
		ActionArgumentValue valNewVersion = new ActionArgumentValue(argNewVersion, version);
		values.add(valNewVersion);
		
		ActionArgument argPath = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_PATH);
		ActionArgumentValue valPath = new ActionArgumentValue(argPath, path);
		values.add(valPath);
		
		ActionArgument argType = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_TYPE);
		ActionArgumentValue valType = new ActionArgumentValue(argType, type);
		values.add(valType);
		
		s.actDoFirmwareUpgrade(values.toArray(new ActionArgumentValue[values.size()]), new UpgradeFirmwareSettingScuuessCaller());
		
		// show progress bar
		progressDialog = new ProgressDialog(fragmentMainView.getContext());
		progressDialog.setMax(100);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("Firmware Upgrading ... ");
		progressDialog.incrementProgressBy(-progressDialog.getProgress());
		progressDialog.setMessage(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_STATE_CHECKING);
		progressDialog.show();
		
		handler = new Handler() {  
            @Override  
            public void handleMessage(Message msg) {  
                switch(msg.what) {  
                    case INCREASE:
                    	
                    	if(msg.obj != null){
                    		
                    		 state = (String)msg.obj;
		                     progressDialog.setMessage(state);
		                     if(state.equalsIgnoreCase(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_STATE_REBOOTING)){
		                    	 progressDialog.dismiss();  
		                    	 getNewFirmwareInfo(chooseDeviceDisplay, fragmentMainView);
		                     }
		                     
                    	}
                    	
	                    if(msg.arg1 != 0)	 {
	                    	progressDialog.setProgress(msg.arg1);
	                    }
                    	
                        break;  
                }  
                super.handleMessage(msg);  
            }  
        }; 
        MainFragmentActivity.getDeviceDisplayList().setFirmwareUpgradeHandler(handler);
		
	}
	private class UpgradeFirmwareSettingScuuessCaller extends AGSActionSuccessCaller<Object>{

		// do nothing now, Arthur
		
	}
	
	private String state;
	private final int INCREASE = 0;
	private ProgressDialog progressDialog;
	private Handler handler;  
	private String path;
	private String type;
	private String version;
	private String versionCur;
	
	@SuppressWarnings("rawtypes")
	protected void upgradeFirmware(final DeviceDisplay chooseDeviceDisplay) {
		
		Device mediaRender = chooseDeviceDisplay.getDevice();
		if(mediaRender != null){
			
			ServiceType typeFirmwareUpgradeService = new ServiceType(FirmwareUpdateServiceValues.DEFAULT_NAMESPACE, FirmwareUpdateServiceValues.SERVICE_NAME);
			Service serviceFirmwareUpgrade = mediaRender.findService(typeFirmwareUpgradeService);
			if(serviceFirmwareUpgrade != null){
				
				final Action actionDoFirmwareUpgrade = serviceFirmwareUpgrade.getAction(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE);
				if(actionDoFirmwareUpgrade != null){
					
					ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
					
					ActionArgument argNewVersion = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_NEWVERSION);
					ActionArgumentValue valNewVersion = new ActionArgumentValue(argNewVersion, version);
					values.add(valNewVersion);
					
					ActionArgument argPath = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_PATH);
					ActionArgumentValue valPath = new ActionArgumentValue(argPath, path);
					values.add(valPath);
					
					ActionArgument argType = actionDoFirmwareUpgrade.getInputArgument(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_INPUT_TYPE);
					ActionArgumentValue valType = new ActionArgumentValue(argType, type);
					values.add(valType);
					
					@SuppressWarnings("unchecked")
					ActionInvocation invokeDoFirmwareUpgrade = new ActionInvocation(actionDoFirmwareUpgrade , values.toArray(new ActionArgumentValue[values.size()]));
					ActionCallback callbackDoFirmwareUpgrade = new ActionCallback(invokeDoFirmwareUpgrade){

						@Override
						public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
							Log.d(tag, "callbackDoFirmwareUpgrade failure - ");
							Log.d(tag, "UPnPResponse : " + ur);
							Log.d(tag, "other messgae : " + str);
							progressDialog.dismiss();
						}

						@Override
						public void success(ActionInvocation ai) {
						
//							new Thread() {  
//			                    public void run() {  
//			                        for(int i=0; i<=100; i++) {  
//			                            handler.sendEmptyMessage(INCREASE);  
//			                            if(progressDialog.getProgress() >= 100) {  
//			                            	progressDialog.dismiss();
//			                                break;  
//			                            }  
//			                            try {  
//			                                Thread.sleep(50);  
//			                            } catch (InterruptedException e) {  
//			                                e.printStackTrace();  
//			                            }  
//			                        }  
//			                    }  
//			                }.start(); 
							
							//ActionArgumentValue output = ai.getOutput(SystemServiceValues.ACTION_IDENTIFY_SPEAKER_OUTPUT);
							//String result = (String)output.getValue();
							//Log.d(tag, result);
							//Toast.makeText(context, result, Toast.LENGTH_LONG).show();
						}
						
					};
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					upnpServer.getControlPoint().execute(callbackDoFirmwareUpgrade);
					
					// show progress bar
					progressDialog = new ProgressDialog(fragmentMainView.getContext());
					progressDialog.setMax(100);
					progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					progressDialog.setTitle("Firmware Upgrading ... ");
					progressDialog.incrementProgressBy(-progressDialog.getProgress());
					progressDialog.setMessage(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_STATE_CHECKING);
					progressDialog.show();
					
					handler = new Handler() {  
			            @Override  
			            public void handleMessage(Message msg) {  
			                switch(msg.what) {  
			                    case INCREASE:
			                    	
			                    	if(msg.obj != null){
			                    		
			                    		 state = (String)msg.obj;
					                     progressDialog.setMessage(state);
					                     if(state.equalsIgnoreCase(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE_EVENT_STATE_REBOOTING)){
					                    	 progressDialog.dismiss();  
					                    	 getNewFirmwareInfo(chooseDeviceDisplay, fragmentMainView);
					                     }
					                     
			                    	}
			                    	
				                    if(msg.arg1 != 0)	 {
				                    	progressDialog.setProgress(msg.arg1);
				                    }
			                    	
			                        break;  
			                }  
			                super.handleMessage(msg);  
			            }  
			        }; 
			        ((MainFragmentActivity)context).getDeviceDisplayList().setFirmwareUpgradeHandler(handler);
					
				}else{
					Log.d(tag, "dofirmwareupgrade action is null");
				}
				
			}else{
				Log.d(tag, "firmwareupgrade service is null");
			}
			
		}else{
			Log.d(tag, "mediarender device is null");
		}
		
	}
	
	//private Runnable changeMessage; // 
	
	@SuppressWarnings("rawtypes")
	public void getNewFirmwareInfo(DeviceDisplay chooseDeviceDisplay, final View fragmentMainView) { // getNewFirmwareInfo
		
		this.fragmentMainView = fragmentMainView;
		final Device mediaRender = chooseDeviceDisplay.getDevice();
		if(mediaRender != null){
			
			ServiceType typeFirmwareUpgradeService = new ServiceType(FirmwareUpdateServiceValues.DEFAULT_NAMESPACE, FirmwareUpdateServiceValues.SERVICE_NAME);
			Service serviceFirmwareUpgrade = mediaRender.findService(typeFirmwareUpgradeService);
			if(serviceFirmwareUpgrade != null){
				
				Action actionGetNewFirmwareInfo = serviceFirmwareUpgrade.getAction(FirmwareUpdateServiceValues.ACTION_NEW_FIRMWARE_INFO);
				if(actionGetNewFirmwareInfo != null){
					
					@SuppressWarnings("unchecked")
					ActionInvocation invokeGetNewFirmwareInfo = new ActionInvocation(actionGetNewFirmwareInfo , null);
					ActionCallback callbackGetNewFirmwareInfo = new ActionCallback(invokeGetNewFirmwareInfo){

						@Override
						public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
							Log.d(tag, "callbackGetNewFirmwareInfo failure - ");
							Log.d(tag, "UPnPResponse : " + ur);
							Log.d(tag, "other messgae : " + str);
						}

						@Override
						public void success(ActionInvocation ai) {
							
							ActionArgumentValue[] outputs =  ai.getOutput();
							if(outputs.length > 0){
								
								ActionArgumentValue output = ai.getOutput(FirmwareUpdateServiceValues.ACTION_NEW_FIRMWARE_INFO_OUTPUT);
								String xml = (String) output.getValue();
								Log.d(tag, xml);
								
								FirmwareInfoParser parser = new FirmwareInfoParser();
								ArrayList<FirmwareInfoVO> datas = parser.parse(xml);
								
								if(datas.size() > 0){
									
									FirmwareInfoVO vo = datas.get(0); // default first element
									
									type = vo.getType();
									path = vo.getPath();
									version = vo.getNewVersion();
									versionCur = vo.getCurVersion();
									
									//handlerUpdateVersion.post(updateVersion);
									Thread thread = new Thread(new Runnable() {  
							           
							            @Override  
							            public void run() {  
							                
							            	//Message msg = handlerUpdateVersions.obtainMessage(UPDATE_VERSION);
							            	handlerUpdateVersions.sendEmptyMessage(UPDATE_VERSION);
							            	//handlerUpdateVersions.sendEmptyMessage();
							            	
							            	
							        }});  
							        thread.start();
									
								}else{
									Log.i(xml, mediaRender + " no new firmware ");
								}
								
							}
							
						}
						
					};
					
					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
					upnpServer.getControlPoint().execute(callbackGetNewFirmwareInfo);
					
				}else{
					Log.d(tag, "getnewfirmwareinfo action is null");
				}
				
			}else{
				Log.d(tag, "firmwareupgrade service is null");
			}
			
		}else{
			Log.d(tag, "mediarender device is null");
		}
		
	}
	
	private int UPDATE_VERSION = 0;
	private Handler handlerUpdateVersions = new Handler(){

		@Override
		public void handleMessage(Message msg) {	
			
			if(msg.what == UPDATE_VERSION){
				
				//FirmwareInfoVO vo = (FirmwareInfoVO)msg.obj;
				
				if(DeviceProperty.isPhone()){
					
					TextView newVersion = (TextView)fragmentMainView.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView);
					
					if(version.trim().isEmpty()){
						fragmentMainView.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button).setEnabled(false);
						version = "N/A";
					}
					
					newVersion.setText(version);
					
					// FiramwareUpgradeSettingCurrentVesrionNumber
					TextView currentVersion = (TextView)fragmentMainView.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView);
					currentVersion.setText("Current Vision : " + versionCur);
					
				}else{ // Pad
					
					TextView newVersion = (TextView)fragmentMainView.findViewById(R.id.FiramwareUpgradeSettingNewVesrionNumber);
					
					if(version.trim().isEmpty()){
						fragmentMainView.findViewById(R.id.FSF_RLayout_RLayout_Update_Button).setEnabled(false);
						version = "N/A";
					}
					
					newVersion.setText(version);
					
					// FiramwareUpgradeSettingCurrentVesrionNumber
					TextView currentVersion = (TextView)fragmentMainView.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber);
					currentVersion.setText("Current Vision : " + versionCur);
					
					
				}
				
				super.handleMessage(msg);
				
			}
			
		}
		
	};
	
}

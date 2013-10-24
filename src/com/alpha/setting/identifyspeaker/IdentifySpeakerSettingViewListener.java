package com.alpha.setting.identifyspeaker;

import org.teleal.cling.model.action.ActionArgumentValue;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.fragments.settings.IdentifySpeakerSettingFragment;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.service.AGSActionSuccessCaller;
import com.alpha.upnp.service.AGSSytemService;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSI_VIEW_LISTNER
public class IdentifySpeakerSettingViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "IdentifySpeakerSettingViewListener";
	private int device_size = 0;
	public IdentifySpeakerSettingViewListener(Context context, int device_size) {
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
	
	public void setIdentifySpeakerListViewListener(ListView listIdentifySpeaker){
		
		if(DeviceProperty.isPhone()){
			listIdentifySpeaker.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					DeviceDisplay devicedisplay = (DeviceDisplay)parent.getItemAtPosition(position);
					identifySpeaker(devicedisplay);
				}
			});
		}else{
			listIdentifySpeaker.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					DeviceDisplay devicedisplay = (DeviceDisplay)parent.getItemAtPosition(position);
					identifySpeaker(devicedisplay);
				}
			});
		}
		
	}
	
	private void identifySpeaker(DeviceDisplay devicedisplay){
		
		AGSSytemService s = new AGSSytemService(devicedisplay.getDevice(), IdentifySpeakerSettingFragment.getMessageHandler());
		s.actIdentifySpeaker(null, new IdentifySpeakerSuccessCaller());
		
	}
	private class IdentifySpeakerSuccessCaller extends AGSActionSuccessCaller<Object>{

		@SuppressWarnings("rawtypes")
		@Override
		public Object call() throws Exception {
			
			ActionArgumentValue output = ai.getOutput(SystemServiceValues.ACTION_IDENTIFY_SPEAKER_OUTPUT);
			String result = (String)output.getValue();
			Log.d(tag, result);
			
			return null;
			
		}
		
	}
	
	
//	@SuppressWarnings("rawtypes")
//	private void identifySpeaker(DeviceDisplay devicedisplay){
//		
//		Device mediaRender = devicedisplay.getDevice();
//		if(mediaRender != null){
//			
//			ServiceType typeSystemService = new ServiceType(SystemServiceValues.DEFAULT_NAMESPACE, SystemServiceValues.SERVICE_NAME);
//			Service serviceSystem = mediaRender.findService(typeSystemService);
//			if(serviceSystem != null){
//				
//				Action actionIdentifySpeaker = serviceSystem.getAction(SystemServiceValues.ACTION_IDENTIFY_SPEAKER);
//				if(actionIdentifySpeaker != null){
//					
//					@SuppressWarnings("unchecked")
//					ActionInvocation invocationIdentifySpeaker = new ActionInvocation(actionIdentifySpeaker , null);
//					ActionCallback callbackIdentifySpeaker = new ActionCallback(invocationIdentifySpeaker){
//
//						@Override
//						public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
//							Log.d(tag, "callbackSystemInfo failure - ");
//							Log.d(tag, "UPnPResponse : " + ur);
//							Log.d(tag, "other messgae : " + str);
//						}
//
//						@Override
//						public void success(ActionInvocation ai) {
//							ActionArgumentValue output = ai.getOutput(SystemServiceValues.ACTION_IDENTIFY_SPEAKER_OUTPUT);
//							String result = (String)output.getValue();
//							Log.d(tag, result);
//							//Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//						}
//						
//					};
//					
//					AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
//					upnpServer.getControlPoint().execute(callbackIdentifySpeaker);
//					
//				}else{
//					Log.d(tag, "identifyspeaker action is null");
//				}
//				
//			}else{
//				Log.d(tag, "system service is null");
//			}
//			
//		}else{
//			Log.d(tag, "mediarender device is null");
//		}
//		
//	}
	
}

package com.alpha.setting.sleeptimer;


import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnp.AGSActionCallback;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSS_VIEW_LISTNER
public class SleepTimerSettingViewListener {
	
	//private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "SleepTimerViewListener";
	//private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	
	private Handler handlerMessager;
	public SleepTimerSettingViewListener(Handler handlerMessager/*, int device_size*/, DeviceDisplay chooseDeviceDisplay) {
		
		this.handlerMessager = handlerMessager;
		
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		//this.context = context;
		this.mlog.switchLog = true;
		//this.device_size = device_size;
	}	
	
	public void setBackButtonListener(Button buttonBack,final FragmentManager fragmentManager){
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
	
	public void setSleepTimerListViewListener(ListView listSleepTimer){
		if(DeviceProperty.isPhone()){
			listSleepTimer.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SleepTimerSettingPhoneAdapter baseAdapter = (SleepTimerSettingPhoneAdapter)parent.getAdapter();
					baseAdapter.setChooseItem(position);//顯示打勾
					setSleepTimer(position);
				}
			});
		}else{
			listSleepTimer.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					SleepTimerSettingPadAdapter baseAdapter = (SleepTimerSettingPadAdapter)parent.getAdapter();
					baseAdapter.setChooseItem(position);//顯示打勾
					setSleepTimer(position);
				}
			});
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setSleepTimer(int position) {
		
		Device mediaRender = chooseDeviceDisplay.getDevice();
		if(mediaRender != null){
			
			ServiceType typeSystemService = new ServiceType(SystemServiceValues.DEFAULT_NAMESPACE, SystemServiceValues.SERVICE_NAME);
			Service serviceSystem = mediaRender.findService(typeSystemService);
			if(serviceSystem != null){
				
				Action actionIdentifySpeaker = serviceSystem.getAction(SystemServiceValues.ACTION_SLEEP_TIMER_SET);
				if(actionIdentifySpeaker != null){
					
					ActionArgumentValue[] values = new ActionArgumentValue[1];
					
					ActionArgument argumentSleepTimerOption = actionIdentifySpeaker.getInputArgument(SystemServiceValues.ACTION_SLEEP_TIMER_INPUT_SLEEPTIMEROPTION);
					String option = SystemServiceValues.getSleepTimerOptionsText(position);
					values[0] = new ActionArgumentValue(argumentSleepTimerOption, option);
					
					ActionInvocation invocationIdentifySpeaker = new ActionInvocation(actionIdentifySpeaker , values);
					AGSActionCallback callbackIdentifySpeaker = new AGSActionCallback(invocationIdentifySpeaker, tag, handlerMessager){

						@Override
						public void success(ActionInvocation ai) {
							
							ActionArgumentValue output = ai.getOutput(SystemServiceValues.DEFAULT_OUTPUT_RESULT);
							String result = (String)output.getValue();
							Log.d(tag, result);
							
							Message msg = handlerMessager.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
							msg.obj = result;
							handlerMessager.sendMessage(msg);
							
							//showMessage(result);
							
						}
						
					};
					
					AndroidUpnpService upnpServer = MainFragmentActivity.getServiceAndroidUPnP(); //((MainFragmentActivity)context).getUPnPService();
					upnpServer.getControlPoint().execute(callbackIdentifySpeaker);
					
				}else{
					Log.d(tag, "setsleeptimer action is null");
				}
				
			}else{
				Log.d(tag, "system service is null");
			}
			
		}else{
			Log.d(tag, "mediarender device is null");
		}
		
	}
	
}

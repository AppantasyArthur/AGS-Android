package com.alpha.upnp.service;

import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alpha.upnp.AGSActionCallback;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;

@SuppressWarnings("rawtypes")
public class AGSSsytemService {
	
	private final String tag = "AGSSsytemService";

	// service
	private final Service serviceSystem;
	
	// action
	private Action actionSystemInfo;
	private Action actionIdentifySpeaker;

	// controlled device
	//private final RemoteDevice device;
	private final Handler handlerMessage;
	
	public AGSSsytemService(Device device, Handler handlerMessage){
			
		this.handlerMessage = handlerMessage;
		
		String systemserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
		String systemserviceType = SystemServiceValues.SERVICE_NAME;
		ServiceType typeSystemService = new ServiceType(systemserviceNamespace, systemserviceType);
		
		serviceSystem = device.findService(typeSystemService);
		if(serviceSystem == null){
			
			String msgWarn = "system service is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
	}

	public void actIdentifySpeaker(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceSystem == null)
			return;
		
		if(actionIdentifySpeaker == null){
			
			actionIdentifySpeaker = serviceSystem.getAction(SystemServiceValues.ACTION_IDENTIFY_SPEAKER);
			if(actionIdentifySpeaker == null){
				
				String msgWarn = "identify speaker action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
				return;
				
			}
			
		}
		
		ActionInvocation invocation = new ActionInvocation(actionIdentifySpeaker , values);
		AGSActionCallback callback = new AGSActionCallback(invocation, tag, handlerMessage){

			@Override
			public void success(ActionInvocation ai) {
				try {
					callerSuccessFunction.setActionInvocation(ai); // 
					callerSuccessFunction.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		MainFragmentActivity.getServiceAndroidUPnP().getControlPoint().execute(callback);
		
	}
	
	@SuppressWarnings( "unchecked" )
	public void actGetSystemInfo(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceSystem == null)
			return;
		
		if(actionSystemInfo == null){
			
			actionSystemInfo = serviceSystem.getAction(SystemServiceValues.ACTION_SYSTEM_INFO);
			if(actionSystemInfo == null){
				
				String msgWarn = "get system info action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
				return;
				
			}
			
		}
		
		ActionInvocation invocationSystemInfo = new ActionInvocation(actionSystemInfo , values);
		AGSActionCallback callbackSystemInfo = new AGSActionCallback(invocationSystemInfo, tag, handlerMessage){

			@Override
			public void success(ActionInvocation ai) {
				try {
					callerSuccessFunction.setActionInvocation(ai); // 
					callerSuccessFunction.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		MainFragmentActivity.getServiceAndroidUPnP().getControlPoint().execute(callbackSystemInfo);
		
	}
	
}

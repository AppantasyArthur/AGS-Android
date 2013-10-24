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

@SuppressWarnings({"rawtypes", "unchecked"})
public class AGSSytemService {
	
	private final String tag = "AGSSytemService";

	// service
	private final Service serviceSystem;
	
	// action
	private Action actionSystemInfo;
	private Action actionIdentifySpeaker;
	
	private Action actionSetupWireless;
	public Action getActionSetupWireless() {
		
		if(actionSetupWireless == null){
			
			actionSetupWireless = serviceSystem.getAction(SystemServiceValues.ACTION_WIRELESS_SETUP);
			if(actionSetupWireless == null){
				
				String msgWarn = "setup wireless action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
				return null;
				
			}
			
		}
		
		return actionSetupWireless;
		
	}

	private Action actionGetCurrentSSID;
	public Action getActionGetCurrentSSID() {
		
		if(actionGetCurrentSSID == null){
			
			actionGetCurrentSSID = serviceSystem.getAction(SystemServiceValues.ACTION_GET_CURRENT_SSID);
			if(actionGetCurrentSSID == null){
				
				String msgWarn = "get current ssid action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
				return null;
				
			}
			
		}
		
		return actionGetCurrentSSID;
		
	}

	// controlled device
	//private final RemoteDevice device;
	private final Handler handlerMessage;
	
	public AGSSytemService(Device device, Handler handlerMessage){
			
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
		
	}
	
	public void actGetCurrentSSID(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceSystem == null)
			return;
		
		if(getActionGetCurrentSSID() == null){
			return;
		}
		
		ActionInvocation invocation = new ActionInvocation(actionGetCurrentSSID , values);
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
	
	public void actSetupWireless(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceSystem == null)
			return;
		
		if(getActionSetupWireless() == null){
			return;
		}
		
		ActionInvocation invocation = new ActionInvocation(getActionSetupWireless() , values);
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

	public void actIdentifySpeaker(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceSystem == null)
			return;
		
		if(actionIdentifySpeaker == null){
			return;
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

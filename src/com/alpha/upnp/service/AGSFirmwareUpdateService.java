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
import com.alpha.upnp.value.FirmwareUpdateServiceValues;
import com.alpha.upnpui.MainFragmentActivity;

public class AGSFirmwareUpdateService {

	private final String tag = "AGSFirmwareUpdateService";

	// service
	@SuppressWarnings("rawtypes")
	private final Service serviceFirmwareUpgrade;
	
	// action
	@SuppressWarnings("rawtypes")
	private Action actionDoFirmwareUpgrade;

	// controlled device
	private final Handler handlerMessage;
	
	@SuppressWarnings("rawtypes")
	public AGSFirmwareUpdateService(Device device, Handler handlerMessage){
		
		this.handlerMessage = handlerMessage;
		
		String namespace = FirmwareUpdateServiceValues.DEFAULT_NAMESPACE;
		String name = FirmwareUpdateServiceValues.SERVICE_NAME;
		ServiceType type = new ServiceType(namespace, name);
		
		serviceFirmwareUpgrade = device.findService(type);
		if(serviceFirmwareUpgrade == null){
			
			String msgWarn = "firmware upgrade service is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
		actionDoFirmwareUpgrade = serviceFirmwareUpgrade.getAction(FirmwareUpdateServiceValues.ACTION_FIRMWARE_UPGRADE);
		if(actionDoFirmwareUpgrade == null){
			
			String msgWarn = "Do firmware upgrade action is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actDoFirmwareUpgrade(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceFirmwareUpgrade == null)
			return;
		
		if(actionDoFirmwareUpgrade == null){
			return;
		}
		
		ActionInvocation invocation = new ActionInvocation(actionDoFirmwareUpgrade , values);
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

	public Action getActionDoFirmwareUpgrade() {
		return actionDoFirmwareUpgrade;
	}

}

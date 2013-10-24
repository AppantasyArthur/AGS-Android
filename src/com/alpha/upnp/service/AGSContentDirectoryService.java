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
import com.alpha.upnp.value.ContentDirectoryServiceValues;
import com.alpha.upnpui.MainFragmentActivity;

public class AGSContentDirectoryService {
	
	private final String tag = "AGSContentDirectoryService";

	// service
	@SuppressWarnings("rawtypes")
	private final Service service;
	
	// action
	@SuppressWarnings("rawtypes")
	private Action actionBrowse;
	@SuppressWarnings("rawtypes")
	public Action getActionBrowse() {
		return actionBrowse;
	}

	// controlled device
	private final Handler handlerMessage;
	
	@SuppressWarnings("rawtypes")
	public AGSContentDirectoryService(Device device, Handler handlerMessage){
			
		this.handlerMessage = handlerMessage;
		
		String namespace = ContentDirectoryServiceValues.DEFAULT_NAMESPACE;
		String name = ContentDirectoryServiceValues.SERVICE_NAME;
		ServiceType type = new ServiceType(namespace, name);
		
		service = device.findService(type);
		if(service == null){
			
			String msgWarn = "content directory service is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
		if(actionBrowse == null){
			
			actionBrowse = service.getAction(ContentDirectoryServiceValues.ACTION_BROWSE);
			if(actionBrowse == null){
				
				String msgWarn = "browse action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
				return;
				
			}
			
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void actBrowse(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(service == null)
			return;
		
		if(actionBrowse == null)
			return;
		
		ActionInvocation invocation = new ActionInvocation(actionBrowse , values);
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

}

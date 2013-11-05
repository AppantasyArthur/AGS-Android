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
import com.alpha.upnp.value.RenderingControlValues;
import com.alpha.upnpui.MainFragmentActivity;

@SuppressWarnings( {"unchecked", "rawtypes"} )
public class AGSRenderingControl {
	
	private final String tag = "AGSRenderingControl";

	// service
	private final Service service;
	
	// action
	private Action actionSetVolume;
	public Action getActionSetVolume() {
		
		if(actionSetVolume == null){
			
			actionSetVolume = service.getAction(RenderingControlValues.ACTION_SET_VOLUME);
			if(actionSetVolume == null){
				
				String msgWarn = "set volume action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
			}
			
		}
		
		return actionSetVolume;
		
	}
	
	private final Handler handlerMessage;
	
	public AGSRenderingControl(Device device, Handler handlerMessage){
		
		this.handlerMessage = handlerMessage;
		
		String namespace = RenderingControlValues.DEFAULT_NAMESPACE;
		String name = RenderingControlValues.SERVICE_NAME;
		ServiceType type = new ServiceType(namespace, name);
		
		service = device.findService(type);
		if(service == null){
			
			String msgWarn = "avtransport service is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
	}

	public void actSetVolume(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(service == null)
			return;
		
		if(getActionSetVolume() == null)
			return;
		
		ActionInvocation invocation = new ActionInvocation(getActionSetVolume() , values);
		AGSActionCallback callback = new AGSActionCallback(invocation, tag, handlerMessage){

			@Override
			public void success(ActionInvocation ai) {
				try {
					
					if(callerSuccessFunction != null){
					
						callerSuccessFunction.setActionInvocation(ai); // 
						callerSuccessFunction.call();
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		
		MainFragmentActivity.getServiceAndroidUPnP().getControlPoint().execute(callback);
		
	}
	

	
}

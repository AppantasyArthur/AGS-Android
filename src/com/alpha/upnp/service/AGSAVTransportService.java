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
import com.alpha.upnp.value.AVTransportServiceValues;
import com.alpha.upnpui.MainFragmentActivity;

@SuppressWarnings( {"unchecked", "rawtypes"} )
public class AGSAVTransportService {
	
	private final String tag = "AGSAVTransportService";

	// service
	private final Service serviceAVTransport;
	
	// action
	private Action actionAddDumpedTracksToQueue;
	public Action getActionAddDumpedTracksToQueue() {
		
		if(actionAddDumpedTracksToQueue == null){
			
			actionAddDumpedTracksToQueue = serviceAVTransport.getAction(AVTransportServiceValues.ACTION_ADD_DUMPED_TRACKS_TO_QUEUE);
			if(actionDumpAllTracksInQueue == null){
				
				String msgWarn = "add dumped tracks to queue action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
			}
			
		}
		
		return actionAddDumpedTracksToQueue;
	}
	
	private Action actionAddTrackToQueue;
	public Action getActionAddTrackToQueue() {
		return actionAddTrackToQueue;
	}
	
	private Action actionDumpAllTracksInQueue;
	public Action getActionDumpAllTracksInQueue() {
		
		if(actionDumpAllTracksInQueue == null){
			
			actionDumpAllTracksInQueue = serviceAVTransport.getAction(AVTransportServiceValues.ACTION_DUMP_ALL_TRACKS_IN_QUEUE);
			if(actionDumpAllTracksInQueue == null){
				
				String msgWarn = "dump all tracks in queue action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
			}
			
		}
		
		return actionDumpAllTracksInQueue;
	}

	// controlled device
	private final Handler handlerMessage;
	
	public AGSAVTransportService(Device device, Handler handlerMessage){
		
		this.handlerMessage = handlerMessage;
		
		String namespace = AVTransportServiceValues.DEFAULT_NAMESPACE;
		String name = AVTransportServiceValues.SERVICE_NAME;
		ServiceType type = new ServiceType(namespace, name);
		
		serviceAVTransport = device.findService(type);
		if(serviceAVTransport == null){
			
			String msgWarn = "avtransport service is null.";
			
			// warning
			Log.i(tag, msgWarn);
			
			Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = msgWarn;
			handlerMessage.sendMessage(msg);
			
		}
		
		if(actionAddTrackToQueue == null){
			
			actionAddTrackToQueue = serviceAVTransport.getAction(AVTransportServiceValues.ACTION_ADD_TRACK_TO_QUEUE);
			if(actionAddTrackToQueue == null){
				
				String msgWarn = "add track to queue action is null.";
				
				// warning
				Log.i(tag, msgWarn);
				
				Message msg = handlerMessage.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
				msg.obj = msgWarn;
				handlerMessage.sendMessage(msg);
				
			}
			
		}
		
	}
	
	public void actAddDumpedTracksToQueue(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceAVTransport == null)
			return;
		
		if(getActionAddDumpedTracksToQueue() == null)
			return;
		
		ActionInvocation invocation = new ActionInvocation(getActionAddDumpedTracksToQueue() , values);
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
	
	public void actDumpAllTracksInQueue(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceAVTransport == null)
			return;
		
		if(getActionDumpAllTracksInQueue() == null)
			return;
		
		ActionInvocation invocation = new ActionInvocation(getActionDumpAllTracksInQueue() , values);
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
	
	public void actAddTrackToQueue(ActionArgumentValue[] values, final AGSActionSuccessCaller callerSuccessFunction){
		
		if(serviceAVTransport == null)
			return;
		
		if(actionAddTrackToQueue == null)
			return;
		
		ActionInvocation invocation = new ActionInvocation(actionAddTrackToQueue , values);
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

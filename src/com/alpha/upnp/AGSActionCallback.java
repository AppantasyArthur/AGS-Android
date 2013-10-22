package com.alpha.upnp;

import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alpha.upnp.value.AGSHandlerMessages;

public abstract class AGSActionCallback extends ActionCallback {

	private Handler handlerMessager;
	private String tag;
	@SuppressWarnings("rawtypes")
	protected AGSActionCallback(ActionInvocation actionInvocation, String tag, Handler handlerMessager) {
		super(actionInvocation);
		this.tag = tag;
		this.handlerMessager = handlerMessager;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
		
		// error log
		// show message
		Log.d(tag, "callbackSystemInfo failure - ");
		Log.d(tag, "UPnPResponse : " + ur + ", other messgae : " + str);
		
		if(handlerMessager != null){
			Message msg = handlerMessager.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
			msg.obj = ur;
			handlerMessager.sendMessage(msg);
		}
		
	}

}

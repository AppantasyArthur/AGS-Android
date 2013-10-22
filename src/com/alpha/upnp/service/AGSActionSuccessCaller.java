package com.alpha.upnp.service;

import java.util.concurrent.Callable;

import org.teleal.cling.model.action.ActionInvocation;

import android.util.Log;

/* 抽象類別：實作多個界面，取捨函示，用於讓人繼承 */
@SuppressWarnings("rawtypes")
public abstract class AGSActionSuccessCaller<T> implements Callable<T> {
	
	protected String tag;
	public void setTag(String tag){
		this.tag = tag;
	}
	
	@Override
	public T call() throws Exception {
		
		if(tag == null)
			tag = "AGSActionSuccessCaller";
		
		Log.d(tag, "call success - ");
		Log.d(tag, "ActionInvocation : " + ai);
		
		return null;
		
	}
	
	protected ActionInvocation ai;
	public void setActionInvocation(ActionInvocation ai){
		this.ai = ai;
	}

}

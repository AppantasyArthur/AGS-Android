package com.alpha.fragments;

import com.alpha.upnp.value.AGSHandlerMessages;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class AGSFragment extends Fragment{
	
	// for setting
	protected static String frequencyText ;
	protected static String timeText ;
	
	protected static String musicText;
	protected static String uriText ;
	protected static String metaDataText ;
	
	//view group
	protected static View fragementMainView;

	// handler
	protected static Handler handler; // = new Handler();
	public static Handler getMessageHandler(){
			
		if(handler == null){
			
			handler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					
					if(msg.what == AGSHandlerMessages.SHOW_MESSAGE){
						
						String message = (String)msg.obj;
								
						Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
						t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
						t.show();
						
					}else if(msg.what == AGSHandlerMessages.SET_FREQUENCY){
						
						if(msg.obj != null)
							frequencyText = (String)msg.obj;
						else
							frequencyText = "N/A";
						
					}else if(msg.what == AGSHandlerMessages.SET_URI){
						
						if(msg.obj != null)
							uriText = (String)msg.obj;
						else
							uriText = "";
						
					}else if(msg.what == AGSHandlerMessages.SET_METADATA){
						
						if(msg.obj != null)
							metaDataText = (String)msg.obj;
						else
							metaDataText = "";
						
					}else if(msg.what == AGSHandlerMessages.SET_MUSIC){
						
						if(msg.obj != null)
							musicText = (String)msg.obj;
						else
							musicText = "N/A";
						
					}
					
				}
				
			};
			
		}
			
		return handler;
		
	}
	
	// context
	protected static Context context;
	public static Context getContext(){
		return context;
	}
	
	public static String getFrequencyText() {
		return frequencyText;
	}
	public static void setFrequencyText(String frequencyText) {
		AGSFragment.frequencyText = frequencyText;
	}
	public static String getTimeText() {
		return timeText;
	}
	public static void setTimeText(String timeText) {
		AGSFragment.timeText = timeText;
	}
	public static String getMusicText() {
		return musicText;
	}
	public static void setMusicText(String musicText) {
		AGSFragment.musicText = musicText;
	}
	public static String getUriText() {
		return uriText;
	}
	public static void setUriText(String uriText) {
		AGSFragment.uriText = uriText;
	}
	public static String getMetaDataText() {
		return metaDataText;
	}
	public static void setMetaDataText(String metaDataText) {
		AGSFragment.metaDataText = metaDataText;
	}
	
}

package com.FSAl.SETTING;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

public class AboutSettingViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSAl_VIEW_SETTING";
	private int device_size = 0;
	
	public AboutSettingViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSAl_RLayout:
				pFSAl_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_RLayout:
				FSAl_RLayout(view);
				break;			
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_RLayout(View view) {
	
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void FSAl_RLayout(View view){
		
	}
//***************************PAD*********************************
}

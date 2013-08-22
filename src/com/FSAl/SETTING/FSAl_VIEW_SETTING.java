package com.FSAl.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;

public class FSAl_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_VIEW_SETTING";
	private int device_size = 0;
	
	public FSAl_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
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

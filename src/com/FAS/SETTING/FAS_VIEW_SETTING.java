package com.FAS.SETTING;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

public class FAS_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAS_VIEW_SETTING";
	private int device_size = 0;
	
	public FAS_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FAS_RLayout_Left_RLayout:
				PAD_FAS_RLayout_Left_RLayout(view);
				break;
			case R.id.FAS_RLayout_Right_RLayout:
				PAD_FAS_RLayout_Right_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FAS_RLayout_Left_RLayout(View view) {
		Tool.fitsViewWidth(271, view);
	}
	private void PAD_FAS_RLayout_Right_RLayout(View view) {
		Tool.fitsViewWidth(753, view);
	}
//***************************PAD*********************************
}

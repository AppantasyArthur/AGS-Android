package com.FSF.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

public class FSF_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSF_VIEW_SETTING";
	private int device_size = 0;
	
	public FSF_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FSF_RLayout_TITLE_RLayout:
				PAD_FSA_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSF_RLayout_BODY_RLayout:
				PAD_FSA_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(50, view);
		
		//Title TextView
		Tool.fitsViewHeight(33, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		//CVision TextView
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewWidth(665, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTopMargin(30, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewLeftMargin(54, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		//NVision _RLayout
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewWidth(665, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewTopMargin(102, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		//LNVision TextView		
		Tool.fitsViewWidth(400, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewLeftMargin(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		//RNVision TextView		
		Tool.fitsViewWidth(400, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		//Update Button
		Tool.fitsViewHeight(40, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewWidth(665, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTopMargin(174, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		
	}
//***************************PAD*********************************
}

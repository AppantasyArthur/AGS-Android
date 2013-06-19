package com.FSM.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

public class FSM_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSM_VIEW_SETTING";
	private int device_size = 0;
	
	public FSM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FSM_RLayout_TITLE_RLayout:
				PAD_FSM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSM_RLayout_Menu_RLayout:
				PAD_FSM_RLayout_Menu_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSM_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(50, view);
		//Done Button
		Tool.fitsViewHeight(33, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button).getLayoutParams().width = Tool.getHeight(54);
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewLeftMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		//Title TextView
		Tool.fitsViewHeight(33, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
		view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView).getLayoutParams().width = Tool.getHeight(90);
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSM_RLayout_Menu_RLayout(View view) {
		//About Button
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSM_RLayout_RLayout_About_Button));
		Tool.fitsViewWidth(224, view.findViewById(R.id.FSM_RLayout_RLayout_About_Button));
		Tool.fitsViewTopMargin(33, view.findViewById(R.id.FSM_RLayout_RLayout_About_Button));
		Tool.fitsViewLeftMargin(24, view.findViewById(R.id.FSM_RLayout_RLayout_About_Button));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_About_Button));
		//Firmrware Button
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewWidth(224, view.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewTopMargin(128, view.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewLeftMargin(24, view.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button));
		//Wireless Button
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewWidth(224, view.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewTopMargin(223, view.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewLeftMargin(24, view.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button));
		//Idenrify Button
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewWidth(224, view.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewTopMargin(318, view.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewLeftMargin(24, view.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button));
	}
//***************************PAD*********************************
}

package com.FSM.SETTING;

import android.R.raw;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
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
			switch(view.getId()){
			case R.id.pFSM_RLayout:
				pFSM_RLayout(view);
				break;
			case R.id.pFSM_RLayout_TITLE_RLayout:
				pFSM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSM_RLayout_Menu_RLayout:
				pFSM_RLayout_Menu_RLayout(view);
				break;
			}
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
	private void pFSM_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSM_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSM_RLayout_RLayout_Title_TextView));
	}
	private void pFSM_RLayout_Menu_RLayout(View view) {
		//About Button
		Tool.fitsViewHeight(61, view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button));
		Tool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button));
		Tool.fitsViewTopMargin(45, view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button));
		Tool.fitsViewLeftMargin(22, view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button));	
		new ThreadReadStateListInAssets(context, "phone/setting/settings_bar_f.PNG", "phone/setting/settings_bar_n.PNG", view.findViewById(R.id.pFSM_RLayout_RLayout_About_Button), 4);
		//Firmrware Button
		Tool.fitsViewHeight(61, view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewTopMargin(141, view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewLeftMargin(22, view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/settings_bar_f.PNG", "phone/setting/settings_bar_n.PNG", view.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button), 4);
		//Wireless Button
		Tool.fitsViewHeight(61, view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewTopMargin(237, view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewLeftMargin(22, view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/settings_bar_f.PNG", "phone/setting/settings_bar_n.PNG", view.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button), 4);
		//Idenrify Button
		Tool.fitsViewHeight(61, view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewTopMargin(333, view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewLeftMargin(22, view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/settings_bar_f.PNG", "phone/setting/settings_bar_n.PNG", view.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button), 4);
	}
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

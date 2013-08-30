package com.FSF.SETTING;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
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
			switch(view.getId()){
			case R.id.pFSF_RLayout:
				pFSF_RLayout(view);
				break;
			case R.id.pFSF_RLayout_TITLE_RLayout:
				pFSF_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSF_RLayout_BODY_RLayout:
				pFSF_RLayout_BODY_RLayout(view);
				break;
			}	
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
	private void pFSF_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSF_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSF_RLayout_RLayout_Title_TextView));
	}
	private void pFSF_RLayout_BODY_RLayout(View view) {		
		//CVision TextView
		Tool.fitsViewHeight(30, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewWidth(290, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTopMargin(59, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewLeftMargin(15, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTextSize(24, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		//NVision _RLayout
		Tool.fitsViewHeight(30, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewTopMargin(100, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/setting/setting_identify_bar_top.png", view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout), 3);
		//LNVision TextView		
		Tool.fitsViewWidth(149, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewLeftMargin(9, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		//RNVision TextView		
		Tool.fitsViewWidth(149, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewRightMargin(9, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		//Update Button
		Tool.fitsViewHeight(22, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewWidth(80, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTopMargin(140, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/firmware_button_f.PNG", "phone/setting/firmware_button_n.PNG", view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button), 4);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		//CVision TextView
		Tool.fitsViewHeight(40, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTopMargin(30, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewLeftMargin(54, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSF_RLayout_RLayout_CVision_TextView));
		//NVision _RLayout
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewTopMargin(102, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout), 3);
		//LNVision TextView		
		Tool.fitsViewWidth(324, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		Tool.fitsViewLeftMargin(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		//RNVision TextView		
		Tool.fitsViewWidth(324, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_RNVision_TextView));
		//Update Button
		Tool.fitsViewHeight(52, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewWidth(179, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTopMargin(174, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
//		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		new ThreadReadStateListInAssets(context, "pad/Settings/updateall_f.png", "pad/Settings/updateall_n.png", view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button), 4);
	}
//***************************PAD*********************************
}

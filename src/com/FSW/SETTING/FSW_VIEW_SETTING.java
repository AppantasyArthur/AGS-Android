package com.FSW.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSW_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSW_VIEW_SETTING";
	private int device_size = 0;
	
	public FSW_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSW_RLayout:
				pFSW_RLayout(view);
				break;
			case R.id.pFSW_RLayout_TITLE_RLayout:
				pFSW_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSW_RLayout_BODY_RLayout:
				pFSW_RLayout_BODY_RLayout(view);
				break;
			}
		}else{
			switch(view.getId()){
			case R.id.FSW_RLayout_TITLE_RLayout:
				PAD_FSW_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSW_RLayout_BODY_RLayout:
				PAD_FSW_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
	private void pFSW_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSW_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSW_RLayout_RLayout_Title_TextView));
	}
	private void pFSW_RLayout_BODY_RLayout(View view){
		//WIFI_RLayout
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout), 3);
		//WIFI_TextView
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFI_TextView));
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFI_TextView));
		//WIFISwitch_ToggleButton
		Tool.fitsViewHeight(30, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		
		//WIFIInfo_RLayout
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewHeight(350, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		//WIFIState_RLayout
		Tool.fitsViewHeight(32, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFIState_RLayout));
		//WIFIState_TextView
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_TextView));
		//WIFIState_ProgressBar
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		Tool.fitsViewWidth(20, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		Tool.fitsViewHeight(20, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		//WIFIAP_RLayout
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFIAP_RLayout));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSW_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSW_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSW_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSW_RLayout_BODY_RLayout(View view) {
		//WIFI_RLayout
		Tool.fitsViewTopMargin(37, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout), 3);
		//WIFI_TextView
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFI_TextView));
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFI_TextView));
		//WIFISwitch_Switch
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		Tool.fitsViewRightMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		
		//WIFIInfo_RLayout
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewHeight(500, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewTopMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		//WIFIState_RLayout
		Tool.fitsViewHeight(61, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFIState_RLayout));
		//WIFIState_TextView
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_TextView));
		//WIFIState_ProgressBar
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		Tool.fitsViewWidth(30, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		Tool.fitsViewHeight(30, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		//WIFIAP_RLayout
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFIAP_RLayout));
	}
//***************************PAD*********************************
}

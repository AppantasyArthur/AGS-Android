package com.alpha.setting.wirelesssetup;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

// FSW_VIEW_SETTING
public class WirelessSettingViewSetting {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "WirelessSettingViewSetting";
//	private int device_size = 0;
	
	public WirelessSettingViewSetting(Context context){
		this.context = context;
		this.mlog.switchLog = true;
//		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
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
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSW_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSW_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSW_RLayout_RLayout_Title_TextView));
	}
	private void pFSW_RLayout_BODY_RLayout(View view){
		//WIFI_RLayout
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.pFSW_RLayout_RLayout_WIFI_RLayout), 3);
		//WIFI_TextView
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFI_TextView));
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFI_TextView));
		//WIFISwitch_ToggleButton
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		
		//WIFIInfo_RLayout
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewHeight(350, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSW_RLayout_RLayout_WIFIInfo_RLayout));
		//WIFIState_RLayout
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFIState_RLayout));
		//WIFIState_TextView
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_TextView));
		//WIFIState_ProgressBar
		TKBTool.fitsViewLeftMargin(5, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		TKBTool.fitsViewWidth(20, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		TKBTool.fitsViewHeight(20, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		//WIFIAP_RLayout
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFSW_RLayout_RLayout_RLayout_WIFIAP_RLayout));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSW_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSW_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSW_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSW_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSW_RLayout_BODY_RLayout(View view) {
		//WIFI_RLayout
		TKBTool.fitsViewTopMargin(37, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.FSW_RLayout_RLayout_WIFI_RLayout), 3);
		//WIFI_TextView
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFI_TextView));
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFI_TextView));
		//WIFISwitch_Switch
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		TKBTool.fitsViewRightMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFISwitch_Switch));
		
		//WIFIInfo_RLayout
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewHeight(500, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewTopMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSW_RLayout_RLayout_WIFIInfo_RLayout));
		//WIFIState_RLayout
		TKBTool.fitsViewHeight(61, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFIState_RLayout));
		//WIFIState_TextView
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_TextView));
		//WIFIState_ProgressBar
		TKBTool.fitsViewLeftMargin(20, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		TKBTool.fitsViewWidth(30, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_RLayout_WIFIState_ProgressBar));
		//WIFIAP_RLayout
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.FSW_RLayout_RLayout_RLayout_WIFIAP_RLayout));
	}
//***************************PAD*********************************
}

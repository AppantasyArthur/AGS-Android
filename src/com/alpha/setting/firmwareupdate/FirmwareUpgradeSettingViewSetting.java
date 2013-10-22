package com.alpha.setting.firmwareupdate;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FSF_VIEW_SETTING
public class FirmwareUpgradeSettingViewSetting {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FirmwareUpgradeSettingViewSetting";
	//private int device_size = 0;
	
	public FirmwareUpgradeSettingViewSetting(Context context/*, int device_size*/){
		
		this.context = context;
		this.mlog.switchLog = true;
		//this.device_size = device_size;
	}
	
	public void setView(View view){
		
		if(DeviceProperty.isPhone()){ // Phone
			
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
			
		}else{ // Pad
			
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
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	
	private void pFSF_RLayout_TITLE_RLayout(View view) {		
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSF_RLayout_RLayout_Title_TextView));
	}
	
	private void pFSF_RLayout_BODY_RLayout(View view) {		
		//CVision TextView
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		TKBTool.fitsViewWidth(290, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		TKBTool.fitsViewTopMargin(59, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		TKBTool.fitsViewLeftMargin(15, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		TKBTool.fitsViewTextSize(24, view.findViewById(R.id.pFSF_RLayout_RLayout_CVision_TextView));
		//NVision _RLayout
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewTopMargin(100, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout));
		new TKBThreadReadBitMapInAssets(context, "phone/setting/setting_identify_bar_top.png", view.findViewById(R.id.pFSF_RLayout_RLayout_NVision_RLayout), 3);
		//LNVision TextView		
		TKBTool.fitsViewWidth(149, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		TKBTool.fitsViewLeftMargin(9, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_LNVision_TextView));
		//RNVision TextView		
		TKBTool.fitsViewWidth(149, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		TKBTool.fitsViewRightMargin(9, view.findViewById(R.id.pFSF_RLayout_RLayout_RLayout_RNVision_TextView));
		//Update Button
		TKBTool.fitsViewHeight(22, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewWidth(80, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewTopMargin(140, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button));
		new TKBThreadReadStateListInAssets(context, "phone/setting/firmware_button_f.PNG", "phone/setting/firmware_button_n.PNG", view.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button), 4);
	}
//*************************** end PHONE*********************************
	
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSF_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		
		//CVision TextView
		TKBTool.fitsViewHeight(40, view.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber));
		TKBTool.fitsViewTopMargin(30, view.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber));
		TKBTool.fitsViewLeftMargin(54, view.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FiramwareUpgradeSettingCurrentVesrionNumber));
		
		//NVision _RLayout
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewTopMargin(102, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/Settings_box.png", view.findViewById(R.id.FSF_RLayout_RLayout_NVision_RLayout), 3);
		
		//LNVision TextView		
		TKBTool.fitsViewWidth(324, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		TKBTool.fitsViewLeftMargin(10, view.findViewById(R.id.FSF_RLayout_RLayout_RLayout_LNVision_TextView));
		
		//RNVision TextView		
		TKBTool.fitsViewWidth(324, view.findViewById(R.id.FiramwareUpgradeSettingNewVesrionNumber));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FiramwareUpgradeSettingNewVesrionNumber));
		TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.FiramwareUpgradeSettingNewVesrionNumber));
		
		//Update Button
		TKBTool.fitsViewHeight(52, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewWidth(179, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewTopMargin(174, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
//		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/updateall_f.png", "pad/Settings/updateall_n.png", view.findViewById(R.id.FSF_RLayout_RLayout_Update_Button), 4);
		
	}
//*************************** end PAD*********************************
}

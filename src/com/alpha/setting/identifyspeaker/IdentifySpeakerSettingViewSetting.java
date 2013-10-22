package com.alpha.setting.identifyspeaker;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

// FSI_VIEW_SETTING
public class IdentifySpeakerSettingViewSetting {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "IdentifySpeakerSettingViewSetting";
	
	//private int device_size = 0;
	
	public IdentifySpeakerSettingViewSetting(Context context/*, int device_size*/){
		
		this.context = context;
		this.mlog.switchLog = true;
		//this.device_size = device_size;
		
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSI_RLayout:
				pFSI_RLayout(view);
				break;
			case R.id.pFSI_RLayout_TITLE_RLayout:
				pFSI_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSI_RLayout_BODY_RLayout:
				pFSI_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSI_RLayout_TITLE_RLayout:
				PAD_FSA_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSI_RLayout_BODY_RLayout:
				PAD_FSA_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}

	//***************************PHONE*********************************
	private void pFSI_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSI_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSI_RLayout_RLayout_Title_TextView));
	}
	private void pFSI_RLayout_BODY_RLayout(View view) {
		//IdSpeaker_RLayout
		TKBTool.fitsViewWidth(294, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		TKBTool.fitsViewTopMargin(62, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_RLayout));
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSA_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSI_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSI_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSA_RLayout_BODY_RLayout(View view) {
		
		//IdSpeaker_RLayout
		TKBTool.fitsViewWidth(666, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		TKBTool.fitsViewHeight(550, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		TKBTool.fitsViewTopMargin(62, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_RLayout));
	}
//***************************PAD*********************************
}

package com.alpha.setting.sleeptimer;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class SleepTimerSettingViewSetting {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSS_VIEW_SETTING";
	private int device_size = 0;
	
	public SleepTimerSettingViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSS_RLayout:
				pFSS_RLayout(view);
				break;
			case R.id.pFSS_RLayout_TITLE_RLayout:
				pFSS_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSS_RLayout_BODY_RLayout:
				pFSS_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSS_RLayout_TITLE_RLayout:
				PAD_FSS_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSS_RLayout_BODY_RLayout:
				PAD_FSS_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSS_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSS_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSS_RLayout_RLayout_Title_TextView));
	}
	private void pFSS_RLayout_BODY_RLayout(View view) {
		//Name TextView
		TKBTool.fitsViewTopMargin(20, view.findViewById(R.id.pFSS_RLayout_RLayout_Name_TextView));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSS_RLayout_RLayout_Name_TextView));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFSS_RLayout_RLayout_Name_TextView));
		//SleepTimer_RLayout
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewHeight(350, view.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_RLayout));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSS_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);	
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSS_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSS_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSS_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSS_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSS_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSS_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSS_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSS_RLayout_BODY_RLayout(View view) {
		//Name TextView
		TKBTool.fitsViewTopMargin(37, view.findViewById(R.id.FSS_RLayout_RLayout_Name_TextView));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSS_RLayout_RLayout_Name_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FSS_RLayout_RLayout_Name_TextView));
		//SleepTimer_RLayout
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewHeight(550, view.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_RLayout));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_RLayout));
	}
//***************************PAD*********************************
}

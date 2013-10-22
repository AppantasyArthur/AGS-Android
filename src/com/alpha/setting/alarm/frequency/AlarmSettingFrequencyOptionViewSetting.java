package com.alpha.setting.alarm.frequency;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class AlarmSettingFrequencyOptionViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSAl_Frequency_VIEW_SETTING";
	private int device_size = 0;
	
	public AlarmSettingFrequencyOptionViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSAl_Frequency_RLayout:
				pFSAl_Frequency_RLayout(view);
				break;
			case R.id.pFSAl_Frequency_RLayout_TITLE_RLayout:
				pFSAl_Frequency_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_Frequency_RLayout_BODY_RLayout:
				pFSAl_Frequency_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_Frequency_RLayout_TITLE_RLayout:
				PAD_FSAl_Frequency_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSAl_Frequency_RLayout_BODY_RLayout:
				PAD_FSAl_Frequency_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_Frequency_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_Frequency_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button));
		new TKBThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Title_TextView));
	}
	private void pFSAl_Frequency_RLayout_BODY_RLayout(View view) {	
		//Frequency_RLayout
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewHeight(350, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSAl_Frequency_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);	
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSAl_Frequency_RLayout_BODY_RLayout(View view) {		
		//Frequency_RLayout
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewHeight(600, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
		TKBTool.fitsViewTopMargin(30, view.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_RLayout));
	}
//***************************PAD*********************************
}

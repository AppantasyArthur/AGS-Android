package com.alpha.setting.alarm;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FSAl_List_VIEW_SETTING
public class AlarmSettingListViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "AlarmSettingListViewSetting";
	private int device_size = 0;
	
	public AlarmSettingListViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSAl_List_RLayout:
				pFSAl_List_RLayout(view);
				break;
			case R.id.pFSAl_List_RLayout_TITLE_RLayout:
				pFSAl_List_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_List_RLayout_BODY_RLayout:
				pFSAl_List_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_List_RLayout_TITLE_RLayout:
				settingAlarmSettingListViewPadTitleBar(view);
				break;
			case R.id.FSAl_List_RLayout_BODY_RLayout:
				settingAlarmSettingListViewPadBody(view);
				break;
			}	
		}
	}




//***************************PHONE*********************************
	private void pFSAl_List_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_List_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button), 3);
		//Edit Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button));
		view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button).getLayoutParams().height = TKBTool.getWidth(27);
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Title_TextView));
		//Add Button
		TKBTool.fitsViewWidth(20, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button));
		TKBTool.fitsViewHeight(20, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button));
		TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/add_btn.png", view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button), 3);
	}
	
	private void pFSAl_List_RLayout_BODY_RLayout(View view) {
		//Alarm_Text
		TKBTool.fitsViewTopMargin(120, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_TextView));
		TKBTool.fitsViewTextSize(20, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_TextView));
		//Alarm_ListView
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewHeight(400, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_RLayout));
	}

//***************************PHONE*********************************
//***************************PAD*********************************
	private void settingAlarmSettingListViewPadTitleBar(View view){
		
		TKBTool.fitsViewHeight(55, view);	
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button), 3);
		//Edit Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button));
		TKBTool.fitsViewLeftMargin(10, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Title_TextView));
		//Add Button
		view.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button).getLayoutParams().width = TKBTool.getHeight(31);
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button));
		TKBTool.fitsViewRightMargin(44, view.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/add_btn.png", view.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button), 3);
	
	}
	
	private void settingAlarmSettingListViewPadBody(View view) {
		
		//Alarm_Text
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.AlarmSettingNoAlarmSign));
		
		//Alarm_ListView
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.AlarmSettingListViewPadView));
		TKBTool.fitsViewHeight(600, view.findViewById(R.id.AlarmSettingListViewPadView));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.AlarmSettingListViewPadView));
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.AlarmSettingListViewPadView));
		
	}
//***************************PAD*********************************
}

package com.alpha.setting.alarm.musicbrowsing;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class AlarmSettingMusicBrowsingViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSAl_Frequency_VIEW_SETTING";
	private int device_size = 0;
	
	public AlarmSettingMusicBrowsingViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSAl_Music_RLayout:
				pFSAl_Music_RLayout(view);
				break;
			case R.id.pFSAl_Music_RLayout_TITLE_RLayout:
				pFSAl_Music_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_Music_RLayout_BODY_RLayout:
				pFSAl_Music_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_Music_RLayout_TITLE_RLayout:
				PAD_FSAl_Music_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSAl_Music_RLayout_BODY_RLayout:
				PAD_FSAl_Music_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_Music_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_Music_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button));
		new TKBThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Title_TextView));
	}
	private void pFSAl_Music_RLayout_BODY_RLayout(View view) {	
		//MusicState_RLayout
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicState_RLayout));
		//MusicState_TextView
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		//MusicControl_RLayout
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		//MusicBack IButton
		TKBTool.fitsViewWidth(51, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewHeight(23, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewLeftMargin(10, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/back.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		TKBTool.fitsViewWidth(51, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewHeight(23, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewLeftMargin(71, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button), 3);
		//Music_RLayout
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		TKBTool.fitsViewHeight(300, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_RLayout));
		//Music ListView
		AlarmSettingMusicBrowsingListViewPhoneAdapter baseAdapter = new AlarmSettingMusicBrowsingListViewPhoneAdapter(context);
		ListView MusicListView = (ListView)view.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView);
		MusicListView.setAdapter(baseAdapter);
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSAl_Music_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);	
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSAl_Music_RLayout_BODY_RLayout(View view) {	
		//MusicState_RLayout
		TKBTool.fitsViewHeight(61, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicState_RLayout));
		//MusicState_TextView
		TKBTool.fitsViewLeftMargin(20, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicState_TextView));
		//MusicControl_RLayout
		TKBTool.fitsViewHeight(61, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_MusicControl_RLayout));
		//MusicBack IButton
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewLeftMargin(20, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewLeftMargin(95, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button), 3);
		//Music_RLayout
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		TKBTool.fitsViewHeight(500, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_RLayout));
		//Music ListView
		AlarmSettingMusicBrowsingListViewPadAdapter baseAdapter = new AlarmSettingMusicBrowsingListViewPadAdapter(context);
		ListView MusicListView = (ListView)view.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView);
		MusicListView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
}

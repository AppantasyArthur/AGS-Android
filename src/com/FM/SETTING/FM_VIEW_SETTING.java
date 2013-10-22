package com.FM.SETTING;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class FM_VIEW_SETTING {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FM_VIEW_SETTING";
	private int device_size = 0;
	
	public FM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFM_RLayout:
				pFM_RLayout(view);
				break;
			case R.id.pFM_RLayout_TITLE2_RLayout:
				pFM_RLayout_TITLE2_RLayout(view);
				break;
			case R.id.pFM_RLayout_TITLE3_RLayout:
				pFM_RLayout_TITLE3_RLayout(view);
				break;
			case R.id.pFM_RLayout_Body_LLayout:
				pFM_RLayout_Body_LLayout(view);
				break;
			case R.id.pFM_RLayout_RLayout_TITLE4_RLayout:
				pFM_RLayout_RLayout_TITLE4_RLayout(view);
				break;
			case R.id.pFM_RLayout_RLayout_RLayout_Music_ListView:
				pFM_RLayout_RLayout_RLayout_Music_ListView(view);
				break;
			case R.id.pFM_RLayout_Bottom_RLayout:
				pFM_RLayout_Bottom_RLayout(view);
				break;	
			}
		}else{
			switch(view.getId()){
			case R.id.FM_RLayout_TITLE_RLayout:
				PAD_FM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FM_RLayout_TITLE2_RLayout:
				PAD_FM_RLayout_TITLE2_RLayout(view);
				break;
			case R.id.FM_RLayout_TITLE3_RLayout:
				PAD_FM_RLayout_TITLE3_RLayout(view);
				break;
			case R.id.FM_RLayout_Music_RLayout:
				PAD_FM_RLayout_Music_RLayout(view);
				break;
			}	
		}
	}

	//***************************PHONE*********************************
	private void pFM_RLayout(View view) {		
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFM_RLayout_TITLE2_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);		
		//Speaker_Button
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button), 4);
		//Center_TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFM_RLayout_RLayout_Center_TextView));
		//NowPlaying_Button
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button), 4);
	}
	private void pFM_RLayout_TITLE3_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_bar.png", view, 3);
		
		//MusicBack IButton
		TKBTool.fitsViewWidth(51, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewLeftMargin(8, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/back.png", view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		TKBTool.fitsViewWidth(51, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewLeftMargin(67, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button), 3);
		//SearchMusic RLayout		
		TKBTool.fitsViewWidth(159, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		TKBTool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		TKBTool.fitsViewRightMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_s.png", view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout), 3);
		//SearchMusic IButton
		view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton).getLayoutParams().height = TKBTool.getWidth(17);
		TKBTool.fitsViewWidth(17, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton));	
		TKBTool.fitsViewLeftMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton));	
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_icon.png", view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton), 2);
		//SearchMusic EText
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_EText));
		TKBTool.fitsViewLeftMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_EText));			
	}
	private void pFM_RLayout_Body_LLayout(View view){		
		((LinearLayout.LayoutParams)view.getLayoutParams()).topMargin = TKBTool.getHeight(2);
	}
	private void pFM_RLayout_RLayout_TITLE4_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);		
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/search_bar.png", view, 3);
		//SerchConditionBG_ImageView
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewWidth(312, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewLeftMargin(4, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "phone/playlist/playlist", view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView), 1);
		//SerchCondition1_Button
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewLeftMargin(4, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		//SerchCondition2_Button
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		//SerchCondition3_Button
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		//SerchCondition4_Button
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
	}
	private void pFM_RLayout_RLayout_RLayout_Music_ListView(View view) {		
		//Music ListView
		ListView listView = (ListView)view;
		FM_Music_ListView_BaseAdapter_Phone baseAdapter = new FM_Music_ListView_BaseAdapter_Phone(context);
		listView.setAdapter(baseAdapter);
	}
	private void pFM_RLayout_Bottom_RLayout(View view) {
		TKBTool.fitsViewHeight(30, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
		//Setting_IButton
		TKBTool.fitsViewWidth(24, view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton).getLayoutParams().height = TKBTool.getWidth(30);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));		
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton), 2);
		//Verson TextView
		TextView Verson_TextView = (TextView)view.findViewById(R.id.pFM_RLayout_RLayout_Verson_TextView);
		TKBTool.fitsViewLeftMargin(10,Verson_TextView);
		TKBTool.fitsViewTextSize(15, Verson_TextView);
		DeviceInformation DeviceInformation = new DeviceInformation(context);
		Verson_TextView.setText("Ver:"+DeviceInformation.getVersion());
	}
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FM_RLayout_TITLE_RLayout(View view) {		
		TKBTool.fitsViewHeight(44, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_01.png", view, 3);
		//Music TextView
		TKBTool.fitsViewWidth(58, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		//SearchMusic LLayout		
		TKBTool.fitsViewWidth(162, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search_box.png", view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout), 3);
		//SearchMusic IButton
		view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton).getLayoutParams().width = TKBTool.getHeight(18);
		TKBTool.fitsViewHeight(17, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		TKBTool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search.png", view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton), 2);
		//SearchMusic EText
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));
		TKBTool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));			
	}
	private void PAD_FM_RLayout_TITLE2_RLayout(View view) {		
		TKBTool.fitsViewHeight(47, view);
		TKBTool.fitsViewTopMargin(4, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_02.png", view, 3);
		//Music2 TextView		
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_Music2_TextView));
		//MusicBack IButton
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewRightMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button), 3);
	}
	private void PAD_FM_RLayout_TITLE3_RLayout(View view) {
		TKBTool.fitsViewHeight(47, view);
		TKBTool.fitsViewTopMargin(4, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_02.png", view, 3);
		//SerchConditionBG_ImageView
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewWidth(337, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		TKBTool.fitsViewLeftMargin(16, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView), 1);
		//SerchCondition1_Button
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewLeftMargin(16, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		//SerchCondition2_Button
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		//SerchCondition3_Button
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		//SerchCondition4_Button
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
	}
	private void PAD_FM_RLayout_Music_RLayout(View view) {
		TKBTool.fitsViewWidth(344, view);
		//Music ListView
		ListView listView = (ListView)view.findViewById(R.id.FM_RLayout_Music_ListView);
		FM_Music_ListView_BaseAdapter_PAD baseAdapter = new FM_Music_ListView_BaseAdapter_PAD(context);
		listView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
	
}

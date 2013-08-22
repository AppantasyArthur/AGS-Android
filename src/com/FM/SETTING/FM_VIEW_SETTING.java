package com.FM.SETTING;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alpha.upnpui.R;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FM_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_VIEW_SETTING";
	private int device_size = 0;
	
	public FM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
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
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFM_RLayout_TITLE2_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);		
		//Speaker_Button
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button), 4);
		//Center_TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFM_RLayout_RLayout_Center_TextView));
		//NowPlaying_Button
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button), 4);
	}
	private void pFM_RLayout_TITLE3_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/playlist/search_bar.png", view, 3);
		
		//MusicBack IButton
		Tool.fitsViewWidth(51, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewLeftMargin(8, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		new ThreadReadBitMapInAssets(context, "phone/playlist/back.png", view.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		Tool.fitsViewWidth(51, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewLeftMargin(67, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button), 3);
		//SearchMusic RLayout		
		Tool.fitsViewWidth(159, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewHeight(23, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewRightMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/playlist/search_s.png", view.findViewById(R.id.pFM_RLayout_RLayout_SearchMusic_RLayout), 3);
		//SearchMusic IButton
		view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton).getLayoutParams().height = Tool.getWidth(17);
		Tool.fitsViewWidth(17, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton));	
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton));	
		new ThreadReadBitMapInAssets(context, "phone/playlist/search_icon.png", view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_IButton), 2);
		//SearchMusic EText
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_EText));
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SearchMusic_EText));			
	}
	private void pFM_RLayout_Body_LLayout(View view){		
		((LinearLayout.LayoutParams)view.getLayoutParams()).topMargin = Tool.getHeight(2);
	}
	private void pFM_RLayout_RLayout_TITLE4_RLayout(View view) {
		Tool.fitsViewHeight(36, view);		
		new ThreadReadBitMapInAssets(context, "phone/playlist/search_bar.png", view, 3);
		//SerchConditionBG_ImageView
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewWidth(312, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewLeftMargin(4, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView).setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/playlist/playlist", view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView), 1);
		//SerchCondition1_Button
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewLeftMargin(4, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button));
		//SerchCondition2_Button
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button));
		//SerchCondition3_Button
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button));
		//SerchCondition4_Button
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewWidth(78, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewTopMargin(5, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewLeftMargin(0, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button));
	}
	private void pFM_RLayout_RLayout_RLayout_Music_ListView(View view) {		
		//Music ListView
		ListView listView = (ListView)view;
		FM_Music_ListView_BaseAdapter_Phone baseAdapter = new FM_Music_ListView_BaseAdapter_Phone(context);
		listView.setAdapter(baseAdapter);
	}
	private void pFM_RLayout_Bottom_RLayout(View view) {
		Tool.fitsViewHeight(30, view);			
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
		//Setting_IButton
		Tool.fitsViewWidth(24, view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton).getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton), 2);
		//Verson TextView
		TextView Verson_TextView = (TextView)view.findViewById(R.id.pFM_RLayout_RLayout_Verson_TextView);
		Tool.fitsViewLeftMargin(10,Verson_TextView);
		Tool.fitsViewTextSize(15, Verson_TextView);
		DeviceInformation DeviceInformation = new DeviceInformation(context);
		Verson_TextView.setText("Ver:"+DeviceInformation.getVersion());
	}
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FM_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(44, view);
		new ThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_01.png", view, 3);
		//Music TextView
		Tool.fitsViewWidth(58, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FM_RLayout_RLayout_Music_TextView));
		//SearchMusic LLayout		
		Tool.fitsViewWidth(162, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search_box.png", view.findViewById(R.id.FM_RLayout_RLayout_SearchMusic_RLayout), 3);
		//SearchMusic IButton
		view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton).getLayoutParams().width = Tool.getHeight(18);
		Tool.fitsViewHeight(17, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton));	
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_search.png", view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_IButton), 2);
		//SearchMusic EText
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));
		Tool.fitsViewLeftMargin(5, view.findViewById(R.id.FM_RLayout_RLayout_LLayout_SearchMusic_EText));			
	}
	private void PAD_FM_RLayout_TITLE2_RLayout(View view) {		
		Tool.fitsViewHeight(47, view);
		Tool.fitsViewTopMargin(4, view);
		new ThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_02.png", view, 3);
		//Music2 TextView		
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FM_RLayout_RLayout_Music2_TextView));
		//MusicBack IButton
		Tool.fitsViewWidth(55, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button), 3);
		//MusicTop IButton
		Tool.fitsViewWidth(55, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewRightMargin(30, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_close_botton.png", view.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button), 3);
	}
	private void PAD_FM_RLayout_TITLE3_RLayout(View view) {
		Tool.fitsViewHeight(47, view);
		Tool.fitsViewTopMargin(4, view);
		new ThreadReadBitMapInAssets(context, "pad/Playlist/Navigation_02.png", view, 3);
		//SerchConditionBG_ImageView
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewWidth(337, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		Tool.fitsViewLeftMargin(16, view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView).setTag(0);
		new ThreadReadBitMapInAssets(context, "pad/Playlist/search_btn_00.png", view.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView), 1);
		//SerchCondition1_Button
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewLeftMargin(16, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button));
		//SerchCondition2_Button
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button));
		//SerchCondition3_Button
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button));
		//SerchCondition4_Button
		Tool.fitsViewHeight(29, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewWidth(84, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewLeftMargin(1, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button));
	}
	private void PAD_FM_RLayout_Music_RLayout(View view) {
		Tool.fitsViewWidth(344, view);
		//Music ListView
		ListView listView = (ListView)view.findViewById(R.id.FM_RLayout_Music_ListView);
		FM_Music_ListView_BaseAdapter_PAD baseAdapter = new FM_Music_ListView_BaseAdapter_PAD(context);
		listView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
	
}

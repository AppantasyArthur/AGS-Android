package com.alpha.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alpha.musicsource.FM_Music_ListView_BaseAdapter_PAD;
import com.alpha.musicsource.FM_Music_ListView_BaseAdapter_Phone;
import com.alpha.musicsource.MusicSourceListView;
import com.alpha.musicsource.MusicSourceViewListener;
import com.alpha.musicsource.MusicSourceViewSetting;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_Music
public class MusicSourceFragement extends AGSFragment {
	
	//VIEWS
	private View Fragment_MainView;	
	
	private MusicSourceListView viewMusicSourceList;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private MusicSourceViewSetting VIEW_SETTING;
	private MusicSourceViewListener VIEW_LISTNER;
	private static String tag = "MusicSourceFragement";
	private TKBLog mlog = new TKBLog();
	private Context context;
//	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(tag, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
//		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = ((MainFragmentActivity)context).getSupportFragmentManager();
		//介面設定取得
        this.VIEW_SETTING = new MusicSourceViewSetting(this.context/*,this.device_size*/);
        //動作設定取得
        this.VIEW_LISTNER = new MusicSourceViewListener(this.context/*,this.device_size*/, this.fragmentManager);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			//手機
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_music_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();//介面設定
			Phone_findViewListner();//動作設定
		}else{
			//平板
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_music_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();//介面設定
			PAD_findViewListner();//動作設定
		}	
		return Fragment_MainView;
	}
	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_TITLE2_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_TITLE3_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_TITLE4_RLayout));
		//==========Msic List================
		viewMusicSourceList = (MusicSourceListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView);
		this.VIEW_SETTING.VIEWSET(viewMusicSourceList);
		//==========Msic================
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_Bottom_RLayout));
	}
	private void Phone_findViewListner(){
		//切換Speaker 按鈕
		VIEW_LISTNER.Speaker_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		//切換NowPlaying 按鈕
		VIEW_LISTNER.NowPlaying_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		//Music List
		VIEW_LISTNER.setMusicSourceListViewListener((MusicSourceListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView),
												(Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		//Back 按鈕
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button),
												(MusicSourceListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView));
		//BackTop 按鈕
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button),
												(Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button),
												(MusicSourceListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView));
		
		//===========搜尋條件 按鈕===============
		VIEW_LISTNER.SET_SerchCondition1_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition2_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition3_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition4_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		//===========搜尋條件===============
		//設定按鈕
		VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));
	}

	private void PAD_findView() {
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE2_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE3_RLayout));
		//==========Msic List================
		viewMusicSourceList = (MusicSourceListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView);
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_Music_RLayout));
		//==========Msic================
		mlog.info(tag, "findView OK");
	}	
	private void PAD_findViewListner(){
		//Music List
		VIEW_LISTNER.setMusicSourceListViewListener((MusicSourceListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView),
												(Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		
		//===========搜尋條件 按鈕===============
		VIEW_LISTNER.SET_SearchMusic_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE2_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE3_RLayout));
		VIEW_LISTNER.SET_SerchCondition1_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchCondition1_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition2_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchCondition2_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition3_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchCondition3_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition4_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchCondition4_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_SerchConditionBG_ImageView));
		//===========搜尋條件===============
		//Back 按鈕
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button),
													(MusicSourceListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
		//BackTop 按鈕
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button),
												(Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button),
													(MusicSourceListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(tag, "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(tag, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(tag, "onResume");
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(tag, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(tag, "onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.v(tag, "onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(tag, "onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v(tag, "onDetach");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	public void MusicListViewLocalNameListChange(){
		if(this.viewMusicSourceList!=null){
			if(DeviceProperty.isPhone()){			
				((FM_Music_ListView_BaseAdapter_Phone)viewMusicSourceList.getAdapter()).GetListner().LocalNameListChange();
			}else{				
				((FM_Music_ListView_BaseAdapter_PAD)viewMusicSourceList.getAdapter()).GetListner().LocalNameListChange();
			}
		}
	}
}

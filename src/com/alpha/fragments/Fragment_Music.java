package com.alpha.fragments;

import com.FM.SETTING.FM_ListView;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_PAD;
import com.FM.SETTING.FM_Music_ListView_BaseAdapter_Phone;
import com.FM.SETTING.FM_VIEW_SETTING;
import com.FM.SETTING.FM_VIEW_LISTNER;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
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

public class Fragment_Music extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	private FM_ListView musicListView;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FM_VIEW_SETTING VIEW_SETTING;
	private FM_VIEW_LISTNER VIEW_LISTNER;
	private static String TAG = "Fragment_Music";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(TAG, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.LogSwitch = true;		
		device_size = ((FragmentActivity_Main)context).getDevice_Size();
		fragmentManager = ((FragmentActivity_Main)context).getSupportFragmentManager();
		//�����]�w���o
        this.VIEW_SETTING = new FM_VIEW_SETTING(this.context,this.device_size);
        //�ʧ@�]�w���o
        this.VIEW_LISTNER = new FM_VIEW_LISTNER(this.context,this.device_size,this.fragmentManager);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			//���
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_music_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();//�����]�w
			Phone_findViewListner();//�ʧ@�]�w
		}else{
			//���O
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_music_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();//�����]�w
			PAD_findViewListner();//�ʧ@�]�w
		}	
		return Fragment_MainView;
	}
	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_TITLE2_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_TITLE3_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_TITLE4_RLayout));
		//==========Msic List================
		musicListView = (FM_ListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView);
		this.VIEW_SETTING.VIEWSET(musicListView);
		//==========Msic================
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFM_RLayout_Bottom_RLayout));
	}
	private void Phone_findViewListner(){
		//����Speaker ���s
		VIEW_LISTNER.Speaker_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_Speaker_Button));
		//����NowPlaying ���s
		VIEW_LISTNER.NowPlaying_Button_LISTNER((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_NowPlaying_Button));
		//Music List
		VIEW_LISTNER.SET_Music_ListView_Listner((FM_ListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView),
												(Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button));
		//Back ���s
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button),
												(FM_ListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView));
		//BackTop ���s
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicBack_Button),
												(Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_MusicTop_Button),
												(FM_ListView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_Music_ListView));
		
		//===========�j�M���� ���s===============
		VIEW_LISTNER.SET_SerchCondition1_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition1_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition2_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition2_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition3_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition3_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		VIEW_LISTNER.SET_SerchCondition4_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchCondition4_Button),
														(ImageView)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_RLayout_SerchConditionBG_ImageView));
		//===========�j�M����===============
		//�]�w���s
		VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)Fragment_MainView.findViewById(R.id.pFM_RLayout_RLayout_Setting_IButton));
	}

	private void PAD_findView() {
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE2_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE3_RLayout));
		//==========Msic List================
		musicListView = (FM_ListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView);
		VIEW_SETTING.VIEWSET(musicListView);
		//==========Msic================
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner(){
		//Music List
		VIEW_LISTNER.SET_Music_ListView_Listner((FM_ListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView),
												(Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button));
		
		//===========�j�M���� ���s===============
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
		//===========�j�M����===============
		//Back ���s
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button),
													(FM_ListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
		//BackTop ���s
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicBack_Button),
												(Button)Fragment_MainView.findViewById(R.id.FM_RLayout_RLayout_MusicTop_Button),
													(FM_ListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(TAG, "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(TAG, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(TAG, "onResume");
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(TAG, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(TAG, "onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.v(TAG, "onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v(TAG, "onDetach");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	public void MusicListViewLocalNameListChange(){
		if(this.musicListView!=null){
			if(device_size==6){			
				((FM_Music_ListView_BaseAdapter_Phone)musicListView.getAdapter()).GetListner().LocalNameListChange();
			}else{				
				((FM_Music_ListView_BaseAdapter_PAD)musicListView.getAdapter()).GetListner().LocalNameListChange();
			}
		}
	}
}

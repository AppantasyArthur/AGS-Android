package com.alpha.fragments;

import com.FS.SETTING.FS_SPEAKER_ExpandableListView;
import com.FS.SETTING.FS_VIEW_LISTNER;
import com.FS.SETTING.FS_VIEW_SETTING;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.DeviceInformation;
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

public class Fragment_Speaker extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	private FS_SPEAKER_ExpandableListView FS_SPEAKER_EListView;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FS_VIEW_SETTING VIEW_SETTING;
	private FS_VIEW_LISTNER VIEW_LISTNER;	

	private static String TAG = "Fragment_Speaker";
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
		
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FS_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FS_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_speaker_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{		
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_speaker_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}
		return Fragment_MainView;
	}
	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_TITLE_TextView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_TITLE2_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayou_RLayout_Nowplaying_Button));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Center_TextView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayou_RLayout_Music_Button));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_TITLE3_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_TITLE4_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_TITLE4_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
//		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pfs));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		
	}
	
	private void PAD_findView() {
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FS_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FS_RLayout_TITLE_RLayout));
//		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FS_RLayout_TITLE2_RLayout));
		FS_SPEAKER_EListView = (FS_SPEAKER_ExpandableListView)Fragment_MainView.findViewById(R.id.FS_RLayout_SPEAKER_EListView);
		VIEW_SETTING.VIEWSET(FS_SPEAKER_EListView);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {
		VIEW_LISTNER.SET_SPEAKER_EListView_Listner(FS_SPEAKER_EListView);
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
}

package com.alpha.fragments;

import com.FM.SETTING.FM_ListView;
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
import android.widget.RelativeLayout;

public class Fragment_Music extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
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
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FM_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FM_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_music_pad, null);
		Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		findView();
		findViewListner();
		return Fragment_MainView;
	}

	private void findView() {
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE2_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE3_RLayout));
		VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
		mlog.info(TAG, "findView OK");
	}	
	private void findViewListner(){
		VIEW_LISTNER.SET_Music_ListView_Listner((FM_ListView)Fragment_MainView.findViewById(R.id.FM_RLayout_Music_ListView));
		VIEW_LISTNER.SET_SearchMusic_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE2_RLayout),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.FM_RLayout_TITLE3_RLayout));
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

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.setting.functionmenu.SettingsMenuListener;
import com.alpha.setting.functionmenu.SettingsMenuPadAdapter;
import com.alpha.setting.functionmenu.SettingsMenuPhoneAdapter;
import com.alpha.setting.functionmenu.SettingsMenuViewSetting;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

public class SettingMenuFragment extends Fragment {
	//VIEWS
	private View fragmentMainView;		
	
	private BaseAdapter Menu_ListView_BaseAdapter;
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private SettingsMenuViewSetting VIEW_SETTING;
	private SettingsMenuListener VIEW_LISTNER;
	
	private static String TAG = "Fragment_SMenu";
	private TKBLog mlog = new TKBLog();
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
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		this.fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new SettingsMenuViewSetting(this.context,this.device_size);
        this.VIEW_LISTNER = new SettingsMenuListener(this.context,this.device_size,SettingMenuFragment.this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_phone, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_pad, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return fragmentMainView;
	}
	
	private void Phone_findView() {
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout_Body_RLayout));
		Menu_ListView_BaseAdapter = new SettingsMenuPhoneAdapter(context);
		ListView menu_ListView = (ListView)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Menu_ListView);
		menu_ListView.setAdapter(Menu_ListView_BaseAdapter);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {
		this.VIEW_LISTNER.setDoneButtonListener((Button)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.setSettingFunctionMenuListener((ListView)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Menu_ListView),
													this.fragmentManager);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.FSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.FSM_RLayout_Body_RLayout));
		Menu_ListView_BaseAdapter = new SettingsMenuPadAdapter(context);
		ListView menu_ListView = (ListView)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Menu_ListView);
		menu_ListView.setAdapter(Menu_ListView_BaseAdapter);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {
		
		this.VIEW_LISTNER.setDoneButtonListener((Button)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.setSettingFunctionMenuListener(
				(ListView)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Menu_ListView)
				,this.fragmentManager);
		
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
	
	public int getChooseMenu(){
		if(DeviceProperty.isPhone()){
			return ((SettingsMenuPhoneAdapter)Menu_ListView_BaseAdapter).getChoosedMenu();
		}else{
			return ((SettingsMenuPadAdapter)Menu_ListView_BaseAdapter).GetChoosedMenu();
		}		
	}
	
}

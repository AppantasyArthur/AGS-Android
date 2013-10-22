package com.alpha.fragments.settings;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.alpha.fragments.AGSFragment;
import com.alpha.setting.firmwareupdate.FirmwareUpdateSettingViewListener;
import com.alpha.setting.firmwareupdate.FirmwareUpgradeSettingViewSetting;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_SFirmrware
public class FirmwareUpdateSettingFragment extends AGSFragment {
	
	//VIEWS
	private View fragmentMainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	
	//SETTING
	private FirmwareUpgradeSettingViewSetting settingView;
	private FirmwareUpdateSettingViewListener listenerView;
	
	private static String tag = "FirmwareUpdateSettingFragment";
	private TKBLog mlog = new TKBLog();
	
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public FirmwareUpdateSettingFragment(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(tag, "onCreate");
	}	
	
	private void CreateProcess() {
		
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		
		//¨ú±oView_SETTING
        this.settingView = new FirmwareUpgradeSettingViewSetting(this.context/*, this.device_size*/);
        this.listenerView = new FirmwareUpdateSettingViewListener(this.context, this.device_size);
      
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_sfirmrware_phone, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPhoneView();
			initPhoneViewListener();
		}else{
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_sfirmrware_pad, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			intiPadView();
			initPadViewListener();
		}
		
		return fragmentMainView;
	}

	private void initPhoneView() {
		settingView.setView(fragmentMainView.findViewById(R.id.pFSF_RLayout_TITLE_RLayout));
		settingView.setView(fragmentMainView.findViewById(R.id.pFSF_RLayout_BODY_RLayout));
		mlog.info(tag, "initPhoneView OK");		
	}
	private void initPhoneViewListener() {
		listenerView.setBackButtonListener((Button)fragmentMainView.findViewById(R.id.pFSF_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		listenerView.setUpdateAllButtonListener((Button)fragmentMainView.findViewById(R.id.pFSF_RLayout_RLayout_Update_Button),
													chooseDeviceDisplay);
	}
	
	private void intiPadView() {
		settingView.setView(fragmentMainView.findViewById(R.id.FSF_RLayout_TITLE_RLayout));
		settingView.setView(fragmentMainView.findViewById(R.id.FSF_RLayout_BODY_RLayout));
		mlog.info(tag, "intiPadView OK");
	}	
	private void initPadViewListener() {		
		listenerView.setBackButtonListener((Button)fragmentMainView.findViewById(R.id.FSF_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		listenerView.setUpdateAllButtonListener((Button)fragmentMainView.findViewById(R.id.FSF_RLayout_RLayout_Update_Button),
												chooseDeviceDisplay);
		
		listenerView.getNewFirmwareInfo(chooseDeviceDisplay, fragmentMainView);
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
}

package com.alpha.fragments.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.AGSFragment;
import com.alpha.setting.identifyspeaker.IdentifySpeakerSettingPhoneAdapter;
import com.alpha.setting.identifyspeaker.IdentifySpeakerSettingPadAdapter;
import com.alpha.setting.identifyspeaker.IdentifySpeakerSettingViewListener;
import com.alpha.setting.identifyspeaker.IdentifySpeakerSettingViewSetting;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_SIdenrify
public class IdentifySpeakerSettingFragment extends AGSFragment {
	
	//VIEWS
	private View Fragment_MainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private IdentifySpeakerSettingViewSetting VIEW_SETTING;
	private IdentifySpeakerSettingViewListener VIEW_LISTNER;
	
	private static String TAG = "IdentifySpeakerSettingFragment";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public IdentifySpeakerSettingFragment(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
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
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new IdentifySpeakerSettingViewSetting(this.context/*,this.device_size*/);
        this.VIEW_LISTNER = new IdentifySpeakerSettingViewListener(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_sidenrify_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_sidenrify_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return Fragment_MainView;
	}

	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSI_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSI_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSI_RLayout_BODY_RLayout));
		IdentifySpeakerSettingPhoneAdapter baseAdapter = new IdentifySpeakerSettingPhoneAdapter(context,chooseDeviceDisplay);
		((ListView)Fragment_MainView.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_ListView)).setAdapter(baseAdapter);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {
		this.VIEW_LISTNER.setBackButtonListener((Button)Fragment_MainView.findViewById(R.id.pFSI_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.setIdentifySpeakerListViewListener((ListView)Fragment_MainView.findViewById(R.id.pFSI_RLayout_RLayout_IdSpeaker_ListView));
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSI_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSI_RLayout_BODY_RLayout));
		IdentifySpeakerSettingPadAdapter baseAdapter = new IdentifySpeakerSettingPadAdapter(context, chooseDeviceDisplay);
		((ListView)Fragment_MainView.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView)).setAdapter(baseAdapter);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.setBackButtonListener((Button)Fragment_MainView.findViewById(R.id.FSI_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.setIdentifySpeakerListViewListener((ListView)Fragment_MainView.findViewById(R.id.FSI_RLayout_RLayout_IdSpeaker_ListView));
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

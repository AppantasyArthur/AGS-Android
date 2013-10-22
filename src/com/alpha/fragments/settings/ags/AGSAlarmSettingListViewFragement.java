package com.alpha.fragments.settings.ags;

import android.app.Activity;
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
import android.widget.TextView;

import com.alpha.setting.alarm.AlarmSettingListViewSetting;
import com.alpha.setting.alarm.AlarmSettingPadViewAdapter;
import com.alpha.setting.alarm.AlarmSettingPhoneViewAdapter;
import com.alpha.setting.alarm.ags.AGSAlarmSettingListViewListener;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;

// Fragment_SAlarm_List
public class AGSAlarmSettingListViewFragement extends Fragment {
	//VIEWS
	private View fragementMainView;	
	private BaseAdapter adapterAlarmSettingList;
	//Fragment Manager
	private FragmentManager pFragmentManager = null;
	private FragmentManager fragmentManager = null;
	//SETTING
	private AlarmSettingListViewSetting settingView;
	private AGSAlarmSettingListViewListener listenerView;
	
	private static String tag = "AGSAlarmSettingListViewFragement";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int deviceScreenSize = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public AGSAlarmSettingListViewFragement(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		processOnCreate();		
		Log.v(tag, "onCreate");
	}	
	private void processOnCreate() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		deviceScreenSize = ((MainFragmentActivity)context).getDeviceScreenSize();
		pFragmentManager = this.getParentFragment().getFragmentManager();
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.settingView = new AlarmSettingListViewSetting(this.context,this.deviceScreenSize);
        this.listenerView = new AGSAlarmSettingListViewListener(this.context,this.deviceScreenSize,this.chooseDeviceDisplay);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		Log.v(tag, "onCreateView");
		if(deviceScreenSize <= 6){
			
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_list_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			initPhoneView();
			
			ListView viewAlarmSettingList = (ListView)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_ListView);
			
			adapterAlarmSettingList = new AlarmSettingPhoneViewAdapter(context
											, (TextView)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_TextView)
											, chooseDeviceDisplay);
			viewAlarmSettingList.setAdapter(adapterAlarmSettingList);
			
			initPhoneViewListener();
			
		}else{
			
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_list_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			
			initPadView();
			
			ListView viewAlarmSettingList = (ListView)fragementMainView.findViewById(R.id.AlarmSettingListViewPadView);
			
			adapterAlarmSettingList = new AlarmSettingPadViewAdapter(context
													, fragementMainView
													, chooseDeviceDisplay);
			viewAlarmSettingList.setAdapter(adapterAlarmSettingList);
			
			initPadViewListener();
			
		}	
		
		return fragementMainView;
		
	}

	private void initPhoneView() {		
		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_List_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_List_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_List_RLayout_BODY_RLayout));
		
		mlog.info(tag, "initPhoneView OK");
		
	}
	private void initPhoneViewListener() {		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button),
												this.pFragmentManager);
		this.listenerView.setEditButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button),
												this.adapterAlarmSettingList);
		this.listenerView.setAddButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button),
												this.fragmentManager);
		this.listenerView.setAlarmSettingListViewListener((ListView)fragementMainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_ListView),
													this.fragmentManager,
													this.adapterAlarmSettingList);
	}
	
	private void initPadView() {
		
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_List_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_List_RLayout_BODY_RLayout));
		
		mlog.info(tag, "initPadView OK");
		
	}	
	private void initPadViewListener() {		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setEditButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button),
												this.adapterAlarmSettingList);
		this.listenerView.setAddButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button),
												this.fragmentManager);
		this.listenerView.setAlarmSettingListViewListener((ListView)fragementMainView.findViewById(R.id.AlarmSettingListViewPadView),
												this.fragmentManager,
												this.adapterAlarmSettingList);
	}

	@Override
	public void onAttach(Activity activity) {
		Log.v(tag, "onAttach");
		super.onAttach(activity);
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

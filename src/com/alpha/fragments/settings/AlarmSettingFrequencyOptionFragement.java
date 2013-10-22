package com.alpha.fragments.settings;

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
import android.widget.ListView;

import com.alpha.setting.alarm.frequency.AlarmSettingFrequencyOptionPhoneViewAdapter;
import com.alpha.setting.alarm.frequency.AlarmSettingFrequencyOptionViewListener;
import com.alpha.setting.alarm.frequency.AlarmSettingFrequencyOptionViewSetting;
import com.alpha.setting.alarm.frequency.AlarmSettingFrequencyOptionPadViewAdapter;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_SAlarm_Frequency
public class AlarmSettingFrequencyOptionFragement extends Fragment {
	
	//VIEWS
	private View viewMainFragment;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private AlarmSettingFrequencyOptionViewSetting settingView;
	private AlarmSettingFrequencyOptionViewListener listenerView;
	
	private static String tag = "AlarmSettingFrequencyOptionFragement";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(2, R.animator.translate_right_in, R.animator.translate_left_out);
		CreateProcess();		
		Log.v(tag, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		//¨ú±oView_SETTING
        this.settingView = new AlarmSettingFrequencyOptionViewSetting(this.context, this.device_size);
        this.listenerView = new AlarmSettingFrequencyOptionViewListener(this.context, this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			viewMainFragment = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_frequency_phone, null);
			viewMainFragment.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPhoneView();
			initPhoneViewListener();
		}else{
			viewMainFragment = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_frequency_pad, null);
			viewMainFragment.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}	
		return viewMainFragment;
	}

	private void initPhoneView() {		
		this.settingView.setView(viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout));
		this.settingView.setView(viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout_TITLE_RLayout));
		this.settingView.setView(viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout_BODY_RLayout));
		//Frequency ListView
		AlarmSettingFrequencyOptionPhoneViewAdapter baseAdapter = new AlarmSettingFrequencyOptionPhoneViewAdapter(context, fragmentManager, viewMainFragment);
		ListView FrequencyListView = (ListView)viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_ListView);
		FrequencyListView.setAdapter(baseAdapter);	
		mlog.info(tag, "initPhoneView OK");
	}
	private void initPhoneViewListener() {		
		this.listenerView.setBackButtonListener((Button)viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setFrequencyListViewListener(viewMainFragment,
														this.fragmentManager);
	}
	private void PAD_findView() {		
		this.settingView.setView(viewMainFragment.findViewById(R.id.FSAl_Frequency_RLayout_TITLE_RLayout));
		this.settingView.setView(viewMainFragment.findViewById(R.id.FSAl_Frequency_RLayout_BODY_RLayout));
		//Frequency ListView
		AlarmSettingFrequencyOptionPadViewAdapter baseAdapter = new AlarmSettingFrequencyOptionPadViewAdapter(context, fragmentManager);
		ListView FrequencyListView = (ListView)viewMainFragment.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_ListView);
		FrequencyListView.setAdapter(baseAdapter);
		mlog.info(tag, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.listenerView.setBackButtonListener((Button)viewMainFragment.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setFrequencyListViewListener(viewMainFragment,
														this.fragmentManager);
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
		//((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(1, R.animator.translate_left_in, R.animator.translate_right_out);
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

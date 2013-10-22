package com.alpha.fragments.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.fragments.AGSFragment;
import com.alpha.setting.sleeptimer.SleepTimerSettingPadAdapter;
import com.alpha.setting.sleeptimer.SleepTimerSettingPhoneAdapter;
import com.alpha.setting.sleeptimer.SleepTimerSettingViewListener;
import com.alpha.setting.sleeptimer.SleepTimerSettingViewSetting;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_SSleepTimer

public class SleepTimerSettingFragement extends AGSFragment {
	
	//VIEWS
	private View fragementMainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private SleepTimerSettingViewSetting VIEW_SETTING;
	private SleepTimerSettingViewListener listenerView;
	
	private static String tag = "SleepTimerSettingFragement";
	private TKBLog mlog = new TKBLog();
	public static Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public SleepTimerSettingFragement(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CreateProcess();		
		Log.v(tag, "onCreate");
	}	
	
	//public static int SHOW_MESSAGE = 1;
	private static Toast t = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	private static Handler handlerMessager = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == AGSHandlerMessages.SHOW_MESSAGE){
				
				t.setText((String) msg.obj);
				t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
				t.show();
				
			}
			
			super.handleMessage(msg);
			
		}

	};
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new SleepTimerSettingViewSetting(this.context,this.device_size);
        this.listenerView = new SleepTimerSettingViewListener(handlerMessager/*, this.device_size*/, chooseDeviceDisplay);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_ssleeptimer_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_ssleeptimer_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return fragementMainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(fragementMainView.findViewById(R.id.pFSS_RLayout));
		this.VIEW_SETTING.VIEWSET(fragementMainView.findViewById(R.id.pFSS_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(fragementMainView.findViewById(R.id.pFSS_RLayout_BODY_RLayout));
		//Name_TextView
		TextView NameTextView = (TextView)fragementMainView.findViewById(R.id.pFSS_RLayout_RLayout_Name_TextView);
		NameTextView.setText("Choose a Sleep Time for the "+chooseDeviceDisplay.getDevice().getDetails().getFriendlyName()+".");
		//SleepTimer ListView
		SleepTimerSettingPhoneAdapter baseAdapter = new SleepTimerSettingPhoneAdapter(context);
		ListView SleepTimerListView = (ListView)fragementMainView.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_ListView);
		SleepTimerListView.setAdapter(baseAdapter);
		mlog.info(tag, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setSleepTimerListViewListener((ListView)fragementMainView.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_ListView));
	}
	private void PAD_findView() {
		
		this.VIEW_SETTING.VIEWSET(fragementMainView.findViewById(R.id.FSS_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(fragementMainView.findViewById(R.id.FSS_RLayout_BODY_RLayout));
		
		//Name_TextView
		TextView viewTitle = (TextView)fragementMainView.findViewById(R.id.FSS_RLayout_RLayout_Name_TextView);
		viewTitle.setText("Choose a Sleep Time for the "+chooseDeviceDisplay.getDevice().getDetails().getFriendlyName()+".");
		
		//SleepTimer ListView
		SleepTimerSettingPadAdapter adapter = new SleepTimerSettingPadAdapter(handlerMessager, chooseDeviceDisplay);
		ListView viewSleepTimerOptions = (ListView)fragementMainView.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_ListView);
		viewSleepTimerOptions.setAdapter(adapter);
		
		mlog.info(tag, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.FSS_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setSleepTimerListViewListener((ListView)fragementMainView.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_ListView));
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

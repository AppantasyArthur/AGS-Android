package com.alpha.fragments.settings;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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

import com.alpha.setting.about.AboutSettingPadViewAdapter;
import com.alpha.setting.about.AboutSettingPhoneViewAdapter;
import com.alpha.setting.about.AboutSettingViewListener;
import com.alpha.setting.about.AboutSettingViewSetting;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// Fragment_SAbout
public class AboutSettingFragment extends Fragment {
	
	// handler
	private static Handler handler; // = new Handler();
	public static Handler getMessageHandler(){
		
		if(handler == null){
			
			handler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					
					if(msg.what == AGSHandlerMessages.SHOW_MESSAGE){
						
						String message = (String)msg.obj;
								
						Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
						t.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
						t.show();
						
					}
					
				}
				
			};
			
		}
			
		return handler;
		
	}
	
	//VIEWS
	private View fragmentMainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private AboutSettingViewSetting VIEW_SETTING;
	private AboutSettingViewListener VIEW_LISTNER;
	
	private static String TAG = "AboutSettingFragment";
	private TKBLog mlog = new TKBLog();
	private static Context context;
	public static Context getContext(){
		return context;
	}
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public AboutSettingFragment(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		context = getActivity();
		CreateProcess();		
		
		Log.v(TAG, "onCreate");
		
	}	
	private void CreateProcess() {
		//this.context = this.getActivity();
		this.mlog.switchLog = true;		
		device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new AboutSettingViewSetting(this.context,this.device_size);
        this.VIEW_LISTNER = new AboutSettingViewListener(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(DeviceProperty.isPhone()){
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_sabout_phone, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_sabout_pad, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return fragmentMainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(fragmentMainView.findViewById(R.id.pFSA_RLayout));
		this.VIEW_SETTING.VIEWSET(fragmentMainView.findViewById(R.id.pFSA_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(fragmentMainView.findViewById(R.id.pFSA_RLayout_BODY_RLayout));
		//Name_TextView
		TextView NameTextView = (TextView)fragmentMainView.findViewById(R.id.pFSA_RLayout_RLayout_Name_TextView);
		NameTextView.setText(chooseDeviceDisplay.getDevice().getDetails().getFriendlyName());
		//About ListView
		AboutSettingPhoneViewAdapter baseAdapter = new AboutSettingPhoneViewAdapter(context, chooseDeviceDisplay);
		ListView AboutListView = (ListView)fragmentMainView.findViewById(R.id.pFSA_RLayout_RLayout_About_ListView);
		AboutListView.setAdapter(baseAdapter);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)fragmentMainView.findViewById(R.id.pFSA_RLayout_RLayout_Back_Button),
												this.fragmentManager);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(fragmentMainView.findViewById(R.id.FSA_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(fragmentMainView.findViewById(R.id.FSA_RLayout_BODY_RLayout));
		
		//Name_TextView
		TextView NameTextView = (TextView)fragmentMainView.findViewById(R.id.FSA_RLayout_RLayout_Name_TextView);
		NameTextView.setText(chooseDeviceDisplay.getDevice().getDetails().getFriendlyName());
		//About ListView
		AboutSettingPadViewAdapter baseAdapter = new AboutSettingPadViewAdapter(context, chooseDeviceDisplay);
		ListView AboutListView = (ListView)fragmentMainView.findViewById(R.id.FSA_RLayout_RLayout_About_ListView);
		AboutListView.setAdapter(baseAdapter);
		
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)fragmentMainView.findViewById(R.id.FSA_RLayout_RLayout_Back_Button),
												this.fragmentManager);
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

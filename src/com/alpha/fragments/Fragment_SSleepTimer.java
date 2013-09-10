package com.alpha.fragments;

import com.FSS.SETTING.FSS_SleepTimer_ListView_BaseAdapter_PAD;
import com.FSS.SETTING.FSS_SleepTimer_ListView_BaseAdapter_Phone;
import com.FSS.SETTING.FSS_VIEW_LISTNER;
import com.FSS.SETTING.FSS_VIEW_SETTING;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
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
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_SSleepTimer extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FSS_VIEW_SETTING VIEW_SETTING;
	private FSS_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SSleepTimer";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public Fragment_SSleepTimer(DeviceDisplay deviceDisplay){
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
		this.mlog.LogSwitch = true;		
		device_size = ((MainFragmentActivity)context).getDevice_Size();
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FSS_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSS_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_ssleeptimer_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_ssleeptimer_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSS_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSS_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSS_RLayout_BODY_RLayout));
		//Name_TextView
		TextView NameTextView = (TextView)Fragment_MainView.findViewById(R.id.pFSS_RLayout_RLayout_Name_TextView);
		NameTextView.setText("Choose a Sleep Time for the "+chooseDeviceDisplay.getDevice().getDetails().getFriendlyName()+".");
		//SleepTimer ListView
		FSS_SleepTimer_ListView_BaseAdapter_Phone baseAdapter = new FSS_SleepTimer_ListView_BaseAdapter_Phone(context);
		ListView SleepTimerListView = (ListView)Fragment_MainView.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_ListView);
		SleepTimerListView.setAdapter(baseAdapter);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSS_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.SleepTimer_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.pFSS_RLayout_RLayout_SleepTimer_ListView));
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSS_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSS_RLayout_BODY_RLayout));
		
		//Name_TextView
		TextView NameTextView = (TextView)Fragment_MainView.findViewById(R.id.FSS_RLayout_RLayout_Name_TextView);
		NameTextView.setText("Choose a Sleep Time for the "+chooseDeviceDisplay.getDevice().getDetails().getFriendlyName()+".");
		//SleepTimer ListView
		FSS_SleepTimer_ListView_BaseAdapter_PAD baseAdapter = new FSS_SleepTimer_ListView_BaseAdapter_PAD(context);
		ListView SleepTimerListView = (ListView)Fragment_MainView.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_ListView);
		SleepTimerListView.setAdapter(baseAdapter);
		
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSS_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.SleepTimer_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.FSS_RLayout_RLayout_SleepTimer_ListView));
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

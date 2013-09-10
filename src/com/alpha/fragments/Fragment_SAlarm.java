package com.alpha.fragments;

import com.FAM.SETTING.FAM_ViewFlipper;
import com.FSA.SETTING.FSA_VIEW_LISTNER;
import com.FSAl.SETTING.FSAl_VIEW_SETTING;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

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

public class Fragment_SAlarm extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	private FAM_ViewFlipper viewFlipper;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private FSAl_VIEW_SETTING VIEW_SETTING;
	private FSA_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SAlarm";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public Fragment_SAlarm(DeviceDisplay deviceDisplay){
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
		cFragmentManager = this.getChildFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FSAl_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSA_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}
		SetFirstFragment();
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_RLayout));
		viewFlipper = (FAM_ViewFlipper)Fragment_MainView.findViewById(R.id.pFSAl_RLayout_Alarm_ViewFlipper);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {	
											
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_RLayout));
		viewFlipper = (FAM_ViewFlipper)Fragment_MainView.findViewById(R.id.FSAl_RLayout_Alarm_ViewFlipper);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		
	}
	private void SetFirstFragment() {
		if(device_size==6){
			Tool.FragmentActivity_MainReplaceFragment(this.cFragmentManager.beginTransaction(), new Fragment_SAlarm_List(this.chooseDeviceDisplay), "Fragment_SAlarm_List", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage1_RLayout,R.animator.translate_bottom_in,R.animator.translate_top_out);
		}else{
			Tool.FragmentActivity_MainReplaceFragment(this.cFragmentManager.beginTransaction(), new Fragment_SAlarm_List(this.chooseDeviceDisplay), "Fragment_SAlarm_List", R.id.FSAl_RLayout_ViewFlipper_AlarmPage1_RLayout, R.animator.alpha_in,  R.animator.alpha_out);
		}
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
		if(viewFlipper!=null){
			viewFlipper.onDetachedFromWindow_P();
		}
		
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
	public void ShowViewFlipper_Display(int Page,int InAnimation,int OutAnimation){
		
		if(viewFlipper==null){
			return;
		}
		if(InAnimation!=0){
			viewFlipper.setInAnimation(context, InAnimation);
		}else{
			viewFlipper.setInAnimation(null);
		}
		if(InAnimation!=0){
			viewFlipper.setOutAnimation(context, OutAnimation);
		}else{
			viewFlipper.setOutAnimation(null);
		}
		
		switch(Page){
		case 0:
			//Alarm List 		
			viewFlipper.setDisplayedChild(0);
			break;
		case 1:
			//Alarm EditAdd
			viewFlipper.setDisplayedChild(1);
			break;
		case 2:
			//Alarm Frequency&Music
			viewFlipper.setDisplayedChild(2);
			break;
		}
	}
}

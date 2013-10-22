package com.alpha.fragments.settings;

import com.FAM.SETTING.FAM_ViewFlipper;
import com.FSAl.SETTING.AboutSettingViewSetting;
import com.alpha.setting.about.AboutSettingViewListener;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

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

// Fragment_SAlarm
public class AlarmSettingFragment extends Fragment {
	//VIEWS
	private View fragementMainView;	
	
	private FAM_ViewFlipper viewFlipper;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private AboutSettingViewSetting settingView;
	private AboutSettingViewListener listenerView;
	
	private static String tag = "AlarmSettingFragment";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int sizeDeviceScreen = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public AlarmSettingFragment(DeviceDisplay deviceDisplay){
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
		sizeDeviceScreen = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		//¨ú±oView_SETTING
        this.settingView = new AboutSettingViewSetting(this.context,this.sizeDeviceScreen);
        this.listenerView = new AboutSettingViewListener(this.context,this.sizeDeviceScreen);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(sizeDeviceScreen==6){
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPadView();
			initPadViewListener();
		}
		SetFirstFragment();
		return fragementMainView;
	}

	private void Phone_findView() {		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_RLayout));
		viewFlipper = (FAM_ViewFlipper)fragementMainView.findViewById(R.id.pFSAl_RLayout_Alarm_ViewFlipper);
		mlog.info(tag, "findView OK");
	}
	private void Phone_findViewListner() {	
											
	}
	
	private void initPadView() {
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_RLayout));
		viewFlipper = (FAM_ViewFlipper)fragementMainView.findViewById(R.id.FSAl_RLayout_Alarm_ViewFlipper);
		mlog.info(tag, "findView OK");
	}	
	private void initPadViewListener() {		
		
	}
	
	private void SetFirstFragment() {
		if(sizeDeviceScreen==6){
			TKBTool.animationReplaceFragment(this.cFragmentManager.beginTransaction(), new AlarmSettingListViewFragement(this.chooseDeviceDisplay), "Fragment_SAlarm_List", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage1_RLayout,R.animator.translate_bottom_in,R.animator.translate_top_out);
		}else{
			TKBTool.animationReplaceFragment(this.cFragmentManager.beginTransaction(), new AlarmSettingListViewFragement(this.chooseDeviceDisplay), "Fragment_SAlarm_List", R.id.FSAl_RLayout_ViewFlipper_AlarmPage1_RLayout, R.animator.alpha_in,  R.animator.alpha_out);
//			TKBTool.FragmentActivity_MainReplaceAddStackFragment(
//					fragmentManager.beginTransaction()
//					, new AlarmSettingListViewFragement(chooseDeviceDisplay)
//					, "Fragment_SAlarm_List"
//					, R.id.FSAl_RLayout_ViewFlipper_AlarmPage1_RLayout
//					, R.animator.alpha_in, R.animator.alpha_out
//					, R.animator.alpha_in, R.animator.alpha_out);
		}
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(tag, "onActivityCreated");
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.v(tag, "onAttach");
		super.onAttach(activity);
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
		if(viewFlipper!=null){
			viewFlipper.onDetachedFromWindow_P();
		}
		
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

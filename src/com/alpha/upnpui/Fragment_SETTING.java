package com.alpha.upnpui;

import com.FAS.SETTING.FAS_VIEW_LISTNER;
import com.FAS.SETTING.FAS_VIEW_SETTING;
import com.alpha.fragments.Fragment_SMenu;
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
import android.widget.ViewFlipper;

public class Fragment_SETTING extends Fragment {
	//VIEWS
	private View Fragment_MainView;		
	
	private Fragment fragment_SMenu;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;

	//SETTING
	private FAS_VIEW_SETTING VIEW_SETTING;
	private FAS_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SETTING";
	private MLog mlog = new MLog();
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
		this.mlog.LogSwitch = true;		
		device_size = ((FragmentActivity_Main)context).getDevice_Size();
		fragmentManager = this.getChildFragmentManager();
		//取得View_SETTING
        this.VIEW_SETTING = new FAS_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FAS_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragmentactivity_setting_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
			//加入Fragment_SMenu
	    	set_Phone_First_Fragment();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragmentactivity_setting_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
			//加入Fragment_SMenu
	    	set_PAD_First_Fragment();
		}		
		return Fragment_MainView;
	}
	
	private void Phone_findView() {

	}
	private void Phone_findViewListner() {

	}
	private void set_Phone_First_Fragment() {		
		fragment_SMenu = new Fragment_SMenu();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_SMenu, "Fragment_SMenu", R.id.pFAS_RLayout_ViewFlipper_Left_FLayout, R.animator.alpha_in, R.animator.alpha_out);
	}
	private void PAD_findView() {
		//設定PAD介面
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FAS_RLayout_Right_ImageView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FAS_RLayout_Left_ImageView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FAS_RLayout_TitleBG_ImageView));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FAS_RLayout_Left_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FAS_RLayout_Right_RLayout));
	
	}	
	private void PAD_findViewListner() {
		
	}
	private void set_PAD_First_Fragment() {		
		fragment_SMenu = new Fragment_SMenu();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_SMenu, "Fragment_SMenu", R.id.FAS_RLayout_Left_RLayout, R.animator.alpha_in, R.animator.alpha_out);
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
	public void ShowViewContent_ViewFlipperDisplay(int Page,int InAnimation,int OutAnimation){
		View ViewContent_ViewFlipper = Fragment_MainView.findViewById(R.id.pFAS_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper==null){
			return;
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(context, InAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(null);
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(context, OutAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(null);
		}

		switch(Page){
		case 0:
			//Fragment_Information
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(0);
			break;
		case 1:
			//Fragment_Speaker
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(1);
			break;
		}
	}
	
}

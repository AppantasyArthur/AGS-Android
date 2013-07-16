package com.alpha.upnpui;

import com.FAS.SETTING.FAS_VIEW_LISTNER;
import com.FAS.SETTING.FAS_VIEW_SETTING;
import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_Speaker;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.MLog;
import com.tkb.tool.RoomSize;
import com.tkb.tool.Tool;
import android.os.Bundle;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewFlipper;


public class FragmentActivity_Setting extends FragmentActivity {
	
	//VIEWS
	private View MainView;

	private Fragment fragment_SMenu;
	
	//SETTING
	private FAS_VIEW_SETTING VIEW_SETTING;
	private FAS_VIEW_LISTNER VIEW_LISTNER;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	
	private static String TAG = "FragmentActivity_Setting";
	private MLog mlog = new MLog();
	private Context context;
	private Display display;
	private DeviceInformation deviceImformation;
	private int device_size = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG,"onCreate");
		CreateProcess();
		
		//6�B��� 7�B���O
	    if(this.device_size==6){
	    	//PHONE����
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    	setContentView(R.layout.fragmentactivity_setting_phone);
	    	Tool.roomSize = new RoomSize(this.display,this.deviceImformation);
	    	//���o�̩��h��VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.pFAS_RLayout);
	    	//�]�wPhone����
	    	Phone_findVIEW();
	    	//�[�JFragment_SMenu
	    	set_Phone_First_Fragment();
	    	
	    }else{	 
	    	//PAD����
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    	setContentView(R.layout.fragmentactivity_setting_pad);
	    	Tool.roomSize = new RoomSize(this.display,this.deviceImformation);
	    	//���o�̩��h��VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.FAS_RLayout);
	    	//�]�wPAD����
	    	PAD_findVIEW();
	    	//�[�JFragment_SMenu
	    	set_PAD_First_Fragment();
	    	//�]�wLISTNER
	    }
	}
	
	private void CreateProcess(){
		//�]�wNO TITLE �B ���ù�
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		//���o�򥻸�T
	    this.context = this;
	    this.mlog.LogSwitch = true;//���LOG
	    this.display = this.getWindowManager().getDefaultDisplay();	   
	    this.deviceImformation = new DeviceInformation(this.context);
	    this.device_size = deviceImformation.getDevice();
	    //���ofragmentManager
	    this.fragmentManager = this.getSupportFragmentManager();         
        //���oView_SETTING
        this.VIEW_SETTING = new FAS_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FAS_VIEW_LISTNER(this.context,this.device_size);
        
	}
	private void Phone_findVIEW() {

	}
	private void set_Phone_First_Fragment() {		
		fragment_SMenu = new Fragment_SMenu();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_SMenu, "Fragment_SMenu", R.id.pFAS_RLayout_ViewFlipper_Left_RLayout, R.animator.alpha_in, R.animator.alpha_out);
	}
	
	private void PAD_findVIEW() {
		//�]�wPAD����
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAS_RLayout_Right_ImageView));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAS_RLayout_Left_ImageView));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAS_RLayout_TitleBG_ImageView));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAS_RLayout_Left_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAS_RLayout_Right_RLayout));
	}
	private void set_PAD_First_Fragment() {		
		fragment_SMenu = new Fragment_SMenu();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_SMenu, "Fragment_SMenu", R.id.FAS_RLayout_Left_RLayout, R.animator.alpha_in, R.animator.alpha_out);
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(TAG,"onRestart");
	}
    
    @Override
	protected void onStart() {
		super.onStart();
		Log.v(TAG,"onStart");
	}
    
    @Override
	protected void onResume() {
		super.onResume();
	
		Log.v(TAG,"onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG,"onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.v(TAG,"onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(TAG,"onDestroy");
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {		
		super.onConfigurationChanged(newConfig);
	}
	public int getDevice_Size(){
		return device_size;
	}
	public void ShowViewContent_ViewFlipperDisplay(int Page,int InAnimation,int OutAnimation){
		View ViewContent_ViewFlipper = MainView.findViewById(R.id.pFAS_RLayout_ViewContent_ViewFlipper);
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

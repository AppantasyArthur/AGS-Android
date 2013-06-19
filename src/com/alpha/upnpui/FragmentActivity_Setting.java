package com.alpha.upnpui;

import com.FAS.SETTING.FAS_VIEW_LISTNER;
import com.FAS.SETTING.FAS_VIEW_SETTING;
import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_Speaker;
import com.tkb.tool.DeviceImformation;
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
	private DeviceImformation deviceImformation;
	private int device_size = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(TAG,"onCreate");
		CreateProcess();
		
		//6、手機 7、平板
	    if(this.device_size==6){
	    	//PHONE介面
	    }else{	 
	    	//PAD介面
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    	setContentView(R.layout.fragmentactivity_setting_pad);
	    	Tool.roomSize = new RoomSize(this.display);
	    	//取得最底層的VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.FAS_RLayout);
	    	//設定PAD介面
	    	PAD_findVIEW();
	    	//加入Fragment_Speaker
	    	set_PAD_First_Fragment();
	    	//設定LISTNER
	    }
	}
	
	

	private void CreateProcess(){
		//設定NO TITLE 、 全螢幕
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		//取得基本資訊
	    this.context = this;
	    this.mlog.LogSwitch = true;//顯示LOG
	    this.display = this.getWindowManager().getDefaultDisplay();	   
	    this.deviceImformation = new DeviceImformation(this.context);
	    this.device_size = deviceImformation.getDevice();
	    //取得fragmentManager
	    this.fragmentManager = this.getSupportFragmentManager();         
        //取得View_SETTING
        this.VIEW_SETTING = new FAS_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FAS_VIEW_LISTNER(this.context,this.device_size);
        
	}
	private void PAD_findVIEW() {
		//設定PAD介面
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
}

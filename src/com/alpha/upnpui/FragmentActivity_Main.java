package com.alpha.upnpui;

import com.FAM.SETTING.FAM_VIEW_LISTNER;
import com.FAM.SETTING.FAM_VIEW_SETTING;
import com.alpha.fragments.Fragment_Information;
import com.alpha.fragments.Fragment_Music;
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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class FragmentActivity_Main extends FragmentActivity {
	
	//VIEWS
	private View MainView;

	//Fragments
	private Fragment fragment_Speaker;
	private Fragment fragment_Infor;
	private Fragment fragment_Music;
	//SETTING
	private FAM_VIEW_SETTING VIEW_SETTING;
	private FAM_VIEW_LISTNER VIEW_LISTNER;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	
	private static String TAG = "FragmentActivity_Main";
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
	    	setContentView(R.layout.fragmentactivity_main_pad);
	    	Tool.roomSize = new RoomSize(this.display);
	    	//取得最底層的VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.FAM_RLayout);
	    	//設定PAD介面
	    	PAD_findVIEW();
	    	//加入Fragment_Speaker
	    	set_PAD_First_Fragment();
	    	//設定LISTNER
	    	PAD_findLISTNER();
	    	
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
        this.VIEW_SETTING = new FAM_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FAM_VIEW_LISTNER(this.context,this.device_size);
        
	}
	private void PAD_findVIEW() {
		//設定PAD介面
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC1_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC2_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LEFT_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_CENTER_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_RIGHT_RLayout));
		this.VIEW_SETTING.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_BOTTOM_RLayout));
	}
	private void PAD_findLISTNER() {		
		//設定VIEW LISTNER
		//ShowCloseMediaC2 IButton LISTNER
		this.VIEW_LISTNER.ShowCloseMediaC2_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton),
															(RelativeLayout)MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC2_RLayout));
		//Sound IButton LISTNER
		this.VIEW_LISTNER.Sound_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));
		//Edit Button BISTNER
		this.VIEW_LISTNER.Edit_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button),
											(Fragment_Information)fragment_Infor);
		this.VIEW_LISTNER.Done_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(Fragment_Information)fragment_Infor);
		this.VIEW_LISTNER.Cycle_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton),
												(ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton));
		this.VIEW_LISTNER.Random_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton),
												(ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton));
		this.VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
	}
	private void set_PAD_First_Fragment() {		
		fragment_Speaker = new Fragment_Speaker();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Speaker, "Fragment_Speaker", R.id.FAM_RLayout_LEFT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Infor = new Fragment_Information();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Infor, "Fragment_Infor", R.id.FAM_RLayout_CENTER_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Music = new Fragment_Music();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Music, "Fragment_Music", R.id.FAM_RLayout_RIGHT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
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
	
	public void FMTransformTouchToFI(MotionEvent FM_Event){
		if(fragment_Infor!=null){
			((Fragment_Information)fragment_Infor).GET_TRANSFROM_FROM_FM(FM_Event);
		}
	}
	public void ShowDoneButton(){
		MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button).setVisibility(View.GONE);
		MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button).setVisibility(View.GONE);
		MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button).setVisibility(View.VISIBLE);
	}
}

package com.alpha.upnpui;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.android.AndroidUpnpServiceImpl;
import com.FAM.SETTING.FAM_VIEW_LISTNER;
import com.FAM.SETTING.FAM_VIEW_SETTING;
import com.alpha.UPNP.BrowseRegistryListener;
import com.alpha.UPNP.DeviceDisplayList;
import com.alpha.UPNP.UpnpServiceConnection;
import com.alpha.fragments.Fragment_Information;
import com.alpha.fragments.Fragment_Music;
import com.alpha.fragments.Fragment_Speaker;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.MLog;
import com.tkb.tool.RoomSize;
import com.tkb.tool.Tool;
import android.os.Bundle;
import android.os.StrictMode;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

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
	
	//Upnp
	private BrowseRegistryListener browseRegistryListener;
	private UpnpServiceConnection upnpServiceConnection;
	//Device List
	private DeviceDisplayList deviceDisplayList;
	
	private static String TAG = "FragmentActivity_Main";
	private MLog mlog = new MLog();
	private Context context;
	private Display display;
	private DeviceInformation deviceImformation;
	private int device_size = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads()
		.detectDiskWrites()
		.detectNetwork() 
		.penaltyLog()
		.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects() 
	    .penaltyLog() 
 	    .penaltyDeath()
		.build()); 
		super.onCreate(savedInstanceState);
		Log.v(TAG,"onCreate");
		CreateProcess();
		
		//6、手機 7、平板
	    if(this.device_size==6){
	    	//PHONE介面
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    	setContentView(R.layout.fragmentactivity_main_phone);
	    	Tool.roomSize = new RoomSize(this.display,this.deviceImformation);
	    	//取得最底層的VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.pFAM_RLayout);
	    	//設定PAD介面
	    	Phone_findVIEW();
	    	//加入Fragment
	    	set_Phone_First_Fragment();
	    }else{	 
	    	//PAD介面
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    	setContentView(R.layout.fragmentactivity_main_pad);
	    	Tool.roomSize = new RoomSize(this.display,this.deviceImformation);
	    	//取得最底層的VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.FAM_RLayout);
	    	//設定PAD介面
	    	PAD_findVIEW();
	    	//加入Fragment
	    	set_PAD_First_Fragment();
	    	//設定LISTNER
	    	PAD_findLISTNER();	
	    }
	    //Create UPNP	
	    CreateUpnpService();
	    StartUpnpService();
	    
	}

	private void CreateProcess(){
		//設定NO TITLE 、 全螢幕
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		//取得基本資訊
	    this.context = this;
	    this.mlog.LogSwitch = true;//顯示LOG
	    this.display = this.getWindowManager().getDefaultDisplay();	   
	    this.deviceImformation = new DeviceInformation(this.context);
	    this.device_size = deviceImformation.getDevice();
	    //取得fragmentManager
	    this.fragmentManager = this.getSupportFragmentManager();         
        //取得View_SETTING
        this.VIEW_SETTING = new FAM_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FAM_VIEW_LISTNER(this.context,this.device_size);
        //建立Device主要清單
        deviceDisplayList = new DeviceDisplayList(context);
	}
	private void Phone_findVIEW() {
		
		
	}
	private void set_Phone_First_Fragment() {	
		fragment_Speaker = new Fragment_Speaker();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Speaker, "Fragment_Speaker", R.id.pFAM_RLayout_ViewFlipper_Speaker_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Infor = new Fragment_Information();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Infor, "Fragment_Infor", R.id.pFAM_RLayout_ViewFlipper_Information_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Music = new Fragment_Music();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Music, "Fragment_Music", R.id.pFAM_RLayout_ViewFlipper_Music_RLayout, R.animator.alpha_in, R.animator.alpha_out);
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
		this.VIEW_LISTNER.Clear_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(ImageView)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		this.VIEW_LISTNER.Save_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button),
											(ImageView)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		this.VIEW_LISTNER.Done_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button),
											(Fragment_Information)fragment_Infor);
		this.VIEW_LISTNER.CycleRandom_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton),
													(ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
//		this.VIEW_LISTNER.Random_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		this.VIEW_LISTNER.Setting_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		this.VIEW_LISTNER.Previous_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));
		this.VIEW_LISTNER.Next_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));
		this.VIEW_LISTNER.Play_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));
	}
	private void set_PAD_First_Fragment() {		
		fragment_Speaker = new Fragment_Speaker();		
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Speaker, "Fragment_Speaker", R.id.FAM_RLayout_LEFT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Infor = new Fragment_Information();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Infor, "Fragment_Infor", R.id.FAM_RLayout_CENTER_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragment_Music = new Fragment_Music();
		Tool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), fragment_Music, "Fragment_Music", R.id.FAM_RLayout_RIGHT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
	}
	
	private void CreateUpnpService() {		
		browseRegistryListener = new BrowseRegistryListener(deviceDisplayList);
		upnpServiceConnection = new UpnpServiceConnection(context,browseRegistryListener);
	}
	private void StartUpnpService() {		
		Intent intent = new Intent(context,AndroidUpnpServiceImpl.class);
    	this.bindService(intent, upnpServiceConnection, Context.BIND_AUTO_CREATE);
	}
	public DeviceDisplayList GETDeviceDisplayList(){
		return this.deviceDisplayList;
	}
	public AndroidUpnpService GETUPnPService(){
		return upnpServiceConnection.getUPnPService();
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
		FragmentActivity_Main.this.unbindService(upnpServiceConnection);	
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
		if(device_size==6){
			MainView.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button).setVisibility(View.GONE);
			MainView.findViewById(R.id.pFI_RLayout_RLayout_Save_Button).setVisibility(View.GONE);
			MainView.findViewById(R.id.pFI_RLayout_RLayout_Done_Button).setVisibility(View.VISIBLE);
		}else{
			MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button).setVisibility(View.GONE);
			MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button).setVisibility(View.GONE);
			MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button).setVisibility(View.VISIBLE);
		}		
	}
	public void ShowViewContent_ViewFlipperDisplay(int Page,int InAnimation,int OutAnimation){
		View ViewContent_ViewFlipper = MainView.findViewById(R.id.pFAM_RLayout_ViewContent_ViewFlipper);
		if(ViewContent_ViewFlipper==null){
			return;
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(getApplicationContext(), InAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setInAnimation(null);
		}
		if(InAnimation!=0){
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(getApplicationContext(), OutAnimation);
		}else{
			((ViewFlipper)ViewContent_ViewFlipper).setOutAnimation(null);
		}
		
		switch(Page){
		case 0:
			//Fragment_Speaker			
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(0);
			break;
		case 1:
			//Fragment_Information
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(1);
			break;
		case 2:
			//Fragment_Music
			((ViewFlipper)ViewContent_ViewFlipper).setDisplayedChild(2);
			break;
		}
	}
	public Fragment_Speaker GETFragment_Speaker(){
		return (Fragment_Speaker)this.fragment_Speaker;
	}
}

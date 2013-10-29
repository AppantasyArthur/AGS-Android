package com.alpha.upnpui;

import java.io.IOException;

import org.teleal.cling.android.AndroidUpnpService;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.alpha.fragments.MediaRendererMusicInfoFragement;
import com.alpha.fragments.MusicSourceFragement;
import com.alpha.fragments.MediaRendererListFragement;
import com.alpha.mainfragment.MainFragementViewListener;
import com.alpha.mainfragment.MainFragementViewSetting;
import com.alpha.upnp.BrowseRegistryListener;
import com.alpha.upnp.DeviceDisplayList;
import com.alpha.upnp.UpnpServiceConnection;
import com.alpha.upnp.device.AGSMediaServerDemand;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.util.DeviceProperty;
import com.tkb.UpnpOverride.AndroidUpnpServiceImpl;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.RoomSize;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FragmentActivity_Main
public class MainFragmentActivity extends FragmentActivity {
	
	private AGSMediaServerDemand msDemand = null;
	public static int port = 55688;
	public static String formatedIpAddress;
	
	protected static Handler handler; // = new Handler();
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
	private View MainView;

	//Fragments
	private Fragment fragmentMediaRendererList;
	private Fragment fragmentMRMusicInfo;
	private Fragment fragmentMusicSource;
	//SETTING
	private MainFragementViewSetting settingView;
	public MainFragementViewListener listenerView;
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	
	//Upnp
	private BrowseRegistryListener browseRegistryListener;
	private UpnpServiceConnection upnpServiceConnection;
	
	private static AndroidUpnpService serviceAndroidUPnP;
	public static AndroidUpnpService getServiceAndroidUPnP() {
		return serviceAndroidUPnP;
	}
	public static void setServiceAndroidUPnP(AndroidUpnpService serviceAndroidUPnP) {
		MainFragmentActivity.serviceAndroidUPnP = serviceAndroidUPnP;
	}
	
	
	//Device List
	private static DeviceDisplayList deviceDisplayList;
	
	private static String tag = "MainFragmentActivity";
	private TKBLog mlog = new TKBLog();
	private static Context context;
	public static Context getContext(){
		return context;
	}
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
		Log.v(tag,"onCreate");
		
		this.context = this;
		
		CreateProcess();
		
		//6、手機 7、平板
	    if(DeviceProperty.isPhone()){
	    	//PHONE介面
	    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    	setContentView(R.layout.fragmentactivity_main_phone);
	    	TKBTool.roomSize = new RoomSize(this.display,this.deviceImformation);
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
	    	TKBTool.roomSize = new RoomSize(this.display,this.deviceImformation);
	    	//取得最底層的VIEW
	    	this.MainView = this.getWindow().getDecorView().findViewById(R.id.FAM_RLayout);
	    	//設定PAD介面
	    	initPadView();
	    	//加入Fragment
	    	setPadViewFragment();
	    	//設定LISTNER
	    	initPadViewListener();	
	    }
	    //Create UPNP	
	    CreateUpnpService();
	    StartUpnpService();
	    
	    if(msDemand == null){
	    	
	    	//TextView textIpaddr = (TextView) findViewById(R.id.ipaddr);
	        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
	        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
	        formatedIpAddress = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
	            (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
	    	msDemand = new AGSMediaServerDemand(formatedIpAddress, port);
	    	try {
				msDemand.start();
				Log.i(tag, "MediaServerDemand start success! - http://" + formatedIpAddress + ":" + port );
			} catch (IOException e) {
				Log.d(tag, "MediaServerDemand start faild!");
				e.printStackTrace();
			}
	    	
	    }
	    
	}

	private void CreateProcess(){
		//設定NO TITLE 、 全螢幕
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		//取得基本資訊
	    
	    this.mlog.switchLog = true;//顯示LOG
	    this.display = this.getWindowManager().getDefaultDisplay();	   
	    this.deviceImformation = new DeviceInformation(this.context);
	    this.device_size = deviceImformation.getDeviceScreenSize();
	    //取得fragmentManager
	    this.fragmentManager = this.getSupportFragmentManager();         
        //取得View_SETTING
        this.settingView = new MainFragementViewSetting(this.context, this.device_size);
        this.listenerView = new MainFragementViewListener(this.context, this.device_size, this.fragmentManager);
        //建立Device主要清單
        deviceDisplayList = new DeviceDisplayList(context);
	}
	private void Phone_findVIEW() {
		
		
	}
	private void set_Phone_First_Fragment() {	
		fragmentMediaRendererList = new MediaRendererListFragement();		
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMediaRendererList, "Fragment_Speaker", R.id.pFAM_RLayout_ViewFlipper_Speaker_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragmentMRMusicInfo = new MediaRendererMusicInfoFragement();
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMRMusicInfo, "Fragment_Infor", R.id.pFAM_RLayout_ViewFlipper_Information_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragmentMusicSource = new MusicSourceFragement();
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMusicSource, "Fragment_Music", R.id.pFAM_RLayout_ViewFlipper_Music_RLayout, R.animator.alpha_in, R.animator.alpha_out);
	}
	private void initPadView() {
		//設定PAD畫面
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC1_RLayout));
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC2_RLayout));
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_LEFT_RLayout));
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_CENTER_RLayout));
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_RIGHT_RLayout));
		this.settingView.VIEWSET(MainView.findViewById(R.id.FAM_RLayout_BOTTOM_RLayout));
	}
	private void initPadViewListener() {		
		//設定VIEW LISTNER
		//ActionProgress_ProgressBar
		this.listenerView.CreateProcessBarListner((ProgressBar)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ActionProgress_ProgressBar));
		//ShowCloseMediaC2 IButton LISTNER
		this.listenerView.ShowCloseMediaC2_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton),
															(RelativeLayout)MainView.findViewById(R.id.FAM_RLayout_LLayout_MediaC2_RLayout));
		//TimeSeekLISTNER
		this.listenerView.setTimeProgressListener((TextView)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView),
											(SeekBar)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Music_SeekBar),
											(TextView)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));
		//Sound IButton LISTNER
		this.listenerView.Sound_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));
		this.listenerView.setVolumeSeekBarListener((SeekBar)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_SeekBar),
												(ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));
		//Edit Button BISTNER
		this.listenerView.Clear_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(ImageView)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		this.listenerView.Save_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button),
											(ImageView)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		this.listenerView.Done_Button_LISTNER((Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button),
											(Button)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button),
											(MediaRendererMusicInfoFragement)fragmentMRMusicInfo);
		this.listenerView.CycleRandom_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton),
													(ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
//		this.VIEW_LISTNER.Random_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		this.listenerView.Setting_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		this.listenerView.Previous_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));
		this.listenerView.Next_IButton_LISTNER((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));
		this.listenerView.setPlaybackButtonListener((ImageButton)MainView.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));
	}
	private void setPadViewFragment() {	
		
		fragmentMediaRendererList = new MediaRendererListFragement();		
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMediaRendererList, "Fragment_Speaker", R.id.FAM_RLayout_LEFT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragmentMRMusicInfo = new MediaRendererMusicInfoFragement();
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMRMusicInfo, "Fragment_Infor", R.id.FAM_RLayout_CENTER_RLayout, R.animator.alpha_in, R.animator.alpha_out);
		fragmentMusicSource = new MusicSourceFragement();
		TKBTool.animationAddFragment(fragmentManager.beginTransaction(), fragmentMusicSource, "Fragment_Music", R.id.FAM_RLayout_RIGHT_RLayout, R.animator.alpha_in, R.animator.alpha_out);
	
	}
	
	private void CreateUpnpService() {		
		browseRegistryListener = new BrowseRegistryListener(deviceDisplayList);
		upnpServiceConnection = new UpnpServiceConnection(context,this.device_size,browseRegistryListener);
	}
	private void StartUpnpService() {		
		Intent intent = new Intent(context,AndroidUpnpServiceImpl.class);
    	this.bindService(intent, upnpServiceConnection, Context.BIND_AUTO_CREATE);
	}
	public static DeviceDisplayList getDeviceDisplayList(){
		return deviceDisplayList;
	}
	public AndroidUpnpService getUPnPService(){
		return upnpServiceConnection.getUPnPService();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(tag,"onRestart");
	}
    
    @Override
	protected void onStart() {
		super.onStart();
		Log.v(tag,"onStart");
	}
    
    @Override
	protected void onResume() {
		super.onResume();
	
		Log.v(tag,"onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(tag,"onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.v(tag,"onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();			
		MainFragmentActivity.this.deviceDisplayList.CancelAllListner();//清除所有Listner
		MainFragmentActivity.this.unbindService(upnpServiceConnection);		
//		FragmentActivity_Main.this.deviceDisplayList.cancelTimeSeekBarTimer();//關閉Timer
		Log.v(tag,"onDestroy");		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {		
		super.onConfigurationChanged(newConfig);
	}
	public int getDeviceScreenSize(){
		return device_size;
	}
	
	public void FMTransformTouchToFI(MotionEvent FM_Event){
		if(fragmentMRMusicInfo!=null){			
			((MediaRendererMusicInfoFragement)fragmentMRMusicInfo).GET_TRANSFROM_FROM_FM(FM_Event);
		}
	}
	public void ShowDoneButton(){
		if(DeviceProperty.isPhone()){
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
	public MediaRendererListFragement GETFragment_Speaker(){
		return (MediaRendererListFragement)this.fragmentMediaRendererList;
	}
	public MediaRendererMusicInfoFragement GETFragment_Infor(){
		return (MediaRendererMusicInfoFragement)this.fragmentMRMusicInfo;
	}
	public MusicSourceFragement GETFragment_Music(){
		return (MusicSourceFragement)this.fragmentMusicSource;
	}

	

	
}

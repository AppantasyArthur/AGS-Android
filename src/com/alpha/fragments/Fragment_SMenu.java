package com.alpha.fragments;

import com.FSM.SETTING.FSM_VIEW_LISTNER;
import com.FSM.SETTING.FSM_VIEW_SETTING;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;

import android.R.integer;
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

public class Fragment_SMenu extends Fragment {
	//VIEWS
	private View Fragment_MainView;		
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FSM_VIEW_SETTING VIEW_SETTING;
	private FSM_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SMenu";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	
	private int choosedMenu = 0;
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
		this.fragmentManager = this.getFragmentManager();
		//取得View_SETTING
        this.VIEW_SETTING = new FSM_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSM_VIEW_LISTNER(this.context,this.device_size,Fragment_SMenu.this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return Fragment_MainView;
	}
	
	private void Phone_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSM_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSM_RLayout_Menu_RLayout));
	}
	private void Phone_findViewListner() {
		this.VIEW_LISTNER.Done_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.About_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_About_Button), fragmentManager);
		this.VIEW_LISTNER.Firmrware_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_Firmrware_Button), fragmentManager);
		this.VIEW_LISTNER.Wireless_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_Wireless_Button), fragmentManager);
		this.VIEW_LISTNER.Idenrify_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_Idenrify_Button), fragmentManager);
		this.VIEW_LISTNER.Alarm_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_Alarm_Button), fragmentManager);
		this.VIEW_LISTNER.SleepTimer_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSM_RLayout_RLayout_SleepTimer_Button), fragmentManager);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSM_RLayout_Menu_RLayout));
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {
		this.VIEW_LISTNER.Done_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.About_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_About_Button), fragmentManager);
		this.VIEW_LISTNER.Firmrware_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button), fragmentManager);
		this.VIEW_LISTNER.Wireless_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button), fragmentManager);
		this.VIEW_LISTNER.Idenrify_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button), fragmentManager);
		this.VIEW_LISTNER.Alarm_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Alarm_Button), fragmentManager);
		this.VIEW_LISTNER.SleepTimer_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_SleepTimer_Button), fragmentManager);
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
	
	public void SetChooseMenu(int choose){
		if(device_size==6){
			
		}else{
			//改變 old Menu Image
			switch (choosedMenu){
			case 1:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_About_Button), 3);
				break;
			case 2:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button), 3);
				break;
			case 3:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button), 3);
				break;
			case 4:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button), 3);
				break;
			case 5:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Alarm_Button), 3);
				break;
			case 6:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_n.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_SleepTimer_Button), 3);
				break;
			}
			//改變 new Menu Image
			switch (choose){
			case 1:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_About_Button), 3);
				break;
			case 2:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Firmrware_Button), 3);
				break;
			case 3:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Wireless_Button), 3);
				break;
			case 4:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Idenrify_Button), 3);
				break;
			case 5:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_Alarm_Button), 3);
				break;
			case 6:
				new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_item_f.png", Fragment_MainView.findViewById(R.id.FSM_RLayout_RLayout_SleepTimer_Button), 3);
				break;
			}
			
		}
		this.choosedMenu = choose;
	}
	public int getChooseMenu(){
		return this.choosedMenu;
	}
	
}

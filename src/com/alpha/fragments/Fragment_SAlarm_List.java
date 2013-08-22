package com.alpha.fragments;

import com.FSAl_List.SETTING.FSAl_Alarm_ListView_BaseAdapter_PAD;
import com.FSAl_List.SETTING.FSAl_Alarm_ListView_BaseAdapter_Phone;
import com.FSAl_List.SETTING.FSAl_List_VIEW_LISTNER;
import com.FSAl_List.SETTING.FSAl_List_VIEW_SETTING;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Fragment_SAlarm_List extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	private BaseAdapter alarm_ListView_BaseAdapter;
	//Fragment Manager
	private FragmentManager pFragmentManager = null;
	private FragmentManager fragmentManager = null;
	//SETTING
	private FSAl_List_VIEW_SETTING VIEW_SETTING;
	private FSAl_List_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SAlarm_List";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public Fragment_SAlarm_List(DeviceDisplay deviceDisplay){
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
		device_size = ((FragmentActivity_Main)context).getDevice_Size();
		pFragmentManager = this.getParentFragment().getFragmentManager();
		fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FSAl_List_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSAl_List_VIEW_LISTNER(this.context,this.device_size,this.chooseDeviceDisplay);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_list_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			alarm_ListView_BaseAdapter = new FSAl_Alarm_ListView_BaseAdapter_Phone(context);
			ListView alarm_ListView = (ListView)Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_ListView);
			alarm_ListView.setAdapter(alarm_ListView_BaseAdapter);
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_list_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			alarm_ListView_BaseAdapter = new FSAl_Alarm_ListView_BaseAdapter_PAD(context);
			ListView alarm_ListView = (ListView)Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Alarm_ListView);
			alarm_ListView.setAdapter(alarm_ListView_BaseAdapter);
			PAD_findViewListner();
		}	
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_BODY_RLayout));
		
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Back_Button),
												this.pFragmentManager);
		this.VIEW_LISTNER.Edit_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Edit_Button),
												this.alarm_ListView_BaseAdapter);
		this.VIEW_LISTNER.Add_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Add_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.Alarm_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.pFSAl_List_RLayout_RLayout_Alarm_ListView),
													this.fragmentManager,
													this.alarm_ListView_BaseAdapter);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_BODY_RLayout));
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Back_Button),
												this.pFragmentManager);
		this.VIEW_LISTNER.Edit_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Edit_Button),
												this.alarm_ListView_BaseAdapter);
		this.VIEW_LISTNER.Add_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Add_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.Alarm_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.FSAl_List_RLayout_RLayout_Alarm_ListView),
												this.fragmentManager,
												this.alarm_ListView_BaseAdapter);
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

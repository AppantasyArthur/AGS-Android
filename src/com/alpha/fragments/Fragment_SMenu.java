package com.alpha.fragments;

import com.FSM.SETTING.FSM_MENU_ListView_BaseAdapter_PAD;
import com.FSM.SETTING.FSM_MENU_ListView_BaseAdapter_Phone;
import com.FSM.SETTING.FSM_VIEW_LISTNER;
import com.FSM.SETTING.FSM_VIEW_SETTING;
import com.alpha.upnpui.MainFragmentActivity;
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

public class Fragment_SMenu extends Fragment {
	//VIEWS
	private View fragmentMainView;		
	
	private BaseAdapter Menu_ListView_BaseAdapter;
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	//SETTING
	private FSM_VIEW_SETTING VIEW_SETTING;
	private FSM_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SMenu";
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
		device_size = ((MainFragmentActivity)context).getDevice_Size();
		this.fragmentManager = this.getFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FSM_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSM_VIEW_LISTNER(this.context,this.device_size,Fragment_SMenu.this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_phone, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			fragmentMainView = (ViewGroup)inflater.inflate(R.layout.fragment_smenu_pad, null);
			fragmentMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return fragmentMainView;
	}
	
	private void Phone_findView() {
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.pFSM_RLayout_Body_RLayout));
		Menu_ListView_BaseAdapter = new FSM_MENU_ListView_BaseAdapter_Phone(context);
		ListView menu_ListView = (ListView)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Menu_ListView);
		menu_ListView.setAdapter(Menu_ListView_BaseAdapter);
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {
		this.VIEW_LISTNER.setDoneButtonListener((Button)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.setSettingFunctionMenuListener((ListView)fragmentMainView.findViewById(R.id.pFSM_RLayout_RLayout_Menu_ListView),
													this.fragmentManager);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.FSM_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.setView(fragmentMainView.findViewById(R.id.FSM_RLayout_Body_RLayout));
		Menu_ListView_BaseAdapter = new FSM_MENU_ListView_BaseAdapter_PAD(context);
		ListView menu_ListView = (ListView)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Menu_ListView);
		menu_ListView.setAdapter(Menu_ListView_BaseAdapter);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {
		
		this.VIEW_LISTNER.setDoneButtonListener((Button)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		this.VIEW_LISTNER.setSettingFunctionMenuListener(
				(ListView)fragmentMainView.findViewById(R.id.FSM_RLayout_RLayout_Menu_ListView)
				,this.fragmentManager);
		
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
	
	public int getChooseMenu(){
		if(device_size==6){
			return ((FSM_MENU_ListView_BaseAdapter_Phone)Menu_ListView_BaseAdapter).GetChoosedMenu();
		}else{
			return ((FSM_MENU_ListView_BaseAdapter_PAD)Menu_ListView_BaseAdapter).GetChoosedMenu();
		}		
	}
	
}

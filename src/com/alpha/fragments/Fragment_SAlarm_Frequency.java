package com.alpha.fragments;

import com.FSAl_Frequency.SETTING.FSAl_Frequency_ListView_BaseAdapter_PAD;
import com.FSAl_Frequency.SETTING.FSAl_Frequency_ListView_BaseAdapter_Phone;
import com.FSAl_Frequency.SETTING.FSAl_Frequency_VIEW_LISTNER;
import com.FSAl_Frequency.SETTING.FSAl_Frequency_VIEW_SETTING;
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
import android.widget.Button;
import android.widget.ListView;

public class Fragment_SAlarm_Frequency extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private FSAl_Frequency_VIEW_SETTING VIEW_SETTING;
	private FSAl_Frequency_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SAlarm_Frequency";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((Fragment_SAlarm)this.getParentFragment()).ShowViewFlipper_Display(2, R.animator.translate_right_in, R.animator.translate_left_out);
		CreateProcess();		
		Log.v(TAG, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.LogSwitch = true;		
		device_size = ((FragmentActivity_Main)context).getDevice_Size();
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		//¨ú±oView_SETTING
        this.VIEW_SETTING = new FSAl_Frequency_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSAl_Frequency_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_frequency_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_frequency_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}	
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout_BODY_RLayout));
		//Frequency ListView
		FSAl_Frequency_ListView_BaseAdapter_Phone baseAdapter = new FSAl_Frequency_ListView_BaseAdapter_Phone(context, fragmentManager);
		ListView FrequencyListView = (ListView)Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_ListView);
		FrequencyListView.setAdapter(baseAdapter);	
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.Frequency_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_ListView),
														this.fragmentManager);
	}
	private void PAD_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_Frequency_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_Frequency_RLayout_BODY_RLayout));
		//Frequency ListView
		FSAl_Frequency_ListView_BaseAdapter_PAD baseAdapter = new FSAl_Frequency_ListView_BaseAdapter_PAD(context, fragmentManager);
		ListView FrequencyListView = (ListView)Fragment_MainView.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_ListView);
		FrequencyListView.setAdapter(baseAdapter);
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.Frequency_ListView_LISTNER((ListView)Fragment_MainView.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_ListView),
														this.fragmentManager);
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
		((Fragment_SAlarm)this.getParentFragment()).ShowViewFlipper_Display(1, R.animator.translate_left_in, R.animator.translate_right_out);
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

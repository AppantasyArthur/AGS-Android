package com.alpha.fragments;

import com.FSAL_Music.SETTING.FSAl_Music_VIEW_LISTNER;
import com.FSAL_Music.SETTING.FSAl_Music_VIEW_SETTING;
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

public class Fragment_SAlarm_Music extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private FSAl_Music_VIEW_SETTING VIEW_SETTING;
	private FSAl_Music_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SAlarm_Music";
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
		//���oView_SETTING
        this.VIEW_SETTING = new FSAl_Music_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSAl_Music_VIEW_LISTNER(this.context,this.device_size);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_music_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_music_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}	
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_BODY_RLayout));
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.SET_Music_ListView_Listner((ListView)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView),
													(Button)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													this.fragmentManager);
		//Back ���s
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(ListView)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView));
		//BackTop ���s
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(Button)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button),
													(ListView)Fragment_MainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView));

}
	private void PAD_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_BODY_RLayout));
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.SET_Music_ListView_Listner((ListView)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView),
														(Button)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
														this.fragmentManager);
		//Back ���s	
		VIEW_LISTNER.SET_MusicBack_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(ListView)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView));
		//BackTop ���s
		VIEW_LISTNER.SET_MusicTop_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(Button)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button),
													(ListView)Fragment_MainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView));
		
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
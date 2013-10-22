package com.alpha.fragments.settings;

import com.alpha.setting.alarm.musicbrowsing.AlarmSettingMusicBrowsingViewListener;
import com.alpha.setting.alarm.musicbrowsing.AlarmSettingMusicBrowsingViewSetting;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
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

public class AlarmSettingMusicBrowsingFragement extends Fragment {
	//VIEWS
	private View fragementMainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private AlarmSettingMusicBrowsingViewSetting settingView;
	private AlarmSettingMusicBrowsingViewListener listenerView;
	
	private static String TAG = "Fragment_SAlarm_Music";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int sizeDeviceScreen = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(2, R.animator.translate_right_in, R.animator.translate_left_out);
		
		processOnCreate();
		
		Log.v(TAG, "onCreate");
		
	}	
	private void processOnCreate() {
		
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		sizeDeviceScreen = ((MainFragmentActivity)context).getDeviceScreenSize();
		
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		
		//¾켹View_SETTING
        this.settingView = new AlarmSettingMusicBrowsingViewSetting(this.context,this.sizeDeviceScreen);
        this.listenerView = new AlarmSettingMusicBrowsingViewListener(this.context,this.sizeDeviceScreen);
        
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		
		if(sizeDeviceScreen == 6){
			
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_music_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
			
		}else{
			
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_music_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPadView();
			initPadViewListener();
			
		}	
		return fragementMainView;
	}

	private void Phone_findView() {		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_Music_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_BODY_RLayout));
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.listenerView.setMusicBrowsingListViewBackButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setMusicBrowsingListViewListener((ListView)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView),
													(Button)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													this.fragmentManager);
		//Back ヶ턵
		listenerView.setMusicBrowsingBackButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(ListView)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView));
		//BackTop ヶ턵
		listenerView.setMusicBrowsingGoTopButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(Button)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button),
													(ListView)fragementMainView.findViewById(R.id.pFSAl_Music_RLayout_RLayout_Music_ListView));

	}
	
	private void initPadView() {		
		
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_Music_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_Music_RLayout_BODY_RLayout));
		mlog.info(TAG, "initPadView OK");
		
	}	
	
	private void initPadViewListener() {		
		
		//// List View
		this.listenerView.setMusicBrowsingListViewBackButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setMusicBrowsingListViewListener((ListView)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView),
														(Button)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
														this.fragmentManager);
		
		//// Browsing
		// Back ヶ턵	
		listenerView.setMusicBrowsingBackButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(ListView)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView));
		// BackTop ヶ턵
		listenerView.setMusicBrowsingGoTopButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicBack_Button),
													(Button)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_RLayout_MusicTop_Button),
													(ListView)fragementMainView.findViewById(R.id.FSAl_Music_RLayout_RLayout_Music_ListView));
		
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
		//((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(1, R.animator.translate_left_in, R.animator.translate_right_out);
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

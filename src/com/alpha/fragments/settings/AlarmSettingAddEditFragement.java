package com.alpha.fragments.settings;

import java.text.DecimalFormat;

import org.teleal.cling.support.model.item.Item;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.alpha.setting.alarm.AlarmItemContent;
import com.alpha.setting.alarm.AlarmSettingAddEditViewListener;
import com.alpha.setting.alarm.AlarmSettingAddEditViewSetting;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;

// Fragment_SAlarm_EditAdd
public class AlarmSettingAddEditFragement extends Fragment {
	//VIEWS
	private View fragementMainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private AlarmSettingAddEditViewSetting settingView;
	private AlarmSettingAddEditViewListener listenerView;
	
	private static String tag = "AlarmSettingAddEditFragement";
	private TKBLog mlog = new TKBLog();
	private Context context;
	private int sizeDeviceScreen = 0;
	private DeviceDisplay chooseDeviceDisplay;
	private AlarmItemContent currentItem = null;
	public AlarmSettingAddEditFragement(DeviceDisplay deviceDisplay, Object item){
		this.chooseDeviceDisplay = deviceDisplay;
		currentItem = (AlarmItemContent)item;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(1, R.animator.translate_right_in, R.animator.translate_left_out);
		processOnCreate();		
		Log.v(tag, "onCreate");
	}
	
	private void processOnCreate() {
		
		this.context = this.getActivity();
		this.mlog.switchLog = true;		
		sizeDeviceScreen = ((MainFragmentActivity)context).getDeviceScreenSize();
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		
		//取得View_SETTING
        this.settingView = new AlarmSettingAddEditViewSetting(this.context,this.sizeDeviceScreen, currentItem);
        this.listenerView = new AlarmSettingAddEditViewListener(this.context,this.sizeDeviceScreen,this.chooseDeviceDisplay);
        
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		Log.v(tag, "onCreateView");
		if(sizeDeviceScreen == 6){
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_editadd_phone, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPhoneView();
			initPhoneViewListener();
		}else{
			fragementMainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_editadd_pad, null);
			fragementMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			initPadView();
			initPadViewListener();
		}		
		return fragementMainView;
	}

	private void initPhoneView() {		
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_BODY_RLayout));
		mlog.info(tag, "findView OK");
	}
	private void initPhoneViewListener() {		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setAlarmSwitchListener((Switch)fragementMainView.findViewById(R.id.AlarmSettingProfileEnableSwitchPhoneView),
												(RelativeLayout)fragementMainView.findViewById(R.id.AlarmProfileSettingLayoutPhoneView));
		this.listenerView.setAlarmTimeListener((RelativeLayout)fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout),
														this.fragmentManager);
		this.listenerView.setAlarmFrequencyListener((RelativeLayout)fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout),
				this.fragmentManager);
		this.listenerView.setAlarmMusicListener((RelativeLayout)fragementMainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout),
														this.fragmentManager);
	}
	private void initPadView() {
		
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_TITLE_RLayout));
		this.settingView.setView(fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_BODY_RLayout));
		
		mlog.info(tag, "initPadView OK");
		
	}	
	private void initPadViewListener() {
		
		this.listenerView.setBackButtonListener((Button)fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.listenerView.setAlarmSwitchListener((Switch)fragementMainView.findViewById(R.id.AlarmSettingProfileEnableSwitch),
													(RelativeLayout)fragementMainView.findViewById(R.id.AlarmProfileSettingLayout));
		this.listenerView.setAlarmTimeListener((RelativeLayout)fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout),
														this.fragmentManager);
		this.listenerView.setAlarmFrequencyListener((RelativeLayout)fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout),
															this.fragmentManager);
		this.listenerView.setAlarmMusicListener((RelativeLayout)fragementMainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout),
															this.fragmentManager);
		
		listenerView.setSaveButtonListener(	fragementMainView/*(Button)fragementMainView.findViewById(R.id.AlarmSettingAddEditSaveButton)*/
										,	fragmentManager);
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		Log.v(tag, "onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(tag, "onActivityCreated");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(tag, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(tag, "onResume");
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(tag, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(tag, "onStop");
	}
	
	@Override
	public void onDestroyView() {
		((AlarmSettingFragment)this.getParentFragment()).ShowViewFlipper_Display(0, R.animator.translate_left_in, R.animator.translate_right_out);
		super.onDestroyView();
		Log.v(tag, "onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(tag, "onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v(tag, "onDetach");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	private int myH = -1;
	private int myM = -1;
	private int myAP = -1;
	String[] APStringList = new String[]{"AM","PM"};
	public void SetTime (int H , int M,int AP){
		String timeString = "";
		
		if(H>=1&&H<=12&&M>=0&&M<=59&&AP>=0&&AP<APStringList.length){
			this.myH = H;
			this.myM = M;
			this.myAP = AP;
			DecimalFormat df = new DecimalFormat("00");
			timeString = ""+df.format(H)+":"+df.format(M)+"　"+APStringList[AP];
		}

		TextView RAlarmTime_TextView;
		if(sizeDeviceScreen==6){
			RAlarmTime_TextView =(TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileTimeTextPhoneView);
		}else{
			RAlarmTime_TextView =(TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileTimeText);
		}
		RAlarmTime_TextView.setText(timeString);
	}
	String[] listFrequency = new String[]{"Once","Every Monday","Every Tuesday","Every Wedesday","Every Thursday","Every Friday","Every Saturday","Every Sunday"};
	private int myFrequency = -1;
	public void SetFrequency(int frequency){
		String frequencyString = "";
		if(frequency>=0&&frequency<listFrequency.length){
			this.myFrequency = frequency;
			frequencyString = listFrequency[frequency];
		}
		TextView RAlarmFrequency_TextView;
		if(sizeDeviceScreen==6){
			RAlarmFrequency_TextView =(TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileFrequencyTextPhoneView);
		}else{
			RAlarmFrequency_TextView = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileFrequencyText);
		}
		RAlarmFrequency_TextView.setText(frequencyString);
	}
	
	//classMode: 3=item  5 = TrackList
	private Object myMusic = null;
	private int myMusicMode = -1;
	public void setAlarmSettingProfileMusic(Object musicItme,int classMode){
		
		String musicString = "";
		switch(classMode){
		case 3:
			Item item = (Item)musicItme;
			musicString = item.getTitle();
			this.myMusic = item;
			this.myMusicMode = 3;
			break;
		case 5:
			TrackDO trackDO = (TrackDO)musicItme;
			musicString = trackDO.getTitle();
			this.myMusic = trackDO;
			this.myMusicMode = 5;
			break;
		}
		
		TextView viewAlarmSettingProfileMusic;
		if(sizeDeviceScreen==6){
			viewAlarmSettingProfileMusic = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicTextPhoneView);
		}else{
			viewAlarmSettingProfileMusic = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicText);
		}
		viewAlarmSettingProfileMusic.setText(musicString);
		
		if(myMusicMode == 3){
			
			Item item = (Item)myMusic;
			//
			
		}
		
	}
	
	public void setAlarmSettingProfileMusicURI(String uri) {
	
		TextView text;
		if(sizeDeviceScreen == 6){
			text = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicURITextPhoneView);
		}else{
			text = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicURIText);
		}
		
		Log.d(tag, "setAlarmSettingProfileMusicURI : " + uri);
		text.setText(uri);
		
	}
	
	public void setAlarmSettingProfileMusicMetaData(String dataMeta) {
		
		TextView text;
		if(sizeDeviceScreen == 6){
			text = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicMetaDataTextPhoneView);
		}else{
			text = (TextView) fragementMainView.findViewById(R.id.AlarmSettingProfileMusicMetaDataText);
		}
		
		Log.d(tag, "setAlarmSettingProfileMusicMetaData : " + dataMeta);
		text.setText(dataMeta);
		
	}
	
	//取得資料
	public String GetAlarmName(){
		String alarmName = "";
		if(sizeDeviceScreen==6){
			EditText alarmName_EditText = (EditText)fragementMainView.findViewById(R.id.AlarmSettingProfileNameTextPhoneView);
			alarmName = alarmName_EditText.getText().toString();
		}else{
			EditText alarmName_EditText = (EditText)fragementMainView.findViewById(R.id.AlarmSettingProfileNameText);
			alarmName = alarmName_EditText.getText().toString();
		}
		return alarmName;
	}
	public int GetTimeH(){
		return this.myH;		
	}
	public int GetTimeM(){
		return this.myM;		
	}
	public int GetTimeAP(){
		return this.myAP;		
	}
	public int GetFrequency(){
		return this.myFrequency;
	}
	public MusicData GetMusicData(){
		MusicData musicData = new MusicData();
		musicData.setMusic(this.myMusic);
		musicData.setMusicMode(this.myMusicMode);
		return musicData;
	}
	public int GetVolume(){
		int volume = 0;
		SeekBar alarmVolume_SeekBar = null;
		if(sizeDeviceScreen==6){
			alarmVolume_SeekBar = (SeekBar)fragementMainView.findViewById(R.id.AlarmSettingProfileVolumeSeekBarPhoneView);
		}else{
			alarmVolume_SeekBar = (SeekBar)fragementMainView.findViewById(R.id.AlarmSettingProfileVolumeSeekBar);
		}
		if(alarmVolume_SeekBar!=null){
			volume = alarmVolume_SeekBar.getProgress();
		}
		return volume;
	}
	public DeviceDisplay GetChooseDeviceDisplay(){
		return this.chooseDeviceDisplay;
	}
	public class MusicData{
		private Object music;
		private int musicMode;
		public Object getMusic() {
			return music;
		}
		public void setMusic(Object music) {
			this.music = music;
		}
		public int getMusicMode() {
			return musicMode;
		}
		public void setMusicMode(int musicMode) {
			this.musicMode = musicMode;
		}
	}
	
}

package com.alpha.fragments;

import java.text.DecimalFormat;

import org.teleal.cling.support.model.item.Item;

import com.FSAl_EditAdd.SETTING.FSAl_EditAdd_VIEW_LISTNER;
import com.FSAl_EditAdd.SETTING.FSAl_EditAdd_VIEW_SETTING;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.tkb.tool.MLog;

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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Fragment_SAlarm_EditAdd extends Fragment {
	//VIEWS
	private View Fragment_MainView;	
	
	//Fragment Manager
	private FragmentManager fragmentManager = null;
	private FragmentManager cFragmentManager = null;
	
	//SETTING
	private FSAl_EditAdd_VIEW_SETTING VIEW_SETTING;
	private FSAl_EditAdd_VIEW_LISTNER VIEW_LISTNER;
	
	private static String TAG = "Fragment_SAlarm_EditAdd";
	private MLog mlog = new MLog();
	private Context context;
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public Fragment_SAlarm_EditAdd(DeviceDisplay deviceDisplay){
		this.chooseDeviceDisplay = deviceDisplay;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		((Fragment_SAlarm)this.getParentFragment()).ShowViewFlipper_Display(1, R.animator.translate_right_in, R.animator.translate_left_out);
		CreateProcess();		
		Log.v(TAG, "onCreate");
	}	
	private void CreateProcess() {
		this.context = this.getActivity();
		this.mlog.LogSwitch = true;		
		device_size = ((MainFragmentActivity)context).getDevice_Size();
		fragmentManager = this.getFragmentManager();
		cFragmentManager = this.getChildFragmentManager();
		//取得View_SETTING
        this.VIEW_SETTING = new FSAl_EditAdd_VIEW_SETTING(this.context,this.device_size);
        this.VIEW_LISTNER = new FSAl_EditAdd_VIEW_LISTNER(this.context,this.device_size,this.chooseDeviceDisplay);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		if(device_size==6){
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_editadd_phone, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			Phone_findView();
			Phone_findViewListner();
		}else{
			Fragment_MainView = (ViewGroup)inflater.inflate(R.layout.fragment_salarm_editadd_pad, null);
			Fragment_MainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			PAD_findView();
			PAD_findViewListner();
		}		
		return Fragment_MainView;
	}

	private void Phone_findView() {		
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_BODY_RLayout));
		mlog.info(TAG, "findView OK");
	}
	private void Phone_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.AlarmSwitch_Switch_Listner((Switch)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch),
												(RelativeLayout)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		this.VIEW_LISTNER.AlarmTime_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout),
														this.fragmentManager);
		this.VIEW_LISTNER.AlarmFrequency_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout),
				this.fragmentManager);
		this.VIEW_LISTNER.AlarmMusic_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout),
														this.fragmentManager);
	}
	private void PAD_findView() {
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_TITLE_RLayout));
		this.VIEW_SETTING.VIEWSET(Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_BODY_RLayout));
		
		mlog.info(TAG, "findView OK");
	}	
	private void PAD_findViewListner() {		
		this.VIEW_LISTNER.Back_Button_Listner((Button)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button),
												this.fragmentManager);
		this.VIEW_LISTNER.AlarmSwitch_Switch_Listner((Switch)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch),
													(RelativeLayout)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		this.VIEW_LISTNER.AlarmTime_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout),
														this.fragmentManager);
		this.VIEW_LISTNER.AlarmFrequency_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout),
															this.fragmentManager);
		this.VIEW_LISTNER.AlarmMusic_RLayout_Listner((RelativeLayout)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout),
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
		((Fragment_SAlarm)this.getParentFragment()).ShowViewFlipper_Display(0, R.animator.translate_left_in, R.animator.translate_right_out);
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
		if(device_size==6){
			RAlarmTime_TextView =(TextView) Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView);
		}else{
			RAlarmTime_TextView =(TextView) Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView);
		}
		RAlarmTime_TextView.setText(timeString);
	}
	String[] dataList = new String[]{"Once","Every Monday","Every Tuesday","Every Wedesday","Every Thursday","Every Friday","Every Saturday","Every Sunday"};
	private int myFrequency = -1;
	public void SetFrequency(int frequency){
		String frequencyString = "";
		if(frequency>=0&&frequency<dataList.length){
			this.myFrequency = frequency;
			frequencyString = dataList[frequency];
		}
		TextView RAlarmFrequency_TextView;
		if(device_size==6){
			RAlarmFrequency_TextView =(TextView) Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView);
		}else{
			RAlarmFrequency_TextView =(TextView) Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView);
		}
		RAlarmFrequency_TextView.setText(frequencyString);
	}
	//classMode: 3=item  5 = TrackList
	private Object myMusic = null;
	private int myMusicMode = -1;
	public void SetMusic(Object musicItme,int classMode){
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
		TextView RAlarmMusic_TextView;
		if(device_size==6){
			RAlarmMusic_TextView =(TextView) Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView);
		}else{
			RAlarmMusic_TextView =(TextView) Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView);
		}
		RAlarmMusic_TextView.setText(musicString);
	}
	//取得資料
	public String GetAlarmName(){
		String alarmName = "";
		if(device_size==6){
			EditText alarmName_EditText = (EditText)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText);
			alarmName = alarmName_EditText.getText().toString();
		}else{
			EditText alarmName_EditText = (EditText)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText);
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
		if(device_size==6){
			alarmVolume_SeekBar = (SeekBar)Fragment_MainView.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmVolume_SeekBar);
		}else{
			alarmVolume_SeekBar = (SeekBar)Fragment_MainView.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmVolume_SeekBar);
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

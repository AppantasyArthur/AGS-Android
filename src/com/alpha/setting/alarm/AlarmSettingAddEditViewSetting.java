package com.alpha.setting.alarm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.alpha.fragments.settings.ags.AGSAlarmSettingAddEditFragement;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FSAl_EditAdd_VIEW_SETTING
public class AlarmSettingAddEditViewSetting {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AlarmSettingAddEditViewSetting";
	private int sizeDeviceScreen = 0;
	private AlarmItemContent currentItem;
	public AlarmSettingAddEditViewSetting(Context context,int sizeDeviceScreen, AlarmItemContent currentItem){
		
		this.context = context;
		this.mlog.switchLog = true;
		this.sizeDeviceScreen = sizeDeviceScreen;
		this.currentItem = currentItem;
		
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFSAl_EditAdd_RLayout:
				pFSAl_EditAdd_RLayout(view);
				break;
			case R.id.pFSAl_EditAdd_RLayout_TITLE_RLayout:
				intiAlarmSettingAddEditTitlePhoneLayout(view);
				break;
			case R.id.pFSAl_EditAdd_RLayout_BODY_RLayout:
				intiAlarmSettingAddEditBodyPhoneLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_EditAdd_RLayout_TITLE_RLayout:
				intiAlarmSettingAddEditTitlePadLayout(view);
				break;
			case R.id.FSAl_EditAdd_RLayout_BODY_RLayout:
				intiAlarmSettingAddEditBodyPadLayout(view);
				break;			
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_EditAdd_RLayout(View view){
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void intiAlarmSettingAddEditTitlePhoneLayout(View view){
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Title_TextView));
		//Save Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.AlarmSettingAddEditSaveButtonPhoneView));
		view.findViewById(R.id.AlarmSettingAddEditSaveButtonPhoneView).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.AlarmSettingAddEditSaveButtonPhoneView));
		new TKBThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.AlarmSettingAddEditSaveButtonPhoneView), 4);
	}
	private void intiAlarmSettingAddEditBodyPhoneLayout(View view){
		
		//Alarm_RLayout
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout), 3);
		//Alarm_TextView
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		//AlarmSwitch_Switch
		Switch enable = (Switch)view.findViewById(R.id.AlarmSettingProfileEnableSwitchPhoneView);
		if(currentItem != null){
			
			String state = currentItem.getState();
			if(state.equalsIgnoreCase("On")){
				Log.i(tag, " set state : " + currentItem.getState());
				enable.setChecked(true);
			}else{
				enable.setChecked(false);
			}
			
		}
		TKBTool.fitsViewHeight(30, enable);
		TKBTool.fitsViewRightMargin(10, enable);
		//TKBTool.fitsViewHeight(30, view.findViewById(R.id.AlarmSettingProfileEnableSwitchPhoneView));
		//TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.AlarmSettingProfileEnableSwitchPhoneView));
			
		//AlarmInfo_RLayout
		RelativeLayout layoutAlarmProfile = (RelativeLayout)view.findViewById(R.id.AlarmProfileSettingLayoutPhoneView);
		if(enable.isChecked()){
			Log.i(tag, " set layoutAlarmProfile is visible ");
			layoutAlarmProfile.setVisibility(View.VISIBLE);
		}else{
			layoutAlarmProfile.setVisibility(View.INVISIBLE);
		}
		TKBTool.fitsViewLeftMargin(12, layoutAlarmProfile);
		TKBTool.fitsViewWidth(297, layoutAlarmProfile);
		//TKBTool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		//TKBTool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		
		TextView position = (TextView)view.findViewById(R.id.AlarmSettingProfilePositionPhoneView);
		if(currentItem != null){
			Log.i(tag, " set position : " + currentItem.getPosition());
			position.setText(String.valueOf( currentItem.getPosition() ));
		}else{
			position.setText(String.valueOf(-1));
		}
		
		//AlarmName_RLayout
		TKBTool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout), 3);
		//AlarmName_EditText
		TextView name = (TextView)view.findViewById(R.id.AlarmSettingProfileNameTextPhoneView);
		if(currentItem != null){
			Log.i(tag, " set name : " + currentItem.getNameAlarm());
			name.setText(currentItem.getNameAlarm());
		}
		TKBTool.fitsViewLeftMargin(10, name);
		TKBTool.fitsViewRightMargin(30, name);
		TKBTool.fitsViewTextSize(14, name);
//		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
//		TKBTool.fitsViewRightMargin(30,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
//		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		
		//AlarmTime_RLayout
		TKBTool.fitsViewTopMargin(53, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout), 3);
		//LAlarmTime_TextView
		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		//RAlarmTime_TextView
		TextView time = (TextView)view.findViewById(R.id.AlarmSettingProfileTimeTextPhoneView);
		//if(currentItem != null){
			Log.i(tag, " set time : " + AGSAlarmSettingAddEditFragement.getTimeText());
			time.setText(AGSAlarmSettingAddEditFragement.getTimeText());
		//}
		TKBTool.fitsViewRightMargin(25, time);
		TKBTool.fitsViewTextSize(14, time);
//		TKBTool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
//		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
		
		//AlarmFrequency_RLayout
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout), 3);
		//LAlarmFrequency_TextView
		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		//RAlarmFrequency_TextView
		TextView frequency = (TextView)view.findViewById(R.id.AlarmSettingProfileFrequencyTextPhoneView);
		//if(currentItem != null){
			Log.i(tag, " set frequency : " + AGSAlarmSettingAddEditFragement.getFrequencyText());
			frequency.setText(AGSAlarmSettingAddEditFragement.getFrequencyText());
		//}
		TKBTool.fitsViewRightMargin(25, frequency);
		TKBTool.fitsViewTextSize(14, frequency);
//		TKBTool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
//		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
		//RAlarmFrequency_ImageView
		TKBTool.fitsViewHeight(20, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		TKBTool.fitsViewWidth(20, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		
		//AlarmMusic_RLayout
		TKBTool.fitsViewTopMargin(129, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout), 3);
		//LAlarmMusic_TextView
		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		//RAlarmMusic_TextView
		TextView music = (TextView)view.findViewById(R.id.AlarmSettingProfileMusicTextPhoneView);
		//if(currentItem != null){
			Log.i(tag, " set music : " + AGSAlarmSettingAddEditFragement.getMusicText());
			music.setText(AGSAlarmSettingAddEditFragement.getMusicText());
			((TextView)view.findViewById(R.id.AlarmSettingProfileMusicURITextPhoneView)).setText(AGSAlarmSettingAddEditFragement.getUriText());
			((TextView)view.findViewById(R.id.AlarmSettingProfileMusicMetaDataTextPhoneView)).setText(AGSAlarmSettingAddEditFragement.getMetaDataText());
		//}
		TKBTool.fitsViewRightMargin(25, music);
		TKBTool.fitsViewTextSize(14, music);
//		TKBTool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
//		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
		//RAlarmMusic_ImageView
		TKBTool.fitsViewHeight(15, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		TKBTool.fitsViewWidth(15, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		
		//AlarmVolume_RLayout
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout), 3);
		//LAlarmVolume_TextView
		TKBTool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		TKBTool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		//AlarmVolume_SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.AlarmSettingProfileVolumeSeekBarPhoneView);
		if(currentItem != null){
			Log.i(tag, " set seekBar : " + currentItem.getVolume());
			seekBar.setProgress(currentItem.getVolume());
		}
		TKBTool.fitsViewWidth(140, seekBar);
		TKBTool.fitsViewHeight(23, seekBar);
		TKBTool.fitsViewRightMargin(25, seekBar);		
		seekBar.setPadding(TKBTool.getWidth(6), TKBTool.getHeight(6), TKBTool.getWidth(6), TKBTool.getHeight(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(14), TKBTool.getWidth(16), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
//		SeekBar seekBar = (SeekBar)view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmVolume_SeekBar);
//		TKBTool.fitsViewWidth(140, seekBar);
//		TKBTool.fitsViewHeight(23, seekBar);
//		TKBTool.fitsViewRightMargin(25,seekBar);		
//		seekBar.setPadding(TKBTool.getWidth(6), TKBTool.getHeight(6), TKBTool.getWidth(6), TKBTool.getHeight(6));
//		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(14), TKBTool.getWidth(16), false);
//		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
//		seekBar.setThumb(myThumb);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void intiAlarmSettingAddEditTitlePadLayout(View view){
		
		TKBTool.fitsViewHeight(55, view);	
		//Back Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Title_TextView));
		
		//Save Button
		TKBTool.fitsViewWidth(55, view.findViewById(R.id.AlarmSettingAddEditSaveButton));
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.AlarmSettingAddEditSaveButton));
		TKBTool.fitsViewRightMargin(44, view.findViewById(R.id.AlarmSettingAddEditSaveButton));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.AlarmSettingAddEditSaveButton));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/Settings_done_f.png", "pad/Settings/Settings_done_n.png", view.findViewById(R.id.AlarmSettingAddEditSaveButton), 4);
	}
	
	private void intiAlarmSettingAddEditBodyPadLayout(View view){
		
		////Alarm_RLayout
		TKBTool.fitsViewTopMargin(37, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewWidth(667, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout), 3);
		
		//Alarm_TextView
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		
		//AlarmSwitch_Switch
		Switch enable = (Switch)view.findViewById(R.id.AlarmSettingProfileEnableSwitch);
		if(currentItem != null){
			
			String state = currentItem.getState();
			if(state.equalsIgnoreCase("On")){
				Log.i(tag, " set state : " + currentItem.getState());
				enable.setChecked(true);
			}else{
				enable.setChecked(false);
			}
			
		}
		TKBTool.fitsViewHeight(50, enable);
		TKBTool.fitsViewRightMargin(20, enable);
		
		////AlarmInfo_RLayout
		RelativeLayout layoutAlarmProfile = (RelativeLayout)view.findViewById(R.id.AlarmProfileSettingLayout);
		if(enable.isChecked()){
			Log.i(tag, " set layoutAlarmProfile is visible ");
			layoutAlarmProfile.setVisibility(View.VISIBLE);
		}else{
			layoutAlarmProfile.setVisibility(View.INVISIBLE);
		}
		TKBTool.fitsViewLeftMargin(44, layoutAlarmProfile);
		TKBTool.fitsViewWidth(667, layoutAlarmProfile);
		
		TextView position = (TextView)view.findViewById(R.id.AlarmSettingProfilePosition);
		if(currentItem != null){
			Log.i(tag, " set position : " + currentItem.getPosition());
			position.setText(String.valueOf( currentItem.getPosition() ));
		}else{
			position.setText(String.valueOf(-1));
		}
		
		//AlarmName_RLayout
		TKBTool.fitsViewTopMargin(37, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout), 3);
		//AlarmName_EditText
		TextView name = (TextView)view.findViewById(R.id.AlarmSettingProfileNameText);
		if(currentItem != null){
			Log.i(tag, " set name : " + currentItem.getNameAlarm());
			name.setText(currentItem.getNameAlarm());
		}
		TKBTool.fitsViewLeftMargin(20, name);
		TKBTool.fitsViewRightMargin(40, name);
		TKBTool.fitsViewTextSize(8, name);
		
		//AlarmTime_RLayout
		TKBTool.fitsViewTopMargin(136, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout), 3);
		//LAlarmTime_TextView
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		//RAlarmTime_TextView
		TextView time = (TextView)view.findViewById(R.id.AlarmSettingProfileTimeText);
		//if(currentItem != null){
			Log.i(tag, " set time : " + AGSAlarmSettingAddEditFragement.getTimeText());
			time.setText(AGSAlarmSettingAddEditFragement.getTimeText());
		//}
		TKBTool.fitsViewRightMargin(40, time);
		TKBTool.fitsViewTextSize(8, time);
		
		//AlarmFrequency_RLayout
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout), 3);
		//LAlarmFrequency_TextView
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		//RAlarmFrequency_TextView
		TextView frequency = (TextView)view.findViewById(R.id.AlarmSettingProfileFrequencyText);
		//if(currentItem != null){
			Log.i(tag, " set frequency : " + AGSAlarmSettingAddEditFragement.getFrequencyText());
			frequency.setText(AGSAlarmSettingAddEditFragement.getFrequencyText());
		//}
		TKBTool.fitsViewRightMargin(40, frequency);
		TKBTool.fitsViewTextSize(8, frequency);
		//RAlarmFrequency_ImageView
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		TKBTool.fitsViewWidth(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		
		//AlarmMusic_RLayout
		TKBTool.fitsViewTopMargin(297, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout), 3);
		//LAlarmMusic_TextView
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		//RAlarmMusic_TextView
		TextView music = (TextView)view.findViewById(R.id.AlarmSettingProfileMusicText);
		//if(currentItem != null){
			
			Log.i(tag, " set music : " + AGSAlarmSettingAddEditFragement.getMusicText());
			music.setText(AGSAlarmSettingAddEditFragement.getMusicText());
			((TextView)view.findViewById(R.id.AlarmSettingProfileMusicURIText)).setText(AGSAlarmSettingAddEditFragement.getUriText());
			((TextView)view.findViewById(R.id.AlarmSettingProfileMusicMetaDataText)).setText(AGSAlarmSettingAddEditFragement.getMetaDataText());
			
		//}
		TKBTool.fitsViewRightMargin(40, music);
		TKBTool.fitsViewTextSize(8, music);
		//RAlarmMusic_ImageView
		TKBTool.fitsViewHeight(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		TKBTool.fitsViewWidth(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		
		//AlarmVolume_RLayout
		TKBTool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout), 3);
		//LAlarmVolume_TextView
		TKBTool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		//AlarmVolume_SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.AlarmSettingProfileVolumeSeekBar);
		if(currentItem != null){
			Log.i(tag, " set seekBar : " + currentItem.getVolume());
			seekBar.setProgress(currentItem.getVolume());
		}
		TKBTool.fitsViewWidth(350, seekBar);
		TKBTool.fitsViewHeight(36, seekBar);
		TKBTool.fitsViewRightMargin(40,seekBar);		
		seekBar.setPadding(TKBTool.getHeight(15), TKBTool.getHeight(8), TKBTool.getHeight(15), TKBTool.getHeight(8));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), TKBTool.getHeight(23), TKBTool.getHeight(26), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
	}
//***************************PAD*********************************
}

package com.FSAl_EditAdd.SETTING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSAl_EditAdd_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_EditAdd_VIEW_SETTING";
	private int device_size = 0;
	
	public FSAl_EditAdd_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSAl_EditAdd_RLayout:
				pFSAl_EditAdd_RLayout(view);
				break;
			case R.id.pFSAl_EditAdd_RLayout_TITLE_RLayout:
				pFSAl_EditAdd_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSAl_EditAdd_RLayout_BODY_RLayout:
				pFSAl_EditAdd_RLayout_BODY_RLayout(view);
				break;
			}	
		}else{
			switch(view.getId()){
			case R.id.FSAl_EditAdd_RLayout_TITLE_RLayout:
				FSAl_EditAdd_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSAl_EditAdd_RLayout_BODY_RLayout:
				FSAl_EditAdd_RLayout_BODY_RLayout(view);
				break;			
			}	
		}
	}


//***************************PHONE*********************************
	private void pFSAl_EditAdd_RLayout(View view){
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSAl_EditAdd_RLayout_TITLE_RLayout(View view){
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Back Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Save_Button));
		view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Save_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Save_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Save_Button), 4);
	}
	private void pFSAl_EditAdd_RLayout_BODY_RLayout(View view){
		//Alarm_RLayout
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_Alarm_RLayout), 3);
		//Alarm_TextView
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		//AlarmSwitch_Switch
		Tool.fitsViewHeight(30, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch));
		Tool.fitsViewRightMargin(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch));
			
			
		//AlarmInfo_RLayout
		Tool.fitsViewLeftMargin(12, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		Tool.fitsViewWidth(297, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		
		//AlarmName_RLayout
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout), 3);
		//AlarmName_EditText
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		Tool.fitsViewRightMargin(30,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		
		//AlarmTime_RLayout
		Tool.fitsViewTopMargin(53, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout), 3);
		//LAlarmTime_TextView
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		//RAlarmTime_TextView
		Tool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
		
		//AlarmFrequency_RLayout
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout), 3);
		//LAlarmFrequency_TextView
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		//RAlarmFrequency_TextView
		Tool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
		//RAlarmFrequency_ImageView
		Tool.fitsViewHeight(20, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		Tool.fitsViewWidth(20, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		
		//AlarmMusic_RLayout
		Tool.fitsViewTopMargin(129, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout), 3);
		//LAlarmMusic_TextView
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		//RAlarmMusic_TextView
		Tool.fitsViewRightMargin(25,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
		//RAlarmMusic_ImageView
		Tool.fitsViewHeight(15, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		Tool.fitsViewWidth(15, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		
		//AlarmVolume_RLayout
		Tool.fitsViewHeight(33, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout), 3);
		//LAlarmVolume_TextView
		Tool.fitsViewLeftMargin(10,view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		Tool.fitsViewTextSize(14, view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		//AlarmVolume_SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.pFSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmVolume_SeekBar);
		Tool.fitsViewWidth(140, seekBar);
		Tool.fitsViewHeight(23, seekBar);
		Tool.fitsViewRightMargin(25,seekBar);		
		seekBar.setPadding(Tool.getWidth(6), Tool.getHeight(6), Tool.getWidth(6), Tool.getHeight(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(14), Tool.getWidth(16), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void FSAl_EditAdd_RLayout_TITLE_RLayout(View view){
		Tool.fitsViewHeight(55, view);	
		//Back Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button));
		new ThreadReadBitMapInAssets(context, "pad/Playlist/playlist_back_btn.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewWidth(55, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Save_Button));
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Save_Button));
		Tool.fitsViewRightMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Save_Button));
		new ThreadReadStateListInAssets(context, "pad/Settings/Settings_done_f.png", "pad/Settings/Settings_done_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Save_Button), 4);
	}
	private void FSAl_EditAdd_RLayout_BODY_RLayout(View view){
		//Alarm_RLayout
		Tool.fitsViewTopMargin(37, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_Alarm_RLayout), 3);
		//Alarm_TextView
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_Alarm_TextView));
		//AlarmSwitch_Switch
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch));
		Tool.fitsViewRightMargin(20, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmSwitch_Switch));
		
		
		//AlarmInfo_RLayout
		Tool.fitsViewLeftMargin(44, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		Tool.fitsViewWidth(667, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_AlarmInfo_RLayout));
		
		//AlarmName_RLayout
		Tool.fitsViewTopMargin(37, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/box.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmName_RLayout), 3);
		//AlarmName_EditText
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		Tool.fitsViewRightMargin(40,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RLayout_AlarmName_EditText));
		
		//AlarmTime_RLayout
		Tool.fitsViewTopMargin(136, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmTime_RLayout), 3);
		//LAlarmTime_TextView
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmTime_TextView));
		//RAlarmTime_TextView
		Tool.fitsViewRightMargin(40,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmTime_TextView));
		
		//AlarmFrequency_RLayout
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_03_f.png","pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmFrequency_RLayout), 3);
		//LAlarmFrequency_TextView
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmFrequency_TextView));
		//RAlarmFrequency_TextView
		Tool.fitsViewRightMargin(40,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_TextView));
		//RAlarmFrequency_ImageView
		Tool.fitsViewHeight(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		Tool.fitsViewWidth(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmFrequency_ImageView));
		
		//AlarmMusic_RLayout
		Tool.fitsViewTopMargin(297, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout));
		new ThreadReadStateListInAssets(context, "pad/Settings/alarm_box_01_f.png","pad/Settings/alarm_box_01_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmMusic_RLayout), 3);
		//LAlarmMusic_TextView
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmMusic_TextView));
		//RAlarmMusic_TextView
		Tool.fitsViewRightMargin(40,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_TextView));
		//RAlarmMusic_ImageView
		Tool.fitsViewHeight(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		Tool.fitsViewWidth(30, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmMusic_ImageView));
		
		//AlarmVolume_RLayout
		Tool.fitsViewHeight(62, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/alarm_box_03_n.png", view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_AlarmVolume_RLayout), 3);
		//LAlarmVolume_TextView
		Tool.fitsViewLeftMargin(20,view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_LAlarmVolume_TextView));
		//AlarmVolume_SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.FSAl_EditAdd_RLayout_RLayout_RLayout_RAlarmVolume_SeekBar);
		Tool.fitsViewWidth(350, seekBar);
		Tool.fitsViewHeight(36, seekBar);
		Tool.fitsViewRightMargin(40,seekBar);		
		seekBar.setPadding(Tool.getHeight(15), Tool.getHeight(8), Tool.getHeight(15), Tool.getHeight(8));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), Tool.getHeight(23), Tool.getHeight(26), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
	}
//***************************PAD*********************************
}

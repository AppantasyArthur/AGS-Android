package com.alpha.mainfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.DeviceInformation;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class MainFragementViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FAM_VIEW_SETTING";
	private int device_size = 0;
	
	public MainFragementViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			
		}else{
			switch(view.getId()){
			case R.id.FAM_RLayout_LLayout_MediaC1_RLayout:
				PAD_FAM_RLayout_LLayout_MediaC1_RLayout(view);
				break;
			case R.id.FAM_RLayout_LLayout_MediaC2_RLayout:
				PAD_FAM_RLayout_LLayout_MediaC2_RLayout(view);
				break;
			case R.id.FAM_RLayout_LEFT_RLayout:
				PAD_FAM_RLayout_LEFT_RLayout(view);
				break;
			case R.id.FAM_RLayout_CENTER_RLayout:
				PAD_FAM_RLayout_CENTER_RLayout(view);
				break;
			case R.id.FAM_RLayout_RIGHT_RLayout:
				PAD_FAM_RLayout_RIGHT_RLayout(view);
				break;
			case R.id.FAM_RLayout_BOTTOM_RLayout:
				PAD_FAM_RLayout_BOTTOM_RLayout(view);
				break;
			}	
		}
	}
//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FAM_RLayout_LLayout_MediaC1_RLayout(View view){
		TKBTool.fitsViewHeight(76, view);
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_bg.png", view, 3);
		//Sound ImageButton
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));		
		TKBTool.fitsViewHeight(21, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton).getLayoutParams().width = TKBTool.getHeight(30);
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_03.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton), 2);
		//Sound SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_SeekBar);
		TKBTool.fitsViewHeight(36, seekBar);
		seekBar.getLayoutParams().width = TKBTool.getHeight(110);
		TKBTool.fitsViewLeftMargin(10, seekBar);		
		seekBar.setPadding(TKBTool.getHeight(15), TKBTool.getHeight(8), TKBTool.getHeight(15), TKBTool.getHeight(8));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), TKBTool.getHeight(23), TKBTool.getHeight(26), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
		
		//Previous ImageButton
		TKBTool.fitsViewLeftMargin(444, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));		
		TKBTool.fitsViewHeight(34, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton).getLayoutParams().width = TKBTool.getHeight(34);
		new TKBThreadReadStateListInAssets(context, "pad/PlayBack/rew_f.png","pad/PlayBack/rew_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton), 2);
		//Play ImageButton
		TKBTool.fitsViewLeftMargin(492, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));		
		TKBTool.fitsViewHeight(45, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton).getLayoutParams().width = TKBTool.getHeight(45);
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton).setTag(0);
		new TKBThreadReadStateListInAssets(context, "pad/PlayBack/play_f.png","pad/PlayBack/play_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		TKBTool.fitsViewLeftMargin(550, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));		
		TKBTool.fitsViewHeight(34, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton).getLayoutParams().width = TKBTool.getHeight(34);
		new TKBThreadReadStateListInAssets(context, "pad/PlayBack/ff_f.png","pad/PlayBack/ff_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton), 2);
		
		//ActionProgress_ProgressBar
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ActionProgress_ProgressBar));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ActionProgress_ProgressBar).getLayoutParams().width = TKBTool.getHeight(50);
		TKBTool.fitsViewRightMargin(10, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ActionProgress_ProgressBar));
		
		//ShowCloseMediaC2 ImageButton
		TKBTool.fitsViewHeight(29, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton).getLayoutParams().width = TKBTool.getHeight(29);
		TKBTool.fitsViewRightMargin(25, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton));
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton), 2);
	}
	private void PAD_FAM_RLayout_LLayout_MediaC2_RLayout(View view){
		TKBTool.fitsViewHeight(54, view);
		TKBTool.fitsViewTopMargin(-6, view);
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/mash_bg.png", view, 3);
		//Cycle IButton
		TKBTool.fitsViewHeight(39, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton).getLayoutParams().width = TKBTool.getHeight(50);
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton), 2);
		//Random IButton
		TKBTool.fitsViewHeight(39, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton).getLayoutParams().width = TKBTool.getHeight(50);
		TKBTool.fitsViewLeftMargin(15, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton), 2);
		//Total TextView
		TKBTool.fitsViewLeftMargin(145, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));		
		TKBTool.fitsViewHeight(22, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView).getLayoutParams().width = TKBTool.getHeight(60);
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));
		
		//Music SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Music_SeekBar);
		TKBTool.fitsViewHeight(36, seekBar);
		seekBar.getLayoutParams().width = TKBTool.getHeight(630);
		seekBar.setPadding(TKBTool.getHeight(15), TKBTool.getHeight(8), TKBTool.getHeight(15), TKBTool.getHeight(8));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), TKBTool.getHeight(23), TKBTool.getHeight(27), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		seekBar.setThumb(myThumb);
		//Total TextView
		TKBTool.fitsViewLeftMargin(843, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));	
		TKBTool.fitsViewHeight(22, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView).getLayoutParams().width = TKBTool.getHeight(60);
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));		
	}
	private void PAD_FAM_RLayout_LEFT_RLayout(View view) {
		TKBTool.fitsViewWidth(284, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Speakermanagement/speaker_bg.png", view, 3);
		TKBTool.fitsViewTopMargin(-6, view);		
	}
	private void PAD_FAM_RLayout_CENTER_RLayout(View view) {
		TKBTool.fitsViewWidth(410, view);
		TKBTool.fitsViewLeftMargin(270, view);
		TKBTool.fitsViewTopMargin(-6, view);		
	}
	private void PAD_FAM_RLayout_RIGHT_RLayout(View view) {
		TKBTool.fitsViewWidth(359, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Playlist/playlist_bg.png", view, 3);
		TKBTool.fitsViewTopMargin(-6, view);		
	}
	private void PAD_FAM_RLayout_BOTTOM_RLayout(View view) {
		TKBTool.fitsViewHeight(48, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/settings_bar.png", view, 3);
		//BLEFT RLayout
		TKBTool.fitsViewWidth(270, view.findViewById(R.id.FAM_RLayout_RLayout_BLEFT_RLayout));
		//PasueALL Button
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		TKBTool.fitsViewWidth(96, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		TKBTool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settingsbar/pause all_f.png", "pad/Settingsbar/pause all_n.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button), 4);
		//BCENTER RLayout
		TKBTool.fitsViewWidth(412, view.findViewById(R.id.FAM_RLayout_RLayout_BCENTER_RLayout));
		TKBTool.fitsViewLeftMargin(270, view.findViewById(R.id.FAM_RLayout_RLayout_BCENTER_RLayout));
		//ButtonsBG_ImageView
		TKBTool.fitsViewWidth(212, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));		
		TKBTool.fitsViewLeftMargin(50, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView));
		new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView), 1);
		view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_ButtonsBG_ImageView).setTag(0);
		//Clear Button
		TKBTool.fitsViewLeftMargin(50, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewWidth(106, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		//Save Button
		TKBTool.fitsViewLeftMargin(156, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewWidth(106, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Save_Button));
		//Done Button
		TKBTool.fitsViewLeftMargin(50, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewWidth(212, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settingsbar/clear&save_f.png", "pad/Settingsbar/clear&save_done.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button), 4);
		//BRIGHT RLayout
		TKBTool.fitsViewWidth(344, view.findViewById(R.id.FAM_RLayout_RLayout_BRIGHT_RLayout));
		//Verson TextView
		TextView Verson_TextView = (TextView)view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Verson_TextView);
		TKBTool.fitsViewRightMargin(20,Verson_TextView);
		TKBTool.fitsViewTextSize(6, Verson_TextView);
		DeviceInformation DeviceInformation = new DeviceInformation(context);
		Verson_TextView.setText("Ver:"+DeviceInformation.getVersion());
		//Setting IButton 
		TKBTool.fitsViewRightMargin(20, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		TKBTool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton).getLayoutParams().width = TKBTool.getHeight(33);
		new TKBThreadReadStateListInAssets(context, "pad/Settingsbar/setting_icon_f.png", "pad/Settingsbar/setting_icon_n.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton), 2);
	}
	
//***************************PAD*********************************
}

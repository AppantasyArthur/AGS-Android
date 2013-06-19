package com.FAM.SETTING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.SeekBar;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FAM_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_VIEW_SETTING";
	private int device_size = 0;
	
	public FAM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
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
		Tool.fitsViewHeight(75, view);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_bg.png", view, 3);
		//Sound ImageButton
		Tool.fitsViewLeftMargin(33, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));		
		Tool.fitsViewHeight(21, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton).getLayoutParams().width = Tool.getHeight(30);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/volumn_03.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_IButton), 2);
		//Sound SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Sound_SeekBar);
		Tool.fitsViewHeight(36, seekBar);
		seekBar.getLayoutParams().width = Tool.getHeight(110);
		Tool.fitsViewLeftMargin(10, seekBar);		
		seekBar.setPadding(Tool.getHeight(15), Tool.getHeight(4), Tool.getHeight(15), Tool.getHeight(4));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), Tool.getHeight(23), Tool.getHeight(27), false);
		Bitmap myThumbR = Bitmap.createBitmap(myThumbO.getWidth(), myThumbO.getHeight()+Tool.getHeight(6), Config.ARGB_8888);
		Canvas canvas = new Canvas(myThumbR);
		canvas.drawBitmap(myThumbO, 0, 0,null);
		myThumbO.recycle();
		Drawable myThumb = new BitmapDrawable(myThumbR);
		seekBar.setThumb(myThumb);
		
		//Previous ImageButton
		Tool.fitsViewLeftMargin(444, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));		
		Tool.fitsViewHeight(34, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton).getLayoutParams().width = Tool.getHeight(34);
		new ThreadReadStateListInAssets(context, "pad/PlayBack/rew_f.png","pad/PlayBack/rew_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Previous_IButton), 2);
		//Play ImageButton
		Tool.fitsViewLeftMargin(492, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));		
		Tool.fitsViewHeight(45, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton).getLayoutParams().width = Tool.getHeight(45);
		new ThreadReadStateListInAssets(context, "pad/PlayBack/play_f.png","pad/PlayBack/play_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		Tool.fitsViewLeftMargin(550, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));		
		Tool.fitsViewHeight(34, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton).getLayoutParams().width = Tool.getHeight(34);
		new ThreadReadStateListInAssets(context, "pad/PlayBack/ff_f.png","pad/PlayBack/ff_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Next_IButton), 2);
		//ShowCloseMediaC2 ImageButton
		Tool.fitsViewHeight(29, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton).getLayoutParams().width = Tool.getHeight(29);
		Tool.fitsViewRightMargin(25, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton));
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_ShowCloseMediaC2_IButton), 2);
	}
	private void PAD_FAM_RLayout_LLayout_MediaC2_RLayout(View view){
		Tool.fitsViewHeight(54, view);
		Tool.fitsViewTopMargin(-6, view);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/mash_bg.png", view, 3);
		//Cycle IButton
		Tool.fitsViewHeight(30, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton).getLayoutParams().width = Tool.getHeight(35);
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle_IButton), 2);
		//Cycle IButton2
		Tool.fitsViewHeight(10, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton).getLayoutParams().width = Tool.getHeight(48);
		Tool.fitsViewLeftMargin(25, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_off.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Cycle2_IButton), 2);
		//Random IButton
		Tool.fitsViewHeight(21, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton).getLayoutParams().width = Tool.getHeight(31);
		Tool.fitsViewLeftMargin(15, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random_IButton), 2);
		//Random2 IButton
		Tool.fitsViewHeight(10, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton).getLayoutParams().width = Tool.getHeight(45);
		Tool.fitsViewLeftMargin(8, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_off.png", view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Random2_IButton), 2);
		//Total TextView
		Tool.fitsViewLeftMargin(145, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));		
		Tool.fitsViewHeight(22, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView).getLayoutParams().width = Tool.getHeight(51);
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Total_TextView));
		
		//Music SeekBar
		SeekBar seekBar = (SeekBar)view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Music_SeekBar);
		Tool.fitsViewHeight(36, seekBar);
		seekBar.getLayoutParams().width = Tool.getHeight(630);
		seekBar.setPadding(Tool.getHeight(15), Tool.getHeight(4), Tool.getHeight(15), Tool.getHeight(4));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "pad/PlayBack/volumn_control.png"), Tool.getHeight(23), Tool.getHeight(27), false);
		Bitmap myThumbR = Bitmap.createBitmap(myThumbO.getWidth(), myThumbO.getHeight()+Tool.getHeight(6), Config.ARGB_8888);
		Canvas canvas = new Canvas(myThumbR);
		canvas.drawBitmap(myThumbO, 0, 0,null);
		myThumbO.recycle();
		Drawable myThumb = new BitmapDrawable(myThumbR);
		seekBar.setThumb(myThumb);
		//Total TextView
		Tool.fitsViewLeftMargin(843, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));	
		Tool.fitsViewHeight(22, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));
		view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView).getLayoutParams().width = Tool.getHeight(51);
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FAM_RLayout_LLayout_RLayout_Current_TextView));		
	}
	private void PAD_FAM_RLayout_LEFT_RLayout(View view) {
		Tool.fitsViewWidth(282, view);
		Tool.fitsViewTopMargin(-6, view);
	}
	private void PAD_FAM_RLayout_CENTER_RLayout(View view) {
		Tool.fitsViewWidth(410, view);
		Tool.fitsViewLeftMargin(270, view);
		Tool.fitsViewTopMargin(-6, view);
	}
	private void PAD_FAM_RLayout_RIGHT_RLayout(View view) {
		Tool.fitsViewWidth(357, view);
		Tool.fitsViewTopMargin(-6, view);		
	}
	private void PAD_FAM_RLayout_BOTTOM_RLayout(View view) {
		Tool.fitsViewHeight(53, view);
		new ThreadReadBitMapInAssets(context, "pad/Settingsbar/settings_bar.png", view, 3);
		//BLEFT RLayout
		Tool.fitsViewWidth(270, view.findViewById(R.id.FAM_RLayout_RLayout_BLEFT_RLayout));
		//PasueALL Button
		Tool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		Tool.fitsViewWidth(96, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		Tool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button));
		new ThreadReadStateListInAssets(context, "pad/Settingsbar/pause all_f.png", "pad/Settingsbar/pause all_n.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_PauseALL_Button), 4);
		//BCENTER RLayout
		Tool.fitsViewWidth(412, view.findViewById(R.id.FAM_RLayout_RLayout_BCENTER_RLayout));
		Tool.fitsViewLeftMargin(270, view.findViewById(R.id.FAM_RLayout_RLayout_BCENTER_RLayout));
		//Edit Button
		Tool.fitsViewLeftMargin(50, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button));
		Tool.fitsViewHeight(35, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button));
		Tool.fitsViewWidth(100, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Edit_Button));
		//Clear Button
		Tool.fitsViewLeftMargin(150, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		Tool.fitsViewHeight(35, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		Tool.fitsViewWidth(100, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Clear_Button));
		//Done Button
		Tool.fitsViewLeftMargin(50, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		Tool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(212, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6,  view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "pad/Settingsbar/clear&save_f.png", "pad/Settingsbar/clear&save_done.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Done_Button), 4);
		//BRIGHT RLayout
		Tool.fitsViewWidth(344, view.findViewById(R.id.FAM_RLayout_RLayout_BRIGHT_RLayout));
		//Setting IButton 
		Tool.fitsViewRightMargin(20, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		Tool.fitsViewHeight(33, view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton).getLayoutParams().width = Tool.getHeight(33);
		new ThreadReadStateListInAssets(context, "pad/Settingsbar/setting_icon_f.png", "pad/Settingsbar/setting_icon_n.png", view.findViewById(R.id.FAM_RLayout_RLayout_RLayout_Setting_IButton), 2);
	}
	
//***************************PAD*********************************
}

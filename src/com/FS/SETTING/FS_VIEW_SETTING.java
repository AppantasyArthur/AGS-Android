package com.FS.SETTING;

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

public class FS_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FS_VIEW_SETTING";
	private int device_size = 0;
	
	public FS_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFS_RLayout:
				pFS_RLayout(view);
				break;			
			case R.id.pFS_RLayout_TITLE2_RLayout:
				pFS_RLayout_TITLE2_RLayout(view);
				break;
			case R.id.pFS_RLayout_RLayout_Nowplaying_Button:
				pFS_RLayout_RLayout_Nowplaying_Button(view);
				break;
			case R.id.pFS_RLayout_RLayout_Center_TextView:
				pFS_RLayout_RLayout_Center_TextView(view);
				break;
			case R.id.pFS_RLayout_RLayout_Music_Button:
				pFS_RLayout_RLayout_Music_Button(view);
				break;	
			case R.id.pFS_RLayout_TITLE3_RLayout:
				pFS_RLayout_TITLE3_RLayout(view);
				break;
			case R.id.pFS_RLayout_RLayout_Sound_IButton:
				pFS_RLayout_RLayout_Sound_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_Sound_SeekBar:
				pFS_RLayout_RLayout_Sound_SeekBar(view);
				break;
			case R.id.pFS_RLayout_RLayout_Previous_IButton:
				pFS_RLayout_RLayout_Previous_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_Play_IButton:
				pFS_RLayout_RLayout_Play_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_Next_IButton:
				pFS_RLayout_RLayout_Next_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton:
				pFS_RLayout_RLayout_ShowTITLE4_IButton(view);
				break;
			case R.id.pFS_RLayout_TITLE4_RLayout:
				pFS_RLayout_TITLE4_RLayout(view);
				break;
			case R.id.pFS_RLayout_RLayout_Cycle_IButton:
				pFS_RLayout_RLayout_Cycle_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_Random_IButton:
				pFS_RLayout_RLayout_Random_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_Current_TextView:
				pFS_RLayout_RLayout_Current_TextView(view);
				break;
			case R.id.pFS_RLayout_RLayout_Music_SeekBar:
				pFS_RLayout_RLayout_Music_SeekBar(view);
				break;
			case R.id.pFS_RLayout_RLayout_Total_TextView:
				pFS_RLayout_RLayout_Total_TextView(view);
				break;
			case R.id.pFS_RLayout_SPEAKER_EListView:
				pFS_RLayout_SPEAKER_EListView(view);
				break;
			case R.id.pFS_RLayout_Bottom_RLayout:
				pFS_RLayout_Bottom_RLayout(view);
				break;
			case R.id.pFS_RLayout_RLayout_Setting_IButton:
				pFS_RLayout_RLayout_Setting_IButton(view);
				break;
			case R.id.pFS_RLayout_RLayout_PASUEALL_Button:
				pFS_RLayout_RLayout_PASUEALL_Button(view);
				break;			
			}
		}else{
			switch(view.getId()){
			case R.id.FS_RLayout:
				PAD_FS_RLayout(view);
				break;
			case R.id.FS_RLayout_TITLE_RLayout:
				PAD_FS_RLayout_TITLE_RLayout(view);
				break;
//			case R.id.FS_RLayout_TITLE2_RLayout:
//				PAD_FS_RLayout_TITLE2_RLayout(view);
//				break;
			case R.id.FS_RLayout_SPEAKER_EListView:
				PAD_FS_RLayout_SPEAKER_EListView(view);
				break;
			}	
		}
	}	
	
	//***************************PHONE*********************************	
	
	private void pFS_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}	
	private void pFS_RLayout_TITLE2_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);
	}
	private void pFS_RLayout_RLayout_Nowplaying_Button(View view) {
		Tool.fitsViewLeftMargin(7, view);
		Tool.fitsViewHeight(26, view);
		Tool.fitsViewWidth(59, view);
		Tool.fitsViewTextSize(10, view);
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view, 4);
	}
	private void pFS_RLayout_RLayout_Center_TextView(View view) {
		Tool.fitsViewTextSize(18, view);
	}
	private void pFS_RLayout_RLayout_Music_Button(View view) {
		Tool.fitsViewRightMargin(7, view);
		Tool.fitsViewHeight(26, view);
		Tool.fitsViewWidth(59, view);
		Tool.fitsViewTextSize(10, view);
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view, 4);
	}
	private void pFS_RLayout_TITLE3_RLayout(View view) {
		Tool.fitsViewHeight(43, view);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/play_bar.png", view, 3);
	}
	private void pFS_RLayout_RLayout_Sound_IButton(View view) {
		Tool.fitsViewWidth(23, view);
		view.getLayoutParams().height = Tool.getWidth(23);
		Tool.fitsViewLeftMargin(7, view);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume.png", view, 2);
	}
	private void pFS_RLayout_RLayout_Sound_SeekBar(View view) {
		Tool.fitsViewWidth(72, view);
		view.getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(4, view);		
		view.setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view).setThumb(myThumb);
	}
	private void pFS_RLayout_RLayout_Previous_IButton(View view) {
		//Previous ImageButton
		Tool.fitsViewLeftMargin(126, view);		
		Tool.fitsViewWidth(33, view);
		view.getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_left_f.png","phone/play_volume/arrow_left_n.png", view, 2);
	}
	private void pFS_RLayout_RLayout_Play_IButton(View view) {
		//Play ImageButton
		Tool.fitsViewLeftMargin(175, view);		
		Tool.fitsViewWidth(40, view);
		view.getLayoutParams().height = Tool.getWidth(41);		
		view.setTag(0);
		new ThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", view, 2);
	}
	private void pFS_RLayout_RLayout_Next_IButton(View view) {
		//Next ImageButton
		Tool.fitsViewLeftMargin(229, view);		
		Tool.fitsViewWidth(33, view);
		view.getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_right_f.png","phone/play_volume/arrow_right_n.png", view, 2);
	}
	private void pFS_RLayout_RLayout_ShowTITLE4_IButton(View view) {
		//SHOW TITLE4_IButton
		Tool.fitsViewWidth(22, view);
		view.getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/timeline_open.png", view, 2);
	}
	private void pFS_RLayout_TITLE4_RLayout(View view) {
		Tool.fitsViewHeight(37, view);			
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume_bar.png", view, 3);
	}
	private void pFS_RLayout_RLayout_Cycle_IButton(View view) {
		//Cycle IButton
		Tool.fitsViewWidth(39, view);
		view.getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view);
		Tool.fitsViewTopMargin(4, view);
		view.setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", view, 2);
	}
	private void pFS_RLayout_RLayout_Random_IButton(View view) {
		//Random IButton
		Tool.fitsViewWidth(39, view);
		view.getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(2, view);
		Tool.fitsViewTopMargin(4, view);
		view.setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", view, 2);
	}
	private void pFS_RLayout_RLayout_Current_TextView(View view) {
		Tool.fitsViewWidth(35, view);
		view.getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewLeftMargin(90, view);
		Tool.fitsViewTextSize(10, view);
	}
	private void pFS_RLayout_RLayout_Music_SeekBar(View view) {		
		view.getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(1, view);		
		Tool.fitsViewRightMargin(1, view);
		view.setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view).setThumb(myThumb);
	}
	private void pFS_RLayout_RLayout_Total_TextView(View view) {		
		Tool.fitsViewWidth(35, view);
		view.getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view);
		Tool.fitsViewTextSize(10, view);
	}
	private void pFS_RLayout_SPEAKER_EListView(View view) {
		FS_SPEAKER_ExpandableListAdapter_Phone EBaseAdapter = new FS_SPEAKER_ExpandableListAdapter_Phone(context,(FS_SPEAKER_ExpandableListView)view);
		((FS_SPEAKER_ExpandableListView)view).setAdapter(EBaseAdapter);
		((FS_SPEAKER_ExpandableListView)view).setItemsCanFocus(true);
		((FS_SPEAKER_ExpandableListView)view).setGroupIndicator(null);
	}
	private void pFS_RLayout_Bottom_RLayout(View view) {
		Tool.fitsViewHeight(30, view);			
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
	}
	private void pFS_RLayout_RLayout_Setting_IButton(View view) {
		Tool.fitsViewWidth(24, view);
		view.getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view);		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view, 2);
	}
	private void pFS_RLayout_RLayout_PASUEALL_Button(View view) {
		Tool.fitsViewWidth(66, view);
		view.getLayoutParams().height = Tool.getWidth(27);		
		Tool.fitsViewTextSize(10, view);			
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view, 4);
	}
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FS_RLayout(View view){
		Tool.fitsViewWidth(284, view);		
	}
	private void PAD_FS_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(56, view);
		new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/speaker_navigation bar.png", view, 3);
		//Speaker TextView
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
	}
//	private void PAD_FS_RLayout_TITLE2_RLayout(View view) {		
//		Tool.fitsViewHeight(26, view);
//		//Speaker2 TextView
//		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//		Tool.fitsViewTextSize(8, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//	}
	private void PAD_FS_RLayout_SPEAKER_EListView(View view) {		
		Tool.fitsViewWidth(270, view);	
		FS_SPEAKER_ExpandableListAdapter_Pad EBaseAdapter = new FS_SPEAKER_ExpandableListAdapter_Pad(context,(FS_SPEAKER_ExpandableListView)view);
		((FS_SPEAKER_ExpandableListView)view).setAdapter(EBaseAdapter);
		((FS_SPEAKER_ExpandableListView)view).setItemsCanFocus(true);
		((FS_SPEAKER_ExpandableListView)view).setGroupIndicator(null);
	}
//***************************PAD*********************************
	
}

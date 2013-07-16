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
			case R.id.pFS_RLayout_TITLE2_1_RLayout:
				pFS_RLayout_TITLE2_1_RLayout(view);
				break;	
			case R.id.pFS_RLayout_TITLE3_RLayout:
				pFS_RLayout_TITLE3_RLayout(view);
				break;			
			case R.id.pFS_RLayout_TITLE4_RLayout:
				pFS_RLayout_TITLE4_RLayout(view);
				break;
			case R.id.pFS_RLayout_SPEAKER_EListView:
				pFS_RLayout_SPEAKER_EListView(view);
				break;			
			case R.id.pFS_RLayout_Bottom_RLayout:
				pFS_RLayout_Bottom_RLayout(view);
				break;
			case R.id.pFS_RLayout_Bottom2_RLayout:
				pFS_RLayout_Bottom2_RLayout(view);
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
		//Nowplaying_Button
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button), 4);
		//Center_TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFS_RLayout_RLayout_Center_TextView));
		//Music_Button
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button), 4);
	}
	
	private void pFS_RLayout_TITLE2_1_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);
		//Close_Button
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button), 4);
		//Center_TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFS_RLayout_RLayout_Center2_TextView));
		//Done_Button
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "phone/grouprooms/group_done_f.png","phone/grouprooms/group_done.png", view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button), 4);
	}
	
	private void pFS_RLayout_TITLE3_RLayout(View view) {
		Tool.fitsViewHeight(43, view);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/play_bar.png", view, 3);
		//Sound_IButton
		Tool.fitsViewWidth(23, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton).getLayoutParams().height = Tool.getWidth(23);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume.png", view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton), 2);
		//Sound_SeekBar
		Tool.fitsViewWidth(72, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar));
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar).getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar));		
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar).setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar)).setThumb(myThumb);
		//Previous ImageButton
		Tool.fitsViewLeftMargin(126, view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));		
		Tool.fitsViewWidth(33, view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton).getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_left_f.png","phone/play_volume/arrow_left_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton), 2);
		//Play ImageButton
		Tool.fitsViewLeftMargin(175, view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));		
		Tool.fitsViewWidth(40, view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton).getLayoutParams().height = Tool.getWidth(41);		
		view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton).setTag(0);
		new ThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		Tool.fitsViewLeftMargin(229, view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));		
		Tool.fitsViewWidth(33, view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton).getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_right_f.png","phone/play_volume/arrow_right_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton), 2);
		//SHOW TITLE4_IButton
		Tool.fitsViewWidth(22, view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton));
		new ThreadReadBitMapInAssets(context, "phone/play_volume/timeline_open.png", view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton), 2);
	}	
	private void pFS_RLayout_TITLE4_RLayout(View view) {
		Tool.fitsViewHeight(37, view);			
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume_bar.png", view, 3);
		//Cycle IButton
		Tool.fitsViewWidth(39, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton), 2);
		//Random IButton
		Tool.fitsViewWidth(39, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		Tool.fitsViewLeftMargin(2, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton), 2);
		//Current_TextView
		Tool.fitsViewWidth(35, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewLeftMargin(90, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		//Music_SeekBar
		view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar).getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(1, view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar));		
		Tool.fitsViewRightMargin(1, view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar));
		view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar).setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar)).setThumb(myThumb);
		//Total_TextView
		Tool.fitsViewWidth(35, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
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
		//Setting_IButton
		Tool.fitsViewWidth(24, view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton).getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton));		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton), 2);
		//PASUEALL_Button
		Tool.fitsViewWidth(66, view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button));
		view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button).getLayoutParams().height = Tool.getWidth(27);		
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button));			
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button), 4);
	}
	private void pFS_RLayout_Bottom2_RLayout(View view) {
		Tool.fitsViewHeight(30, view);			
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
		//Setting_IButton
		Tool.fitsViewWidth(24, view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton).getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton));		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton), 2);
		//UNSELECT_Button
		Tool.fitsViewWidth(66, view.findViewById(R.id.pFS_RLayout_RLayout_UNSELECT_Button));
		view.findViewById(R.id.pFS_RLayout_RLayout_UNSELECT_Button).getLayoutParams().height = Tool.getWidth(27);		
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_UNSELECT_Button));			
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_UNSELECT_Button), 4);
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

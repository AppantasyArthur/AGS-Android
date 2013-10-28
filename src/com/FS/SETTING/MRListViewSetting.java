package com.FS.SETTING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SeekBar;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class MRListViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FS_VIEW_SETTING";
	private int device_size = 0;
	
	public MRListViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
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
			case R.id.FS_RLayout_SPEAKER_RLayout:
				FS_RLayout_SPEAKER_RLayout(view);
				break;
			}	
		}
	}	
	
	
	//***************************PHONE*********************************	
	
	
	private void pFS_RLayout(View view) {
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}	
	private void pFS_RLayout_TITLE2_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);
		//Nowplaying_Button
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Nowplaying_Button), 4);
		//Center_TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFS_RLayout_RLayout_Center_TextView));
		//Music_Button
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Music_Button), 4);
	}
	
	private void pFS_RLayout_TITLE2_1_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);
		//Close_Button
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_Close_Button), 4);
		//Center_TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFS_RLayout_RLayout_Center2_TextView));
		//Done_Button
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "phone/grouprooms/group_done_f.png","phone/grouprooms/group_done.png", view.findViewById(R.id.pFS_RLayout_RLayout_Done_Button), 4);
	}
	
	private void pFS_RLayout_TITLE3_RLayout(View view) {
		TKBTool.fitsViewHeight(43, view);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/play_bar.png", view, 3);
		//Sound_IButton
		TKBTool.fitsViewWidth(23, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton).getLayoutParams().height = TKBTool.getWidth(23);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton));
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume.png", view.findViewById(R.id.pFS_RLayout_RLayout_Sound_IButton), 2);
		//Sound_SeekBar
		TKBTool.fitsViewWidth(72, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar));
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar).getLayoutParams().height = TKBTool.getWidth(23);	
		TKBTool.fitsViewLeftMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar));		
		view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar).setPadding(TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(12), TKBTool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFS_RLayout_RLayout_Sound_SeekBar)).setThumb(myThumb);
		//Previous ImageButton
		TKBTool.fitsViewLeftMargin(126, view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));		
		TKBTool.fitsViewWidth(33, view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton).getLayoutParams().height = TKBTool.getWidth(34);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/arrow_left_f.png","phone/play_volume/arrow_left_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Previous_IButton), 2);
		//Play ImageButton
		TKBTool.fitsViewLeftMargin(175, view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));		
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton).getLayoutParams().height = TKBTool.getWidth(41);		
		view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton).setTag(0);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		TKBTool.fitsViewLeftMargin(229, view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));		
		TKBTool.fitsViewWidth(33, view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton).getLayoutParams().height = TKBTool.getWidth(34);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/arrow_right_f.png","phone/play_volume/arrow_right_n.png", view.findViewById(R.id.pFS_RLayout_RLayout_Next_IButton), 2);
		//SHOW TITLE4_IButton
		TKBTool.fitsViewWidth(22, view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewRightMargin(6, view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton));
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/timeline_open.png", view.findViewById(R.id.pFS_RLayout_RLayout_ShowTITLE4_IButton), 2);
	}	
	private void pFS_RLayout_TITLE4_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_bar.png", view, 3);
		//Cycle IButton
		TKBTool.fitsViewWidth(39, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", view.findViewById(R.id.pFS_RLayout_RLayout_Cycle_IButton), 2);
		//Random IButton
		TKBTool.fitsViewWidth(39, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewLeftMargin(2, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", view.findViewById(R.id.pFS_RLayout_RLayout_Random_IButton), 2);
		//Current_TextView
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewLeftMargin(90, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Current_TextView));
		//Music_SeekBar
		view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar).getLayoutParams().height = TKBTool.getWidth(23);	
		TKBTool.fitsViewLeftMargin(1, view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar));		
		TKBTool.fitsViewRightMargin(1, view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar));
		view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar).setPadding(TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(12), TKBTool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFS_RLayout_RLayout_Music_SeekBar)).setThumb(myThumb);
		//Total_TextView
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewRightMargin(6, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_Total_TextView));
	}

	private void pFS_RLayout_SPEAKER_EListView(View view) {
		FS_SPEAKER_ExpandableListAdapter_Phone EBaseAdapter = new FS_SPEAKER_ExpandableListAdapter_Phone(context,(ExpandableListView)view);
		((ExpandableListView)view).setAdapter(EBaseAdapter);
		((ExpandableListView)view).setItemsCanFocus(true);
		((ExpandableListView)view).setGroupIndicator(null);
	}	
	private void pFS_RLayout_Bottom_RLayout(View view) {
		TKBTool.fitsViewHeight(30, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
		//Setting_IButton
		TKBTool.fitsViewWidth(24, view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton).getLayoutParams().height = TKBTool.getWidth(30);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton));		
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFS_RLayout_RLayout_Setting_IButton), 2);
		//PASUEALL_Button
		TKBTool.fitsViewWidth(66, view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button));
		view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button).getLayoutParams().height = TKBTool.getWidth(27);		
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button));			
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_PASUEALL_Button), 4);
	}
	private void pFS_RLayout_Bottom2_RLayout(View view) {
		TKBTool.fitsViewHeight(30, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);		
		//Setting_IButton
		TKBTool.fitsViewWidth(24, view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton));
		view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton).getLayoutParams().height = TKBTool.getWidth(30);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton));		
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFS_RLayout_RLayout_Setting2_IButton), 2);
		//UNSELECT_Button
		TKBTool.fitsViewWidth(66, view.findViewById(R.id.pFS_RLayout_RLayout_SELECT_Button));
		view.findViewById(R.id.pFS_RLayout_RLayout_SELECT_Button).getLayoutParams().height = TKBTool.getWidth(27);		
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFS_RLayout_RLayout_SELECT_Button));			
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFS_RLayout_RLayout_SELECT_Button), 4);
	}
//***************************PHONE*********************************
//***************************PAD*********************************	
	private void PAD_FS_RLayout(View view){
		TKBTool.fitsViewWidth(284, view);		
	}
	private void PAD_FS_RLayout_TITLE_RLayout(View view) {		
		TKBTool.fitsViewHeight(56, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Speakermanagement/speaker_navigation bar.png", view, 3);
		//Speaker TextView
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.FS_RLayout_RLayout_Speaker_TextView));
	}
//	private void PAD_FS_RLayout_TITLE2_RLayout(View view) {		
//		Tool.fitsViewHeight(26, view);
//		//Speaker2 TextView
//		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//		Tool.fitsViewTextSize(8, view.findViewById(R.id.FS_RLayout_RLayout_Speaker2_TextView));
//	}
	private void FS_RLayout_SPEAKER_RLayout(View view) {		
		TKBTool.fitsViewWidth(270, view);
		
		ExpandableListView SPEAKER_EListView = (ExpandableListView)view.findViewById(R.id.FS_RLayout_SPEAKER_EListView);
		FS_SPEAKER_ExpandableListAdapter_Pad EBaseAdapter = new FS_SPEAKER_ExpandableListAdapter_Pad(context,SPEAKER_EListView);
		SPEAKER_EListView.setAdapter(EBaseAdapter);
		SPEAKER_EListView.setItemsCanFocus(true);
		SPEAKER_EListView.setGroupIndicator(null);		
	}
//***************************PAD*********************************
	
}

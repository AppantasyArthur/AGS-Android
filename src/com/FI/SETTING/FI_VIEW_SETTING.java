package com.FI.SETTING;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class FI_VIEW_SETTING {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FI_VIEW_SETTING";
	private int device_size = 0;
	
	public FI_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			switch(view.getId()){
			case R.id.pFI_RLayout:
				pFI_RLayout(view);
				break;			
			case R.id.pFI_RLayout_TITLE2_RLayout:
				pFI_RLayout_TITLE2_RLayout(view);
				break;		
			case R.id.pFI_RLayout_TITLE2_1_RLayout:
				pFI_RLayout_TITLE2_1_RLayout(view);
				break;					
			case R.id.pFI_RLayout_TITLE3_RLayout:
				pFI_RLayout_TITLE3_RLayout(view);
				break;		
			
			case R.id.pFI_RLayout_TITLE4_RLayout:
				pFI_RLayout_TITLE4_RLayout(view);
				break;			
			case R.id.pFI_RLayout_UP_RLayout:
				pFI_RLayout_UP_RLayout(view);
				break;				
			case R.id.pFI_RLayout_DOWN_RLayout:
				pFI_RLayout_DOWN_RLayout(view);
				break;
			case R.id.pFI_RLayout_Bottom_RLayout:
				pFI_RLayout_Bottom_RLayout(view);
				break;
			case R.id.pFI_RLayout_Bottom2_RLayout:
				pFI_RLayout_Bottom2_RLayout(view);
				break;
			}
		}else{
			switch(view.getId()){
			case R.id.FI_RLayout_UP_RLayout:
				PAD_FI_RLayout_UP_RLayout(view);
				break;
			case R.id.FI_RLayout_DOWN_RLayout:
				PAD_FI_RLayout_DOWN_RLayout(view);
				break;
			}	
		}
	}

	//***************************PHONE*********************************
	private void pFI_RLayout(View view) {		
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFI_RLayout_TITLE2_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);		
		//Speaker_Button
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button), 4);
		//Center_TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFI_RLayout_RLayout_Center_TextView));	
		//Music_Button
		TKBTool.fitsViewRightMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button), 4);
	}	
	private void pFI_RLayout_TITLE2_1_RLayout(View view) {
		TKBTool.fitsViewHeight(36, view);
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);	
		//Close_Button
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button), 4);
		//Center2_TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFI_RLayout_RLayout_Center2_TextView));	
	}
	private void pFI_RLayout_TITLE3_RLayout(View view) {
		TKBTool.fitsViewHeight(43, view);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/play_bar.png", view, 3);
		//Sound_IButton
		TKBTool.fitsViewWidth(23, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton).getLayoutParams().height = TKBTool.getWidth(23);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume.png", view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton), 2);
		//Sound_SeekBar
		TKBTool.fitsViewWidth(72, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar));
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar).getLayoutParams().height = TKBTool.getWidth(23);	
		TKBTool.fitsViewLeftMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar));		
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar).setPadding(TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(14), TKBTool.getWidth(16), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar)).setThumb(myThumb);
		
		//Previous ImageButton
		TKBTool.fitsViewLeftMargin(126, view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));		
		TKBTool.fitsViewWidth(33, view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton).getLayoutParams().height = TKBTool.getWidth(34);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/arrow_left_f.png","phone/play_volume/arrow_left_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton), 2);
		//Play_IButton
		TKBTool.fitsViewLeftMargin(175, view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));		
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton).getLayoutParams().height = TKBTool.getWidth(41);		
		view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton).setTag(0);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		TKBTool.fitsViewLeftMargin(229, view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));		
		TKBTool.fitsViewWidth(33, view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton).getLayoutParams().height = TKBTool.getWidth(34);
		new TKBThreadReadStateListInAssets(context, "phone/play_volume/arrow_right_f.png","phone/play_volume/arrow_right_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton), 2);
		//SHOW TITLE4_IButton
		TKBTool.fitsViewWidth(22, view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewRightMargin(6, view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton));
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/timeline_open.png", view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton), 2);
	}	
	private void pFI_RLayout_TITLE4_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/volume_bar.png", view, 3);
		//Cycle IButton
		TKBTool.fitsViewWidth(39, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton), 2);
		//Random IButton
		TKBTool.fitsViewWidth(39, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewLeftMargin(2, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		TKBTool.fitsViewTopMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton).setTag(0);
		new TKBThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton), 2);
		//Current_TextView
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewLeftMargin(90, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		//Music_SeekBar
		view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar).getLayoutParams().height = TKBTool.getWidth(23);	
		TKBTool.fitsViewLeftMargin(1, view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar));		
		TKBTool.fitsViewRightMargin(1, view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar));
		view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar).setPadding(TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6), TKBTool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(TKBTool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), TKBTool.getWidth(12), TKBTool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar)).setThumb(myThumb);
		//Total_TextView
		TKBTool.fitsViewWidth(40, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView).getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewRightMargin(6, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		
	}
	private void pFI_RLayout_UP_RLayout(View view){		
		new TKBThreadReadBitMapInAssets(context, "phone/nowplaying/now playing_bg.PNG", view, 3);
		//Point LLayout
		view.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout).getLayoutParams().height = TKBTool.getWidth(10);
		TKBTool.fitsViewBottomMargin(15, view.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout));
	}
	private void pFI_RLayout_DOWN_RLayout(View view){
//		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_bg.png", view, 3);		
		//INFOR ListView
		FI_ListView fi_ListView = (FI_ListView)view.findViewById(R.id.pFI_RLayout_RLayout_QUEUE_ListView);
		fi_ListView.setAdapter(new FI_Queqe_ListView_BaseAdapter_Phone(context));
	}
	private void pFI_RLayout_Bottom_RLayout(View view) {
		TKBTool.fitsViewHeight(30, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);
		//Setting_IButton
		TKBTool.fitsViewWidth(24, view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton).getLayoutParams().height = TKBTool.getWidth(30);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));		
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton), 2);
		//Queue_Button
		TKBTool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button));
		view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button).getLayoutParams().height = TKBTool.getWidth(27);		
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button));			
		new TKBThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button), 4);
	}
	
	private void pFI_RLayout_Bottom2_RLayout(View view) {
		TKBTool.fitsViewHeight(30, view);			
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);
		
		//ButtonsBG_ImageView
		TKBTool.fitsViewWidth(132, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		TKBTool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));		
		TKBTool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		new TKBThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png", view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView), 1);
		view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView).setTag(0);
		//Clear Button
		TKBTool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		TKBTool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		//Save Button
		TKBTool.fitsViewLeftMargin(165, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		//Done Button
		TKBTool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewWidth(132, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "phone/queue/done_button_f.PNG", "phone/queue/done_button_n.PNG", view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button), 4);
		//Setting2_IButton
		TKBTool.fitsViewWidth(24, view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton).getLayoutParams().height = TKBTool.getWidth(30);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));		
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton), 2);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FI_RLayout_UP_RLayout(View view){
		TKBTool.fitsViewHeight(342, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Nowplaying/nowplaying_bg_f.png", view, 3);
		//DTITLE RLayout
		TKBTool.fitsViewHeight(38, view.findViewById(R.id.FI_RLayout_RLayout_UTITLE_RLayout));		
		//Playing TextView
		TKBTool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		//Point LLayout
		TKBTool.fitsViewHeight(10, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		TKBTool.fitsViewBottomMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
	}
	private void PAD_FI_RLayout_DOWN_RLayout(View view){
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/queqe_bg.png", view, 3);
		//DTITLE RLayout
		TKBTool.fitsViewHeight(35, view.findViewById(R.id.FI_RLayout_RLayout_DTITLE_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Queqe/queqe_navigation.png", view.findViewById(R.id.FI_RLayout_RLayout_DTITLE_RLayout), 3);
		//QUEUE TextView
		TKBTool.fitsViewLeftMargin(30, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		//INFOR ListView
		FI_ListView fi_ListView = (FI_ListView)view.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		fi_ListView.setAdapter(new FI_Queqe_ListView_BaseAdapter_PAD(context));
	}
//***************************PAD*********************************
	
}

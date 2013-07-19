package com.FI.SETTING;

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

public class FI_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FI_VIEW_SETTING";
	private int device_size = 0;
	
	public FI_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
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
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFI_RLayout_TITLE2_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);		
		//Speaker_Button
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Speaker_Button), 4);
		//Center_TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFI_RLayout_RLayout_Center_TextView));	
		//Music_Button
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Music_Button), 4);
	}	
	private void pFI_RLayout_TITLE2_1_RLayout(View view) {
		Tool.fitsViewHeight(36, view);
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/top_talie.PNG", view, 3);	
		//Close_Button
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Close_Button), 4);
		//Center2_TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFI_RLayout_RLayout_Center2_TextView));	
	}
	private void pFI_RLayout_TITLE3_RLayout(View view) {
		Tool.fitsViewHeight(43, view);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/play_bar.png", view, 3);
		//Sound_IButton
		Tool.fitsViewWidth(23, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton).getLayoutParams().height = Tool.getWidth(23);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton));
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume.png", view.findViewById(R.id.pFI_RLayout_RLayout_Sound_IButton), 2);
		//Sound_SeekBar
		Tool.fitsViewWidth(72, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar));
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar).getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar));		
		view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar).setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFI_RLayout_RLayout_Sound_SeekBar)).setThumb(myThumb);
		
		//Previous ImageButton
		Tool.fitsViewLeftMargin(126, view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));		
		Tool.fitsViewWidth(33, view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton).getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_left_f.png","phone/play_volume/arrow_left_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Previous_IButton), 2);
		//Play_IButton
		Tool.fitsViewLeftMargin(175, view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));		
		Tool.fitsViewWidth(40, view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton).getLayoutParams().height = Tool.getWidth(41);		
		view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton).setTag(0);
		new ThreadReadStateListInAssets(context, "phone/play_volume/play_f.png","phone/play_volume/play_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Play_IButton), 2);
		//Next ImageButton
		Tool.fitsViewLeftMargin(229, view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));		
		Tool.fitsViewWidth(33, view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton).getLayoutParams().height = Tool.getWidth(34);
		new ThreadReadStateListInAssets(context, "phone/play_volume/arrow_right_f.png","phone/play_volume/arrow_right_n.png", view.findViewById(R.id.pFI_RLayout_RLayout_Next_IButton), 2);
		//SHOW TITLE4_IButton
		Tool.fitsViewWidth(22, view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton));
		new ThreadReadBitMapInAssets(context, "phone/play_volume/timeline_open.png", view.findViewById(R.id.pFI_RLayout_RLayout_ShowTITLE4_IButton), 2);
	}	
	private void pFI_RLayout_TITLE4_RLayout(View view) {
		Tool.fitsViewHeight(37, view);			
		new ThreadReadBitMapInAssets(context, "phone/play_volume/volume_bar.png", view, 3);
		//Cycle IButton
		Tool.fitsViewWidth(39, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/repeat off_f.png", view.findViewById(R.id.pFI_RLayout_RLayout_Cycle_IButton), 2);
		//Random IButton
		Tool.fitsViewWidth(39, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		Tool.fitsViewHeight(26, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		Tool.fitsViewLeftMargin(2, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		Tool.fitsViewTopMargin(4, view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton).setTag(0);
		new ThreadReadBitMapInAssets(context, "phone/play_volume/shuffle off_f.PNG", view.findViewById(R.id.pFI_RLayout_RLayout_Random_IButton), 2);
		//Current_TextView
		Tool.fitsViewWidth(35, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewLeftMargin(90, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Current_TextView));
		//Music_SeekBar
		view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar).getLayoutParams().height = Tool.getWidth(23);	
		Tool.fitsViewLeftMargin(1, view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar));		
		Tool.fitsViewRightMargin(1, view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar));
		view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar).setPadding(Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6), Tool.getWidth(6));
		Bitmap myThumbO = Bitmap.createScaledBitmap(Tool.readBitMapInAssets(context, "phone/play_volume/base_icon.png"), Tool.getWidth(12), Tool.getWidth(14), false);
		Drawable myThumb = new BitmapDrawable(context.getResources(),myThumbO);
		((SeekBar)view.findViewById(R.id.pFI_RLayout_RLayout_Music_SeekBar)).setThumb(myThumb);
		//Total_TextView
		Tool.fitsViewWidth(35, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView).getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewRightMargin(6, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Total_TextView));
		
	}
	private void pFI_RLayout_UP_RLayout(View view){		
		new ThreadReadBitMapInAssets(context, "phone/nowplaying/now playing_bg.PNG", view, 3);
		//Point LLayout
		view.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout).getLayoutParams().height = Tool.getWidth(10);
		Tool.fitsViewBottomMargin(15, view.findViewById(R.id.pFI_RLayout_RLayout_Point_LLayout));
	}
	private void pFI_RLayout_DOWN_RLayout(View view){
//		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_bg.png", view, 3);		
		//INFOR ListView
		FI_ListView fi_ListView = (FI_ListView)view.findViewById(R.id.pFI_RLayout_RLayout_QUEUE_ListView);
		fi_ListView.setAdapter(new FI_Queqe_ListView_BaseAdapter_Phone(context));
	}
	private void pFI_RLayout_Bottom_RLayout(View view) {
		Tool.fitsViewHeight(30, view);			
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);
		//Setting_IButton
		Tool.fitsViewWidth(24, view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton).getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton));		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFI_RLayout_RLayout_Setting_IButton), 2);
		//Queue_Button
		Tool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button));
		view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button).getLayoutParams().height = Tool.getWidth(27);		
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button));			
		new ThreadReadStateListInAssets(context, "phone/speaker/bottom_button_f.png","phone/speaker/bottom_button_single.png", view.findViewById(R.id.pFI_RLayout_RLayout_Queue_Button), 4);
	}
	
	private void pFI_RLayout_Bottom2_RLayout(View view) {
		Tool.fitsViewHeight(30, view);			
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting_bar.png", view, 3);
		
		//ButtonsBG_ImageView
		Tool.fitsViewWidth(132, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		Tool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));		
		Tool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView));
		new ThreadReadBitMapInAssets(context, "pad/Settingsbar/clear&save_00.png", view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView), 1);
		view.findViewById(R.id.pFI_RLayout_RLayout_ButtonsBG_ImageView).setTag(0);
		//Clear Button
		Tool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		Tool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		Tool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		Tool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Clear_Button));
		//Save Button
		Tool.fitsViewLeftMargin(165, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		Tool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		Tool.fitsViewWidth(66, view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Save_Button));
		//Done Button
		Tool.fitsViewLeftMargin(99, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		Tool.fitsViewHeight(27, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(132, view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(10,  view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "phone/queue/done_button_f.PNG", "phone/queue/done_button_n.PNG", view.findViewById(R.id.pFI_RLayout_RLayout_Done_Button), 4);
		//Setting2_IButton
		Tool.fitsViewWidth(24, view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));
		view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton).getLayoutParams().height = Tool.getWidth(30);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton));		
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/setting.png", view.findViewById(R.id.pFI_RLayout_RLayout_Setting2_IButton), 2);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FI_RLayout_UP_RLayout(View view){
		Tool.fitsViewHeight(342, view);
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/nowplaying_bg_f.png", view, 3);
		//DTITLE RLayout
		Tool.fitsViewHeight(38, view.findViewById(R.id.FI_RLayout_RLayout_UTITLE_RLayout));		
		//Playing TextView
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_PLAYING_TextView));
		//Point LLayout
		Tool.fitsViewHeight(10, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
		Tool.fitsViewBottomMargin(20, view.findViewById(R.id.FI_RLayout_RLayout_Point_LLayout));
	}
	private void PAD_FI_RLayout_DOWN_RLayout(View view){
		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_bg.png", view, 3);
		//DTITLE RLayout
		Tool.fitsViewHeight(35, view.findViewById(R.id.FI_RLayout_RLayout_DTITLE_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Queqe/queqe_navigation.png", view.findViewById(R.id.FI_RLayout_RLayout_DTITLE_RLayout), 3);
		//QUEUE TextView
		Tool.fitsViewLeftMargin(30, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_RLayout_RLayout_RLayout_QUEUE_TextView));
		//INFOR ListView
		FI_ListView fi_ListView = (FI_ListView)view.findViewById(R.id.FI_RLayout_RLayout_QUEUE_ListView);
		fi_ListView.setAdapter(new FI_Queqe_ListView_BaseAdapter_PAD(context));
	}
//***************************PAD*********************************
	
}

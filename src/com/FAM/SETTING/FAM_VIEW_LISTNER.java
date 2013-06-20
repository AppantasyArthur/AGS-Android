package com.FAM.SETTING;

import com.alpha.fragments.Fragment_Information;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class FAM_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_VIEW_LISTNER";
	private int device_size = 0;
	
	private FAM_Save_PopupWindow popupWindow;
	public FAM_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void ShowCloseMediaC2_IButton_LISTNER(ImageButton ShowCloseMediaC2_IButton,final RelativeLayout MediaC2_RLayout) {
		ShowCloseMediaC2_IButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(MediaC2_RLayout.getVisibility()==View.GONE){
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_f.png", v, 2);
					MediaC2_RLayout.setVisibility(View.VISIBLE);
				}else{
					new ThreadReadBitMapInAssets(context, "pad/PlayBack/playback_arrow_n.png", v, 2);
					MediaC2_RLayout.setVisibility(View.GONE);
				}
			}
		});
	}
	public void Sound_IButton_LISTNER(ImageButton Sound_IButton){
		Sound_IButton.setOnClickListener(new View.OnClickListener() {
			private FAM_PopupWindow fam_PopupWindow = new FAM_PopupWindow(context);
			@Override
			public void onClick(View view) {
				fam_PopupWindow.showAsDropDown(view);
			}
		});
	}	
	public void Clear_Button_LISTNER(Button Clear_Button,final Button Save_Button,final Button Done_Button,final Fragment_Information fragment_Infor){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Clear_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Save_Button_LISTNER(Button Save_Button){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Save_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(popupWindow==null){
						popupWindow = new FAM_Save_PopupWindow(context);
					}
					popupWindow.ShowPopupWindow(v.getRootView(), Gravity.CENTER, 0, 0);
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Done_Button_LISTNER(Button Done_Button,final Button Edit_Button,final Button Clear_Button,final Fragment_Information fragment_Infor){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//¿À¨d
					if(fragment_Infor==null||Clear_Button==null||Edit_Button==null){
						return;
					}
					//¡Ù¬√Done ≈„•‹Clear_Button°BEdit_Button
					if(!fragment_Infor.SET_FI_ListView_Edite(false)){
						v.setVisibility(View.GONE);
						Clear_Button.setVisibility(View.VISIBLE);
						Edit_Button.setVisibility(View.VISIBLE);
					}
					
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Cycle_IButton_LISTNER(final ImageButton Cycle_IButton,final ImageButton Cycle2_IButton){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Cycle_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)v.getTag();
					Log.i(TAG, "Tag = "+Tag);
					switch(Tag){
					case 0:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_f.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_all.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(1);
						Cycle2_IButton.setTag(1);
						break;
					case 1:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_one_f.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_one.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(2);
						Cycle2_IButton.setTag(2);
						break;
					case 2:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_off.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(0);
						Cycle2_IButton.setTag(0);
						break;
					}
				}
			});
			Cycle2_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)v.getTag();
					Log.i(TAG, "Tag = "+Tag);
					switch(Tag){
					case 0:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_f.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_all.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(1);
						Cycle2_IButton.setTag(1);
						break;
					case 1:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_one_f.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_one.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(2);
						Cycle2_IButton.setTag(2);
						break;
					case 2:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_n.png", Cycle_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/repeat_off.png", Cycle2_IButton, 2);
						Cycle_IButton.setTag(0);
						Cycle2_IButton.setTag(0);
						break;
					}
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Random_IButton_LISTNER(final ImageButton Random_IButton,final ImageButton Random2_IButton){
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Random_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)v.getTag();
					Log.i(TAG, "Tag = "+Tag);
					switch(Tag){
					case 0:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_f.png", Random_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_on.png", Random2_IButton, 2);
						Random_IButton.setTag(1);
						Random2_IButton.setTag(1);
						break;
					case 1:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_off.png", Random2_IButton, 2);
						Random_IButton.setTag(0);
						Random2_IButton.setTag(0);
						break;					
					}
				}
			});
			Random2_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int Tag = (Integer)v.getTag();
					Log.i(TAG, "Tag = "+Tag);
					switch(Tag){
					case 0:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_f.png", Random_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_on.png", Random2_IButton, 2);
						Random_IButton.setTag(1);
						Random2_IButton.setTag(1);
						break;
					case 1:
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_n.png", Random_IButton, 2);
						new ThreadReadBitMapInAssets(context, "pad/PlayBack/shuffle_off.png", Random2_IButton, 2);
						Random_IButton.setTag(0);
						Random2_IButton.setTag(0);
						break;					
					}
				}
			});
			//***************************PAD*********************************
		}
	}
	public void Setting_IButton_LISTNER(ImageButton Setting_IButton) {
		if(device_size==6){
			//***************************PHONE*********************************	
			//***************************PHONE*********************************	
		}else{
			//***************************PAD*********************************
			Setting_IButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intnet = new Intent();
					intnet.setClass(context, FragmentActivity_Setting.class);
					context.startActivity(intnet);
				}
			});
			//***************************PAD*********************************
		}
	}	
}

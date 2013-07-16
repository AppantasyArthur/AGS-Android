package com.FAS.SETTING;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

public class FAS_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAS_VIEW_SETTING";
	private int device_size = 0;
	
	public FAS_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			
		}else{
			switch(view.getId()){
			case R.id.FAS_RLayout_Right_ImageView:
				FAS_RLayout_Right_ImageView(view);
				break;
			case R.id.FAS_RLayout_Left_ImageView:
				FAS_RLayout_Left_ImageView(view);
				break;
			case R.id.FAS_RLayout_TitleBG_ImageView:
				FAS_RLayout_TitleBG_ImageView(view);
				break;
			case R.id.FAS_RLayout_Left_RLayout:
				PAD_FAS_RLayout_Left_RLayout(view);
				break;
			case R.id.FAS_RLayout_Right_RLayout:
				PAD_FAS_RLayout_Right_RLayout(view);
				break;
			}	
		}
	}


//***************************PHONE*********************************
//***************************PHONE*********************************
//***************************PAD*********************************
	

	private void FAS_RLayout_Right_ImageView(View view) {
		Tool.fitsViewWidth(761, view);
		new ThreadReadBitMapInAssets(context, "pad/Settings/bg_02.png", view, 1);
	}
	private void FAS_RLayout_Left_ImageView(View view) {
		Tool.fitsViewWidth(284, view);
		new ThreadReadBitMapInAssets(context, "pad/Settings/bg_01.png", view, 1);
	}
	private void FAS_RLayout_TitleBG_ImageView(View view) {
		Tool.fitsViewHeight(55, view);
		new ThreadReadBitMapInAssets(context, "pad/Settings/Settings_top_bg.png", view, 1);
	}
	private void PAD_FAS_RLayout_Left_RLayout(View view) {
		Tool.fitsViewWidth(284, view);		
	}
	private void PAD_FAS_RLayout_Right_RLayout(View view) {
		Tool.fitsViewWidth(761, view);		
	}
//***************************PAD*********************************
}

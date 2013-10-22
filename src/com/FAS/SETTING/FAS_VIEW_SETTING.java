package com.FAS.SETTING;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class FAS_VIEW_SETTING {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FAS_VIEW_SETTING";
	private int device_size = 0;
	
	public FAS_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
			
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
		TKBTool.fitsViewWidth(761, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/bg_02.png", view, 1);
	}
	private void FAS_RLayout_Left_ImageView(View view) {
		TKBTool.fitsViewWidth(284, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/bg_01.png", view, 1);
	}
	private void FAS_RLayout_TitleBG_ImageView(View view) {
		TKBTool.fitsViewHeight(55, view);
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/Settings_top_bg.png", view, 1);
	}
	private void PAD_FAS_RLayout_Left_RLayout(View view) {
		TKBTool.fitsViewWidth(284, view);		
	}
	private void PAD_FAS_RLayout_Right_RLayout(View view) {
		TKBTool.fitsViewWidth(761, view);		
	}
//***************************PAD*********************************
}

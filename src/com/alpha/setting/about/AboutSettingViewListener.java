package com.alpha.setting.about;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

public class AboutSettingViewListener {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSM_VIEW_LISTNER";
	private int device_size = 0;
	public AboutSettingViewListener(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}	
	
	public void Back_Button_Listner(Button Back_Button,final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
}

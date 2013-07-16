package com.FSM.SETTING;

import com.alpha.fragments.Fragment_SAbout;
import com.alpha.fragments.Fragment_SFirmrware;
import com.alpha.fragments.Fragment_SIdenrify;
import com.alpha.fragments.Fragment_SMenu;
import com.alpha.fragments.Fragment_SWireless;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

import android.R.raw;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

public class FSM_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSM_VIEW_LISTNER";
	private int device_size = 0;
	public FSM_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void Done_Button_Listner(Button Done_Button){
		if(device_size==6){
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {			
					((FragmentActivity_Setting)context).finish();
				}
			});			
		}else{
			Done_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {			
					((FragmentActivity_Setting)context).finish();
				}
			});
		}
		
	}
	public void About_Button_Listner(Button About_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			About_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(), "Fragment_SAbout", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);	
					((FragmentActivity_Setting)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_bottom_in,R.animator.alpha_out);
				}
			});
		}else{
			About_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(), "Fragment_SAbout", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
				}
			});
		}
	}
	public void Firmrware_Button_Listner(Button Firmrware_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Firmrware_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(), "Fragment_SFirmrware", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					((FragmentActivity_Setting)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_bottom_in,R.animator.alpha_out);
				}
			});
		}else{
			Firmrware_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(), "Fragment_SFirmrware", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
				}
			});
		}
	}
	public void Wireless_Button_Listner(Button Wireless_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Wireless_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					((FragmentActivity_Setting)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_bottom_in,R.animator.alpha_out);
				}
			});
		}else{
			Wireless_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
				}
			});
		}
	}
	public void Idenrify_Button_Listner(Button Idenrify_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Idenrify_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(), "Fragment_SIdenrify", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					((FragmentActivity_Setting)context).ShowViewContent_ViewFlipperDisplay(1,R.animator.translate_bottom_in,R.animator.alpha_out);
				}
			});
		}else{
			Idenrify_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(), "Fragment_SIdenrify", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, 0);
				}
			});
		}
	}
}

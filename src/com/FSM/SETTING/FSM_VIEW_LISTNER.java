package com.FSM.SETTING;

import com.alpha.fragments.Fragment_SAbout;
import com.alpha.fragments.Fragment_SFirmrware;
import com.alpha.fragments.Fragment_SIdenrify;
import com.alpha.fragments.Fragment_SWireless;
import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

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
		Done_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				((FragmentActivity_Setting)context).finish();
			}
		});
	}
	public void About_Button_Listner(Button About_Button,final FragmentManager fragmentManager){
		About_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAbout(), "Fragment_SAbout", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		});
	}
	public void Firmrware_Button_Listner(Button Firmrware_Button,final FragmentManager fragmentManager){
		Firmrware_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SFirmrware(), "Fragment_SFirmrware", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		});
	}
	public void Wireless_Button_Listner(Button Wireless_Button,final FragmentManager fragmentManager){
		Wireless_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		});
	}
	public void Idenrify_Button_Listner(Button Idenrify_Button,final FragmentManager fragmentManager){
		Idenrify_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SIdenrify(), "Fragment_SIdenrify", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
			}
		});
	}
}

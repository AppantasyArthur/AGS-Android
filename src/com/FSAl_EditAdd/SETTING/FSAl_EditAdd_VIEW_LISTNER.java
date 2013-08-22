package com.FSAl_EditAdd.SETTING;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.fragments.Fragment_SAlarm_Frequency;
import com.alpha.fragments.Fragment_SAlarm_Music;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class FSAl_EditAdd_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_EditAdd_VIEW_LISTNER";
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public FSAl_EditAdd_VIEW_LISTNER(Context context, int device_size,DeviceDisplay chooseDeviceDisplay) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
		this.chooseDeviceDisplay = chooseDeviceDisplay;
	}	
	
	public void Back_Button_Listner(Button Back_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	
	public void AlarmSwitch_Switch_Listner(Switch AlarmSwitch,final RelativeLayout AlarmInfo_RLayout){
		
		if(device_size==6){
			AlarmSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(TAG, "isChecked = "+isChecked);
					if(isChecked){
						AlarmInfo_RLayout.setVisibility(View.VISIBLE);
					}else{
						AlarmInfo_RLayout.setVisibility(View.GONE);
					}
				}
			});
		}else{
			AlarmSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(TAG, "isChecked = "+isChecked);
					if(isChecked){
						AlarmInfo_RLayout.setVisibility(View.VISIBLE);
					}else{
						AlarmInfo_RLayout.setVisibility(View.GONE);
					}
				}
			});
		}		
	}
	public void AlarmTime_RLayout_Listner(RelativeLayout AlarmTime_RLayout,FragmentManager fragmentManager){
		final FSAl_PopupWindow popupWindow = new FSAl_PopupWindow(context,fragmentManager);
		if(device_size==6){
			AlarmTime_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					popupWindow.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);
					mlog.info(TAG, "AlarmTime_RLayout onClick");					
				}
			});
		}else{
			AlarmTime_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					popupWindow.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);
					mlog.info(TAG, "AlarmTime_RLayout onClick");
					
				}
			});
		}
	}
	public void AlarmFrequency_RLayout_Listner(RelativeLayout AlarmFrequency_RLayout,final FragmentManager fragmentManager){
		final FSAl_PopupWindow popupWindow = new FSAl_PopupWindow(context,fragmentManager);
		if(device_size==6){
			AlarmFrequency_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Frequency")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_Frequency(), "Fragment_SAlarm_Frequency", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}
					mlog.info(TAG, "AlarmFrequency_RLayout onClick");					
				}
			});
		}else{
			AlarmFrequency_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Frequency")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_Frequency(), "Fragment_SAlarm_Frequency", R.id.FSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}
					mlog.info(TAG, "AlarmFrequency_RLayout onClick");
					
				}
			});
		}
	}
	public void AlarmMusic_RLayout_Listner(RelativeLayout AlarmMusic_RLayout,final FragmentManager fragmentManager){
		final FSAl_PopupWindow popupWindow = new FSAl_PopupWindow(context,fragmentManager);
		if(device_size==6){
			AlarmMusic_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Music")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_Music(), "Fragment_SAlarm_Music", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}
					mlog.info(TAG, "AlarmMusic_RLayout onClick");					
				}
			});
		}else{
			AlarmMusic_RLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Music")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_Music(), "Fragment_SAlarm_Music", R.id.FSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}
					mlog.info(TAG, "AlarmMusic_RLayout onClick");
					
				}
			});
		}
	}
	
}

package com.FSW.SETTING;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class FSW_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSW_VIEW_LISTNER";
	private int device_size = 0;
	public FSW_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void Back_Button_Listner(Button Back_Button, final FragmentManager fragmentManager){
		if(device_size==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_RLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	public void WIFISwitch_Switch_Listner(Switch WIFISwitch,final RelativeLayout WIFIInfo_RLayout){
		
		if(device_size==6){
			WIFISwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(TAG, "isChecked = "+isChecked);
					if(isChecked){
						WIFIInfo_RLayout.setVisibility(View.VISIBLE);
					}else{
						WIFIInfo_RLayout.setVisibility(View.GONE);
					}
				}
			});
		}else{
			WIFISwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(TAG, "isChecked = "+isChecked);
					if(isChecked){
						WIFIInfo_RLayout.setVisibility(View.VISIBLE);
					}else{
						WIFIInfo_RLayout.setVisibility(View.GONE);
					}
				}
			});
		}		
	}
	public void WIFIAP_ListView_LISTNER(ListView WIFIAP_ListView,final DeviceDisplay deviceDisplay){
		final FSW_PopupWindow popupWindow = new FSW_PopupWindow(context);
		if(device_size==6){
			WIFIAP_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					if((arg0.getCount()-1)==arg2){
						//Other 
						popupWindow.SetDeviceDisplay(deviceDisplay);
						popupWindow.showAtLocation(arg1.getRootView(), Gravity.CENTER, 0, 0);
					}else{
						
					}
				}
			});
		}else{
			WIFIAP_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					if((arg0.getCount()-1)==arg2){
						//Other 
						popupWindow.SetDeviceDisplay(deviceDisplay);
						popupWindow.showAtLocation(arg1.getRootView(), Gravity.CENTER, 0, 0);
					}else{
						
					}
				}
			});
		}
	}
}

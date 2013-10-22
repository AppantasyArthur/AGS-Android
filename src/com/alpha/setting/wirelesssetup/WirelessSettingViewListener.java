package com.alpha.setting.wirelesssetup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSW_VIEW_LISTNER
@SuppressLint("ShowToast")
public class WirelessSettingViewListener {
	
	private static Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "WirelessSettingViewListener";
	private int sizeDeviceScreen = 0;
	
	public WirelessSettingViewListener(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		this.sizeDeviceScreen = device_size;
	}
	
	public void setBackButtonListener(Button backButton, final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){
			backButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			backButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	
	public void setWifiSwitchListener(Switch switchWifi,final RelativeLayout layoutWifiInfo){
		
		if(DeviceProperty.isPhone()){
			switchWifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(tag, "wifi switch isChecked = "+isChecked);
					if(isChecked){
						layoutWifiInfo.setVisibility(View.VISIBLE);
					}else{
						layoutWifiInfo.setVisibility(View.INVISIBLE);
					}
				}
			});
		}else{
			switchWifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(tag, "wifi switch isChecked = "+isChecked);
					if(isChecked){
						layoutWifiInfo.setVisibility(View.VISIBLE);
					}else{
						layoutWifiInfo.setVisibility(View.INVISIBLE);
					}
				}
			});
		}		
	}
	
	private static WirelessSettingSSIDSetupPopupWindow popupWindow;
	public void setWifiListViewItemListener(ListView viewWifis,final DeviceDisplay deviceDisplay){
		
		popupWindow = new WirelessSettingSSIDSetupPopupWindow(context, handlerWirelessSetupPopupWindow);
		if(DeviceProperty.isPhone()){
			viewWifis.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					if((parent.getCount()-1) == position){
						//Other 
						popupWindow.setDeviceDisplay(deviceDisplay);
						popupWindow.showAtLocation(view.getRootView(), Gravity.CENTER, 0, 0);
					}else{
						
					}
				}
			});
		}else{
			viewWifis.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					if((parent.getCount()-1) == position){
						//Other 
						popupWindow.setDeviceDisplay(deviceDisplay);
						//popupWindow.showAtLocation(view.getRootView(), Gravity.CENTER, 0, 0);
						popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
					}else{
						
					}
				}
			});
		}
	}
	
	//public static int CLOSE_POPUPWINDOW = 1;
	private static Handler handlerWirelessSetupPopupWindow = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == AGSHandlerMessages.SHOW_MESSAGE){
	
				if(msg.obj != null){
					Toast t = Toast.makeText(context, (String)msg.obj, Toast.LENGTH_SHORT);
					t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_VERTICAL, 0, 0);
					t.show();
				}
				
				if(popupWindow != null)
					popupWindow.dismiss();
				
			}
			
		}
		
	};
}

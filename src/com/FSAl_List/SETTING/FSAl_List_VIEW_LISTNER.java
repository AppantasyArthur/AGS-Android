package com.FSAl_List.SETTING;


import com.alpha.UPNP.DeviceDisplay;
import com.alpha.fragments.Fragment_SAlarm;
import com.alpha.fragments.Fragment_SAlarm_EditAdd;
import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FSAl_List_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_List_VIEW_LISTNER";
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	public FSAl_List_VIEW_LISTNER(Context context, int device_size,DeviceDisplay chooseDeviceDisplay) {
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
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
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
	public void Edit_Button_Listner(Button Edit_Button,final BaseAdapter baseAdapter) {
		if(device_size==6){
			Edit_Button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {	
					FSAl_Alarm_ListView_BaseAdapter_Phone baseAdapter_phone = (FSAl_Alarm_ListView_BaseAdapter_Phone)baseAdapter;
					if(baseAdapter_phone.GetEdit()){
						baseAdapter_phone.SetEdit(false);
					}else{
						baseAdapter_phone.SetEdit(true);
					}
				}
			});
		}else{
			Edit_Button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {	
					FSAl_Alarm_ListView_BaseAdapter_PAD baseAdapter_PAD = (FSAl_Alarm_ListView_BaseAdapter_PAD)baseAdapter;
					if(baseAdapter_PAD.GetEdit()){
						baseAdapter_PAD.SetEdit(false);
					}else{
						baseAdapter_PAD.SetEdit(true);
					}
				}
			});
		}
		
	}

	public void Add_Button_Listner(Button Add_Button,final FragmentManager fragmentManager) {
		if(device_size==6){
			Add_Button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {					
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_EditAdd(chooseDeviceDisplay), "Fragment_SAlarm_EditAdd", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}else{
			Add_Button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {					
					Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_EditAdd(chooseDeviceDisplay), "Fragment_SAlarm_EditAdd", R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}
		
	}

	public void Alarm_ListView_LISTNER(ListView Alarm_ListView, final FragmentManager fragmentManager, final BaseAdapter alarm_ListView_BaseAdapter) {
		if(device_size==6){
			Alarm_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					mlog.info(TAG, "onClick position = "+arg2);
					FSAl_Alarm_ListView_BaseAdapter_Phone baseAdapter_Phone = (FSAl_Alarm_ListView_BaseAdapter_Phone)alarm_ListView_BaseAdapter;
					if(baseAdapter_Phone.GetEdit()){
						return;
					}
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_EditAdd(chooseDeviceDisplay), "Fragment_SAlarm_EditAdd", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}
				}
			});
		}else{
			Alarm_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					mlog.info(TAG, "onClick position = "+arg2);
					FSAl_Alarm_ListView_BaseAdapter_PAD baseAdapter_PAD = (FSAl_Alarm_ListView_BaseAdapter_PAD)alarm_ListView_BaseAdapter;
					if(baseAdapter_PAD.GetEdit()){
						return;
					}
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd")==null){
						Tool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SAlarm_EditAdd(chooseDeviceDisplay), "Fragment_SAlarm_EditAdd", R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					}					
				}
			});
		}
		
	}
}

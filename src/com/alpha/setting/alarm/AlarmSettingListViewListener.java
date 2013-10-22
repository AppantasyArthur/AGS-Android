package com.alpha.setting.alarm;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.Fragment_SRenderers;
import com.alpha.fragments.settings.AlarmSettingAddEditFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSAl_List_VIEW_LISTNER
public class AlarmSettingListViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "AlarmSettingListViewListener";
	private int device_size = 0;
	private DeviceDisplay chooseDeviceDisplay;
	
	public AlarmSettingListViewListener(Context context, int device_size,DeviceDisplay chooseDeviceDisplay) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
		this.chooseDeviceDisplay = chooseDeviceDisplay;
	}	
	
	public void setBackButtonListener(Button buttonBack,final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_top_in, R.animator.translate_bottom_out);			
				}
			});	
		}else{
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	
	public void setEditButtonListener(Button buttonEdit,final BaseAdapter baseAdapter) {
		
		if(device_size == 6){ // Phone
			buttonEdit.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {	
					AlarmSettingPhoneViewAdapter adapterPhoneView = (AlarmSettingPhoneViewAdapter)baseAdapter;
					if(adapterPhoneView.isEdit()){
						adapterPhoneView.setEdit(false);
					}else{
						adapterPhoneView.setEdit(true);
					}
				}
			});
		}else{ // Pad
			buttonEdit.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {	
					AlarmSettingPadViewAdapter adapterPadView = (AlarmSettingPadViewAdapter)baseAdapter;
					if(adapterPadView.isEdit()){
						adapterPadView.setEdit(false);
					}else{
						adapterPadView.setEdit(true);
					}
				}
			});
		}
		
	}

	public void setAddButtonListener(Button buttonAdd,final FragmentManager fragmentManager) {
		
		if(device_size == 6){
			buttonAdd.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {					
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingAddEditFragement(chooseDeviceDisplay, null), "Fragment_SAlarm_EditAdd", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}else{
			buttonAdd.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {					
					TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingAddEditFragement(chooseDeviceDisplay, null), "Fragment_SAlarm_EditAdd", R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}
		
	}

	public void setAlarmSettingListViewListener(ListView viewAlarmList, final FragmentManager fragmentManager, final BaseAdapter adapterAlarmSettingListView) {
		
		if(device_size == 6){
			viewAlarmList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					mlog.info(TAG, "AlarmSettingListView click position = " + position);
					AlarmSettingPhoneViewAdapter adapterPhoneView = (AlarmSettingPhoneViewAdapter)adapterAlarmSettingListView;
					if(adapterPhoneView.isEdit()){
						return;
					}
					
					//if(fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd")==null){
						TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingAddEditFragement(chooseDeviceDisplay, adapterPhoneView.getItem(position)), "Fragment_SAlarm_EditAdd", R.id.pFSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
					//}
				}
			});
		}else{
			viewAlarmList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					mlog.info(TAG, "AlarmSettingListView click position = " + position);
					AlarmSettingPadViewAdapter adapterPadView = (AlarmSettingPadViewAdapter)adapterAlarmSettingListView;
					if(adapterPadView.isEdit()){ // in remove mode, Arthur
						return;
					}
					//if(fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd") == null){
						TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingAddEditFragement(chooseDeviceDisplay, adapterPadView.getItem(position)), "Fragment_SAlarm_EditAdd", R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
						//TKBTool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SRenderers(), "Fragment_SRenderers", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
						
						//TKBTool.FragmentActivity_MainAddFragment(fragmentManager.beginTransaction(), new AlarmSettingAddEditFragement(chooseDeviceDisplay, adapterPad.getItem(position)), "Fragment_SAlarm_EditAdd", R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//						TKBTool.FragmentActivity_MainReplaceAddStackFragment(
//								fragmentManager.beginTransaction() 
//								, new AlarmSettingAddEditFragement(chooseDeviceDisplay, adapterPad.getItem(position))
//								, "Fragment_SAlarm_EditAdd"
//								, R.id.FSAl_RLayout_ViewFlipper_AlarmPage2_RLayout
//								//, R.id.FSAl_List_RLayout
//								, R.animator.alpha_in
//								, R.animator.alpha_out
//								, R.animator.alpha_in
//								, R.animator.alpha_out);
					//}					
				}
			});
		}
		
	}
}

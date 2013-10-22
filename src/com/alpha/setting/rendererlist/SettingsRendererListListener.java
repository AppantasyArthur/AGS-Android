package com.alpha.setting.rendererlist;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.Fragment_SWireless;
import com.alpha.fragments.SettingMenuFragment;
import com.alpha.fragments.settings.AboutSettingFragment;
import com.alpha.fragments.settings.AlarmSettingFragment;
import com.alpha.fragments.settings.FirmwareUpdateSettingFragment;
import com.alpha.fragments.settings.IdentifySpeakerSettingFragment;
import com.alpha.fragments.settings.SleepTimerSettingFragement;
import com.alpha.fragments.settings.ags.AGSAlarmSettingListViewFragement;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnpui.Fragment_SETTING;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

public class SettingsRendererListListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "SettingsRendererListListener";
	private int device_size = 0;
	
	public SettingsRendererListListener(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	
	public void setBackButtonListener(Button Back_Button){
		Back_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				Fragment_SETTING fragment_SETTING = (Fragment_SETTING)((MainFragmentActivity)context).getSupportFragmentManager().findFragmentByTag("Fragment_SETTING");
				if(fragment_SETTING!=null){
					fragment_SETTING.ShowViewContent_ViewFlipperDisplay(0, R.animator.translate_top_in, R.animator.translate_bottom_out);
				}	
			}
		});	
	}
	
	public void setRenderersListViewListener(ListView listviewRenderer,final FragmentManager fragmentManager){
		
		if(DeviceProperty.isPhone()){
			listviewRenderer.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {					
					DeviceDisplay deviceDisplay = (DeviceDisplay)arg0.getItemAtPosition(arg2);					
					SettingMenuFragment fragment_SMenu = (SettingMenuFragment)fragmentManager.findFragmentByTag("Fragment_SMenu");
					int chooseMenu = -1;
					if(fragment_SMenu!=null&&(chooseMenu=fragment_SMenu.getChooseMenu())!=-1){
						switch(chooseMenu){
						case 0:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AboutSettingFragment(deviceDisplay), "Fragment_SAbout", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 1:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new FirmwareUpdateSettingFragment(deviceDisplay), "Fragment_SFirmrware", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 2:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(deviceDisplay), "Fragment_SWireless", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 3:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new IdentifySpeakerSettingFragment(deviceDisplay), "Fragment_SIdenrify", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						case 4:
							//TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingFragment(deviceDisplay), "Fragment_SAlarm", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							TKBTool.animationReplaceFragment(
									fragmentManager.beginTransaction()
									, new AGSAlarmSettingListViewFragement(deviceDisplay)
									, "Fragment_SAlarm_List"
									, R.id.pFAS_RLayout_ViewFlipper_Right_FLayout
									, R.anim.in_from_right, R.anim.out_to_left);
							break;
						case 5:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new SleepTimerSettingFragement(deviceDisplay), "Fragment_SSleepTimer", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.translate_bottom_in, R.animator.translate_top_out);
							break;
						}
					}
				}
			});
		}else{
			listviewRenderer.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {					
					DeviceDisplay deviceDisplay = (DeviceDisplay)parent.getItemAtPosition(position);					
					SettingMenuFragment fragment_SMenu = (SettingMenuFragment)fragmentManager.findFragmentByTag("Fragment_SMenu");
					int chooseMenu = -1;
					if(fragment_SMenu!=null&&(chooseMenu=fragment_SMenu.getChooseMenu())!=-1){
						switch(chooseMenu){
						case 0:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AboutSettingFragment(deviceDisplay), "Fragment_SAbout", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 1:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new FirmwareUpdateSettingFragment(deviceDisplay), "Fragment_SFirmrware", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 2:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new Fragment_SWireless(deviceDisplay), "Fragment_SWireless", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 3:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new IdentifySpeakerSettingFragment(deviceDisplay), "Fragment_SIdenrify", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 4:
							//TKBTool.FragmentActivity_MainReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingFragment(deviceDisplay), "Fragment_SAlarm", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
//							TKBTool.FragmentActivity_MainReplaceAddStackFragment(
//									fragmentManager.beginTransaction()
//									, new AlarmSettingListViewFragement(deviceDisplay)
//									, "Fragment_SAlarm_List"
//									, R.id.FSAl_RLayout_ViewFlipper_AlarmPage1_RLayout
//									, R.animator.alpha_in, R.animator.alpha_out
//									, R.animator.alpha_in, R.animator.alpha_out);
							TKBTool.animationReplaceFragment(
									fragmentManager.beginTransaction()
									, new AGSAlarmSettingListViewFragement(deviceDisplay)
									, "Fragment_SAlarm_List"
									, R.id.FAS_RLayout_Right_RLayout
									, R.anim.in_from_right, R.anim.out_to_left);
//									, R.animator.alpha_in, R.animator.alpha_out);
							break;
						case 5:
							TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new SleepTimerSettingFragement(deviceDisplay), "Fragment_SSleepTimer", R.id.FAS_RLayout_Right_RLayout, R.animator.alpha_in, R.animator.alpha_out);
							break;
						}
					}
				}
			});
		}		
	}
}

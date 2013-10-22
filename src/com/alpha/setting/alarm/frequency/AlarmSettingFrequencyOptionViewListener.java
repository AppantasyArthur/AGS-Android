package com.alpha.setting.alarm.frequency;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.alpha.fragments.settings.AlarmSettingAddEditFragement;
import com.alpha.fragments.settings.ags.AGSAlarmSettingAddEditFragement;
import com.alpha.setting.alarm.AlarmItemContent;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSAl_Frequency_VIEW_LISTNER
public class AlarmSettingFrequencyOptionViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AlarmSettingFrequencyOptionViewListener";
	//private int device_size = 0;
	public AlarmSettingFrequencyOptionViewListener(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		//this.device_size = device_size;
	}	
	
	public void setBackButtonListener(Button Back_Button,final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}		
	}
	public void setFrequencyListViewListener(final View viewMainFragment,final FragmentManager fragmentManager){
		
		if(DeviceProperty.isPhone()){
			
			ListView viewList = (ListView)viewMainFragment.findViewById(R.id.pFSAl_Frequency_RLayout_RLayout_Frequency_ListView);
			viewList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					
//					AlarmSettingAddEditFragement fragment_SAlarm_EditAdd = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
//					if(fragment_SAlarm_EditAdd!=null){
//						fragment_SAlarm_EditAdd.SetFrequency(position);
//					}
					
					
					//TextView viewFrequencyText = (TextView)viewMainFragment.findViewById(R.id.AlarmSettingProfileFrequencyTextPhoneView);
					//viewFrequencyText.setText(getFrequencyText(position));
					
					final int p = position; 
					Thread t = new Thread(){

						@Override
						public void run() {
							
							Looper.prepare();
							Handler handler = AGSAlarmSettingAddEditFragement.getMessageHandler();
							Message msg = handler.obtainMessage(AGSHandlerMessages.SET_FREQUENCY);
							msg.obj = AlarmItemContent.getFrequencyOptionText(p);
							handler.sendMessage(msg);
							Looper.loop();
							
						}
						
					};
					t.start();
					
					
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
					
				}
			});
			
		}else{
			
			ListView viewList = (ListView)viewMainFragment.findViewById(R.id.FSAl_Frequency_RLayout_RLayout_Frequency_ListView);
			viewList.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//					AlarmSettingAddEditFragement fragment_SAlarm_EditAdd = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
//					if(fragment_SAlarm_EditAdd!=null){
//						fragment_SAlarm_EditAdd.SetFrequency(position);
//					}
//					TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
				
					final int p = position; 
					Thread t = new Thread(){

						@Override
						public void run() {
							
							Looper.prepare();
							Handler handler = AGSAlarmSettingAddEditFragement.getMessageHandler();
							Message msg = handler.obtainMessage(AGSHandlerMessages.SET_FREQUENCY);
							msg.obj = AlarmItemContent.getFrequencyOptionText(p);
							handler.sendMessage(msg);
							Looper.loop();
							
						}
						
					};
					t.start();
				
					TKBTool.doPopBackFragment(fragmentManager);
					
				}
			});
		}
	}

}

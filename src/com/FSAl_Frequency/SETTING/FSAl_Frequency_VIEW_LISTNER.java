package com.FSAl_Frequency.SETTING;

import com.alpha.fragments.Fragment_SAlarm_EditAdd;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class FSAl_Frequency_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSAl_Frequency_VIEW_LISTNER";
	private int device_size = 0;
	public FSAl_Frequency_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}	
	
	public void Back_Button_Listner(Button Back_Button,final FragmentManager fragmentManager){
		if(device_size==6){
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}else{
			Back_Button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
				}
			});	
		}		
	}
	public void Frequency_ListView_LISTNER(ListView Frequency_ListView,final FragmentManager fragmentManager){
		if(device_size==6){			
			Frequency_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
					if(fragment_SAlarm_EditAdd!=null){
						fragment_SAlarm_EditAdd.SetFrequency(arg2);
					}
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}else{
			Frequency_ListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					Fragment_SAlarm_EditAdd fragment_SAlarm_EditAdd = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
					if(fragment_SAlarm_EditAdd!=null){
						fragment_SAlarm_EditAdd.SetFrequency(arg2);
					}
					Tool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_Frequency", R.animator.alpha_in, R.animator.alpha_out);
				}
			});
		}
	}

}

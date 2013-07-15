package com.FSW.SETTING;

import com.alpha.upnpui.FragmentActivity_Setting;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

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
	public void Back_Button_Listner(Button Back_Button){
		Back_Button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				((FragmentActivity_Setting)context).ShowViewContent_ViewFlipperDisplay(0, R.animator.alpha_in, R.animator.translate_bottom_out);
			}
		});	
	}
}

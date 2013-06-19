package com.FSI.SETTING;

import com.tkb.tool.MLog;
import android.content.Context;

public class FSI_VIEW_LISTNER {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSI_VIEW_LISTNER";
	private int device_size = 0;
	public FSI_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}	
}

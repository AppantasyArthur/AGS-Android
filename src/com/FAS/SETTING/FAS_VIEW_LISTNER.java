package com.FAS.SETTING;

import com.tkb.tool.TKBLog;
import android.content.Context;

public class FAS_VIEW_LISTNER {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FAS_VIEW_LISTNER";
	private int device_size = 0;
	public FAS_VIEW_LISTNER(Context context, int device_size) {
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
}

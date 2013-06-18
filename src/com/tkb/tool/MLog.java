package com.tkb.tool;

public class MLog {
	public boolean LogSwitch = false;
	private boolean allLogShow = true;
	
	public MLog(){
		
	}
	public MLog(boolean LogSwitch){
		this.LogSwitch = LogSwitch;
	}
	public void verbose(String tag, String text)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.w(tag,text);
        }
    }
	public void debug(Object obj, String text)
    {
        if (LogSwitch&&allLogShow) {
            if (obj != null)
            {
                debug(obj.getClass().getSimpleName(),text);
            }
        }
    }
	public void debug(String tag, String text)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.d(tag, text);
        }
    }
	public void info(String tag, String text)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.i(tag, text);
        }

    }
	public void warn(String tag, String text)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.w(tag, text);
        }
    }
	public void warn(String tag, String text, Throwable throwable)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.w(tag, text, throwable);
        }
    }
	public void error(String tag, String text)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.e(tag, text);
        }
    }
    public void error(String tag, String text, Throwable throwable)
    {
        if (LogSwitch&&allLogShow) {
            android.util.Log.e(tag, text, throwable);
        }
    }
	
}

package com.tkb.tool;

public class TKBLog {
	public boolean switchLog = true;
	private boolean allLogShow = true;
	
	public TKBLog(){
		
	}
	public TKBLog(boolean LogSwitch){
		this.switchLog = LogSwitch;
	}
	public void verbose(String tag, String text)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.w(tag,text);
        }
    }
	public void debug(Object obj, String text)
    {
        if (switchLog&&allLogShow) {
            if (obj != null)
            {
                debug(obj.getClass().getSimpleName(),text);
            }
        }
    }
	public void debug(String tag, String text)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.d(tag, text);
        }
    }
	public void info(String tag, String text)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.i(tag, text);
        }

    }
	public void warn(String tag, String text)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.w(tag, text);
        }
    }
	public void warn(String tag, String text, Throwable throwable)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.w(tag, text, throwable);
        }
    }
	public void error(String tag, String text)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.e(tag, text);
        }
    }
    public void error(String tag, String text, Throwable throwable)
    {
        if (switchLog&&allLogShow) {
            android.util.Log.e(tag, text, throwable);
        }
    }
	
}

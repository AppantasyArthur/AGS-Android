package com.FM.SETTING;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class FM_EditText extends EditText {
	

	
	private Context context;
	public FM_EditText(Context context) {
		super(context);		
		CreateProcess();
	}

	public FM_EditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		CreateProcess();
	}
	
	public FM_EditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);	
		CreateProcess();
		
	}	
	private void CreateProcess(){
		this.context = this.getContext();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode ==KeyEvent.KEYCODE_SEARCH){
			  
		}
		return super.onKeyDown(keyCode, event);
	}
}

package com.FAM.SETTING;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class FAM_ViewFlipper extends ViewFlipper {
	
	public FAM_ViewFlipper(Context context) {
		super(context);		
	}
	public FAM_ViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);		
	}
	@Override
	protected void onDetachedFromWindow() {
		try{			
			super.onDetachedFromWindow();		
		}catch (IllegalArgumentException e) {
			this.stopFlipping();			            
        }
		
	}
	public void onDetachedFromWindow_P(){
		this.onDetachedFromWindow();
	}
	

}

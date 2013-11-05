package com.alpha.mediarender;

import android.widget.TextView;

public class RunStateHandler{
	public int mode = -1;
	public String MetaData_Title ="";
	public TextView textView;
	public RunStateHandler(String playMode,String MetaData,TextView textView){
		
		//¿À¨dplayMode
		if(playMode==null||playMode.equals("")){
			mode = -1;						
		}else if(playMode.equals("PLAYING")){
			mode = 1;						
		}else{
			mode = 0;		
		}
		//¿À¨dMetaData_Title
		if(MetaData==null||MetaData.equals("")){
			MetaData_Title = "°@NA";
		}else{
			MetaData_Title = "°@"+MetaData;
		}

		this.textView = textView;
	}
}

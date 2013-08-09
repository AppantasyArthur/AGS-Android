package com.FSR.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSR_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSR_VIEW_SETTING";
	private int device_size = 0;
	
	public FSR_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSR_RLayout:
				pFSR_RLayout(view);
				break;
			case R.id.pFSR_RLayout_TITLE_RLayout:
				pFSR_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSR_RLayout_BODY_RLayout:
				pFSR_RLayout_BODY_RLayout(view);
				break;
			}
		}else{
			switch(view.getId()){
			case R.id.FSR_RLayout_TITLE_RLayout:
				PAD_FSR_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSR_RLayout_BODY_RLayout:
				PAD_FSR_RLayout_BODY_RLayout(view);
				break;
			}	
		}
	}

//***************************PHONE*********************************
	private void pFSR_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSR_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewRightMargin(7, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		new ThreadReadStateListInAssets(context, "phone/setting/done_f.png", "phone/setting/done_n.png", view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSR_RLayout_RLayout_Title_TextView));
	}
	private void pFSR_RLayout_BODY_RLayout(View view){
		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSR_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);
		
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSR_RLayout_RLayout_Title_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSR_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSR_RLayout_BODY_RLayout(View view) {
		
	}
//***************************PAD*********************************
}

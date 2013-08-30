package com.FSM.SETTING;

import android.content.Context;
import android.view.View;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;

public class FSM_VIEW_SETTING {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FSM_VIEW_SETTING";
	private int device_size = 0;
	
	public FSM_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.LogSwitch = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(this.device_size==6){
			switch(view.getId()){
			case R.id.pFSM_RLayout:
				pFSM_RLayout(view);
				break;
			case R.id.pFSM_RLayout_TITLE_RLayout:
				pFSM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.pFSM_RLayout_Body_RLayout:
				pFSM_RLayout_Body_RLayout(view);
				break;
			}
		}else{
			switch(view.getId()){
			case R.id.FSM_RLayout_TITLE_RLayout:
				PAD_FSM_RLayout_TITLE_RLayout(view);
				break;
			case R.id.FSM_RLayout_Body_RLayout:
				PAD_FSM_RLayout_Body_RLayout(view);
				break;
			}	
		}
	}

	

//***************************PHONE*********************************
	private void pFSM_RLayout(View view) {
		new ThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSM_RLayout_TITLE_RLayout(View view) {		
		Tool.fitsViewHeight(37, view);
		new ThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		Tool.fitsViewWidth(59, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button).getLayoutParams().height = Tool.getWidth(26);
		Tool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		new ThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button), 3);
		//Title TextView
		Tool.fitsViewTextSize(18, view.findViewById(R.id.pFSM_RLayout_RLayout_Title_TextView));
	}
	private void pFSM_RLayout_Body_RLayout(View view) {
		Tool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_Menu_RLayout));		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSM_RLayout_TITLE_RLayout(View view) {
		Tool.fitsViewHeight(55, view);		
		//Done Button
		Tool.fitsViewHeight(32, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button).getLayoutParams().width = Tool.getHeight(53);
		Tool.fitsViewTopMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewLeftMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "pad/Settings/Settings_done_f.png", "pad/Settings/Settings_done_n.png", view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button), 4);
		//Title TextView
		Tool.fitsViewHeight(50, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
		view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView).getLayoutParams().width = Tool.getHeight(90);
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSM_RLayout_Body_RLayout(View view) {		
		Tool.fitsViewWidth(272, view.findViewById(R.id.FSM_RLayout_RLayout_Menu_RLayout));
	}
//***************************PAD*********************************
}

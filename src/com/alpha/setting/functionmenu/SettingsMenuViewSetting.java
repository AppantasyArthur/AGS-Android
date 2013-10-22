package com.alpha.setting.functionmenu;

import android.content.Context;
import android.view.View;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class SettingsMenuViewSetting {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSM_VIEW_SETTING";
	private int device_size = 0;
	
	public SettingsMenuViewSetting(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void setView(View view){
		if(DeviceProperty.isPhone()){
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
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSM_RLayout_TITLE_RLayout(View view) {		
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSM_RLayout_RLayout_Done_Button), 3);
		//Title TextView
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSM_RLayout_RLayout_Title_TextView));
	}
	private void pFSM_RLayout_Body_RLayout(View view) {
		TKBTool.fitsViewWidth(276, view.findViewById(R.id.pFSM_RLayout_RLayout_Menu_RLayout));		
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSM_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);		
		//Done Button
		TKBTool.fitsViewHeight(32, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button).getLayoutParams().width = TKBTool.getHeight(53);
		TKBTool.fitsViewTopMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewLeftMargin(9, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(6, view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/Settings_done_f.png", "pad/Settings/Settings_done_n.png", view.findViewById(R.id.FSM_RLayout_RLayout_Done_Button), 4);
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
		view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView).getLayoutParams().width = TKBTool.getHeight(90);
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSM_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSM_RLayout_Body_RLayout(View view) {		
		TKBTool.fitsViewWidth(272, view.findViewById(R.id.FSM_RLayout_RLayout_Menu_RLayout));
	}
//***************************PAD*********************************
}

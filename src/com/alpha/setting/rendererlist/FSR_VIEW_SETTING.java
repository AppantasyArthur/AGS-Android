package com.alpha.setting.rendererlist;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class FSR_VIEW_SETTING {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FSR_VIEW_SETTING";
	private int device_size = 0;
	
	public FSR_VIEW_SETTING(Context context,int device_size){
		this.context = context;
		this.mlog.switchLog = true;
		this.device_size = device_size;
	}
	public void VIEWSET(View view){
		if(DeviceProperty.isPhone()){
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
		new TKBThreadReadBitMapInAssets(context, "phone/speaker/bg.PNG", view, 3);
	}
	private void pFSR_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(37, view);
		new TKBThreadReadBitMapInAssets(context, "phone/setting/top_talie.PNG", view, 3);
		//Done Button
		TKBTool.fitsViewWidth(59, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button).getLayoutParams().height = TKBTool.getWidth(26);
		TKBTool.fitsViewLeftMargin(7, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		TKBTool.fitsViewTextSize(10, view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button));
		new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/back.png", view.findViewById(R.id.pFSR_RLayout_RLayout_Back_Button), 3);
		//Title TextView
		TKBTool.fitsViewHeight(20, view.findViewById(R.id.pFSR_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(18, view.findViewById(R.id.pFSR_RLayout_RLayout_Title_TextView));
		//Title2 TextView
		TKBTool.fitsViewHeight(15, view.findViewById(R.id.pFSR_RLayout_RLayout_Title2_TextView));
		TKBTool.fitsViewTextSize(12, view.findViewById(R.id.pFSR_RLayout_RLayout_Title2_TextView));
	}
	private void pFSR_RLayout_BODY_RLayout(View view){		
		TKBTool.fitsViewWidth(276, view.findViewById(R.id.pFSR_RLayout_RLayout_Renders_RLayout));
		TKBTool.fitsViewTopMargin(20, view.findViewById(R.id.pFSR_RLayout_RLayout_Renders_RLayout));
		
		FSR_Renders_ListView_BaseAdapter_Phone baseAdapter = new FSR_Renders_ListView_BaseAdapter_Phone(this.context);
		ListView renderersListView = (ListView)view.findViewById(R.id.pFSR_RLayout_RLayout_Renders_ListView);
		renderersListView.setAdapter(baseAdapter);
	}
//***************************PHONE*********************************
//***************************PAD*********************************
	private void PAD_FSR_RLayout_TITLE_RLayout(View view) {
		TKBTool.fitsViewHeight(55, view);
		
		//Title TextView
		TKBTool.fitsViewHeight(50, view.findViewById(R.id.FSR_RLayout_RLayout_Title_TextView));
		TKBTool.fitsViewTextSize(8, view.findViewById(R.id.FSR_RLayout_RLayout_Title_TextView));
	}
	private void PAD_FSR_RLayout_BODY_RLayout(View view) {
		
		TKBTool.fitsViewWidth(276, view.findViewById(R.id.FSR_RLayout_RLayout_Renders_RLayout));
		TKBTool.fitsViewTopMargin(20, view.findViewById(R.id.FSR_RLayout_RLayout_Renders_RLayout));
		TKBTool.fitsViewLeftMargin(20, view.findViewById(R.id.FSR_RLayout_RLayout_Renders_RLayout));
		
		FSR_Renders_ListView_BaseAdapter_PAD baseAdapter = new FSR_Renders_ListView_BaseAdapter_PAD(this.context);
		ListView renderersListView = (ListView)view.findViewById(R.id.FSR_RLayout_RLayout_Renders_ListView);
		renderersListView.setAdapter(baseAdapter);
	}
//***************************PAD*********************************
}

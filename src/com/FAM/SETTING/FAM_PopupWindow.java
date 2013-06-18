package com.FAM.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class FAM_PopupWindow extends PopupWindow {
	private View contentView;
	
	private List<ViewHandler> ControlList = new ArrayList<ViewHandler>();
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_PopupWindow";
	public FAM_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		
		CreateContentView();
		//設定內容
		this.setContentView(contentView);
		this.setWidth(Tool.getHeight(200));
		this.setHeight(Tool.getHeight(250));
		//設定back鍵  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//設定Outside dismiss
		this.setOutsideTouchable(true);
		
		
	}
	private void CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(220, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout).getLayoutParams().width = Tool.getHeight(180);
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FAM_PopupWindow_RLayout_OPTION_LLayout);
		CreateOptionButtons(OPTION_LLayout,6);		
		mlog.info(TAG, "CreateContentView");
	}
	private void CreateOptionButtons(LinearLayout OPTION_LLayout,int ControlCount){
		if(!ControlList.isEmpty()){
			ControlList.clear();
		}
		for(int i =0;i<ControlCount;i++){
			//新建ViewHandler			
			ViewHandler viewHandler = new ViewHandler();
			//findView
			View sound_View = LayoutInflater.from(context).inflate(R.layout.fam_popupwindow_soundcell, null);
			viewHandler.SoundCell_RLayout = (RelativeLayout)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout);
			viewHandler.sound_IView = (ImageView)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout_SOUND_IView);
			viewHandler.sound_SeekBar = (SeekBar)sound_View.findViewById(R.id.FAM_PopupWindow_SoundCell_RLayout_SOUND_SeekBar);
			VIEW_SETTING(viewHandler);
			ControlList.add(viewHandler);
			OPTION_LLayout.addView(sound_View);
		}
	}
	
	private void VIEW_SETTING(ViewHandler viewHandler) {
		//SoundCell RLayout
		viewHandler.SoundCell_RLayout.setLayoutParams(new LayoutParams(Tool.getHeight(180),Tool.getHeight(50)));
		//Sound ImageView
		Tool.fitsViewHeight(37,viewHandler.sound_IView);
		viewHandler.sound_IView.getLayoutParams().width = Tool.getHeight(42);
		Tool.fitsViewLeftMargin(5, viewHandler.sound_IView);
		//Sound SeekBar
		Tool.fitsViewHeight(30, viewHandler.sound_SeekBar);
		viewHandler.sound_SeekBar.getLayoutParams().width = Tool.getHeight(110);
		Tool.fitsViewLeftMargin(10, viewHandler.sound_SeekBar);
	}

	private class ViewHandler{		
		private RelativeLayout SoundCell_RLayout;
		private ImageView sound_IView;
		private SeekBar sound_SeekBar;
	}
}

package com.FS.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FS_PopupWindow extends PopupWindow {
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FS_PopupWindow";
	private List<OptionButton> OptionButtonsList;
	public FS_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		
		CreateContentView();
		ContentViewListner();
		//設定內容
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		//設定back鍵  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//設定Outside dismiss
		this.setOutsideTouchable(true);
		
	}
	private void CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fs_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(380, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(352, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout));
		//TITLE RLayout
		Tool.fitsViewHeight(45, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(53, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Done Button
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(53, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		//Choose TextView
		Tool.fitsViewHeight(26, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(5, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(26, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(5, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//ChooseScroll ScrollView
		Tool.fitsViewHeight(230, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		Tool.fitsViewWidth(312, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		Tool.fitsViewTopMargin(110, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		//SelectAll Button
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewBottomMargin(5, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		mlog.info(TAG, "CreateContentView");
	}
	public void ShowPopupWindow(View parent,int gravity,int x,int y){
		CreateOptionButtons((LinearLayout)contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ScrollView_ChooseScroll_LLayout));
		this.showAtLocation(parent, gravity, x, y);
	}
	private void CreateOptionButtons(LinearLayout ChooseScroll_LLayout){		
		ChooseScroll_LLayout.removeAllViews();
		if(OptionButtonsList!=null){
			OptionButtonsList.clear();
		}else{
			OptionButtonsList = new ArrayList<OptionButton>();
		}
		
		for(int i =0 ;i<10;i++){
			OptionButton optionButton = new OptionButton();
			ChooseScroll_LLayout.addView(optionButton.cellView);
			this.OptionButtonsList.add(optionButton);
		}
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FS_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FS_PopupWindow.this.dismiss();	
			}
		});	
		//Close Button Listener
		this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FS_PopupWindow.this.dismiss();				
			}
		});
		//Done Button Listener
		this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FS_PopupWindow.this.dismiss();				
			}
		});	
		//SelectAll Button Listener
		this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(FS_PopupWindow.this.OptionButtonsList!=null&&FS_PopupWindow.this.OptionButtonsList.size()>0){
					//全改為以選取
					for (OptionButton optionButton:FS_PopupWindow.this.OptionButtonsList){
						//設定已選取				
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", optionButton.Radio_ImageButton, 2);
					}
				}
			}
		});
	}
	private class OptionButton{
		public View cellView;
		public RelativeLayout ChooseScroll_RLayout;
		public ImageButton Radio_ImageButton;
		public TextView Name_TextView;
		
		public OptionButton(){
			cellView = LayoutInflater.from(context).inflate(R.layout.fs_popupwindow_choosescroll_cell, null);
			cellView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,Tool.getHeight(40)));
			findView(cellView);
			basicSetView();
			ClickListener();
		}
		private void findView(View cellView) {
			this.ChooseScroll_RLayout = (RelativeLayout)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout);
			this.Radio_ImageButton = (ImageButton)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout_Radio_ImageButton);
			this.Name_TextView = (TextView)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout_Name_TextView);
		}
		private void basicSetView() {
			//Radio ImageButton
			Tool.fitsViewHeight(22, this.Radio_ImageButton);
			this.Radio_ImageButton.getLayoutParams().width = Tool.getHeight(22);
			if(true){
				//已選取
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", this.Radio_ImageButton, 2);
			}else{
				//未選取
				new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", this.Radio_ImageButton, 2);
			}
			//Name TextView
			Tool.fitsViewTextSize(6, this.Name_TextView);
		}
		private void ClickListener(){
			this.Radio_ImageButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {				
					if(true){
						//改成未選
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", v, 2);
					}else{
						//改成已選
						new ThreadReadBitMapInAssets(context, "pad/Speakermanagement/group_n.png", v, 2);
					}
				}
			});
		}
	}
	
}

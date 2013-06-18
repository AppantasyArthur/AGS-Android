package com.FM.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class FM_PopupWindow extends PopupWindow {
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_PopupWindwo";
	public FM_PopupWindow(Context context){
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
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fm_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(290, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(295, this.contentView.findViewById(R.id.FM_PopupWindow_Content_RLayout));
		//TITLE TextView
		Tool.fitsViewHeight(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_TITLE_TextView));
		//OPTION ScrollView
		Tool.fitsViewHeight(200, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(50, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_ScrollView));
		//OPTION_LLayout
		LinearLayout OPTION_LLayout = (LinearLayout)this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_OPTION_LLayout);
		CreateOptionButtons(OPTION_LLayout);
		//CANCEL Button
		Tool.fitsViewHeight(40, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewWidth(210, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		Tool.fitsViewBottomMargin(5, this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button));
		
		mlog.info(TAG, "CreateContentView");
	}
	private void CreateOptionButtons(LinearLayout OPTION_LLayout){		
		Button OPTION_Button_1 = new Button(context);		
		Button OPTION_Button_2 = new Button(context);		
		Button OPTION_Button_3 = new Button(context);		
		Button OPTION_Button_4 = new Button(context);
		OPTION_LLayout.addView(OPTION_Button_1);
		OPTION_LLayout.addView(OPTION_Button_2);
		OPTION_LLayout.addView(OPTION_Button_3);
		OPTION_LLayout.addView(OPTION_Button_4);
		SetOptionButtonView(OPTION_Button_1,"Play Now");
		SetOptionButtonView(OPTION_Button_2,"Play Next");
		SetOptionButtonView(OPTION_Button_3,"Replay Queqe");
		SetOptionButtonView(OPTION_Button_4,"Add To Queqe");
	}
	private void SetOptionButtonView(Button OPTION_Button,String str){
		Tool.fitsViewHeight(50, OPTION_Button);
		Tool.fitsViewTextSize(8, OPTION_Button);
		OPTION_Button.setText(str);
		OPTION_Button.setPadding(0, 0, 0, 0);
		OPTION_Button.setGravity(Gravity.CENTER);
		OPTION_Button.setTextColor(Color.WHITE);
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FM_PopupWindow.this.dismiss();	
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FM_PopupWindow_RLayout_CANCEL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FM_PopupWindow.this.dismiss();					
			}
		});		
	}
	
}

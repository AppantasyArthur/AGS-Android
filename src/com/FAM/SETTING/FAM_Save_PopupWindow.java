package com.FAM.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public class FAM_Save_PopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_Save_PopupWindow";
	
	public FAM_Save_PopupWindow(Context context){
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
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_save_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(380, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(352, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		//TITLE RLayout
		Tool.fitsViewHeight(45, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(53, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewWidth(53, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		//Choose TextView
		Tool.fitsViewHeight(26, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(5, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(26, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(5, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		Tool.fitsViewHeight(26, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		Tool.fitsViewWidth(332, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		Tool.fitsViewTextSize(5, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		mlog.info(TAG, "CreateContentView");
	}
	public void ShowPopupWindow(View parent,int gravity,int x,int y){		
		this.showAtLocation(parent, gravity, x, y);		
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.toggleSoftInput(this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText).getId(), 0);
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FAM_Save_PopupWindow.this.dismiss();	
			}
		});	
		//Close Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FAM_Save_PopupWindow.this.dismiss();				
			}
		});
		//Done Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FAM_Save_PopupWindow.this.dismiss();				
			}
		});	
	}
	@Override
	public void dismiss() {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText).getWindowToken(), 0);
		super.dismiss();
	}
}

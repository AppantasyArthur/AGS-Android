package com.FAM.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
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
		Tool.fitsViewHeight(393, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(519, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_01_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewRightMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(295, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(449, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_02_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//Choose TextView
		Tool.fitsViewHeight(22, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(4, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(28, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		Tool.fitsViewHeight(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		Tool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText));
		new ThreadReadBitMapInAssets(context, "pad/pop/insert_box.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText), 3);
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

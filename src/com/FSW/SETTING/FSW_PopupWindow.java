package com.FSW.SETTING;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;

public class FSW_PopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FM_PopupWindwo";
	
	private DeviceDisplay deviceDisplay;
	
	private int device_size = 0;
	public FSW_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDevice_Size();
		if(device_size==6){
			Phone_CreateContentView();
		}else{
			PAD_CreateContentView();
		}
		
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
	private void Phone_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsw_popupwindow_context, null,true);		
		//Content RLayout
		Tool.fitsViewHeight(358, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_01.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(34, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		Tool.fitsViewTopMargin(5,this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Title TextView
		Tool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Close Button
		Tool.fitsViewHeight(22, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(46, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(268, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_02.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//OPTION_ScrollView
		Tool.fitsViewHeight(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		Tool.fitsViewWidth(238, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		//EditBG_ImageView
		new ThreadReadBitMapInAssets(context, "pad/Settings/info_bg.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_EditBG_ImageView), 1);
		//Name EditText
		Tool.fitsViewHeight(36, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		Tool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		Tool.fitsViewLeftMargin(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		//Securty EditText
		Tool.fitsViewHeight(36, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		Tool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		Tool.fitsViewLeftMargin(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		//Join Button
		Tool.fitsViewHeight(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		new ThreadReadStateListInAssets(context, "pad/Settings/join_f.png", "pad/Settings/join_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button), 4);
		Tool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		Tool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		mlog.info(TAG, "CreateContentView");
	}
	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsw_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(597, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_01.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(57, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		Tool.fitsViewTopMargin(8,this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Close Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(447, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_02.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//OPTION_ScrollView
		Tool.fitsViewHeight(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		Tool.fitsViewWidth(397, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		Tool.fitsViewTopMargin(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		//EditBG_ImageView
		new ThreadReadBitMapInAssets(context, "pad/Settings/info_bg.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_EditBG_ImageView), 1);
		//Name EditText
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		Tool.fitsViewLeftMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		//Securty EditText
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		Tool.fitsViewLeftMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		//Join Button
		Tool.fitsViewHeight(45, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		new ThreadReadStateListInAssets(context, "pad/Settings/join_f.png", "pad/Settings/join_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button), 4);
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		Tool.fitsViewTopMargin(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		mlog.info(TAG, "CreateContentView");
	}
	
	
	
		
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FSW_PopupWindow.this.dismiss();	
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FSW_PopupWindow.this.dismiss();					
			}
		});
		//Join Button Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FSW_PopupWindow.this.dismiss();					
			}
		});		
	}
	
	public void SetDeviceDisplay(DeviceDisplay deviceDisplay){
		this.deviceDisplay = deviceDisplay;
		EditText Name_EditText = (EditText)this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText);
		Name_EditText.setText("");
		EditText Securty_EditText = (EditText)this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText);
		Securty_EditText.setText("");
	}
}

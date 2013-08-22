package com.FSAl_EditAdd.SETTING;

import com.alpha.fragments.Fragment_SAlarm_EditAdd;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.NumberPicker;
import android.widget.PopupWindow;


public class FSAl_PopupWindow extends PopupWindow {
	
	private View contentView;

	private NumberPicker hPicker;
	private NumberPicker mPicker;
	private NumberPicker apPicker;
	
	private int device_size = 0;
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_PopupWindow";
	
	private FragmentManager fragmentManager;
	public FSAl_PopupWindow(Context context,FragmentManager fragmentManager){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;		
		this.fragmentManager = fragmentManager;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		if(device_size==6){
			Phone_CreateContentView();			
		}else{
			PAD_CreateContentView();
		}	
		SetNumberPickers();
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
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsal_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(237, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(311, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(32, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(32, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Done Button
		Tool.fitsViewHeight(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(32, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "phone/pop/save_done_botton_f.png", "phone/pop/save_done_botton_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(177, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(270, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg_01.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout), 3);
		mlog.info(TAG, "CreateContentView");
	}

	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsal_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(393, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(519, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_01_bg.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Done Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewRightMargin(40, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(295, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(449, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_02_bg.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout), 3);

		mlog.info(TAG, "CreateContentView");
	}
	
	private void SetNumberPickers() {
		hPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_HPicker_NumberPicker);
		hPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		mPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_MPicker_NumberPicker);
		mPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		apPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_APPicker_NumberPicker);
		apPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		//hPicker
		hPicker.setMaxValue(12);
		hPicker.setMinValue(1);
		hPicker.setValue(1);
		//mPicker
		mPicker.setMaxValue(59);
		mPicker.setMinValue(0);	
		mPicker.setValue(0);
		//apPicker
		apPicker.setDisplayedValues(new String[] {"AM","PM"});
		apPicker.setMaxValue(1);
		apPicker.setMinValue(0);
		apPicker.setValue(0);
		
	
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FSAL_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FSAl_PopupWindow.this.dismiss();	
			}
		});	
		//Close Button Listener
		this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FSAl_PopupWindow.this.dismiss();				
			}
		});
		//Done Button Listener
		this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {	
				Fragment_SAlarm_EditAdd fragment = (Fragment_SAlarm_EditAdd)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
				if(fragment!=null){
					fragment.SetTime(hPicker.getValue(), mPicker.getValue(), apPicker.getValue());
				}
				FSAl_PopupWindow.this.dismiss();				
			}
		});	
	}
}

package com.alpha.setting.alarm;

import com.alpha.fragments.settings.AlarmSettingAddEditFragement;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

// FAM_PopupWindow
public class AlarmSettingTimePickerPopupWindow extends PopupWindow {
	
	private View contentView;

	private NumberPicker hPicker;
	private NumberPicker mPicker;
	private NumberPicker apPicker;
	
	private int sizeDeviceScreen = 0;
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "FAM_PopupWindow";
	
	private FragmentManager fragmentManager;
	public AlarmSettingTimePickerPopupWindow(Context context, FragmentManager fragmentManager){
		
		super(context);
		
		this.mlog.switchLog = true;
		this.context = context;		
		this.fragmentManager = fragmentManager;
		this.sizeDeviceScreen = ((MainFragmentActivity)context).getDeviceScreenSize();
		if(sizeDeviceScreen == 6){
			initPhoneView();			
		}else{
			initPadView();
		}	
		
		initNumberPickers();
		setViewListner();
		
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

	private void initPhoneView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsal_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(172, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(252, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/time_bg_01.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(19, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		TKBTool.fitsViewTopMargin(5, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		TKBTool.fitsViewHeight(14, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(32, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Done Button
		TKBTool.fitsViewHeight(14, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewWidth(32, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "phone/pop/save_done_botton_f.png", "phone/pop/save_done_botton_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(124, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(212, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewTopMargin(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/time_bg_02.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout), 3);
		mlog.info(tag, "CreateContentView");
	}

	private void initPadView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsal_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(286, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(419, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/time_bg_01.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(33, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		TKBTool.fitsViewTopMargin(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		TKBTool.fitsViewHeight(30, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(42, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Done Button
		TKBTool.fitsViewHeight(30, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewWidth(42, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewRightMargin(40, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button));
		new TKBThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(206, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(353, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewTopMargin(15, this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/time_bg_02.png", this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_Body_RLayout), 3);

		mlog.info(tag, "CreateContentView");
	}
	
	private void initNumberPickers() {
		
		hPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_HPicker_NumberPicker);
		hPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		mPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_MPicker_NumberPicker);
		mPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//		apPicker = (NumberPicker)contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_LLayout_APPicker_NumberPicker);
//		apPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//		
		//hPicker
		hPicker.setMaxValue(12);
		hPicker.setMinValue(1);
		hPicker.setValue(1);
		//mPicker
		mPicker.setMaxValue(59);
		mPicker.setMinValue(0);	
		mPicker.setValue(0);
		//apPicker
//		apPicker.setDisplayedValues(new String[] {"AM","PM"});
//		apPicker.setMaxValue(1);
//		apPicker.setMinValue(0);
//		apPicker.setValue(0);
//		
	}
	
	private void setViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FSAL_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlarmSettingTimePickerPopupWindow.this.dismiss();	
			}
		});	
		//Close Button Listener
		this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlarmSettingTimePickerPopupWindow.this.dismiss();				
			}
		});
		//Done Button Listener
		this.contentView.findViewById(R.id.FSAL_PopupWindow_Content_RLayout_RLayout_Done_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {	
				AlarmSettingAddEditFragement fragment = (AlarmSettingAddEditFragement)fragmentManager.findFragmentByTag("Fragment_SAlarm_EditAdd");
				//if(fragment!=null){
					fragment.SetTime(hPicker.getValue(), mPicker.getValue(), apPicker.getValue());
				//}
				AlarmSettingTimePickerPopupWindow.this.dismiss();				
			}
		});	
	}
}

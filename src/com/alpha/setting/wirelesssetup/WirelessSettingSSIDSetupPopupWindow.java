package com.alpha.setting.wirelesssetup;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.alpha.upnp.AGSActionCallback;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AGSHandlerMessages;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnp.value.SystemServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

public class WirelessSettingSSIDSetupPopupWindow extends PopupWindow {
	
	private View contentView;
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "WirelessSettingSSIDSetupPopupWindow";
	
	private DeviceDisplay deviceDisplay;
	
	private int device_size = 0;
	private Handler handlerWirelessSetupPopupWindow;
	public WirelessSettingSSIDSetupPopupWindow(Context context, Handler handlerWirelessSetupPopupWindow){
		
		super(context);
		this.handlerWirelessSetupPopupWindow = handlerWirelessSetupPopupWindow;
		this.mlog.switchLog = true;
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		if(DeviceProperty.isPhone()){
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
		TKBTool.fitsViewHeight(358, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_01.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(34, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		TKBTool.fitsViewTopMargin(5,this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Title TextView
		TKBTool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Close Button
		TKBTool.fitsViewHeight(22, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(46, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(268, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_02.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//OPTION_ScrollView
		TKBTool.fitsViewHeight(300, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewWidth(238, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewTopMargin(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		//EditBG_ImageView
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/info_bg.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_EditBG_ImageView), 1);
		//Name EditText
		TKBTool.fitsViewHeight(36, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		TKBTool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		TKBTool.fitsViewLeftMargin(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		//Securty EditText
		TKBTool.fitsViewHeight(36, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		TKBTool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		TKBTool.fitsViewLeftMargin(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		//Join Button
		TKBTool.fitsViewHeight(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/join_f.png", "pad/Settings/join_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button), 4);
		TKBTool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		TKBTool.fitsViewTopMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		mlog.info(tag, "CreateContentView");
	}
	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fsw_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(597, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_01.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(57, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		TKBTool.fitsViewTopMargin(8,this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Title TextView
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_TITLE_TextView));
		//Close Button
		TKBTool.fitsViewHeight(35, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(56, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(447, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/pop_bg_02.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//OPTION_ScrollView
		TKBTool.fitsViewHeight(500, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewWidth(397, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		TKBTool.fitsViewTopMargin(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_OPTION_ScrollView));
		//EditBG_ImageView
		new TKBThreadReadBitMapInAssets(context, "pad/Settings/info_bg.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_EditBG_ImageView), 1);
		//Name EditText
		TKBTool.fitsViewHeight(61, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		TKBTool.fitsViewLeftMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText));
		//Securty EditText
		TKBTool.fitsViewHeight(61, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		TKBTool.fitsViewLeftMargin(10, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText));
		//Join Button
		TKBTool.fitsViewHeight(45, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		new TKBThreadReadStateListInAssets(context, "pad/Settings/join_f.png", "pad/Settings/join_n.png", this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button), 4);
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		TKBTool.fitsViewTopMargin(20, this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button));
		mlog.info(tag, "CreateContentView");
	}
	
	
	
		
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//SSIDSettingPopupWindow.this.dismiss();
				handlerWirelessSetupPopupWindow.sendEmptyMessage(AGSHandlerMessages.SHOW_MESSAGE);
			}
		});
		//CANCEL Button Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//SSIDSettingPopupWindow.this.dismiss();
				handlerWirelessSetupPopupWindow.sendEmptyMessage(AGSHandlerMessages.SHOW_MESSAGE);
			}
		});
		//Join Button Click Dismiss 
		this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Join_Button).setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void onClick(View v) {
				
				AndroidUpnpService upnpServer = MainFragmentActivity.getServiceAndroidUPnP(); // ((MainFragmentActivity)context).getUPnPService();
				DeviceDisplay mediaRenderDisplay = deviceDisplay;
				
				Device mediaRenderer = mediaRenderDisplay.getDevice();
				
				String systemserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
				String systemserviceType = SystemServiceValues.SERVICE_NAME;
				ServiceType typeSystemService = new ServiceType(systemserviceNamespace, systemserviceType);
				
				Service serviceSystem = mediaRenderer.findService(typeSystemService);
				if(serviceSystem != null){
					
					Action actionSystemInfo = serviceSystem.getAction(SystemServiceValues.ACTION_WIRELESS_SETUP);
					if(actionSystemInfo != null){
						
						List<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
						
						EditText textName = (EditText)contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText);
						ActionArgument argSSID = actionSystemInfo.getInputArgument(SystemServiceValues.ACTION_WIRELESS_SETUP_INPUT_SSID);
						ActionArgumentValue valSSID = new ActionArgumentValue(argSSID, textName.getText().toString());
						values.add(valSSID);
						
						EditText textPassword = (EditText)contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText);
						ActionArgument argPassword = actionSystemInfo.getInputArgument(SystemServiceValues.ACTION_WIRELESS_SETUP_INPUT_PWD);
						ActionArgumentValue valPassword = new ActionArgumentValue(argPassword, textPassword.getText().toString());
						values.add(valPassword);
			
						ActionInvocation invocationSystemInfo = new ActionInvocation(actionSystemInfo , values.toArray(new ActionArgumentValue[values.size()]));
						AGSActionCallback callbackWirelessSetup = new AGSActionCallback(invocationSystemInfo, tag, handlerWirelessSetupPopupWindow){

							@Override
							public void success(ActionInvocation ai) {
								
								Log.d(tag, "callbackWirelessSetup success - ");
								Log.d(tag, "ActionInvocation : " + ai);
								
								ActionArgumentValue result = ai.getOutput(SystemServiceValues.DEFAULT_OUTPUT_RESULT);
								String resultText = (String)result.getValue();
								Log.d(tag, resultText);
								
								Message msg = handlerWirelessSetupPopupWindow.obtainMessage(AGSHandlerMessages.SHOW_MESSAGE);
								msg.obj = resultText;
								handlerWirelessSetupPopupWindow.sendMessage(msg);
								
							}
							
						};
						upnpServer.getControlPoint().execute(callbackWirelessSetup);
						
					}else{
						// error log
						// show message
					}
					
				}else{
					// error log
					// show message
				}
				
			}
			
		});		
		
	}
	
	public void setDeviceDisplay(DeviceDisplay deviceDisplay){
		
		this.deviceDisplay = deviceDisplay;
		
		EditText textName = (EditText)this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Name_EditText);
		textName.setText("");
		EditText textPassword = (EditText)this.contentView.findViewById(R.id.FSW_PopupWindow_Content_RLayout_RLayout_ScrollView_RLayout_Securty_EditText);
		textPassword.setText("");
		
	}
	
}

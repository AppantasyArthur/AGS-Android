package com.FS.SETTING;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.UDAServiceId;

import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.GroupVO;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.graphics.Color;
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
	private DeviceDisplay addDeviceDisplay;
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
		Tool.fitsViewHeight(479, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(417, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/group_01_bg.png", this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(32, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView		
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Done Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewRightMargin(32, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Done_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(359, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Body_RLayout));
		Tool.fitsViewWidth(357, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/group_02_bg.png", this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Body_RLayout), 3);
		//Choose TextView
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(342, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTopMargin(3,this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(30, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(342, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(4, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//ChooseScroll ScrollView
		Tool.fitsViewHeight(280, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		Tool.fitsViewWidth(342, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		Tool.fitsViewTopMargin(71, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ChooseScroll_ScrollView));
		//SelectAll Button
		Tool.fitsViewHeight(36, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewWidth(112, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewBottomMargin(24, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/unselect_f.png", "pad/pop/select_f.png", this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button), 4);
		mlog.info(TAG, "CreateContentView");
	}
	public void ShowPopupWindow(View parent,int gravity,int x,int y){
		this.showAtLocation(parent, gravity, x, y);
	}
	public void SetAddDeviceDisplay(DeviceDisplay addDeviceDisplay){
		this.addDeviceDisplay = addDeviceDisplay;
	}
	public void SetOptionButtons(List<GroupVO> groupVOList){	
		LinearLayout ChooseScroll_LLayout = (LinearLayout)contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_ScrollView_ChooseScroll_LLayout);
		ChooseScroll_LLayout.removeAllViews();
		if(OptionButtonsList!=null){
			OptionButtonsList.clear();
		}else{
			OptionButtonsList = new ArrayList<OptionButton>();
		}
		
		for(int i =0 ;i<groupVOList.size();i++){
			OptionButton optionButton = new OptionButton(context,i,groupVOList.get(i));
			ChooseScroll_LLayout.addView(optionButton.cellView);
			this.OptionButtonsList.add(optionButton);
		}
	}
	public void SetALLOptionButtonsSelect(){
		if(OptionButtonsList!=null){
			for(OptionButton optionButton:OptionButtonsList){
				optionButton.isSelected = true;
				new ThreadReadBitMapInAssets(context, "pad/pop/btn_f.png", optionButton.Radio_ImageButton, 2);
			}
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
				for(int i=0;i<OptionButtonsList.size();i++){
					if(OptionButtonsList.get(i).isSelected){
						SetRelationWithMaster(OptionButtonsList.get(i).groupVO.getUdn(),true);
					}else{
						SetRelationWithMaster(OptionButtonsList.get(i).groupVO.getUdn(),false);
					}					
				}
				mlog.info(TAG, "Done");
				FS_PopupWindow.this.dismiss();				
			}
		});	
		//SelectAll Button Listener
		this.contentView.findViewById(R.id.FS_PopupWindow_Content_RLayout_RLayout_SelectALL_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FS_PopupWindow.this.SetALLOptionButtonsSelect();
			}
		});
	}
	
	private void SetRelationWithMaster(String SUDN,boolean isAdd){
		//取得upnpServer
		AndroidUpnpService upnpServer = ((FragmentActivity_Main)context).GETUPnPService();
		Device mMMDevice = addDeviceDisplay.getMMDevice();
		String MUDN = mMMDevice.getIdentity().getUdn().toString();
		Device MMDevice = ((FragmentActivity_Main)context).GETDeviceDisplayList().GetMMDevice(SUDN);
		if(MMDevice==null){
			return;
		}
		Service GroupService = MMDevice.findService(new UDAServiceId("Group"));
		if(GroupService!=null){
			Action SetRelationWithMasterAction = GroupService.getAction("SetRelationWithMaster");
			if(SetRelationWithMasterAction!=null){				
				ActionArgumentValue[] values = new ActionArgumentValue[2];
				ActionArgument DeviceUDN = SetRelationWithMasterAction.getInputArgument("DeviceUDN");
				ActionArgument RelationAction = SetRelationWithMasterAction.getInputArgument("RelationAction");
				if(DeviceUDN!=null&&RelationAction!=null){
					values[0] =new ActionArgumentValue(DeviceUDN, MUDN);
					if(isAdd){
						values[1] =new ActionArgumentValue(RelationAction, "Add");
					}else{
						values[1] =new ActionArgumentValue(RelationAction, "Remove");
					}
					mlog.info(TAG, "DeviceUDN  = "+values[0].toString());
					mlog.info(TAG, "RelationAction = "+values[1].toString());
					ActionInvocation ai = new ActionInvocation(SetRelationWithMasterAction,values);
					ActionCallback SetRelationWithMasterActionCallBack = new ActionCallback(ai){
						@Override
						public void failure(ActionInvocation arg0, UpnpResponse arg1, String arg2) {
							mlog.info(TAG, "SetRelationWithMasterActionCallBack failure = "+arg2);
						}
						@Override
						public void success(ActionInvocation arg0) {									
							mlog.info(TAG, "SetRelationWithMasterActionCallBack success");
						}											
					};
					upnpServer.getControlPoint().execute(SetRelationWithMasterActionCallBack);	
				}
			}			
		}		
	}	
}

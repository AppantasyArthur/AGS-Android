package com.alpha.mediarender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.upnp.parser.GroupVO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class OptionButton{
	public View cellView;
	public RelativeLayout ChooseScroll_RLayout;
	public ImageButton Radio_ImageButton;
	public TextView Name_TextView;
	public boolean isSelected;
	private int position;
	public GroupVO groupVO;
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "OptionButton";
	private int device_size = 0;
	
	public OptionButton(Context context,int position,GroupVO groupVO){
		this.mlog.switchLog = true;
		this.context = context;
		this.position = position;
		this.groupVO = groupVO;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		cellView = LayoutInflater.from(context).inflate(R.layout.fs_popupwindow_choosescroll_cell, null);
		cellView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		findView(cellView);
		if(DeviceProperty.isPhone()){
			Phone_basicSetView();
		}else{
			PAD_basicSetView();
		}
		
		ClickListener();
	}
	private void findView(View cellView) {
		this.ChooseScroll_RLayout = (RelativeLayout)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout);
		this.Radio_ImageButton = (ImageButton)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout_Radio_ImageButton);
		this.Name_TextView = (TextView)cellView.findViewById(R.id.FS_PopupWindow_ChooseScroll_RLayout_Name_TextView);
	}
	private void Phone_basicSetView() {
		TKBTool.fitsViewHeight(40, cellView);
		//Radio ImageButton
		TKBTool.fitsViewWidth(20, this.Radio_ImageButton);
		this.Radio_ImageButton.getLayoutParams().height = TKBTool.getWidth(20);
		TKBTool.fitsViewLeftMargin(7, this.Radio_ImageButton);
		if(groupVO.isSlave()){
			//¤w¿ï¨ú
			isSelected = true;
		}else{
			//¥¼¿ï¨ú	
			isSelected = false;
		}
		
		if(isSelected){
			//¤w¿ï¨ú
			new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/select_icon.PNG", this.Radio_ImageButton, 2);
		}else{
			//¥¼¿ï¨ú
			new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/unselect_icon.png", this.Radio_ImageButton, 2);
		}
		//Name TextView
		TKBTool.fitsViewLeftMargin(7, this.Name_TextView);
		TKBTool.fitsViewTextSize(14, this.Name_TextView);
		this.Name_TextView.setText(groupVO.getName());
	}
	private void PAD_basicSetView() {
		TKBTool.fitsViewHeight(50, cellView);
		//Radio ImageButton
		TKBTool.fitsViewHeight(30, this.Radio_ImageButton);
		this.Radio_ImageButton.getLayoutParams().width = TKBTool.getHeight(29);
		if(groupVO.isSlave()){
			//¤w¿ï¨ú
			isSelected = true;
		}else{
			//¥¼¿ï¨ú	
			isSelected = false;
		}			
		if(isSelected){
			//¤w¿ï¨ú
			new TKBThreadReadBitMapInAssets(context, "pad/pop/btn_f.png", this.Radio_ImageButton, 2);
		}else{
			//¥¼¿ï¨ú
			new TKBThreadReadBitMapInAssets(context, "pad/pop/btn_n.png", this.Radio_ImageButton, 2);
		}
		//Name TextView
		TKBTool.fitsViewLeftMargin(8, this.Name_TextView);
		TKBTool.fitsViewTextSize(6, this.Name_TextView);
		this.Name_TextView.setText(groupVO.getName());
	}
	private void ClickListener(){
		this.Radio_ImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				if(isSelected){
					if(DeviceProperty.isPhone()){
						//Phone §ï¦¨¥¼¿ï
						isSelected = false;
						new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/unselect_icon.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}else{
						//PAD §ï¦¨¥¼¿ï
						isSelected = false;
						new TKBThreadReadBitMapInAssets(context, "pad/pop/btn_n.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}					
				}else{
					if(DeviceProperty.isPhone()){
						//Phone §ï¦¨¤w¿ï
						isSelected = true;
						new TKBThreadReadBitMapInAssets(context, "phone/grouprooms/select_icon.PNG", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}else{
						//PAD §ï¦¨¤w¿ï
						isSelected = true;
						new TKBThreadReadBitMapInAssets(context, "pad/pop/btn_f.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}					
				}
			}
		});
	}
}

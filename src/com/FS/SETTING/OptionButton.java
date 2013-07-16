package com.FS.SETTING;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.GroupVO;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

public class OptionButton{
	public View cellView;
	public RelativeLayout ChooseScroll_RLayout;
	public ImageButton Radio_ImageButton;
	public TextView Name_TextView;
	public boolean isSelected;
	private int position;
	public GroupVO groupVO;
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "OptionButton";
	private int device_size = 0;
	
	public OptionButton(Context context,int position,GroupVO groupVO){
		this.mlog.LogSwitch = true;
		this.context = context;
		this.position = position;
		this.groupVO = groupVO;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		cellView = LayoutInflater.from(context).inflate(R.layout.fs_popupwindow_choosescroll_cell, null);
		cellView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		findView(cellView);
		if(device_size==6){
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
		Tool.fitsViewHeight(40, cellView);
		//Radio ImageButton
		Tool.fitsViewWidth(20, this.Radio_ImageButton);
		this.Radio_ImageButton.getLayoutParams().height = Tool.getWidth(20);
		Tool.fitsViewLeftMargin(7, this.Radio_ImageButton);
		if(groupVO.isSlave()){
			//¤w¿ï¨ú
			isSelected = true;
		}else{
			//¥¼¿ï¨ú	
			isSelected = false;
		}
		
		if(isSelected){
			//¤w¿ï¨ú
			new ThreadReadBitMapInAssets(context, "phone/grouprooms/select_icon.PNG", this.Radio_ImageButton, 2);
		}else{
			//¥¼¿ï¨ú
			new ThreadReadBitMapInAssets(context, "phone/grouprooms/unselect_icon.png", this.Radio_ImageButton, 2);
		}
		//Name TextView
		Tool.fitsViewLeftMargin(7, this.Name_TextView);
		Tool.fitsViewTextSize(14, this.Name_TextView);
		this.Name_TextView.setText(groupVO.getName());
	}
	private void PAD_basicSetView() {
		Tool.fitsViewHeight(50, cellView);
		//Radio ImageButton
		Tool.fitsViewHeight(30, this.Radio_ImageButton);
		this.Radio_ImageButton.getLayoutParams().width = Tool.getHeight(29);
		if(groupVO.isSlave()){
			//¤w¿ï¨ú
			isSelected = true;
		}else{
			//¥¼¿ï¨ú	
			isSelected = false;
		}			
		if(isSelected){
			//¤w¿ï¨ú
			new ThreadReadBitMapInAssets(context, "pad/pop/btn_f.png", this.Radio_ImageButton, 2);
		}else{
			//¥¼¿ï¨ú
			new ThreadReadBitMapInAssets(context, "pad/pop/btn_n.png", this.Radio_ImageButton, 2);
		}
		//Name TextView
		Tool.fitsViewLeftMargin(8, this.Name_TextView);
		Tool.fitsViewTextSize(6, this.Name_TextView);
		this.Name_TextView.setText(groupVO.getName());
	}
	private void ClickListener(){
		this.Radio_ImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				if(isSelected){
					if(device_size==6){
						//Phone §ï¦¨¥¼¿ï
						isSelected = false;
						new ThreadReadBitMapInAssets(context, "phone/grouprooms/unselect_icon.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}else{
						//PAD §ï¦¨¥¼¿ï
						isSelected = false;
						new ThreadReadBitMapInAssets(context, "pad/pop/btn_n.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}					
				}else{
					if(device_size==6){
						//Phone §ï¦¨¤w¿ï
						isSelected = true;
						new ThreadReadBitMapInAssets(context, "phone/grouprooms/select_icon.PNG", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}else{
						//PAD §ï¦¨¤w¿ï
						isSelected = true;
						new ThreadReadBitMapInAssets(context, "pad/pop/btn_f.png", v, 2);
						mlog.info(TAG, "isSelected = "+isSelected);
					}					
				}
			}
		});
	}
}

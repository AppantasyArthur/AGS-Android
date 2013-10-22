package com.FI.SETTING;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBTool;

public class FI_PointLiLayout extends LinearLayout {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "BaseWord_PointLiLayout";
	private List<ImageView> PointList;
	private int Current = -1;
	private int device_size = 0;
	public FI_PointLiLayout(Context context) {
		super(context);			
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		this.mlog.switchLog = true;
	}
	public FI_PointLiLayout(Context context, AttributeSet attrs) {
		super(context, attrs);	
		this.context = context;
		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		this.mlog.switchLog = true;
	}
	
	public void setPointCount(int Count){	
		if(PointList!=null){
			PointList.clear();
		}else{
			PointList = new ArrayList<ImageView>();
		}
		this.removeAllViews();
		//Create PagePoint
		for(int i =0;i<Count;i++){
			ImageView Point = new ImageView(context);			
			this.addView(Point);
			if(DeviceProperty.isPhone()){
				TKBTool.fitsViewWidth(9, Point);
				Point.getLayoutParams().height = TKBTool.getWidth(9);
			}else{
				TKBTool.fitsViewHeight(9, Point);
				Point.getLayoutParams().width = TKBTool.getHeight(9);
			}
			
			Point.setBackgroundColor(Color.parseColor("#00000000"));
			Point.setScaleType(ScaleType.FIT_XY);
			if(i == Current){
				new TKBThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_f.png", Point, 1);
			}else{
				new TKBThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_n.png", Point, 1);				
			}
			
			PointList.add(Point);
		}	
	}
	public void setPointCurrent(int CurrentPoint){		
		if(Current>=0&&Current<PointList.size()){
			ImageView PointCurrent = PointList.get(Current);
			new TKBThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_n.png", PointCurrent, 1);
		}	
		Current = CurrentPoint;
		if(CurrentPoint>=0){			
			ImageView Point = PointList.get(CurrentPoint);
			new TKBThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_f.png", Point, 1);
		}
		
	}
}

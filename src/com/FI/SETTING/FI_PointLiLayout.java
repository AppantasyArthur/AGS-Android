package com.FI.SETTING;

import java.util.ArrayList;
import java.util.List;

import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class FI_PointLiLayout extends LinearLayout {
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "BaseWord_PointLiLayout";
	private List<ImageView> PointList;
	private int Current = 0;
	private int device_size = 0;
	public FI_PointLiLayout(Context context) {
		super(context);			
		this.context = context;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		this.mlog.LogSwitch = true;
	}
	public FI_PointLiLayout(Context context, AttributeSet attrs) {
		super(context, attrs);	
		this.context = context;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		this.mlog.LogSwitch = true;
	}
	
	public void setPointCount(int Count){	
		PointList = new ArrayList<ImageView>();
		//Create PagePoint
		for(int i =0;i<Count;i++){
			ImageView Point = new ImageView(context);			
			this.addView(Point);
			if(device_size==6){
				Tool.fitsViewWidth(9, Point);
				Point.getLayoutParams().height = Tool.getWidth(9);
			}else{
				Tool.fitsViewHeight(9, Point);
				Point.getLayoutParams().width = Tool.getHeight(9);
			}
			
			Point.setBackgroundColor(Color.parseColor("#00000000"));
			Point.setScaleType(ScaleType.FIT_XY);
			if(i == Current){
				new ThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_n.png", Point, 1);
			}else{
				new ThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_f.png", Point, 1);				
			}
			
			PointList.add(Point);
		}	
	}
	public void setPointCurrent(int CurrentPoint){
		ImageView PointCurrent = PointList.get(Current);
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_f.png", PointCurrent, 1);
		Current = CurrentPoint;
		ImageView Point = PointList.get(CurrentPoint);
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/spot_n.png", Point, 1);
	}
}

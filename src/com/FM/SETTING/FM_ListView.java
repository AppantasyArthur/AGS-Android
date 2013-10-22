package com.FM.SETTING;

import com.alpha.upnpui.MainFragmentActivity;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
public class FM_ListView extends ListView {
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String TAG = "FI_ListView";
	
	private WindowManager windowManager;
	private WindowManager.LayoutParams windowLayoutParam;
	private ImageView dragImageView;
	
	private Bitmap bm;
	private boolean isItemLongClick = false;//在onItemLongClick 轉false
	//手指位置
	private int ev_RX = -1;
	private int ev_RY = -1;
	
	public FM_ListView(Context context) {
		super(context);
		CreateProcess();
	}
	
	public FM_ListView(Context context, AttributeSet attrs) {
		super(context, attrs);	
		CreateProcess();
	}
	public FM_ListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);	
		CreateProcess();
	}
	private void CreateProcess() {
		this.context = this.getContext();
		this.mlog.switchLog = true;
		this.windowManager = (WindowManager)this.context.getSystemService(Context.WINDOW_SERVICE);
		this.bm = TKBTool.readBitMapInAssets(context, "pad/Playlist/song_icon.png");
	}
	public void setItemLongClickState(boolean State){
		this.isItemLongClick = State;		
		this.AddDrag(bm, ev_RX, ev_RY);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//傳送 MotionEvent//
		if(this.isItemLongClick){
			((MainFragmentActivity)context).FMTransformTouchToFI(event);
		}
		//*************//
		this.ev_RX = (int)event.getRawX();
		this.ev_RY = (int)event.getRawY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			if(dragImageView!=null){
				OnDrag(ev_RX-TKBTool.getHeight(100),ev_RY-TKBTool.getHeight(100));
			}
			break;
		case MotionEvent.ACTION_UP:
			
			
			//歸零
			this.ev_RX = -1;
			this.ev_RY = -1;
			this.isItemLongClick = false;
			StopDrag();
			break;
		}
		if(this.isItemLongClick){
			return true;
		}else{		
			return super.onTouchEvent(event);
			
		}
	}	
	private void AddDrag(Bitmap bm,int ev_X,int ev_Y) {
		//確定dragImageView被刪除
		StopDrag();
		windowLayoutParam = new WindowManager.LayoutParams();
		windowLayoutParam.gravity = Gravity.TOP|Gravity.LEFT;
		windowLayoutParam.x = ev_X-TKBTool.getHeight(107);
		windowLayoutParam.y = ev_Y-TKBTool.getHeight(111);
		windowLayoutParam.width = TKBTool.getHeight(107);
		windowLayoutParam.height = TKBTool.getHeight(111);
		windowLayoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		windowLayoutParam.format = PixelFormat.TRANSLUCENT;
		windowLayoutParam.windowAnimations = 0;
		
		dragImageView = new ImageView (this.context);
		dragImageView.setScaleType(ScaleType.FIT_XY);
		dragImageView.setImageBitmap(bm);
		windowManager.addView(dragImageView, windowLayoutParam);
		mlog.info(TAG, "StartDrag");
	}
	public void OnDrag(int ev_X,int ev_Y){
		//移動 dragImageView
		if(dragImageView!=null){
			windowLayoutParam.alpha = 0.8f;
			windowLayoutParam.x = ev_X;
			windowLayoutParam.y = ev_Y;			
	        windowManager.updateViewLayout(dragImageView, windowLayoutParam);
	    }
		mlog.info(TAG, "OnDrag");
	}
	public void StopDrag(){
		//刪除dragImageView
		if(this.dragImageView!=null){
			windowManager.removeView(this.dragImageView);
			this.dragImageView = null;
		}
		mlog.info(TAG, "StopDrag");
	}
	
}

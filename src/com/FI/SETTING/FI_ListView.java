package com.FI.SETTING;

import java.util.List;

import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.tkb.tool.MLog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class FI_ListView extends ListView {
	
	private Context context;
	private MLog mlog = new MLog();
	private static final String TAG = "FI_ListView";
	//拖移
	private WindowManager windowManager;
	private WindowManager.LayoutParams windowLayoutParam;
	private ImageView dragImageView;
	private int StarPosition = -1;
	private int holdPosition = -1;
	private int[] QUEUE_ListView_Location = new int[2];
	private int TouchSlop;
	private int ScrollSlop;
	private int device_size = 0;
	
	private Handler handler = new Handler (){
		@Override
		public void handleMessage(Message msg) {
			int check ;
			switch (msg.what){
			case 0:		
				//顯示上一個
				check = FI_ListView.this.getFirstVisiblePosition()-1;
				if(check>=0){
					holdPosition = check;
					if(device_size==6){
						((FI_Queqe_ListView_BaseAdapter_Phone)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
					}else{
						((FI_Queqe_ListView_BaseAdapter_PAD)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
					}					
					FI_ListView.this.setSelection(holdPosition);
					mlog.info(TAG, "handler previous");
				}									
				break;
			case 1:
				//顯示下一個
				check = FI_ListView.this.getLastVisiblePosition()+1;
				if(check<=FI_ListView.this.getCount()){					
					holdPosition = check-1;		
					if(device_size==6){
						((FI_Queqe_ListView_BaseAdapter_Phone)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
					}else{
						((FI_Queqe_ListView_BaseAdapter_PAD)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
					}
					
					FI_ListView.this.setSelection(FI_ListView.this.getFirstVisiblePosition()+1);
					mlog.info(TAG, "handler next");
				}				
				break;
			}
			super.handleMessage(msg);
		}
	};
	private SHOW_PREVIOUS_THREAD show_Previous_Thread;
	private SHOW_NEXT_THREAD show_Next_Thread;
	
	public FI_ListView(Context context) {
		super(context);
		CreateProcess();
	}
	public FI_ListView(Context context, AttributeSet attrs) {
		super(context, attrs);	
		CreateProcess();
	}
	public FI_ListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);	
		CreateProcess();
	}
	public List<TrackDO>GetQueue(){
		if(device_size==6){
			return ((FI_Queqe_ListView_BaseAdapter_Phone)this.getAdapter()).GetQueue();
		}else{
			return ((FI_Queqe_ListView_BaseAdapter_PAD)this.getAdapter()).GetQueue();
		}
	}
	private void CreateProcess(){
		this.context = this.getContext();
		this.mlog.LogSwitch = true;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		this.windowManager = (WindowManager)this.context.getSystemService(Context.WINDOW_SERVICE);
		this.ScrollSlop = 10;//自動上下一個的範圍
		mlog.info(TAG, "CreateProcess");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(dragImageView!=null){
			int check;
			switch(ev.getAction()){
			case MotionEvent.ACTION_MOVE:
				//限制dragImageView移動範圍
				if(ev.getRawY()>=QUEUE_ListView_Location[1]+TouchSlop&&ev.getRawY()<=(QUEUE_ListView_Location[1]+this.getHeight()+TouchSlop-dragImageView.getHeight())){
					OnDrag((int)ev.getRawY()-TouchSlop);
				}	
				check = this.pointToPosition((int)ev.getX(), (int)ev.getY());//取得position
				if(check!= AdapterView.INVALID_POSITION&&(ev.getRawY()>(QUEUE_ListView_Location[1]+ScrollSlop))&&(ev.getRawY()<(QUEUE_ListView_Location[1]+this.getHeight()-ScrollSlop))){
					if(this.holdPosition!=check){
						
						ViewGroup itemView = (ViewGroup)this.getChildAt(holdPosition-this.getFirstVisiblePosition());
					
						TranslateAnimation move;
						
						if(this.holdPosition>check){
							move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
									Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);
						}else {
							move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
									Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
						}
						this.holdPosition = check; 
						if(device_size==6){
							((FI_Queqe_ListView_BaseAdapter_Phone)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
						}else{
							((FI_Queqe_ListView_BaseAdapter_PAD)FI_ListView.this.getAdapter()).SET_DRAG_HOLD_POSITION(holdPosition);
						}						
						move.setDuration(200);						
						move.setFillBefore(true);
						move.setStartTime(10);
						if(itemView!=null){
							itemView.startAnimation(move);
						}						
					}					
				}				
				//自動顯示上一個
				if(ev.getRawY()>=(QUEUE_ListView_Location[1])&&ev.getRawY()<=(QUEUE_ListView_Location[1]+ScrollSlop)){
					if(show_Previous_Thread==null||!show_Previous_Thread.isStart){
						show_Previous_Thread = new SHOW_PREVIOUS_THREAD();
					}
				}else{
					if(show_Previous_Thread!=null&&show_Previous_Thread.isStart){
						show_Previous_Thread.STOP();
					}
				}
				//自動顯示下一個
				if(ev.getRawY()>=(QUEUE_ListView_Location[1]+this.getHeight()-ScrollSlop)&&ev.getRawY()<=(QUEUE_ListView_Location[1]+this.getHeight())){
					if(show_Next_Thread==null||!show_Next_Thread.isStart){
						show_Next_Thread = new SHOW_NEXT_THREAD();
					}
				}else{
					if(show_Next_Thread!=null&&show_Next_Thread.isStart){
						show_Next_Thread.STOP();
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				StopDrag();	//移除拖移畫面
				//移除上下個 Thread
				if(show_Previous_Thread!=null){
					show_Previous_Thread.STOP();
				}
				if(show_Next_Thread!=null){
					show_Next_Thread.STOP();
				}
				check =this.pointToPosition((int)ev.getX(), (int)ev.getY());//取得position
				if(check!=AdapterView.INVALID_POSITION){
					this.holdPosition = check;
				}
				if(device_size==6){
					((FI_Queqe_ListView_BaseAdapter_Phone)this.getAdapter()).SET_SORT(this.holdPosition);//設定排序位置
					((FI_Queqe_ListView_BaseAdapter_Phone)this.getAdapter()).SET_DRAG_START_POSITION(-1);//還原Start Hold position
				}else{
					((FI_Queqe_ListView_BaseAdapter_PAD)this.getAdapter()).SET_SORT(this.holdPosition);//設定排序位置
					((FI_Queqe_ListView_BaseAdapter_PAD)this.getAdapter()).SET_DRAG_START_POSITION(-1);//還原Start Hold position
				}
				
				mlog.info(TAG, "holdPosition = "+holdPosition+",StarPosition = "+this.StarPosition);
				break;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//取得position
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			//取得QUEUE_ListView_Location
			this.getLocationOnScreen(QUEUE_ListView_Location);
			//設定Start、Hold的Position
			this.holdPosition = this.StarPosition = this.pointToPosition((int)ev.getX(), (int)ev.getY());//取得position
			//是否點到Item有效位置
			if(StarPosition==AdapterView.INVALID_POSITION){
	            return super.onInterceptTouchEvent(ev);
	        }
			//取得顯示的ItemView
			ViewGroup itemView = (ViewGroup)this.getChildAt(StarPosition-this.getFirstVisiblePosition());		
			//取得Drag ImageView
			View dragImageView = itemView.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Drag_ImageView);
			//取得Drag ImageView位置
			int[] dragLocation = new int[2];
			dragImageView.getLocationOnScreen(dragLocation);
			int dragX_Off = dragLocation[0]+dragImageView.getWidth();
			int dragY_Off = dragLocation[1]+dragImageView.getHeight();
			//檢查dragImageView範圍
			int ev_RX = (int)ev.getRawX();
			int ev_RY = (int)ev.getRawY();
			if((!((ev_RX>=dragLocation[0]&&ev_RX<=dragX_Off)&&(ev_RY>=dragLocation[1]&&ev_RY<=dragY_Off)))||dragImageView.getVisibility()==View.GONE){
				return super.onInterceptTouchEvent(ev);
			}			
			
			itemView.destroyDrawingCache();//清除舊的DrawingCache
			itemView.setDrawingCacheEnabled(true);//開啟DrawingCacheEnabled
			Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());//複製畫面
			
			//取得ItemView位置
			int[] location = new int[2];
			itemView.getLocationOnScreen(location);
			TouchSlop = ev_RY-location[1];//設定手指 跟Cell距離
			ScrollSlop = itemView.getHeight()/2;
			
			StartDrag(bm,location[0],location[1]);
			if(device_size==6){
				((FI_Queqe_ListView_BaseAdapter_Phone)this.getAdapter()).SET_DRAG_START_POSITION(this.StarPosition);
			}else{
				((FI_Queqe_ListView_BaseAdapter_PAD)this.getAdapter()).SET_DRAG_START_POSITION(this.StarPosition);
			}
			
			return false;
		}
		
		return super.onInterceptTouchEvent(ev);
	}
	
	
	private void StartDrag(Bitmap bm,int ev_X,int ev_Y) {
		//確定dragImageView被刪除
		StopDrag();
		windowLayoutParam = new WindowManager.LayoutParams();
		windowLayoutParam.gravity = Gravity.TOP|Gravity.LEFT;
		windowLayoutParam.x = ev_X;
		windowLayoutParam.y = ev_Y;
		windowLayoutParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowLayoutParam.height = WindowManager.LayoutParams.WRAP_CONTENT;
		windowLayoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		windowLayoutParam.format = PixelFormat.TRANSLUCENT;
		windowLayoutParam.windowAnimations = 0;
		
		dragImageView = new ImageView (this.context);
		dragImageView.setImageBitmap(bm);
		windowManager.addView(dragImageView, windowLayoutParam);
		mlog.info(TAG, "StartDrag");
	}
	public void StopDrag(){
		//刪除dragImageView
		if(this.dragImageView!=null){
			windowManager.removeView(this.dragImageView);
			this.dragImageView = null;
		}
		mlog.info(TAG, "StopDrag");
	}
	
	public void OnDrag(int ev_Y){
		//移動 dragImageView
		if(dragImageView!=null){
			windowLayoutParam.alpha = 0.8f;
			windowLayoutParam.y = ev_Y;
	        windowManager.updateViewLayout(dragImageView, windowLayoutParam);
	    }
		mlog.info(TAG, "OnDrag");
	}
	private class SHOW_NEXT_THREAD{
		public boolean isStart = false;
		private Thread thread;
		public SHOW_NEXT_THREAD(){
			isStart = true;
			thread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(isStart){						
						handler.obtainMessage(1).sendToTarget();
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {							
							e.printStackTrace();
						}
					}
				}				
			});
			thread.start();
		}
		public void STOP(){
			isStart = false;
		}
	}
	private class SHOW_PREVIOUS_THREAD{
		public boolean isStart = false;
		private Thread thread;
		public SHOW_PREVIOUS_THREAD(){
			isStart = true;
			thread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(isStart){
						handler.obtainMessage(0).sendToTarget();
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {							
							e.printStackTrace();
						}
					}
				}				
			});
			thread.start();
		}
		public void STOP(){
			isStart = false;
		}
	}
}

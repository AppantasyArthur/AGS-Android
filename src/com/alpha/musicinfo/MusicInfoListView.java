package com.alpha.musicinfo;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;

// FI_ListView
public class MusicInfoListView extends ListView {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "MusicInfoListView";
	//拖移
	private WindowManager windowManager;
	private WindowManager.LayoutParams windowLayoutParam;
	private ImageView dragImageView;
	private int positionStart = -1;
	private int positionStop = -1;
	private int[] QUEUE_ListView_Location = new int[2];
	private int TouchSlop;
	private int offsetScrollSlot;
//	private int device_size = 0;
	
	private Handler handler = new Handler (){
		@Override
		public void handleMessage(Message msg) {
			int check ;
			switch (msg.what){
			case 0:		
				//顯示上一個
				check = MusicInfoListView.this.getFirstVisiblePosition()-1;
				if(check>=0){
					positionStop = check;
					if(DeviceProperty.isPhone()){
						((MusicInfoListPhoneViewAdapter)MusicInfoListView.this.getAdapter()).setDragNHoldPosition(positionStop);
					}else{
						((MusicInfoListPadViewAdapter)MusicInfoListView.this.getAdapter()).setDragStopPosition(positionStop);
					}					
					MusicInfoListView.this.setSelection(positionStop);
					mlog.info(tag, "handler previous");
				}									
				break;
			case 1:
				//顯示下一個
				check = MusicInfoListView.this.getLastVisiblePosition()+1;
				if(check<=MusicInfoListView.this.getCount()){					
					positionStop = check-1;		
					if(DeviceProperty.isPhone()){
						((MusicInfoListPhoneViewAdapter)MusicInfoListView.this.getAdapter()).setDragNHoldPosition(positionStop);
					}else{
						((MusicInfoListPadViewAdapter)MusicInfoListView.this.getAdapter()).setDragStopPosition(positionStop);
					}
					
					MusicInfoListView.this.setSelection(MusicInfoListView.this.getFirstVisiblePosition()+1);
					mlog.info(tag, "handler next");
				}				
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	private BackwardThread threadBackward;
	private ForwardThread threadForward;
	
	public MusicInfoListView(Context context) {
		super(context);
		CreateProcess();
	}
	public MusicInfoListView(Context context, AttributeSet attrs) {
		super(context, attrs);	
		CreateProcess();
	}
	public MusicInfoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);	
		CreateProcess();
	}
	public List<TrackDO>GetQueue(){
		if(DeviceProperty.isPhone()){
			return ((MusicInfoListPhoneViewAdapter)this.getAdapter()).GetQueue();
		}else{
			return ((MusicInfoListPadViewAdapter)this.getAdapter()).getQueue();
		}
	}
	private void CreateProcess(){
		this.context = this.getContext();
		this.mlog.switchLog = true;
//		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		this.windowManager = (WindowManager)this.context.getSystemService(Context.WINDOW_SERVICE);
		this.offsetScrollSlot = 10;//自動上下一個的範圍
		mlog.info(tag, "CreateProcess");
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
				if(check!= AdapterView.INVALID_POSITION&&(ev.getRawY()>(QUEUE_ListView_Location[1]+offsetScrollSlot))&&(ev.getRawY()<(QUEUE_ListView_Location[1]+this.getHeight()-offsetScrollSlot))){
					if(this.positionStop!=check){
						
						ViewGroup itemView = (ViewGroup)this.getChildAt(positionStop-this.getFirstVisiblePosition());
					
						TranslateAnimation move;
						
						if(this.positionStop>check){
							move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
									Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);
						}else {
							move = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
									Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
						}
						this.positionStop = check; 
						if(DeviceProperty.isPhone()){
							((MusicInfoListPhoneViewAdapter)MusicInfoListView.this.getAdapter()).setDragNHoldPosition(positionStop);
						}else{
							((MusicInfoListPadViewAdapter)MusicInfoListView.this.getAdapter()).setDragStopPosition(positionStop);
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
				if(ev.getRawY()>=(QUEUE_ListView_Location[1])&&ev.getRawY()<=(QUEUE_ListView_Location[1]+offsetScrollSlot)){
					if(threadBackward==null||!threadBackward.isStart){
						threadBackward = new BackwardThread();
					}
				}else{
					if(threadBackward!=null&&threadBackward.isStart){
						threadBackward.STOP();
					}
				}
				//自動顯示下一個
				if(ev.getRawY()>=(QUEUE_ListView_Location[1]+this.getHeight()-offsetScrollSlot)&&ev.getRawY()<=(QUEUE_ListView_Location[1]+this.getHeight())){
					if(threadForward==null||!threadForward.isStart){
						threadForward = new ForwardThread();
					}
				}else{
					if(threadForward!=null&&threadForward.isStart){
						threadForward.STOP();
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				StopDrag();	//移除拖移畫面
				//移除上下個 Thread
				if(threadBackward!=null){
					threadBackward.STOP();
				}
				if(threadForward!=null){
					threadForward.STOP();
				}
				check =this.pointToPosition((int)ev.getX(), (int)ev.getY());//取得position
				if(check!=AdapterView.INVALID_POSITION){
					this.positionStop = check;
				}
				if(DeviceProperty.isPhone()){
					((MusicInfoListPhoneViewAdapter)this.getAdapter()).SET_SORT(this.positionStop);//設定排序位置
					((MusicInfoListPhoneViewAdapter)this.getAdapter()).SET_DRAG_START_POSITION(-1);//還原Start Hold position
				}else{
					((MusicInfoListPadViewAdapter)this.getAdapter()).setSort(this.positionStop);//設定排序位置
					((MusicInfoListPadViewAdapter)this.getAdapter()).setDragStartPosition(-1);//還原Start Hold position
				}
				
				mlog.info(tag, "holdPosition = "+positionStop+",StarPosition = "+this.positionStart);
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
			this.positionStop = this.positionStart = this.pointToPosition((int)ev.getX(), (int)ev.getY());//取得position
			//是否點到Item有效位置
			if(positionStart==AdapterView.INVALID_POSITION){
	            return super.onInterceptTouchEvent(ev);
	        }
			//取得顯示的ItemView
			ViewGroup itemView = (ViewGroup)this.getChildAt(positionStart-this.getFirstVisiblePosition());		
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
			offsetScrollSlot = itemView.getHeight()/2;
			
			StartDrag(bm,location[0],location[1]);
			if(DeviceProperty.isPhone()){
				((MusicInfoListPhoneViewAdapter)this.getAdapter()).SET_DRAG_START_POSITION(this.positionStart);
			}else{
				((MusicInfoListPadViewAdapter)this.getAdapter()).setDragStartPosition(this.positionStart);
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
		mlog.info(tag, "StartDrag");
	}
	public void StopDrag(){
		//刪除dragImageView
		if(this.dragImageView!=null){
			windowManager.removeView(this.dragImageView);
			this.dragImageView = null;
		}
		mlog.info(tag, "StopDrag");
	}
	
	public void OnDrag(int ev_Y){
		//移動 dragImageView
		if(dragImageView!=null){
			windowLayoutParam.alpha = 0.8f;
			windowLayoutParam.y = ev_Y;
	        windowManager.updateViewLayout(dragImageView, windowLayoutParam);
	    }
		mlog.info(tag, "OnDrag");
	}
	private class ForwardThread{
		public boolean isStart = false;
		private Thread thread;
		public ForwardThread(){
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
	private class BackwardThread{
		public boolean isStart = false;
		private Thread thread;
		public BackwardThread(){
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

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
	//�첾
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
				//��ܤW�@��
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
				//��ܤU�@��
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
		this.offsetScrollSlot = 10;//�۰ʤW�U�@�Ӫ��d��
		mlog.info(tag, "CreateProcess");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(dragImageView!=null){
			int check;
			switch(ev.getAction()){
			case MotionEvent.ACTION_MOVE:
				//����dragImageView���ʽd��
				if(ev.getRawY()>=QUEUE_ListView_Location[1]+TouchSlop&&ev.getRawY()<=(QUEUE_ListView_Location[1]+this.getHeight()+TouchSlop-dragImageView.getHeight())){
					OnDrag((int)ev.getRawY()-TouchSlop);
				}	
				check = this.pointToPosition((int)ev.getX(), (int)ev.getY());//���oposition
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
				//�۰���ܤW�@��
				if(ev.getRawY()>=(QUEUE_ListView_Location[1])&&ev.getRawY()<=(QUEUE_ListView_Location[1]+offsetScrollSlot)){
					if(threadBackward==null||!threadBackward.isStart){
						threadBackward = new BackwardThread();
					}
				}else{
					if(threadBackward!=null&&threadBackward.isStart){
						threadBackward.STOP();
					}
				}
				//�۰���ܤU�@��
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
				StopDrag();	//�����첾�e��
				//�����W�U�� Thread
				if(threadBackward!=null){
					threadBackward.STOP();
				}
				if(threadForward!=null){
					threadForward.STOP();
				}
				check =this.pointToPosition((int)ev.getX(), (int)ev.getY());//���oposition
				if(check!=AdapterView.INVALID_POSITION){
					this.positionStop = check;
				}
				if(DeviceProperty.isPhone()){
					((MusicInfoListPhoneViewAdapter)this.getAdapter()).SET_SORT(this.positionStop);//�]�w�ƧǦ�m
					((MusicInfoListPhoneViewAdapter)this.getAdapter()).SET_DRAG_START_POSITION(-1);//�٭�Start Hold position
				}else{
					((MusicInfoListPadViewAdapter)this.getAdapter()).setSort(this.positionStop);//�]�w�ƧǦ�m
					((MusicInfoListPadViewAdapter)this.getAdapter()).setDragStartPosition(-1);//�٭�Start Hold position
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
		//���oposition
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			//���oQUEUE_ListView_Location
			this.getLocationOnScreen(QUEUE_ListView_Location);
			//�]�wStart�BHold��Position
			this.positionStop = this.positionStart = this.pointToPosition((int)ev.getX(), (int)ev.getY());//���oposition
			//�O�_�I��Item���Ħ�m
			if(positionStart==AdapterView.INVALID_POSITION){
	            return super.onInterceptTouchEvent(ev);
	        }
			//���o��ܪ�ItemView
			ViewGroup itemView = (ViewGroup)this.getChildAt(positionStart-this.getFirstVisiblePosition());		
			//���oDrag ImageView
			View dragImageView = itemView.findViewById(R.id.FI_QUEUE_ListView_Cell_RLayout_Drag_ImageView);
			//���oDrag ImageView��m
			int[] dragLocation = new int[2];
			dragImageView.getLocationOnScreen(dragLocation);
			int dragX_Off = dragLocation[0]+dragImageView.getWidth();
			int dragY_Off = dragLocation[1]+dragImageView.getHeight();
			//�ˬddragImageView�d��
			int ev_RX = (int)ev.getRawX();
			int ev_RY = (int)ev.getRawY();
			if((!((ev_RX>=dragLocation[0]&&ev_RX<=dragX_Off)&&(ev_RY>=dragLocation[1]&&ev_RY<=dragY_Off)))||dragImageView.getVisibility()==View.GONE){
				return super.onInterceptTouchEvent(ev);
			}			
			
			itemView.destroyDrawingCache();//�M���ª�DrawingCache
			itemView.setDrawingCacheEnabled(true);//�}��DrawingCacheEnabled
			Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());//�ƻs�e��
			
			//���oItemView��m
			int[] location = new int[2];
			itemView.getLocationOnScreen(location);
			TouchSlop = ev_RY-location[1];//�]�w��� ��Cell�Z��
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
		//�T�wdragImageView�Q�R��
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
		//�R��dragImageView
		if(this.dragImageView!=null){
			windowManager.removeView(this.dragImageView);
			this.dragImageView = null;
		}
		mlog.info(tag, "StopDrag");
	}
	
	public void OnDrag(int ev_Y){
		//���� dragImageView
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

package com.tkb.tool;


import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.StateSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ThreadReadStateListInAssets {
	private Context context;
	private String resName_a;
	private String resName_b;
	private View view;
	private int type;
//	private static HashMap<String,SoftReference<StateListDrawable>> map = new HashMap<String,SoftReference<StateListDrawable>>();
	private static HashMap<String,SoftReference<Bitmap>> map = new HashMap<String,SoftReference<Bitmap>>();
	private static Handler mHandler = new Handler() {  
        public void handleMessage (Message msg) {        	
        	HandlerView hview = (HandlerView)msg.obj;
            switch(msg.what) {  
            case 1:  
            	((ImageView)hview.view).setImageDrawable((Drawable)hview.states);
                break;  
            case 2:             
            	((ImageButton)hview.view).setBackgroundDrawable((Drawable)hview.states);
                break;
            case 3:
            	hview.view.setBackgroundDrawable((Drawable)hview.states);
            	break;
            case 4:         
            	((Button)hview.view).setBackgroundDrawable((Drawable)hview.states);
            	break;
            }            
        }  
    };
	private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
	//type 1 =ImageView, 2 = ImageButton
	public ThreadReadStateListInAssets(Context context, String resName_a,String resName_b,View view,int type){
		this.context = context;
		this.resName_a = resName_a;
		this.resName_b = resName_b;
		this.view = view;
		this.type = type;		
		CreateThread();
	}
	
	private void CreateThread(){
		executorService.submit(new Runnable(){
			@Override
			public void run() {
				
				Bitmap bitmapA = null;
				Bitmap bitmapB = null;
				if(map.get(resName_a)==null||map.get(resName_a).get()==null){
					bitmapA = Tool.readBitMapInAssets(context, resName_a);
					SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmapA);
					map.put(resName_a, softReference);					
				}else{
					bitmapA = map.get(resName_a).get();					
				}
				if(map.get(resName_b)==null||map.get(resName_b).get()==null){
					bitmapB = Tool.readBitMapInAssets(context, resName_b);
					SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmapB);
					map.put(resName_b, softReference);					
				}else{
					bitmapB = map.get(resName_b).get();					
				}				
				
				StateListDrawable states = null;
				
				states = new StateListDrawable();
				
				states.addState(new int[] {android.R.attr.state_pressed}, new BitmapDrawable(bitmapA));
				states.addState(StateSet.WILD_CARD,new BitmapDrawable(bitmapB));
				
				HandlerView handlerView = new HandlerView(ThreadReadStateListInAssets.this.view,states);
				mHandler.obtainMessage(type, handlerView).sendToTarget();				
			}
		});
	}
	private class HandlerView{
		public View view;
		public StateListDrawable states;
		public HandlerView(View view,StateListDrawable states){
			this.view = view;
			this.states = states;
		}
	}
	
}

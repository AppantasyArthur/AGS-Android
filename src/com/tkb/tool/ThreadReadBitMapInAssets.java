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
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ThreadReadBitMapInAssets {
	private Context context;
	private String resName;
	private View view;
	private int type;
	private static HashMap<String,SoftReference<Bitmap>> map = new HashMap<String,SoftReference<Bitmap>>();
	private static final ExecutorService executorService = Executors.newFixedThreadPool(2);
	private static Handler mHandler = new Handler() {  
        public void handleMessage (Message msg) {
        	HandlerView hview = (HandlerView)msg.obj;
            switch(msg.what) {             
            case 1:  
            	((ImageView)hview.view).setImageBitmap((Bitmap)hview.bitmap);
            	hview.view.invalidate();
                break;  
            case 2:  
            	((ImageButton)hview.view).setImageBitmap((Bitmap)hview.bitmap);
                break;
            case 3:  
            	hview.view.setBackgroundDrawable(new BitmapDrawable((Bitmap)hview.bitmap));
                break;  
            case 4: 
            	//¹w¸ü¹Ï¤ù
            	break;
            }           
        }  
    };
	//type 1 =ImageView, 2 = ImageButton,3 = view
	public ThreadReadBitMapInAssets(Context context, String resName,View view,int type){
		this.context = context;
		this.resName = resName;
		this.view = view;
		this.type = type;
		CreateThread();
	}
	private void CreateThread(){
		
		executorService.submit(new Runnable(){
			@Override
			public void run() {
				
				Bitmap bitmap = null;
				if(map.get(resName)==null||map.get(resName).get()==null){
					int randon = (int)(Math.random()*50);
					try {
						Thread.sleep(randon);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}
					bitmap = Tool.readBitMapInAssets(context, resName);					
					SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
					map.put(resName, softReference);					
				}else{
					bitmap = map.get(resName).get();					
				}
				
				HandlerView hview = new HandlerView(ThreadReadBitMapInAssets.this.view,bitmap);
				mHandler.obtainMessage(type, hview).sendToTarget();
			}
		});
	}
	private class HandlerView{
		public View view;
		public Bitmap bitmap;
		public HandlerView(View view,Bitmap bitmap){
			this.view = view;
			this.bitmap = bitmap;
		}
	}
	
}

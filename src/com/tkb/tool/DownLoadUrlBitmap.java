package com.tkb.tool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class DownLoadUrlBitmap {
	private static HashMap<String,SoftReference<Bitmap>> map = new HashMap<String,SoftReference<Bitmap>>();
	private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	private ImageView imageView;
	private String Url;
	
	private static Handler mHandler = new Handler() {  
        public void handleMessage (Message msg) {
        	HandlerView hview = (HandlerView)msg.obj;
            switch(msg.what) {             
            case 1: 
            	if(hview.view!=null){
            		((ImageView)hview.view).setImageBitmap((Bitmap)hview.bitmap);
//                	hview.view.invalidate();
            	}            	
                break;  
            }           
        }  
    };
    
	public DownLoadUrlBitmap(ImageView imageView, String Url){
		this.imageView = imageView;
		this.Url = Url;
		CreateThread();
	}
	private void CreateThread(){
		executorService.submit(new Runnable(){
			@Override
			public void run() {
				Bitmap bitmap = null;
				URL imageURL = null;
				if(map.get(Url)==null||map.get(Url).get()==null){					
					try {
						imageURL = new URL(DownLoadUrlBitmap.this.Url);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					try {
						HttpURLConnection connection = (HttpURLConnection)imageURL.openConnection();
						connection.connect();
						InputStream is = connection.getInputStream();
						bitmap = BitmapFactory.decodeStream(is,null,Tool.opt()); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(bitmap==null){
						return;
					}
									
					SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
					map.put(DownLoadUrlBitmap.this.Url, softReference);					
				}else{
					bitmap = map.get(DownLoadUrlBitmap.this.Url).get();					
				}
				
				HandlerView hview = new HandlerView(DownLoadUrlBitmap.this.imageView,bitmap);
				mHandler.obtainMessage(1, hview).sendToTarget();
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

package com.tkb.tool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DownLoadUrlBitmap {
	private static HashMap<String,SoftReference<Bitmap>> map = new HashMap<String,SoftReference<Bitmap>>();
	private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
	public Bitmap DownLoadUrlBitmap(String Url){
		URL imageURL = null;
		Bitmap bitmap = null;
		try {
			imageURL = new URL(Url);
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
		return bitmap;
	}
}

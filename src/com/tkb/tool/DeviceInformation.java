package com.tkb.tool;

import java.lang.reflect.Method;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.view.Display;

public class DeviceInformation {
	private Context context;
	
	private static String TAG = "DeviceImformation";
	private TKBLog mlog = new TKBLog();
	public DeviceInformation(Context context) {
		this.context = context;
		this.mlog.switchLog = true;
	}
//	private boolean getDetect_Network(){
//		mlog.info(TAG, "getDetect_Network");
//		boolean Detect_Network = false;
//		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo netInfo = cm.getActiveNetworkInfo();
//		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//			Detect_Network = true;
//		}else{
//			Detect_Network = false;
//		}
//		mlog.info(TAG, "Detect_Network = "+Detect_Network);
//		return Detect_Network;
//	}
	
	public int getDeviceScreenSize(){
		mlog.info(TAG, "getDevice");
		int device = 6;//預設值是手機
		Display display;
		DisplayMetrics displayMetrics = new DisplayMetrics();
		Activity activity = (Activity)context;
		display = activity.getWindowManager().getDefaultDisplay(); 
		Class<?> c;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
        }catch(Exception e){
            e.printStackTrace();
        }
        
       if(Math.pow((Math.pow((float)displayMetrics.widthPixels/(float)displayMetrics.densityDpi, 2)+Math.pow((float)displayMetrics.heightPixels/(float)displayMetrics.densityDpi, 2)),0.5f)>6.5){
    	   device = 7;
       }else{
    	   device = 6;
       }
       mlog.info(TAG, "device = "+device);
		return device;
	}
//	private String getDeviceType(){
//		String deviceType = "";
//		try{
//			deviceType  = Build.MODEL;
//		}catch(Exception e){
//			deviceType = "";
//		}
//		return deviceType;
//	}
//	private String getOS(){
//		mlog.info(TAG, "getOS");
//		String sdk = Build.VERSION.RELEASE;
//		mlog.info(TAG, "OS = "+sdk);
//		return sdk;
//	}
	public String getVersion(){
		String versionName = "";
		
		try {
			versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		version = Float.valueOf(versionName);
//		version = version+0.0f;			
//		mlog.info(TAG,"versionName ="+versionName);
		return versionName;
	}
//	private double[] getPosition(){
//		mlog.info(TAG, "getPosition");
//		double[] position = {777,777};
//		String best;
//		Location location = null;
//		try{
//			LocationManager mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
//			if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
//				mlog.info(TAG, "GPS = open");
//				Criteria criteria = new Criteria();
//				best = mLocationManager.getBestProvider(criteria, true);
//				location = mLocationManager.getLastKnownLocation(best);
//				if(location!=null){
//					position[0] = location.getLongitude();
//					position[1] = location.getLatitude();
//				}
//			}else{
//				mlog.info(TAG, "GPS = close");
//			}
//		}catch(SecurityException e){
//			Log.w(TAG, ""+e.toString());
//		}
//		mlog.info(TAG, "Longitude = "+position[0]+"Latitude = "+position[1]);
//		return position;
//	}
//	private String getTimeZone(){
//		mlog.info(TAG, "getTimeZone");
//		TimeZone time = TimeZone.getDefault();
//		String timeZone = time.getID();
//		mlog.info(TAG, "timeZone= "+timeZone);
//		return timeZone;
//	}
}

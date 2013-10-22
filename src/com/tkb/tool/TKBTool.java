package com.tkb.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.alpha.upnpui.R;
import com.alpha.upnpui.R.drawable;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class TKBTool {
	private static final String TAG = "Tool";
	private static TKBLog mLog = new TKBLog();
	public static LayoutParams createLayoutParam(float weight){
		LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, weight);
		return param;
	}
	
	public static void showAlert(Context context,String ErrorMessage) {
        String title = null;
        String alertMessage = null;
        title = "Error";
        alertMessage = ErrorMessage;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(alertMessage).setPositiveButton("OK", null);
        builder.show();
        mLog.info(TAG, "showAlert");
    }
	public static ProgressDialog getProgressDialog(Context context){
		ProgressDialog LoadingProgressDialog = new ProgressDialog(context);
		LoadingProgressDialog.setCancelable(false);
		LoadingProgressDialog.setMessage("Loading....");
		mLog.info(TAG, "createLoadingProgressDialog OK");
		return LoadingProgressDialog;
	}
	
	public static BitmapFactory.Options opt(){
		BitmapFactory.Options option;
		option = new BitmapFactory.Options();
		option.inPreferredConfig = Bitmap.Config.ARGB_8888; 
		option.inPurgeable = true; 
		option.inInputShareable = true;
		option.inSampleSize = 1;
		return option;
	 }
	 
	 public static Bitmap readBitMap(Context context, int resId){ 
	 	InputStream is = context.getResources().openRawResource(resId); 
	 	Bitmap orgBitmap = BitmapFactory.decodeStream(is,null,opt()); 
	 	long size = orgBitmap.getHeight()*orgBitmap.getWidth();
	 	if(size>=7680){
	 		Bitmap SceleBitmap = Bitmap.createScaledBitmap(orgBitmap, (int)(orgBitmap.getWidth()*0.7), (int)(orgBitmap.getHeight()*0.7), true);
	 		orgBitmap.recycle();
	 		return SceleBitmap; 
	 	}
	 	return orgBitmap; 
	 }
	 public static Bitmap readBitMapInAssets(Context context, String resName){ 
		 	InputStream is = context.getClass().getResourceAsStream("/assets/"+resName); 
		 	Bitmap orgBitmap = BitmapFactory.decodeStream(is,null,opt()); 
		 	long size = orgBitmap.getHeight()*orgBitmap.getWidth();
		 	float Room = roomSize.getWidthRoomSize();
		 	if(size>=7680){
		 		Bitmap SceleBitmap = null;
		 		if(Room<1){
		 			SceleBitmap = Bitmap.createScaledBitmap(orgBitmap, (int)(orgBitmap.getWidth()*0.7*Room), (int)(orgBitmap.getHeight()*0.7*Room), true);
		 		}else{
		 			SceleBitmap = Bitmap.createScaledBitmap(orgBitmap, (int)(orgBitmap.getWidth()*0.7), (int)(orgBitmap.getHeight()*0.7), true);
		 		}
		 		orgBitmap.recycle();
		 		return SceleBitmap; 
		 	}
		 	return orgBitmap; 
	 }
	 public static int findResIDByString(String imageName){
		 Class<drawable> drawable = R.drawable.class;
		 Field field = null;
		 String[] name = imageName.split("\\.");
		 int r_id = 0;
		 try {
			 field = drawable.getField(name[0]);
			 r_id = field.getInt(field.getName());
	     } catch (Exception e) {
	    	 mLog.info(TAG, "findResIDByString not found");
	    	 r_id = 0;
	     }
		 return r_id;
	 }
	 public static RoomSize roomSize;
	//orignal size 480*800
	 public static int getWidth(int orignalSize){
		 int resultSize = (int)((float)orignalSize * roomSize.getWidthRoomSize());
		 return resultSize;
	 }
	 public static int getHeight(int orignalSize){
		 int resultSize = (int)((float)orignalSize * roomSize.getHeightRoomSize());
		 return resultSize;
	 }
	 public static void fitsViewHeight(int orignalSize,View view){
		 int resultSize = (int)((float)orignalSize * roomSize.getHeightRoomSize());
		 view.getLayoutParams().height = resultSize;		
	 }
	 public static void fitsViewWidth(int orignalSize,View view){
		 int resultSize = (int)((float)orignalSize * roomSize.getWidthRoomSize());
		 view.getLayoutParams().width = resultSize;		
	 }
	 public static void fitsViewTopMargin (int orignalSize,View view){
		 RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
		 int resultSize = (int)((float)orignalSize * roomSize.getHeightRoomSize());
		 layoutParams.topMargin =  resultSize;
	 }
	 public static void fitsViewBottomMargin (int orignalSize,View view){
		 RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
		 int resultSize = (int)((float)orignalSize * roomSize.getHeightRoomSize());
		 LayoutParams.bottomMargin =  resultSize;
	 }
	 public static void fitsViewLeftMargin (int orignalSize,View view){
		 RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
		 int resultSize = (int)((float)orignalSize * roomSize.getWidthRoomSize());
		 LayoutParams.leftMargin =  resultSize;
	 }
	 public static void ToDayStatus_ReLayout_ScrollView_ReLayout_NeedleBackGround_ImageView (int orignalSize,View view){
		 RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
		 int resultSize = (int)((float)orignalSize * roomSize.getWidthRoomSize());
		 LayoutParams.leftMargin =  resultSize;
	 }

	public static void fitsViewRightMargin (int orignalSize,View view){
		 RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
		 int resultSize = (int)((float)orignalSize * roomSize.getWidthRoomSize());
		 LayoutParams.rightMargin =  resultSize;
	 }
	 public static void fitsViewTextSize (int orignalSize,View view){
		 TextView textView = (TextView) view;
		 textView.setTextSize((float)orignalSize*roomSize.getTextRoomSize());
	 }
	public static String format(int x ){
			String s = ""+x;
			if(s.length()==1) s="0"+s;
			return s;
	}
	public static String getPreference(Context context, String sName,String isNull){
		SharedPreferences settings = context.getSharedPreferences("Preference", 0);
		String setting = settings.getString(sName, isNull);
		return setting;
	}
	public static void setPreference(Context context, String sName,String value){
		SharedPreferences settings = context.getSharedPreferences("Preference", 0);
		settings.edit().putString(sName, value).commit();
		
	}
	public static String fileToString(Context context,String txtId){
		StringBuilder JASONString = new StringBuilder("");
		try {
			InputStream inputStream = context.getResources().getAssets().open(txtId);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			
			String temp = "";
			while((temp = br.readLine())!=null){
				JASONString.append(temp);
				JASONString.append("\n");
			}
			br.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return JASONString.toString();
	}
	public static JSONObject StringToJSONObject(String JASONString){
		JSONObject JSONObjectSource=null;
		if (JASONString==null || JASONString.equals("")){
			return null;
		}
		if(JASONString!=null&&JASONString.startsWith("\ufeff")){
			JASONString = JASONString.substring(1);
		}
		try {
			JSONObjectSource = new  JSONObject(JASONString);
			mLog.info(TAG,"JSONObjectSource create" );
		} catch (JSONException e) {
			Log.e(TAG,"JSONObjectSource ERROR" );
			e.printStackTrace();
		}
		
		return JSONObjectSource;
	}
	public static JSONObject GetJSONObjectFromJSONArray(JSONArray JSONArraySource,int index){
		JSONObject JSONObjectSource=null;
		if (JSONArraySource==null || JSONArraySource.length()==0){
			return null;
		}
		try {
			JSONObjectSource = JSONArraySource.getJSONObject(index);
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return JSONObjectSource;
	}
	public static JSONArray AnalysisJSONArray(JSONObject JSONObjectSource,String name){
		JSONArray jsonArray = null;
		if(JSONObjectSource==null){
			return null;
		}
		try {
			jsonArray = JSONObjectSource.getJSONArray(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.w(TAG, "AnalysisJSONArray===>"+name);
		}
		return jsonArray;
	} 
	public static String AnalysisJSONObjectToString(JSONObject JSONObjectSource,String name){
		String jsonString = null;
		if(JSONObjectSource==null){
			return null;
		}
		try {
			jsonString = JSONObjectSource.getString(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.w(TAG, "JSONObjectSource===>"+name);
			
		}
		return jsonString;
	} 
	
	public static void setButtonHighLight(View view){
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(!v.isClickable()){
					return false;
				}
				switch(event.getAction()){				
				case MotionEvent.ACTION_DOWN:					
					((ImageButton)v).setAlpha(154);
					break;
				case MotionEvent.ACTION_UP:
					((ImageButton)v).setAlpha(255);
					break;
				case MotionEvent.ACTION_CANCEL:
					((ImageButton)v).setAlpha(255);
					break;
				}
				return false;
			}
		});
	}
//	public static void FragmentActivity_MainReplaceFragment(Context fActivity,Fragment fragment,String Tag,int AnimationIn,int AnimationOut){
//		FragmentTransaction fragmentTransaction = ((FragmentActivity_Main)fActivity).getSupportFragmentManager().beginTransaction();
//		fragmentTransaction.setCustomAnimations(AnimationIn, AnimationOut);
//		fragmentTransaction.replace(R.id.FragmentActivity_Main_ReLayout, fragment,Tag);
//		fragmentTransaction.commit();
//		mLog.info(TAG, "FragmentActivity_MainReplaceFragment");
//	}
	public static void animationAddFragment(FragmentTransaction fragmentTransaction,Fragment fragment,String Tag,int View_ID,int AnimationIn,int AnimationOut){
		fragmentTransaction.setCustomAnimations(AnimationIn, AnimationOut);
		fragmentTransaction.add(View_ID, fragment,Tag);
		fragmentTransaction.commit();
		mLog.info(TAG, "animationAddFragment");
	}
	public static void animationReplaceFragment(FragmentTransaction fragmentTransaction,Fragment fragment,String Tag,int View_ID,int AnimationIn,int AnimationOut){
		fragmentTransaction.setCustomAnimations(AnimationIn, AnimationOut);
		fragmentTransaction.replace(View_ID, fragment,Tag);
		fragmentTransaction.commit();
		mLog.info(TAG, "animationReplaceFragment");
	}
	public static void animationReplaceNAdd2BackFragment(FragmentTransaction fragmentTransaction,Fragment fragment,String Tag,int View_ID,int AnimationIn,int AnimationOut,int AnimationIn2,int AnimationOut2){
		fragmentTransaction.setCustomAnimations(AnimationIn, AnimationOut,AnimationIn2, AnimationOut2);
		fragmentTransaction.replace(View_ID, fragment, Tag);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
		mLog.info(TAG, "animationReplaceNAdd2BackFragment");
	}
	public static void animationRemoveFragment(FragmentManager fragmentManager,String Tag,int AnimationIn,int AnimationOut){
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = fragmentManager.findFragmentByTag(Tag);
		if(fragment!=null){
			fragmentTransaction.setCustomAnimations(AnimationIn, AnimationOut);
			fragmentTransaction.remove(fragment);
			fragmentTransaction.commit();
			mLog.info(TAG, "animationRemoveFragment");
		}else{	
			mLog.info(TAG, "animationRemoveFragment is null");
		}
	}
	public static void doPopBackFragment(FragmentManager fragmentManager){
		fragmentManager.popBackStack();
		mLog.info(TAG, "doPopBackFragment");
	}
}

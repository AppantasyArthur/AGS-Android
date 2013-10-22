package com.tkb.tool;

import android.util.DisplayMetrics;
import android.view.Display;
/*
 * SCREEN 介面比例，類別化
 */
public class RoomSize {
	private static float widthRoomSize;//寬的比例
	private static float heightRoomSize;//高的比例
	private static float textRoomSize;
	
	public RoomSize(Display display,DeviceInformation deviceInformation){
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);//取得DisplayMetrics
		if(deviceInformation.getDeviceScreenSize()==6){
			//手機
			if(displayMetrics.heightPixels>displayMetrics.widthPixels){
				//直向比例
				heightRoomSize = (float)displayMetrics.heightPixels/(float)480;//高的比例
				widthRoomSize = (float)displayMetrics.widthPixels/(float)320;//寬的比例
				textRoomSize = ((float)displayMetrics.widthPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}else{
				//橫向比例
				heightRoomSize = (float)displayMetrics.heightPixels/(float)320;//寬的比例
				widthRoomSize = (float)displayMetrics.widthPixels/(float)480;//高的比例
				textRoomSize = ((float)displayMetrics.heightPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}
		}else{
			//平板
			if(displayMetrics.heightPixels>displayMetrics.widthPixels){
				//直向比例
				heightRoomSize = (float)displayMetrics.heightPixels/(float)1024;//高的比例
				widthRoomSize = (float)displayMetrics.widthPixels/(float)768;//寬的比例
				textRoomSize = ((float)displayMetrics.widthPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}else{
				//橫向比例
				heightRoomSize = (float)displayMetrics.heightPixels/(float)768;//寬的比例
				widthRoomSize = (float)displayMetrics.widthPixels/(float)1024;//高的比例
				textRoomSize = ((float)displayMetrics.heightPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}
		}		
	}
	
	public float getWidthRoomSize() {
		return widthRoomSize;
	}

	public float getHeightRoomSize() {
		return heightRoomSize;
	}
	public float getTextRoomSize(){		
		return textRoomSize;
	};
}

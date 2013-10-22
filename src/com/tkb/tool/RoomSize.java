package com.tkb.tool;

import android.util.DisplayMetrics;
import android.view.Display;
/*
 * SCREEN ������ҡA���O��
 */
public class RoomSize {
	private static float widthRoomSize;//�e�����
	private static float heightRoomSize;//�������
	private static float textRoomSize;
	
	public RoomSize(Display display,DeviceInformation deviceInformation){
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);//���oDisplayMetrics
		if(deviceInformation.getDeviceScreenSize()==6){
			//���
			if(displayMetrics.heightPixels>displayMetrics.widthPixels){
				//���V���
				heightRoomSize = (float)displayMetrics.heightPixels/(float)480;//�������
				widthRoomSize = (float)displayMetrics.widthPixels/(float)320;//�e�����
				textRoomSize = ((float)displayMetrics.widthPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}else{
				//��V���
				heightRoomSize = (float)displayMetrics.heightPixels/(float)320;//�e�����
				widthRoomSize = (float)displayMetrics.widthPixels/(float)480;//�������
				textRoomSize = ((float)displayMetrics.heightPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}
		}else{
			//���O
			if(displayMetrics.heightPixels>displayMetrics.widthPixels){
				//���V���
				heightRoomSize = (float)displayMetrics.heightPixels/(float)1024;//�������
				widthRoomSize = (float)displayMetrics.widthPixels/(float)768;//�e�����
				textRoomSize = ((float)displayMetrics.widthPixels/(float)displayMetrics.densityDpi)/((float)480/(float)240);
			}else{
				//��V���
				heightRoomSize = (float)displayMetrics.heightPixels/(float)768;//�e�����
				widthRoomSize = (float)displayMetrics.widthPixels/(float)1024;//�������
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

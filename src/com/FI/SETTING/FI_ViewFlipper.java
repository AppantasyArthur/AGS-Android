package com.FI.SETTING;

import com.alpha.upnpui.R;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class FI_ViewFlipper extends ViewFlipper {
	
	private View new_View;
	private View old_View;
	private FI_PointLiLayout Point_LLayout;
	
	private int CurrentPage = 0;
	private int CountPage = 3;
	
	//手勢
	private GestureDetector ViewGestureDetector;
	
	private static final String TAG = "FI_ViewFlipper";
	private MLog mlog = new MLog();	
	private Context context;
	
	private boolean isTouchDownHere = false;//是不是在這個VIEW上Touch Down
	
	public FI_ViewFlipper(Context context) {
		super(context);	
		this.context = context;
		CreateProcess();
		ADD_NEW_VIEW(0);
	}
	public FI_ViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);	
		this.context = context;
		CreateProcess();
		ADD_NEW_VIEW(0);
	}
	
	public void setFI_PointLiLayout(FI_PointLiLayout Point_LLayout){
		this.Point_LLayout = Point_LLayout;
		if(this.Point_LLayout!=null){
			Point_LLayout.setPointCount(CountPage);
		}
	}
	
	private void CreateProcess(){
		this.mlog.LogSwitch = true;
		ViewGestureDetector = new GestureDetector(context,new MyGestureListener());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch(event.getAction()){		
		case MotionEvent.ACTION_DOWN:
			isTouchDownHere = true;
			mlog.info(TAG, "ACTION_DOWN");
			break;
		
		case MotionEvent.ACTION_UP:
			if(isTouchDownHere){			
				ViewGestureDetector.onTouchEvent(event);			
			}
			isTouchDownHere = false;
			mlog.info(TAG, "ACTION_UP");
			break;
		}
		if(isTouchDownHere){			
			ViewGestureDetector.onTouchEvent(event);			
		}
		return true;
	}
	
	public void loadPrevious(){
		//在入上一頁
		//檢查
		if((CurrentPage-1)<0){			
    		return;
    	}		
		ADD_NEW_VIEW(-1);//載入		
		//設定動畫
		this.setInAnimation(AnimationUtils.loadAnimation(context, R.animator.translate_left_in));
		this.setOutAnimation(AnimationUtils.loadAnimation(context,R.animator.translate_right_out));
		this.showPrevious();//顯示
		this.removeView(old_View);//移除
	}
	public void loadNext(){
		//在入下一頁
		//檢查
		if((CurrentPage+1)>=CountPage){			
    		return;
    	}
		ADD_NEW_VIEW(1);//載入		
		//設定動畫
		this.setInAnimation(AnimationUtils.loadAnimation(context, R.animator.translate_right_in));
		this.setOutAnimation(AnimationUtils.loadAnimation(context,R.animator.translate_left_out));
		this.showPrevious();//顯示
		this.removeView(old_View);//移除
	}
	
	public void SetCount(int Count){
		if(Point_LLayout!=null){
			Point_LLayout.setPointCount(Count);
		}
		this.CountPage = Count;
	}
	private void ADD_NEW_VIEW(int NB){		
		
		//加入上一頁或下一頁
		CurrentPage = CurrentPage+NB;
		
		mlog.info(TAG, "CurrentPage = "+CurrentPage);
		mlog.info(TAG, "CountPage = "+CountPage);
		
		if(this.Point_LLayout!=null){
			Point_LLayout.setPointCurrent(CurrentPage);
		}
		
		old_View = new_View;		
		new_View = LayoutInflater.from(context).inflate(R.layout.fi_infor_viewflipper_cell_pad, null);
		new_View.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		SetView(new_View);
		this.addView(new_View);
	}
	//設定介面
	private void SetView(View view) {
		//Cell RLayout
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/nowplaying_bg_f.png", view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout), 3);
		//MusicName TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewHeight(30, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewTopMargin(55, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewTextSize(10, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		//MusicCount TextView
		Tool.fitsViewWidth(77, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewHeight(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewTextSize(5, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		//MusicArtist TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewHeight(23, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewTopMargin(120, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		//MusicAlbum TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewHeight(23, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		//MusicGenre TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewHeight(23, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewTopMargin(10, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		//MusicNext TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewHeight(23, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewBottomMargin(50, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewLeftMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewTextSize(6, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		//Image_RLayout
		view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout).getLayoutParams().width = Tool.getHeight(150);
		Tool.fitsViewHeight(150, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		Tool.fitsViewTopMargin(55, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		Tool.fitsViewRightMargin(20, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		//Image_ImageView
		view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView).getLayoutParams().width = Tool.getHeight(135);
		Tool.fitsViewHeight(135, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView));
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/NoCover.png", view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView), 1);
	}
	private class MyGestureListener extends SimpleOnGestureListener{

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,	float velocityY) {
			mlog.info(TAG, "onFling");
			if(e1.getX()>e2.getX()){
				//下一頁
				loadNext();
				mlog.info(TAG, "onFling_loadNext");
			}else{
				//上一頁
				loadPrevious();
				mlog.info(TAG, "onFling_loadPrevious");
			}
			return false;
		}
	}
}

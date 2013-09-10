package com.FI.SETTING;

import java.util.ArrayList;
import java.util.List;
import com.alpha.UPNP.DeviceDisplay;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.tkb.tool.DownLoadUrlBitmap;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class FI_ViewFlipper extends ViewFlipper {
	
	private View new_View;
	private View old_View;
	private FI_PointLiLayout Point_LLayout;
	
	private int CurrentPage = -1;	
	
	private List<DeviceDisplay> GroupList;	
	//手勢
	private GestureDetector ViewGestureDetector;
	
	private static final String TAG = "FI_ViewFlipper";
	private MLog mlog = new MLog();	
	private Context context;
	
	private boolean isTouchDownHere = false;//是不是在這個VIEW上Touch Down
	private int device_size = 0;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(new_View==null){
				return;
			}
			String str = "";
			switch(msg.what){
			case 0:
				str = (String)msg.obj;
				TextView MusicName_TextView = (TextView)new_View.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView);
				if(str.equals("")||str==null){
					MusicName_TextView.setText("NA");
				}else{
					MusicName_TextView.setText(str);
				}			
				break;
			case 1:
				str = (String)msg.obj;
				TextView MusicArtist_TextView = (TextView)new_View.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView);
				if(str.equals("")||str==null){
					MusicArtist_TextView.setText("Artist : NA");
				}else{
					MusicArtist_TextView.setText("Artist : "+str);
				}				
				break;
			case 2:
				str = (String)msg.obj;
				TextView MusicAlbum_TextView = (TextView)new_View.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView);
				if(str.equals("")||str==null){
					MusicAlbum_TextView.setText("Album : NA");
				}else{
					MusicAlbum_TextView.setText("Album : "+str);
				}	
				break;
			case 3:
				str = (String)msg.obj;
				TextView MusicGenre_TextView = (TextView)new_View.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView);
				if(str.equals("")||str==null){
					MusicGenre_TextView.setText("Genre : NA");
				}else{
					MusicGenre_TextView.setText("Genre : "+str);
				}
				break;
			case 4:
				
				str = (String)msg.obj;
				Log.i(TAG, "bbbbbbbbb = 4"+str);
				ImageView Image_ImageView = (ImageView)new_View.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView);
				if(str.equals("")||str==null){
					Image_ImageView.setImageBitmap(Tool.readBitMapInAssets(context, "pad/Nowplaying/NoCover.png"));
				}else{
					new DownLoadUrlBitmap(Image_ImageView,str);					
				}
				break;
			case 5:					
				FI_ViewFlipper.this.GroupList.clear();
				FI_ViewFlipper.this.GroupList = GetGroupList();				
				FI_ViewFlipper.this.setFI_PointLiLayoutCount(GroupList.size());
				break;
			case 6:
				if(context!=null&&FI_ViewFlipper.this.Point_LLayout!=null){				
					FI_ViewFlipper.this.CurrentPage = GroupList.indexOf(((MainFragmentActivity)context).GETDeviceDisplayList().getChooseMediaRenderer());
					FI_ViewFlipper.this.Point_LLayout.setPointCurrent(CurrentPage);
				}
				break;
			}
		}
	};
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
	@Override
	protected void onDetachedFromWindow() {
		try{			
			super.onDetachedFromWindow();			
		}catch (IllegalArgumentException e) {
			this.stopFlipping();	        
        }
		
	}
	public void onDetachedFromWindow_P(){
		this.onDetachedFromWindow();
	}
		
	public void setFI_PointLiLayout(FI_PointLiLayout Point_LLayout){
		this.Point_LLayout = Point_LLayout;
		this.setFI_PointLiLayoutCount(GroupList.size());
	}
	public void setFI_PointLiLayoutCount(int count){
		if(this.Point_LLayout!=null){
			Point_LLayout.setPointCount(count);
		}
	}
	private void CreateProcess(){
		this.mlog.LogSwitch = true;
		this.GroupList = GetGroupList();
		ViewGestureDetector = new GestureDetector(context,new MyGestureListener());
		device_size = ((MainFragmentActivity)context).getDevice_Size();
		CreateMusicInfor_Listner();
	}
	private List<DeviceDisplay> GetGroupList(){
		List<DeviceDisplay> list = new ArrayList<DeviceDisplay>();
		for(DeviceDisplay deviceDisplay:((MainFragmentActivity)context).GETDeviceDisplayList().getGroupList()){
			list.add(deviceDisplay);
		}
		return list;		
	}
	private void CreateMusicInfor_Listner(){
		MusicInfo_Listner musicInfo = new MusicInfo_Listner(){
			
			@Override
			public void ClearMusicInfo_State() {
				handler.obtainMessage(0, "").sendToTarget();
				handler.obtainMessage(1, "").sendToTarget();
				handler.obtainMessage(2, "").sendToTarget();
				handler.obtainMessage(3, "").sendToTarget();	
				handler.obtainMessage(4, "").sendToTarget();	
			}
			@Override
			public void SetMusicInfo_State(String Title, String Artist,String Album, String Genre,String AlbumURI) {
				if(Title!=null&&Title!=""){
					handler.obtainMessage(0, Title).sendToTarget();
				}
				if(Artist!=null&&Artist!=""){
					handler.obtainMessage(1, Artist).sendToTarget();
				}
				if(Album!=null&&Album!=""){
					handler.obtainMessage(2, Album).sendToTarget();
				}
				if(Genre!=null&&Genre!=""){
					handler.obtainMessage(3, Genre).sendToTarget();
				}
				if(AlbumURI!=null&&AlbumURI!=""){
					handler.obtainMessage(4, AlbumURI).sendToTarget();
				}
			}
			@Override
			public void MediaRendererCountChange() {
				handler.obtainMessage(5).sendToTarget();			
				
			}		
			@Override
			public void SetPositionChange() {
				handler.obtainMessage(6).sendToTarget();			
			}
				
		};
		((MainFragmentActivity)context).GETDeviceDisplayList().setMusicInfo_Listner(musicInfo);
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
		SetChooseMediaRenderer();
	}
	public void loadNext(){
		//在入下一頁
		//檢查
		if((CurrentPage+1)>=GroupList.size()){			
    		return;
    	}
		ADD_NEW_VIEW(1);//載入		
		//設定動畫
		this.setInAnimation(AnimationUtils.loadAnimation(context, R.animator.translate_right_in));
		this.setOutAnimation(AnimationUtils.loadAnimation(context,R.animator.translate_left_out));
		this.showPrevious();//顯示		
		this.removeView(old_View);//移除
		SetChooseMediaRenderer();
	}
	public void SetChooseMediaRenderer(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(350);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(CurrentPage>=0){
					((MainFragmentActivity)context).GETDeviceDisplayList().setChooseMediaRenderer(GroupList.get(CurrentPage));
				}	
			}			
		}).start();
	}
	public void SetCount(int Count){
		if(Point_LLayout!=null){
			Point_LLayout.setPointCount(Count);
		}		
	}
	private void ADD_NEW_VIEW(int NB){		
		
		//加入上一頁或下一頁
		CurrentPage = CurrentPage+NB;
			
		mlog.info(TAG, "CurrentPage = "+CurrentPage);
		mlog.info(TAG, "CountPage = "+GroupList.size());
		
		if(this.Point_LLayout!=null){
			Point_LLayout.setPointCurrent(CurrentPage);
			
		}
		old_View = new_View;		
		new_View = LayoutInflater.from(context).inflate(R.layout.fi_infor_viewflipper_cell_pad, null);
		new_View.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		if(device_size==6){
			Phone_SetView(new_View);
		}else{
			PAD_SetView(new_View);
		}		
		this.addView(new_View);
	}
	
	//設定介面
	private void Phone_SetView(View view) {
		//Cell RLayout
		new ThreadReadBitMapInAssets(context, "phone/nowplaying/now playing_bg.PNG", view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout), 3);
		//MusicName TextView
		Tool.fitsViewWidth(270, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewHeight(19, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewTopMargin(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		Tool.fitsViewTextSize(12, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicName_TextView));
		//MusicCount TextView
		Tool.fitsViewWidth(183, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewHeight(8, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewTopMargin(3, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		Tool.fitsViewTextSize(5, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicCount_TextView));
		//MusicArtist TextView
		Tool.fitsViewWidth(255, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewHeight(14, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewTopMargin(250, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicArtist_TextView));
		//MusicAlbum TextView
		Tool.fitsViewWidth(255, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewHeight(14, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewTopMargin(3, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicAlbum_TextView));
		//MusicGenre TextView
		Tool.fitsViewWidth(255, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewHeight(14, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewTopMargin(3, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicGenre_TextView));
		//MusicNext TextView
		Tool.fitsViewWidth(212, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewHeight(13, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewBottomMargin(35, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewLeftMargin(65, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		Tool.fitsViewTextSize(8, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_MusicNext_TextView));
		//Image_RLayout
		view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout).getLayoutParams().height = Tool.getWidth(160);
		Tool.fitsViewWidth(158, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		Tool.fitsViewTopMargin(40, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		Tool.fitsViewRightMargin(80, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_Image_RLayout));
		//Image_ImageView
		view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView).getLayoutParams().height = Tool.getWidth(160);
		Tool.fitsViewWidth(158, view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView));
		new ThreadReadBitMapInAssets(context, "pad/Nowplaying/NoCover.png", view.findViewById(R.id.FI_INFOR_ViewFlipper_Cell_RLayout_RLayout_Image_ImageView), 1);
	}	
	//設定介面
	private void PAD_SetView(View view) {
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

package com.FAM.SETTING;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alpha.upnpui.FragmentActivity_Main;
import com.alpha.upnpui.R;
import com.appantasy.androidapptemplate.event.lastchange.TrackDO;
import com.tkb.tool.MLog;
import com.tkb.tool.ThreadReadBitMapInAssets;
import com.tkb.tool.ThreadReadStateListInAssets;
import com.tkb.tool.Tool;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public class FAM_Save_PopupWindow extends PopupWindow {
	
	private View contentView;
	private EditText InputName_EditText;
	
	private Context context;	
	private int device_size = 0;
	private MLog mlog = new MLog();
	private static final String TAG = "FAM_Save_PopupWindow";
	
	public FAM_Save_PopupWindow(Context context){
		super(context);
		this.mlog.LogSwitch = true;
		this.context = context;
		this.device_size = ((FragmentActivity_Main)context).getDevice_Size();
		if(device_size==6){
			Phone_CreateContentView();
		}else{
			PAD_CreateContentView();
		}		
		ContentViewListner();
		//設定內容
		this.setContentView(contentView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		//設定back鍵  dismiss
		ColorDrawable dw = new ColorDrawable(-00000);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);	
		//設定Outside dismiss
		this.setOutsideTouchable(true);
		
	}
	private void Phone_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_save_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(237, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(311, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewHeight(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewWidth(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		new ThreadReadStateListInAssets(context, "phone/pop/save_done_botton_f.png", "phone/pop/save_done_botton_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(177, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(270, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg_01.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//Choose TextView
		Tool.fitsViewHeight(31, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(238, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(21, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(238, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		InputName_EditText = (EditText)this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText);
		Tool.fitsViewHeight(26, InputName_EditText);
		Tool.fitsViewWidth(242, InputName_EditText);
		Tool.fitsViewTextSize(8, InputName_EditText);
		Tool.fitsViewTopMargin(5, InputName_EditText);
		new ThreadReadBitMapInAssets(context, "phone/pop/save_name_bar.png", InputName_EditText, 3);
		mlog.info(TAG, "CreateContentView");
	}
	private void PAD_CreateContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_save_popupwindow_context, null,true);
		//Content RLayout
		Tool.fitsViewHeight(393, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		Tool.fitsViewWidth(519, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_01_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		Tool.fitsViewHeight(61, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		Tool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		Tool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewRightMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		new ThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button), 4);
		//Body_RLayout
		Tool.fitsViewHeight(295, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		Tool.fitsViewWidth(449, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		new ThreadReadBitMapInAssets(context, "pad/pop/save queqe_02_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//Choose TextView
		Tool.fitsViewHeight(22, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		Tool.fitsViewTextSize(4, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		Tool.fitsViewHeight(28, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		Tool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		InputName_EditText = (EditText)this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText);
		Tool.fitsViewHeight(40, InputName_EditText);
		Tool.fitsViewWidth(400, InputName_EditText);
		Tool.fitsViewTextSize(8, InputName_EditText);
		Tool.fitsViewTopMargin(10, InputName_EditText);
		new ThreadReadBitMapInAssets(context, "pad/pop/insert_box.png", InputName_EditText, 3);
		mlog.info(TAG, "CreateContentView");
	}
	public void ShowPopupWindow(View parent,int gravity,int x,int y){		
		this.showAtLocation(parent, gravity, x, y);		
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.toggleSoftInput(this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText).getId(), 0);
	    
	}
	private void ContentViewListner(){
		//setDismiss 
		//Outside Click Dismiss 
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_BackGround_RLayout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FAM_Save_PopupWindow.this.dismiss();	
			}
		});	
		//Close Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FAM_Save_PopupWindow.this.dismiss();				
			}
		});
		//Done Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String inputName = InputName_EditText.getText().toString();
				//取得Queue 轉成 JSONArray
				List<TrackDO> queueItems = ((FragmentActivity_Main)context).GETFragment_Infor().GetQueueItems();
				JSONArray queueArray = new JSONArray();
				for(TrackDO item:queueItems){
					JSONObject jItme = new JSONObject();
					try {
						jItme.put("Track_Id", item.getId());
						jItme.put("Track_Title", item.getTitle());
					} catch (JSONException e) {						
						e.printStackTrace();
					}
					if(item.getMetaData()==null){
						try {
							jItme.put("Track_MetaData", "");
						} catch (JSONException e) {							
							e.printStackTrace();
						}
					}else{
						try {
							jItme.put("Track_MetaData", item.getMetaData());
						} catch (JSONException e) {							
							e.printStackTrace();
						}
					}
					queueArray.put(jItme);
				}
				
				SharedPreferences sharedPreferences = context.getSharedPreferences("LocalMusicList", Context.MODE_PRIVATE);
				String strLocalMusicListName = sharedPreferences.getString("LocalMusicList", "{}");
				JSONObject MusicList = null;
				try {
					MusicList = new JSONObject(strLocalMusicListName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				//複寫 或 新增
				try {
					MusicList.put(inputName, queueArray);
				} catch (JSONException e) {						
					e.printStackTrace();
				}
							
				sharedPreferences.edit().putString("LocalMusicList", MusicList.toString()).commit();
				//刷新MusicList
				((FragmentActivity_Main)context).GETFragment_Music().MusicListViewLocalNameListChange();
				FAM_Save_PopupWindow.this.dismiss();				
			}
		});	
	}
	@Override
	public void dismiss() {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText).getWindowToken(), 0);
		super.dismiss();
	}
}

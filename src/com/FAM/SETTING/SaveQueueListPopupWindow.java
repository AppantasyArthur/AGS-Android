package com.FAM.SETTING;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.alpha.fragments.MediaRendererMusicInfoFragement;
import com.alpha.upnp.DeviceDisplayList;
import com.alpha.upnp.parser.TrackDO;
import com.alpha.upnp.service.AGSAVTransportService;
import com.alpha.upnp.service.AGSActionSuccessCaller;
import com.alpha.upnp.value.AVTransportServiceValues;
import com.alpha.upnp.value.ContentDirectoryServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.AGSParser;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBThreadReadBitMapInAssets;
import com.tkb.tool.TKBThreadReadStateListInAssets;
import com.tkb.tool.TKBTool;

// FAM_Save_PopupWindow
@SuppressWarnings({"rawtypes", "unchecked"})
public class SaveQueueListPopupWindow extends PopupWindow {
	
	private View contentView;
	private EditText editCustomQueueListName;
	
	private Context context;	
//	private int device_size = 0;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "SaveQueueListPopupWindow";
	
	public SaveQueueListPopupWindow(Context context){
		
		super(context);
		this.mlog.switchLog = true;
		this.context = context;
//		this.device_size = ((MainFragmentActivity)context).getDeviceScreenSize();
		
		if(DeviceProperty.isPhone()){
			initPhoneContentView();
		}else{
			initPadContentView();
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
	private void initPhoneContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_save_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(237, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(311, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		TKBTool.fitsViewHeight(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "phone/pop/save_close_botton.png", "phone/pop/save_close_botton.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(12, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		TKBTool.fitsViewHeight(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewWidth(32, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTopMargin(9, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewRightMargin(16, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		new TKBThreadReadStateListInAssets(context, "phone/pop/save_done_botton_f.png", "phone/pop/save_done_botton_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(177, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(270, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_pop_bg_01.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//Choose TextView
		TKBTool.fitsViewHeight(31, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		TKBTool.fitsViewWidth(238, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		TKBTool.fitsViewHeight(21, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		TKBTool.fitsViewWidth(238, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		TKBTool.fitsViewTextSize(10, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		editCustomQueueListName = (EditText)this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText);
		TKBTool.fitsViewHeight(26, editCustomQueueListName);
		TKBTool.fitsViewWidth(242, editCustomQueueListName);
		TKBTool.fitsViewTextSize(8, editCustomQueueListName);
		TKBTool.fitsViewTopMargin(5, editCustomQueueListName);
		new TKBThreadReadBitMapInAssets(context, "phone/pop/save_name_bar.png", editCustomQueueListName, 3);
		mlog.info(tag, "CreateContentView");
	}
	
	private void initPadContentView() {
		this.contentView = LayoutInflater.from(context).inflate(R.layout.fam_save_popupwindow_context, null,true);
		//Content RLayout
		TKBTool.fitsViewHeight(393, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		TKBTool.fitsViewWidth(519, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/pop/save queqe_01_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout), 3);
		//TITLE RLayout
		TKBTool.fitsViewHeight(61, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_TITLE_RLayout));
		//Close Button
		TKBTool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewLeftMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button));
		new TKBThreadReadStateListInAssets(context, "pad/pop/cancel_f.png", "pad/pop/cancel_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button), 4);
		//Title TextView
		TKBTool.fitsViewTextSize(8, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Title_TextView));
		//Save Button
		TKBTool.fitsViewHeight(35, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewWidth(56, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTopMargin(13, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewRightMargin(40, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button));
		new TKBThreadReadStateListInAssets(context, "pad/pop/done_f.png", "pad/pop/done_n.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button), 4);
		//Body_RLayout
		TKBTool.fitsViewHeight(295, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		TKBTool.fitsViewWidth(449, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout));
		new TKBThreadReadBitMapInAssets(context, "pad/pop/save queqe_02_bg.png", this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_Body_RLayout), 3);
		//Choose TextView
		TKBTool.fitsViewHeight(22, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		TKBTool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		TKBTool.fitsViewTextSize(4, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Choose_TextView));
		//ChooseNmae TextView
		TKBTool.fitsViewHeight(28, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		TKBTool.fitsViewWidth(400, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		TKBTool.fitsViewTextSize(6, this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_ChooseName_TextView));
		//InputName EditText
		editCustomQueueListName = (EditText)this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText);
		TKBTool.fitsViewHeight(40, editCustomQueueListName);
		TKBTool.fitsViewWidth(400, editCustomQueueListName);
		TKBTool.fitsViewTextSize(8, editCustomQueueListName);
		TKBTool.fitsViewTopMargin(10, editCustomQueueListName);
		new TKBThreadReadBitMapInAssets(context, "pad/pop/insert_box.png", editCustomQueueListName, 3);
		mlog.info(tag, "CreateContentView");
	}
	
	public void showPopupWindow(View parent,int gravity,int x,int y){		
		
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
				SaveQueueListPopupWindow.this.dismiss();	
			}
		});	
		
		//Close Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Close_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SaveQueueListPopupWindow.this.dismiss();				
			}
		});
		
		//Done Button Listener
		this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_Save_Button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				AGSAVTransportService service = new AGSAVTransportService(DeviceDisplayList.getChooseMediaRenderer().getDevice()
												, MediaRendererMusicInfoFragement.getMessageHandler());
				
				Action action = service.getActionDumpAllTracksInQueue();
				if(action != null){
					
					ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
					
					ActionArgument argInstanceID = action.getInputArgument(AVTransportServiceValues.ACTION_DUMP_ALL_TRACKS_IN_QUEUE_INPUT_INSTANCE_ID);
					ActionArgumentValue valInstanceID = new ActionArgumentValue(argInstanceID, "0");
					values.add(valInstanceID);
					
					service.actDumpAllTracksInQueue(values.toArray(new ActionArgumentValue[values.size()])
							, new DumpAllTracksInQueueScuuessCaller());
					
				}
				
			}
			
		});	
		
	}
	
	private class DumpAllTracksInQueueScuuessCaller extends AGSActionSuccessCaller<Object>{
		
		@Override
		public Object call() throws Exception {
			
			// get result
			String didlAllTracks = (String)ai.getOutput(AVTransportServiceValues.ACTION_DUMP_ALL_TRACKS_IN_QUEUE_OUTPUT_ALL_TRACKS_DIDL).getValue();
			
			// parsing result
			List<TrackDO> tracks = AGSParser._parseTrack(didlAllTracks);
			// store result
			
//			ai.getOutput().;
//			ai.getOutput(argumentName)
			
//			String nameCustomList = editCustomQueueListName.getText().toString();
//			
//			//取得Queue 轉成 JSONArray
//			List<TrackDO> queueItems = ((MainFragmentActivity)context).GETFragment_Infor().GetQueueItems();
//			JSONArray queueArray = new JSONArray();
//			for(TrackDO item:queueItems){
//				JSONObject jItme = new JSONObject();
//				try {
//					jItme.put("Track_Id", item.getId());
//					jItme.put("Track_Title", item.getTitle());
//				} catch (JSONException e) {						
//					e.printStackTrace();
//				}
//				if(item.getMetaData()==null){
//					try {
//						jItme.put("Track_MetaData", "");
//					} catch (JSONException e) {							
//						e.printStackTrace();
//					}
//				}else{
//					try {
//						jItme.put("Track_MetaData", item.getMetaData());
//					} catch (JSONException e) {							
//						e.printStackTrace();
//					}
//				}
//				queueArray.put(jItme);
//			}
//			
//			SharedPreferences sharedPreferences = context.getSharedPreferences("LocalMusicList", Context.MODE_PRIVATE);
//			String strLocalMusicListName = sharedPreferences.getString("LocalMusicList", "{}");
//			JSONObject MusicList = null;
//			try {
//				MusicList = new JSONObject(strLocalMusicListName);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			//複寫 或 新增
//			try {
//				MusicList.put(nameCustomList, queueArray);
//			} catch (JSONException e) {						
//				e.printStackTrace();
//			}
//						
//			sharedPreferences.edit().putString("LocalMusicList", MusicList.toString()).commit();
//			//刷新MusicList
//			((MainFragmentActivity)context).GETFragment_Music().MusicListViewLocalNameListChange();
//			SaveQueueListPopupWindow.this.dismiss();
			
			return super.call();
			
		}
		
	}
	
	@Override
	public void dismiss() {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.contentView.findViewById(R.id.FAM_Save_PopupWindow_Content_RLayout_RLayout_InputName_EditText).getWindowToken(), 0);
		super.dismiss();
	}
	
}

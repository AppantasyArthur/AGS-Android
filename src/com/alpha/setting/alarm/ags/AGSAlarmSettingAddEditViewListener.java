package com.alpha.setting.alarm.ags;

import java.util.ArrayList;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionArgumentValue;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Action;
import org.teleal.cling.model.meta.ActionArgument;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceType;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.alpha.fragments.settings.AlarmSettingFrequencyOptionFragement;
import com.alpha.fragments.settings.AlarmSettingMusicBrowsingFragement;
import com.alpha.setting.alarm.AlarmSettingTimePickerPopupWindow;
import com.alpha.upnp.DeviceDisplay;
import com.alpha.upnp.value.AlarmServiceValues;
import com.alpha.upnp.value.ServiceValues;
import com.alpha.upnpui.MainFragmentActivity;
import com.alpha.upnpui.R;
import com.alpha.util.DeviceProperty;
import com.tkb.tool.TKBLog;
import com.tkb.tool.TKBTool;

// FSAl_EditAdd_VIEW_LISTNER
public class AGSAlarmSettingAddEditViewListener {
	
	private Context context;
	private TKBLog mlog = new TKBLog();
	private static final String tag = "AGSAlarmSettingAddEditViewListener";
	private int sizeDeviceScreen = 0;
	private DeviceDisplay chooseDeviceDisplay;
	
	public AGSAlarmSettingAddEditViewListener(Context context, int sizeDeviceScreen, DeviceDisplay chooseDeviceDisplay) {
		
		this.context = context;
		this.mlog.switchLog = true;
		this.sizeDeviceScreen = sizeDeviceScreen;
		this.chooseDeviceDisplay = chooseDeviceDisplay;
		
	}	
	
	public void setBackButtonListener(Button buttonBack,final FragmentManager fragmentManager){
		if(DeviceProperty.isPhone()){ // DeviceProperty.isPhone()
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}else{
			buttonBack.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {	
					//TKBTool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
					TKBTool.doPopBackFragment(fragmentManager);
				}
			});	
		}		
	}
	
	public void setAlarmSwitchListener(Switch switchAlarm, final RelativeLayout layoutAlarmProfile){
		
		if(DeviceProperty.isPhone()){
			switchAlarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(tag, " setAlarmSwitchListener isChecked = " + isChecked);
					if(isChecked){
						layoutAlarmProfile.setVisibility(View.VISIBLE);
					}else{
						layoutAlarmProfile.setVisibility(View.INVISIBLE);
					}
				}
			});
		}else{
			switchAlarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					mlog.info(tag, " setAlarmSwitchListener isChecked = " + isChecked);
					if(isChecked){
						layoutAlarmProfile.setVisibility(View.VISIBLE);
					}else{
						layoutAlarmProfile.setVisibility(View.INVISIBLE);
					}
				}
			});
		}	
		
	}
	
	public void setAlarmTimeListener(RelativeLayout layoutAlarmTime, final FragmentManager fragmentManager){
		
		if(DeviceProperty.isPhone()){
			layoutAlarmTime.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AGSAlarmSettingTimePickerPopupWindow popupWindow = new AGSAlarmSettingTimePickerPopupWindow(context, fragmentManager);
					popupWindow.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);
					mlog.info(tag, "AlarmTime_RLayout onClick");					
				}
			});
		}else{
			layoutAlarmTime.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					AGSAlarmSettingTimePickerPopupWindow popupWindow = new AGSAlarmSettingTimePickerPopupWindow(context, fragmentManager);
					popupWindow.showAtLocation(v.getRootView(), Gravity.CENTER, 0, 0);
					mlog.info(tag, "AlarmTime_RLayout onClick");
					
				}
			});
		}
		
	}
	
	public void setAlarmFrequencyListener(RelativeLayout layoutAlarmFrequency,final FragmentManager fragmentManager){
		//final FSAl_PopupWindow popupWindow = new FSAl_PopupWindow(context,fragmentManager);
		if(DeviceProperty.isPhone()){
			layoutAlarmFrequency.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Frequency")==null){
						//TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingFrequencyOptionFragement(), "Fragment_SAlarm_Frequency", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.alpha_in, R.animator.alpha_out);
						TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction()
								,	new AlarmSettingFrequencyOptionFragement()
								,	"Fragment_SAlarm_Frequency"
								,	R.id.pFAS_RLayout_ViewFlipper_Right_FLayout
								,	R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
					}
					mlog.info(tag, "AlarmFrequency_RLayout onClick");					
				}
			});
		}else{
			layoutAlarmFrequency.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Frequency")==null){
						//TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingFrequencyOptionFragement(), "Fragment_SAlarm_Frequency", R.id.FSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
						TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction()
								,	new AlarmSettingFrequencyOptionFragement()
								,	"Fragment_SAlarm_Frequency"
								,	R.id.FAS_RLayout_Right_RLayout
								,	R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
					}
					mlog.info(tag, "AlarmFrequency_RLayout onClick");
					
				}
			});
		}
	}
	
	public void setAlarmMusicListener(RelativeLayout layoutAlarmMusic,final FragmentManager fragmentManager){
		//final FSAl_PopupWindow popupWindow = new FSAl_PopupWindow(context,fragmentManager);
		if(DeviceProperty.isPhone()){
			layoutAlarmMusic.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Music")==null){
						//TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingMusicBrowsingFragement(), "Fragment_SAlarm_Music", R.id.pFAS_RLayout_ViewFlipper_Right_FLayout, R.animator.alpha_in, R.animator.alpha_out);
						TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction()
								,	new AlarmSettingMusicBrowsingFragement()
								,	"Fragment_SAlarm_Music"
								,	R.id.pFAS_RLayout_ViewFlipper_Right_FLayout
								, 	R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
					}
					mlog.info(tag, "AlarmMusic_RLayout onClick");					
				}
			});
		}else{
			layoutAlarmMusic.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(fragmentManager.findFragmentByTag("Fragment_SAlarm_Music")==null){
						//TKBTool.animationReplaceFragment(fragmentManager.beginTransaction(), new AlarmSettingMusicBrowsingFragement(), "Fragment_SAlarm_Music", R.id.FSAl_RLayout_ViewFlipper_AlarmPage3_RLayout, R.animator.alpha_in, R.animator.alpha_out);
						TKBTool.animationReplaceNAdd2BackFragment(fragmentManager.beginTransaction()
								,	new AlarmSettingMusicBrowsingFragement()
								,	"Fragment_SAlarm_Music"
								,	R.id.FAS_RLayout_Right_RLayout
								, 	R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
					}
					mlog.info(tag, "AlarmMusic_RLayout onClick");
					
				}
			});
		}
	}

	public void setSaveButtonListener(final View fragementMainView, final FragmentManager fragmentManager) {
		
		if(DeviceProperty.isPhone()){ // Phone
			
			Button save = (Button) fragementMainView.findViewById(R.id.AlarmSettingAddEditSaveButtonPhoneView);
			save.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					// fetch data
					 enable = (Switch)fragementMainView.findViewById(R.id.AlarmSettingProfileEnableSwitchPhoneView);
					 position = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfilePositionPhoneView);
					 name = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileNameTextPhoneView);
					 time = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileTimeTextPhoneView);
					 frequency = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileFrequencyTextPhoneView);
					//TextView music = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicText);
					 uri = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicURITextPhoneView));
					 metadata = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicMetaDataTextPhoneView));
					 seekBar = ((SeekBar)fragementMainView.findViewById(R.id.AlarmSettingProfileVolumeSeekBarPhoneView));
					
					editAlarmProfile(fragementMainView, fragmentManager);
					
					TKBTool.doPopBackFragment(fragmentManager);
					
				}
				
			});
			
		}else{
			
			Button save = (Button) fragementMainView.findViewById(R.id.AlarmSettingAddEditSaveButton);
			save.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					
					// fetch data
					 enable = (Switch)fragementMainView.findViewById(R.id.AlarmSettingProfileEnableSwitch);
					 position = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfilePosition);
					 name = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileNameText);
					 time = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileTimeText);
					 frequency = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileFrequencyText);
					//TextView music = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicText);
					 uri = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicURIText));
					 metadata = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicMetaDataText));
					 seekBar = ((SeekBar)fragementMainView.findViewById(R.id.AlarmSettingProfileVolumeSeekBar));
					
					editAlarmProfile(fragementMainView, fragmentManager);
					
					TKBTool.doPopBackFragment(fragmentManager);
					
				}
				
			});
			
		}
		
		//TKBTool.animationRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
		
		
		
//		if(DeviceProperty.isPhone()){ // Phone
//			
//		}else{ // Pad
//			
//			save.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View v) {
//					
//					
//					editAlarmProfile(fragementMainView, fragmentManager);
//					
//					
//				}
//				
//			});
//			//TKBTool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
//			
//		}
		
	}
	
	private Switch enable; // = (Switch)fragementMainView.findViewById(R.id.AlarmSettingProfileEnableSwitch);
	private TextView position; // = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfilePosition);
	private TextView name; // = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileNameText);
	private TextView time; // = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileTimeText);
	private TextView frequency; // = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileFrequencyText);
	//TextView music = (TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicText);
	private TextView uri; // = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicURIText));
	private TextView metadata; // = ((TextView)fragementMainView.findViewById(R.id.AlarmSettingProfileMusicMetaDataText));
	private SeekBar seekBar; // = ((SeekBar)fragementMainView.findViewById(R.id.AlarmSettingProfileVolumeSeekBar));
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void editAlarmProfile(View fragementMainView, final FragmentManager fragmentManager){
		
		//
		Integer posText = Integer.parseInt( position.getText().toString() );
		String nameText =  name.getText().toString();
		String timeText =  time.getText().toString();
		String freqText =  frequency.getText().toString();
		//String musicText = (String) music.getText();
		String uriText = uri.getText().toString();
		String metadataText = metadata.getText().toString();
		Integer vloume = seekBar.getProgress();
		
		String stateText; // = enable.isChecked();
		if(enable.isChecked()){
			
			// if on, validate fields, Arthur TODO
			
			stateText = "On";
			
		}else{
			stateText = "Off";
		}
		
		
		AndroidUpnpService upnpServer = ((MainFragmentActivity)context).getUPnPService();
		DeviceDisplay mediaRenderDisplay = chooseDeviceDisplay;
		
		Device mediaRenderer = mediaRenderDisplay.getDevice();
		
		String alarmserviceNamespace = ServiceValues.DEFAULT_NAMESPACE;
		String alarmserviceType = AlarmServiceValues.SERVICE_NAME;
		ServiceType typeAlarmService = new ServiceType(alarmserviceNamespace, alarmserviceType);
		
		Service serviceAlarm = mediaRenderer.findService(typeAlarmService);
		if(serviceAlarm != null){
			
			Action actionCreateAlarmProfile; // = serviceAlarm.getAction(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE);
			if(posText == -1){ // Create
				actionCreateAlarmProfile = serviceAlarm.getAction(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE); 
			}else{ // Update
				actionCreateAlarmProfile = serviceAlarm.getAction(AlarmServiceValues.ACTION_UPDATE_ALARM_PROFILE); 
			}
			
			if(actionCreateAlarmProfile != null){
				
				ArrayList<ActionArgumentValue> values = new ArrayList<ActionArgumentValue>();
				
				ActionArgument argState = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_STATE);
				ActionArgumentValue valState = new ActionArgumentValue(argState, stateText);
				values.add(valState);
				
				ActionArgument argName = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_NAME);
				if(argName != null){
					ActionArgumentValue valName = new ActionArgumentValue(argName, nameText);
					values.add(valName);
				}
				
				ActionArgument argTime = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_TIME);
				ActionArgumentValue valTime = new ActionArgumentValue(argTime, timeText);
				values.add(valTime);
				
				ActionArgument argMusicURI = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_URI);
				ActionArgumentValue vaURIl = new ActionArgumentValue(argMusicURI, uriText);
				values.add(vaURIl);
				
				ActionArgument argMusicMetaData = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_METADATA);
				ActionArgumentValue valMusicMetaData = new ActionArgumentValue(argMusicMetaData, metadataText);
				values.add(valMusicMetaData);
				
				ActionArgument argVolume = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_VOLUME);
				ActionArgumentValue valVolume = new ActionArgumentValue(argVolume, vloume);
				values.add(valVolume);
				
				ActionArgument argFrequency = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_FREQUENCY);
				ActionArgumentValue valFrequency = new ActionArgumentValue(argFrequency, freqText);
				values.add(valFrequency);
				
				ActionArgument argPosition = actionCreateAlarmProfile.getInputArgument(AlarmServiceValues.ACTION_CREATE_ALARM_PROFILE_INPUT_POSITION);
				if(argPosition != null){
					ActionArgumentValue valPosition = new ActionArgumentValue(argPosition, posText);
					values.add(valPosition);
				}
				
				ActionInvocation invokeGetAlarmProfileList = new ActionInvocation(actionCreateAlarmProfile , values.toArray(new ActionArgumentValue[values.size()]));
				ActionCallback callbackGetAlarmProfileList = new ActionCallback(invokeGetAlarmProfileList){

					@Override
					public void failure(ActionInvocation ai, UpnpResponse ur, String str) {
						// error log
						// show message
						Log.d(tag, "callbackSystemInfo failure - ");
						Log.d(tag, "UPnPResponse : " + ur + ", other messgae : " + str);
					}

					@Override
					public void success(ActionInvocation ai) {
						
						// update done
						ActionArgumentValue output = ai.getOutput(AlarmServiceValues.ACTION_GET_ALARM_PROFILE_LIST_OUTPUT);
						String infoSystem = (String) output.getValue();
						Log.d(tag, "AlarmProfileList : " + infoSystem);
						
						// AlarmItemContent
						// dataList
						
						//TKBTool.FragmentActivity_MainRemoveFragment(fragmentManager, "Fragment_SAlarm_EditAdd", R.animator.alpha_in, R.animator.alpha_out);
						TKBTool.doPopBackFragment(fragmentManager);
					
					}
					
				};
				upnpServer.getControlPoint().execute(callbackGetAlarmProfileList);
				
			}else{
				// error log
				// show message
			}
			
		}else{
			// error log
			// show message
		}
		
	}
	
}

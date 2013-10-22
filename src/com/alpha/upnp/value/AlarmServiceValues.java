package com.alpha.upnp.value;

public class AlarmServiceValues extends ServiceValues {

	public static final String SERVICE_NAME = "Alarm";
	
	public static final String DEFAULT_OUTPUT = "AlarmProfileList";
	
	public static final String ACTION_GET_ALARM_PROFILE_LIST = "GetAlarmProfileList";
	public static final String ACTION_GET_ALARM_PROFILE_LIST_OUTPUT = DEFAULT_OUTPUT;
	
	public static final String ACTION_CREATE_ALARM_PROFILE = "CreateAlarmProfile";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_POSITION = "AlarmProfilePosition";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_NAME = "AlarmProfileName";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_STATE = "AlarmState";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_TIME = "AlarmTime";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_URI = "AlarmMusicURI";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_METADATA = "AlarmMusicMetaData";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_VOLUME = "AlarmVolume";
	public static final String ACTION_CREATE_ALARM_PROFILE_INPUT_FREQUENCY = "AlarmFrequency";
	public static final String ACTION_CREATE_ALARM_PROFILE_OUTPUT = DEFAULT_OUTPUT;
	
	public static final String ACTION_UPDATE_ALARM_PROFILE = "UpdateAlarmProfile";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_STATE = "AlarmState";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_TIME = "AlarmTime";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_URI = "AlarmMusicURI";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_METADATA = "AlarmMusicMetaData";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_VOLUME = "AlarmVolume";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_FREQUENCY = "AlarmFrequency";
	public static final String ACTION_UPDATE_ALARM_PROFILE_INPUT_POSITION = "AlarmProfilePosition";
	public static final String ACTION_UPDATE_ALARM_PROFILE_OUTPUT = DEFAULT_OUTPUT;
	
	public static final String ACTION_DELETE_ALARM_PROFILE = "DeleteAlarmProfile";
	public static final String ACTION_DELETE_ALARM_PROFILE_INPUT_POSITION = "AlarmProfilePosition";
	public static final String ACTION_DELETE_ALARM_PROFILE_OUTPUT = DEFAULT_OUTPUT;
	
}

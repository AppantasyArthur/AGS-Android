package com.alpha.setting.alarm;

import java.util.List;

import org.teleal.cling.support.contentdirectory.DIDLParser;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.item.Item;

public class AlarmItemContent {
	
	public static final String TAG = "AlarmProfile";
	
	
	public static final String prtPosition = "alarm_position";
	public static final String prtState = "alarm_state";
	public static final String prtName = "alarm_name";
	
	public static final String prtTime = "alarm_time";
	public static final String prtFrequency = "alarm_frequency";
	public static final String prtVolume = "alarm_volume";
	
	public static final String prtURI = "alarm_music_uri";
	public static final String prtMetaData = "alarm_music_metadata";
	
	private String state = "On";
	private String time = "00:00 AM";
	private String uri = "N/A";
	private String metaData = "N/A";
	private Integer volume = 0;
	private String freaquency = "Once";
	private Integer position = 0;
	
	private String hour = "00";
	private String minute = "00";
	private String AMPM = "";
	
	private String nameAlarm = "Alarm Name";
	
	private String music = "N/A";
	
	public AlarmItemContent(){
	}
	
	public AlarmItemContent(String state, String time, String uri, String metaData, Integer volume, String freaquency, Integer position) {

		this.state = state;
		
		//this.time = time;
		String[] times = time.split("[: ]");
		hour = times[0];
		minute = times[1];
		AMPM = times[2];
		
		this.uri = uri;
		this.metaData = metaData;
		this.volume = volume;
		this.freaquency = freaquency;
		this.position = position;
		
	}
	
	private final String STA_ON = "ALARMSTATETYPE_E_ON";
	private final String STA_OFF = "ALARMSTATETYPE_E_OFF";
	private final String DISP_STA_ON = "On";
	private final String DISP_STA_OFF = "Off";
	public String getState() {
		return state;
		
//		if(state.equalsIgnoreCase(DISP_STA_ON)){
//			return STA_ON;
//		}else if(state.equalsIgnoreCase(DISP_STA_OFF)){
//			return STA_OFF;
//		}else{
//			return state;
//		}
		
	}
	
	public void setState(String state) {
		
		if(state.equalsIgnoreCase(STA_ON)){
			state = DISP_STA_ON;
		}else if(state.equalsIgnoreCase(STA_OFF)){
			state = DISP_STA_OFF;
		}
			
		this.state = state;
		
	}

	public String getTime() {
		//return time;
		return hour + ":" + minute + " " + AMPM;
	}
	public void setTime(String time) {
		String[] times = time.split("[: ]");
		this.setHour(times[0]);
		this.setMinute(times[1]);
		this.time = time;
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMetaData() {
		return metaData;
	}
	public void setMetaData(String metaData) {
		
		//Log.d(this.toString(), metaData);
		this.metaData = metaData;
		
		DIDLParser parser = new DIDLParser();
		try {
			
			// parsing meta data
			DIDLContent content = parser.parse(metaData);
			
			// set to music
			List<Item> items = content.getItems();
			if(items != null && items.size() > 0){
				
				String title = items.get(0).getTitle();
				setMusic(title);
				
			}else{
				setMusic("N/A");
			}
			
		} catch (Exception e) {
			setMusic("N/A");
			e.printStackTrace();
		}
		
	}

	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	private static final String FRQ_ONCE = "ALARMFREQUENCYTYPE_E_ONCE"; 
	private static final String FRQ_MON = "ALARMFREQUENCYTYPE_E_MONDAY"; 
	private static final String FRQ_TUE = "ALARMFREQUENCYTYPE_E_TUESDAY"; 
	private static final String FRQ_WED = "ALARMFREQUENCYTYPE_E_WEDNESDAY"; 
	private static final String FRQ_THU = "ALARMFREQUENCYTYPE_E_THURSDAY"; 
	private static final String FRQ_FRI = "ALARMFREQUENCYTYPE_E_FRIDAY"; 
	private static final String FRQ_SAT = "ALARMFREQUENCYTYPE_E_SATURDAY"; 
	private static final String FRQ_SUN = "ALARMFREQUENCYTYPE_E_SUNDAY"; 
	
	public static final String DISP_FRQ_ONCE = "Once";
	public static final String DISP_FRQ_MON = "Every Monday";
	public static final String DISP_FRQ_TUE = "Every Tuesday";
	public static final String DISP_FRQ_WED = "Every Wednesday";
	public static final String DISP_FRQ_THU = "Every Thursday";
	public static final String DISP_FRQ_FRI = "Every Friday";
	public static final String DISP_FRQ_SAT = "Every Saturday";
	public static final String DISP_FRQ_SUN = "Every Sunday";
	public String getFreaquency() {
		return freaquency;
	}
	public void setFreaquency(String freaquency) {
		
		if(freaquency.equalsIgnoreCase(FRQ_ONCE)){
			freaquency = DISP_FRQ_ONCE;
		}else if(freaquency.equalsIgnoreCase(FRQ_MON)){
			freaquency = DISP_FRQ_MON;
		}else if(freaquency.equalsIgnoreCase(FRQ_TUE)){
			freaquency = DISP_FRQ_TUE;
		}else if(freaquency.equalsIgnoreCase(FRQ_WED)){
			freaquency = DISP_FRQ_WED;
		}else if(freaquency.equalsIgnoreCase(FRQ_THU)){
			freaquency = DISP_FRQ_THU;
		}else if(freaquency.equalsIgnoreCase(FRQ_FRI)){
			freaquency = DISP_FRQ_FRI;
		}else if(freaquency.equalsIgnoreCase(FRQ_SAT)){
			freaquency = DISP_FRQ_SAT;
		}else if(freaquency.equalsIgnoreCase(FRQ_SUN)){
			freaquency = DISP_FRQ_SUN;
		} 
		
		this.freaquency = freaquency;
		
	}

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}

	public AlarmItemContent(String hour, String minute, String AMPM, String freaquency, String nameAlarm) {
		
		//super();
		
		this.hour = hour;
		this.minute = minute;
		this.AMPM = AMPM;
		this.freaquency = freaquency;
		this.nameAlarm = nameAlarm;
		
	}
	
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getAMPM() {
		return AMPM;
	}
	public void setAMPM(String AMPM) {
		this.AMPM = AMPM;
	}
	public String getNameAlarm() {
		return nameAlarm;
	}
	public void setNameAlarm(String nameAlarm) {
		this.nameAlarm = nameAlarm;
	}

	public static int getFrequencyOptionPosition(String freq) {

		if(freq.equalsIgnoreCase(DISP_FRQ_ONCE))
			return 0;
		else if(freq.equalsIgnoreCase(DISP_FRQ_MON))
			return 1;
		else if(freq.equalsIgnoreCase(DISP_FRQ_TUE))
			return 2;
		else if(freq.equalsIgnoreCase(DISP_FRQ_WED))
			return 3;
		else if(freq.equalsIgnoreCase(DISP_FRQ_THU))
			return 4;
		else if(freq.equalsIgnoreCase(DISP_FRQ_FRI))
			return 5;
		else if(freq.equalsIgnoreCase(DISP_FRQ_SAT))
			return 6;
		else if(freq.equalsIgnoreCase(DISP_FRQ_SUN))
			return 7;
		else
			return -1;
		
	}
	
	public static CharSequence getFrequencyOptionText(int position) {
	
		switch(position){
			case 0:
				return AlarmItemContent.DISP_FRQ_ONCE;
			case 1:
				return AlarmItemContent.DISP_FRQ_MON;
			case 2:
				return AlarmItemContent.DISP_FRQ_TUE;
			case 3:
				return AlarmItemContent.DISP_FRQ_WED;
			case 4:
				return AlarmItemContent.DISP_FRQ_THU;
			case 5:
				return AlarmItemContent.DISP_FRQ_FRI;
			case 6:
				return AlarmItemContent.DISP_FRQ_SAT;
			case 7:
				return AlarmItemContent.DISP_FRQ_SUN;
			default:
				return "N/A";
		}
	
	}

}

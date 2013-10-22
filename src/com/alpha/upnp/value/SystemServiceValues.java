package com.alpha.upnp.value;

public class SystemServiceValues extends ServiceValues {

	public static final String SERVICE_NAME = "System";
	
	public static final String ACTION_WIRELESS_SETUP = "SetupWireless";
	public static final String ACTION_WIRELESS_SETUP_INPUT_SSID = "AP_SSID";
	public static final String ACTION_WIRELESS_SETUP_INPUT_PWD = "AP_Password";
	
	public static final String ACTION_SYSTEM_INFO = "GetSystemInfo";
	public static final String ACTION_SYSTEM_INFO_OUTPUT = "SystemInfo";
	
	public static final String ACTION_IDENTIFY_SPEAKER = "IdentifySpeaker";
	public static final String ACTION_IDENTIFY_SPEAKER_OUTPUT = DEFAULT_OUTPUT_RESULT;
	
	public static final String ACTION_SLEEP_TIMER_GET = "GetAutoSleepTimer";
	public static final String ACTION_SLEEP_TIMER_SET = "SetAutoSleepTimer";

	public static final String ACTION_SLEEP_TIMER_INPUT_SLEEPTIMEROPTION = "SleepTimerOption";
	
	public static enum SleepTimerOptions{
		
		
		
//		Off("Off"),
//		Minutes15("15 Minutes"),
//		Minutes30("30 Minutes"),
//		Minutes45("45 Minutes"),
//		Hour1("1 Hour"),
//		Hours2("2 Hours"),
//		Hours3("3 Hours");
		
//		private String text;
//		public String getText(){
//			return text;
//		}
//		
//		SleepTimerOptions(String text){
//			this.text = text;
//		}
		
	}

	public static String getSleepTimerOptionsText(int option){
		
		switch(option){
		
		case 0:
			return "Off";
			
		case 1:
			return "15 Minutes";
			
		case 2:
			return "30 Minutes";
			
		case 3:
			return "45 Minutes";
			
		case 4:
			return "1 Hour";
			
		case 5:
			return "2 Hours";
			
		case 6:
			return "3 Hours";
			
		default:
			return "N/A";
		
		}
		
	}
	
	public static int getSleepTimerOptions(String result) {
		
		if(result.equalsIgnoreCase("Off")){
			return 0;
		}else if(result.equalsIgnoreCase("15 Minutes")){
			return 1;
		}else if(result.equalsIgnoreCase("30 Minutes")){
			return 2;
		}else if(result.equalsIgnoreCase("45 Minutes")){
			return 3;
		}else if(result.equalsIgnoreCase("1 Hour")){
			return 4;
		}else if(result.equalsIgnoreCase("2 Hours")){
			return 5;
		}else if(result.equalsIgnoreCase("3 Hours")){
			return 6;
		}else{
			return -1;
		}
		
	}
	
	
	//public static String EVENT_
	
}

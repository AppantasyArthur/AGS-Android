package com.FSAl_List.SETTING;

public class AlarmItemContent {
	private String TimeH ="";
	private String TimeM ="";
	private String TimeAP ="";
	private int Freaquency =-1;
	private String AlarmNmae ="";
	public AlarmItemContent(){
		
	}
	public AlarmItemContent(String TimeH,String TimeM,String TimeAP,int Freaquency,String AlarmNmae){
		this.TimeH = TimeH;
		this.TimeM = TimeM;
		this.TimeAP = TimeAP;
		this.Freaquency = Freaquency;
		this.AlarmNmae = AlarmNmae;
	}
	public String getTimeH() {
		return TimeH;
	}
	public void setTimeH(String timeH) {
		TimeH = timeH;
	}
	public String getTimeM() {
		return TimeM;
	}
	public void setTimeM(String timeM) {
		TimeM = timeM;
	}
	public String getTimeAP() {
		return TimeAP;
	}
	public void setTimeAP(String timeAP) {
		TimeAP = timeAP;
	}
	public int getFreaquency() {
		return Freaquency;
	}
	public void setFreaquency(int freaquency) {
		Freaquency = freaquency;
	}
	public String getAlarmNmae() {
		return AlarmNmae;
	}
	public void setAlarmNmae(String alarmNmae) {
		AlarmNmae = alarmNmae;
	}
	
	
}

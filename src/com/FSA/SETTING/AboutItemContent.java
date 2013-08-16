package com.FSA.SETTING;

public class AboutItemContent {
	private String leftString ="";
	private String rightString ="";
	public AboutItemContent(){
		
	}
	public AboutItemContent(String LeftString,String RightString){
		leftString = LeftString;
		rightString = RightString;
	}
	public String getLeftString() {
		return leftString;
	}
	public void setLeftString(String leftString) {
		this.leftString = leftString;
	}
	public String getRightString() {
		return rightString;
	}
	public void setRightString(String rightString) {
		this.rightString = rightString;
	}
	
}

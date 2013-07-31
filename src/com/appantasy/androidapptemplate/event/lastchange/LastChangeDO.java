package com.appantasy.androidapptemplate.event.lastchange;

public class LastChangeDO {

	public String getAVTransportURIMetaData() {
		return AVTransportURIMetaData;
	}

	public void setAVTransportURIMetaData(String AVTransportURIMetaData) {
		this.AVTransportURIMetaData = AVTransportURIMetaData;
	}

	public static String TRANSPORT_STATE = "TransportState";
	public static String CURRENT_TRACK_DURATION = "CurrentTrackDuration";
	public static String RELATIVE_TIME_POSITION = "RelativeTimePosition";
	
	public enum E_TRANSPORT_STATE{
		
		STOPPED("STOPPED"),
		PLAYING("PLAYING"),
		PAUSED("PAUSED");
		
		private String event_value;
		private E_TRANSPORT_STATE(String event_value){
			this.event_value = event_value;
		}
		
		public String getEventValue(){
			return event_value;
		}
		
	};
	
	private String transportState;
	private String currentTrackDuration;
	private String relativeTimePosition;
	private String CurrentPlayMode;
	private String currentTrackURI;
//	private String currentTrackMetaData;
	private String AVTransportURIMetaData;
	
	
	public String getCurrentTrackURI() {
		return currentTrackURI;
	}

	public void setCurrentTrackURI(String currentTrackURI) {
		this.currentTrackURI = currentTrackURI;
	}

//	public String getCurrentTrackMetaData() {
//		return currentTrackMetaData;
//	}
//
//	public void setCurrentTrackMetaData(String currentTrackEmbeddedMetaData) {
//		this.currentTrackMetaData = currentTrackEmbeddedMetaData;
//	}

	public String getTransportState() {
		return transportState;
	}

	public void setTransportState(String transportState) {
		this.transportState = transportState;
	}

	public String getCurrentTrackDuration() {
		return currentTrackDuration;
	}

	public void setCurrentTrackDuration(String currentTrackDuration) {
		this.currentTrackDuration = currentTrackDuration;
	}

	public String getRelativeTimePosition() {
		return relativeTimePosition;
	}

	public void setRelativeTimePosition(String relativeTimePosition) {
		this.relativeTimePosition = relativeTimePosition;
	}
	
	public String getCurrentPlayMode() {
		return CurrentPlayMode;
	}

	public void setCurrentPlayMode(String currentPlayMode) {
		CurrentPlayMode = currentPlayMode;
	} 

	// I know this could be an int, but this is just to show you how it works   
	public String sectionId;   
	public String section;   
	public String area;
	
	@Override
	public String toString() {
		
		StringBuffer ret = new StringBuffer();
		
		return ret.toString();
		
	}

	
	
	
	
}

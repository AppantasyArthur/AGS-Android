package com.appantasy.androidapptemplate.event.lastchange;

public class SoundLastChangeDO {
	
	private Integer Volume;
	private String Mute;	
	
	public Integer getVolume() {
		return Volume;
	}

	public void setVolume(String Volume) {
		this.Volume = Integer.parseInt(Volume);
	}

	public String getMute() {
		return Mute;
	}

	public void setMute(String Mute) {
		this.Mute = Mute;
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

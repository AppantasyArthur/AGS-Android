package com.alpha.upnp.value;

public class AGSHandlerMessages {

	public static final int CLOSE_GENERAL_PROGRESS = -2;
	public static final int SHOW_GENERAL_PROGRESS = -1;
	public static final int SHOW_MESSAGE = 0;
	
	public static final int SET_FREQUENCY = 1;
	public static final int SET_MUSIC = 2;
	public static final int SET_URI = 3;
	public static final int SET_METADATA = 4;
	
	public static class AGSMessageBody{
		
		private String title;
		private String content;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	}

}

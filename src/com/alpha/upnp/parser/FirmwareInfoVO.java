package com.alpha.upnp.parser;

public class FirmwareInfoVO {

	public static final String TAG_FIRMWARE_INFO = "FirmwareInfo";
	
	public static final String PRPTY_PATH = "path";
	public static final String PRPTY_NEW_VERSION = "new_version";
	public static final String PRPTY_CUR_VERSION = "cur_version";
	public static final String PRPTY_TYPE = "type";
	
	private String path;
	private String newVersion;
	private String curVersion;
	private String type;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
	public String getCurVersion() {
		return curVersion;
	}
	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}

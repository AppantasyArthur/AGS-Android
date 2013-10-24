package com.alpha.musicinfo;

public interface MusicInfo_Listner {
	public void ClearMusicInfo_State();
	public void SetMusicInfo_State(String Title,String Artist,String Album,String Genre, String albumURI);
	public void SetPositionChange();
	public void MediaRendererCountChange();
	
}

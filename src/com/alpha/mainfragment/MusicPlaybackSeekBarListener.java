package com.alpha.mainfragment;

public interface MusicPlaybackSeekBarListener {
	public void setSeekTime(Long secondTotal,Long secondRun,String stringTotal,String stringRun);
	public int getElapsedTime();
}

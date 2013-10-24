package com.alpha.musicinfo;
import java.util.List;
import org.teleal.cling.support.model.item.Item;

import com.alpha.upnp.parser.TrackDO;

public interface MusicInfoQueueListViewBaseAdapterListener {
	public void cleanQueueList();
	public void setQueueList(List<TrackDO> trackList);	
}

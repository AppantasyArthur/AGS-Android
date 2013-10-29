package com.alpha.upnp.device;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.model.types.UnsignedIntegerFourBytes;
import org.teleal.cling.support.contentdirectory.AbstractContentDirectoryService;
import org.teleal.cling.support.contentdirectory.ContentDirectoryException;
import org.teleal.cling.support.contentdirectory.DIDLParser;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.BrowseResult;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.DIDLObject;
import org.teleal.cling.support.model.PersonWithRole;
import org.teleal.cling.support.model.Res;
import org.teleal.cling.support.model.SortCriterion;
import org.teleal.cling.support.model.container.StorageFolder;
import org.teleal.cling.support.model.item.MusicTrack;
import org.teleal.common.util.MimeType;

import com.alpha.upnpui.MainFragmentActivity;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

public class AGSMediaServerContentDirectoryService extends
		AbstractContentDirectoryService {
	
	public static final String ROOT = "/storage/sdcard0";
	private final String ROOT_ID = "0";
	private final String MUSIC_ID = "0_1";
	private final String MUSIC = "Music";
	private final String MUSIC_ROOT = ROOT + "/" + MUSIC + "/";
	
	@Override
	public BrowseResult browse(String idObject, BrowseFlag flagBrowse, String filter,
			long indexStarting, long countRequest, SortCriterion[] criteriaSort)
			throws ContentDirectoryException {
		
		if(flagBrowse == BrowseFlag.DIRECT_CHILDREN){
			return getDirectChildren(idObject, criteriaSort);
		}else if(flagBrowse == BrowseFlag.METADATA){
			return gerBrowseMetaData(idObject);
		}
		
		return null;
		
	}

	private BrowseResult gerBrowseMetaData(String idObject) {
		
//		String result = "";
//		UnsignedIntegerFourBytes count = new UnsignedIntegerFourBytes(0);
//		UnsignedIntegerFourBytes totalMatches = new UnsignedIntegerFourBytes(0);
//		UnsignedIntegerFourBytes containerUpdateID = new UnsignedIntegerFourBytes(0);
//		
//		BrowseResult br = new BrowseResult(result, count, totalMatches, containerUpdateID);
//		return br;
		
		return getSubFolders(idObject);
		
	}

	private BrowseResult getDirectChildren(String idObject,
			SortCriterion[] criteriaSort) {
		
		BrowseResult br; // = new BrowseResult(result, count, totalMatches, containerUpdateID);
		
		String[] folders = idObject.split("_");
		
		if(idObject.equalsIgnoreCase("0") && folders.length == 1){ // root folder
			br = getRootFolders();
		}else if(folders.length > 1){ // sub folders
			br = getSubFolders(idObject);
		}else{
			br = new BrowseResult("", 0, 0, -1);
		}
		
		return br;
		
	}

	private BrowseResult getSubFolders(String idObject) {
		
		// searching level
		BrowsingPathResult path = getBrowsingPath(idObject);
//		File currentDir = new File(path.path);
		
		// collect data
//		File[] children = currentDir.listFiles();
		
		return collectChildrens(path);
		
	}
	private BrowseResult collectChildrens(BrowsingPathResult path) {
		
		String tag = "Browse:collectChildrens";
		
		String result = "";
		Long count = Long.valueOf(0);
		Long totalMatches = Long.valueOf(0);
		//UnsignedIntegerFourBytes containerUpdateID = new UnsignedIntegerFourBytes(0);
		
		DIDLContent ct = new DIDLContent();
		Integer id = 1;
		File currentDir = new File(path.path);
		File[] childrens = currentDir.listFiles();
		if(childrens == null){
			
			ct.addItem(getMusicTrack(currentDir));
			id = 2;
			
		}else{
			
			for(File children : childrens){
				
				if(	!children.exists()
				||	!children.canRead()
				//||	!children.canWrite()
				||	children.isHidden()){
					continue;
				}
				
				if(children.isDirectory()){
					
					StorageFolder container = new StorageFolder();
					container.setId(path.preID + "_" + id++);
					container.setTitle(children.getName());
					container.setParentID(path.parentID);
					container.setChildCount(children.listFiles().length);
					
					List<DIDLObject.Class> searchClasses = new ArrayList<DIDLObject.Class>();
					//searchClasses.add(MusicAlbum.CLASS);
					searchClasses.add(MusicTrack.CLASS);
					searchClasses.add(StorageFolder.CLASS);
					container.setSearchClasses(searchClasses);
					
					container.setStorageUsed(children.getTotalSpace() - children.getUsableSpace());
					
					ct.addContainer(container);
					
				}else{
					
					MediaMetadataRetriever mediaMetadataRetriever = (MediaMetadataRetriever) new MediaMetadataRetriever();
			        Uri uri = (Uri) Uri.fromFile(children);
			        mediaMetadataRetriever.setDataSource(MainFragmentActivity.getContext(), uri);
			        
			        String title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
			        String artist =  mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
					String album = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
					String creator = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR); // Required
					String mimeType = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
					
					Log.d(tag, "title : " + title);
					Log.d(tag, "artist : " + artist);
					Log.d(tag, "album : " + album);
					Log.d(tag, "creator : " + creator);
					Log.d(tag, "mimeType : " + mimeType);
					Log.d(tag, "uri : " + uri.toString());
					
					if(title == null){
						title = children.getName();
						Log.d(tag, "change title : " + title);
					}
					
					String[] mimeTypes = mimeType.split("/");
			        MimeType typeMIME = new MimeType(mimeTypes[0], mimeTypes[1]);
			        
			        String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
			        String bitrate = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
			        
					ct.addItem(new MusicTrack(
							path.preID + "_" + id++, path.parentID, 
							title,
			                creator, album, artist,
			                new Res(typeMIME, children.length(), duration, Long.valueOf(bitrate), getURL(uri.toString()))
			        ));
					
				}
			
			}
			
		}
		
		DIDLParser p = new DIDLParser();
		try {
			
			result = p.generate(ct);
			count = Long.valueOf(id - 1);
			totalMatches = Long.valueOf(id - 1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BrowseResult(result, count, totalMatches);
		
	}
	
	private MusicTrack getMusicTrack(File children){
		
		String tag = "Browse:getMusicTrack";
		
		MediaMetadataRetriever mediaMetadataRetriever = (MediaMetadataRetriever) new MediaMetadataRetriever();
        Uri uri = (Uri) Uri.fromFile(children);
        mediaMetadataRetriever.setDataSource(MainFragmentActivity.getContext(), uri);
        
        String title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist =  mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
		String album = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
		String creator = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR); // Required
		String mimeType = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
		
		String genre = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
		String albumArtURI = "";
		
		Log.d(tag, "title : " + title);
		Log.d(tag, "artist : " + artist);
		Log.d(tag, "album : " + album);
		Log.d(tag, "creator : " + creator);
		Log.d(tag, "mimeType : " + mimeType);
		Log.d(tag, "uri : " + uri.toString());
		
		if(title == null){
			title = children.getName();
			Log.d(tag, "change title : " + title);
		}
		
		String[] mimeTypes = mimeType.split("/");
        MimeType typeMIME = new MimeType(mimeTypes[0], mimeTypes[1]);
        
        String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        String bitrate = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
        Log.d(tag, "duration : " + duration.toString());
        Log.d(tag, "bitrate : " + bitrate.toString());
        
        Res r = new Res(typeMIME, children.length(), duration, Long.valueOf(bitrate), getURL(uri.toString()));
        //r.setDuration(duration);
        MusicTrack mt = new MusicTrack(
				"-1", "-1", 
				title,
                creator, album, artist,
                r);
        
        String[] gs = new String[1];
        gs[0] = genre;
        mt.setGenres(gs);
        
        //mt.se
        
        return mt ;
        
//		ct.addItem(new MusicTrack(
//				path.preID + "_" + id++, path.parentID, 
//				title,
//                creator, album, artist,
//                new Res(typeMIME, children.length(), duration, Long.valueOf(bitrate), getURL(uri.toString()))
//        ));
		
	}
	private String getURL(String uri) {
		
		Log.d("Browse:getURL", "uri : " + uri);
		String[] r = uri.split("/");
		//System.out.println(r);
		
		StringBuffer sb = new StringBuffer();
		for(int i = 5;i < r.length;i++){
			
			sb.append("/" + r[i]);
			
		}
		//System.out.println(sb.toString());
		return "http://" + MainFragmentActivity.formatedIpAddress + ":" + MainFragmentActivity.port + sb.toString();
		
	}

	private BrowsingPathResult getBrowsingPath(String idObject) {
		
		String[] folders = idObject.split("_");
		StringBuffer path = new StringBuffer("/storage/sdcard0/");
		
		String parentID = "0";
		boolean hasNext = false;
		for(int i = 1;i < folders.length;i++){
			
			if(hasNext){ 
				
				int j = 1;
				File file = new File(path.toString());
				for (File temp : file.listFiles()) {
					
					//temp.
					
					if(folders[i].equalsIgnoreCase(String.valueOf(j))){
						path = new StringBuffer(temp.getAbsolutePath());
						parentID = String.valueOf(j);
						break;
					}
					
					j++;
					
				}
				
			}else{ // first run, root, id == 1, 2, 3
				
				hasNext = true;
				
				if(folders[i].equalsIgnoreCase("1")){
					path.append("Music/");
				}
				
			}
			
		}
		
		return new BrowsingPathResult(path.toString(), parentID, idObject);
		
	}
	private class BrowsingPathResult{
		
		private String path;
		private String parentID;
		private String preID;
		
		public BrowsingPathResult(String path, String parentID, String preID){
			this.path = path;
			this.parentID = parentID;
			this.preID = preID;
		}
		
	}

	private BrowseResult getRootFolders() {
		
		String result = "";
		Long count = Long.valueOf(0);
		Long totalMatches = Long.valueOf(0);
		//UnsignedIntegerFourBytes containerUpdateID = new UnsignedIntegerFourBytes(0);
		
		File rootMusic = new File(MUSIC_ROOT);
		
		StorageFolder music = new StorageFolder();
		music.setId(MUSIC_ID);
		music.setTitle(MUSIC);
		music.setParentID(ROOT_ID);
		music.setChildCount(rootMusic.listFiles().length);
		
		List<DIDLObject.Class> searchClasses = new ArrayList<DIDLObject.Class>();
		//searchClasses.add(MusicAlbum.CLASS);
		searchClasses.add(MusicTrack.CLASS);
		searchClasses.add(StorageFolder.CLASS);
		music.setSearchClasses(searchClasses);
		
		music.setStorageUsed(rootMusic.getTotalSpace() - rootMusic.getUsableSpace());
		
		DIDLContent c = new DIDLContent();
		c.addContainer(music);
		
		DIDLParser p = new DIDLParser();
		try {
			result = p.generate(c);
			count = Long.valueOf(1);
			totalMatches = Long.valueOf(1);
			//containerUpdateID = new UnsignedIntegerFourBytes(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return new BrowseResult(result, count, totalMatches);
		return new BrowseResult(result, count, totalMatches);
		
	}

}

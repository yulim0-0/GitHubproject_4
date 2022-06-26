package com.sist.data;

import java.io.Serializable;

/*
 *   m.setMno(mno);
     m.setCno(cno);
     m.setTitle(title.get(j).text());
     m.setSinger(singer.get(j).text());
     m.setAlbum(ablum.get(j).text());
     m.setPoster(poster.get(j).attr("src"));
     m.setState(state);
     m.setIdcrement(id);
     m.setKey(youtubeGetKey(title.get(j).text()));
 */
public class Music implements Serializable{
    private int mno,cno,idcrement;
    private String title,singer,album,poster,state,key;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getIdcrement() {
		return idcrement;
	}
	public void setIdcrement(int idcrement) {
		this.idcrement = idcrement;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
  
}
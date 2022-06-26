package com.sist.data;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class MusicData {

	public static void musicAllData()
    {
    	
    	ArrayList<Music> list=new ArrayList<Music>();
    	String[] urls={
    		"https://www.genie.co.kr/chart/top200?ditc=D&ymd=20220615&hh=11&rtm=Y&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0100&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0200&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0300&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0107&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0500&pg=",
    		"https://www.genie.co.kr/chart/genre?ditc=D&ymd=20220613&genrecode=M0600&pg="
    	};
    	try
    	{
    		int mno=1;// 고유번호
    		int cno=1;// 카테고리 번호 
    		for(String url:urls)
    		{
    			System.out.println(url);
    			for(int i=1;i<=2;i++)
    			{
    				// 데이터가 없는 경우 스킵 
    			    
    				Document doc=Jsoup.connect(url+i).get();
    				Elements title=doc.select("table.list-wrap td.info a.title");
    				//System.out.println(title);
    				Elements singer=doc.select("table.list-wrap td.info a.artist");
    				Elements ablum=doc.select("table.list-wrap td.info a.albumtitle");
    				Elements poster=doc.select("a.cover img");
    				Elements etc=null;
    				if(cno==1)
    				    etc=doc.select("tr.list span.rank span.rank");
    				else
    					etc=doc.select("tr.list span.rank");
    				
    				for(int j=0;j<title.size();j++)
    				{
    					// etc
    					/*
    					 *     5상승 
    					 *     2하강
    					 *     유지
    					 *     new 
    					 */
    					  String state="";
    					  int id=0;
    					  String etc_data=etc.get(j).text();
    					  state=etc_data.replaceAll("[0-9]","");
    					  System.out.println(state);
    					  if(state.equals("유지") || state.equals("new"))
    						  id=0;
    					  else
    						  id=Integer.parseInt(etc_data.replaceAll("[가-힣]",""));
    					  
    				  try {
    					   
    					    System.out.println(
    						   mno+" "
	    					   +title.get(j).text()+" "
	    					   +singer.get(j).text()+" "
	    					   +ablum.get(j).text()+" "
	    					   +poster.get(j).attr("src")+" "
							   +state+" "+id
							   
	    					  );
    					    
    					   Music m=new Music();
    					   m.setMno(mno);
    					   m.setCno(cno);
    					   m.setTitle(title.get(j).text());
    					   m.setSinger(singer.get(j).text());
    					   m.setAlbum(ablum.get(j).text());
    					   m.setPoster(poster.get(j).attr("src"));
    					   m.setState(state);
    					   m.setIdcrement(id);
    					   m.setKey(youtubeGetKey(title.get(j).text()));
    					   list.add(m);
    					   
    				  }catch(Exception ex){}
    				  mno++;
    				}
    				System.out.println("===================");
    			  
    			}
    			cno++;
    			System.out.println("cno="+cno);
    			
    			
    		}
    		  FileOutputStream fos=
			     new FileOutputStream("c:\\java_data\\music.txt");
			  ObjectOutputStream oos=
					new ObjectOutputStream(fos);
			  oos.writeObject(list);
			  oos.close();
			  fos.close();
	          System.out.println("데이터 저장 완료!!");
    	}catch(Exception ex){}
    }
   public static String youtubeGetKey(String title)
	{
		String key="";
		try
		{
			Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
			Pattern p=Pattern.compile("/watch\\?v=[^가-힣]+");
			// /watch?v=(숫자,알파벳,특수문자) +(여러개문자를 읽어 올때)
			Matcher m=p.matcher(doc.toString());
			// /watch?v=47JjBTbI6P0"
			while(m.find())// 시작하는 문자열을 찾은 경우 
			{
				String s=m.group();//찾은 문장을 읽어 온다 
				key=s.substring(s.indexOf("=")+1,s.indexOf("\""));
				break;
			}
		}catch(Exception ex){}
		return key;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        musicAllData();
	}

}
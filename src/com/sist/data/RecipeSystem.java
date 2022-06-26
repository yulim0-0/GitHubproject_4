package com.sist.data;

import java.util.ArrayList;
import java.io.*;
// DAO => 웹(오라클) 
public class RecipeSystem {
   private static ArrayList<RecipeVO> list=
		          new ArrayList<RecipeVO>();
   /*
    *   private static ArrayList<RecipeVO> rList=
		          new ArrayList<RecipeVO>();
		private static ArrayList<RecipeDetailVO> dList=
		          new ArrayList<RecipeDetailVO>();
		          
		static
      {
		   try
		   {
			   FileInputStream fis=
					   new FileInputStream("c:\\java_data\\recipe.txt");
			   ObjectInputStream ois=
					   new ObjectInputStream(fis);
			   rList=(ArrayList<RecipeVO>)ois.readObject();
			   ois.close();
			   fis.close();
			   
			   fis=
					   new FileInputStream("c:\\java_data\\recipe_detail.txt");
			   ois=
					   new ObjectInputStream(fis);
			   dList=(ArrayList<RecipeDetailVO>)ois.readObject();
			   ois.close();
			   fis.close();
		   }catch(Exception ex){}
      }
    */
   // 초기화 
   static
   {
	   try
	   {
		   FileInputStream fis=
				   new FileInputStream("/Users/yulim/java_data/recipe.txt");
		   ObjectInputStream ois=
				   new ObjectInputStream(fis);
		   list=(ArrayList<RecipeVO>)ois.readObject();
		   ois.close();
		   fis.close();
	   }catch(Exception ex){}
   }
   
    public static ArrayList<RecipeVO> getList() {
	  return list;
    }
	public static ArrayList<RecipeVO> RecipeVOListData(int cno,int page)
    {
	   ArrayList<RecipeVO> cList=
			   new ArrayList<RecipeVO>();
	   // 페이지 나누기 
	   int j=0;
	   // 100  115  130
	   // 
	   int pagecnt=(page*15)-15;
	   /*
	    *    list.size ==> 700  (0~699)
	    *    cno  1 => 1
	    *    cno  2 => 100
	    *    cno  3 => 200
	    *    cno  4 => 300...
	    */
	   for(int i=0;i<list.size();i++)
	   {
		     RecipeVO m=list.get(i);
		     if(j<15 && i>=(pagecnt+((cno-1)*100)))//pagecnt
			 {
				   cList.add(m);
				   j++;
			 }
		  
	   }
	   return cList;
   }
   public static int RecipeVOToalPage()
   {
	   return (int)(Math.ceil(100/15.0));
   }
   public static void main(String[] args) {
	   ArrayList<RecipeVO> list=RecipeVOListData(7, 3);
	   for(RecipeVO m:list)
	   {
		   System.out.println(m.getNo()+"."+m.getTitle());
	   }
	   
   }
   //
   public static ArrayList<RecipeVO> RecipeVOFind(String fd)
   {
	   ArrayList<RecipeVO> fList=new ArrayList<RecipeVO>();
	   for(RecipeVO m:list)
	   {
		   if(m.getTitle().contains(fd))
		   {
			   // LIKE '%fd%'
			   fList.add(m);
		   }
	   }
	   return fList;
   }
   // 데이터 관리 => 웹(자바의 기능 => 데이터 관리)
   // 파일(자바) / 데이터베이스 (웹) => rownum
   public static ArrayList<RecipeVO> RecipeVOTop10()
   {
	   ArrayList<RecipeVO> tList=new ArrayList<RecipeVO>();
	   for(int i=0;i<10;i++)
	   {
		   int r=(int)(Math.random()*700);
		   RecipeVO m=list.get(r);
		   tList.add(m);
	   }
	   return tList;
   }
}





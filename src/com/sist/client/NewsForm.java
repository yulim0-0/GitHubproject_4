package com.sist.client;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import com.sist.data.*;
public class NewsForm extends JPanel{
   public NewsCard[] nc=new NewsCard[10];
   JLabel la=new JLabel("실시간 네이버 뉴스",JLabel.CENTER);
   //String data="뮤직";
   public JTextField tf=new JTextField(30);
   public JButton b=new JButton("검색");
   public ArrayList<News> list;
   public NewsForm()
   {
	   list=NaverNewsMain.newsAllData("레시피");
	   JPanel p=new JPanel();
	   p.setLayout(new GridLayout(10,1,5,10));
	   for(int i=0;i<nc.length;i++)
	   {
		   nc[i]=new NewsCard();
		   nc[i].la.setText(list.get(i).getTitle());
		   nc[i].ta.setText(list.get(i).getDescription());
		   p.add(nc[i]);
	   }
	   
	   setLayout(null);
	   la.setFont(new Font("돋움체",Font.BOLD,45));
	   la.setBounds(10, 15, 840, 50);
	   add(la);
	   JPanel p1=new JPanel();
	   p1.add(tf);
	   p1.add(b);
	   p1.setBounds(10, 75, 840,40);
	   add(p1);
	   
	   p.setBounds(10, 125, 840, 750);
	   add(p);
   }
   
   public void findData(String s)
   {
	   list=NaverNewsMain.newsAllData(s);
	   for(int i=0;i<nc.length;i++)
	   {
		   //nc[i]=new NewsCard();
		   nc[i].la.setText(list.get(i).getTitle());
		   nc[i].ta.setText(list.get(i).getDescription());
	   }
   }
}










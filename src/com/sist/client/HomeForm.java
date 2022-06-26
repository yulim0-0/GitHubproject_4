package com.sist.client;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.sist.data.RecipeVO;
import com.sist.data.RecipeSystem;
public class HomeForm extends JPanel{
    public JButton b1,b2; // 이전 , 다음 
    public JButton[] m=new JButton[6];
    public RecipeManager rm;
    public RecipeSystem rs=new RecipeSystem();
    public JLabel pagLa=new JLabel("0 page / 0 pages");
    public HomeForm(ControllerPanel cp)
    {
    	rm=new RecipeManager(cp);
    	b1=new JButton("이전");
    	b2=new JButton("다음");
    	JPanel p=new JPanel();
    	String[] title={"Top100","종류별","상황별","재료별","방법별","테마별"};
    	for(int i=0;i<m.length;i++)
    	{
    		m[i]=new JButton(title[i]);
    		p.add(m[i]);
    	}
    	// 배치 
    	setLayout(null);
    	p.setBounds(0, 0, 840, 35);
    	add(p);
    	rm.setBounds(0,0, 840, 780);
    	add(rm);
    	
    	JPanel p1=new JPanel();
    	p1.add(b1);
    	p1.add(pagLa);
    	p1.add(b2);
    	
    	p1.setBounds(0, 790, 840, 35);
    	add(p1);
    	
    	// 시작과 동시에 데이터를 받기 
    	ArrayList<RecipeVO> list=RecipeSystem.RecipeVOListData(1, 1);
    	rm.cardPrint(list);
    	
    }
}
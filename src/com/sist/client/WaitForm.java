package com.sist.client;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import com.sist.data.RecipeSystem;
import com.sist.data.RecipeVO;
import com.sist.main.NetworkMain;
// Network 
public class WaitForm extends JPanel{
	JTable table;
	DefaultTableModel model;
	JLabel la=new JLabel("Recipe Hit10",JLabel.CENTER);
    public WaitForm()
    {
    	String[] col={"","Title"};
    	Object[][] row=new Object[0][2];
    	// 익명의 클래스 => 상속없이 오버라이딩 => 스프링 (POJO) => 독립적
    	model=new DefaultTableModel(row,col)
    	{

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return getValueAt(0, columnIndex).getClass();
			}
    		
    	};
    	table=new JTable(model);
    	table.setRowHeight(50);
    	JScrollPane js1=new JScrollPane(table);
    	
    	
    	// 배치
    	setLayout(null);// 사용자 정의
    	
    	la.setBounds(0, 65, 250, 30);
    	js1.setBounds(0, 100 , 250, 500);
    	add(js1);
    	add(la);
    	try
    	{
    		ArrayList<RecipeVO> list=RecipeSystem.RecipeVOTop10();
    		for(RecipeVO m:list)
    		{
    			URL url=new URL(m.getPoster());
    			Image img=NetworkMain.getImage(
    					new ImageIcon(url), 50, 45);
    			Object[] data= {
    				new ImageIcon(img),
    				m.getTitle()
    			};
    			model.addRow(data);
    		}
    	}catch(Exception ex){}
    }
}








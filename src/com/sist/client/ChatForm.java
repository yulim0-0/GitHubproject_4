package com.sist.client;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class ChatForm extends JPanel{
     public JTable table;
     public DefaultTableModel model;
     public JTextArea ta;
     public JTextField tf;
     public JButton b1,b2;
     public ChatForm()
     {
    	 // 초기화 
    	 ta=new JTextArea();
    	 JScrollPane js1=new JScrollPane(ta);
    	 ta.setEditable(false);
    	 
    	 tf=new JTextField();
    	 
    	 b1=new JButton("쪽지보내기");
    	 b2=new JButton("정보보기");
    	 
    	 String[] col={"ID","Name","Sex"};
    	 String[][] row=new String[0][3];
    	 model=new DefaultTableModel(row,col)
    	 {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
    		  
    	 };
    	 table=new JTable(model);
    	 JScrollPane js2=new JScrollPane(table);
    	 // 배치 
    	 
    	 setLayout(null);
    	 
    	 js1.setBounds(10, 15, 500, 500);
    	 tf.setBounds(10, 520, 500, 30);
    	 
    	 js2.setBounds(515, 15, 300, 470);
    	 
    	 JPanel p=new JPanel();
    	 p.add(b1);
    	 p.add(b2);
    	 p.setBounds(515,490, 300, 35);
    	 
    	 add(js1);add(tf);add(js2);
    	 add(p);
     }
}







package com.sist.client;
import java.awt.*;
import javax.swing.*;
public class RecvForm extends JFrame{
   JLabel la;
   JTextField tf;
   JTextArea ta;
   JButton b1,b2;
   public RecvForm()
   {
	   la=new JLabel("보낸 사람");
	   tf=new JTextField(10);
	   ta=new JTextArea();
	   //ta.setEditable(false);
	   JScrollPane js=new JScrollPane(ta);
	   b1=new JButton("답장");
	   b2=new JButton("취소");
	   
	   setLayout(new BorderLayout());
	   JPanel p=new JPanel();
	   p.add(la);p.add(tf);
	   JPanel p1=new JPanel();
	   p1.add(b1);p1.add(b2);
	   add("North",p);
	   add("Center",js);
	   add("South",p1);
	   setSize(300,350);
	   //setVisible(true);
   }
}
   
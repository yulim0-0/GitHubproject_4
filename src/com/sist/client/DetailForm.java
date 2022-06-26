package com.sist.client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DetailForm extends JPanel implements ActionListener{
   public ControllerPanel cp;
   JLabel posterLa=new JLabel();
   JLabel title=new JLabel();
   JLabel chef=new JLabel();
   JLabel link=new JLabel();
  // JLabel idcrement=new JLabel();
  // JLabel movie=new JLabel();
   JButton b1,b2;
   public DetailForm(ControllerPanel cp)
   {
	   b1=new JButton("목록");
	   b2=new JButton("상세보기");
	   this.cp=cp;
	   //setBackground(Color.cyan);
	   // 배치 
	   setLayout(null);
	   // CSS
	   posterLa.setBounds(10, 15, 500, 450 );
	   title.setBounds(515, 15, 300, 35);
	   chef.setBounds(515, 55, 300, 35);
	   link.setBounds(515, 95, 300, 35);
	  // idcrement.setBounds(515, 135, 300, 35);
	  // movie.setBounds(515, 175, 300, 35);
	  // movie.setVisible(false);
	   JPanel p=new JPanel();
	   p.add(b1);p.add(b2);
	   p.setBounds(365, 220, 400, 35);
	   
	   add(posterLa);add(title);
	   add(chef);add(link);
	 //  add(idcrement);add(movie);
	   add(p);
	   
	   b1.addActionListener(this);
	   b2.addActionListener(this);
   }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1)
		{
			cp.card.show(cp,"HF");
		}
//		else if(e.getSource()==b2)
//		{
//			try
//			{
//				Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe "
//						             +"http://youtube.com/embed/"+movie.getText());
//			}catch(Exception ex){}
//		}
	}
}








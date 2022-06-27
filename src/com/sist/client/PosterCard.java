package com.sist.client;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.net.*;
import com.sist.data.RecipeVO;
import com.sist.main.NetworkMain;
public class PosterCard extends JPanel{
    JLabel poster=new JLabel();
    JLabel title=new JLabel();
    JLabel chef =new JLabel();
    public PosterCard(RecipeVO m)
    {
    	//poster.setBorder(new LineBorder(Color.blue,2));
    	//title.setBorder(new LineBorder(Color.red,1));
    	//singer.setBorder(new LineBorder(Color.red,1));
    	setLayout(null);
    	poster.setBounds(5,5,165,170);
    	//poster.setOpaque(true);
    	//poster.setBackground(Color.pink);
    	try
    	{
    		URL url=new URL(m.getPoster());
    		Image img=NetworkMain.getImage(new ImageIcon(url), 168, 170);
    		poster.setIcon(new ImageIcon(img));
    	}catch(Exception ex) {}
    	
    	title.setBounds(5,180, 165, 30);
    	//title.setOpaque(true);
    	//title.setBackground(Color.orange);
    	title.setText(m.getTitle());
    	chef.setBounds(5, 215 , 165, 30);
    	//singer.setOpaque(true);
    	//singer.setBackground(Color.cyan);
    	chef.setText(m.getChef());
    	add(poster);
    	add(title);
    	add(chef);
    }
}
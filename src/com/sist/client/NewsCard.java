package com.sist.client;
import java.awt.*;
import javax.swing.*;
public class NewsCard extends JPanel{
    public JLabel la;
    public JTextArea ta;
    public NewsCard()
    {
    	la=new JLabel("");
    	la.setForeground(Color.magenta);
    	ta=new JTextArea();
    	setLayout(null);
    	ta.setEditable(false);
    	la.setBounds(10, 15, 830, 30);
    	ta.setBounds(10, 50, 830, 50);
    	add(la);
    	add(ta);
    }
}
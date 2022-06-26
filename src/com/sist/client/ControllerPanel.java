package com.sist.client;

import java.awt.*;

import javax.swing.*;
// Controller =>  화면 이동 
public class ControllerPanel extends JPanel{
	public CardLayout card=new CardLayout();
	public HomeForm hf;
	public DetailForm df;
	public RecipeFindForm rf=new RecipeFindForm();
	public ChatForm cf=new ChatForm();
	//public LoginForm lf=new LoginForm();
	public NewsForm nf=new NewsForm();
    public ControllerPanel()
    {
    	hf=new HomeForm(this);
    	df=new DetailForm(this);
    	setLayout(card);
    	//add("LF",lf);
    	add("HF",hf);
    	add("DF",df);
    	add("RF",rf);
    	add("CF",cf);
    	add("NF",nf);
    }
}
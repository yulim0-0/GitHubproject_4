package com.sist.client;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;
import com.sist.data.*;
import com.sist.main.NetworkMain;
public class RecipeManager extends JPanel implements MouseListener{
    public PosterCard[] RecipeVOs=new PosterCard[15];
    public JPanel pan=new JPanel();
    public ControllerPanel cp;
    public RecipeManager(ControllerPanel cp)
    {
    	this.cp=cp;
    }
    public void cardPrint(ArrayList<RecipeVO> list)
    {
    	setLayout(null);
    	//JPanel p=new JPanel();
    	pan.setLayout(new GridLayout(3,5));
    	int i=0;
    	for(RecipeVO m:list)
    	{
    		
    		RecipeVOs[i]=new PosterCard(m);
    		pan.add(RecipeVOs[i]);
    		RecipeVOs[i].addMouseListener(this);
    		i++;
    	}
    	
    	pan.setBounds(10, 35, 840, 750);
    	add(pan);
    	
    	
    }
    public void cardInit(ArrayList<RecipeVO> list)
    {
    	for(int i=0;i<list.size();i++)
    	{
    		
    		RecipeVOs[i].poster.setIcon(null);
    		RecipeVOs[i].chef.setText("");
    		RecipeVOs[i].title.setText("");
   
    	}
    	pan.removeAll();
		pan.validate();
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<RecipeVOs.length;i++)
		{
			if(e.getSource()==RecipeVOs[i])
			{
				String title=RecipeVOs[i].title.getText();
				for(int j=0;j<RecipeSystem.getList().size();j++)
				{
					RecipeVO m=RecipeSystem.getList().get(j);
					if(m.getTitle().equals(title))
					{
					//	cp.df.album.setText("앨범:"+m.ge);
						cp.df.title.setText("제목:"+m.getTitle());
						cp.df.chef.setText("쉐프명:"+m.getChef());
					    cp.df.link.setText("주소:"+m.getLink());
						String s="";
//						if(m.getState().equals("유지"))
//						{
//							s="-";
//						}
//						else if(m.getState().equals("상승"))
//						{
//							s="▲"+m.getIdcrement();
//						}
//						else if(m.getState().equals("하강"))
//						{
//							s="▼"+m.getIdcrement();
//						}
//						cp.df.idcrement.setText("상태:"+s);
//						cp.df.movie.setText(m.getKey());
						try
				    	{
				    		URL url=new URL("http:"+m.getPoster());
				    		Image img=NetworkMain.getImage(new ImageIcon(url), 500, 450);
				    		cp.df.posterLa.setIcon(new ImageIcon(img));
				    		
				    	}catch(Exception ex) {}
						break;
					}
				}
				cp.card.show(cp, "DF");// 화면 이동 
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
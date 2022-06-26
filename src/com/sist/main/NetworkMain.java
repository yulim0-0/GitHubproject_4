package com.sist.main;
import com.sist.client.*;
import com.sist.common.Function;
import com.sist.data.RecipeVO;
import com.sist.data.RecipeSystem;
import com.sist.data.NaverNewsMain;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;

import javax.swing.*;
public class NetworkMain extends JFrame implements ActionListener,Runnable{
    MenuForm menu=new MenuForm();
    ControllerPanel cp=new ControllerPanel();
    WaitForm wr=new WaitForm();
    LoginForm lf=new LoginForm();
    SendForm sf=new SendForm();
    RecvForm rf=new RecvForm();
    int curpage=1;
    int totalpage=0;
    int cno=1;
    // 서버와 관련된 클래스 
    Socket s;
    BufferedReader in; //쓰레드 
    OutputStream out;// 일반 유저 
    // 서버 연결되는 시점 => 로그인 버튼 클릭시 연결 
    public NetworkMain()
    {
    	setTitle("네트워크 레시피 프로그램");
    	setLayout(null);// 사용자 정의 (직접 배치)
    	menu.setBounds(10, 15, 100, 350);
    	add(menu);
    	
    	cp.setBounds(120, 15, 850, 820);
    	add(cp);
    	
    	wr.setBounds(980, 15, 250, 700);
    	add(wr);
    	
    	setSize(1250, 900);
    	//setVisible(true);
    	// 종료 
    	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    	// 이벤트 등록 
    	for(int i=0;i<cp.hf.m.length;i++)
    	{
    		cp.hf.m[i].addActionListener(this);
    	}
    	
    	cp.hf.b1.addActionListener(this);// 이전
    	cp.hf.b2.addActionListener(this);// 다음 
    	
    	/*for(int i=0;i<cp.hf.mm.RecipeVOs.length;i++)
    	{
    		cp.hf.mm.RecipeVOs[i].addMouseListener(this);
    	}*/
    	
    	totalpage=RecipeSystem.RecipeVOToalPage();
    	cp.hf.pagLa.setText(curpage+ " page / "+totalpage+" pages");
    
        //1.menu => 
    	menu.chatBtn.addActionListener(this);
    	menu.exitBtn.addActionListener(this);
    	menu.homeBtn.addActionListener(this);
    	menu.newsBtn.addActionListener(this);
    	menu.foodBtn.addActionListener(this);
    	
    	cp.rf.btn.addActionListener(this);
    	
    	// 로그인 처리 
    	lf.b1.addActionListener(this);
    	lf.b2.addActionListener(this);
    	
    	cp.cf.tf.addActionListener(this); // 채팅 
    	cp.cf.b1.addActionListener(this);
    	cp.cf.b2.addActionListener(this);
    	
    	cp.nf.b.addActionListener(this);
    }
    public static Image getImage(ImageIcon ii,int width,int height)
    {
    	Image dimg=ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return dimg;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		}catch(Exception ex){}
		new NetworkMain();
	}
	// 버튼 클릭시 처리 => 구현이 안됨 => 클릭을 하면 자동 시스템(JVM)에 의핸 자동 호출 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==cp.hf.b1)// 이전 
		{
			if(curpage>1)
			{
				curpage--;
				ArrayList<RecipeVO> list=
						   cp.hf.rs.RecipeVOListData(cno, curpage);
				
				cp.hf.rm.cardInit(list);
				cp.hf.rm.cardPrint(list);
				
				cp.hf.pagLa.setText(curpage+ " page / "+totalpage+" pages");
			}
		}
		// 로그인 처리 
		else if(e.getSource()==lf.b1)
		{
			// id 
			// name ==> 반드시 입력 => 유효성 검사 => JQuery
			// => 기본 (보안) => => Spring Security
			String id=lf.tf1.getText();
			if(id.length()<1)
			{
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				// alert("ID~")
				lf.tf1.requestFocus();
				return;
			}
			String name=lf.tf2.getText();
			if(name.length()<1)
			{
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				// alert("ID~")
				lf.tf2.requestFocus();
				return;
			}
			
			String sex="";
			if(lf.rb1.isSelected())//남자 버튼 클릭
			{
				sex="남자";
			}
			else
			{
				sex="여자";
			}
			
			// 서버 연결 
			try
			{
				s=new Socket("localhost",3355);
				in=new BufferedReader(
						new InputStreamReader(s.getInputStream()));
				// 서버가 보내준 데이터를 저장된 위치 
				out=s.getOutputStream(); // 보내는 위치 
				// TCP 
				
				// 로그인 요청 
				out.write((Function.LOGIN+"|"+id+"|"+name+"|"+sex+"\n").getBytes());
			}catch(Exception ex) {}
			    // 서버에서 들어오는 데이터를 읽어서 출력 
			 new Thread(this).start();
		}
		else if(e.getSource()==lf.b2)
		{
			System.exit(0); //0응 정상 종료 
		}
	    else if(e.getSource()==cp.cf.tf)
	    {
		     //1. 채팅문자렬 읽기
	    	 String msg=cp.cf.tf.getText();
	    	 if(msg.length()<1)
	    	     return;
	    	 try
	    	 {
	    		 out.write((Function.CHAT+"|"+msg+"\n").getBytes());
	    	 }catch(Exception ex){}
	    	 cp.cf.tf.setText("");
	    }
		else if(e.getSource()==cp.hf.b2) // 다음 
		{
			if(curpage<totalpage)
			{
				System.out.println("cno="+cno);
				curpage++;
				ArrayList<RecipeVO> list=
						   cp.hf.rs.RecipeVOListData(cno, curpage);
				
				cp.hf.rm.cardInit(list);
				cp.hf.rm.cardPrint(list);
				
				cp.hf.pagLa.setText(curpage+ " page / "+totalpage+" pages");
			}
		}
		else if(e.getSource()==menu.newsBtn)
		{
			cp.card.show(cp, "NF");
		}
		else if(e.getSource()==menu.chatBtn)
		{
			cp.card.show(cp,"CF");
		}
		else if(e.getSource()==menu.exitBtn)
		{
			try
			{
				out.write((Function.END+"|\n").getBytes());
			}catch(Exception ex){}
		}
		else if(e.getSource()==menu.foodBtn)
		{
			cp.card.show(cp, "rf");
		}
		else if(e.getSource()==cp.cf.b1)
		{
			sf.setVisible(true);
		}
		else if(e.getSource()==menu.homeBtn)
		{
			cp.card.show(cp, "HF");
		}
		else if(e.getSource()==cp.nf.b)
		{
			String ss=cp.nf.tf.getText();
			if(ss.length()<1)
			{
				cp.nf.tf.requestFocus();
				return;
			}
			//cp.nf.list=NaverNewsMain.newsAllData(ss);
			cp.nf.findData(ss);
		}
		// 검색 버튼 클릭 
		else if(e.getSource()==cp.rf.btn)
		{
			// 1. 입력값을 읽어 온다 
			String fd=cp.rf.tf.getText();
			if(fd.length()<1)//입력이 안된 상태 
			{
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.rf.tf.requestFocus();
				return;
			}
			
			ArrayList<RecipeVO> fList=RecipeSystem.RecipeVOFind(fd);
			// 출력된 내용을 지운다 
			for(int i=cp.rf.model.getRowCount()-1;i>=0;i--)
			{
				cp.rf.model.removeRow(i);
			}
			// ==> 밑에서부터 지운다 
			try
			{
				for(RecipeVO m:fList)
				{
					URL url=new URL("http:"+m.getPoster());
					Image img=getImage(new ImageIcon(url), 30, 30);
					Object[] data= {
							m.getNo(),
							new ImageIcon(img),
							m.getTitle(),
							m.getChef()
					};
					cp.rf.model.addRow(data);
				}
			}catch(Exception ex){}
		}
		for(int i=0;i<cp.hf.m.length;i++)
		{
			if(e.getSource()==cp.hf.m[i])
			{
				curpage=1;
				cno=i+1;
				//System.out.println("button Click:"+(i+1));
				ArrayList<RecipeVO> list=
						   cp.hf.rs.RecipeVOListData(cno, curpage);
				
				cp.hf.rm.cardInit(list);
				cp.hf.rm.cardPrint(list);
				
				cp.hf.pagLa.setText(curpage+ " page / "+totalpage+" pages");
			}
		}
	}
	// 서버로 부터 실시간 데이터를 읽어 온다 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true)
			{
				// 서버에서 보내주는 데이터를 받는다 
				String msg=in.readLine();
				System.out.println(msg);
				StringTokenizer st=new StringTokenizer(msg,"|");
				int protocol=Integer.parseInt(st.nextToken());
				switch(protocol)
				{
					case Function.LOGIN:
					{
						String[] data= {
							st.nextToken(), // ID
							st.nextToken(), // Name
							st.nextToken()  // Sex
						};
						cp.cf.model.addRow(data);
					}
				    break;
					case Function.MYLOG:
					{
						lf.setVisible(false);//로그인은 종료
						setVisible(true);// 메인창 
					}
					break;
					case Function.CHAT:
					{
					      cp.cf.ta.append(st.nextToken()+"\n");	
					}
					break;
					case Function.SEND:
						break;
					case Function.END:// 남아 있는 사람 처리
					{
					    String myId=st.nextToken();
					    for(int i=0;i<cp.cf.model.getRowCount();i++)
					    {
					    	String you=cp.cf.model.getValueAt(i, 0).toString();
					    	if(myId.equals(you))
					    	{
					    		cp.cf.model.removeRow(i);
					    		break;
					    	}
					    }
					}
				    break;
					case Function.MYEND:
					{
					    System.exit(0);	
					}
					break;
				}
			}
		}catch(Exception ex){}
	}
	

}
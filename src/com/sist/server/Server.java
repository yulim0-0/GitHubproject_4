package com.sist.server;
import java.io.*;// 통신 ==> 메모리 / 파일 / 네트워크 
import java.util.*;//StringTokenizer

import com.sist.common.Function;
import com.sist.server.Server.Client;

import java.net.*;// 네트워크 
/*
 *     =============
 *        접속만 담당 ==> 교환 소켓 ==> 1개 
 *          접속에시 클라이언트의 정보 (IP,PORT)
 *     =============
 *        통신만 담당 ==> 통신 소켓 ==> 클라이언트 갯수만큼 
 *                                =============== 쓰레드 
 *     =============
 *       => 클라이언트 정보 공유 => 내부 클래스 (멤버 클래스)
 *       
 *     class Server
 *     {
 *         Vector
 *         class Client
 *         {
 *         }
 *     }
 */
// 내부클래스 => AI 
public class Server implements Runnable{
    private ServerSocket ss; // 접속을 담당하는 연결 기기
    private final int PORT=3355; // 0~65535 => (0~1023)
    private Vector<Client> waitVc=
    		  new Vector<Client>();// 클라이언트 정보 저장 (IP,PORT, ID...)
	// 서버 구동 
    public Server()
    {
    	try
    	{
    		ss=new ServerSocket(PORT);
    		System.out.println("Server Start...");
    		// => 사이트 (Spring) / 채팅 (NodeJS)
    		// NodeJS => 스프링 대체 
    	}catch(Exception ex) {}
    }
    public void run()
	{
		// 클라이언트 접속시마다 처리 
    	try
    	{
    	    while(true)
    	    {
	    		Socket s=ss.accept();// 클라이언트가 접속시에 호출 
	    		// 클라이언트에 대한 정보 (IP,PORT=>Socket)
	    		// s는 클라이언트 정보 
	    		// s=>Thread에 전송후에 통신이 가능하게 만든다 
	    		Client client=new Client(s);
	    		client.start();
    	    }
    	}catch(Exception ex) {}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Server server=new Server();
        new Thread(server).start();
	}
	
	class Client extends Thread
	{
		String id,name,sex;
		Socket s; // 클라이언트 접속 정보 
		BufferedReader in;
		OutputStream out;
		public Client(Socket s)
		{
			// 클라이언트와 연결 
			try
			{
				this.s=s;
				in=new BufferedReader(
                   new InputStreamReader(s.getInputStream()));
				out=s.getOutputStream();
				// 서버 => 클라이언트 정보
				// 클라이언트 => 서버 정보 
			}catch(Exception ex){}
		}
		// 실제 통신 => Thread가 동작 => 호출시에 start()
		public void run()
		{
			try
			{
				while(true)
				{
					// 클라이언트의 요청사항 받기 
					String msg=in.readLine();
					// 어떤 요구 사항인지 
					StringTokenizer st=new StringTokenizer(msg,"|");
					int protocol=Integer.parseInt(st.nextToken());
					// login.jsp 
					// chat.jsp
					// send.jsp
					// end.jsp
					switch(protocol)
					{
					 case Function.LOGIN://로그인처리
					 {
						 id=st.nextToken();
						 name=st.nextToken();
						 sex=st.nextToken();
						 
						 // 접속된 모든 사람에게 전송 
						 messageAll(Function.LOGIN+"|"+id+"|"+name+"|"+sex);
						 
						 // 저장 
						 waitVc.add(this);
						 // 로그인 하는 사람 처리 
						 messageTo(Function.MYLOG+"|"+name);
						 for(Client client:waitVc)
						 {
							 messageTo(Function.LOGIN+"|"
									 +client.id+"|"
									 +client.name+"|"
									 +client.sex);
						 }
					 }
					 break;
					 case Function.CHAT://채팅 처리
					 {
					     messageAll(Function.CHAT+"|["+name+"]"+st.nextToken());	 
					 }
					 break;
					 case Function.SEND://쪽지보내기
						break;
					 case Function.END:// 종료하기 
					 {
					     messageAll(Function.END+"|"+id);
					     for(int i=0;i<waitVc.size();i++)
					     {
					    	 Client c=waitVc.get(i);
					    	 if(id.equals(c.id))
					    	 {
					    		 messageTo(Function.MYEND+"|");
					    		 waitVc.remove(i);
					    		 in.close();
					    		 out.close();
					    		 break;
					    	 }
					     }
					 }
					 break;
					}
				}
			}catch(Exception ex){}
		}
		// 개인마다 
		public void messageTo(String msg)
		{
			try
			{
				out.write((msg+"\n").getBytes());
			}catch(Exception ex){}
		}
		// 전체 접속자에게 전송 
		public void messageAll(String msg)
		{
			try
			{
				for(Client client:waitVc)
				{
					client.messageTo(msg);
				}
			}catch(Exception ex) {}
		}
	}

}







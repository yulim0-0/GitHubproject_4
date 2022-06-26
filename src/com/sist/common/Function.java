package com.sist.common;
// 공유 => 서버를 거쳐서 작업 => 서버에서 지시 
// 서버 = master , 클라이언트 : 슬레이브 
// 기능별 분리 => 서버가 처리 (숫자) => 내부 프로토콜 (약속)
// 웹 => 구분을 할 필요가 없다 
// 구분 ==> 웹(파일을 요청) 

public class Function {
  public static final int LOGIN=100;// 로그인된 사람
  public static final int MYLOG=110;// 로그인 하는 사람 처리 
  /// 로그인 처리 
  public static final int CHAT=200;// 채팅 
  public static final int SEND=300;// 쪽지보내기 
  public static final int END=900;// 남아 있는 사람 
  public static final int MYEND=910;// 나가는 사람 
}
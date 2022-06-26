package com.sist.client;

import javax.swing.*;// Container,Component => 경량 
/*
 *       Container(윈도우창)  
 *         = JFrame => 일반 윈도우 
 *         = JPanel => 단독 실행이 불가능 (JFrame, JDialog)
 *            => 변경되는 화면 
 *         = JWindow : 타이틀바가 없는 창 
 *         = JDialog 
 *       Component 
 *         = 기능이 한개인 윈도우 => 단독 실행이 불가능 => JFrame,JPanel
 *         = Button 
 *             = JButton ==> <input type=button>
 *             = JRadioButton <input type=radio>
 *             = JCheckBox    <input type=checkbox>
 *         = 입력창 
 *             = 한줄 입력 : JTextField , JPasswordField
 *                           <input type=text>
 *                           <input type=password>
 *             = 여러줄 입력 : JTextArea(메모장) => JTextPane(word)
 *                          <textarea>
 *         = JLabel : 보여만 준다 (이미지) <label>
 *           HTML/CSS => 암기 
 *         ========================== Java+HTML = JSP
 *           JAVA  / HTML => 분리 (MVC) 
 *           => 어노테이션 / XML => Spring 
 *           
 *         => 목록 
 *            => JTable , JTree , SplitPane 
 *               <table>
 */
import java.awt.*;
import javax.swing.table.*;
public class RecipeFindForm extends JPanel{
   public JTextField tf;
   public JButton btn;
   public JTable table;
   public DefaultTableModel model;
   // 초기화 => 배치 
   public RecipeFindForm()
   {
	   tf=new JTextField();
	   btn=new JButton("검색");
	   String[] col={"순위","","음식명","쉐프명"};
	   //   int     ImageIcon String String 
	   Object[][] row=new Object[0][4];
	   model=new DefaultTableModel(row,col)
	   {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;// 편집 방지 
			}
            // 이미지 출력 
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return getValueAt(0, columnIndex).getClass();
			}
		   
	   }; // 익명의 클래스 => 생성자안에서 재정의 
	   table=new JTable(model);
	   table.setRowHeight(40);
	   // <tr height=40>
	   table.setShowGrid(false);
	   table.setShowHorizontalLines(true);
	   table.getTableHeader().setReorderingAllowed(false);
	   JScrollPane js=new JScrollPane(table);
	   
	   //  배치 
	   setLayout(null);
	   tf.setBounds(10, 15, 200, 30);
	   btn.setBounds(215, 15, 100, 30);
	   js.setBounds(10, 55, 800, 500);
	   
	   add(tf);
	   add(btn);
	   add(js);
	   
   }
}




















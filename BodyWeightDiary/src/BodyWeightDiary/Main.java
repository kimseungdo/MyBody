package BodyWeightDiary;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class Main {

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		main.display();
	}

}

class MainFrame extends JFrame implements ActionListener
{	
	JPanel p0 = new JPanel(); // 오늘 연원일 쓰레드 담을 패널
	JPanel p1 = new JPanel(); // 달력 년도월 담을 패널
	JPanel p2 = new JPanel(); // 요일 레이블 담을 패널 
	JPanel p3 = new JPanel(); // 달력을 담을 패널
	JPanel p4 = new JPanel(); // 선택된 날자 레이블을 담을 패널
	JPanel p5 = new JPanel(); // 신장 입력 패널
	JPanel p6 = new JPanel(); // 텍스트 필드(체중입력) 담을 패널

	JPanel p7 = new JPanel(); // 슬라이드, 비만도, 비만율 레이블 담을 패널
	JPanel p8 = new JPanel(); // 전 월 비교 레이블 담을 패널
	JPanel p9 = new JPanel(); // 전 주 비교 레이블 담을 패널
	JPanel p10 = new JPanel(); // 저장, 초기화 버튼 담을 패널

	JLabel ymd, ym, cm, kg1, kg2; // ymd는 쓰레드를 이용한 날짜출력, ym은 달력의 연월, cm, kg는 "cm", "kg" 출력을 위한 레이블
	JLabel comm, comw; // comm은 전 월, comw는 전 주 체중 비교할 레이블
	JLabel day[]; // 요일을 표시할 레이블
	JLabel choday; // 선택된 날자를 표시할 레이블
	String [] days = {"일", "월", "화", "수", "목", "금", "토"}; // day label에 넣을 요일
	JButton date[]; // 달력의 날자 버튼
	JLabel savekg []; // 달력에 표시될 그 날의 체중
	JButton later, next; // 달력 전 달, 다음 달로 이동할 버튼
	JSlider sl = new JSlider(JSlider.HORIZONTAL,0, 4, 1); // 비만정도를 나타낼 JSlider
	JTextField cminput, minput; // 신장(키) 입력, 체중 입력
	JLabel cmlb, mlb, outod, outor; // "신장, 저녁 체중입력"뜨는 레이블 outod 비만정도, outor 비만율
	JButton save = new JButton("저장");
	JButton reset = new JButton("초기화");
	JMenuBar bar = new JMenuBar(); // 메뉴 바
	// 메뉴
	JMenu inform = new JMenu("정보");
	JMenu direc = new JMenu("사용법");
	JMenuItem direcinfo = new JMenuItem("사용 설명서");
	JMenuItem info = new JMenuItem("개발자");
			
	
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	
	GregorianCalendar gc = new GregorianCalendar();
	int year = gc.get(gc.YEAR); 
	int month = (gc.get(gc.MONTH)+1);
	int dat = gc.get(gc.DATE);
	
	static int moon = 0;
	
	MainFrame()
	{	
		// 패널 정렬
		//p0.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		p1.setLayout(new GridLayout(1,1,17,10));
		p2.setLayout(new GridLayout(1,1,32,10));
		p3.setLayout(new GridLayout(11,1,1,1));
		p4.setLayout(new GridLayout(1,2,1,1));
		//p6.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
		//p7.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
		//p8.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
		//p9.setLayout(new GridLayout(2,5));
		p10.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
		
		// 스레드를 이용하여 현재 연도 날자 시간을 표시할 레이블
		ymd = new JLabel("");
		ymd.setFont(new Font("고딕", Font.BOLD, 24));
		
		// 달력을 옮길 버튼
		later = new JButton("◀");
		next = new JButton("▶");
		later.addActionListener(this);
		next.addActionListener(this);
		
		// 달력에 표시할 연도와 월
		GregorianCalendar gc = new GregorianCalendar();
		ym = new JLabel("");
		ym.setText(gc.get(gc.YEAR)+ "년 "  + (gc.get(gc.MONTH)+1) + "월 ");
		ym.setFont(new Font("고딕",Font.BOLD, 18));
		
		// 선택된 날자 (저장할 날자)
		choday = new JLabel("");
		choday.setFont(new Font("고딕", Font.BOLD, 18));
		choday.setText("선택한 날짜 : " + gc.get(gc.YEAR)+ "년 "  + (gc.get(gc.MONTH)+1) + "월 " + gc.get(gc.DATE)+ "일 ");
		
		// 일월 화 수 목 금 토 
		day = new JLabel[7];
		date = new JButton[42];
		savekg = new JLabel[42];
		
		for(int i=0; i<7; i++)
		{
			p2.add(day[i] = new JLabel(days[i]));
			day[i].setFont(new Font("고딕",Font.BOLD, 16));
		}
		day[0].setForeground(Color.RED);
		day[6].setForeground(Color.BLUE);
		
		setcal(moon);
		setmoon();
		AddDate(); // 달력에 날짜 추가
		Loaddata(); // 데이터 불러오기
		
		cminput = new JTextField(10);
		SetheightQuery sq = new SetheightQuery();
		double hegiht = sq.SQ();
		cminput.setText("" + hegiht);
		minput = new JTextField(10);
		
		cmlb = new JLabel("     신장 입력 : ");
		mlb = new JLabel("몸무게 입력 :");
		cm = new JLabel("cm");
		kg1 = new JLabel("kg");
		kg2 = new JLabel("kg");
		
		outor = new JLabel("비만율");
		outod = new JLabel("비만정도");
		
		comm = new JLabel("지난 월 대비 kg증가(감소) 했습니다.");
		comw = new JLabel("지난 주 대비 kg증가(감소) 했습니다.");
		
		sl.setMajorTickSpacing(5);
		sl.setMinorTickSpacing(1);
		sl.setPaintTicks(true);

		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("저체중"));
		labelTable.put( new Integer( 1 ), new JLabel("정상"));
		labelTable.put( new Integer( 2 ), new JLabel("과제충"));
		labelTable.put( new Integer( 3 ), new JLabel("비만"));
		labelTable.put( new Integer( 4 ), new JLabel("고도비만"));
		sl.setLabelTable( labelTable );
		sl.setPaintLabels(true);
						
		save.addActionListener(this);
		reset.addActionListener(this);
		// GUI선언 및 정의 문
		setJMenuBar(bar);
		bar.add(direc);
		bar.add(inform);
		direc.add(direcinfo);
		direcinfo.addActionListener(this);
		inform.add(info);
		info.addActionListener(this);
		
		add(p0); add(p1); add(p2); add(p3);
		add(p4); add(p5); add(p6); add(p7);
		add(p8); add(p9); add(p10);
		
		p0.add(ymd);
		p1.add(later); p1.add(ym); p1.add(next);
		p4.add(choday);
		
		p5.add(cmlb); p5.add(cminput); p5.add(cm);
		p6.add(mlb); p6.add(minput); p6.add(kg1);
		
		p7.add(sl); p7.add(outod); p7.add(outor);
		p8.add(comm); p9.add(comw);
		p10.add(save); p10.add(reset);
		
		// GUI add문
	}
	
	void setcal(int m) // 날짜 세팅
	{
		cal.set(year, month-1+m, dat);
	}
	
	void setcalminusday7(int m, int w) // 날짜 세팅
	{
		cal.set(year, month-1+m, w-7);
	}
	
	void setcalminusday1(int m, int w)
	{
		cal.set(year, month-1+m, w-1);
	}
	
	void setcal(int m, int w)
	{
		cal.set(year, month-1+m, w);
	}
	
	public void setmoon() // 달력 날짜 정하는 메소드
	{
		
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		for(int i=0; i< lastDay; i++) // 이번달 세팅
		{
			calArr.add(i+1);
		}
		
		
		cal.set(Calendar.DATE, 1);
		int yoil = cal.get(Calendar.DAY_OF_WEEK);
		int numOfPreMonth = yoil -1;
		cal.add(Calendar.MONTH, -1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		
		for(int i=0; i<numOfPreMonth; i++) { // 지난 달 세팅
			calArr.add(0, 0);
			lastDay--;
		}
		
		cal.add(Calendar.MONTH, 1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DATE, lastDay);
		yoil = cal.get(Calendar.DAY_OF_WEEK);
		
		int numOfNextMonth = 7 - yoil;
		for(int i=0; i< numOfNextMonth; i++) { // 다음 달 세팅
			calArr.add(0);
		}
		if(calArr.size() == 42)
		{
			p3.setLayout(new GridLayout(13,1,1,1));
		}
		else
		{
			p3.setLayout(new GridLayout(11,1,1,1));
		}
		ym.setText(cal.get(cal.YEAR) + "년 "  + (cal.get(cal.MONTH)+1) + "월 "); // 바뀐 달력 년월 값 셋팅
	}
	
	public void AddDate() { // 달력에 날짜 추가 메소드
		
		int k=1, l =0;
		
		for(int i=0; i<calArr.size(); i++)
		{
			date[i] = new JButton("" + calArr.get(i));
			savekg[i] = new JLabel("       -");
			
			if(i%7 == 0) // 달력 날짜에 색 입히기
			{
				date[i].setForeground(Color.RED);
			}
			else if(i%7 == 6)
			{
				date[i].setForeground(Color.BLUE);
			}
			
			p3.add(date[i]);
			date[i].addActionListener(this);
			
			if(k%7 == 0)
			{
				for(int j=0; j<7; j++)
				{
					p3.add(savekg[l]);
					l++;
				}
			}
			k++;
		}
		
		for(int a=0; a<calArr.size(); a++) // 지난달 이번달 날짜 없애기
		{
			if(Integer.parseInt(date[a].getText()) == 0)
			{
				date[a].setText("");
				savekg[a].setText("");
			}
		}
	}
	
	public void Loaddata()
	{
		for(int b=0; b<calArr.size(); b++)
		{			
			String dat = cal.get(cal.YEAR) + "년 "  + (cal.get(cal.MONTH)+1) + "월 " + date[b].getText() + "일";
			SetweightQuery sq = new SetweightQuery();
			double we = sq.SQ(dat);
			if(we != -1)
			{
				savekg[b].setText("   " + we);
				
				if(savekg[b-1].getText() != "       -" && savekg[b-1].getText() != "") // 날짜별 폰트 색 넣기
				{
					double sum = Double.parseDouble(savekg[b].getText()) - Double.parseDouble(savekg[b-1].getText());
					
					if (sum <= 0)
					{
						savekg[b].setForeground(Color.BLUE);
					}
					else
					{
						savekg[b].setForeground(Color.RED);
					}
				}
			}
			if(date[b].getText() != "" && Integer.parseInt(date[b].getText()) == 1) // 1일 날짜 폰트 색 넣기
			{
				setcalminusday1(moon, Integer.parseInt(date[b].getText()));
				String da = cal.get(cal.YEAR) + "년 " + (cal.get(cal.MONTH)+1) + "월 " + cal.get(cal.DATE) + "일";
				SetweightQuery ssq = new SetweightQuery();
				double mlastday = ssq.SQ(da);
				
				if(mlastday != -1)
				{
					double sum = Double.parseDouble(savekg[b].getText()) - mlastday;
					if (sum <= 0)
					{
						savekg[b].setForeground(Color.BLUE);
					}
					else
					{
						savekg[b].setForeground(Color.RED);
					}
				}
				setcal(moon, Integer.parseInt(date[b].getText()));
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == later) // 이전 달 버튼
		{
			p3.removeAll();
			calArr.clear();
			moon--;
			setcal(moon);
			setmoon();
			AddDate();
			Loaddata();
			
			comm.setText("지난 월 대비 kg증가(감소) 했습니다.");
			comw.setText("지난 주 대비 kg증가(감소) 했습니다.");
		}
		
		if(e.getSource() == next) // 다음 달 버튼
		{
			p3.removeAll();
			calArr.clear();
			moon++;
			setcal(moon);
			setmoon();
			AddDate();
			Loaddata();
			
			comm.setText("지난 월 대비 kg증가(감소) 했습니다.");
			comw.setText("지난 주 대비 kg증가(감소) 했습니다.");
		}
		
		for(int i=0; i<calArr.size(); i++) // 달력 날짜 버튼 누를 때
		{
			if(e.getSource() == date[i])
			{
				if(date[i].getText() == "") // 달력 날짜 없는 날을 누른다면
				{
					choday.setText("                             선택한 날짜 없음                             ");
				}
				
				else
				{
					
					if(savekg[i].getText() == "       -")
					{
						minput.setText("");
					}
				setcal(moon);
				choday.setText("선택한 날짜 : " + cal.get(cal.YEAR) + "년 "  + (cal.get(cal.MONTH)+1) + "월 " + date[i].getText() + "일 ");
				
				String nowm = cal.get(cal.YEAR) + "년 "  + (cal.get(cal.MONTH)+1) + "월 " + date[i].getText() + "일 ";
				String lastm = cal.get(cal.YEAR) + "년 "  + cal.get(cal.MONTH) + "월 " + date[i].getText() + "일";
				
				setcalminusday7(moon, Integer.parseInt(date[i].getText()));
				String lastw = cal.get(cal.YEAR) + "년 "  + (cal.get(cal.MONTH)+1) + "월 " + cal.get(cal.DATE) + "일";
				
				SetweightQuery sq = new SetweightQuery();
				double lastmoonth = sq.SQ(lastm);
				double nowmoonth = sq.SQ(nowm);
				double lastweek = sq.SQ(lastw);
				
				if(nowmoonth != -1)
				{
					minput.setText("" + nowmoonth); // 선택된 날 값 꺼내서 셋팅
				
					double or; //비만율(BMI) 계산식
					double cm = Double.parseDouble(cminput.getText());
					double kg = Double.parseDouble(minput.getText());
					or = (kg/(cm*cm))*10000;
					or = Math.round(or*100)/100.0;
					outor.setText("BMI : " + or);
				
					if(or < 18.5)
					{
						outod.setText("저체중");
						sl.setValue(0);
					}
					else if (or < 23)
					{
						outod.setText("정상");
						sl.setValue(1);
					}
					else if (or < 25)
					{
						outod.setText("과체중");
						sl.setValue(2);
					}
					else if (or < 30)
					{
						outod.setText("비만");
						sl.setValue(3);
					}
					else
					{
						outod.setText("고도비만");
						sl.setValue(4);
					}
				}
				
				if(lastweek != -1 && nowmoonth != -1) // 지난 주와 몸무게 비교
				{
					nowmoonth -= lastweek;
					
					if(nowmoonth < 0)
					{
						comw.setText("  지난 주 대비 " + Math.round(nowmoonth*10)/10.0 + " kg감소 했습니다.  ");
					}
					else if( nowmoonth > 0)
					{
						comw.setText("  지난 주 대비 " + Math.round(nowmoonth*10)/10.0 + " kg증가 했습니다.  ");
					}
					else
					{
						comw.setText("  지난 주와 " + Math.round(lastweek*10)/10.0 + " kg로 체중이 같습니다.  ");
					}
					
					nowmoonth += lastweek;
				}
				else
				{
					comw.setText("지난 주 대비 kg증가(감소) 했습니다.");
				}
				
				if(lastmoonth != -1 && nowmoonth != -1) // 지난 달과 몸무게 비교
				{
					nowmoonth -= lastmoonth;
					
					if(nowmoonth < 0)
					{
						comm.setText("지난 월 대비 " + Math.round(nowmoonth*10)/10.0 + " kg감소 했습니다.");
					}
					else if( nowmoonth > 0)
					{
						comm.setText("지난 월 대비 " + Math.round(nowmoonth*10)/10.0 + " kg증가 했습니다.");
					}
					else
					{
						comm.setText("지난 월과 " + Math.round(lastmoonth*10)/10.0 + " kg로 체중이 같습니다.");
					}
					nowmoonth += lastmoonth;
				}
				else
				{
					comm.setText("지난 월 대비 kg증가(감소) 했습니다.");
				}
				}
			}
		}
		
		if(e.getSource() == save) // 저장버튼
		{
			if(cminput.getText().trim().isEmpty() || minput.getText().trim().isEmpty()) // 키나 체중이 입력되지 않았다면
			{
				Inputplease in = new Inputplease(); // 다이얼로그 출력
			}
			else
				
			{
				double or; //비만율(BMI) 계산식
				double cm = Double.parseDouble(cminput.getText());
				double kg = Double.parseDouble(minput.getText());
				or = (kg/(cm*cm))*10000;
				or = Math.round(or*100)/100.0;
				outor.setText("BMI : " + or);
				
				if(or < 18.5)
				{
					outod.setText("저체중");
					sl.setValue(0);
				}
				else if (or < 23)
				{
					outod.setText("정상");
					sl.setValue(1);
				}
				else if (or < 25)
				{
					outod.setText("과체중");
					sl.setValue(2);
				}
				else if (or < 30)
				{
					outod.setText("비만");
					sl.setValue(3);
				}
				else
				{
					outod.setText("고도비만");
					sl.setValue(4);
				}
				
				String date = choday.getText();
				int idx = date.indexOf(": "); // :을 기준으로 문자열 자르기
				String savedate = date.substring(idx+2); // XXXX년 XX월 XX일 저장

				InsertQuery iq = new InsertQuery(); // DB연동 데이터 저장
				iq.IQ(savedate, kg, cm);
				
				Loaddata();
				
				Complete co = new Complete(); // 다이얼로그 출력
			}
		}
			
		if(e.getSource() == reset) // 초기화 버튼
		{
			minput.setText("");
			comm.setText("지난 월 대비 kg증가(감소) 했습니다.");
			comw.setText("지난 주 대비 kg증가(감소) 했습니다.");
			sl.setValue(0);
			outor.setText("비만율");
			outod.setText("비만정도");
			choday.setText("선택된 날자 : " + gc.get(gc.YEAR)+ "년 "  + (gc.get(gc.MONTH)+1) + "월 " + gc.get(gc.DATE)+ "일 ");
		}
		
		if(e.getSource() == direcinfo)
		{
			Direcinfo di = new Direcinfo();
		}
		
		if(e.getSource() == info)
		{
			Info in = new Info();
		}
	}
	class Clock extends Thread {
		
		String year, month, dates, hour, minute, second;
		
		public void run() {
			while(true) {
				GregorianCalendar gc = new GregorianCalendar();
				try {
					year = gc.get(gc.YEAR)+ "년 ";
					month = gc.get(gc.MONTH)+1+ "월 ";
					dates = gc.get(gc.DATE)+ "일 ";
//					hour = gc.get(gc.HOUR) + "";
//					minute = gc.get(gc.MINUTE) + "";
//					second = gc.get(gc.SECOND) + "";
					Thread.sleep(1000);
				} catch(InterruptedException ie) {
				}
				ymd.setText(year + month + dates);
			}
		}
	}
	
	void display()
	{
		setLayout(new FlowLayout());
		setTitle("체중 일기장");
		setSize(430, 850);
		setResizable(false);
		setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("icon.jpg");
		setIconImage(img);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Thread t1 = new Thread(new Clock());
		t1.start();
	}
	
	class Direcinfo extends JDialog {
		JButton btn;
		JPanel p1, p2;
		JLabel lb;
		public Direcinfo() {
			p1 = new JPanel();
			p2 = new JPanel();
			p1.setLayout(new FlowLayout(FlowLayout.CENTER, 100,20));
			p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("menual.jpg");
			JLabel lb = new JLabel(new ImageIcon(img));
			btn = new JButton("확인");
			add(p1);
			p1.add(lb);
			
			add(p2);
			p2.add(btn);
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					
				}
			});
			setLayout(new FlowLayout());
			setTitle("사용 설명서");
			setSize(450, 950);
			setLocation(0, 50);
			setVisible(true);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image im = tk.getImage("icon.jpg");
			setIconImage(im);
		}
	}
	
	class Info extends JDialog {
		JButton btn;
		JPanel p1, p2;
		JLabel lb;
		public Info() {
			p1 = new JPanel();
			p2 = new JPanel();
			p1.setLayout(new FlowLayout(FlowLayout.CENTER, 100,20));
			p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("developer.jpg");
			JLabel lb = new JLabel(new ImageIcon(img));
			btn = new JButton("확인");
			add(p1);
			p1.add(lb);
			
			add(p2);
			p2.add(btn);
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					
				}
			});
			setLayout(new FlowLayout());
			setTitle("개발자 정보");
			setSize(280, 270);
			setLocation(100, 300);
			setVisible(true);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image im = tk.getImage("icon.jpg");
			setIconImage(im);
		}
	}
	
	class Complete extends JDialog {
		JButton btn;
		JPanel p1, p2, p3;
		JLabel lb, msg;
		
		public Complete() {
			p1 = new JPanel();
			p3 = new JPanel();
			p1.setLayout(new FlowLayout(FlowLayout.CENTER, 100,20));
			p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
			btn = new JButton("확인");
			msg = new JLabel("정상적으로 저장 되었습니다.");
			add(p1);
			p1.add(msg);
			
			add(p3);
			p3.add(btn);
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setVisible(false);
					
				}
			});
			setLayout(new FlowLayout());
			setTitle("알림");
			setSize(200, 175);
			setLocation(100, 300);
			setVisible(true);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image img = toolkit.getImage("icon.jpg");
			setIconImage(img);
		}
	}
}

class Inputplease extends JDialog {
	JButton btn;
	JLabel label;
	
	public Inputplease() {
		btn = new JButton("확인");
		label = new JLabel("키와 체중을 입력해 주세요.");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		add(label);
		add(btn);
		setLayout(new FlowLayout());
		setTitle("알림");
		setSize(200, 100);
		setLocation(100, 300);
		setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("icon.jpg");
		setIconImage(img);
	}
}
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
	JPanel p0 = new JPanel(); // ���� ������ ������ ���� �г�
	JPanel p1 = new JPanel(); // �޷� �⵵�� ���� �г�
	JPanel p2 = new JPanel(); // ���� ���̺� ���� �г� 
	JPanel p3 = new JPanel(); // �޷��� ���� �г�
	JPanel p4 = new JPanel(); // ���õ� ���� ���̺��� ���� �г�
	JPanel p5 = new JPanel(); // ���� �Է� �г�
	JPanel p6 = new JPanel(); // �ؽ�Ʈ �ʵ�(ü���Է�) ���� �г�

	JPanel p7 = new JPanel(); // �����̵�, �񸸵�, ���� ���̺� ���� �г�
	JPanel p8 = new JPanel(); // �� �� �� ���̺� ���� �г�
	JPanel p9 = new JPanel(); // �� �� �� ���̺� ���� �г�
	JPanel p10 = new JPanel(); // ����, �ʱ�ȭ ��ư ���� �г�

	JLabel ymd, ym, cm, kg1, kg2; // ymd�� �����带 �̿��� ��¥���, ym�� �޷��� ����, cm, kg�� "cm", "kg" ����� ���� ���̺�
	JLabel comm, comw; // comm�� �� ��, comw�� �� �� ü�� ���� ���̺�
	JLabel day[]; // ������ ǥ���� ���̺�
	JLabel choday; // ���õ� ���ڸ� ǥ���� ���̺�
	String [] days = {"��", "��", "ȭ", "��", "��", "��", "��"}; // day label�� ���� ����
	JButton date[]; // �޷��� ���� ��ư
	JLabel savekg []; // �޷¿� ǥ�õ� �� ���� ü��
	JButton later, next; // �޷� �� ��, ���� �޷� �̵��� ��ư
	JSlider sl = new JSlider(JSlider.HORIZONTAL,0, 4, 1); // �������� ��Ÿ�� JSlider
	JTextField cminput, minput; // ����(Ű) �Է�, ü�� �Է�
	JLabel cmlb, mlb, outod, outor; // "����, ���� ü���Է�"�ߴ� ���̺� outod ������, outor ����
	JButton save = new JButton("����");
	JButton reset = new JButton("�ʱ�ȭ");
	JMenuBar bar = new JMenuBar(); // �޴� ��
	// �޴�
	JMenu inform = new JMenu("����");
	JMenu direc = new JMenu("����");
	JMenuItem direcinfo = new JMenuItem("��� ����");
	JMenuItem info = new JMenuItem("������");
			
	
	ArrayList<Integer> calArr = new ArrayList<>();
	Calendar cal = Calendar.getInstance();
	
	GregorianCalendar gc = new GregorianCalendar();
	int year = gc.get(gc.YEAR); 
	int month = (gc.get(gc.MONTH)+1);
	int dat = gc.get(gc.DATE);
	
	static int moon = 0;
	
	MainFrame()
	{	
		// �г� ����
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
		
		// �����带 �̿��Ͽ� ���� ���� ���� �ð��� ǥ���� ���̺�
		ymd = new JLabel("");
		ymd.setFont(new Font("���", Font.BOLD, 24));
		
		// �޷��� �ű� ��ư
		later = new JButton("��");
		next = new JButton("��");
		later.addActionListener(this);
		next.addActionListener(this);
		
		// �޷¿� ǥ���� ������ ��
		GregorianCalendar gc = new GregorianCalendar();
		ym = new JLabel("");
		ym.setText(gc.get(gc.YEAR)+ "�� "  + (gc.get(gc.MONTH)+1) + "�� ");
		ym.setFont(new Font("���",Font.BOLD, 18));
		
		// ���õ� ���� (������ ����)
		choday = new JLabel("");
		choday.setFont(new Font("���", Font.BOLD, 18));
		choday.setText("������ ��¥ : " + gc.get(gc.YEAR)+ "�� "  + (gc.get(gc.MONTH)+1) + "�� " + gc.get(gc.DATE)+ "�� ");
		
		// �Ͽ� ȭ �� �� �� �� 
		day = new JLabel[7];
		date = new JButton[42];
		savekg = new JLabel[42];
		
		for(int i=0; i<7; i++)
		{
			p2.add(day[i] = new JLabel(days[i]));
			day[i].setFont(new Font("���",Font.BOLD, 16));
		}
		day[0].setForeground(Color.RED);
		day[6].setForeground(Color.BLUE);
		
		setcal(moon);
		setmoon();
		AddDate(); // �޷¿� ��¥ �߰�
		Loaddata(); // ������ �ҷ�����
		
		cminput = new JTextField(10);
		SetheightQuery sq = new SetheightQuery();
		double hegiht = sq.SQ();
		cminput.setText("" + hegiht);
		minput = new JTextField(10);
		
		cmlb = new JLabel("     ���� �Է� : ");
		mlb = new JLabel("������ �Է� :");
		cm = new JLabel("cm");
		kg1 = new JLabel("kg");
		kg2 = new JLabel("kg");
		
		outor = new JLabel("����");
		outod = new JLabel("������");
		
		comm = new JLabel("���� �� ��� kg����(����) �߽��ϴ�.");
		comw = new JLabel("���� �� ��� kg����(����) �߽��ϴ�.");
		
		sl.setMajorTickSpacing(5);
		sl.setMinorTickSpacing(1);
		sl.setPaintTicks(true);

		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("��ü��"));
		labelTable.put( new Integer( 1 ), new JLabel("����"));
		labelTable.put( new Integer( 2 ), new JLabel("������"));
		labelTable.put( new Integer( 3 ), new JLabel("��"));
		labelTable.put( new Integer( 4 ), new JLabel("����"));
		sl.setLabelTable( labelTable );
		sl.setPaintLabels(true);
						
		save.addActionListener(this);
		reset.addActionListener(this);
		// GUI���� �� ���� ��
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
		
		// GUI add��
	}
	
	void setcal(int m) // ��¥ ����
	{
		cal.set(year, month-1+m, dat);
	}
	
	void setcalminusday7(int m, int w) // ��¥ ����
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
	
	public void setmoon() // �޷� ��¥ ���ϴ� �޼ҵ�
	{
		
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		for(int i=0; i< lastDay; i++) // �̹��� ����
		{
			calArr.add(i+1);
		}
		
		
		cal.set(Calendar.DATE, 1);
		int yoil = cal.get(Calendar.DAY_OF_WEEK);
		int numOfPreMonth = yoil -1;
		cal.add(Calendar.MONTH, -1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		
		for(int i=0; i<numOfPreMonth; i++) { // ���� �� ����
			calArr.add(0, 0);
			lastDay--;
		}
		
		cal.add(Calendar.MONTH, 1);
		lastDay = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DATE, lastDay);
		yoil = cal.get(Calendar.DAY_OF_WEEK);
		
		int numOfNextMonth = 7 - yoil;
		for(int i=0; i< numOfNextMonth; i++) { // ���� �� ����
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
		ym.setText(cal.get(cal.YEAR) + "�� "  + (cal.get(cal.MONTH)+1) + "�� "); // �ٲ� �޷� ��� �� ����
	}
	
	public void AddDate() { // �޷¿� ��¥ �߰� �޼ҵ�
		
		int k=1, l =0;
		
		for(int i=0; i<calArr.size(); i++)
		{
			date[i] = new JButton("" + calArr.get(i));
			savekg[i] = new JLabel("       -");
			
			if(i%7 == 0) // �޷� ��¥�� �� ������
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
		
		for(int a=0; a<calArr.size(); a++) // ������ �̹��� ��¥ ���ֱ�
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
			String dat = cal.get(cal.YEAR) + "�� "  + (cal.get(cal.MONTH)+1) + "�� " + date[b].getText() + "��";
			SetweightQuery sq = new SetweightQuery();
			double we = sq.SQ(dat);
			if(we != -1)
			{
				savekg[b].setText("   " + we);
				
				if(savekg[b-1].getText() != "       -" && savekg[b-1].getText() != "") // ��¥�� ��Ʈ �� �ֱ�
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
			if(date[b].getText() != "" && Integer.parseInt(date[b].getText()) == 1) // 1�� ��¥ ��Ʈ �� �ֱ�
			{
				setcalminusday1(moon, Integer.parseInt(date[b].getText()));
				String da = cal.get(cal.YEAR) + "�� " + (cal.get(cal.MONTH)+1) + "�� " + cal.get(cal.DATE) + "��";
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
		if(e.getSource() == later) // ���� �� ��ư
		{
			p3.removeAll();
			calArr.clear();
			moon--;
			setcal(moon);
			setmoon();
			AddDate();
			Loaddata();
			
			comm.setText("���� �� ��� kg����(����) �߽��ϴ�.");
			comw.setText("���� �� ��� kg����(����) �߽��ϴ�.");
		}
		
		if(e.getSource() == next) // ���� �� ��ư
		{
			p3.removeAll();
			calArr.clear();
			moon++;
			setcal(moon);
			setmoon();
			AddDate();
			Loaddata();
			
			comm.setText("���� �� ��� kg����(����) �߽��ϴ�.");
			comw.setText("���� �� ��� kg����(����) �߽��ϴ�.");
		}
		
		for(int i=0; i<calArr.size(); i++) // �޷� ��¥ ��ư ���� ��
		{
			if(e.getSource() == date[i])
			{
				if(date[i].getText() == "") // �޷� ��¥ ���� ���� �����ٸ�
				{
					choday.setText("                             ������ ��¥ ����                             ");
				}
				
				else
				{
					
					if(savekg[i].getText() == "       -")
					{
						minput.setText("");
					}
				setcal(moon);
				choday.setText("������ ��¥ : " + cal.get(cal.YEAR) + "�� "  + (cal.get(cal.MONTH)+1) + "�� " + date[i].getText() + "�� ");
				
				String nowm = cal.get(cal.YEAR) + "�� "  + (cal.get(cal.MONTH)+1) + "�� " + date[i].getText() + "�� ";
				String lastm = cal.get(cal.YEAR) + "�� "  + cal.get(cal.MONTH) + "�� " + date[i].getText() + "��";
				
				setcalminusday7(moon, Integer.parseInt(date[i].getText()));
				String lastw = cal.get(cal.YEAR) + "�� "  + (cal.get(cal.MONTH)+1) + "�� " + cal.get(cal.DATE) + "��";
				
				SetweightQuery sq = new SetweightQuery();
				double lastmoonth = sq.SQ(lastm);
				double nowmoonth = sq.SQ(nowm);
				double lastweek = sq.SQ(lastw);
				
				if(nowmoonth != -1)
				{
					minput.setText("" + nowmoonth); // ���õ� �� �� ������ ����
				
					double or; //����(BMI) ����
					double cm = Double.parseDouble(cminput.getText());
					double kg = Double.parseDouble(minput.getText());
					or = (kg/(cm*cm))*10000;
					or = Math.round(or*100)/100.0;
					outor.setText("BMI : " + or);
				
					if(or < 18.5)
					{
						outod.setText("��ü��");
						sl.setValue(0);
					}
					else if (or < 23)
					{
						outod.setText("����");
						sl.setValue(1);
					}
					else if (or < 25)
					{
						outod.setText("��ü��");
						sl.setValue(2);
					}
					else if (or < 30)
					{
						outod.setText("��");
						sl.setValue(3);
					}
					else
					{
						outod.setText("����");
						sl.setValue(4);
					}
				}
				
				if(lastweek != -1 && nowmoonth != -1) // ���� �ֿ� ������ ��
				{
					nowmoonth -= lastweek;
					
					if(nowmoonth < 0)
					{
						comw.setText("  ���� �� ��� " + Math.round(nowmoonth*10)/10.0 + " kg���� �߽��ϴ�.  ");
					}
					else if( nowmoonth > 0)
					{
						comw.setText("  ���� �� ��� " + Math.round(nowmoonth*10)/10.0 + " kg���� �߽��ϴ�.  ");
					}
					else
					{
						comw.setText("  ���� �ֿ� " + Math.round(lastweek*10)/10.0 + " kg�� ü���� �����ϴ�.  ");
					}
					
					nowmoonth += lastweek;
				}
				else
				{
					comw.setText("���� �� ��� kg����(����) �߽��ϴ�.");
				}
				
				if(lastmoonth != -1 && nowmoonth != -1) // ���� �ް� ������ ��
				{
					nowmoonth -= lastmoonth;
					
					if(nowmoonth < 0)
					{
						comm.setText("���� �� ��� " + Math.round(nowmoonth*10)/10.0 + " kg���� �߽��ϴ�.");
					}
					else if( nowmoonth > 0)
					{
						comm.setText("���� �� ��� " + Math.round(nowmoonth*10)/10.0 + " kg���� �߽��ϴ�.");
					}
					else
					{
						comm.setText("���� ���� " + Math.round(lastmoonth*10)/10.0 + " kg�� ü���� �����ϴ�.");
					}
					nowmoonth += lastmoonth;
				}
				else
				{
					comm.setText("���� �� ��� kg����(����) �߽��ϴ�.");
				}
				}
			}
		}
		
		if(e.getSource() == save) // �����ư
		{
			if(cminput.getText().trim().isEmpty() || minput.getText().trim().isEmpty()) // Ű�� ü���� �Էµ��� �ʾҴٸ�
			{
				Inputplease in = new Inputplease(); // ���̾�α� ���
			}
			else
				
			{
				double or; //����(BMI) ����
				double cm = Double.parseDouble(cminput.getText());
				double kg = Double.parseDouble(minput.getText());
				or = (kg/(cm*cm))*10000;
				or = Math.round(or*100)/100.0;
				outor.setText("BMI : " + or);
				
				if(or < 18.5)
				{
					outod.setText("��ü��");
					sl.setValue(0);
				}
				else if (or < 23)
				{
					outod.setText("����");
					sl.setValue(1);
				}
				else if (or < 25)
				{
					outod.setText("��ü��");
					sl.setValue(2);
				}
				else if (or < 30)
				{
					outod.setText("��");
					sl.setValue(3);
				}
				else
				{
					outod.setText("����");
					sl.setValue(4);
				}
				
				String date = choday.getText();
				int idx = date.indexOf(": "); // :�� �������� ���ڿ� �ڸ���
				String savedate = date.substring(idx+2); // XXXX�� XX�� XX�� ����

				InsertQuery iq = new InsertQuery(); // DB���� ������ ����
				iq.IQ(savedate, kg, cm);
				
				Loaddata();
				
				Complete co = new Complete(); // ���̾�α� ���
			}
		}
			
		if(e.getSource() == reset) // �ʱ�ȭ ��ư
		{
			minput.setText("");
			comm.setText("���� �� ��� kg����(����) �߽��ϴ�.");
			comw.setText("���� �� ��� kg����(����) �߽��ϴ�.");
			sl.setValue(0);
			outor.setText("����");
			outod.setText("������");
			choday.setText("���õ� ���� : " + gc.get(gc.YEAR)+ "�� "  + (gc.get(gc.MONTH)+1) + "�� " + gc.get(gc.DATE)+ "�� ");
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
					year = gc.get(gc.YEAR)+ "�� ";
					month = gc.get(gc.MONTH)+1+ "�� ";
					dates = gc.get(gc.DATE)+ "�� ";
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
		setTitle("ü�� �ϱ���");
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
			btn = new JButton("Ȯ��");
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
			setTitle("��� ����");
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
			btn = new JButton("Ȯ��");
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
			setTitle("������ ����");
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
			btn = new JButton("Ȯ��");
			msg = new JLabel("���������� ���� �Ǿ����ϴ�.");
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
			setTitle("�˸�");
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
		btn = new JButton("Ȯ��");
		label = new JLabel("Ű�� ü���� �Է��� �ּ���.");
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
		setTitle("�˸�");
		setSize(200, 100);
		setLocation(100, 300);
		setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("icon.jpg");
		setIconImage(img);
	}
}
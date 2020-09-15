package Gomoku.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GUIJFrame extends JPanel implements GUIConfig{
	
	private static final int COLUNM = 0;
	public Graphics g; // ����һ֧���ʡ�
	public int[][] chessPosition = new int [COLLUMN][ROW]; // ����һ����ά����洢���̵��������
	public ArrayList<ChessPosition> ChessPositionList = new ArrayList<ChessPosition>();//����ÿһ�����ӵ����
	public int turn = 0; // ����غ�ֵ������0ʱ�޷�����
	public int ChosseType = 0; // ������������ͣ�0��ʾ���˶�ս��1��ʾ�˻���ս
	
	public static HashMap<String,Integer> chessMap = new HashMap<String,Integer>();//���ò�ͬ�����������ӦȨֵ������
	static {
		
		//����ס
		chessMap.put("01", 25); //��1��
		chessMap.put("02", 22); //��1��
		chessMap.put("001", 17); //��1��
		chessMap.put("002", 12); //��1��
		chessMap.put("0001", 17); //��1��
		chessMap.put("0002", 12); //��1��
		
		chessMap.put("0102",25); //��1����15
		chessMap.put("0201",22); //��1����10
		chessMap.put("0012",15); //��1����15
		chessMap.put("0021",10); //��1����10
		chessMap.put("01002",25); //��1����15
		chessMap.put("02001",22); //��1����10
		chessMap.put("00102",17); //��1����15
		chessMap.put("00201",12); //��1����10
		chessMap.put("00012",15); //��1����15
		chessMap.put("00021",10); //��1����10

		chessMap.put("01000",25); //��1����15
		chessMap.put("02000",22); //��1����10
		chessMap.put("00100",19); //��1����15
		chessMap.put("00200",14); //��1����10
		chessMap.put("00010",17); //��1����15
		chessMap.put("00020",12); //��1����10
		chessMap.put("00001",15); //��1����15
		chessMap.put("00002",10); //��1����10
		
		//��ǽ��ס
		chessMap.put("0101",65); //��2����40
		chessMap.put("0202",60); //��2����30
		chessMap.put("0110",80); //��2����40
		chessMap.put("0220",76); //��2����30
		chessMap.put("011",80); //��2����40
		chessMap.put("022",76); //��2����30
		chessMap.put("0011",65); //��2����40
		chessMap.put("0022",60); //��2����30
				
		chessMap.put("01012",65); //��2����40
		chessMap.put("02021",60); //��2����30
		chessMap.put("01102",80); //��2����40
		chessMap.put("02201",76); //��2����30
		chessMap.put("01120",80); //��2����40
		chessMap.put("02210",76); //��2����30
		chessMap.put("00112",65); //��2����40
		chessMap.put("00221",60); //��2����30

		chessMap.put("01100",80); //��2����40
		chessMap.put("02200",76); //��2����30
		chessMap.put("01010",75); //��2����40
		chessMap.put("02020",70); //��2����30
		chessMap.put("00110",75); //��2����40
		chessMap.put("00220",70); //��2����30
		chessMap.put("00011",75); //��2����40
		chessMap.put("00022",70); //��2����30
				
		//����ס
		chessMap.put("0111",150); //��3����100
		chessMap.put("0222",140); //��3����80
				
		chessMap.put("01112",150); //��3����100
		chessMap.put("02221",140); //��3����80
			
		chessMap.put("01110", 1100); //��3��
		chessMap.put("02220", 1050); //��3��
		chessMap.put("01101",1000); //��3����130
		chessMap.put("02202",800); //��3����110
		chessMap.put("01011",1000); //��3����130
		chessMap.put("02022",800); //��3����110
				
		chessMap.put("01111",3000); //4����300
		chessMap.put("02222",3500);  //4����280
	}
	
	public int[][] weightArray = new int[COLLUMN][ROW];//����һ���µĶ�ά��������¼�������Ȩֵ
	
	/*
	//main function input
	public static void main(String[] args) {
		GUIJFrame gf = new GUIJFrame();
		gf.initialGUI();
	}
	*/
	
	public void initialGUI(){
		//Initialize an interface and set attributes such as title size
		JFrame jf = new JFrame();
		jf.setTitle("Welcome to Gomoku!");
		jf.setSize(850,635);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(3);
		
		jf.setLayout(new BorderLayout());//���ö�������JFrameΪ��ܲ���
		
		Dimension dim1 = new Dimension(230,0); //right size
		Dimension dim3 = new Dimension(550,0); //left size
		Dimension dim2 = new Dimension(140,40); //button size
		Dimension dim4 = new Dimension(170,30);
		Dimension userpic = new Dimension(120,120);
		
		//Define the size of the left interface, ��GoBangframe�Ķ�����ӵ���ܲ��ֵ��м䲿��
		//Already have a GUIFrame object��" this" is the object
		this.setPreferredSize(dim3); //Set the size of the chessboard
		this.setBackground(Color.LIGHT_GRAY);//Set chessboard color
		//Add left page to JFrame
		//There will be some minor problems if placed in other sections
		jf.add(this,BorderLayout.CENTER);//Add to the middle part of the frame layout
		
		//right JPanel pages
		JPanel jp = new JPanel();
		jp.setPreferredSize(dim1);        // Set JPanel size
		jp.setBackground(Color.white);    // Set the interface color on the right to white (�����ұߵĽ�����ɫΪ��ɫ)
		jf.add(jp,BorderLayout.EAST);     //Add to the east part of the frame layout
		jp.setLayout(new FlowLayout());   //Set JPanel to flow layout
		
		//Users information
		String[] userMessage = {"pic","name��Jiayi","sex��man","Level��Topplayers"};
		JLabel[] user = new JLabel[4];
		
		/* width and height can't be zero
		USERPICTURE.setImage(USERPICTURE.getImage().getScaledInstance(dim3.width, dim3.height,Image.SCALE_DEFAULT ));
		*/
		
		USERPICTURE.setImage(USERPICTURE.getImage().getScaledInstance(300, 200,Image.SCALE_DEFAULT ));
		user[0] = new JLabel(USERPICTURE);
		user[0].setPreferredSize(userpic);
		jp.add(user[0]);
		
		for(int i = 1;i < 4;i++){
			user[i]=new JLabel(userMessage[i]);
			user[i].setPreferredSize(dim4);
			/*
			 *����setFont������TextField�ı���������Ϣ�������С
			 *textx1.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
			 */	
			user[i].setFont(new Font(Font.MONOSPACED, Font.ITALIC,20));
			jp.add(user[i]);
		}
		
		//add the button on JPanen
		//set a button array
		String[] btnname = {"��ʼ����Ϸ","����","����"};
		ImageIcon[] BackgroundPicture = {STARTBUTTON,BACKBUTTON,LOSEBUTTON}; 
		JButton[] button = new JButton[3];
		
		//Use for loop to add button to JPnane
		for (int i = 0; i < btnname.length; i++) {
			BackgroundPicture[i].setImage(BackgroundPicture[i].getImage().getScaledInstance(dim2.width+20, dim2.height,Image.SCALE_DEFAULT ));
			button[i] = new JButton(btnname[i],BackgroundPicture[i]);
			button[i].setPreferredSize(dim2);
			jp.add(button[i]);
		}
		
		//Select box which is Human-vs-Human and Human-vs-Machine
		
		ImageIcon[] pic={BATTLEBUTTON1,BATTLEBUTTON2}; 
		JComboBox box = new JComboBox(pic);
		box.setPreferredSize(dim4);
		pic[0].setImage(pic[0].getImage().getScaledInstance(dim4.width, dim4.height,Image.SCALE_DEFAULT ));
		pic[1].setImage(pic[1].getImage().getScaledInstance(dim4.width, dim4.height,Image.SCALE_DEFAULT ));
		jp.add(box);

		//Create a monitoring object
		ButtonListener butListen = new ButtonListener(this,box);
		//add monitoring ����  for every button
		for(int i = 0; i < btnname.length; i++) {
			button[i].addActionListener(butListen); //Add a monitoring method for the operation to occur
		}
				
		// Add event monitoring mechanism to optional boxes
		box.addActionListener(butListen);
				
		frameListener fl = new frameListener();
		fl.setGraphics(this);   //get Graphics
		this.addMouseListener(fl);
				
		jf.setVisible(true);
	}
	
	public void PopUp(String top, String result){
		JOptionPane jo = new JOptionPane();
		jo.showMessageDialog(null, result, top, JOptionPane.PLAIN_MESSAGE);
	}
	
	//Redraw the chessboard function
	public void paint(Graphics g) {
		super.paint(g); // draw White frame
		//g.drawImage(CHESSBOARD, 0, 0, this.getWidth(), this.getHeight(), this); //��ӱ���ͼƬ
		
		
		Image icon = new ImageIcon("pic\\chessboard2.jpg").getImage();
		g.drawImage(icon, 0, 0, ROW*SIZE, COLLUMN*SIZE, null);  
		
		/*
		g.setColor(Color.black); 
		for(int i = 0; i < ROW; i++) {
			g.drawLine(X, Y+SIZE*i, X+SIZE*(COLLUMN-1), Y+SIZE*i);
		}
		for(int j = 0; j < COLLUMN; j++) {
			g.drawLine(X+SIZE*j, Y, X+SIZE*j, Y+SIZE*(ROW-1));
		}
		*/
		
		//draw the chess
		for(int i = 0; i < ROW; i++) {
			for(int j = 0;j < COLLUMN; j++) {
				if(chessPosition[i][j] == 1) {
					int countx = SIZE*j + 20;
					int county = SIZE*i + 20;
					g.drawImage(BLACKCHESS, countx-SIZE/2, county-SIZE/2, SIZE, SIZE,null);
				}else if(chessPosition[i][j] == 2) {
					int countx = SIZE*j + 20;
					int county = SIZE*i + 20;
					g.drawImage(WHITECHESS, countx-SIZE/2, county-SIZE/2, SIZE, SIZE,null);
				}
			}
		}
	}	
}

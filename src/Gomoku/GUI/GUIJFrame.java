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
	public Graphics g; // 定义一支画笔。
	public int[][] chessPosition = new int [COLLUMN][ROW]; // 定义一个二维数组存储棋盘的落子情况
	public ArrayList<ChessPosition> ChessPositionList = new ArrayList<ChessPosition>();//保存每一次落子的情况
	public int turn = 0; // 定义回合值：等于0时无法下棋
	public int ChosseType = 0; // 定义下棋的类型：0表示人人对战，1表示人机对战
	
	public static HashMap<String,Integer> chessMap = new HashMap<String,Integer>();//设置不同落子情况和相应权值的数组
	static {
		
		//被堵住
		chessMap.put("01", 25); //眠1连
		chessMap.put("02", 22); //眠1连
		chessMap.put("001", 17); //眠1连
		chessMap.put("002", 12); //眠1连
		chessMap.put("0001", 17); //眠1连
		chessMap.put("0002", 12); //眠1连
		
		chessMap.put("0102",25); //眠1连，15
		chessMap.put("0201",22); //眠1连，10
		chessMap.put("0012",15); //眠1连，15
		chessMap.put("0021",10); //眠1连，10
		chessMap.put("01002",25); //眠1连，15
		chessMap.put("02001",22); //眠1连，10
		chessMap.put("00102",17); //眠1连，15
		chessMap.put("00201",12); //眠1连，10
		chessMap.put("00012",15); //眠1连，15
		chessMap.put("00021",10); //眠1连，10

		chessMap.put("01000",25); //活1连，15
		chessMap.put("02000",22); //活1连，10
		chessMap.put("00100",19); //活1连，15
		chessMap.put("00200",14); //活1连，10
		chessMap.put("00010",17); //活1连，15
		chessMap.put("00020",12); //活1连，10
		chessMap.put("00001",15); //活1连，15
		chessMap.put("00002",10); //活1连，10
		
		//被墙堵住
		chessMap.put("0101",65); //眠2连，40
		chessMap.put("0202",60); //眠2连，30
		chessMap.put("0110",80); //眠2连，40
		chessMap.put("0220",76); //眠2连，30
		chessMap.put("011",80); //眠2连，40
		chessMap.put("022",76); //眠2连，30
		chessMap.put("0011",65); //眠2连，40
		chessMap.put("0022",60); //眠2连，30
				
		chessMap.put("01012",65); //眠2连，40
		chessMap.put("02021",60); //眠2连，30
		chessMap.put("01102",80); //眠2连，40
		chessMap.put("02201",76); //眠2连，30
		chessMap.put("01120",80); //眠2连，40
		chessMap.put("02210",76); //眠2连，30
		chessMap.put("00112",65); //眠2连，40
		chessMap.put("00221",60); //眠2连，30

		chessMap.put("01100",80); //活2连，40
		chessMap.put("02200",76); //活2连，30
		chessMap.put("01010",75); //活2连，40
		chessMap.put("02020",70); //活2连，30
		chessMap.put("00110",75); //活2连，40
		chessMap.put("00220",70); //活2连，30
		chessMap.put("00011",75); //活2连，40
		chessMap.put("00022",70); //活2连，30
				
		//被堵住
		chessMap.put("0111",150); //眠3连，100
		chessMap.put("0222",140); //眠3连，80
				
		chessMap.put("01112",150); //眠3连，100
		chessMap.put("02221",140); //眠3连，80
			
		chessMap.put("01110", 1100); //活3连
		chessMap.put("02220", 1050); //活3连
		chessMap.put("01101",1000); //活3连，130
		chessMap.put("02202",800); //活3连，110
		chessMap.put("01011",1000); //活3连，130
		chessMap.put("02022",800); //活3连，110
				
		chessMap.put("01111",3000); //4连，300
		chessMap.put("02222",3500);  //4连，280
	}
	
	public int[][] weightArray = new int[COLLUMN][ROW];//设置一个新的二维数组来记录各个点的权值
	
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
		
		jf.setLayout(new BorderLayout());//设置顶级容器JFrame为框架布局
		
		Dimension dim1 = new Dimension(230,0); //right size
		Dimension dim3 = new Dimension(550,0); //left size
		Dimension dim2 = new Dimension(140,40); //button size
		Dimension dim4 = new Dimension(170,30);
		Dimension userpic = new Dimension(120,120);
		
		//Define the size of the left interface, 把GoBangframe的对象添加到框架布局的中间部分
		//Already have a GUIFrame object，" this" is the object
		this.setPreferredSize(dim3); //Set the size of the chessboard
		this.setBackground(Color.LIGHT_GRAY);//Set chessboard color
		//Add left page to JFrame
		//There will be some minor problems if placed in other sections
		jf.add(this,BorderLayout.CENTER);//Add to the middle part of the frame layout
		
		//right JPanel pages
		JPanel jp = new JPanel();
		jp.setPreferredSize(dim1);        // Set JPanel size
		jp.setBackground(Color.white);    // Set the interface color on the right to white (设置右边的界面颜色为白色)
		jf.add(jp,BorderLayout.EAST);     //Add to the east part of the frame layout
		jp.setLayout(new FlowLayout());   //Set JPanel to flow layout
		
		//Users information
		String[] userMessage = {"pic","name：Jiayi","sex：man","Level：Topplayers"};
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
			 *利用setFont来设置TextField文本框输入信息的字体大小
			 *textx1.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
			 */	
			user[i].setFont(new Font(Font.MONOSPACED, Font.ITALIC,20));
			jp.add(user[i]);
		}
		
		//add the button on JPanen
		//set a button array
		String[] btnname = {"开始新游戏","悔棋","认输"};
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
		//add monitoring 监听  for every button
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
		//g.drawImage(CHESSBOARD, 0, 0, this.getWidth(), this.getHeight(), this); //添加背景图片
		
		
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

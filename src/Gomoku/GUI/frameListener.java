package Gomoku.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Dimension;
//实现对GoBangframe下棋界面的监听接口处理
public class frameListener implements GUIConfig,MouseListener {
	
	public GUIJFrame gf;
	GUIConfig go;
	
	public void setGraphics(GUIJFrame gf) {
		this.gf = gf;
	}
	/*
	//AI联合算法
	public Integer unionWeight(Integer a, Integer b) {
		//必须要先判断a,b两个数值是不是null
		if((a == null) || (b == null)){
			return 0; //一一:101/202
		}else if((a >= 22) && (a <= 25) && (b >= 22) && (b <= 25)) {
			return 60; // 一二、二一: 1011/2022
		}else if(((a >= 22) && (a <= 25) && (b >= 76) && (b <= 80)) || 
				((a >= 76) && (a <= 80) && (b >= 22) && (b <= 25))) {
			return 800; //一三、三一、二二:10111/20222
		}else if(((a >= 10) && (a <= 25) && (b >= 1050) && (b <= 1100))
				|| ((a >= 1050) && (a <= 1100) && (b >= 10) && (b <= 25))
				|| ((a >= 76) && (a <= 80) && (b >= 76) && (b <= 80))) {
			return 3000; //眠三连和眠一连。一三、三一
		}else if(((a >= 22) && (a <= 25) && (b >= 140) && (b <= 150))
				|| ((a >= 140) && (a <= 150) && (b >= 22) && (b <= 25))) {
			return 3000; //二三、三二 : 110111
		}else if(((a >= 76) && (a <= 80) && (b >= 1050) && (b <= 1100))
				|| ((a >= 1050) && (a <= 1100) && (b >= 76) && (b <= 80))) {
			return 3000;
		}else {
			return 0;
		}
	}
	*/
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		 //Calculate at which intersection of the chessboard the piece will fall计算棋子要落在棋盘的哪个交叉点上
		int countx = (x/40) * 40 + 20;
		int county = (y/40) * 40 + 20;
		Graphics g = gf.getGraphics();
		//Calculate the corresponding position of the chess piece on the chessboard in the array计算棋盘上棋子在数组中相应的位置
		int Arrayj = (countx - 20) / 40;
		int Arrayi = (county - 20) / 40;
		if(gf.turn != 0){ //Judgment whether can play 
			if(gf.chessPosition[Arrayi][Arrayj] != 0) {
				gf.PopUp("Warning","There are already chess pieces here, please play elsewhere");
				
			}else {
				 //Human-vs-Human
				if(gf.ChosseType == 0) {
					if(gf.turn == 1) {
						 //Get the chess position
						g.drawImage(BLACKCHESS, countx-SIZE/2, county-SIZE/2, SIZE, SIZE,null);
						//Set this position already have a chess,which is the black chess
						gf.chessPosition[Arrayi][Arrayj] = 1;
						 //Add this position to the array
						gf.ChessPositionList.add(new ChessPosition(Arrayi,Arrayj));
						gf.turn++;
						 
						//Judgment whether have five piece
						//Column judgment
						//First define the scope of the array to prevent out of bounds
						int imin = Arrayi - 4,imax = Arrayi + 4;
						if(imin < 0) {
							imin = 0;
						}if(imax > 14) {
							imax = 14;
						}
						int count1 = 0;//Judgment the connect chess
						for(int i = imin; i <= imax; i++) {
							if(gf.chessPosition[i][Arrayj] == 1) {
								count1++; 
							}else { //If have another chess or don't have chess at next position, Recalculate
								count1 = 0;
							}
							if(count1 == 5) {
								gf.PopUp("Game over","Black Win!!");
								gf.turn = 0;
								return ;
							}
						}
						
						//Row judgment 
						//First define the scope of the array to prevent out of bounds
						int jmin = Arrayj - 4, jmax = Arrayj + 4;
						if(jmin < 0) {
							jmin = 0;
						}if(jmax > 14) {
							jmax = 14;
						}
						int count2 = 0;//Judgment the connect chess
						for(int j = jmin; j <= jmax; j++) {
							 if(gf.chessPosition[Arrayi][j] == 1) {
								 count2++;
							 }else {
								 count2 = 0;
							 }
							 if(count2 == 5) {
								 gf.PopUp("Game over","Black Win!!");
								 gf.turn = 0;
								 return;
							 }
							
					}
						
						//Oblique(135°) judgment
						int count3 = 0;
						for(int i = -4;i <= 4; i++) {
							if((Arrayi+i >= 0)&&(Arrayj+i >= 0)
									&&(Arrayi+i <= 14)&&(Arrayj+i <= 14)) {
								if(gf.chessPosition[Arrayi+i][Arrayj+i] == 1) {
									count3++;
								}
								else { 
									count3 = 0;
								}
								if(count3 == 5) {
									gf.PopUp("Game over","Black Win!!");
									gf.turn = 0;
									return;
								  }
							  }
						  }
						 int count4 = 0;
						 for(int i = -4; i <= 4;i++) {
							 if((Arrayi+i >= 0) && (Arrayj-i >= 0)
									 && (Arrayi+i <= 14) && (Arrayj-i <= 14)) {
								//System.out.print("count4:"+count4);
								if(gf.chessPosition[Arrayi+i][Arrayj-i] == 1) {
									count4++;
								}
								else { 
									count4 = 0;
								}
								if(count4 == 5) {
									gf.PopUp("Game over","Black Win!!");
									gf.turn = 0;
									return;
								}
							 }
						 }
					  }else if(gf.turn == 2) {
						  g.drawImage(WHITECHESS,countx-SIZE/2, county-SIZE/2, SIZE, SIZE,null);
						  //Set this position already have a chess,which is the white chess
						  gf.ChessPositionList.add(new ChessPosition(Arrayi,Arrayj));
						  gf.chessPosition[Arrayi][Arrayj] = 2;
						  gf.turn--;
						  
						  //Judgment whether have five piece
						  //Column judgment
						  //First define the scope of the array to prevent out of bounds
						  int imin = Arrayi - 4, imax = Arrayi + 4;
						  if(imin < 0) {
							  imin = 0;
						  }
						  if(imax > 14) {
							  imax = 14;
						  }
						  int count1 = 0;
						  for(int i = imin; i <= imax; i++) {
							  if(gf.chessPosition[i][Arrayj] == 2) {
								  count1++;
							  }else {
								  count1 = 0;
							  }
							  if(count1 == 5) {
								  gf.PopUp("Game over", "White Win!!");
								  gf.turn = 0;
								  return;
							  }
						  }
						  
						  int jmin = Arrayj - 4, jmax = Arrayj + 4;
						  if(jmin < 0) {
							  jmin = 0;
						  }
						  if(jmax > 14) {
							  jmax = 14;
						  }
						  //Row judgment 
						  //First define the scope of the array to prevent out of bounds
						  int count2 = 0;
						  for(int j = jmin; j <= jmax; j++) {
							  if(gf.chessPosition[Arrayi][j] == 2) {
								  count2++;
							  }else {
								  count2 = 0;
							  }
							  if(count2 == 5) {
								  gf.PopUp("Game over", "White Win!!");
								  gf.turn = 0;
								  return;
							  }
						  }
						  //Oblique(135°) judgment
						  int count3 = 0;
						  for(int i = -4; i <= 4; i++) {
							  if((Arrayi+i >= 0) && (Arrayj+i >= 0)
									  && (Arrayi+i <= 14) && (Arrayj+i <= 14)) {
								  if(gf.chessPosition[Arrayi+i][Arrayj+i] == 2) {
									  count3++;
								  }else {
									  count3 = 0;
								  }
								  if(count3 == 5) {
									  gf.PopUp("Game over", "White Win!!");
									  gf.turn = 0;
									  return;
								  }
							  }
						  }
						  int count4 = 0;
						  for(int i = -4; i <= 4; i++) {
							  if((Arrayi+i >= 0) && (Arrayj-i >= 0)
									  && (Arrayi+i <= 14) && (Arrayj-i <=14)) {
								  if(gf.chessPosition[Arrayi+i][Arrayj-i] == 2) {
									  count4++;
								  }else {
									  count4 = 0;
								  }
								  if(count4 == 5) {
									  gf.PopUp("Game over", "White Win!!");
									  gf.turn = 0;
									  return;
								  }
							  }
						  }
					  }
					}
				
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

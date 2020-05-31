import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private Image backGroundImage;
	private BreakerBall[] bBall;
	private Brick[][] bricks;
	private ArrayList<PowerUps> powerUps;
	private boolean isDoublePoints;
	private int score;
	private int scoreMultiplayer;
	private int moveSpeed;
	private int lifes;
	private int level;
	public GamePanel()
	{
		ImageIcon ii = new ImageIcon("background.jpg");
		backGroundImage = ii.getImage();
		player = new Player(0,getScreenHeight()-80, 300, 100, this);
		bBall = new BreakerBall[3];
		bBall[0] =  new BreakerBall(player.getX()+player.getWidth(),player.getY()-100, 50, 30, this);
		bricks = new Brick[5][8];
		score=0;
		scoreMultiplayer = 0;
		moveSpeed = 10;
		lifes = 3;
		powerUps = new ArrayList<PowerUps>();
		isDoublePoints = false;
		level = 1;
		int y=80;	
		for(int i=0;i<5;i++)
		{
			int x=10;
			for(int j=0;j<8;j++)
			{
				bricks[i][j]= new Brick(x,y,200,50,this, false);
				x+=240;
			}
			y+=100;
		}
		
		
		addKeyListener(new KL ());
		this.setFocusable(true);
	}
	
	
	public void paintComponent(Graphics g)
	{
		int i;
		boolean finishLevel=true;
		super.paintComponent(g);
		int fontSize = 20;
		g.drawImage(backGroundImage, 0,0,getWidth(),getHeight(), null);
	    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	     
	    g.setColor(Color.red);
	    g.drawString("Level:" + this.level, 500, 40);
	    g.drawString("Score:" + this.score, 50, 40);
	    g.drawString("Lifes:" , 150, 40);
	    ImageIcon ii = new ImageIcon("Ball.png");
	    for(i=0;i<lifes;i++) {
	    	g.drawImage(ii.getImage(), 200 + i*80, 5, 50, 50, null);
	    }
		player.drawPlayer(g);
		
		for(i=0;i<bBall.length;i++) {
			if(bBall[i]!= null && bBall[i].isAlive) {
				bBall[i].drawBall(g);
			}
		}
		//draw powerUps
		for ( i = 0; i < powerUps.size(); i++) {
			PowerUps p = powerUps.get(i);
			if(p.isAlive) {
				p.drawpowerUps(g);
				
			}
		}
		if(level==1) {
			//draws bricks level 1
			for(i=0;i<5;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(!bricks[i][j].isHit)
					{
						bricks[i][j].drawBrick(g);
						finishLevel =false;
					}
				}
				
			}
		}
		
	
		if(finishLevel && level==1){
					
			level=2;
			
			bBall = new BreakerBall[3];
			bBall[0] =  new BreakerBall(player.getX()+player.getWidth(),player.getY()-100, 50, 30, this);
			bricks = new Brick[4][7];
			int y=80;	
			for( i=0;i<4;i++)
			{
				int x=10;
				for(int j=0;j<7;j++)
				{
					bricks[i][j]= new Brick(x,y,200,50,this,false);
					x+=280;
				}
				y+=150;
			}
		}
		if(level ==2) {
			//draws bricks level 1
			for(i=0;i<4;i++)
			{
				for(int j=0;j<7;j++)
				{
						if(bricks[i][j].lifes==2) {
							bricks[i][j].drawBrick(g);
							finishLevel =false;
						}if(bricks[i][j].lifes==1) {
							bricks[i][j].isHit=false;
							bricks[i][j].drawBrokenBrick(g);
							finishLevel =false;
						}
						
						
					
				}
				
			}
		}
		if(finishLevel && level==2) {
			g.setColor(new Color (255,255,255,80));
			g.fillRect(0, 0, getWidth(), getHeight());
			Font myFont = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(myFont);
			g.setColor(Color.red);
			g.drawString("YOU WON!!!",getWidth()/2,getHeight()/2);
			g.drawString("Your score is:" + this.score,getWidth()/2,getHeight()/2 + 100);
			player.isPaused=true;
			for(i=0;i<bBall.length;i++) {
				if(bBall[i]!=null){
					bBall[i].pause();
    			}
				
			}
			for ( i = 0; i < powerUps.size(); i++) {
				PowerUps p = powerUps.get(i);
				if(p.isAlive) {
					p.isPause=true;
				}
			}
		}
		if (player.isPaused == true)
		  {
			g.setColor(new Color (255,255,255,80));
			g.fillRect(0, 0, getWidth(), getHeight());
			Font myFont = new Font("TimesRoman", Font.BOLD, 50);
			g.setFont(myFont);
			g.setColor(Color.red);
			g.drawString("PAUSE",getWidth()/2,getHeight()/2);
		  }
	}
	public Brick[][] getBricks() {return this.bricks;}
	public Player getPlayer() { return this.player;}
	public BreakerBall[] getBall() {return this.bBall;}
	public int getScore() {return this.score;}
	public void incScoreMultiplayer() {this.scoreMultiplayer++;}
	public void setDefaultScoreMultiplayer() {this.scoreMultiplayer=0;}
	public int getScoreMultiplayer() {return this.scoreMultiplayer;}
	public void activateDoublepoints() {this.isDoublePoints=true;}
	public void deactivateDoublepoints() {this.isDoublePoints=false;}
	public void incLifes() {if(this.lifes<4)this.lifes++;}
	public void decLifes() {this.lifes--;}
	public void setMoveSpeed(int moveSpeed) {this.moveSpeed = moveSpeed;}
	public void decScore(int amount) {
		if(this.score<amount){
		this.score =0;
		}else {
			this.score -= amount;
		}
	}
	public void addScore(int s) 
	{
		if(isDoublePoints)
			this.score += s * scoreMultiplayer * 2;
		else
			this.score += s * scoreMultiplayer;
		}
	public void addPowerUp(PowerUps p) {this.powerUps.add(p);}
	public void addBall(BreakerBall b) {
		for(int i=0;i<bBall.length;i++) {
			if(bBall[i] == null) {
				bBall[i]=b;
			}
		}
	}
	public void removeBall(int index) {bBall[index]=null;}
	/**
	 * get screen dimension
	 * @return an Dimension object
	 */
	public static Dimension getScreenSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	
	/**
	 *  gets the width of the screen
	 * @return the width of the screen
	 */
	public static int getScreenWidth()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.width;
	}
	/**
	 * gets the height of the screen
	 * @return the height of the screen
	 */
	public static int getScreenHeight()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.height;
	}
	class KL extends KeyAdapter
    {
		/**
		 * Input from user, left and right arrows to move the player, 
		 *  space - release the ball from the player and start the game,
		 *  pressing p-pauses the game pressing r-resumes the game 
		 */
		public void keyPressed(KeyEvent e)
		{	
			int i;
			int code=e.getKeyCode();			
			switch(code)
			{
			case KeyEvent.VK_P:
				player.isPaused=true;
				for(i=0;i<bBall.length;i++) {
					if(bBall[i]!=null){
						bBall[i].pause();
	    			}
					
				}
				for ( i = 0; i < powerUps.size(); i++) {
					PowerUps p = powerUps.get(i);
					if(p.isAlive) {
						p.isPause=true;
					}
				}
				
				//TODO pause event
				break;
			case KeyEvent.VK_R:		
				player.isPaused=false;
				for(i=0;i<bBall.length;i++)
				{
					if(bBall[i]!=null)
					{
						synchronized (bBall[i])
						{	
							bBall[i].notify();
							bBall[i].unPause();
						}
						
								
					}
				}
				for ( i = 0; i < powerUps.size(); i++) {
					PowerUps p = powerUps.get(i);
					if(p.isAlive) {
						p.isPause=false;
					}
				}
				player.isPaused=false;
				
				//TODO resume event
				break;
			case KeyEvent.VK_RIGHT:
				//TODO add right move
				if(player.getX() < GamePanel.getScreenSize().width- player.getWidth() && !player.isPaused)
				{
					
					for(i=0;i<bBall.length;i++) {
						if(bBall[i]!=null){
							if(!bBall[i].isStarted())
							{
								bBall[i].setX(bBall[i].getX()+moveSpeed);
							}
		    			}
						
					}

					//bBall.hit(getScreenSize().height, getScreenSize().width, player);
					player.setX(player.getX()+moveSpeed);				
				}
				break;
			case KeyEvent.VK_LEFT:
				if(player.getX()>0 && !player.isPaused)
				{
					for(i=0;i<bBall.length;i++) {
						if(bBall[i]!=null){
							if(!bBall[i].isStarted())
							{
								bBall[i].setX(bBall[i].getX()-moveSpeed);
							}
		    			}
					}
					//bBall.hit(getScreenSize().height, getScreenSize().width, player);
					player.setX(player.getX()-moveSpeed);
					
				}
				break;
			case KeyEvent.VK_SPACE:
				for(i=0;i<bBall.length;i++) {
					if(bBall[i]!=null){
						if(!bBall[i].isStarted())
						{
							bBall[i].startBall();
						}
	    			}
				}
				break;
			case KeyEvent.VK_C:
				for(i=0;i<5;i++)
				{
					for(int j=0;j<8;j++)
					{
						bricks[i][j].isHit=true;
					}
					
				}
				break;
			}
			
		}
	}
		/**
		 * A method to hide the mouse cursor while playing the game 
		 */
		public void  hideMouseCursor(){
			 //Transparent 16 x 16 pixel cursor image.
			BufferedImage cursorimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

			// Create a new blank cursor.
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorimg, new Point(0, 0), "blank cursor");

			// Set the blank cursor to the JPanel.
			setCursor(blankCursor);	
		}
		
		
		public static void main(String[] args) {
			JFrame s = new JFrame("MarioBreaker menu");
			s.getContentPane().setBackground( Color.black );
			s.setSize(1024, 768);
			s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			s.setResizable(false);
			s.setVisible(true);	
			s.setFocusable(false);
			
			Font font = new Font("TimesRoman", Font.BOLD, 30);
			JButton start= new JButton("Start");
			start.setSize(350,100);
			start.setLocation(300, 250);
			start.setFont(font);		
			start.setBackground(Color.red);
			start.setForeground(Color.black);
			JButton info = new JButton("Info");
			info.setSize(350,100);
			info.setLocation(300, 400);
			info.setFont(font);
			info.setBackground(Color.red);
			info.setForeground(Color.black);

			
			start.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					JFrame f=new JFrame("MarioBreaker");
					GamePanel bp = new GamePanel();
					f.setSize(getScreenSize());
					f.add(bp);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setResizable(false);
					f.setVisible(true);	
					f.setFocusable(false);	
					//bp.hideMouseCursor();
				}
			});
			info.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					JOptionPane.showMessageDialog(s,"Welcome to Mario Breaker \n"
							+ "Press on the Start button in order to start the game!\n"
							+ "Controls: arrow keys to move right and left, spacebar to throw ball\n"
							+ "Good PowerUps:increase length of racket, double points, extra ball, extra life\n"
							+ "Bad PowerUps: increase speed, decrease speed, minus points, decrease length of racket" );
				}
			});
			JLabel l = new JLabel("");
			s.add(start);
			s.add(info);
			s.add(l);
			
			
			/*
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
		
}




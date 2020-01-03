import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private Image backGroundImage;
	private BreakerBall bBall;
	private Brick[][] bricks;
	private int score;
	private int scoreMultiplayer;
	public GamePanel()
	{
		ImageIcon ii = new ImageIcon("background.jpg");
		backGroundImage = ii.getImage();
		player = new Player(0,getScreenHeight()-80, 300, 100, this);
		bBall = new BreakerBall(player.getX()+player.getWidth(),player.getY()-100, 100, 50, this);
		bricks = new Brick[5][8];
		score=0;
		scoreMultiplayer = 0;
		int y=10;
		//int x=10;
		/*
		for(int i=0;i<bricks.length * bricks[0].length;i++)
		{
			bricks[i % bricks.length][i / bricks.length]= new Brick(x,y,200,150,this);
			x+=240;
			if(i%8==0)
			{
				x=10;
				y+=100;
			}
		}
		*/
		
		for(int i=0;i<5;i++)
		{
			int x=10;
			for(int j=0;j<8;j++)
			{
				bricks[i][j]= new Brick(x,y,200,50,this);
				x+=240;
			}
			y+=100;
		}
		
		
		addKeyListener(new KL ());
		this.setFocusable(true);
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGroundImage, 0,0,getWidth(),getHeight(), null);
		player.drawPlayer(g);
		bBall.drawBall(g);
		//draws bricks
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(!bricks[i][j].isHit)
				{
					bricks[i][j].drawBrick(g);
				}
			}
			
		}
		
	}
	public void removeBrick(int x, int y)
	{
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(bricks[i][j].getX()==x && bricks[i][j].getY()==y)
				{
					bricks[i][j]=null;
				}
			}
			
		}
		System.out.println("yay");
		this.repaint();
	}
	public Brick[][] getBricks() {return this.bricks;}
	public Player getPlayer() { return this.player;}
	public BreakerBall getBall() {return this.bBall;}
	public int getScore() {return this.score;}
	public void incScoreMultiplayer() {this.scoreMultiplayer++;}
	public void setDefaultScoreMultiplayer() {this.scoreMultiplayer=0;}
	public int getScoreMultiplayer() {return this.scoreMultiplayer;}
	public void addScore(int s) {
		this.score += s * scoreMultiplayer;
	}
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
			int code=e.getKeyCode();			
			switch(code)
			{
			case KeyEvent.VK_P:
				player.isPaused=true;
				//TODO pause event
				break;
			case KeyEvent.VK_R:		
				player.isPaused=false;
				//TODO resume event
				break;
			case KeyEvent.VK_RIGHT:
				//TODO add right move
				if(player.getX() < GamePanel.getScreenSize().width- player.getWidth())
				{
					if(!bBall.isStarted())
					{
						bBall.setX(bBall.getX()+10);
						//System.out.println(bBall.getY());
					}
					//bBall.hit(getScreenSize().height, getScreenSize().width, player);
					player.setX(player.getX()+10);				
				}
				break;
			case KeyEvent.VK_LEFT:
				if(player.getX()>0)
				{
					if(!bBall.isStarted())
					{
						bBall.setX(bBall.getX()-10);
					}
					//bBall.hit(getScreenSize().height, getScreenSize().width, player);
					player.setX(player.getX()-10);
					
				}
				break;
			case KeyEvent.VK_SPACE:
				bBall.startBall();
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
			JFrame f=new JFrame("MarioBreaker");
			GamePanel bp = new GamePanel();
			f.setSize(getScreenSize());
			f.add(bp);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
			f.setVisible(true);	
			f.setFocusable(false);			
			
			//bp.hideMouseCursor();
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




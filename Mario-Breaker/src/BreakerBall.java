
import javax.swing.*;
import java.awt.*;

public class BreakerBall  extends Thread {

	private int x; // x coordinate of the ball 
	private int y; // y coordinate of the ball
	
	private GamePanel panel; // game panel
	private Image ballImage; // Image of the ball
	
	private int numOfBalls; // number of balls in the game 
	private int width;
	private int height;
	private boolean isStarted; 
	private int dirx;
	private int diry;
	
	public BreakerBall(int x, int y, int width, int height, GamePanel panel)
	{
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		ImageIcon ii = new ImageIcon("Ball.png");
		ballImage = ii.getImage();
		this.panel = panel;
		isStarted = false;
		dirx=1;
		diry=-1;
		
	}
	
	public void drawBall(Graphics g){
		g.drawImage(ballImage, x,y,width,height, null);
		g.setColor(Color.green);
		g.drawRect(x, y, width, height);
	}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getWidth() { return this.width;}
	public int getHeight() { return this.height;}
	public int getNumOfBalls() {return this.numOfBalls;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public void removeBall() {this.numOfBalls--;}
	public void addBall() {this.numOfBalls++;}
	public boolean isStarted() {return this.isStarted;}
	public void startBall() { 
		this.isStarted=true;
		this.start();}

	
	
	public void hitPlayer(Player p) { 
		if(this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight() &&(this.x >= p.getX() && this.x<=p.getX()+p.getWidth()))
		{
			

			setDirAfterHitPlayer(p);
			System.out.println("right corner");
			
		}
		
		
		if((this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight()) &&(this.x + width >= p.getX() && this.x + width <= p.getX() + +p.getWidth()))
		{
			setDirAfterHitPlayer(p);
			System.out.println("left corner");
		}
	}
	/**
	 * sets the direction of the breaker ball after it hits the player
	 * @param p player object
	 */
	private void setDirAfterHitPlayer(Player p) 
	{
		//TODO change middle hit so that if the dirx = 0 than the ball goes to the sides after a hit
		int playerX = p.getX();
		int length = p.getWidth();
		if(this.dirx==0)
		{
			if(this.x < (length/2 + playerX))
			{
				this.dirx = 1;
				this.diry = -1;
				System.out.println("left after middle ");
				
			}
			if(this.x > ((length/2)  + playerX))
			{
				this.dirx = -1;
				this.diry = -1;
				System.out.println("right after middle ");
			}
		}
		if(this.x < (length/3 + playerX))
		{
			this.dirx = 1;
			this.diry = -1;
			System.out.println("left ");
			
		}
		if((this.x > length/3 + playerX) && (this.x < (length/3) * 2 + playerX))
		{
			this.dirx = 0;
			this.diry = -1;
			System.out.println("middle ");
		}
		if(this.x > ((length/3) * 2 + playerX))
		{
			this.dirx = -1;
			this.diry = -1;
			System.out.println("right ");
		}
	}
	/**
	 * sets the direction of the breaker ball after it hits a brick
	 * @param b brick object
	 */
	public void setDirAfterHitBrick(Brick b)
	{
		//bugs 
		//hit 2 bricks - the ball will keep going up
		//hit the sides 
		System.out.println("asdasd");
//		if(b.getY() + b.getHeight() == this.y) //bottom
//		{
//			System.out.println("bottom");
//			this.diry=1;
//			
//		}
//		
//		if(b.getY() == this.y) // top
//		{
//			System.out.println("top");
//		}
//		if(b.getY()<this.y && b.getY() + b.getHeight() > this.y) // sides
//		{
//			System.out.println("sides");
//		}
		this.diry = this.diry*-1;
	}
	
	/**
	 *  calculate the distance between A and B with pythagoras theorem
	 * @param a 
	 * @param b
	 * @return the distance 
	 */
	public  double distance(int a,int b){
		return Math.sqrt(Math.pow(a,2.0)+Math.pow(b,2.0));
	}
	
	public void run()
	{
		Player p = panel.getPlayer();
		while(true)
		{
			
			
			int h=panel.getHeight();
			int w=panel.getWidth();
			hitPlayer(p);
			
			if (x + width/2  > w)
				dirx = -1;
			
			if (x-width/2  < 0)
				dirx = 1;
			
			if (y + height/2  > h)
				diry = -1;
			
			if (y + height*2 < p.getHeight())
				diry = 1;
			
				//break;
			//TODO add a lost screen
			
			x+=dirx;
			y+=diry;
		    try {
		    	Thread.sleep(5);

			
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			panel.repaint();
		}
	
	
	}
}

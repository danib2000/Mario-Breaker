
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
	private boolean hitPlayer;
	private int dirx;
	private int diry;
	private int speed;
	private int scoreBoardHeight; 
	private boolean isPaused;
	boolean isAlive;
	
	
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
		hitPlayer = true;
		dirx=1;
		diry=-1;
		speed=5;
		scoreBoardHeight=65;
		isPaused = false;
		isAlive = true;
		
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
		System.out.println(isStarted);
		this.start();}
	public boolean hitPlayer() { return this.hitPlayer;}
	public void changeHitPlayer() {this.hitPlayer= !this.hitPlayer; }
	public void setSpeed(int speed) {this.speed =speed;}
	public void pause(){this.isPaused = true;}
	public void unPause(){this.isPaused = false;}
	public void hitPlayer(Player p) { 
		if(this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight() &&(this.x >= p.getX() && this.x<=p.getX()+p.getWidth()))
		{
			

			setDirAfterHitPlayer(p);
			panel.setDefaultScoreMultiplayer();
			//System.out.println("right corner");
			
		}
		
		
		if((this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight()) &&(this.x + width >= p.getX() && this.x + width <= p.getX() + +p.getWidth()))
		{
			setDirAfterHitPlayer(p);
			panel.setDefaultScoreMultiplayer();
			//System.out.println("left corner");
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
//		if(this.dirx==0)
//		{
//			if(this.x < (length/2 + playerX))
//			{
//				this.dirx = 1;
//				this.diry = -1;
//				//System.out.println("left after middle ");
//				
//			}
//			if(this.x > ((length/2)  + playerX))
//			{
//				this.dirx = -1;
//				this.diry = -1;
//				//System.out.println("right after middle ");
//			}
//		}
//		if(this.x < (length/3 + playerX))
//		{
//			this.dirx = 1;
//			this.diry = -1;
//			//System.out.println("left ");
//			
//		}
//		if((this.x > length/3 + playerX) && (this.x < (length/3) * 2 + playerX))
//		{
//			this.dirx = 0;
//			this.diry = -1;
//			//System.out.println("middle ");
//		}
//		if(this.x > ((length/3) * 2 + playerX))
//		{
//			this.dirx = -1;
//			this.diry = -1;
//			//System.out.println("right ");
//		}
		if(this.dirx == -1) {
			this.dirx = 1;
			this.diry = -1;
		}else {
			this.dirx = -1;
			this.diry = -1;
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
		boolean sides=false;
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
		//System.out.println(String.format("brick.x: %d, brick.y: %d, ball.x: %d, ball.y: %d",b.getX(), b.getY(), this.getX(), this.getY() ));
		//test if hits from the site
		if(this.x + this.width > b.getX()  && this.y > b.getY() && this.y < b.getY() + b.getHeight()) {
			if(this.y + this.height > b.getY() && this.y + this.height < b.getY() + b.getHeight()) {
				sides= true;
			}
		}
		if(this.x  < b.getX() + b.getWidth()  && this.y > b.getY() && this.y < b.getY() + b.getHeight()) {
			if(this.y + this.height > b.getY() && this.y + this.height < b.getY() + b.getHeight()) {
				sides= true;

			}
		}
		if(sides) {
			this.dirx = this.dirx*-1;
		}else {
			this.diry = this.diry*-1;
		}
	}
	
	
	public void run()
	{
		Player p = panel.getPlayer();
		while(isAlive)
		{
			if(isPaused)
			{
				synchronized (this)
				{
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			
			int h=panel.getHeight();
			int w=panel.getWidth();
			hitPlayer(p);
			
			if (x + width/2  > w)
				dirx = -1;
			
			if (x-width/2  < 0)
				dirx = 1;
			
			if (y + height/2  > h )
			{
				if(panel.getBall()[1] == null) {
					System.out.println(panel.getBall()[0]);
					this.x = p.getX()+p.getWidth();
					this.y = p.getY()-100;
					this.diry = -1;
					this.isAlive = false;
					panel.getBall()[0] = new BreakerBall(p.getX()+p.getWidth(),p.getY()-100, 50, 30, this.panel);
					panel.decLifes();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					panel.getBall()[1]=null;
					this.isAlive= false;
				}
			}
				
			if (y  < this.scoreBoardHeight)
				diry = 1;
			
				//break;
			//TODO add a lost screen
			
			x+=dirx;
			y+=diry;
		    try {
		    	Thread.sleep(this.speed);

			
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			panel.repaint();
		}
	
	
	}
}

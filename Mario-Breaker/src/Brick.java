import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Brick extends Thread {
	GamePanel panel;
	private int x;
	private int y;
	private int width;
	private int height;
	Image BrickImage;
	boolean isPaused=false;
	boolean isHit=false;
	boolean hitFlag = true; 
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param panel
	 */
	public Brick(int x, int y, int width, int height, GamePanel panel)
	{
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		this.panel=panel;
		ImageIcon ii=new ImageIcon("Brick.png");
		BrickImage=ii.getImage();
		start();
	}
	public void drawBrick(Graphics g){
		g.drawImage(BrickImage, x,y,width,height, null);
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
	}
	
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getWidth() { return this.width;}
	public int getHeight() { return this.height;}
	public void incWidth(int width) {this.width+=width;}
	public void decWidth(int width) {this.width-=width;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public boolean isHit() {return this.isHit;}
	private void hitBrick(BreakerBall b)
	{
		boolean c = CollusionHandler.DidCollusion(b,this);
		 if(c) {
			 //System.out.println("asd");

			 System.out.println(CollusionHandler.getLastDir());
		 }
		 Rectangle brickRec = new Rectangle(this.x, this.y, this.width, this.height);
		 Rectangle ballkRec = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		 if(ballkRec.intersects(brickRec) && !this.isHit && this.hitFlag)
		 {
			//System.out.println(String.format("brick.x: %d, brick.y: %d, ball.x: %d, ball.y: %d",this.x, this.y, b.getX(), b.getY() ));
			System.out.println(CollusionHandler.getLastDir());
			this.hitFlag= false;
			this.isHit=true;
			panel.incScoreMultiplayer();
			panel.addScore(10);
			System.out.println(panel.getScoreMultiplayer());
			System.out.println("score:"+panel.getScore());
			b.setDirAfterHitBrick(this);
			 //System.exit(0);
		 }
		 if(!ballkRec.intersects(brickRec) && !this.isHit && !this.hitFlag) {
			 this.hitFlag=true;
		 }
		 
		 
		
	}
	
	
	public void run()
	{
		while(true)
		{
		    try {

		    		hitBrick(this.panel.getBall());	
		    		Thread.sleep(10);
		      } 
		    catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			panel.repaint();
		}
	}
}

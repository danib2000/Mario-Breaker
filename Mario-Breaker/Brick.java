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
	int lifes;
	private int id;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param panel
	 */
	public Brick(int x, int y, int width, int height, GamePanel panel, boolean isBroken)
	{
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		this.panel=panel;
		this.id = (int)(Math.random()*10 +1);
		this.lifes=2;
		ImageIcon img;
		if(isBroken){
			 img =new ImageIcon("png/Brick" +  id +"Broken.png");	
		}else {
			 img =new ImageIcon("png/Brick" +  id +".png");	
		}
		
		BrickImage=img.getImage();
		start();
	}
	public void drawBrick(Graphics g){
		g.drawImage(BrickImage, x,y,width,height, null);
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
	}
	public void drawBrokenBrick(Graphics g){
		ImageIcon img =new ImageIcon("png/Brick" +  id +"Broken.png");	
		BrickImage=img.getImage();
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
		 Rectangle brickRec = new Rectangle(this.x, this.y, this.width, this.height);
		 Rectangle ballkRec = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());

		 if((ballkRec.intersects(brickRec) && !this.isHit && this.hitFlag))
		 {
			//System.out.println(String.format("brick.x: %d, brick.y: %d, ball.x: %d, ball.y: %d",this.x, this.y, b.getX(), b.getY() ));
			PowerUps.calculatePowerUpChance(x+width/2, y+height/2, 100, 50, this.panel);
			this.hitFlag= false;
			this.isHit=true;
			this.lifes--;
			panel.incScoreMultiplayer();
			panel.addScore(10);
			System.out.println(panel.getScoreMultiplayer());
			System.out.println("score:"+panel.getScore());
			b.setDirAfterHitBrick(this);
			 //System.exit(0);
			System.out.println("lifes:" +this.lifes);
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
		    		BreakerBall[] balls =panel.getBall();
		    		for(int i=0;i<balls.length;i++) {
		    			if(balls[i]!=null){
				    		hitBrick(balls[i]);	

		    			}
		    		}
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

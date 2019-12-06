import java.awt.*;
import javax.swing.*;

public class Player extends Thread {
	
	GamePanel panel;
	private int x;
	private int y;
	private int width;
	private int height;
	
	Image playerImage;
	boolean isPaused=false;
	/**
	 * player constructor
	 * @param x 
	 * @param y 
	 * @param width 
	 * @param height 
	 * @param panel 
	 */
	public Player(int x,int y, int width, int height, GamePanel panel)
	{
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		//this.size=size;
		this.panel=panel;
		ImageIcon ii=new ImageIcon("player.png");
		playerImage=ii.getImage();
		start();
	}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public int getWidth() { return this.width;}
	public int getHeight() { return this.height;}
	public void incWidth(int width) {this.width+=width;}
	public void decWidth(int width) {this.width-=width;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	
	/**
	 *  draws the player image
	 * @param g - graphics 
	 */
	public void drawPlayer(Graphics g){
		g.drawImage(playerImage, x,y, width, height, null);
	}
	
	
	public void run()
	{
		while(true)
		{
		    try {
		    	Thread.sleep(100);		
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			panel.repaint();
		}
	}
	
}

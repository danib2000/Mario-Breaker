import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PowerUps extends Thread { 
	GamePanel panel;
	int x;
	int y;
	int width;
	int height;
	int type;
	boolean isGood;
	boolean isAlive;
	boolean isPause;
	String[] goodPowerUps;
	String[] badPowerUps;
	Image[] goodPowerUpsImages;
	Image[] badPowerUpsImages;

	public PowerUps(int x,int y, int width, int height, boolean isGood, int type, GamePanel panel) {
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		//this.size=size;
		this.panel=panel;
		this.isGood = isGood;
		this.type = type;
		this.isAlive=true;
		this.isPause= false;
		this.goodPowerUps = new String[] {"png/incLength", "png/extraBall", "png/doublePoints", "png/extraLife"};
		this.badPowerUps = new String[] {"png/decLength", "png/miunsPoint", "png/incSpeed", "png/decSpeed"};
		goodPowerUpsImages = new Image[4];
		badPowerUpsImages = new Image[4];
		getgoodPowerUpsImages();
		getBadPowerUpsImages();
		start();
	}
	private void getgoodPowerUpsImages() {
		for(int i=0;i<this.goodPowerUps.length;i++) {
			String path = goodPowerUps[i] +".png";
			ImageIcon ii=new ImageIcon(path);
			this.goodPowerUpsImages[i]= ii.getImage();
		}
	}
	private void getBadPowerUpsImages() {
		for(int i=0;i<this.badPowerUps.length;i++) {
			String path = badPowerUps[i] +".png";
			ImageIcon ii=new ImageIcon(path);
			this.badPowerUpsImages[i]= ii.getImage();
		}
	}
	/**
	 *  draws the power up
	 * @param g - graphics 
	 */
	public void drawpowerUps(Graphics g){
		if(isGood) {
			g.drawImage(goodPowerUpsImages[type], x,y, width, height, null);
		}else {
			g.drawImage(badPowerUpsImages[type], x,y, width, height, null);

		}
		//start();

	}
	public static void calculatePowerUpChance(int startX, int startY, int width, int height ,GamePanel panel) {

		//first calculate the chance to get power up - 20%
		int chance = (int)(Math.random() * 5); 
		if(chance > 0) {
			//bad or good power ups - 50%
			chance = (int)(Math.random() * 2); 
			if(chance==1) {
				//good power up
				chance = (int)(Math.random() * 4); 
				System.out.println(chance);
				panel.addPowerUp(new PowerUps(startX,startY, width, height, true, chance, panel));
				//return new PowerUps(startX,startY, width, height, true, chance, panel);
			}
			else {
				//bad power up
				chance = (int)(Math.random() * 4); 
				System.out.println(chance);
				panel.addPowerUp(new PowerUps(startX,startY, width, height, false, chance, panel));
				//return new PowerUps(startX,startY, width, height, false, chance, panel);
			}
		}
	}
	public void activetePowerUp(PowerUps p) {
		System.out.println(p.isGood);
		if(p.isGood){
			System.out.println(p.type);
			switch(p.type) {
			
				case 0:
					//inc size
					this.incSize(30);
					break;
				case 1:
					//extra ball
					System.out.println("started extra ball");

					BreakerBall b = new BreakerBall(panel.getPlayer().getX()+panel.getPlayer().getWidth(),panel.getPlayer().getY()-100, 50, 30, panel);
					panel.addBall(b);
					break;
				case 2:
					//double points
					this.doublePoints();
					break;
				case 3:
					panel.incLifes();
					//extra life
					break;
				
			}
		}else {
			switch(p.type) {
			
			case 0:
				//dec size
				this.decSize(20);
				break;
			case 1:
				//miunsPoint
				this.panel.decScore(100);
				break;
			case 2:
				//inc speed
				this.incSpeed();
				break;
			case 3:
				//dec speed
				this.decSpeed();
				break;
			
		}
		}
	}
	private void doublePoints() {
		try {
			System.out.println("started double points");

			panel.activateDoublepoints();
			Thread.sleep(20000);
			panel.deactivateDoublepoints();

		}catch (Exception e) {
            System.out.println(e);
         }
	}
	private void incSize(int width) {
		System.out.println("started incsize");

		try {
			panel.getPlayer().incWidth(width);
            // thread to sleep for 1000 milliseconds
            Thread.sleep(20000);
    		panel.getPlayer().decWidth(width);
         } catch (Exception e) {
            System.out.println(e);
         }
	}
	private void decSize(int width) {
		try {
			System.out.println("started decsize");

			panel.getPlayer().decWidth(width);
            // thread to sleep for 1000 milliseconds
            Thread.sleep(20000);
    		panel.getPlayer().incWidth(width);

         } catch (Exception e) {
            System.out.println(e);
         }
	}
	private void incSpeed() {
		try {
			System.out.println("started incSpeed");
			BreakerBall[] balls = panel.getBall();
    		for(int i=0;i<balls.length;i++) {
    			if(balls[i]!=null){
		    		balls[i].setSpeed(3);	

    			}
			panel.setMoveSpeed(20);
            // thread to sleep for 1000 milliseconds
            Thread.sleep(20000);
            for(i=0;i<balls.length;i++) {
    			if(balls[i]!=null){
		    		balls[i].setSpeed(5);	

    			}
    		panel.setMoveSpeed(10);
         }}} catch (Exception e) {
            System.out.println(e);
         }
		
	}
	private void decSpeed() {
		try {
			System.out.println("started decSpeed");
			BreakerBall[] balls = panel.getBall();
    		for(int i=0;i<balls.length;i++) {
    			if(balls[i]!=null){
		    		balls[i].setSpeed(7);

    			}
    		}
			panel.setMoveSpeed(5);
            // thread to sleep for 1000 milliseconds
            Thread.sleep(20000);
            for(int i=0;i<balls.length;i++) {
    			if(balls[i]!=null){
		    		balls[i].setSpeed(5);
    			}
    		}
    		panel.setMoveSpeed(10);
    		System.out.println("started incSpeed");
         } catch (Exception e) {
            System.out.println(e);
         }
		
	}
	public void hitPlayer(Player p) { 
			if(this.isAlive) {
				if(this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight() &&(this.x >= p.getX() && this.x<=p.getX()+p.getWidth()))
				{	
					this.isAlive=false;
					activetePowerUp(this);		
				}
				if((this.y + height >= p.getY() && this.y + height <= p.getY()+p.getHeight()) &&(this.x + width >= p.getX() && this.x + width <= p.getX() + +p.getWidth()))
				{
					this.isAlive=false;
					activetePowerUp(this);
				}
			}
		}
	public void run()
	{
		Player p = panel.getPlayer();
		while(true)
		{
			
			if(this.isAlive && !this.isPause) {
				
		
			int h=panel.getHeight();
			int w=panel.getWidth();			
			hitPlayer(p);
			if (y + height/2  > h) {
				this.isAlive=false;
			}
			y++;
			}
		    try {
		    	Thread.sleep(6);
		      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		    }
			
			panel.repaint();
			
		}
	
	
	}
	
	
}

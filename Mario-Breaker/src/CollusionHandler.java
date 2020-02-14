

enum Direction{
	top, bottom, sides 
}

public class CollusionHandler {
	
	
		private static Direction lastDir;
	
		
		
		
		public static Direction getLastDir() {
			return lastDir;
		}
		
	/**
	 * Extended and general check weather one game piece has encounter the other
	 * @param ball - the breaker ball
	 * @param brick - the brick the player hit / didn't hit
	 * @return boolean value representing did collision occurred
	 */
	public static boolean DidCollusion(BreakerBall ball, Brick brick) {
		//System.out.println(String.format("gp1.x: %d, gp1.y: %d, gp2.x: %d, gp2.y: %d",gp1.getX(), gp1.getY(), gp2.getX(), gp2.getY() ));
		
		//first check if the the top left corner of the ball is inside the brick
					
		// first check, verify that ball y coordinate 
		// is within brick's y coordinates range
//		if( ball.getY() >= brick.getY() &&
//				ball.getY() <= (brick.getY() +brick.getHeight()) ){
			 

			//check if the Y coordinate of the ball is equal to the Y coordinate of the bottom of the brick
			if(ball.getY() == (brick.getY() + brick.getHeight())) {
				System.out.println(String.format("brick.x: %d, brick.y: %d, ball.x: %d, ball.y: %d",brick.getX(), brick.getY()+ brick.getHeight(), ball.getX(), ball.getY() ));
				
				//check if top left corner of the ball is within the the border of the brick
				if(ball.getX() >= brick.getX() &&
						ball.getX() <= brick.getX() + brick.getWidth()){
					lastDir = Direction.bottom;
					return true;
					
				}
				//check if top right corner  is within the the border of the brick
				if(ball.getX() + ball.getWidth() >= brick.getX() &&
						ball.getX()+ball.getWidth() <= brick.getX() + brick.getWidth()) {
					lastDir = Direction.bottom;
					return true;
					
				}
			}
			
		//}
			
			return false;
				
		
				
				
	}
	
}

//
//
//enum Direction{
//	EAST,NORTH_EAST,NORTH,NORTH_WEST,WEST,SOUTH_WEST,SOUTH,SOUTH_EAST,ALL
//}
//
//public class CollusionHandler {
//	
//	/**
//	 * Extended and general check weather one game piece has encounter the other
//	 * @param gp1 one game piece in the encounter
//	 * @param gp2 other game piece in the encounter
//	 * @param d in what direction did gp1 hit gp2.
//	 * @return boolean value representing did collision occurred
//	 */
//	public static boolean DidCollusion(BreakerBall ball, Brick brick, Direction d) {
//		
//		// NOTE: There is additional size added to the parameter of 
//		//       game piece 2, to cover option of close but no touching
//		//		 encounter.
//		//System.out.println(String.format("gp1.x: %d, gp1.y: %d, gp2.x: %d, gp2.y: %d",gp1.getX(), gp1.getY(), gp2.getX(), gp2.getY() ));
//		switch(d) {
//		case NORTH:
//			
//			// in case the encounter is from the south to game piece 2,
//			// check if game piece 1 is touching game piece 2 from below
//			// or up to half size in it, and verify that game piece 1
//			// boundaries are partially or fully within game piece 2
//			// length and coordinates range.
//			return (
//					
//					// first check, verify that game piece 1 y coordinate 
//					// is within game piece 2 y coordinates range
//					   ball.getY() >= (brick.getY() - (brick.getHeight() / 2))
//					&& ball.getY() <= (brick.getY() + brick.getHeight())
//					
//					// then, check x coordinates. there are three possible
//					// states: 
//					// 1 - game piece 1 is partially with in piece 2 x
//					//     coordinates, from the left side.
//					// 2 - game piece 1 is fully within game piece 2 x 
//					//     coordinates range.
//					// 3 - game piece 1 is partially with in piece 2 x
//					//     coordinates, from the right side.
//					&&(
//					// state 1:
//						   // check left up corner to be outside of range.
//					    (  gp1.getX() <= gp2.getX()                
//					   	   // check right up corner to be within range and not passing it.
//					    && gp1.getX() + gp1.getSize() >= gp2.getX()
//					    && gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
//					||
//					// stage 2:
//						   // check left up corner to be within range 
//						(  gp1.getX() >= gp2.getX()
//						   // check right up corner to be within range
//						&& gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
//					||
//					// stage 3:
//					       // check left up corner to be within range.
//						(  gp1.getX() >= gp2.getX()
//						&& gp1.getX() <= gp2.getX() + gp2.getSize()
//						   // check right up corner to be outside of range
//						&& gp1.getX() + gp1.getSize() >= gp2.getX() + gp2.getSize())
//				   ));
//			
//		case SOUTH:
//			
//			// in case the encounter is from the north to game piece 2,
//			// check if game piece 1 is touching game piece 2 from above
//			// or up to half size in it, and verify that game piece 1
//			// boundaries are partially or fully within game piece 2
//			// length and coordinates range.
//			
//			return (
//					
//					// first check, verify that game piece 1 y coordinate 
//					// is within game piece 2 y coordinates range
//				 	   
//					   gp1.getY() + gp1.getSize() <= (gp2.getY() + (gp2.getSize() / 2))
//					&& gp1.getY() + gp1.getSize() >=  (gp2.getY())
//					
//					// then, check x coordinates. there are three possible
//					// states: 
//					// 1 - game piece 1 is partially with in piece 2 x
//					//     coordinates, from the left side.
//					// 2 - game piece 1 is fully within game piece 2 x 
//					//     coordinates range.
//					// 3 - game piece 1 is partially with in piece 2 x
//					//     coordinates, from the right side.
//					&&(
//					// state 1:
//						   // check left up corner to be outside of range.
//					    (  gp1.getX() <= gp2.getX()        
//					   	   // check right up corner to be within range and not passing it.
//					    && gp1.getX() + gp1.getSize() >= gp2.getX()
//					    && gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
//					||
//					// stage 2:
//						   // check left up corner to be within range 
//						(  gp1.getX() >= gp2.getX()
//						   // check right up corner to be within range
//						&& gp1.getX() + gp1.getSize() <= gp2.getX() + gp2.getSize())
//					||
//					// stage 3:
//					       // check left up corner to be within range.
//						(  gp1.getX() >= gp2.getX()
//						&& gp1.getX() <= gp2.getX() + gp2.getSize()
//						   // check right up corner to be outside of range
//						&& gp1.getX() + gp1.getSize() >= gp2.getX() + gp2.getSize())
//				   ));
//		
//		case EAST:
//			
//			// in case the encounter is from the west to game piece 2,
//			// check if game piece 1 is touching game piece 2 from the left
//			// or up to half size in it, and verify that game piece 1
//			// boundaries are partially or fully within game piece 2
//			// length and coordinates range.
//			
//			return (
//					
//					// first check, verify that game piece 1 X coordinate 
//					// is within game piece 2 X coordinates range
//				 	   
//					   gp1.getX() + gp1.getSize() <= (gp2.getX() + (gp2.getSize() / 2))
//					&& gp1.getX() + gp1.getSize() >= (gp2.getX()) 
//					
//					// then, check x coordinates. there are three possible
//					// states: 
//					// 1 - game piece 1 is partially with in piece 2 y
//					//     coordinates, from the left side.
//					// 2 - game piece 1 is fully within game piece 2 y 
//					//     coordinates range.
//					// 3 - game piece 1 is partially with in piece 2 y
//					//     coordinates, from the right side.
//					&&(
//					// state 1:
//						   // check left up corner to be outside of range.
//					    (  gp1.getY() <= gp2.getY()         
//					   	   // check right up corner to be within range and not passing it.
//					    && gp1.getY() + gp1.getSize() >= gp2.getY()
//					    && gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
//					||
//					// stage 2:
//						   // check left up corner to be within range 
//						(  gp1.getY() >= gp2.getY()
//						   // check right up corner to be within range
//						&& gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
//					||
//					// stage 3:
//					       // check left up corner to be within range.
//						(  gp1.getY() >= gp2.getY()
//						&& gp1.getY() <= gp2.getY() + gp2.getSize()
//						   // check right up corner to be outside of range
//						&& gp1.getY() + gp1.getSize() >= gp2.getY() + gp2.getSize())
//				   ));
//			
//		case WEST:
//			
//			// in case the encounter is from the west to game piece 2,
//			// check if game piece 1 is touching game piece 2 from the right
//			// or up to half size in it, and verify that game piece 1
//			// boundaries are partially or fully within game piece 2
//			// length and coordinates range.
//			
//			return (
//					
//					// first check, verify that game piece 1 X coordinate 
//					// is within game piece 2 X coordinates range
//				 	   
//					   gp1.getX() >= (gp2.getX() + (gp2.getSize() / 2))
//					&& gp1.getX() <= (gp2.getX() + gp2.getSize()) 
//					
//					// then, check x coordinates. there are three possible
//					// states: 
//					// 1 - game piece 1 is partially with in piece 2 y
//					//     coordinates, from the left side.
//					// 2 - game piece 1 is fully within game piece 2 y 
//					//     coordinates range.
//					// 3 - game piece 1 is partially with in piece 2 y
//					//     coordinates, from the right side.
//					&&(
//					// state 1:
//						   // check left up corner to be outside of range.
//					    (  gp1.getY() <= gp2.getY()                
//					   	   // check right up corner to be within range and not passing it.
//					    && gp1.getY() + gp1.getSize() >= gp2.getY()
//					    && gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
//					||
//					// stage 2:
//						   // check left up corner to be within range 
//						(  gp1.getY() >= gp2.getY()
//						   // check right up corner to be within range
//						&& gp1.getY() + gp1.getSize() <= gp2.getY() + gp2.getSize())
//					||
//					// stage 3:
//					       // check left up corner to be within range.
//						(  gp1.getY() >= gp2.getY()
//						&& gp1.getY() <= gp2.getY() + gp2.getSize()
//						   // check right up corner to be outside of range
//						&& gp1.getY() + gp1.getSize() >= gp2.getY() + gp2.getSize())
//				   ));
//		default:
//			// check all four direction for collusion
//			return false;
//					
//		}
//				
//				
//	}
//	
//	/**
//	 * Extended and general check weather one game piece has encounter the other
//	 * @param gp1 gp1 one game piece in the encounter
//	 * @param gp2 gp2 other game piece in the encounter
//	 * @return boolean value representing did collision occurred
//	 */
//	public static boolean DidCollusion(GamePiece gp1, GamePiece gp2) {
//		return DidCollusion(gp1, gp2, Direction.ALL);
//	}
//}

package org.napf.squarewar.core;

import org.napf.util.NapfMath;

import javafx.scene.paint.Color;

public class TankBullet extends Projectile{

	
	public TankBullet(double xpos, double ypos) {
		super(xpos, ypos, "TankBullet", Color.GREY);
		  speed = 4;
		  		  
	}
	public void update(double mouseXCord, double mouseYCord, double tankXCord, double tankYCord){
	      // Maths out projektile movement
		  /*double h;
	      double deg; 
	      while( quickmaths == true) {
	      h = Math.sqrt(Math.pow((tankXCord - mouseXCord),2) + Math.pow((tankYCord - mouseYCord),2));
	      deg = Math.acos((tankXCord - mouseXCord) / h);
	      adjacent = Math.cos(deg) * speed;
	      opposite = Math.cos(deg) * speed;
	      quickmaths = false;
	      }
	      x = xpos + adjacent;
	      y = ypos + opposite;
	      moveTo(x, y);
	      xpos = x;
	      ypos = y;*/

	      double dirX, dirY;

	      dirX = mouseXCord - tankXCord;
	      dirY = mouseYCord - tankYCord;
	    // Convert into unit vector
	      if (!(dirX == 0 && dirY == 0)) {
				double magnitude = NapfMath.magnitude2D(dirX, dirY);
				dirX /= magnitude;
				dirY /= magnitude;
				moveBy(dirX * speed * GameManager.getInstance().getDeltaTime(), dirY * speed * GameManager.getInstance().getDeltaTime());

	      }

	  }
}

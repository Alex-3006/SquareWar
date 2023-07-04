package org.napf.squarewar.core;

import org.napf.util.NapfMath;

import javafx.scene.paint.Color;

public class BotTank extends Tank{

    private int downWall;
    private int upWall;
    private int rightWall;
    private int leftWall;

    public BotTank(double xpos, double ypos) {
          super(xpos, ypos, "BotTank", Color.RED);
          downWall = 0;
          upWall = 0;
          rightWall = 0;
          leftWall = 0;
      }

    public void ki(double enemyXPos, double enemyYPos) {
        if(downWall == 0 && upWall == 0 && leftWall == 0 && rightWall == 0) {

              double dirX, dirY;

              dirX = enemyXPos - xpos;
              dirY = enemyYPos - ypos;
            // Convert into unit vector
              if (!(dirX == 0 && dirY == 0)) {
                    double magnitude = NapfMath.magnitude2D(dirX, dirY);
                    dirX /= magnitude;
                    dirY /= magnitude;
                    moveBy(dirX * speed * GameManager.getInstance().getDeltaTime(), dirY * speed * GameManager.getInstance().getDeltaTime());
        }else if(downWall != 0) {
            moveBy(speed * GameManager.getInstance().getDeltaTime(),0);
            downWall--;
        }else if(upWall != 0) {
            moveBy(-speed * GameManager.getInstance().getDeltaTime(),0);
            upWall--;
        }else if(leftWall != 0) {
            moveBy(0,speed * GameManager.getInstance().getDeltaTime());
            leftWall--;
        }else if(rightWall != 0) {
            moveBy(0,-speed * GameManager.getInstance().getDeltaTime());
            rightWall--;
        }
    }

}
}


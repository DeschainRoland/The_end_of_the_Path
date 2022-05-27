package com.example.gunslinger;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
public abstract class GameObject {

    Bitmap image;
    Paint paint = new Paint();
    GameMap gameMap;
    Resources res;

    Rect hitbox;
    int fallingVelocity = 9,
    movingVelocity,
    x, y,
    height, width,
     fallRow, fallColumn,horColRow,horColColumn;


    public GameObject(GameMap gameMap, Resources res, int x, int y){
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.res = res;

    }

public boolean isFalling(){
    return gameMap.drawMap.mapArray[fallRow][fallColumn].equals("e") ||
            gameMap.drawMap.mapArray[fallRow][fallColumn].equals("|") ||
            gameMap.drawMap.mapArray[fallRow][fallColumn].equals("l") ||
            gameMap.drawMap.mapArray[fallRow][fallColumn].equals("k") ||
            gameMap.drawMap.mapArray[fallRow][fallColumn].equals("s") ||
            gameMap.drawMap.mapArray[fallRow][fallColumn].equals("c");
}

    public boolean isCollision(int left, int right, int bottom){
        return hitbox.contains(left,bottom)||hitbox.contains(right,bottom);
    }

    public abstract void draw(Canvas canvas);

    public void fall(){}

    public void moveX(){}
}

package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

public class Spike {
    int x, y; //координаты кадра
    Rect hitBox;
    GameMap gameMap;


    Bitmap image; //спрайты
    Paint paint = new Paint();
    public Spike(Bitmap image, GameMap gameMap, int startX, int startY){
        this.gameMap = gameMap;
        this.image = image;
        x = startX;
        y = startY;
        hitBox = new Rect(x+5,y+5,image.getWidth()-5,image.getHeight());

    }

  //public boolean checkCollision(Roland roland) {
  //    if () {
  //    }
  //}
}

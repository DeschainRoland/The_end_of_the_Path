package com.example.gunslinger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;



public class Roland {
    GameMap gameMap;
    Bitmap image; //спрайты
    Paint paint = new Paint();

    final int IMAGE_ROWS = 1;
    final int IMAGE_COLUMNS = 8;
    int currentFrame = 0;
    int direction = 4;

    //переменные для передвижения
    int x, y; //координаты кадра
    int dx;
    int tX;
    boolean jumping = false;
    boolean falling = false;
    int height, width; //ширина и высота персонажа
    int fallVelocity = 8;
    int jumpVelocity = 6;
    int jumpBorder; //высота прыжка
    int jumpCounter = 0; //счетчик текущей высоты прыжка

    public Roland(Bitmap image, GameMap gameMap, int startX, int startY){
        this.gameMap = gameMap;
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        x = startX;
        y = startY;
        jumpBorder = 48;
//width = this.image.getWidth() / IMAGE_COLUMNS;
//height = this.image.getHeight() / IMAGE_ROWS;
    }
    public void settX(float touchX) {
        tX = (int) touchX;
        dx = 7;

    }
    public void jump(){

        if (jumping && jumpCounter < jumpBorder) {
            y -= jumpVelocity;
            jumpCounter += jumpVelocity;
        }
        else {
            jumping = false;

        }
    }

    public void fall(){
        if (falling && !jumping) {
            y += fallVelocity;

        }
        else {
            falling = false;


        }
    }

    public void moveToX(){
        if (tX>x){
            if (Math.abs(x - tX)-width> 5) {
                x += dx;
            }
        }
        else if (tX<x){
            if (Math.abs(x - tX)> 5) {
                x -= dx;
            }
        }

        else dx = 0;

//было здесь
//currentFrame = ++currentFrame%IMAGE_COLUMNS;
    }

    public void draw(Canvas canvas) {

//Rect src = new Rect(currentFrame*width, direction*height,
// currentFrame*width+width, direction*height+height);
//Rect dst = new Rect(x, y, x+width, y+width);
//canvas.drawBitmap(image, src, dst, paint);
//currentFrame = ++currentFrame%IMAGE_COLUMNS;
        canvas.drawBitmap(image,x,y,paint);
        moveToX();
        jump();
        fall();
//fallInPit();
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
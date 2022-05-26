package com.example.gunslinger;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameMap extends SurfaceView implements SurfaceHolder.Callback {
    //Переменные для рисования
    float x1, y1, //текущее положение картинки
        // x2, y2, //смещение координат
            touchX, touchY; //точки касания
    int row,column;
    Resources res;
    DrawMap drawMap;
    DrawThread drawThread;
    boolean isSpawned = false;
    Roland roland;


    public GameMap(Context context) {
        super(context);
        getHolder().addCallback(this);
        res = getResources();
        drawMap = new DrawMap(res);
        roland = new Roland(BitmapFactory.decodeResource(res,R.drawable.roland_single_32),
                this, 0,0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                roland.settX(touchX); break;
            case MotionEvent.ACTION_UP:
                if (!roland.falling) {roland.jumping = true;
                roland.jumpCounter = 0;}
                break;

        }

        return true;
    }
    public void checkFalling(){
        row = Math.round((roland.y+roland.height)/48);
        column = Math.round(roland.x/48);
        if (drawMap.mapArray[row][column].equals("e")||drawMap.mapArray[row][column].equals("|"))
            roland.falling = true;
        else roland.falling = false;

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        drawMap.draw(canvas);
        if (!isSpawned) {
            roland.setX(drawMap.spawnX);
            roland.setY(drawMap.spawnY);
            isSpawned = true;
        }
        roland.draw(canvas);
        checkFalling();


//Rect rectRoland = new Rect(roland.x + 5, roland.y + 5, roland.width - 5, roland.height);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(this, getHolder());
        drawThread.setRun(true);
        drawThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean stop = true;
        drawThread.setRun(false);
        while(stop) {
            try {
                drawThread.join();
                stop = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
package com.example.gunslinger;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DrawMap {
    Resources resources;
    Bitmap innerBrick, upBrick, downBrick;
    String[][] mapArray;

    int textureWidth, textureHeight;
    boolean generatedFirst = true;
    int spawnX, spawnY;
    Paint paint = new Paint();

    //переменные для работы с файлами
    InputStream path;
    InputStreamReader isr;
    BufferedReader br;
    String info;

    public DrawMap(Resources resources) {
        this.resources = resources;
        innerBrick = BitmapFactory.decodeResource(resources, R.drawable.inner_brick_16);
        upBrick = BitmapFactory.decodeResource(resources, R.drawable.up_brick_16);
        downBrick = BitmapFactory.decodeResource(resources, R.drawable.down_brick_16);
        textureHeight = innerBrick.getHeight();
        textureWidth = innerBrick.getWidth();
        path = resources.openRawResource(R.raw.map_true);
        isr = new InputStreamReader(path);
        br = new BufferedReader(isr);
        if (generatedFirst) {
            try {
                generateMapArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

            generatedFirst = false;
        }

    }
    //рассчитывает длину и высоту массива перед выделением памяти
    private void checkLength() throws IOException {
        int rowCounter = 0;
        int columnCounter = 0;
        boolean hasTaken = false;

        while ((info = br.readLine()) != null) {
//чтобы забрать кол-во колонок лишь один раз
            if (!hasTaken) {
                columnCounter = info.length();
                hasTaken = true;
            }
            rowCounter++;
        }
        br.close();

        mapArray = new String[rowCounter][columnCounter];
    }

    //генерирует строковый массив из файла
    public void generateMapArray() throws IOException {
        checkLength();
        path = resources.openRawResource(R.raw.map_true);
        isr = new InputStreamReader(path);
        br = new BufferedReader(isr);

        int i = 0;
        while ((info = br.readLine()) != null) {
            for (int j = 0; j < info.length(); j++) {
                mapArray[i][j] = String.valueOf(info.charAt(j));
            }
            i++;
        }
        br.close();

    }

    public void draw(Canvas canvas) {
//рисуем задний фон
        canvas.drawColor(Color.DKGRAY);
        drawTextures(canvas);
    }

    public void drawTextures(Canvas canvas) {
        for (int y = 0; y < mapArray.length; y++) {
            for (int x = 0; x < mapArray[y].length; x++) {
                switch (mapArray[y][x]){
                    case "i" : canvas.drawBitmap(innerBrick,x*textureWidth,y*textureHeight, null);break;
                    case "u" : canvas.drawBitmap(upBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "d" : canvas.drawBitmap(downBrick,x*textureWidth,y*textureHeight, paint);break;
                    case "|" : spawnX = x*textureWidth; spawnY = y*textureHeight-48;break;

                }

            }
        }
    }

}
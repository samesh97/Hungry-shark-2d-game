package com.sba.thehungryshark;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {


    private Bitmap fish[] = new Bitmap[2];

    private Bitmap bomb[] = new Bitmap[2];



    private int bombX;
    private  int bombY;
    private int bombSpeed = 8;

    Bitmap scaled,scaled2;






    private Bitmap backhroundImage,backhroundImage2;
    Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];


    private int fishX = 20;
    private  int fishY;
    private int fishSpeed;

    private int yellowX,yellowY,yellowSpeed = 8;
    private Paint yellowPaint = new Paint();


    private int greenX,greenY,greenSpeed = 8;
    private Paint greenPaint = new Paint();

    private int score,lifeCouterOfFish;


    private boolean touch = false;


    private int canvasWidth,canvasHeight;

    boolean isGameOver = false;

    long startTime;
    int swipe = 0;
    int swipe2 = 0;
    int swipe3 = 0;
    int swipeSpeed = 5;

    public GameView(Context context)
    {



        super(context);


        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);




        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);


        backhroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background2);
        backhroundImage2 = BitmapFactory.decodeResource(getResources(),R.drawable.background2);
        scaled = Bitmap.createScaledBitmap(backhroundImage, MainActivity.width,MainActivity.height, true);
        scaled2 = Bitmap.createScaledBitmap(backhroundImage2, MainActivity.width,MainActivity.height, true);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);




        bomb[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bomb);
        bomb[1] = BitmapFactory.decodeResource(getResources(),R.drawable.blast);

        fishY = 550;
        score = 0;
        lifeCouterOfFish = 3;

        startTime  = System.currentTimeMillis();





    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(!isGameOver)
        {
            canvasHeight = getHeight();
            canvasWidth = getWidth();


            /*
            canvas.drawBitmap(scaled,-swipe,0,null);
            canvas.drawBitmap(scaled2,canvasWidth-swipe2,0,null);
            canvas.drawBitmap(scaled,(canvasWidth * 2) -swipe3,0,null);*/

            canvas.drawBitmap(scaled,-swipe,0, null);
            canvas.drawBitmap(scaled2,scaled2.getWidth() - swipe,0, null);


            swipe = swipe + swipeSpeed;
            swipe2 = swipe2 + swipeSpeed;
            swipe3 = swipe3 + swipeSpeed;

            if(swipe >= scaled.getWidth())
            {
                swipe = 0;
            }



            int minFishY = fish[0].getHeight();
            int maxFishY = canvasHeight - fish[0].getHeight() * 2;
            fishY = fishY + fishSpeed;

            if(fishY < minFishY)
            {
                fishY = minFishY;
            }
            if(fishY > maxFishY)
            {
                fishY = maxFishY;
            }
            fishSpeed = fishSpeed + 2;

            if(touch)
            {
                canvas.drawBitmap(fish[1],fishX,fishY,null);
                touch = false;
            }
            else
            {
                canvas.drawBitmap(fish[0],fishX,fishY,null);
            }



            yellowX = yellowX - yellowSpeed;

            if(hitBallChecker(yellowX,yellowY))
            {
                score = score + 10;
                yellowX = - 100;
            }

            if(yellowX < -100)
            {
                yellowX = canvasWidth + 21;
                yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            }
            canvas.drawCircle(yellowX,yellowY,15,yellowPaint);




            bombX = bombX - bombSpeed;

            if(hitBallChecker(bombX,bombY))
            {
                lifeCouterOfFish--;
                canvas.drawBitmap(bomb[1],bombX,bombY,null);
                bombX = - 100;
            }

            if(hitBallChecker(bombX + (bomb[0].getWidth()/2 ),bombY + (bomb[0].getHeight()/2)))
            {
                lifeCouterOfFish--;
                canvas.drawBitmap(bomb[1],bombX,bombY,null);
                bombX = - 100;
            }

            if(bombX < -100)
            {
                bombX = canvasWidth + 21;
                bombY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            }
            canvas.drawBitmap(bomb[0],bombX,bombY,null);



            greenX = greenX - greenSpeed;

            if(hitBallChecker(greenX,greenY))
            {
                score = score + 20;
                greenX = - 100;
            }

            if(greenX < -100)
            {
                greenX = canvasWidth + 21;
                greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
            }
            canvas.drawCircle(greenX,greenY,20,greenPaint);





            canvas.drawText("Score : " + score,30,60, scorePaint);





            if(lifeCouterOfFish == 1)
            {
                canvas.drawBitmap(life[0],canvasWidth -200,20,null);
                canvas.drawBitmap(life[1],canvasWidth -140,20,null);
                canvas.drawBitmap(life[1],canvasWidth -80,20,null);
            }
            else if(lifeCouterOfFish == 2)
            {
                canvas.drawBitmap(life[0],canvasWidth -200,20,null);
                canvas.drawBitmap(life[0],canvasWidth -140,20,null);
                canvas.drawBitmap(life[1],canvasWidth -80,20,null);
            }
            else if(lifeCouterOfFish == 3)
            {
                canvas.drawBitmap(life[0],canvasWidth -200,20,null);
                canvas.drawBitmap(life[0],canvasWidth -140,20,null);
                canvas.drawBitmap(life[0],canvasWidth -80,20,null);
            }



            if(lifeCouterOfFish == 0)
            {
                canvas.drawBitmap(life[1],580,10,null);
                canvas.drawBitmap(life[1],640,10,null);
                canvas.drawBitmap(life[1],700,10,null);
                isGameOver = true;
            }


            long elapsedTime = System.currentTimeMillis() - startTime;
            long elapsedSeconds = elapsedTime / 1000;

            if(elapsedSeconds == 10)
            {
                bombSpeed++;
                greenSpeed++;
                yellowSpeed++;
                startTime = System.currentTimeMillis();
            }
        }
        else
        {
            invalidate();
            Intent intent = new Intent(getContext(),GameOver.class);
            intent.putExtra("Marks",String.valueOf(score));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(intent);
        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishSpeed = -22;


        }



        return true;
    }
    public  boolean hitBallChecker(int x, int y)
    {
        if(fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY +  fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }






}

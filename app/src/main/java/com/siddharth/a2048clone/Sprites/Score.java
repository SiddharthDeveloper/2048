package com.siddharth.a2048clone.Sprites;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.siddharth.a2048clone.R;

public class Score implements Sprite {

    public static final String SCORE_PREF = "Score Pref";

    private Resources resources;
    private int screenWidth, screenHeight, standardSize;
    private Bitmap bmpScore, bmpTopScore;
    private Bitmap bmpTopScoreBonus, bmp2048Bonus;
    private int score, topScore;
    private SharedPreferences prefs;
            // to write the text basically score on screen..
    private Paint paint;
    private boolean topScoreBonus = false;
    private boolean a2048Bonus = false;

    public Score(Resources resources, int screenWidth, int screenHeight, int standardSize, SharedPreferences prefs) {

        this.resources = resources;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.standardSize = standardSize;
        this.prefs = prefs;


        topScore = prefs.getInt(SCORE_PREF, 0);
        int width = (int) resources.getDimension(R.dimen.score_lable_width);
        int height = (int) resources.getDimension(R.dimen.score_bonus_height);

        Bitmap sc = BitmapFactory.decodeResource(resources, R.drawable.topscore);
        bmpScore = Bitmap.createScaledBitmap(sc, width, height, false);

        Bitmap tsc = BitmapFactory.decodeResource(resources, R.drawable.topscore);
        bmpTopScore = Bitmap.createScaledBitmap(tsc, width, height, false);



        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(resources.getDimension(R.dimen.score_text_size));


    }


    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bmpScore, screenWidth / 4 - bmpScore.getWidth() / 2, bmpScore.getHeight(), null);
        canvas.drawBitmap(bmpTopScore, 3 * screenWidth / 4 - bmpTopScore.getWidth() / 2, bmpTopScore.getHeight(), null);

        int width1 = (int) paint.measureText(String.valueOf(score));
        int width2 = (int) paint.measureText(String.valueOf(topScore));
        canvas.drawText(String.valueOf(score), screenWidth / 4 - width1 / 2, bmpScore.getHeight() * 4, paint);
        canvas.drawText(String.valueOf(topScore), 3 * screenWidth / 4 - width2 / 2, bmpTopScore.getHeight() * 4, paint);

        if (topScoreBonus) {
            canvas.drawBitmap(bmpTopScoreBonus, screenWidth / 2 - 2 * standardSize, screenHeight / 2 - 2 * standardSize - 2 * bmpTopScoreBonus.getHeight(), null);
        }

        if (a2048Bonus) {
            canvas.drawBitmap(bmp2048Bonus, screenWidth / 2 - 2 * standardSize, screenHeight / 2 - 2 * standardSize - 7 * bmp2048Bonus.getHeight() / 2, null);
        }
    }


    @Override
    public void update() {

    }

    public void updateScore(int delta) {
        score += delta;
        checkTopScore();
    }
//  TODO: Check top Score Bonus..
    public void checkTopScore() {
        topScore = prefs.getInt(SCORE_PREF, 0);
        if (topScore < score) {
            prefs.edit().putInt(SCORE_PREF, score).apply();
            topScore = score;

            int width = (int) resources.getDimension(R.dimen.score_bonus_width);
            int height = (int) resources.getDimension(R.dimen.score_bonus_height);
            Bitmap tsb = BitmapFactory.decodeResource(resources, R.drawable.highscore);
            bmpTopScoreBonus = Bitmap.createScaledBitmap(tsb, width, height, false);

            topScoreBonus = true;
        }
    }

    public void reached2048() {
        int width = (int) resources.getDimension(R.dimen.score_bonus_width);
        int height = (int) resources.getDimension(R.dimen.score_bonus_height);
        Bitmap bmp = BitmapFactory.decodeResource(resources, R.drawable.a2048);
        bmp2048Bonus = Bitmap.createScaledBitmap(bmp, width, height, false);
        a2048Bonus = true;
    }

}

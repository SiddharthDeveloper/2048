package com.siddharth.a2048clone;

import android.graphics.Bitmap;

import com.siddharth.a2048clone.Sprites.Tile;

public interface TileManagerCallback {

    Bitmap getBitmap(int count);
    void finishedMoving(Tile t);
    void updateScore(int delta);
    void reached2048();

}

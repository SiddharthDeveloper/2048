package com.siddharth.a2048clone;

public interface GameManagerCallback {
    void gameOver();
    void updateScore(int delta);
    void reached2048();

}

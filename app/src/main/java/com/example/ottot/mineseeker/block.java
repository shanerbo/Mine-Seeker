package com.example.ottot.mineseeker;

import java.util.Random;

/**
 * Created by ottot on 2/10/2017.
 */

public class block {
    private int Mine;
    public block(){
        Random rg = new Random();
        Mine = rg.nextInt(1);
    }

    public int getMine() {
        return Mine;
    }

    public void setMine(int mine) {
        Mine = mine;
    }
}

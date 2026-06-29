package com.ventura.bracketslib.application;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsConfig {

    private int screenHeight;
    private static BracketsConfig instance;

    private BracketsConfig() {}

    public static synchronized BracketsConfig getInstance() {
        if (instance == null) {
            instance = new BracketsConfig();
        }
        return instance;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}

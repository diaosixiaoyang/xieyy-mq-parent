package com.xieyy.basic;

/**
 * @author xieyyt
 * @date 2019/12/24 19:09
 */
public class MyPen {

    private PenColors penColors;

    public MyPen(PenColors penColors) {
        this.penColors = penColors;
    }

    public PenColors getPenColors() {
        return this.penColors;
    }

    public void choicePen() {
        this.penColors.getPenColor();
    }

    public static void main(String[] args) {
        new MyPen(new Red()).choicePen();
    }
}

package com.example.chartviewlibrary;

import java.io.Serializable;

public class BarValue implements Serializable {
    private float value;
    private int color;
    private String label;

    public BarValue(float value, int color){
        this.value = value;
        this.color = color;
    }

    public BarValue(float value, int color, String label){
        this.value = value;
        this.color = color;
        this.label = label;
    }

    public float getValue() {
        return value;
    }
    public int getColor() {
        return color;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
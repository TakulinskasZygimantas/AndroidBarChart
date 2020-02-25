package com.example.componentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.example.chartviewlibrary.ChartView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChartView.OnFragmentInteractionListener {

    LinearLayout chartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<com.example.chartviewlibrary.BarValue> barValues = new ArrayList<>();

        barValues.add(new com.example.chartviewlibrary.BarValue(536.692f, Color.parseColor("#F2476B"), "Vilnius"));
        barValues.add(new com.example.chartviewlibrary.BarValue(312.120f, Color.parseColor("#FB654D"), "Kaunas"));
        barValues.add(new com.example.chartviewlibrary.BarValue(164.310f, Color.parseColor("#EC2C39"), "Klaipėda"));
        barValues.add(new com.example.chartviewlibrary.BarValue(107.086f, Color.parseColor("#AED8E6"), "Šiauliai"));
        barValues.add(new com.example.chartviewlibrary.BarValue(98.598f, Color.parseColor("#90EE90"), "Panevėžys"));

        barValues.add(new com.example.chartviewlibrary.BarValue(54.437f, Color.parseColor("#B17BAA"), "Alytus"));
        barValues.add(new com.example.chartviewlibrary.BarValue(36.628f, Color.parseColor("#6598C6"), "Marijampolė"));
        barValues.add(new com.example.chartviewlibrary.BarValue(33.281f, Color.parseColor("#F37248"), "Mažeikiai"));
        barValues.add(new com.example.chartviewlibrary.BarValue(29.031f, Color.parseColor("#C54B6C"), "Jonava"));
        barValues.add(new com.example.chartviewlibrary.BarValue(27.041f, Color.parseColor("#5784BA"), "Utena"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment2);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        chart.showValue(true);
        chart.showLabels(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyGraphview extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Paint paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        private Paint paintLabelText = new Paint(Paint.ANTI_ALIAS_FLAG);
        float[] v;
        List<BarValue> innerBarValues;

        private String chartPosition = "bottom";
        private float barWidth = 80;

        private boolean stroke = false;
        private int strokeColor = Color.BLACK;

        private boolean valueText = false;
        private int valueTextColor = Color.BLACK;
        private int valueTextSize = 24;

        private boolean labelText = false;
        private int labelTextColor = Color.BLACK;

        public View setChartPosition(String cPosition) {
            this.chartPosition = cPosition;
            return getRootView();
        }

        public View setBarWidth(float barWidth) {
            this.barWidth = barWidth;
            return getRootView();
        }

        public View setStroke(boolean stroke) {
            this.stroke = stroke;
            return getRootView();
        }

        public View setValueTextColor(int valueTextColor) {
            this.valueTextColor = valueTextColor;
            return getRootView();
        }

        public View showValue(boolean valueText) {
            this.valueText = valueText;
            return getRootView();
        }

        public View setStrokeColor(int strokeColor) {
            this.strokeColor = strokeColor;
            return getRootView();
        }

        public View setValueTextSize(int valueTextSize) {
            this.valueTextSize = valueTextSize;
            return getRootView();
        }

        public View showLabels(boolean labelText) {
            this.labelText = labelText;
            return getRootView();
        }

        public View setLabelTextColor(int labelTextColor) {
            this.labelTextColor = labelTextColor;
            return getRootView();
        }

        public MyGraphview(Context context, List<BarValue> barValues) {

            super(context);
            if (barValues != null) {
                innerBarValues = barValues;
                v = new float[barValues.size()];
                for (int k = 0; k < barValues.size(); k++) {
                    float a = barValues.get(k).value;
                    v[k] = barValues.get(k).value;
                }
            }
        }

        protected void onDraw (Canvas c) {
            super.onDraw (c);
            paint.setAntiAlias (true);

            for (int i = 0; i < v.length; i ++) {

                paint.setStyle (Paint.Style.FILL);
                paint.setColor (innerBarValues.get(i).color);

                paintStroke.setStyle(Paint.Style.STROKE);
                paintStroke.setColor(strokeColor);

                paintText.setColor(valueTextColor);
                paintText.setTextSize(valueTextSize);

                paintLabelText.setColor(labelTextColor);
                paintLabelText.setTextSize(24);

                switch(chartPosition) {
                    case "bottom":
                        c.drawRect (i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paint);
                        if (stroke) { c.drawRect(i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]),(i * barWidth) + barWidth / 4, valueTextSize - 5 + largest(v) - v[i], paintText); }
                        if (labelText) {
                            c.drawRect ((v.length + 1) * barWidth, barWidth/2 + i * barWidth - 24,  (v.length + 1) * barWidth + 25, barWidth/4 + i * barWidth + 24, paint);
                            if (innerBarValues.get(i).label != null) {
                                c.drawText(innerBarValues.get(i).label, (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                        }
                        break;
                    case "top":
                        c.drawRect (i * barWidth, v[i], barWidth + i * barWidth, 0, paint);
                        if (stroke) { c.drawRect(i * barWidth, v[i], barWidth + i * barWidth, 0, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]),(i * barWidth) + barWidth / 4, v[i] + valueTextSize, paintText); }
                        if (labelText) {
                            c.drawRect ((v.length + 1) * barWidth, barWidth/2 + i * barWidth - 24,  (v.length + 1) * barWidth + 25, barWidth/4 + i * barWidth + 24, paint);
                            if (innerBarValues.get(i).label != null) {
                                c.drawText(innerBarValues.get(i).label, (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                        }
                        break;
                    case "left":
                        c.drawRect (0, i * barWidth,  v[i], barWidth + i * barWidth, paint);
                        if (stroke) { c.drawRect(0, i * barWidth, v[i], barWidth + i * barWidth, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]), v[i] + barWidth/2, barWidth/2 + i * barWidth, paintText); }
                        if (labelText) {
                            c.drawRect (largest(v) + 4 * barWidth, barWidth/2 + i * barWidth - 24,  largest(v) + 4 * barWidth + 25, barWidth/4 + i * barWidth + 24, paint);
                            if (innerBarValues.get(i).label != null) {
                                c.drawText(innerBarValues.get(i).label, largest(v) + 5 * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), largest(v) + 5 * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                        }
                        break;
                    case "right":
                        c.drawRect (largest(v) - v[i], i * barWidth,  largest(v), barWidth + i * barWidth, paint);
                        if (stroke) { c.drawRect(largest(v) - v[i], i * barWidth, largest(v), barWidth + i * barWidth, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]), largest(v), barWidth/2 + i * barWidth, paintText); }
                        if (labelText) {
                            c.drawRect (largest(v) + 4 * barWidth, barWidth/2 + i * barWidth - 24,  largest(v) + 4 * barWidth + 25, barWidth/4 + i * barWidth + 24, paint);
                            if (innerBarValues.get(i).label != null) {
                                c.drawText(innerBarValues.get(i).label, largest(v) + 5 * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), largest(v) + 5 * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                        }
                        break;
                    default:
                        c.drawRect (i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paint);
                        if (stroke) { c.drawRect(i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]),(i * barWidth) + barWidth / 4, valueTextSize - 5 + largest(v) - v[i], paintText); }
                        if (labelText) {
                            c.drawRect ((v.length + 1) * barWidth, barWidth/2 + i * barWidth - 24,  (v.length + 1) * barWidth + 25, barWidth/4 + i * barWidth + 24, paint);
                            if (innerBarValues.get(i).label != null) {
                                c.drawText(innerBarValues.get(i).label, (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * barWidth, barWidth/2 + i * barWidth, paintLabelText);
                            }
                        }
                }
            }
        }

        public float largest(float arr[]) {
            float max = arr[0];

            for (int i = 1; i < arr.length; i++)
                if (arr[i] > max)
                    max = arr[i];

            return max;
        }
    }
    
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
        public String getLabrl() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
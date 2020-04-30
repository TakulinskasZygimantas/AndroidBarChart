# AndroidBarChart for Android
[JitPack 1.0.0](https://jitpack.io/#com.github.TakulinskasZygimantas/AndroidBarChart)

AndroidColumnChart is a data visualization library for easily creating interactive bar charts in Android apps. It runs on API 16+ (Android 4.1).

## Installation

### Gradle

Add this to the root build.gradle at the end of repositories (WARNING: Make sure you add this under allprojects not under buildscript):

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency to the project build.gradle:

```
dependencies {
        implementation 'com.github.TakulinskasZygimantas:AndroidBarChart:1.0.0'
}
```

## Chart Creation

### Create New Project And Select a Project Template

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/1.png)

### Configure Your Project

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/2.png)

### Add the Repository

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/3.png)

### Add the Dependency And Sync the Project

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/4_.png)

### Add a View To a Layout

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/5.png)

### Add Java Code And Run Project

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/6.png)

### Running App

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/7.png)

## Customization And Methods

### Method: setChartPosition(String Position)

```
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        
        chart.setChartPosition("top");
```

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/8_2.png)

### Methods: setStroke(boolean value), setStrokeColor(int strokeColor)

```
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        
        chart.setStroke(true);
        chart.setStrokeColor(Color.WHITE);
```

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/9_.png)

### Methods: showValue(boolean value), setValueTextColor(int textColor), setValueTextSize(int textSize)

```
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        
        chart.showValue(true);
        chart.setValueTextColor(Color.WHITE);
        chart.setValueTextSize(30)
```

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/10_.png)

### Method: setBarWidth(float barWidth)

```
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        
        chart.setBarWidth(120);
```

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/11.png)

### Method: showLabels(boolean labelText), setLabelTextColor(int labelTextColor)

```
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        
        chart.showLabels(true);
        chart.setLabelTextColor(Color.MAGENTA);
```

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/12.png)

## Example

### activity_main.xml code

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="List of 10 Lithuania cities by population"
        android:layout_margin="10dp"
        android:textSize="32dp" />

    <fragment
        android:id="@+id/fragment"
        android:name="com.example.chartviewlibrary.ChartView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

### MainActivity.java code

```
package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import com.example.chartviewlibrary.BarValue;
import com.example.chartviewlibrary.ChartView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChartView.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<BarValue> barValues = new ArrayList<>();

        barValues.add(new BarValue(536.692f, Color.parseColor("#F2476B"), "Vilnius"));
        barValues.add(new BarValue(312.120f, Color.parseColor("#FB654D"), "Kaunas"));
        barValues.add(new BarValue(164.310f, Color.parseColor("#EC2C39"), "Klaipėda"));
        barValues.add(new BarValue(107.086f, Color.parseColor("#AED8E6"), "Šiauliai"));
        barValues.add(new BarValue(98.598f, Color.parseColor("#90EE90"), "Panevėžys"));

        barValues.add(new BarValue(54.437f, Color.parseColor("#B17BAA"), "Alytus"));
        barValues.add(new BarValue(36.628f, Color.parseColor("#6598C6"), "Marijampolė"));
        barValues.add(new BarValue(33.281f, Color.parseColor("#F37248"), "Mažeikiai"));
        barValues.add(new BarValue(29.031f, Color.parseColor("#C54B6C"), "Jonava"));
        barValues.add(new BarValue(27.041f, Color.parseColor("#5784BA"), "Utena"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        ChartView myFrag = (ChartView) fragmentManager.findFragmentById(R.id.fragment);
        ChartView.MyGraphview chart = myFrag.createChart(barValues);
        chart.showValue(true);
        chart.showLabels(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
```

### Application window

![Image of Zygis](https://github.com/TakulinskasZygimantas/AndroidBarChart/blob/master/13.png)

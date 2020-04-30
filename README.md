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

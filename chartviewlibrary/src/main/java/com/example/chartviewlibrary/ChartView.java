package com.example.chartviewlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChartView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChartView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChartView() {
        // Required empty public constructor
    }

    public MyGraphview createChart(List<BarValue> barValues2) {
        List<BarValue> barValues = new ArrayList<>();

        barValues.add(new BarValue(300, Color.BLUE));
        barValues.add(new BarValue(425, Color.GRAY));
        barValues.add(new BarValue(115, Color.RED));
        barValues.add(new BarValue(200, Color.MAGENTA));

        BarValue barValue = new BarValue(600, Color.YELLOW);
        barValue.setLabel("Latata");
        barValues.add(barValue);


        LinearLayout chartLayout = (LinearLayout) getActivity().findViewById(R.id.chartLayout);
        MyGraphview mg = new MyGraphview(getContext(), barValues2);
        chartLayout.addView(mg);
        return mg;
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
        private float labelBarWidth = 60;

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
                    float a = barValues.get(k).getValue();
                    v[k] = barValues.get(k).getValue();
                }
            }
        }

        protected void onDraw (Canvas c) {
            super.onDraw (c);
            paint.setAntiAlias (true);

            for (int i = 0; i < v.length; i ++) {

                paint.setStyle (Paint.Style.FILL);
                paint.setColor (innerBarValues.get(i).getColor());

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
                            c.drawRect ((v.length + 1) * barWidth, labelBarWidth/2 + i * labelBarWidth - 24,  (v.length + 1) * barWidth + 25, labelBarWidth/4 + i * labelBarWidth + 24, paint);
                            if (innerBarValues.get(i).getLabel() != null) {
                                c.drawText(innerBarValues.get(i).getLabel(), (v.length + 2) * barWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * barWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                        }
                        break;
                    case "top":
                        c.drawRect (i * barWidth, v[i], barWidth + i * barWidth, 0, paint);
                        if (stroke) { c.drawRect(i * barWidth, v[i], barWidth + i * barWidth, 0, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]),(i * barWidth) + barWidth / 4, v[i] + valueTextSize, paintText); }
                        if (labelText) {
                            c.drawRect ((v.length + 1) * labelBarWidth, labelBarWidth/2 + i * labelBarWidth - 24,  (v.length + 1) * labelBarWidth + 25, labelBarWidth/4 + i * labelBarWidth + 24, paint);
                            if (innerBarValues.get(i).getLabel() != null) {
                                c.drawText(innerBarValues.get(i).getLabel(), (v.length + 2) * labelBarWidth, labelBarWidth/2 + i * barWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                        }
                        break;
                    case "left":
                        c.drawRect (0, i * barWidth,  v[i], barWidth + i * barWidth, paint);
                        if (stroke) { c.drawRect(0, i * barWidth, v[i], barWidth + i * barWidth, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]), v[i] + barWidth/2, barWidth/2 + i * barWidth, paintText); }
                        if (labelText) {
                            c.drawRect (largest(v) + 4 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth - 24,  largest(v) + 4 * labelBarWidth + 25, labelBarWidth/4 + i * labelBarWidth + 24, paint);
                            if (innerBarValues.get(i).getLabel() != null) {
                                c.drawText(innerBarValues.get(i).getLabel(), largest(v) + 5 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), largest(v) + 5 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                        }
                        break;
                    case "right":
                        c.drawRect (largest(v) - v[i], i * barWidth,  largest(v), barWidth + i * barWidth, paint);
                        if (stroke) { c.drawRect(largest(v) - v[i], i * barWidth, largest(v), barWidth + i * barWidth, paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]), largest(v), barWidth/2 + i * barWidth, paintText); }
                        if (labelText) {
                            c.drawRect (largest(v) + 4 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth - 24,  largest(v) + 4 * labelBarWidth + 25, labelBarWidth/4 + i * labelBarWidth + 24, paint);
                            if (innerBarValues.get(i).getLabel() != null) {
                                c.drawText(innerBarValues.get(i).getLabel(), largest(v) + 5 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), largest(v) + 5 * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                        }
                        break;
                    default:
                        c.drawRect (i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paint);
                        if (stroke) { c.drawRect(i * barWidth, valueTextSize + largest(v) - v[i], barWidth + i * barWidth, largest(v), paintStroke); }
                        if (valueText) { c.drawText(String.format("%4.1f", v[i]),(i * barWidth) + barWidth / 4, valueTextSize - 5 + largest(v) - v[i], paintText); }
                        if (labelText) {
                            c.drawRect ((v.length + 1) * barWidth, labelBarWidth/2 + i * labelBarWidth - 24,  (v.length + 1) * labelBarWidth + 25, labelBarWidth/4 + i * labelBarWidth + 24, paint);
                            if (innerBarValues.get(i).getLabel() != null) {
                                c.drawText(innerBarValues.get(i).getLabel(), (v.length + 2) * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
                            }
                            else {
                                c.drawText(String.format("%4.1f", v[i]), (v.length + 2) * labelBarWidth, labelBarWidth/2 + i * labelBarWidth, paintLabelText);
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChartView.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartView newInstance(String param1, String param2) {
        ChartView fragment = new ChartView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_view, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

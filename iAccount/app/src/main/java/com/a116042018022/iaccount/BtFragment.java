package com.a116042018022.iaccount;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class BtFragment extends Fragment {
    private PieChart pc;
    private List<PieEntry> yVals = new ArrayList<>();
    private int shf, gz, qt, y, s, z, x, qt2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pc = getActivity().findViewById(R.id.pc);
        pc.setEntryLabelTextSize(20f);

        Description description = new Description();
        description.setText("");
        pc.setDescription(description);
        Legend l = pc.getLegend();
        l.setEnabled(false);
        pc.setHoleRadius(0f);
        pc.setTransparentCircleRadius(0f);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#ab0000"));
        colors.add(Color.parseColor("#ab009b"));
        colors.add(Color.parseColor("#2500ab"));
        colors.add(Color.parseColor("#00aba2"));;
        colors.add(Color.parseColor("#ab6700"));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);

        pc.setData(pieData);
    }

    public void addShouruData(int num1, int num2, int num3) {
        shf = num1;
        gz = num2;
        qt = num3;
        int sum = num1 + num2 + num3;
        float data1 = (float) num1 / sum * 100;
        float data2 = (float) num2 / sum * 100;
        float data3 = (float) num3 / sum * 100;
        if (data1 != 0)
            yVals.add(new PieEntry(data1, "生活费"));
        if (data2 != 0)
            yVals.add(new PieEntry(data2, "工资"));
        if (data3 != 0)
            yVals.add(new PieEntry(data3, "其他"));
    }

    public void addZhichuData(int num1, int num2, int num3, int num4, int num5) {
        y = num1;
        s = num2;
        z = num3;
        x = num4;
        qt2 = num5;
        int sum = num1 + num2 + num3 + num4 + num5;
        float data1 = (float) num1 / sum * 100;
        float data2 = (float) num2 / sum * 100;
        float data3 = (float) num3 / sum * 100;
        float data4 = (float) num4 / sum * 100;
        float data5 = (float) num5 / sum * 100;
        if (data1 != 0)
            yVals.add(new PieEntry(data1, "衣"));
        if (data2 != 0)
            yVals.add(new PieEntry(data2, "食"));
        if (data3 != 0)
            yVals.add(new PieEntry(data3, "住"));
        if (data4 != 0)
            yVals.add(new PieEntry(data4, "行"));
        if (data5 != 0)
            yVals.add(new PieEntry(data5, "其他"));
    }

    public int getShf() {
        return shf;
    }

    public int getGz() {
        return gz;
    }

    public int getQt() {
        return qt;
    }

    public int getY() {
        return y;
    }

    public int getS() {
        return s;
    }

    public int getZ() {
        return z;
    }

    public int getX() {
        return x;
    }

    public int getQt2() {
        return qt2;
    }
}
package com.example.examenpractico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Informacion extends AppCompatActivity {
    BarChart graficaBarras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Intent intent = getIntent();
        HashMap<String, String> mapeo_info = (HashMap<String, String>)intent.getSerializableExtra("map_info");
        if(mapeo_info!=null){
            System.out.println(mapeo_info);
            graficaBarras = findViewById(R.id.graficoBarras);
            TextView nombretext = findViewById(R.id.txtViewName);
            nombretext.setText(mapeo_info.get("name"));
            List<BarEntry> entradas = new ArrayList<>();
            entradas.add(new BarEntry(0f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("intelligence")))));
            entradas.add(new BarEntry(1f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("strength")))));
            entradas.add(new BarEntry(2f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("speed")))));
            entradas.add(new BarEntry(3f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("durability")))));
            entradas.add(new BarEntry(4f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("power")))));
            entradas.add(new BarEntry(5f,Integer.parseInt(Objects.requireNonNull(mapeo_info.get("combat")))));


            BarDataSet datos = new BarDataSet(entradas,"HABILIDADES");
            String[] labels = {"inteligencia","fuerza","velocidad","durabilidad","poder","combate"};
            BarData data = new BarData(datos);
            datos.setColors(ColorTemplate.COLORFUL_COLORS);
            data.setBarWidth(0.9f);
            graficaBarras.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
            graficaBarras.getXAxis().setPosition(XAxis.XAxisPosition.TOP);
            graficaBarras.getXAxis().setDrawAxisLine(true);
            graficaBarras.getXAxis().setDrawAxisLine(true);
            graficaBarras.getXAxis().setGranularity(1f);
            graficaBarras.getXAxis().setLabelCount(labels.length);
            graficaBarras.setData(data);
            graficaBarras.setFitBars(true);
            graficaBarras.invalidate();

        }



    }
}
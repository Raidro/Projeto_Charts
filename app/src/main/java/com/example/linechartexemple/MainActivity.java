package com.example.linechartexemple;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LineChart myChart;
    List<Entry> valores = new ArrayList<>();
    List<Entry> valores2 = new ArrayList<>();
    LineDataSet dataset1;
    LineDataSet dataset2;
    ArrayList<ILineDataSet> colecaoDados = new ArrayList<>();
    LineData dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myChart = (LineChart) findViewById(R.id.meuGrafico);


        myChart.setDragEnabled(true);
        myChart.setScaleEnabled(false);

        /*retira a regua do lado direito*/

        myChart.getAxisRight().setEnabled(false);


        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 100);
            valores.add(new Entry(i, val));
        }
        for (int i = 0; i < 10; i++) {

            float val = (float) (Math.random() * 100);
            valores2.add(new Entry(i, val));
        }
        dataset1 = new LineDataSet(valores, "Coletas 01");
        dataset1.setFillAlpha(150);

        /*configuracao da linha*/
        dataset1.setColor(Color.RED);
        dataset1.setLineWidth(3f);
        dataset1.setValueTextSize(12f);
        dataset1.setValueTextColor(Color.GREEN);

        /*data set 2*/
        dataset2 = new LineDataSet(valores2, "Coletas 02");
        dataset2.setFillAlpha(150);

        /*configuracao da linha*/
        dataset2.setColor(Color.MAGENTA);
        dataset2.setLineWidth(3f);
        dataset2.setValueTextSize(12f);
        dataset2.setValueTextColor(Color.DKGRAY);

        String[] meses = new String[]{"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Oct", "Nov", "Dec"};

        XAxis eixoX = myChart.getXAxis();
        eixoX.setValueFormatter(new formatEixoX(meses));
        eixoX.setGranularity(1);
        eixoX.setPosition(XAxis.XAxisPosition.BOTTOM);







        /*linha de restricao maxima*/

        LimitLine linhaMax = new LimitLine(50f, "Limite Maximo");
        linhaMax.setLineColor(Color.BLACK);
        linhaMax.setLineWidth(3f);
        linhaMax.setTextColor(Color.GREEN);
        linhaMax.setTextSize(12f);
        linhaMax.enableDashedLine(10f, 20f, 0);

        YAxis eixoEsq = myChart.getAxisLeft();
        eixoEsq.addLimitLine(linhaMax);

        /*limite do eixo Y*/

        eixoEsq.setAxisMinimum(0);
        eixoEsq.setAxisMaximum(100);


        colecaoDados.add(dataset1);
        colecaoDados.add(dataset2);
        dados = new LineData(colecaoDados);
        myChart.setData(dados);


    }

    public class formatEixoX implements IAxisValueFormatter {

        private String[] mValores;

        public formatEixoX(String[] mValores) {
            this.mValores = mValores;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValores[(int) value];
        }
    }
}

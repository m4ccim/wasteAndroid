/*
package com.example.andrusha.notavendingv09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsActivity extends AppCompatActivity {

    private BarChart statistics;
    private Retrofit retrofit;
    private JsonPlaceholderApi jsonPlaceholderApi;
    private EditText month, cardId;
    private Button generateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        month = (EditText)findViewById(R.id.monthForStat);
        cardId = (EditText)findViewById(R.id.cardIdForStat);

        generateBtn = (Button)findViewById(R.id.generateStat);



        statistics = (BarChart)findViewById(R.id.chart);

        clickedButton();
    }

    public void clickedButton() {
        generateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cid = cardId.getText().toString();
                        int mon = Integer.parseInt(month.getText().toString());
                        drawChart(cid, mon);
                    }
                }
        );
    }

    public void drawChart(String id, int mon) {
        Call<List<CertainStatistics>> call = NetworkService.getInstance()
                .getJSONApi().getCertainStatisticsForAllMachines(id, mon);

        call.enqueue(new Callback<List<CertainStatistics>>() {
            @Override
            public void onResponse(Call<List<CertainStatistics>> call, Response<List<CertainStatistics>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(),
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String res = new DateFormatSymbols().getMonths()[Integer.parseInt(month.getText().toString())-1];

                List<CertainStatistics> stat = response.body();

                ArrayList<BarEntry> yVals = new ArrayList<>();

                ArrayList<String> strs = new ArrayList<String>();

                int max = 0;

                for (int i = 0; i < stat.size(); i++) {
                    yVals.add(new BarEntry(stat.get(i).getUses(), i));
                    if (stat.get(i).getUses() > max) {
                        max = stat.get(i).getUses();
                    }
                    strs.add("Machine id: " + stat.get(i).getMachineId());
                }


                BarDataSet set = new BarDataSet(yVals, res);
                set.setColors(ColorTemplate.COLORFUL_COLORS);
                set.setDrawValues(true);

                BarData data = new BarData(strs, set);


                statistics.setData(data);
                statistics.setDescription("");
                statistics.animateY(500);
                statistics.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<CertainStatistics>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

//    public void setData(int count) {
//        ArrayList<BarEntry> yVals = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * 100);
//            yVals.add(new BarEntry(i, (int) value));
//        }
//
//        BarDataSet set = new BarDataSet(yVals, "Data Set");
//        set.setColors(ColorTemplate.MATERIAL_COLORS);
//        set.setDrawValues(true);
//
//        BarData data = new BarData(set);
//
//        statistics.setData(data);
//        statistics.invalidate();
//        statistics.animateY(500);
//    }
}
*/

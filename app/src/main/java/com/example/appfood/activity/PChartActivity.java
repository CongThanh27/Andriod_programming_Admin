package com.example.appfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.appfood.R;



import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appfood.api.APIService;
import com.example.appfood.model.listDoanhThu;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private PieChart mChart;
    private String a = new String();

    private float choxacnhan=0;
    private float daxacnhan=0;
   private  float dahuy=0;
    private float dangvanchuyen=0;
    private float danhan=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pchart);

        mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("PieChart");
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);

        addDataSet(mChart);

        mChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        getCount("1", new CountCallback() {
            @Override
            public void onCountResult(String result) {
               choxacnhan = Float.parseFloat(result);
                // Sử dụng giá trị choxacnhan
                getCount("2", new CountCallback() {
                    @Override
                    public void onCountResult(String result) {
                        daxacnhan = Float.parseFloat(result);
                        // Sử dụng giá trị daxacnhan
                        getCount("3", new CountCallback() {
                            @Override
                            public void onCountResult(String result) {
                                dangvanchuyen = Float.parseFloat(result);
                                // Sử dụng giá trị daxacnhan
                                getCount("4", new CountCallback() {
                                    @Override
                                    public void onCountResult(String result) {
                                        danhan = Float.parseFloat(result);
                                        // Sử dụng giá trị daxacnhan
                                        getCount("0", new CountCallback() {
                                            @Override
                                            public void onCountResult(String result) {
                                                dahuy = Float.parseFloat(result);
                                                // Sử dụng giá trị daxacnhan
                                                float[] yData = { choxacnhan, daxacnhan, dahuy,dangvanchuyen,danhan };
                                                String[] xData = { "Chờ xác nhân", "Đã xác nhận", "Đã hủy","Đang vận chuyển","Đã nhận" };

                                                for (int i = 0; i < yData.length;i++){
                                                    yEntrys.add(new PieEntry(yData[i],i));
                                                }
                                                for (int i = 0; i < xData.length;i++){
                                                    xEntrys.add(xData[i]);
                                                }

                                                PieDataSet pieDataSet=new PieDataSet(yEntrys,"Status Order");
                                                pieDataSet.setSliceSpace(2);
                                                pieDataSet.setValueTextSize(12);

                                                ArrayList<Integer> colors=new ArrayList<>();
                                                colors.add(Color.GRAY);
                                                colors.add(Color.BLUE);
                                                colors.add(Color.RED);
                                                colors.add(Color.YELLOW);
                                                colors.add(Color.GREEN);

                                                pieDataSet.setColors(colors);

                                                Legend legend=pieChart.getLegend();
                                                legend.setForm(Legend.LegendForm.CIRCLE);
                                                legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

                                                PieData pieData=new PieData(pieDataSet);
                                                pieChart.setData(pieData);
                                                pieChart.invalidate();


                                            }
                                        });

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });








    }

    /*private void getCount(String status){
        APIService apiService= APIService.apiService;


        Call<listDoanhThu> call =apiService.getCount(status);
        call.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> call, Response<listDoanhThu> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null) {
                        if (response.body().getDoanhthu().get(0).getPrice() == null) {
                           if(status.contains("1"))
                               choxacnhan=0;
                           else if(status.contains("2"))
                                daxacnhan=0;
                           else if(status.contains("3"))
                               dangvanchuyen=0;
                           else if(status.contains("4"))
                               danhan=0;
                           else if(status.contains("0"))
                               dahuy=0;

                        }

                        else {

                            if(status.contains("1"))
                                choxacnhan=Float.parseFloat(response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("2"))
                                daxacnhan=Float.parseFloat(response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("3"))
                                dangvanchuyen=Float.parseFloat(response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("4"))
                                danhan=Float.parseFloat(response.body().getDoanhthu().get(0).getPrice());
                            else if(status.contains("0"))
                                dahuy=Float.parseFloat(response.body().getDoanhthu().get(0).getPrice());

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<listDoanhThu> call, Throwable t) {
                Toast.makeText(PChartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    // Định nghĩa một Callback để nhận kết quả từ phương thức getCount()
    interface CountCallback {
        void onCountResult(String result);
    }*/
    private void getCount(String status, final CountCallback callback) {
        APIService apiService = APIService.apiService;

        Call<listDoanhThu> call = apiService.getCount(status);
        call.enqueue(new Callback<listDoanhThu>() {
            @Override
            public void onResponse(Call<listDoanhThu> call, Response<listDoanhThu> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getDoanhthu().isEmpty()) {
                        if (response.body().getDoanhthu().get(0).getPrice().isEmpty()) {
                            // Xử lý giá trị mặc định
                            callback.onCountResult("0");
                        } else {
                            float price = Float.parseFloat(response.body().getDoanhthu().get(0).getPrice().toString());
                            callback.onCountResult(String.valueOf(price));
                        }

                    }
                    else if(response.body().getDoanhthu().isEmpty()) {
                        callback.onCountResult("0");
                    }
                }
            }

            @Override
            public void onFailure(Call<listDoanhThu> call, Throwable t) {
                Toast.makeText(PChartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                // Xử lý lỗi và gửi giá trị mặc định
                callback.onCountResult("0.0");
            }
        });


    }
    public interface CountCallback {
        void onCountResult(String result);
    }

}
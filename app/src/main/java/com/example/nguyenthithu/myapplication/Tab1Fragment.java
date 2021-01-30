package com.example.nguyenthithu.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1Fragment} interface
 * to handle interaction events.
 * Use the {@link Tab1Fragment#} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {

    //biến toàn cục biến trạng thái
    GraphView graphTemperature1;
    GraphView graphTemperature2;
    int counter = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
       // ImageView imageView = (ImageView) view.findViewById(R.id.my_image);

         /* ngày 12.01.2021 */
        Timer aTimer = new Timer();
        TimerTask aTask =new TimerTask() {
            @Override
            public void run() {
                sendDataToThingSpeak("12",counter+"");
                // getDataToThingSpeak();
                counter = counter +1;
                if(counter >=20) counter =10;
            }
        };

        aTimer.schedule(aTask, 30000, 60000);


          /* ngày 14.01.2021 thiết kế giao */
        graphTemperature1 = view.findViewById(R.id.graphTemperature1);
        graphTemperature2 = view.findViewById(R.id.graphTemperature2);

        DataPoint[] dataPointTemp = new DataPoint[5];
        dataPointTemp[0] = new DataPoint(0, 30);
        dataPointTemp[1] = new DataPoint(1, 31);
        dataPointTemp[2] = new DataPoint(2, 31);
        dataPointTemp[3] = new DataPoint(3, 29);
        dataPointTemp[4] = new DataPoint(4, 29);

        LineGraphSeries<DataPoint> seriesTemp = new LineGraphSeries<>(dataPointTemp);
        //show dư liêụ trên grag
         showDataOnGraph(seriesTemp, graphTemperature1);

        //lấy dữ liệu từ server
        setupBlinkyTimer();

        // chỉnh sửa thông tin của sensor
        ImageButton btn_edit = view.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gọi hàm show
                displayAlertDialog(view.getContext(), " Cảm biên 1","ghi chú");
            }
        });


        //======================================================================================
       return view;
    }//</**********************************************************>

    private void sendDataToThingSpeak(String ID, String value){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        String apiURL = "https://api.thingspeak.com/update?api_key=2QCJ81R498BZNB52&field1"
                +  "=" + value;

        Log.d("sendDataToThingSpeak","apiURL");
        Request request = builder.url(apiURL).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }
    // ngày 14.01.21
    private void getDataToThingSpeak(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        String apiURL = "https://api.thingspeak.com/channels/1280928/feeds.json?results=5";

        Log.d("apiURL:  ",apiURL);
        Request request = builder.url(apiURL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("ABC",e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String jsonString = response.body().string();
                try{
                    JSONObject jsonData = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonData.getJSONArray("feeds");
                    double temp0 = jsonArray.getJSONObject(0).getDouble("field1");
                    double temp1 = jsonArray.getJSONObject(1).getDouble("field1");
                    double temp2 = jsonArray.getJSONObject(2).getDouble("field1");
                    double temp3 = jsonArray.getJSONObject(3).getDouble("field1");
                    double temp4 = jsonArray.getJSONObject(4).getDouble("field1");

                    LineGraphSeries<DataPoint> seriesTemp = new LineGraphSeries<>(new DataPoint[]
                            {   new DataPoint(0, temp0),
                                    new DataPoint(1, temp1),
                                    new DataPoint(2, temp2),
                                    new DataPoint(3, temp3),
                                    new DataPoint(4, temp4)
                            });

                    showDataOnGraph(seriesTemp, graphTemperature1);
                    showDataOnGraph(seriesTemp, graphTemperature2);

                }catch (Exception e){}
            }
        });

    }
    private void setupBlinkyTimer(){
        Timer mTimer = new Timer();
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                getDataToThingSpeak();
            }
        };
        mTimer.schedule(mTask, 2000, 5000);
    }

    private void showDataOnGraph(LineGraphSeries<DataPoint> series, GraphView graph){
        if(graph.getSeries().size() > 0){
            graph.getSeries().remove(0);
        }
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
    }
    // ngày 17.01.21
    // chỉnh sửa thông tin của sensor
    public void displayAlertDialog(Context activity, String str_NameSensor, String str_Note ) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.one_dialog, null);
        //
        final EditText NameSensor = (EditText) alertLayout.findViewById(R.id.NameSensor);

        final EditText Note = (EditText) alertLayout.findViewById(R.id.Note);

        //
        NameSensor.setText(str_NameSensor);

        Note.setText(str_Note);

        //
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("THÔNG SỐ KỸ THUẬT");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("HUỶ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("LƯU", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
                String str_minDoam = NameSensor.getText().toString();
                String str_maxDoam = Note.getText().toString();
                /// Toast.makeText(getBaseContext(), "Username: " + user + " Password: " + pass, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }



    //
}

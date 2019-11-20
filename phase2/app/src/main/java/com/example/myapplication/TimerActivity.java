//package com.example.myapplication;
//
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.app.Activity;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Chronometer;
//import android.widget.Chronometer.OnChronometerTickListener;
//
//public class TimerActivity extends Activity
//{
//    Chronometer time=null;
//    Button stop =null;
//    Button resume =null;
//    protected long convertStrTimeToLong(String strTime) {
//        // TODO Auto-generated method stub
//        String []timeArry=strTime.split(":");
//        long longTime=0;
//        if (timeArry.length==2) {
//            longTime=Integer.parseInt(timeArry[0])*1000*60+Integer.parseInt(timeArry[1])*1000;
//        }else if (timeArry.length==3){
//            longTime=Integer.parseInt(timeArry[0])*1000*60*60+Integer.parseInt(timeArry[1])
//                    *1000*60+Integer.parseInt(timeArry[0])*1000;
//        }
//        return SystemClock.elapsedRealtime()-longTime;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_entrymain);
//
//
//        time=(Chronometer) findViewById(R.id.chronometer);
//        stop =(Button) findViewById(R.id.stop);
//        resume =(Button) findViewById(R.id.resume);
//
//        stop.setVisibility(View.VISIBLE);
//        resume.setVisibility(View.VISIBLE);
//        time.setBase(SystemClock.elapsedRealtime());
//        time.setFormat("USED TIME：%s");
//        time.start();
//
//        stop.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0)
//            {
//                //flag = convertStrTimeToLong(time.getText().toString());
//
//                //time.setBase(flag);
//                time.stop();
//                //time.setBase(convertStrTimeToLong(time.getText().toString()));
//
//            }
//
//        });
//
//
//        resume.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0)
//            {
//                //time.setBase(flag);
//                time.start();
//            }
//        });
//
//    }
//
//}

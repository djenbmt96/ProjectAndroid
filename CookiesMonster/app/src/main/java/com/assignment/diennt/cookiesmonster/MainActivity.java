package com.assignment.diennt.cookiesmonster;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressBar pb1,pb2,pb3,pbc1,pb4;
    Button b1,b2;
    Handler mHandler = new Handler();
    TextView t4,t1,t2,t3,tResult;
    Boolean isRunning = false;
    public int time
    ,cookies
    , monsterEatting1
    , monsterEatting2
    , monsterAte1
    , monsterAte2
    ;
    Timer mTimer = new Timer();
    Thread thread1,thread2,threadGrandma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        pb3 = (ProgressBar) findViewById(R.id.pb3);
        pb4 = (ProgressBar) findViewById(R.id.pb4);
        pbc1 = (ProgressBar) findViewById(R.id.pbc1);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        t4 = (TextView) findViewById(R.id.t4);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        tResult = (TextView) findViewById(R.id.tResult);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        pbc1.setVisibility(View.INVISIBLE);

    }
    private void createThread1(){
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monsterAte1 < 100 && !thread1.isInterrupted()) {
                    if (!isRunning) {
                        isRunning = true;
                        Random r = new Random();
                        monsterEatting1 = r.nextInt(cookies);
                        cookies -= monsterEatting1;
                        monsterAte1 += monsterEatting1;


                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                t1.setText("Total cookies eaten so far "+ monsterAte1);
                                pb1.setProgress(monsterAte1);
                                t3.setText("Total cookies baked so far "+cookies);
                            }
                        });
                        SystemClock.sleep(r.nextInt(5)*1000);
                        isRunning = false;
                    }
                }
                onBreak();

            }
        });
    }
    private void createThreadGrandma(){
        threadGrandma = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monsterAte1 < 100 && monsterAte2 < 100  && !threadGrandma.isInterrupted()) {

                    Random r = new Random();
                    cookies +=  r.nextInt(10);




                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                                Toast.makeText(MainActivity.this, cookies +" : cookies", Toast.LENGTH_SHORT).show();
                            t3.setText("Total cookies baked so far "+cookies);
                        }
                    });
                    SystemClock.sleep(5000);

                }


            }
        });

    }
    private void createThread2(){
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monsterAte2 < 100 && !thread2.isInterrupted()) {
                    if (!isRunning) {
                        isRunning = true;
                        Random r = new Random();
                        monsterEatting2 = r.nextInt(cookies);
                        cookies -= monsterEatting2;
                        monsterAte2 += monsterEatting2;


                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                t2.setText("Total cookies eaten so far "+ monsterAte2);
                                pb2.setProgress(monsterAte2);
                                t3.setText("Total cookies baked so far "+cookies);
                            }
                        });
                        SystemClock.sleep(r.nextInt(5)*1000);
                        isRunning = false;
                    }
                }
                onBreak();


            }
        });
    }

    private void onBreak(){

        thread1.interrupt();
        thread2.interrupt();
        threadGrandma.interrupt();
        mTimer.cancel();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                        if(pbc1!= null) pbc1.setVisibility(View.INVISIBLE);
//                        Toast.makeText(MainActivity.this, "Game has stopped", Toast.LENGTH_SHORT).show();
                if(monsterAte1 > monsterAte2)
                    tResult.setText("The winner is monster 1");
                if(monsterAte1 < monsterAte2)
                    tResult.setText("The winner is monster 2");
            }
        });

    }
    private void create(){
        isRunning = false;
        mTimer = new Timer();
        pbc1.setVisibility(View.VISIBLE);
        pb1.setMax(100);
        pb2.setMax(100);
        pb4.setMax(5);
        pb4.setProgress(0);
        pb1.setProgress(0);
        pb2.setProgress(0);
        tResult.setText("");
        time = 0;
        cookies = 10;
        monsterEatting1 = 0;
        monsterEatting2 = 0;
        monsterAte1 = 0;
        monsterAte2 = 0;
        createThread1();
        createThread2();
        createThreadGrandma();
        startTimer();
        thread1.start();
        thread2.start();
        threadGrandma.start();
        t1.setText("Total cookies eaten so far ");
        t2.setText("Total cookies eaten so far ");
        t3.setText("Total cookies baked so far ");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                if(thread1 !=null || thread2 !=null || threadGrandma != null)
                    onBreak();
                create();
                break;
            case R.id.b2:
               onBreak();
                break;

        }
    }

    private void startTimer() {

       
        pb3.setMax(120);
        TimerTask  mTimerTask = new TimerTask() {
            @Override
            public void run() {
                      mHandler.post(new Runnable() {
                          @Override
                          public void run() {
                                //update UI
                              time++;
                              t4.setText("Simulation clock: "+( time  )+"/120 sec");
                            pb3.setProgress(time);
                            pb4.setProgress((time%5)==0?5:(time%5));
                              if(time == 120){
                                  onBreak();
                              }

                          }
                      });
            }
        };
    mTimer.schedule(mTimerTask,500,1000);

    }
}

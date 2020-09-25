package com.metehancoskun.catchmeth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
//import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int scored;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=(TextView)findViewById(R.id.timeText);
        scoreText=(TextView)findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        sharedPreferences=this.getSharedPreferences("com.metehancoskun.catchmeth",Context.MODE_PRIVATE);



        resimGizle();

        scored =0;

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
            timeText.setText("Time :"+l/1000);

            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off!");
                handler.removeCallbacks(runnable);
                for(int i=0;i<9;i++){
                    imageArray[i].setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart");
                alert.setMessage("Oyuna yeniden baslamak istiyor musunuz?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alert.show();



            }
        }.start();

    }

    public void score(View view){
        scored++;
        scoreText.setText("Score :"+scored);


    }
    public void resimGizle(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<9;i++){
                    imageArray[i].setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);//0 ile 8 arasi random rakam
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,1000);//periyotta calistir
            }
        };
        handler.post(runnable);

    }



}
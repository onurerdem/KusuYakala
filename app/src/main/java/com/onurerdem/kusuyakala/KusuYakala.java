package com.onurerdem.kusuyakala;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class KusuYakala extends AppCompatActivity {
    TextView textTime;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    TextView textScore;
    int score;
    int bestScore;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    TextView textBestScore;
    SharedPreferences preferences1;
    SharedPreferences.Editor editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.kusuyakala);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        score = 0;

        textBestScore = (TextView) findViewById(R.id.textBestScore);
        textBestScore.setVisibility(View.INVISIBLE);

        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textTime = (TextView) findViewById(R.id.textTime);
                textTime.setText(R.string.kalan_süre);
                textTime.setText(textTime.getText().toString() + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                textTime = (TextView) findViewById(R.id.textTime);
                textTime.setText(R.string.süre_bitti);
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                    textBestScore.setVisibility(View.VISIBLE);
                }
            }
        }.start();

        preferences1 = this.getSharedPreferences("En yüksek puan", Context.MODE_PRIVATE);
        textBestScore.setText(R.string.en_yüksek_puan2);
        textBestScore.setText(textBestScore.getText().toString() + preferences1.getInt("En yüksek puan",0));
        bestScore = preferences1.getInt("En yüksek puan",0);
    }
    @Override
    public void onBackPressed() {
        Intent ana_menu = new Intent(this,MainActivity.class);
        ana_menu.addCategory(Intent.CATEGORY_HOME);
        ana_menu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(ana_menu);
    }
    public void increaseScore(View view) {

        textScore = (TextView) findViewById(R.id.textScore);

        score++;

        textScore.setText(R.string.puanın2);

        textScore.setText(textScore.getText().toString() + score);

        if (bestScore>=score) {
            textBestScore = (TextView) findViewById(R.id.textBestScore);
            preferences1 = this.getSharedPreferences("En yüksek puan", Context.MODE_PRIVATE);
            textBestScore.setText(R.string.en_yüksek_puan2);
            textBestScore.setText(textBestScore.getText().toString() + preferences1.getInt("En yüksek puan",0));
        }
        else if (bestScore<score) {
            bestScore = score;
            textBestScore = (TextView) findViewById(R.id.textBestScore);
            preferences1 = this.getSharedPreferences("En yüksek puan", Context.MODE_PRIVATE);
            editor1 = preferences1.edit();
            editor1.putInt("En yüksek puan", bestScore);
            editor1.commit();
            textBestScore.setText(R.string.yeni_en_yüksek_puan);
            textBestScore.setText(textBestScore.getText().toString() + preferences1.getInt("En yüksek puan", 0));
        }
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random r = new Random();
                int i = r.nextInt(8 - 0);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }

        };

        handler.post(runnable);
    }
    public void tekrar_dene(View view){

        /*AlertDialog.Builder builder = new AlertDialog.Builder(KusuYakala.this);
        builder.setTitle("TEKRAR");
        builder.setMessage("Tekrar denemek istediğinize emin misiniz?");
        builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent tekrar_dene = new Intent(KusuYakala.this, KusuYakala.class);
                tekrar_dene.addCategory(Intent.CATEGORY_HOME);
                tekrar_dene.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(tekrar_dene);
            }
        });
        builder.show();*/
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.uyari);
        Button button6 = (Button) dialog.findViewById(R.id.button6);
        Button button7 = (Button) dialog.findViewById(R.id.button7);
        TextView textAlert = (TextView) dialog.findViewById(R.id.textAlert);
        TextView textTitle = (TextView) dialog.findViewById(R.id.textTitle);
        ImageView kus = (ImageView) dialog.findViewById(R.id.imageView10);
        textTitle.setText(R.string.tekrar);
        textAlert.setText(R.string.tekrar_denemek_istediğinize_emin_misiniz);
        button6.setText(R.string.evet);
        button7.setText(R.string.hayır);
        kus.setImageResource(R.drawable.kus);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tekrar_dene = new Intent(KusuYakala.this, KusuYakala.class);
                tekrar_dene.addCategory(Intent.CATEGORY_HOME);
                tekrar_dene.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(tekrar_dene);
                dialog.dismiss();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void ana_menu(View view){
        onBackPressed();
    }
}

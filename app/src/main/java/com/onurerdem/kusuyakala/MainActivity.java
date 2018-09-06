package com.onurerdem.kusuyakala;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SharedPreferences preferences2;
    SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        preferences2 = this.getSharedPreferences("dil", Context.MODE_PRIVATE);
        Locale locale = new Locale(preferences2.getString("dil",""));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        editText = (EditText) findViewById(R.id.editText);
        preferences = this.getSharedPreferences("ad", Context.MODE_PRIVATE);
        editText.setText(preferences.getString("ad",""));
    }
    @Override
    public void onBackPressed() {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("ÇIKIŞ");
        builder.setMessage("Çıkmak istediğinize emin misiniz?");
        builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent cikis = new Intent(Intent.ACTION_MAIN);
                cikis.addCategory(Intent.CATEGORY_HOME);
                cikis.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(cikis);
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
        textTitle.setText(R.string.çıkış);
        textAlert.setText(R.string.çıkmak_istediğinize_emin_misiniz);
        button6.setText(R.string.evet);
        button7.setText(R.string.hayır);
        kus.setImageResource(R.drawable.kus);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cikis = new Intent(Intent.ACTION_MAIN);
                cikis.addCategory(Intent.CATEGORY_HOME);
                cikis.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(cikis);
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
    public void Oyna(View view){
        editText = (EditText) findViewById(R.id.editText);
        if(editText.getText().toString().trim().length()>=3 && editText.getText().toString().trim().length()<=15) {
            Intent oyna = new Intent(this, KusuYakala.class);
            oyna.addCategory(Intent.CATEGORY_HOME);
            oyna.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(oyna);
        }
        else if(editText.getText().toString().trim().length()==0) {
            /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("DİKKAT!");
            builder.setMessage("Lütfen adınızı giriniz.");
            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
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
            textTitle.setText(R.string.dikkat);
            textAlert.setText(R.string.lütfen_adınızı_giriniz);
            button6.setText(R.string.tamam);
            button7.setVisibility(View.INVISIBLE);
            kus.setImageResource(R.drawable.kus);
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        else if(editText.getText().toString().trim().length()<3) {
            /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("DİKKAT!");
            builder.setMessage("Adınızın uzunluğu 3 karakterden küçük olamaz!");
            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
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
            textTitle.setText(R.string.dikkat);
            textAlert.setText(R.string.adınızın_uzunluğu_3_karakterden_küçük_olamaz);
            button6.setText(R.string.tamam);
            button7.setVisibility(View.INVISIBLE);
            kus.setImageResource(R.drawable.kus);
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        else if(editText.getText().toString().trim().length()>15) {
            /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("DİKKAT!");
            builder.setMessage("Adınızın uzunluğu 15 karakterden büyük olamaz!");
            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
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
            textTitle.setText(R.string.dikkat);
            textAlert.setText(R.string.adınızın_uzunluğu_15_karakterden_büyük_olamaz);
            button6.setText(R.string.tamam);
            button7.setVisibility(View.INVISIBLE);
            kus.setImageResource(R.drawable.kus);
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    public void Kaydet(View view) {
        editText = (EditText) findViewById(R.id.editText);
        preferences = this.getSharedPreferences("ad", Context.MODE_PRIVATE);
        /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("KAYIT");
        builder.setMessage("Kaydetmek istediğinize emin misiniz?");
        builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                editor = preferences.edit();
                editor.putString("ad", editText.getText().toString());
                editor.commit();
                editText.setText(preferences.getString("ad",""));
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
        textTitle.setText(R.string.kaydet);
        textAlert.setText(R.string.kaydetmek_istediğinize_emin_misiniz);
        button6.setText(R.string.evet);
        button7.setText(R.string.hayır);
        kus.setImageResource(R.drawable.kus);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = preferences.edit();
                editor.putString("ad", editText.getText().toString());
                editor.commit();
                editText.setText(preferences.getString("ad",""));
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
    public void Cikis(View view) {
        onBackPressed();
    }
    public void Dil(View view) {
        preferences2 = this.getSharedPreferences("dil", Context.MODE_PRIVATE);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.uyari);
        Button button6 = (Button) dialog.findViewById(R.id.button6);
        Button button7 = (Button) dialog.findViewById(R.id.button7);
        TextView textAlert = (TextView) dialog.findViewById(R.id.textAlert);
        TextView textTitle = (TextView) dialog.findViewById(R.id.textTitle);
        ImageView kus = (ImageView) dialog.findViewById(R.id.imageView10);
        textTitle.setText(R.string.dil);
        textAlert.setText(R.string.kullanmak_istediğiniz_dili_seçebilirsiniz);
        button6.setText(R.string.türkçe);
        button7.setText(R.string.english);
        kus.setImageResource(R.drawable.kus);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor2 = preferences2.edit();
                editor2.putString("dil","");
                editor2.commit();
                Locale locale = new Locale(preferences2.getString("dil",""));
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor2 = preferences2.edit();
                editor2.putString("dil","en");
                editor2.commit();
                Locale locale = new Locale(preferences2.getString("dil",""));
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }
}

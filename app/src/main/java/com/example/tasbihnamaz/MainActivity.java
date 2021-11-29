package com.example.tasbihnamaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView showDisplaytwo, showDisplay, showDisplaythree;
    public int count = 0;
    public int counttwo = 0;
    public int countthree = 0;
    HelperClass myDB;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new HelperClass(this);
        showDisplay = (TextView) findViewById(R.id.display);
        showDisplaytwo = (TextView) findViewById(R.id.displaytwo);
        showDisplaythree = (TextView) findViewById(R.id.displaythree);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.clicksound);
        LoadCounter();
        refreshsubh();
        refresallhamdullilah();
        refreshallahuakbar();

    }


    //Мы сохраняем в базе данных.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putInt("counttwo", counttwo);
        outState.putInt("countthree", countthree);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        counttwo = savedInstanceState.getInt("counttwo");
        countthree = savedInstanceState.getInt("countthree");
        showDisplay.setText("" + count);
        showDisplaytwo.setText("" + counttwo);
        showDisplaythree.setText("" + countthree);

    }

    @Override
    public void onBackPressed() {
       /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_screen, null);
        Button cancel_ = view.findViewById(R.id.textViewNegative);
        Button exit_ = view.findViewById(R.id.textViewPositive);

        TextView exit_title = view.findViewById(R.id.exit_title);
        TextView exit_desc = view.findViewById(R.id.exit_desc);

        exit_title.setText(R.string.exit_title);
        exit_desc.setText(R.string.exit_desc);

        builder.setView(view);
        final AlertDialog dialogExit = builder.create();
        dialogExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlpLogin = dialogExit.getWindow().getAttributes();
        wmlpLogin.gravity = Gravity.CENTER;
        dialogExit.show();
        dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancel_.setOnClickListener(v -> dialogExit.cancel());
        exit_.setOnClickListener(v -> MainActivity.this.finish());*/
        finish();
    }

    public void LoadCounter() {
        SharedPreferences loadCounter = getSharedPreferences("SavedCounter", MODE_PRIVATE);
        count = loadCounter.getInt("CounterValue", MODE_PRIVATE);
        counttwo = loadCounter.getInt("CounterValueTwo", MODE_PRIVATE);
        countthree = loadCounter.getInt("CounterValueThree", MODE_PRIVATE);
        String counter = Integer.toString(count);
        String countertwo = Integer.toString(counttwo);
        String counterthree = Integer.toString(countthree);
        showDisplay.setText(counter);
        showDisplaytwo.setText(countertwo);
        showDisplaythree.setText(counterthree);
    }


    //Мы обрабатываем данные методом onPause и сохраняем их в базе данных.
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences savedPreferences = getSharedPreferences("SavedCounter", MODE_PRIVATE);
        SharedPreferences.Editor editor = savedPreferences.edit();
        editor.putInt("CounterValue", count);
        editor.putInt("CounterValueTwo", counttwo);
        editor.putInt("CounterValueThree", countthree);

        editor.apply();
    }

    //По умолчанию мы устанавливаем onClick на первом представлении CardView в XML  btnAction".
    public void btnAction(View view) {
        if (count == 10000000) {
            count = 0;
        }
        count++;
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        String countValue = Integer.toString(count);
        showDisplay.setText(countValue);
    }

    //По умолчанию мы устанавливаем onClick на втором представлении CardView в XML  btnAllhamdulliah".
    public void btnAllhamdulliah(View view) {
        if (counttwo == 10000000) {
            counttwo = 0;
        }
        counttwo++;
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        String countValue = Integer.toString(counttwo);
        showDisplaytwo.setText(countValue);
    }

    //По умолчанию мы устанавливаем onClick на третьем представлении CardView в XML btnAllahuAkbar".
    public void btnAllahuAkbar(View view) {
        if (countthree == 10000000) {
            countthree = 0;
        }
        countthree++;
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        String countValue = Integer.toString(countthree);
        showDisplaythree.setText(countValue);
    }

    //Мы удаляем цифры с помощью диалогового окна и одновременно очищаем весь контейнер.
    private void refreshsubh() {

        ImageView imageView = findViewById(R.id.refresh);
        imageView.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_screen, null);
            Button cancel_ = view.findViewById(R.id.textViewNegative);
            Button exit_ = view.findViewById(R.id.textViewPositive);

            //Мы устанавливаем тексты по умалчанию.
            TextView exit_title = view.findViewById(R.id.exit_title);
            TextView exit_desc = view.findViewById(R.id.exit_desc);
            TextView refresh_desc = view.findViewById(R.id.refresh_desc);
            refresh_desc.setText(R.string.reset_description);
            exit_title.setText(R.string.exit_title);
            exit_desc.setText(R.string.reset);

            builder.setView(view);

            final AlertDialog dialogExit = builder.create();
            dialogExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlpLogin = dialogExit.getWindow().getAttributes();
            wmlpLogin.gravity = Gravity.CENTER;
            dialogExit.show();
            dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancel_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogExit.cancel();
                }
            });

            exit_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = 0;
                    showDisplay.setText("0");
                    dialogExit.cancel();
                }
            });

        });

    }

    //Мы удаляем цифры с помощью диалогового окна и одновременно очищаем весь контейнер.
    private void refresallhamdullilah() {

        ImageView imageView = findViewById(R.id.refreshcardtwo);
        imageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_screen, null);
            Button cancel_ = view.findViewById(R.id.textViewNegative);
            Button exit_ = view.findViewById(R.id.textViewPositive);

            //Мы устанавливаем тексты по умалчанию.
            TextView exit_title = view.findViewById(R.id.exit_title);
            TextView exit_desc = view.findViewById(R.id.exit_desc);
            exit_title.setText(R.string.exit_title);
            exit_desc.setText(R.string.reset);
            TextView refresh_desc = view.findViewById(R.id.refresh_desc);
            refresh_desc.setText(R.string.reset_description);
            builder.setView(view);

            final AlertDialog dialogExit = builder.create();
            dialogExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlpLogin = dialogExit.getWindow().getAttributes();
            wmlpLogin.gravity = Gravity.CENTER;
            dialogExit.show();
            dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancel_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogExit.cancel();
                }
            });

            exit_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counttwo = 0;
                    showDisplaytwo.setText("0");
                    dialogExit.cancel();
                }
            });

        });

    }

    //Мы удаляем цифры с помощью диалогового окна и одновременно очищаем весь контейнер.
    private void refreshallahuakbar() {

        ImageView imageView = findViewById(R.id.refreshcardthree);
        imageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_screen, null);
            Button cancel_ = view.findViewById(R.id.textViewNegative);
            Button exit_ = view.findViewById(R.id.textViewPositive);

            //Мы устанавливаем тексты по умалчанию.
            TextView exit_title = view.findViewById(R.id.exit_title);
            TextView exit_desc = view.findViewById(R.id.exit_desc);
            exit_title.setText(R.string.exit_title);
            exit_desc.setText(R.string.reset);
            TextView refresh_desc = view.findViewById(R.id.refresh_desc);
            refresh_desc.setText(R.string.reset_description);
            builder.setView(view);

            final AlertDialog dialogExit = builder.create();
            dialogExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlpLogin = dialogExit.getWindow().getAttributes();
            wmlpLogin.gravity = Gravity.CENTER;
            dialogExit.show();
            dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            cancel_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogExit.cancel();
                }
            });

            exit_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countthree = 0;
                    showDisplaythree.setText("0");
                    dialogExit.cancel();
                }
            });
        });

    }


}

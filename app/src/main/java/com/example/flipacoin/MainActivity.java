package com.example.flipacoin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private ImageView imgCoin;
    private boolean coinIsFliping = false;
    private int[] coinDraws = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setListenners();
    }

    private void setListenners() {
        imgCoin.setOnClickListener(view -> {
            coinIsFliping = !coinIsFliping;
            animateFlipCoin(0, 6, 0);
        });
    }

    private void pickRandomHeadOrTail() {
        Random randGen = new Random();
        double rand = randGen.nextInt(2);
        if (rand == 0) {
            imgCoin.setImageResource(R.drawable.a);
        } else {
            imgCoin.setImageResource(R.drawable.g);
        }
    }

    private void animateFlipCoin(int initialDrawCoin, int lastDrawCoin, int currentDrawCoin) {
        if (!coinIsFliping) {
            pickRandomHeadOrTail();
            return;
        }

        if (currentDrawCoin == lastDrawCoin) {
            currentDrawCoin = initialDrawCoin;
        }

        int finalCurrentDrawCoin = currentDrawCoin;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCoin.setImageResource(coinDraws[finalCurrentDrawCoin]);
                animateFlipCoin(initialDrawCoin, lastDrawCoin, finalCurrentDrawCoin + 1);
            }
        }, 50);
    }

    private void setViews() {
        imgCoin = findViewById(R.id.imgCoin);
    }




}
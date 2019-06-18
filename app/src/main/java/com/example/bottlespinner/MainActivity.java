package com.example.bottlespinner;
/**
 * Created by DextorAntony on 17/6/19.
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bottle;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;
    private int lastAngle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle = findViewById(R.id.imageView);
    }

    public void spinBottle(View v) {
        if (!spinning) {
            int newDir = random.nextInt(2800);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(3000);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            bottle.startAnimation(rotate);
        }
    }

    public void reset(View view) {
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        final Animation animRotate = new RotateAnimation(lastAngle == -1 ?
                0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animRotate.setDuration(2000);
        animRotate.setFillAfter(true);
        bottle.startAnimation(animRotate);
    }
}
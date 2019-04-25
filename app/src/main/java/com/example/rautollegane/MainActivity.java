package com.example.rautollegane;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rautollegane.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
int v;
    // sectors of our wheel (look at the image to see the sectors)
    private static final String[] sectors = {"32 red", "15 black",
            "19 red", "4 black", "21 red", "2 black", "25 red", "17 black", "34 red",
            "6 black", "27 red", "13 black", "36 red", "11 black", "30 red", "8 black",
            "23 red", "10 black", "5 red", "24 black", "16 red", "33 black",
            "1 red", "20 black", "14 red", "31 black", "9 red", "22 black",
            "18 red", "29 black", "7 red", "28 black", "12 red", "35 black",
            "3 red", "26 black", "zero"
    };

    private static final int[] values = {32, 15,
            19, 4, 21, 2, 25, 17, 34,
            6, 27, 13, 36, 11, 30, 8,
            23, 10, 5, 24, 16, 33,
            1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35,
            3, 26, 0
    };

    @BindView(R.id.spinBtn)
    Button spinBtn;
    @BindView(R.id.predictbtn)
    Button predictbtn;
    @BindView(R.id.resultTv)
    TextView resultTv;
    @BindView(R.id.wheel)
    ImageView wheel;
//    @BindView(R.id.predictnu)
//    EditText predictnu;
    // We create a Random instance to make our wheel spin randomly
    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 37 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 37f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
public void rotate(){

    degreeOld = degree % 360;
    // we calculate random angle for rotation of our wheel
    degree = RANDOM.nextInt(360) + 720;
    // rotation effect on the center of the wheel
    final RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
    rotateAnim.setDuration(3600);
    rotateAnim.setFillAfter(true);
    rotateAnim.setInterpolator(new DecelerateInterpolator());
    rotateAnim.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            // we empty the result text view when the animation start
            resultTv.setText("");;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // we display the correct sector pointed by the triangle at the end of the rotate animation
            resultTv.setText(getSector(360 - (degree % 360)));
            v = getSectorn(360 - (degree % 360));
            yesorno(v);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });

    // we start the animation
    wheel.startAnimation(rotateAnim);
}

    @OnClick(R.id.spinBtn)
    public void spin(View v) {
        degreeOld = degree % 360;
        // we calculate random angle for rotation of our wheel
        degree = RANDOM.nextInt(360) + 720;
        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // we empty the result text view when the animation start
                resultTv.setText("");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                resultTv.setText(getSector(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // we start the animation
        wheel.startAnimation(rotateAnim);
    }


    private String getSector(int degrees) {
        int i = 0;
        String text = null;
        final String[] sectors = {"32 red", "15 black",
                "19 red", "4 black", "21 red", "2 black", "25 red", "17 black", "34 red",
                "6 black", "27 red", "13 black", "36 red", "11 black", "30 red", "8 black",
                "23 red", "10 black", "5 red", "24 black", "16 red", "33 black",
                "1 red", "20 black", "14 red", "31 black", "9 red", "22 black",
                "18 red", "29 black", "7 red", "28 black", "12 red", "35 black",
                "3 red", "26 black", "zero"
        };
        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors[i];
            }

            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (text == null && i < sectors.length);

        return text;
    }


    private int getSectorn(int degrees) {
        int i = 0;
        //String text = null;
        int va = 0;

        final int[] values = {32, 15,
                19, 4, 21, 2, 25, 17, 34,
                6, 27, 13, 36, 11, 30, 8,
                23, 10, 5, 24, 16, 33,
                1, 20, 14, 31, 9, 22,
                18, 29, 7, 28, 12, 35,
                3, 26, 0
        };
        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                // text = sectors[i];
                va = values[i];
            }

            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (va == 0 && i < sectors.length);

        return va;
    }


    @OnClick(R.id.predictbtn)
    public void precict(View view) {
        // String value = predictnu.getText().toString();
        rotate();
       // int v = getSectorn(360 - (degree % 360));
        //wait(1000);
//Thread.sleep(3600);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.create();
//        builder.setCancelable(true);
//
//        if (v < 12 ) {
//            builder.setMessage("You win");
//            builder.show();
//        }
//

    }
    public int yesorno(int valu){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
    // String o =    predictnu.getText().toString();
   //  int h = Integer.parseInt(o);

        if (valu >=7 && valu <=28 ) {
            builder.setMessage("You win");
            builder.show();
//            viewKonfetti.build()
//                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
//                    .setDirection(0.0, 359.0)
//                    .setSpeed(1f, 5f)
//                    .setFadeOutEnabled(true)
//                    .setTimeToLive(2000L)
//                    .addShapes(Shape.RECT, Shape.CIRCLE)
//                    .addSizes(new Size(12, 5))
//                    .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
//                    .streamFor(300, 5000L)
        }
return 0;

    }
}

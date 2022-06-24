package com.sakshi.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start, o1, o2, o3, o4, playAgain;
    TextView sum, result, points, timer, gameOver, finalResult;
    int correctLocation, p=0, total=0;
    ArrayList<Integer> options;
    ConstraintLayout bg;

    public void start(View view)
    {
        start.setVisibility(View.INVISIBLE);
        p=0;
        total=0;
        points.setText(Integer.toString(p)+"/"+Integer.toString(total));
        setup();
        bg.setBackground(ContextCompat.getDrawable(this, R.drawable.gamebg));
        bg.setAlpha(0.5f);
        hide();
        show();
        CountDownTimer CDT= new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(Long.toString(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                hide();
                displayResult();
            }
        }.start();
    }

    public void check(View view)
    {
        int tag= Integer.parseInt(view.getTag().toString());
        total+=1;

        if(tag==correctLocation)
        {
            result.setText("Correct!");
            p+=1;
        }
        else
        {
            result.setText("Incorrect");
        }

        Log.i("points:", Integer.toString(p)+"/"+Integer.toString(total));

        points.setText(Integer.toString(p)+"/"+Integer.toString(total));
        result.setVisibility(View.VISIBLE);
        setup();
    }

    public void setup()
    {
        Random rand = new Random();
        options = new ArrayList<Integer>();

        int a= rand.nextInt(100);
        int b= rand.nextInt(100);

        sum.setText(Integer.toString(a) +"+"+ Integer.toString(b));

        correctLocation= rand.nextInt(4);

        for(int i=0;i<4;i++)
        {
            if(i==correctLocation)
            {
                options.add(a+b);
            }
            else
            {
                int incorrectOption= rand.nextInt(200);

                while(incorrectOption==a+b)
                {
                    incorrectOption= rand.nextInt(200);
                }

                options.add(incorrectOption);
            }
        }

        o1.setText(Integer.toString(options.get(0)));
        o2.setText(Integer.toString(options.get(1)));
        o3.setText(Integer.toString(options.get(2)));
        o4.setText(Integer.toString(options.get(3)));
    }

    public void hide()
    {
        o1.setVisibility(View.INVISIBLE);
        o2.setVisibility(View.INVISIBLE);
        o3.setVisibility(View.INVISIBLE);
        o4.setVisibility(View.INVISIBLE);
        points.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        sum.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);
        finalResult.setVisibility(View.INVISIBLE);
    }

    public void show()
    {
        o1.setVisibility(View.VISIBLE);
        o2.setVisibility(View.VISIBLE);
        o3.setVisibility(View.VISIBLE);
        o4.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        sum.setVisibility(View.VISIBLE);
    }

    public void displayResult()
    {
        playAgain.setVisibility(View.VISIBLE);
        gameOver.setVisibility(View.VISIBLE);
        finalResult.setVisibility(View.VISIBLE);
        finalResult.setText("Your Score:"+ Integer.toString(p)+"/"+Integer.toString(total));
        bg.setBackground(ContextCompat.getDrawable(this, R.drawable.gameoverbg));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        start= (Button) findViewById(R.id.start);
        o1= (Button) findViewById(R.id.option1);
        o2= (Button) findViewById(R.id.option2);
        o3= (Button) findViewById(R.id.option3);
        o4= (Button) findViewById(R.id.option4);
        sum= (TextView) findViewById(R.id.question);
        result= (TextView) findViewById(R.id.result);
        points= (TextView) findViewById(R.id.score);
        timer= (TextView) findViewById(R.id.timer);
        gameOver= (TextView) findViewById(R.id.gameOver);
        finalResult= (TextView) findViewById(R.id.finalResult);
        playAgain=  (Button) findViewById(R.id.playAgain);
        start.setVisibility(View.VISIBLE);
        bg= (ConstraintLayout) findViewById(R.id.bg);
        bg.setBackground(ContextCompat.getDrawable(this, R.drawable.startbg));
        hide();

    }
}
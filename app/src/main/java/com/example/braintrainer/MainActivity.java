package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button nobutton1;
    Button nobutton2;
    Button nobutton3;
    Button nobutton4;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    Button playbutton;
    int tappedPosition;
    CountDownTimer countDownTimer;
    int correctAnswer;
    int score = 0;
    int totalScore = 0;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    public int generateRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(41);
    }
    public void check(View view){
        Button button = (Button) view;
        tappedPosition = Integer.parseInt(button.getTag().toString());
        Log.i("tappedPosition", Integer.toString(tappedPosition));
        if(tappedPosition - 1 == correctAnswer) {
            textView4.setVisibility(View.VISIBLE);
            score++;
            textView4.setText("CORRECT ANSWER");
            newQues();

        }
        else {
            textView4.setVisibility(View.VISIBLE);
            textView4.setText("Wrong ANSWER");
        }
        totalScore++;
        textView3.setText(Integer.toString(score) + "/" + Integer.toString(totalScore) );
        //view.getTag().toString();//alternate way
    }
    public void playAgain(View view){
        newQues();
        countDownTimer.start();
        score = 0;
        totalScore = 0;
        textView3.setText(Integer.toString(score) + "/" + Integer.toString(totalScore) );
        nobutton1.setEnabled(true);
        nobutton2.setEnabled(true);
        nobutton3.setEnabled(true);
        nobutton4.setEnabled(true);


    }
    public void newQues(){
        Random rand = new Random();
        int a = generateRandomNumber();
        int b = generateRandomNumber();
        textView2.setText(Integer.toString(a) +" + "+ Integer.toString(b));
        int correctAnswerLoc = rand.nextInt(4);
        answer.clear();
        playbutton.setVisibility(View.INVISIBLE);
        //textView4.setVisibility(View.INVISIBLE);
        for(int i = 0 ; i < 4 ; i++ ){
            if(i == correctAnswerLoc){
                answer.add(a+b);
                correctAnswer = i;
            }
            else{
                int wrongAnswer = generateRandomNumber();
                if(wrongAnswer == a+b){
                    wrongAnswer = generateRandomNumber();
                }
                answer.add(wrongAnswer);
            }
        }
        nobutton1.setText(Integer.toString(answer.get(0)));
        nobutton2.setText(Integer.toString(answer.get(1)));
        nobutton3.setText(Integer.toString(answer.get(2)));
        nobutton4.setText(Integer.toString(answer.get(3)));
    }
    public void go(View view){
        Button goButton = (Button) findViewById(R.id.gobutton);
        goButton.setVisibility(View.INVISIBLE);
        nobutton1.setVisibility(View.VISIBLE);
        nobutton2.setVisibility(View.VISIBLE);
        nobutton3.setVisibility(View.VISIBLE);
        nobutton4.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        countDownTimer =  new CountDownTimer(20000, 1000){
            public void onTick(long milli){
                textView1.setText(Long.toString(milli/1000)+"s");
            }
            public void onFinish(){
                Log.i("countdown ends", "timesUp");
                nobutton1.setEnabled(false);
                nobutton2.setEnabled(false);
                nobutton3.setEnabled(false);
                nobutton4.setEnabled(false);
                textView4.setVisibility(View.INVISIBLE);
                playbutton.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nobutton1 = (Button) findViewById(R.id.nobutton1);
        nobutton2 = (Button) findViewById(R.id.nobutton2);
        nobutton3 = (Button) findViewById(R.id.nobutton3);
        nobutton4 = (Button) findViewById(R.id.nobutton4);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        playbutton= (Button) findViewById(R.id.playbutton);
        nobutton1.setVisibility(View.INVISIBLE);
        nobutton2.setVisibility(View.INVISIBLE);
        nobutton3.setVisibility(View.INVISIBLE);
        nobutton4.setVisibility(View.INVISIBLE);
        playbutton.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        newQues();
    }
}

package com.example.sharedpreference_storing_last_score;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView scoreTextView;
    private Button increaseButton,decreaseButton;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.textView_Id);
        increaseButton = findViewById(R.id.increaseScore_Id);
        decreaseButton = findViewById(R.id.decreaseScore_Id);

        if (loadScore()!=0){
            scoreTextView.setText("Score : "+loadScore());

        }



        increaseButton.setOnClickListener(this);
        decreaseButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.increaseScore_Id){
            score = score + 10;
            scoreTextView.setText("Score : "+score);
            saveScore(score);
        }
        else if (v.getId()==R.id.decreaseScore_Id){
            score = score - 10;
            scoreTextView.setText("Score : "+score);
            saveScore(score);

        }


    }

    private void saveScore(int score){

        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore",score);
        editor.commit();
    }

    private int loadScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore",0);
        return score;

    }

}
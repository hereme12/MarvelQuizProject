package com.example.herem.marvelquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class summary_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_layout);

        //Calls on values from main activity
        Intent intent = getIntent();

        //Sets username in text field
        TextView userName = (TextView) findViewById(R.id.full_name_text_view);
        String fullname = intent.getStringExtra("name");
        userName.setText(fullname);

        //Sets text to display summary of the total number correct
        TextView correctSummary = (TextView) findViewById(R.id.question_number_text_view);
        String totalCorrect = intent.getStringExtra("summaryTotalCorrect");
        correctSummary.setText(totalCorrect);
        //Sets text to display summary of answers
        TextView answerSummary = (TextView) findViewById(R.id.responses_text_view);
        String totalResponses = intent.getStringExtra("answers");
        answerSummary.setText(totalResponses);
    }
    public void resetQuiz(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}

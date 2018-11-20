package com.example.herem.marvelquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//  Actions once Press To Begin Button Pressed
//  Takes user to quiz_layout page
    public void startQuiz(View view) {
        int questionNumber = 1;
        //Takes User Name converts it to a String variable
        final EditText addNameEditText = (EditText) findViewById(R.id.enter_name_edit_text);
        String fullNameEditText = addNameEditText.getText().toString();

        //Intent used to switch to quiz_layout
        Intent intent = new Intent(getApplicationContext(), quiz_layout.class);
        //Send question number to quiz_layout
        intent.putExtra("questionCount", questionNumber);
        //Send User Name String variable to quiz_layout
        intent.putExtra("name", fullNameEditText);
        startActivity(intent);
    }

}
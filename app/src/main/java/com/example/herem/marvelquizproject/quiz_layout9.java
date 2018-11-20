package com.example.herem.marvelquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class quiz_layout9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_layout9);

        //Calls on values from main activity
        Intent intent = getIntent();

        //Obtain question count
        int quizCount = intent.getIntExtra("questionCount", 0);
        //Sets statement for question number text for the activity
        TextView question = (TextView) findViewById(R.id.question_number_text_view);
        question.setText(getString(R.string.question_number, quizCount));

        //Sets username in text field
        TextView userName = (TextView) findViewById(R.id.full_name_text_view);
        String fullname = intent.getStringExtra("name");
        userName.setText(fullname);

        //sets values for CheckBoxes
        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.check_box_1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.check_box_2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box_3);
        final CheckBox checkBox4 = (CheckBox) findViewById(R.id.check_box_4);

        //If answer 1 selected, then the other options are unchecked
        //Prevents multiple answers from being selected
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });
        //If answer 2 selected, then the other options are unchecked
        //Prevents multiple answers from being selected
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });
        //If answer 3 selected, then the other options are unchecked
        //Prevents multiple answers from being selected
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });
        //If answer 1 selected, then the other options are unchecked
        //Prevents multiple answers from being selected
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                }
            }
        });
    }
    //Actions when Submit Answer Button clicked
    public void submitAnswer(View view) {
        //Calls on intent to pull values from main activity
        Intent intent = getIntent();
        //Calls on user name from edit text
        String fullName = intent.getStringExtra("name");
        //Calls on question counter to update question number
        int quizCount = intent.getIntExtra("questionCount", 0);

        //Obtains responses from previous activity question
        String summaryQuestionCorrect = intent.getStringExtra("answers");
        /**Determines and creates string for if question number answer right/wrong
         *
         * params quizCount, summaryQuestionCorrect - current question number and previous responses
         */
        String questionCorrect = verifyAnswerChecked(quizCount, summaryQuestionCorrect);
        //Obtain current number of correct answers from previous Activities
        int rightAnswer = intent.getIntExtra("correctCount", 0);
        //Determines the number of correct responses
        int correctCounter = numberRight(rightAnswer);
        /**Determines and creates string for total correct answers out of 10
         *
         * params correctCounter - number answered correctly
         * Will need to pass string value after activity 10!!
         */
        String correctMessage = numberCorrectMessage(correctCounter);
        //Clear CheckBox values before processing the next activity
        clearCheckBoxes();
        //Intent to send values to the next activity
        completeIntent(fullName, quizCount, questionCorrect, correctCounter);
        //To make sure window closed once new activity started
        finish();
    }

    /**Determines and creates string for if question number answer right/wrong
     *
     * params numberAsked, totalAnswers - question number, previous Activity responses
     * returns fullResponse string with total responses to say if question number answers right/wrong
     */
    private String verifyAnswerChecked(int numberAsked, String totalAnswers) {

        String response;
        String fullResponse;

        //Boolean value for CheckBox 3
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box_3);
        boolean hasCheckBox3 = checkBox3.isChecked();

        if (hasCheckBox3) {
            //converts boolean value to correct
            response = getString(R.string.correct_message);
            //creates string with previous responses, current question number and current correct response
            fullResponse = totalAnswers +
                    "\n" + getString(R.string.full_answer, numberAsked, response);
            //Used to test if values working properly
            Toast.makeText(this, fullResponse, Toast.LENGTH_SHORT).show();
            return fullResponse;
        } else {
            //converts boolean value to incorrect
            response = getString(R.string.incorrect_message);
            //creates string with previous responses, current question number and current correct response
            fullResponse = totalAnswers +
                    "\n" + getString(R.string.full_answer, numberAsked, response);
            //Used to test if values working properly
            Toast.makeText(this, fullResponse, Toast.LENGTH_SHORT).show();
            return fullResponse;
        }

    }

    /**int value of correct answers
     *
     * @param correctRecord previous Activities correct count
     * @return correctRecord total correct count
     */
    private int numberRight(int correctRecord) {

        //Boolean value for CheckBox 3
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box_3);
        boolean hasCheckBox3 = checkBox3.isChecked();

        if (hasCheckBox3) {
            //increase value if true
            ++correctRecord;
            return correctRecord;
            //keep value the same if false
        } else {
            return correctRecord;
        }

    }
    /**Creates string for total correct answers
     *
     * params numCorrect total questions answered correctly
     * returns totalCorrect string for total correct answers out of 10
     */
    private String numberCorrectMessage(int numCorrect) {
        //string for total correct answers out of 10
        String totalCorrect = getString(R.string.correct_summary, numCorrect);

        //Toast to test if this is working correctly
        Toast.makeText(this, "Number correct " + numCorrect + " out of 10", Toast.LENGTH_SHORT).show();
        return totalCorrect;
    }

    //Clears Checkboxes when answers have been submitted.
    private void clearCheckBoxes() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.check_box_1);
        boolean hasCheckBox = checkBox1.isChecked();
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.check_box_2);
        boolean hasCheckBox2 = checkBox2.isChecked();
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box_3);
        boolean hasCheckBox3 = checkBox3.isChecked();
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.check_box_4);
        boolean hasCheckBox4 = checkBox4.isChecked();

        if (checkBox1.isChecked()) {
            checkBox1.setChecked(false);
        }
        if (checkBox2.isChecked()) {
            checkBox2.setChecked(false);
        }
        if (checkBox3.isChecked()) {
            checkBox3.setChecked(false);
        }
        if (checkBox4.isChecked()) {
            checkBox4.setChecked(false);
        }

    }

    /**Creates intent to switch to the next activity
     * params enteredName - user name, questionCounter - question count, summaryResponses - answer summary,
     * numberCorrect - number of answers correct
     */
    private void completeIntent(String enteredName, int questionCounter, String summaryResponses, int numberCorrect){
        //increase question count
        ++ questionCounter;
        //Intent used to switch to quiz_layout
        Intent intent = new Intent(getApplicationContext(), quiz_layout10.class);
        //Send question number to quiz_layout
        intent.putExtra("name", enteredName);
        //Send response to question 1
        intent.putExtra("questionCount", questionCounter);
        //Send User Name String variable to quiz_layout
        intent.putExtra("answers", summaryResponses);
        //Send number of correct responses
        intent.putExtra("correctCount", numberCorrect);
        startActivity(intent);
    }

}

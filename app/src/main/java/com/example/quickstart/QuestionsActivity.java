package com.example.quickstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionsActivity extends AppCompatActivity {
    private EditText nameInputField, emailfild, mobilefild;
    private CheckBox catQuestionInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();
        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);

        nameInputField = (EditText) findViewById(R.id.et_name);
        emailfild = (EditText) findViewById(R.id.et_email);
        mobilefild = (EditText) findViewById(R.id.et_mobile);
        catQuestionInputField = (CheckBox) findViewById(R.id.question_cats_input);

        findViewById(R.id.questions_submit_button).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               String nameInput = nameInputField.getText().toString();
               String email = emailfild.getText().toString();
               String mobile = mobilefild.getText().toString();
               String catQuestionInput = String.valueOf(catQuestionInputField.isChecked());
               Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, email, mobile, catQuestionInput);
               completeQuestionnaireCall.enqueue(callCallback);
               }
            }
        );
    }

    private final Callback<Void> callCallback = new Callback<Void>() {

        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };
}

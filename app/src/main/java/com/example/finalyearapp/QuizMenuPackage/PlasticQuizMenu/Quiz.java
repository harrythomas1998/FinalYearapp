package com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.Question;

import com.example.finalyearapp.QuizMenuPackage.QuizResults;
import com.example.finalyearapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class Quiz extends AppCompatActivity {

    private int score;
    String description;
    int id;
    private boolean answered;
    private RadioGroup rbGroup;

    private RadioButton b1;
    private RadioButton b2;
    private RadioButton b3;
    private RadioButton b4;
    private Button popup;


    private long timeLeftInMillis;
    boolean answer;
    String number;

    private TextView t1_question;
    private TextView next;
    private int incorrect = 0;
    private int correct =0;
    int total = 1;
    int total2;
    int total1;
    int answerNr;



    Dialog myDialog;
    private ImageView image;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        number = intent.getStringExtra("quiznum");


        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.drawable.plasticicon);

        b1 = (RadioButton) findViewById(R.id.option1);
        b2 = (RadioButton) findViewById(R.id.option2);
        b3 = (RadioButton) findViewById(R.id.option3);
        b4 = (RadioButton) findViewById(R.id.option4);


         total1 = Integer.parseInt(number);
        total ++;
        total2 = total1 + 4;
        t1_question = (TextView) findViewById(R.id.questionsTxt);
        UpdateQuestion();



        myDialog = new Dialog(this);

    }





    private void UpdateQuestion() {



        if (total1 > total2) {
            details();
        } else {

            DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Questions").child("Plastic").child(String.valueOf(total1));
            databaseref.addValueEventListener(new ValueEventListener() {

                @Override

                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    final Question question = dataSnapshot.getValue(Question.class);

                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getAnswer1());
                    b2.setText(question.getAnswer2());
                    b3.setText(question.getAnswer3());
                    b4.setText(question.getAnswer4());
                    description = question.getDescription();
                    id = question.getId();

                    popup = (Button) findViewById(R.id.popup);
                    popup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
                            RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
                            int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

                            if (answerNr == id) {

                                answer = true;

                            } else {
                                answer = false;
                            }

                            ShowPopup();

                        }


                    });

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });
        }
    }

    public void ShowPopup() {

        Button close;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        close = myDialog.findViewById(R.id.close);


        TextView txtanswer = (TextView) myDialog.findViewById(R.id.Answer);
        if (answer== false) {
            txtanswer.setBackgroundColor(Color.RED);
            txtanswer.setText("Sorry this is Incorrect");
            incorrect++;

        } else {
            txtanswer.setBackgroundColor(Color.GREEN);
            txtanswer.setText("Well done this is correct");
            correct++;

        }


        TextView textview = (TextView) myDialog.findViewById(R.id.description);
        textview.setText(description);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                myDialog.dismiss();
                total1++;
                UpdateQuestion();

            }

        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        }










    private void details(){



        total = total -1 ;

        Intent resultintent = new Intent(Quiz.this, QuizResults.class);
        resultintent.putExtra("total",String.valueOf(total));
        resultintent.putExtra("correct",String.valueOf(correct));
        resultintent.putExtra("incorrect",String.valueOf(incorrect));
        startActivity(resultintent);



    }





    protected void onStart() {

        super.onStart();

        Intent intent = getIntent();

    }
    }




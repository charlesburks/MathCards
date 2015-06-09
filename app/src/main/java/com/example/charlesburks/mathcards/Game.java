package com.example.charlesburks.mathcards;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game extends ActionBarActivity {

    int answer;
    int equation,a,b,f1,f2,f3;
    int points;
    String winner;
    int guessCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Spinner spinner = (Spinner) findViewById(R.id.my_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.my_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void buttonClick2(View v){
        RadioButton answerA = (RadioButton)findViewById(R.id.aRadBtt);
        RadioButton answerB = (RadioButton)findViewById(R.id.bRadBtt);
        RadioButton answerC = (RadioButton)findViewById(R.id.cRadButt);
        RadioButton answerD = (RadioButton)findViewById(R.id.dRadBtt);

        Spinner spinner = (Spinner) findViewById(R.id.my_spinner);
        String answer = spinner.getSelectedItem().toString();
        TextView question = (TextView) findViewById(R.id.questionTxt);


        Random rand = new Random();
        a = rand.nextInt((20 - 0) + 1);
        b = rand.nextInt((20 - 0) + 1);


        if (answer.equals("Addition")){
            equation = (a + b);
            setF1();
            setF2();
            setF3();
            int [] options = {equation,f1,f2,f3};
            ShuffleArray(options);
            question.setText(a +" + "+ b);
            answerA.setText(""+options[0]);
            answerB.setText(""+options[1]);
            answerC.setText(""+options[2]);
            answerD.setText(""+options[3]);



        } else if (answer.equals("Subtraction")){
            equation = (a - b);
            setF1();
            setF2();
            setF3();
            int [] options = {equation,f1,f2,f3};
            ShuffleArray(options);
            question.setText(a +" - "+ b);
            answerA.setText(""+options[0]);
            answerB.setText(""+options[1]);
            answerC.setText(""+options[2]);
            answerD.setText(""+options[3]);

        } else {
            equation = (a * b);
            setF1();
            setF2();
            setF3();
            int [] options = {equation,f1,f2,f3};
            ShuffleArray(options);
            question.setText(a +" X "+ b);
            answerA.setText(""+options[0]);
            answerB.setText(""+options[1]);
            answerC.setText(""+options[2]);
            answerD.setText(""+options[3]);

        }

    }
    private void ShuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    //Enter Button
    public void buttonCLick4(View view){
        TextView score = (TextView) findViewById(R.id.scoreTxt);
        score.setText("Score: " + points);
        Toast.makeText(Game.this, winner, Toast.LENGTH_SHORT).show();
        guessCounter+=1;
        TextView question = (TextView) findViewById(R.id.questionTxt);
        RadioButton answerA = (RadioButton)findViewById(R.id.aRadBtt);
        RadioButton answerB = (RadioButton)findViewById(R.id.bRadBtt);
        RadioButton answerC = (RadioButton)findViewById(R.id.cRadButt);
        RadioButton answerD = (RadioButton)findViewById(R.id.dRadBtt);

        answerA.setChecked(false);
        answerB.setChecked(false);
        answerC.setChecked(false);
        answerD.setChecked(false);

        answerA.setText("");
        answerB.setText("");
        answerC.setText("");
        answerD.setText("");

        question.setText("");



    }
    public void setF1(){
        Random rand = new Random();
        int finalQuestion;

        if (guessCounter % 3 == 0){
            finalQuestion = equation + (rand.nextInt((3 - 1) + 1));
        } else{
            finalQuestion = equation - (rand.nextInt((3 - 1) + 1));
        }
        if (finalQuestion == a+b){
            finalQuestion=finalQuestion+1;
        }
        f1 = finalQuestion;
    }

    public void  setF2(){
        Random rand = new Random();
        int finalQuestion;

        if (guessCounter % 2 == 0){
            finalQuestion = equation + (rand.nextInt((3 - 1) + 1));
        } else{
            finalQuestion = equation - (rand.nextInt((3 - 1) + 1));
        }
        if (finalQuestion == a+b){
            finalQuestion=finalQuestion-2;
        }
        if (finalQuestion == f1 || finalQuestion== f3){
            finalQuestion=finalQuestion+2;
        }
        f2 = finalQuestion;
    }

    public void setF3(){
        Random rand = new Random();
        int finalQuestion;

        if (guessCounter % 1 == 0){
            finalQuestion = equation + (rand.nextInt((3 - 1) + 1));
        } else{
            finalQuestion = equation - (rand.nextInt((3 - 1) + 1));
        }
        if (finalQuestion == a+b){
            finalQuestion=finalQuestion+3;
        }
        if (finalQuestion == f1 || finalQuestion == f2){
            finalQuestion=finalQuestion+3;
        }
        f3 = finalQuestion;
    }


    public void buttonClick3(View view){
        RadioButton answerA = (RadioButton)findViewById(R.id.aRadBtt);
        RadioButton answerB = (RadioButton)findViewById(R.id.bRadBtt);
        RadioButton answerC = (RadioButton)findViewById(R.id.cRadButt);
        RadioButton answerD = (RadioButton)findViewById(R.id.dRadBtt);
        boolean checked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.aRadBtt:
                if (checked)
                    if(answerA.getText().toString().equals(""+equation)) {
                    points += 1;
                        winner = "Correct!";
                    //score.setText("Score: " + points);
                }else if (checked) {
                    points-=1;
                        winner = "Incorrect!";}
                //score.setText("Score: " + points);}
                break;
            case R.id.bRadBtt:
                if (checked)
                 if(answerB.getText().toString().equals(""+equation)) {
                    points += 1;
                     winner = "Correct!";
                    //score.setText("Score: " + points);
                }else if (checked) {
                    points-=1;
                     winner = "Incorrect!";}
                    //score.setText("Score: " + points);}
                break;
            case R.id.cRadButt:
                if (checked)
                if (answerC.getText().toString().equals(""+equation)) {
                    points += 1;
                    winner = "Correct!";
                    //score.setText("Score: " + points);
                }else if (checked) {
                    points-=1;
                    winner = "Incorrect!";}
                    //score.setText("Score: " + points);}
                break;
            case R.id.dRadBtt:
                if (checked)
                    if(answerD.getText().toString().equals(""+equation)) {
                    points += 1;
                        winner = "Correct!";
                    //score.setText("Score: " + points);
                }else  {
                    points-=1;
                        winner = "Incorrect!";}
                    //score.setText("Score: " + points);}
                break;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

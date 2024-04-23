package com.example.quizplacas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tela1 extends AppCompatActivity implements View.OnClickListener {

    // Todos os ids da tela
    TextView txtTotalQuest, txtQuests;
    Button btnA, btnB, btnC, btnD;
    Button btnEnviar;
    ImageView imgPlacas;

    //Varíaveis de score, total de questões, Index de resposta atual, e a resposta selecionada
    int score = 0;
    int totalQuestion = RespostasQuests.question.length;

    int currentQuestIndex = 0;
    String respostaSelec = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);


        txtTotalQuest = findViewById(R.id.txtTotalQuest);
        txtQuests = findViewById(R.id.txtQuests);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnEnviar = findViewById(R.id.btnEnviar);
        imgPlacas = findViewById(R.id.imgPlacas);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);


        txtTotalQuest.setText("Total de questões: "+ totalQuestion);
//carregar nova questão
         loadNewQuestion();


    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        btnA.setBackgroundColor(Color.WHITE);
        btnB.setBackgroundColor(Color.WHITE);
        btnC.setBackgroundColor(Color.WHITE);
        btnD.setBackgroundColor(Color.WHITE);

        //Se o botão for clicado
        if (clickedButton.getId()==R.id.btnEnviar) {

            // Para aumentar o score se a resposta selecionada for a correta
            if (respostaSelec.equals(RespostasQuests.respostasCertas[currentQuestIndex])) {
                score++;
            }
            // Incremento para trocar de pergunta e carregar nova pergunta
            currentQuestIndex++;
            loadNewQuestion();

        }

        else {
            // Escolhas dos botões clicados
            respostaSelec = clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.YELLOW);

        }

    }

    //Método de carregar nova questão
    void loadNewQuestion(){

        // Para quando chegar no fim do array de questões
        if (currentQuestIndex == totalQuestion){
            finishQuiz();
            return;
        }

        // Atualizar a imagem da placa de trânsito
        imgPlacas.setImageResource(RespostasQuests.imgPlacas[currentQuestIndex]);
        txtQuests.setText(RespostasQuests.question[currentQuestIndex]);
        btnA.setText(RespostasQuests.escolhas[currentQuestIndex][0]);
        btnB.setText(RespostasQuests.escolhas[currentQuestIndex][1]);
        btnC.setText(RespostasQuests.escolhas[currentQuestIndex][2]);
        btnD.setText(RespostasQuests.escolhas[currentQuestIndex][3]);

        txtQuests.setText(RespostasQuests.question[currentQuestIndex]);
        btnA.setText(RespostasQuests.escolhas[currentQuestIndex][0]);
        btnB.setText(RespostasQuests.escolhas[currentQuestIndex][1]);
        btnC.setText(RespostasQuests.escolhas[currentQuestIndex][2]);
        btnD.setText(RespostasQuests.escolhas[currentQuestIndex][3]);


    }
   void  finishQuiz() {
       String passStatus = "";
       if (score > totalQuestion * 0.60) {
           passStatus = "Aprovado";
       } else {
           passStatus = "Reprovado";
       }

       new AlertDialog.Builder(this)
               .setTitle(passStatus)
               .setMessage("Sua pontuação foi de " + score + " de " + totalQuestion)
               .setPositiveButton("Reiniciar", (dialogInterface, i) -> restartQuiz())
               .setCancelable(false)
               .show();


   }

  void  restartQuiz(){
        score = 0;
        currentQuestIndex = 0;
        loadNewQuestion();
  }
}
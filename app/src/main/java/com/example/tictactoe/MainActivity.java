package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = Cross and 1 = Circle
    int activePlayer = 0;

    boolean gameisActive = true;
    // 2 in the set means not played or empty.

    int[] gameState= {2,2,2,2,2,2,2,2,2};

    // they are to represent the possible winning position

    int[][] winnigPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view) {
        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameisActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross2);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle2);
                activePlayer = 0;


            }
            counter.animate().translationYBy(1000f).setDuration(500);
            for(int[] winningPosition: winnigPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2){
                    //Winner
                    gameisActive = false;

                    String winner= "Player 2";
                    if(gameState[winningPosition[0]]==0){
                        winner = "Player 1";
                    }



                    TextView winnerMessage = findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner +" has Won!!! ");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                    layout.animate().rotation(1800).setDuration(1000);
                    layout.animate().scaleX(1.5f).setDuration(1000);
                    layout.animate().scaleY(1.5f).setDuration(1000);






                }else{
                    boolean gameisOver = true;

                    for(int counterstate : gameState){
                        if(counterstate== 2){
                            gameisOver = false;
                        }

                    }
                    if(gameisOver){
                        TextView winnerMessage = findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It is a DRAW!!! ");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        layout.animate().rotation(1800).setDuration(1000);
                        layout.animate().scaleX(1.5f).setDuration(1000);
                        layout.animate().scaleY(1.5f).setDuration(1000);
                    }
                }
            }
        }
    }




    public void playAgain(View view){
        gameisActive = true;



        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        System.out.println("Working");

        activePlayer = 0;

        for (int index = 0; index < 9; index++) {
            gameState[index] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 is yellow and 1 is red

    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {2,5,8},{1,4,7}};

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    String winner = "Red";


                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Yellow";
                    }

                    // Winner Message

                    gameActive = false;

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);




                } else {

                    boolean gameisover = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) {

                            gameisover = false;

                        }
                    }

                    if (gameisover == true) {

                        gameActive = false;

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }


            }

        }


    }

    public void replayButton(View view) {

        gameActive = true;

        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        GridLayout grid = findViewById(R.id.GridLayoutId);

        for (int i = 0; i < grid.getChildCount(); i++) {

            ((ImageView) grid.getChildAt(i)).setImageResource(0);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

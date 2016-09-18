package com.example.andrew.test;

import java.util.Random;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button board[][];
    TextView textView;
    TicTacToeGame ticTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setBackgroundColor(Color.BLUE);

        board = new Button[3][3];
        board[0][0] = (Button) findViewById(R.id.tictactoe1);
        board[1][0] = (Button) findViewById(R.id.tictactoe2);
        board[2][0] = (Button) findViewById(R.id.tictactoe3);
        board[0][1] = (Button) findViewById(R.id.tictactoe4);
        board[1][1] = (Button) findViewById(R.id.tictactoe5);
        board[2][1] = (Button) findViewById(R.id.tictactoe6);
        board[0][2] = (Button) findViewById(R.id.tictactoe7);
        board[1][2] = (Button) findViewById(R.id.tictactoe8);
        board[2][2] = (Button) findViewById(R.id.tictactoe9);
        //board.setBackgroundColor(Color.WHITE);

        textView = (TextView) findViewById(R.id.gameInfo);
        textView.setText("Click a tile to start.");
        textView.setTextColor(Color.WHITE);


        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                board[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!board[i][j].isEnabled()) {
                    board[i][j].setText(" ");
                    board[i][j].setEnabled(true);
                }
            }
        }

        ticTacToeGame = new TicTacToeGame();

    }

    class MyClickListener implements View.OnClickListener {
        int x, y;

        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            if (board[x][y].isEnabled()) {
                board[x][y].setEnabled(false);
                board[x][y].setText("X");
                board[x][y].setTextColor(Color.BLUE);
                board[x][y].setTextSize(TypedValue.COMPLEX_UNIT_PX, 60);
                ticTacToeGame.c[x][y] = 1;
                if (!ticTacToeGame.checkBoard()) {
                    ticTacToeGame.makeMove();
                    board[ticTacToeGame.move.x][ticTacToeGame.move.y].setText("O");
                    board[ticTacToeGame.move.x][ticTacToeGame.move.y].setTextColor(Color.MAGENTA);
                    board[ticTacToeGame.move.x][ticTacToeGame.move.y].setTextSize(TypedValue.COMPLEX_UNIT_PX, 60);
                    board[ticTacToeGame.move.x][ticTacToeGame.move.y].setEnabled(false);
                    if(ticTacToeGame.checkBoard()) {
                        displayOutome();
                        // end game
                    }
                }
                else {
                    displayOutome();
                    // end game
                }
            }
        }

        private void displayOutome() {
            if (ticTacToeGame.gameOutcome == 0) {
                textView.setText("It's a draw. Time to start the day!");
            } else if (ticTacToeGame.gameOutcome == 1) {
                textView.setText("You win. Time to start the day!");
            } else{
                textView.setText("You lose. Time to start the day!");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

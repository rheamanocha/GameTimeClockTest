package com.example.andrew.test;
import java.util.Random;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by Andrew on 9/17/16.
 */

public class TicTacToeGame {
    // matrix cell: 0 = untaken, 1 = you, 2 = com
    int c[][];
    // 0 = tie 1 = you win 2 = com win
    int gameOutcome;
    // to store AI's last move
    Move move;

    class Move {
        int x;
        int y;
    }

    public TicTacToeGame() {
        c = new int[3][3];
        move = new Move();
        gameOutcome = 0;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                c[i][j] = 0;
            }
        }
    }

    public void makeMove() {
        if (c[0][0] == 0 &&
                ((c[0][1] == 1 && c[0][2] == 1) ||
                        (c[1][1] == 1 && c[2][2] == 1) ||
                        (c[1][0] == 1 && c[2][0] == 1))) {
            markSquare(0, 0);
        } else if (c[0][1] == 0 &&
                ((c[1][1] == 1 && c[2][1] == 1) ||
                        (c[0][0] == 1 && c[0][2] == 1))) {
            markSquare(0, 1);
        } else if (c[0][2] == 0 &&
                ((c[0][0] == 1 && c[0][1] == 1) ||
                        (c[2][0] == 1 && c[1][1] == 1) ||
                        (c[1][2] == 1 && c[2][2] == 1))) {
            markSquare(0, 2);
        } else if (c[1][0] == 0 &&
                ((c[1][1] == 1 && c[1][2] == 1) ||
                        (c[0][0] == 1 && c[2][0] == 1))) {
            markSquare(1, 0);
        } else if (c[1][1] == 0 &&
                ((c[0][0] == 1 && c[2][2] == 1) ||
                        (c[0][1] == 1 && c[2][1] == 1) ||
                        (c[2][0] == 1 && c[0][2] == 1) ||
                        (c[1][0] == 1 && c[1][2] == 1))) {
            markSquare(1, 1);
        } else if (c[1][2] == 0 &&
                ((c[1][0] == 1 && c[1][1] == 1) ||
                        (c[0][2] == 1 && c[2][2] == 1))) {
            markSquare(1, 2);
        } else if (c[2][0] == 0 &&
                ((c[0][0] == 1 && c[1][0] == 1) ||
                        (c[2][1] == 1 && c[2][2] == 1) ||
                        (c[1][1] == 1 && c[0][2] == 1))) {
            markSquare(2, 0);
        } else if (c[2][1] == 0 &&
                ((c[0][1] == 1 && c[1][1] == 1) ||
                        (c[2][0] == 1 && c[2][2] == 1))) {
            markSquare(2, 1);
        } else if (c[2][2] == 0 &&
                ((c[0][0] == 1 && c[1][1] == 1) ||
                        (c[0][2] == 1 && c[1][2] == 1) ||
                        (c[2][0] == 1 && c[2][1] == 1))) {
            markSquare(2, 2);
        } else {
            Random rand = new Random();

            int a = rand.nextInt(3);
            int b = rand.nextInt(3);
            while (c[a][b] != 0) {
                a = rand.nextInt(3);
                b = rand.nextInt(3);
            }
            markSquare(a, b);
        }
        checkBoard();
    }

    private void markSquare(int a, int b) {
        c[a][b] = 2;
        checkBoard();
        move.x = a;
        move.y = b;
    }

    public boolean checkBoard() {
        boolean gameOver = false;
        if((c[0][0] == 1 && c[1][0] == 1 && c[2][0] == 1) ||
                (c[0][1] == 1 && c[1][1] == 1 && c[2][1] == 1) ||
                (c[0][2] == 1 && c[1][2] == 1 && c[2][2] == 1) ||
                (c[0][0] == 1 && c[0][1] == 1 && c[0][2] == 1) ||
                (c[1][0] == 1 && c[1][1] == 1 && c[1][2] == 1) ||
                (c[2][0] == 1 && c[2][1] == 1 && c[2][2] == 1) ||
                (c[0][0] == 1 && c[1][1] == 1 && c[2][2] == 1) ||
                (c[0][2] == 1 && c[1][1] == 1 && c[2][0] == 1)) {
            gameOutcome = 1;
            gameOver = true;
        } else if((c[0][0] == 2 && c[1][0] == 2 && c[2][0] == 2) ||
                (c[0][1] == 2 && c[1][1] == 2 && c[2][1] == 2) ||
                (c[0][2] == 2 && c[1][2] == 2 && c[2][2] == 2) ||
                (c[0][0] == 2 && c[0][1] == 2 && c[0][2] == 2) ||
                (c[1][0] == 2 && c[1][1] == 2 && c[1][2] == 2) ||
                (c[2][0] == 2 && c[2][1] == 2 && c[2][2] == 2) ||
                (c[0][0] == 2 && c[1][1] == 2 && c[2][2] == 2) ||
                (c[0][2] == 2 && c[1][1] == 2 && c[2][0] == 2)) {
            gameOutcome = 2;
            gameOver = true;
        } else {
            boolean empty = false;
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    if(c[i][j] == 0) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOutcome = 0;
                gameOver = true;
            }
        }
        return gameOver;
    }
}
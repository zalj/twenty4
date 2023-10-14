package com.springaction.knight;

import java.util.Arrays;

public class KnightMain {
    private static final char X = 'x';
    private static final char O = 'o';
    private static final char BLANK = ' ';
    private static final char B = ' ';
    private static int PUZLEN = 10;

    public static void main(String[] args) {
        char[][] puzzle = new char[10][10];
        PUZLEN = puzzle.length;
        for (char[] line : puzzle) {
            Arrays.fill(line, BLANK);
        }
        puzzle = fillPuzzle(puzzle);
        if(fix(puzzle, 0)) {
            for (char[] line : puzzle)
                System.out.println(Arrays.toString(line));
        }
    }

    private static char[][] fillPuzzle(char[][] p) {
        p = new char[][]{
                {B, B, X, B, X, B, O, B, B, B},
                {B, O, X, B, B, O, B, B, X, O},
                {B, B, B, X, B, B, O, O, B, B},
                {X, B, B, B, B, B, B, B, B, X},
                {B, X, X, B, B, O, B, B, O, X},
                {X, B, B, B, B, B, O, B, B, B},
                {B, B, B, B, B, X, B, B, B, X},
                {B, B, B, X, O, B, O, B, O, B},
                {B, X, B, X, B, B, X, B, B, B},
                {B, B, X, B, B, B, B, O, X, X}
        };
        return p;
    }

    public static boolean fix(char[][] puzzle, int index) {
        if (index == PUZLEN * PUZLEN) {
            return true;
        }
        int row = index / PUZLEN, column = index % PUZLEN;
        if (isEmpty(puzzle[row][column])) {
            boolean satisfyX = isSatisfy(puzzle, index, X);
            if (satisfyX) {
                puzzle[row][column] = X;
                if (fix(puzzle, index + 1)) {
                    return true;
                }
                puzzle[row][column] = BLANK;
            }
            boolean satisfyO = isSatisfy(puzzle, index, O);
            if (satisfyO) {
                puzzle[row][column] = O;
                if (fix(puzzle, index + 1)) {
                    return true;
                }
                puzzle[row][column] = BLANK;
            }
            return false;
        }
        return fix(puzzle, index + 1);
    }

    public static boolean isSatisfy(char[][] puzzle, int index, char sign) {
        int row = index / PUZLEN, column = index % PUZLEN;
        return isSatisfyRule1(puzzle, row, column, sign)
                && isSatisfyRule2(puzzle, row, column, sign)
                && isSatisfyRule3(puzzle, row, column, sign);
    }

    // R1. no 3 successive same 'x' or 'o'
    public static boolean isSatisfyRule1(char[][] puzzle, int r, int c, char sign) {
        boolean r1 = c >= 2 && puzzle[r][c - 2] == sign && puzzle[r][c - 1] == sign;
        boolean r2 = c >= 1 && c + 1 < PUZLEN && puzzle[r][c - 1] == sign && puzzle[r][c + 1] == sign;
        boolean r3 = c + 2 < PUZLEN && puzzle[r][c + 1] == sign && puzzle[r][c + 2] == sign;

        boolean c1 = r >= 2 && puzzle[r - 2][c] == sign && puzzle[r - 1][c] == sign;
        boolean c2 = r >= 1 && r + 1 < PUZLEN && puzzle[r - 1][c] == sign && puzzle[r + 1][c] == sign;
        boolean c3 = r + 2 < PUZLEN && puzzle[r + 1][c] == sign && puzzle[r + 2][c] == sign;

        return !(r1 || r2 || r3 || c1 || c2 || c3);
    }

    // R2. number of 'x' and 'o' is equal in same row or column.
    public static boolean isSatisfyRule2(char[][] puzzle, int r, int c, char sign) {
        int signsInRow = 1, signsInColumn = 1;
        for (int i = 0; i < PUZLEN; i++) {
            signsInRow += sign == puzzle[r][i] ? 1 : 0;
            signsInColumn += sign == puzzle[i][c] ? 1 : 0;
        }
        return signsInRow <= PUZLEN / 2 && signsInColumn <= PUZLEN / 2;
    }

    // R3. no 2 rows or 2 columns is same
    private static boolean isSatisfyRule3(char[][] puzzle, int row, int column, char sign) {
        puzzle[row][column] = sign;
        for (int i = 0; i < PUZLEN; i++) {
            boolean sameRow = true;
            if (i == row) {
                continue;
            }
            for (int j = 0; j < PUZLEN; j++) {
                if (isEmpty(puzzle[i][j]) || isEmpty(puzzle[row][j]) || puzzle[i][j] != puzzle[row][j]) {
                    sameRow = false;
                    break;
                }
            }
            if (sameRow) {
                puzzle[row][column] = BLANK;
                return false;
            }
        }
        for (int j = 0; j < PUZLEN; j++) {
            boolean sameCol = true;
            if (j == column) {
                continue;
            }
            for (int i = 0; i < PUZLEN; i++) {
                if (isEmpty(puzzle[i][j]) || isEmpty(puzzle[i][column]) || puzzle[i][j] != puzzle[i][column]) {
                    sameCol = false;
                    break;
                }
            }
            if (sameCol) {
                puzzle[row][column] = BLANK;
                return false;
            }
        }
        puzzle[row][column] = BLANK;
        return true;
    }

    public static boolean isEmpty(char c) {
        return c != X && c != O;
    }
}

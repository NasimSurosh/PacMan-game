package pacMan;

import java.util.Random;
import java.util.Scanner;

public class PacManGame {
  private static final int BoardSize = 16;
  private static final char PacMan = 'P';
  private static final char Ghost = 'G';
  private static final char Dots = '.';
  private static final char PowerUp = '*';
  private char[][] gameBoard;
  private int PacmanRaw;
  private int PacmanColum;
  private int gameScore;

  public PacManGame() {
    gameBoard = new char[BoardSize][BoardSize];
    runGame();
    placeOfPacman();
    placeOfGhost();
    placeOfDots();
    PowerUps();
    gameScore = 0;
  }

  public void runGame() {
    for (int i = 0; i < BoardSize; i++) {
      for (int j = 0; j < BoardSize; j++) {
        gameBoard[i][j] = ' ';
      }
    }
  }

  public void placeOfPacman() {
    PacmanRaw = BoardSize / 2;
    PacmanColum = BoardSize / 2;
    gameBoard[PacmanRaw][PacmanColum] = PacMan;
  }

  public void placeOfGhost() {
    Random rand = new Random();
    for (int i = 0; i < 3; i++) {
      int gameRaw;
      int gameColum;
      do {
        gameRaw = rand.nextInt(BoardSize);
        gameColum = rand.nextInt(BoardSize);
      } while (gameBoard[gameRaw][gameColum] != ' ');
      gameBoard[gameRaw][gameColum] = Ghost;
    }
  }

  public void placeOfDots() {
    Random rand = new Random();
    for (int i = 0; i < BoardSize / 2; i++) {
      int gameRaw;
      int gameColum;
      do {
        gameRaw = rand.nextInt(BoardSize);
        gameColum = rand.nextInt(BoardSize);
      } while (gameBoard[gameRaw][gameColum] != ' ');
      gameBoard[gameRaw][gameColum] = Dots;
    }
  }

  public void PowerUps() {
    Random rand = new Random();
    for (int i = 0; i < 2; i++) {
      int gameRaw;
      int gameColum;
      do {
        gameRaw = rand.nextInt(BoardSize);
        gameColum = rand.nextInt(BoardSize);
      } while (gameBoard[gameRaw][gameColum] != ' ');
      gameBoard[gameRaw][gameColum] = PowerUp;
    }
  }

  public void board() {
    for (int i = 0; i < BoardSize; i++) {
      for (int j = 0; j < BoardSize; j++) {
        System.out.print(gameBoard[i][j] + " ");
      }
      System.out.println();
    }
  }

  public boolean validMove(int row, int colum) {
    return (row >= 0) && (row < BoardSize) && (colum >= 0) && (colum < BoardSize)/* && (gameBoard[row][colum] == ' ' */;
  }

  public void move(char movement) {
    int moveRaw = PacmanRaw;
    int moveColum = PacmanColum;
    switch (movement) {
    case 'U':
      moveRaw -= 1;
      break;
    case 'D':
      moveRaw++;
      break;
    case 'L':
      moveColum--;
      break;
    case 'R':
      moveColum++;
      break;
    }

    boolean moveIsValid = validMove(moveRaw, moveColum);
    System.out.println(moveRaw);
    System.out.println(moveColum);
    System.out.println(moveIsValid);
    
    if (validMove(moveRaw, moveColum)) {

      char newPosition = gameBoard[moveRaw][moveColum];
      if (newPosition == Dots) {
        gameScore++;
        System.out.println(gameScore);
      } else if (newPosition == Ghost) {
        System.out.println("Game over");

        gameScore = gameScore * 5;
      }
      gameBoard[PacmanRaw][PacmanColum] = ' ';
      PacmanRaw = moveRaw;
      PacmanColum = moveColum;
      gameBoard[PacmanRaw][PacmanColum] = PacMan;
    } else {
      System.out.println("Invalid move");
    }
  }

  public static void main(String[] args) {
    PacManGame pacManGame = new PacManGame();
    Scanner scan = new Scanner(System.in);
    
    while (true) {
      pacManGame.board();
      System.out.println("Enter movement (U/D/L/R)");
      char move = scan.next().charAt(0);
      pacManGame.move(move);
    }
  }
}

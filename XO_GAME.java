import java.util.Scanner;
//0181785 Sarah Saadeh
//0186179 Rahaf Albulbul
public class XO_GAME {
    // Creating The 2D Array Calling gameBoard
    static char [][] gameBoard = new char [3][3] ;
    // Flag to keep track of whose turn it is
    static boolean turn = true;
    // Flag to keep track of whether the game is over
    static boolean gameOver = false;

    public static void main(String[] args) {
        while (true) {
            // Initialize the game board
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    gameBoard[i][j] = ' ';
                }
            }

            // Create the two player threads
            Thread x_Thread = new Thread(new Player("X"));
            Thread o_Thread = new Thread(new Player("O"));

            // Set the gameOver flag to false
            gameOver = false;
            System.out.println("Welcome to XO GAME!!!");

            // Start the player threads
            x_Thread.start();
            o_Thread.start();

            try {
                //wait the o thread and x thread to finish execution
                x_Thread.join();
                o_Thread.join();
            } catch (InterruptedException e) {
                // Ignore exception
            }

            // Print the game result
            if (TheWinner("X")) {
                System.out.println("Congratulations! player X wins! Thanks for playing.");
            } else if (TheWinner("O")) {
                System.out.println("Congratulations! player O wins! Thanks for playing.");
            } else {
                System.out.println("It's a draw! Thanks for playing.");
            }

            // Ask the user if they want to play again
            System.out.print("Do you want to play again? (Y/N) ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("N")) {
               break;
            }
        }
    }
    static void printBoard()
    {
        System.out.println("| " + gameBoard[0][0] + " | "
                + gameBoard[0][1] + " | " + gameBoard[0][2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + gameBoard[1][0] + " | "
                + gameBoard[1][1] + " | " + gameBoard[1][2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + gameBoard[2][0] + " | "
                + gameBoard[2][1] + " | " + gameBoard[2][2]
                + " |");
    }

    // Method to check for a win
    public static boolean TheWinner(String symbol) {
        // return the first character of the string
        char symb = symbol.charAt(0);
        // Check rows
        for (int i = 0; i <= 2; i++) {
            if (gameBoard[i][0] == symb && gameBoard[i][1] == symb && gameBoard[i][2] == symb) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j <= 2; j++) {
            if (gameBoard[0][j] == symb && gameBoard[1][j] == symb && gameBoard[2][j] == symb) {
                return true;
            }
        }
        // Check diagonals
        return (gameBoard[0][0] == symb && gameBoard[1][1] == symb && gameBoard[2][2] == symb) ||
                (gameBoard[0][2] == symb && gameBoard[1][1] == symb && gameBoard[2][0] == symb);
    }

    // Method to check for a draw
    public static boolean checkDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
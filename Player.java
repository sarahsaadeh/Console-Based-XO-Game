import java.util.Scanner;
//0181785 Sarah Saadeh
//0186179 Rahaf Albulbul
public class Player extends Thread {
    private final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        while (!XO_GAME.gameOver) {
            // Wait for this player's turn
            synchronized (XO_GAME.gameBoard) {
                while (XO_GAME.turn != symbol.equals("X") && !XO_GAME.gameOver) {
                    try {
                        XO_GAME.gameBoard.wait();
                    } catch (InterruptedException e) {
                        // Ignore exception
                    }
                }
                if (!XO_GAME.gameOver) {
                    // Read input from the console and update the game board
                    Scanner scanner = new Scanner(System.in);
                    int cellNum = 0;
                    while (cellNum < 1 || cellNum > 9) {
                        System.out.print("Enter number (1-9) for " + symbol + ": ");
                        cellNum = scanner.nextInt();
                        if (cellNum < 1 || cellNum > 9) {
                            System.out.println("Invalid cell number. Try again.");
                        } else {
                            int row = (cellNum - 1) / 3;
                            int col = (cellNum - 1) % 3;
                            if (XO_GAME.gameBoard[row][col] != ' ') {
                                System.out.println("Cell is already taken. Try again.");
                                cellNum = 0;
                            } else {
                                XO_GAME.gameBoard[row][col] = symbol.charAt(0);
                            }
                        }
                    }

                    // Print out the updated game board
                    XO_GAME.printBoard();

                    if (XO_GAME.TheWinner(symbol)) {
                        XO_GAME.gameOver=true;
                    }
                    if (XO_GAME.checkDraw()) {
                        XO_GAME.gameOver=true;
                    }

                    // Toggle the turn tracker and notify the other thread
                    XO_GAME.turn = !XO_GAME.turn;
                    XO_GAME.gameBoard.notify();
                }
            }
        }
    }
}

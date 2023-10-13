//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {
    //Declarations

    //JPanels
    JPanel mainPnl;
    JPanel boardPnl;
    JPanel buttonPnl;

    //JButtons and JButtons array
    JButton quitBtn;
    JButton[][] board = new JButton[3][3];
    int size = 3;


    //game declarations
    boolean finished = false;
    boolean playing = true;
    String player = "X";
    int moveCnt = 0;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;


    public TicTacToeFrame() //create tic tac toe
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createBoardPnl();
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(600, 600);
        setTitle("TicTacToe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void createBoardPnl()
    {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));


        for (int row= 0; row < size; row++)
        {
            for (int col= 0; col < size; col++)
            {
                board[row][col] = new JButton();
                board[row][col].setFont(new Font("Sans Serif", Font.BOLD, 125));
                boardPnl.add(board[row][col]);

                board[row][col].addActionListener(this);}
        }

    }


    public void createButtonPnl()  //panel to display quit button
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 1));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        buttonPnl.add(quitBtn);
    }

    public void clearBoard() //if the game is played again, board is cleared and move count is 0
    {
        moveCnt = 0;
        player = "X"; //sets to first player again

        // sets all the board elements to a space
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
            board[row][col].setText("");
            }
        }

    }

    public void switchPlayer() // switches between x and o
    {
        if (player.equals("X"))
        {
            player = "O";
        }

        else
        {
            player = "X";
        }
    }

    public boolean isValidMove(int row, int col)//checks to see if there is nothing in the selected space
    {
        boolean retVal = false;

        if (board[row][col].getText().equals(""))
        {
            retVal = true;

        }
        return retVal;

    }

    public boolean isWin() //checks for win
    {
        if (isColWin(player) == true || isRowWin(player) == true || isDiagnalWin(player) == true)
        {
            return true; //there is a win on the board
        }

        return false; //no win on the board
    }

    public boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if (board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player))
        {
            return true;//diagonal win, left to right
        }

        if (board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player))
        {
            return true;//diagonal win right to left
        }
        return false; //no diagonal win
    }


    public boolean isColWin(String player)
    {
        // checks for a col win for specified player

        for(int col = 0; col < size; col ++)
        {
            if (board[0][col].getText().equals(player) && board[1][col].getText().equals(player) && board[2][col].getText().equals(player))
                {
                    return true;// there is a col win
                }
        }


        return false; // no col win
    }

    public boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row = 0; row < size; row ++)
        {
            if (board[row][0].getText().equals(player) && board[row][1].getText().equals(player) && board[row][2].getText().equals(player))
            {
                return true; //row win
            }
        }
        return false; // no row win
    }

    public boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < size; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < size; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (e.getSource() == board[row][col]) {
                    if (isValidMove(row, col) == false) {
                        JOptionPane.showMessageDialog(null, "Invalid move, try again.");
                    } else //valid move
                    {
                        board[row][col].setText(player); //adds text to board
                        moveCnt++; //adds to move count

                        if (moveCnt >= MOVES_FOR_WIN)//checks for wins in win count
                        {
                            if (isWin() == false)// no win on the board
                            {
                                if (moveCnt >= MOVES_FOR_TIE)//checks for ties
                                {
                                    if (isTie())//tie
                                    {
                                        int reply = JOptionPane.showConfirmDialog(null, "It's a tie!\n" + "Do you want to play again?", "Play again", JOptionPane.YES_NO_OPTION);

                                        if (reply == JOptionPane.YES_OPTION)//game resets to play again
                                        {
                                            clearBoard();
                                            finished = false;
                                        }
                                        else//game quits, player is done playing
                                        {
                                            System.exit(0);
                                        }
                                    }

                                    else{switchPlayer();}
                                }

                                else{switchPlayer();}

                            }

                            else//there is a win on the board
                            {
                                int reply = JOptionPane.showConfirmDialog(null, "Player " + player + " wins!\n" + "Do you want to play again?", "Play again", JOptionPane.YES_NO_OPTION);
                                if (reply == JOptionPane.YES_OPTION) //resets board
                                {
                                    clearBoard();
                                    finished = false;
                                }
                                //game ends
                                else {
                                    System.exit(0);
                                }
                            }

                        }

                        else
                        {
                            switchPlayer();
                        }
                    }

                }
            }
        }
    }
}








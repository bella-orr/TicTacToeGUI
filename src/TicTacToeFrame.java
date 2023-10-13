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
    JButton btn;
    JButton[] board = new JButton[9];


    //game declarations
    boolean finished = false;
    boolean playing = true;
    boolean isWin = false;
    String player = "X";
    int moveCnt = 0;
    int i = -1;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;


    public TicTacToeFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createBoardPnl();
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createButtonPnl();
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void createBoardPnl() {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            board[i] = new JButton();
            board[i].setFont(new Font("Sans Serif", Font.BOLD, 125));
            boardPnl.add(board[i]);

            board[i].addActionListener(this);
        }

    }


    public void createButtonPnl() {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 1));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        buttonPnl.add(quitBtn);
    }

    public void clearBoard() {
        playing = true;
        moveCnt = 0;

        // sets all the board elements to a space
        for (int i = 0; i < 9; i++)
        {
            board[i].setText("");
        }

        player = "X";
    }

    public void switchPlayer()
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

    public boolean isValidMove(int i)
    {
        boolean retVal = false;

        if (board[i].getText().equals(""))
        {
            retVal = true;

        }
        return retVal;

    }

    public boolean isWin()
    {
        if (isColWin(player) == true || isRowWin(player) == true || isDiagnalWin(player) == true)
        {
            return true;
        }

        return false;
    }

    public boolean isDiagnalWin(String player) {
        // checks for a diagonal win for the specified player

        if (board[0].getText().equals(player) && board[4].getText().equals(player) && board[8].getText().equals(player)) {
            return true;
        }

        if (board[2].getText().equals(player) && board[4].getText().equals(player) && board[6].getText().equals(player)) {
            return true;
        }
        return false;
    }


    public boolean isColWin(String player) {
        // checks for a col win for specified player
        if (board[0].getText().equals(player) && board[3].getText().equals(player) && board[6].getText().equals(player)) {
            return true;
        }
        if (board[1].getText().equals(player) && board[4].getText().equals(player) && board[7].getText().equals(player)) {
            return true;
        }
        if (board[2].getText().equals(player) && board[5].getText().equals(player) && board[8].getText().equals(player)) {
            return true;
        }

        return false; // no col win
    }

    public boolean isRowWin(String player) {
        // checks for a row win for the specified player
        if (board[0].getText().equals(player) && board[1].getText().equals(player) && board[2].getText().equals(player)) {

            return true;
        }

        if (board[3].getText().equals(player) && board[4].getText().equals(player) && board[5].getText().equals(player)) {

            return true;
        }

        if (board[6].getText().equals(player) && board[7].getText().equals(player) && board[8].getText().equals(player)) {

            return true;
        }
        return false; // no row win
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
                for (int i = 0; i < 9; i++)
                {
                    if (e.getSource() == board[i])
                    {
                        if (isValidMove(i) == false)
                        {
                            if (player == "O")
                            {
                                JOptionPane.showMessageDialog(null, "Invalid move, try again.");
                            } else
                            {
                                JOptionPane.showMessageDialog(null, "Invalid move, try again.");

                            }

                        }

                        else
                        {
                            board[i].setText(player);
                            moveCnt++;

                            if(moveCnt >= MOVES_FOR_WIN)
                            {
                                if(isWin() == false)
                                {
                                    switchPlayer();
                                }


                                else
                                {
                                    int reply = JOptionPane.showConfirmDialog(null, "Player " + player + " wins!\n" + "Do you want to play again?", "Play again", JOptionPane.YES_NO_OPTION);
                                        if(reply == JOptionPane.YES_OPTION)
                                        {
                                            clearBoard();
                                            finished = false;
                                        }

                                        else
                                        {
                                            System.exit(0);
                                        }
                                }

                            }

                            else
                            {
                                switchPlayer();
                            }

                            System.out.println(moveCnt);
                        }

                    }

                }
    }
}







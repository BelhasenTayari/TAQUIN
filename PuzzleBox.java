import java.awt.*;
import javax.swing.*;

public class PuzzleBox extends JPanel {

    private int[][] board;

    private int boxSize = 80;
    private int boxMargin = 10;

    public PuzzleBox(int[][] board) {
        this.board = board;
        setPreferredSize(new Dimension(3 * boxSize + 4 * boxMargin, 3 * boxSize + 4 * boxMargin));
        setBackground(new Color(237, 237, 237)); // Light gray background color
    }

    public void setBoard(int[][] board) {
        this.board = board;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Open Sans", Font.BOLD, 36)); // Modern font style

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int number = board[row][col];
                if (number != 0) {
                    int x = col * (boxSize + boxMargin) + boxMargin;
                    int y = row * (boxSize + boxMargin) + boxMargin;
                    g2d.setColor(new Color(64, 152, 206)); // Blue box color
                    g2d.fillRect(x, y, boxSize, boxSize);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(Integer.toString(number), x + boxSize / 2 - 10, y + boxSize / 2 + 15); // Slightly
                                                                                                          // adjusted
                                                                                                          // text
                                                                                                          // position
                }
            }
        }
    }

}

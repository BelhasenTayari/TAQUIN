
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class UI extends JFrame {

    private JTextField textField;
    private int[][] initialBoard;
    private final int[][] resultBoard = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    PuzzleBox puzzlePanel;

    Taquin taquin = new Taquin();

    private int[][] getRandomBoard(int size) {
        int[][] board = new int[size][size];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = numbers.get(k);
                k++;
            }
        }
        return board;
    }

    public UI() {

        /**
         * Initialize the contents of the
         */
        setResizable(false);
        setBounds(100, 100, 735, 467);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        initialBoard = getRandomBoard(3);
        JPanel leftPanel = new JPanel();
        getContentPane().add(leftPanel);
        leftPanel.setLayout(null);

        JLabel title = new JLabel("TAQUINGAME");
        title.setFont(new Font("Verdana", Font.BOLD, 21));
        title.setBackground(new Color(0, 0, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(46, 10, 241, 48);
        leftPanel.add(title);
        puzzlePanel = new PuzzleBox(initialBoard);
        puzzlePanel.setBounds(33, 55, 280, 280);
        leftPanel.add(puzzlePanel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(22, 345, 310, 35);
        leftPanel.add(panel_1);

        JRadioButton rbBreadth = new JRadioButton("Breadth First Search");
        rbBreadth.setBackground(new Color(255, 255, 255));
        rbBreadth.setSelected(true);
        rbBreadth.setHorizontalAlignment(SwingConstants.CENTER);
        rbBreadth.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_1.add(rbBreadth);

        JRadioButton rbDepth = new JRadioButton("Depth First Search");
        rbDepth.setBackground(new Color(255, 255, 255));
        rbDepth.setHorizontalAlignment(SwingConstants.CENTER);
        rbDepth.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_1.add(rbDepth);

        // Create the ButtonGroup and add the radio buttons to it
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbBreadth);
        bg.add(rbDepth);

        JPanel rightPanel = new JPanel();

        getContentPane().add(rightPanel);
        rightPanel.setLayout(null);

        JTextArea txtResult = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtResult);
        scrollPane.setBounds(10, 10, 340, 371);
        rightPanel.add(scrollPane);

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewGame.setBounds(130, 391, 104, 34);
        btnNewGame.setBackground(Color.BLACK);
        btnNewGame.setForeground(Color.WHITE);
        rightPanel.add(btnNewGame);

        txtResult.setFont(new Font("Verdana", Font.BOLD, 13));

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialBoard = getRandomBoard(3);
                puzzlePanel.setBoard(initialBoard);
                textField.setText("");
                txtResult.setText("");
            }
        });

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Etat> path = null;
                if (rbBreadth.isSelected()) {
                    path = taquin.rechercheParLargeur(new Etat(initialBoard));
                } else {
                    path = taquin.rechercheParProfondeur(new Etat(initialBoard));
                }
                if (path == null) {
                    JOptionPane.showMessageDialog(getContentPane(), "No Solution Found.");
                } else {
                    List<Action> actions = taquin.getActions();
                    actions.add(0, null);
                    for (int i = 0; i < path.size(); i++) {
                        txtResult.setText(txtResult.getText() + "Step " + i + " Action = " + actions.get(i) + ":\n");
                        int[][] board = path.get(i).getBoard();
                        for (int[] row : board) {
                            txtResult.setText(txtResult.getText() + Arrays.toString(row) + "\n");
                        }
                    }
                    textField.setText(path.size() - 1 + "");
                    puzzlePanel.setBoard(resultBoard);
                }
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(22, 390, 86, 30);
        leftPanel.add(btnSearch);

        JButton btnReplay = new JButton(
                "Replay");
        btnReplay.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnReplay.setBounds(119, 390, 76, 30);
        leftPanel.add(btnReplay);

        btnReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResult.setText("");
                textField.setText("");
                puzzlePanel.setBoard(initialBoard);
            }
        });

        JLabel txtMoves = new JLabel(
                "Moves:");
        txtMoves.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtMoves.setBounds(207, 399, 62, 13);
        leftPanel.add(txtMoves);

        textField = new JTextField();
        textField.setBounds(261, 395, 64, 23);
        leftPanel.add(textField);
        textField.setFont(new Font("Verdana", Font.BOLD, 13));

        textField.setColumns(10);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

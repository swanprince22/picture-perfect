import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Play extends Design {

    public static JLabel start;
    public static Card mainFrame;
    public static Utilities utilities;
    public static Design[] puzzlePieces;
    public static int[] order;
    public static int numPieces = 9;
    public static LinkedList imageList;

    public static int emptyIndex;       
    public static JPanel gridPanel;     

    private static final int TILE_SIZE = 120;
    public static JLabel solve;

    public Play(Card mainFrameInstance) {
        super("/media/img/background.png");
        mainFrame = mainFrameInstance;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        add(utilities);

        setVisible(true);
    }

    public static void startGame() {
        order = generateValidOrder();
        createPuzzleImages(order);
        displayPuzzle();
    }

    private static int[] generateValidOrder() {
        Random r = new Random();
        int[] tempOrder = new int[numPieces];
        boolean valid;

        do {
            for (int i = 0; i < numPieces; i++) {
                tempOrder[i] = i;
            }

            for (int i = numPieces - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                int temp = tempOrder[i];
                tempOrder[i] = tempOrder[j];
                tempOrder[j] = temp;
            }

            valid = isValidOrder(tempOrder);

            System.out.println(Arrays.toString(tempOrder));
            if (valid)
                System.out.println("The puzzle is valid");
            else
                System.out.println("The puzzle is NOT valid");

        } while (!valid);

        return tempOrder;
    }

    private static boolean isValidOrder(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != 8 && arr[j] != 8 && arr[i] > arr[j]) {
                    counter++;
                }
            }
        }
        return counter % 2 == 0;
    }

    private static void createPuzzleImages(int[] validOrder) {
        puzzlePieces = new Design[numPieces];
        imageList = new LinkedList();

        for (int i = 0; i < numPieces; i++) {
            int imgIndex = validOrder[i];

            if (imgIndex == 8) {
                imageList.add(null);
                emptyIndex = i;
            } else {
                int imgNum = imgIndex + 1;
                Design piece = new Design("/media/img/" + imgNum + ".png");

                piece.tileNumber = imgNum;

                piece.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                piece.setMinimumSize(new Dimension(TILE_SIZE, TILE_SIZE));
                piece.setMaximumSize(new Dimension(TILE_SIZE, TILE_SIZE));

                addMoveListener(piece);

                puzzlePieces[i] = piece;
                imageList.add(piece);
            }
        }
    }

    private static void addMoveListener(Design piece) {
        piece.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int clickedIndex = imageList.indexOf(piece);

                if (isAdjacent(clickedIndex, emptyIndex)) {
                    // Swap nodes in linked list
                    imageList.swap(clickedIndex, emptyIndex);
                    emptyIndex = clickedIndex;
                    refreshGrid();

                    checkWin(mainFrame, "Menu");
                }
            }
        });
    }

    private static boolean isAdjacent(int index1, int index2) {
        int row1 = index1 / 3;
        int col1 = index1 % 3;
        int row2 = index2 / 3;
        int col2 = index2 % 3;

        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }

    private static void displayPuzzle() {
        gridPanel = new JPanel(new GridLayout(3, 3, 0, 0));

        Node current = imageList.head;

        while (current != null) {
            if (current.data == null) {
                JPanel blank = new JPanel();
                blank.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                blank.setOpaque(false);
                gridPanel.add(blank);
            } else {
                gridPanel.add(current.data);
            }
            current = current.next;
        }

            if (solve != null) {
            utilities.remove(solve);
        }

        solve = new JLabel("Solve", SwingConstants.CENTER);
        solve.setEnabled(true);               
        solve.setText("Solve");               
        solve.setForeground(Color.BLACK);     
        solve.setCursor(new Cursor(Cursor.HAND_CURSOR));

        solve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                solve.setEnabled(false);
                solve.setText("Solving...");

                new Thread(() -> {
                    Solver solver = new Solver();
                    solver.solve(mainFrame, "Menu");
                }).start();
            }
        });

        utilities.getWhitePanel().setLayout(new BorderLayout());
        utilities.getWhitePanel().add(gridPanel, BorderLayout.CENTER);
        utilities.add(solve, BorderLayout.SOUTH);

        utilities.revalidate();
        utilities.repaint();
}

    static boolean isWin() {
        Node current = imageList.head;
        int expected = 1;
        int position = 0;

        while (current != null) {
            if (position == 8) {
                if (current.data != null) return false;
                break;
            }

            if (current.data == null) return false;

            Design piece = (Design) current.data;

            if (piece.tileNumber != expected) return false;

            expected++;
            position++;
            current = current.next;
        }

        System.out.println("You win!");
        return true;
    }

    private static void checkWin(Card mainFrame, String target) {
        if (isWin()) {
            JOptionPane.showMessageDialog(null, "Congratulations! You solved the puzzle!");
            mainFrame.showPanel(target);
        }
    }

    public static void refreshGrid() {
        gridPanel.removeAll();

        Node current = imageList.head;

        while (current != null) {
            if (current.data == null) {
                JPanel blank = new JPanel();
                blank.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                blank.setOpaque(false);
                gridPanel.add(blank);
            } else {
                gridPanel.add(current.data);
            }
            current = current.next;
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }
}

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Play extends Design {

    JLabel start;
    Card mainFrame;
    Utilities utilities;
    Design[] puzzlePieces;
    private int[] order;
    private int numPieces = 9;
    private LinkedList imageList;

    private int emptyIndex;       // blank tile position
    private JPanel gridPanel;     // dynamic refresh grid

    private static final int TILE_SIZE = 120;

    public Play(Card mainFrame) {
        super("/media/img/background.png");
        this.mainFrame = mainFrame;

        utilities = new Utilities(mainFrame);
        utilities.displayWhitePanel();
        this.add(utilities);

        this.setVisible(true);
    }

    public void startGame(){
        order = generateValidOrder();
        createPuzzleImages(order);
        displayPuzzle();
    }

    private int[] generateValidOrder() {
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

    private boolean isValidOrder(int[] arr) {
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

    // Creates tiles and saves blank index
    private void createPuzzleImages(int[] validOrder) {
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

                // IMPORTANT: store tile number
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

    // Adds click movement to each tile
    private void addMoveListener(Design piece) {
        piece.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int clickedIndex = imageList.indexOf(piece);

                if (isAdjacent(clickedIndex, emptyIndex)) {
                    imageList.swap(clickedIndex, emptyIndex);
                    emptyIndex = clickedIndex;
                    refreshGrid();

                    checkWin(mainFrame, "Menu");
                }
            }
        });
    }

    // Adjacent check (no diagonals allowed)
    private boolean isAdjacent(int index1, int index2) {
        int row1 = index1 / 3;
        int col1 = index1 % 3;
        int row2 = index2 / 3;
        int col2 = index2 % 3;

        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }

    // Draws puzzle for first time
    private void displayPuzzle() {
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

        utilities.getWhitePanel().setLayout(new BorderLayout());
        utilities.getWhitePanel().add(gridPanel, BorderLayout.CENTER);
        utilities.getWhitePanel().revalidate();
        utilities.getWhitePanel().repaint();
    }

    // ✅ FIXED WIN CHECK (ascending order)
    private boolean isWin() {
        Node current = imageList.head;
        int expected = 1;
        int position = 0;

        while (current != null) {

            // Last tile must be blank
            if (position == 8) {
                if (current.data != null) return false;
                break;
            }

            if (current.data == null) return false;

            Design piece = (Design) current.data;

            // Tiles must be 1,2,3,4,5,6,7,8 in order
            if (piece.tileNumber != expected) return false;

            expected++;
            position++;
            current = current.next;
        }

        System.out.println("You win!");
        return true;
    }

    private void checkWin(Card mainFrame, String target){
        if (isWin()){
            JOptionPane.showMessageDialog(this, "🎉 Congratulations! You solved the puzzle!");
            mainFrame.showPanel(target);
        }
    }

    // Redraws puzzle after every move
    private void refreshGrid() {
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

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

    private void createPuzzleImages(int[] validOrder) {
    puzzlePieces = new Design[numPieces];
    imageList = new LinkedList();

    for (int i = 0; i < numPieces; i++) {
        int imgNum = validOrder[i] + 1;

        Design piece = new Design("/media/img/" + imgNum + ".png");
        puzzlePieces[i] = piece;

        imageList.add(piece);
    }
}

     private void displayPuzzle() {

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));

        Node current = imageList.head; 

        while (current != null) {
            gridPanel.add(current.data); 
            current = current.next;
        }

        utilities.getWhitePanel().add(gridPanel, BorderLayout.CENTER);
        utilities.getWhitePanel().revalidate();
        utilities.getWhitePanel().repaint();
    }
}

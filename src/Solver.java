import java.util.*;
import javax.swing.*;

public class Solver {

    public void solve(Card mainFrame, String target) {
        int[] startState = getCurrentState();
        List<String> moves = bfs(startState);

        if (moves == null) {
            System.out.println("No solution found!");
            return;
        }

        for (String move : moves) {
            moveBlank(move);
            try {
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bfsWin(mainFrame, target);
    }

    
    public void bfsWin(Card mainFrame, String target) {
        if (Play.isWin()) {
            JOptionPane.showMessageDialog(null, "Congratulations! You solved the puzzle!");
            System.out.print("You did it!");
            mainFrame.showPanel(target);
        }
    }

    private int[] getCurrentState() {
        int[] state = new int[9];
        LinkedList imageList = Play.imageList;
        Node current = imageList.head;
        int index = 0;
        while (current != null) {
            if (current.data == null) {
                state[index] = 8; 
            } else {
                state[index] = ((Design) current.data).tileNumber - 1;
            }
            current = current.next;
            index++;
        }
        return state;
    }

    private List<String> bfs(int[] start) {
        java.util.Queue<NodeState> queue = new java.util.LinkedList<>();
        java.util.Set<String> visited = new java.util.HashSet<>();

        NodeState startNode = new NodeState(start, new ArrayList<>());
        queue.add(startNode);
        visited.add(Arrays.toString(start));

        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        String[] dirNames = { "UP", "DOWN", "LEFT", "RIGHT" };

        while (!queue.isEmpty()) {
            NodeState current = queue.poll();
            int[] state = current.state;

            if (isGoal(state)) {
                return current.moves;
            }

            int blankIndex = findBlank(state);
            int row = blankIndex / 3;
            int col = blankIndex % 3;

            for (int i = 0; i < 4; i++) {
                int newRow = row + dirs[i][0];
                int newCol = col + dirs[i][1];

                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int swapIndex = newRow * 3 + newCol;
                    int[] newState = state.clone();

                    int temp = newState[blankIndex];
                    newState[blankIndex] = newState[swapIndex];
                    newState[swapIndex] = temp;

                    String key = Arrays.toString(newState);
                    if (!visited.contains(key)) {
                        List<String> newMoves = new ArrayList<>(current.moves);
                        newMoves.add(dirNames[i]);
                        queue.add(new NodeState(newState, newMoves));
                        visited.add(key);
                    }
                }
            }
        }
        return null;
    }

    private boolean isGoal(int[] state) {
        for (int i = 0; i < 8; i++) {
            if (state[i] != i) return false;
        }
        return state[8] == 8; 
    }

    private int findBlank(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 8) return i;
        }
        return -1;
    }

    private void moveBlank(String move) {
        int blankIndex = Play.emptyIndex;
        int targetIndex = -1;

        switch (move) {
            case "UP": targetIndex = blankIndex - 3; break;
            case "DOWN": targetIndex = blankIndex + 3; break;
            case "LEFT": targetIndex = blankIndex - 1; break;
            case "RIGHT": targetIndex = blankIndex + 1; break;
        }

        if (targetIndex >= 0 && targetIndex < 9) {
            Play.imageList.swap(blankIndex, targetIndex);
            Play.emptyIndex = targetIndex;
            Play.refreshGrid();
        }
    }

    private class NodeState {
        int[] state;
        List<String> moves;

        NodeState(int[] state, List<String> moves) {
            this.state = state;
            this.moves = moves;
        }
    }
}

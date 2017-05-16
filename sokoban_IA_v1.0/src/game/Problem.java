package game;

import java.util.ArrayList;

/**
 *
 * @author eduardo
 */
public class Problem {
    
    // the puzzle's initial state
    private final char[][] initialState;
    private char[][] goalState;

    private final int size;
    private int playerColumn;
    private int playerRow;
    
    private int pushes;
    
    public Problem(char[][] initialState, int size){
        this.initialState = initialState;
        this.size = size;
        
        // this is to find the player's position
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (initialState[i][j] == '@' || initialState[i][j] == '+') {
                    this.playerRow = i;
                    this.playerColumn = j;
                }
            }
        }
    }//end constructor
        
    public void getPlayerCoordinates(){
        System.out.println("<"+playerRow+","+playerColumn+">");
    }
    
    public int[] findPlayerCoordinates(char[][] state){
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (state[i][j] == '@' || state[i][j] == '+') {
                    this.playerRow = i;
                    this.playerColumn = j;
                }
            }
        }
        return new int[]{this.playerRow,this.playerColumn};
    }//end findPlayerCoordinates
    
    public ArrayList<int[]> findBoxesCoordinares(char[][] state){
        ArrayList<int[]> boxes = new ArrayList<>();
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (state[i][j] == '$') {
                    boxes.add(new int[]{i,j});
                }
            }
        }
        return boxes;
    }//end findBoxesCoordinares
    
    public ArrayList<int[]> findTargetsCoordinares(char[][] state){
        ArrayList<int[]> targets = new ArrayList<>();
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (state[i][j] == '.') {
                    targets.add(new int[]{i,j});
                }
            }
        }
        return targets;
    }//end findTargetsCoordinares
    
    public boolean goalTest(char[][] possibleState){
        for (int i = 0; i < possibleState.length; i++) {
            for (int j = 0; j < possibleState.length; j++) {
                if (possibleState[i][j]=='.' || possibleState[i][j]=='+') {
                    return false;
                }
            }
        }
        return true;
    }//end goalTest
    
    public String getGoalStateRep() {
        StringBuilder value = new StringBuilder();
        for (char[] row : goalState) {
            for (char cell : row) {
                value.append(cell);
            }
            value.append('\n');
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }//end toString

    private char beneathPlayer(char player){
        if (player == '+') {
            return '.';
        }
        return ' ';
    }
    
    private char beneathBox(char box){
        if (box == '*') {
            return '.';
        }
        return ' ';
    }
    
    public Node moveLeft(Node node){
        char[][] currentState = node.getState();
        findPlayerCoordinates(currentState);
        
        char neighbor = currentState[playerRow][playerColumn-1];
        
        try {
            char farNeighbor = currentState[playerRow][playerColumn-2];
            // if there's no wall left of player
            if (neighbor!='#') {
                //if left to the player is a box but can't be pushed
                if (neighbor=='$' && farNeighbor=='$' ||
                    neighbor=='$' && farNeighbor=='#' ||
                    neighbor=='$' && farNeighbor=='*') {
                    return null;
                // if left to the player is a box and can be moved
                } else if(neighbor=='$' || neighbor=='*') {
                    switch(farNeighbor){
                        case ' ':
                            currentState[playerRow][playerColumn-2] = '$';
                            if (neighbor=='*') {
                                currentState[playerRow][playerColumn-1] = '+';
                            } else {
                                currentState[playerRow][playerColumn-1] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow][playerColumn-2] = '*';
                            if (neighbor=='*') {
                                currentState[playerRow][playerColumn-1] = '+';
                            } else {
                                currentState[playerRow][playerColumn-1] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves and pushes a box to the left
                    playerColumn -= 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.LEFT);
                    nextNode.setState(currentState);
                    nextNode.setPush();
                    return nextNode;
                // if left to the player is empty space
                } else if(neighbor == ' ' || neighbor == '.'){
                    switch(neighbor){
                        case ' ':
                            currentState[playerRow][playerColumn-1] = '@';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow][playerColumn-1] = '+';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves to the left
                    playerColumn -= 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.LEFT);
                    nextNode.setState(currentState);
                    return nextNode;
                }
            }
        } catch (Exception e) {}
        return null;
    }//end moveLeft
    
    public Node moveRight(Node node){
        char[][] currentState = node.getState();
        findPlayerCoordinates(currentState);
        
        char neighbor = currentState[playerRow][playerColumn+1];
        try{
            char farNeighbor = currentState[playerRow][playerColumn+2];

            // if there's no wall right of player
            if (neighbor!='#') {
                //if right to the player is a box but can't be pushed
                if (neighbor=='$' && farNeighbor=='$' ||
                    neighbor=='$' && farNeighbor=='#' ||
                    neighbor=='$' && farNeighbor=='*') {
                    return null;
                // if right to the player is a box and can be moved
                } else if(neighbor=='$' || neighbor=='*') {
                    switch(farNeighbor){
                        case ' ':
                            currentState[playerRow][playerColumn+2] = '$';
                            if (neighbor=='*') {
                                currentState[playerRow][playerColumn+1] = '+';
                            } else {
                                currentState[playerRow][playerColumn+1] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow][playerColumn+2] = '*';
                            if (neighbor=='*') {
                                currentState[playerRow][playerColumn+1] = '+';
                            } else {
                                currentState[playerRow][playerColumn+1] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves and pushes a box to the right
                    
                    playerColumn += 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.RIGHT);
                    nextNode.setState(currentState);
                    nextNode.setPush();
                    return nextNode;
                // if right to the player is empty space
                } else if(neighbor == ' ' || neighbor == '.'){
                    switch(neighbor){
                        case ' ':
                            currentState[playerRow][playerColumn+1] = '@';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow][playerColumn+1] = '+';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves to the right
                    playerColumn += 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.RIGHT);
                    nextNode.setState(currentState);
                    return nextNode;
                }
            }
        } catch (Exception e) {}
        return null;
    }//end moveRight
    
    public Node moveUp(Node node){
        char[][] currentState = node.getState();
        findPlayerCoordinates(currentState);
        
        char neighbor = currentState[playerRow-1][playerColumn];
        try{
            char farNeighbor = currentState[playerRow-2][playerColumn];

            // if there's no wall above player
            if (neighbor!='#') {
                //if above the player is a box but can't be pushed
                if (neighbor=='$' && farNeighbor=='$' ||
                    neighbor=='$' && farNeighbor=='#' ||
                    neighbor=='$' && farNeighbor=='*') {
                    return null;
                // if above the player is a box and can be moved
                } else if(neighbor=='$' || neighbor=='*') {
                    switch(farNeighbor){
                        case ' ':
                            currentState[playerRow-2][playerColumn] = '$';
                            if (neighbor=='*') {
                                currentState[playerRow-1][playerColumn] = '+';
                            } else {
                                currentState[playerRow-1][playerColumn] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow-2][playerColumn] = '*';
                            if (neighbor=='*') {
                                currentState[playerRow-1][playerColumn] = '+';
                            } else {
                                currentState[playerRow-1][playerColumn] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves and pushes a box up
                    
                    playerRow -= 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.UP);
                    nextNode.setState(currentState);
                    nextNode.setPush();
                    return nextNode;
                // if above to the player is empty space
                } else if(neighbor == ' ' || neighbor == '.'){
                    switch(neighbor){
                        case ' ':
                            currentState[playerRow-1][playerColumn] = '@';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow-1][playerColumn] = '+';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves up
                    playerRow -= 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.UP);
                    nextNode.setState(currentState);
                    return nextNode;
                }
            }
        } catch (Exception e) {}
        return null;
    }//end moveUp
    
    public Node moveDown(Node node){
        char[][] currentState = node.getState();
        findPlayerCoordinates(currentState);
        
        char neighbor = currentState[playerRow+1][playerColumn];
        try{
            char farNeighbor = currentState[playerRow+2][playerColumn];

            // if there's no wall beneath the player
            if (neighbor!='#') {
                //if beneath the player is a box but can't be pushed
                if (neighbor=='$' && farNeighbor=='$' ||
                    neighbor=='$' && farNeighbor=='#' ||
                    neighbor=='$' && farNeighbor=='*') {
                    return null;
                // if beneath the player is a box and can be moved
                } else if(neighbor=='$' || neighbor=='*') {
                    switch(farNeighbor){
                        case ' ':
                            currentState[playerRow+2][playerColumn] = '$';
                            if (neighbor=='*') {
                                currentState[playerRow+1][playerColumn] = '+';
                            } else {
                                currentState[playerRow+1][playerColumn] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow+2][playerColumn] = '*';
                            if (neighbor=='*') {
                                currentState[playerRow+1][playerColumn] = '+';
                            } else {
                                currentState[playerRow+1][playerColumn] = '@';
                            }
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves and pushes a box down
                    
                    playerRow += 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.DOWN);
                    nextNode.setState(currentState);
                    nextNode.setPush();
                    return nextNode;
                // if beneath the player is empty space
                } else if(neighbor == ' ' || neighbor == '.'){
                    switch(neighbor){
                        case ' ':
                            currentState[playerRow+1][playerColumn] = '@';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                        case '.':
                            currentState[playerRow+1][playerColumn] = '+';
                            currentState[playerRow][playerColumn] = beneathPlayer(currentState[playerRow][playerColumn]);
                            break;
                    }
                    // the player moves down
                    playerRow += 1;
                    Node nextNode = new Node();
                    nextNode.setAction(Actions.DOWN);
                    nextNode.setState(currentState);
                    return nextNode;
                }
            }
        } catch (Exception e) {}
        return null;
    }//end moveDown

    public char[][] getInitialState() {
        return initialState;
    }//end getInitialState
    
    public char[][] getGoalState(){
        return goalState;
    }//end getGoalState
    
    public String getInitialStateRep(){
        StringBuilder value = new StringBuilder();
        for (char[] row : initialState) {
            for (char cell : row) {
                value.append(cell);
            }
            value.append('\n');
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }//end getInitialStateRep
    
    public int getSize(){
        return this.size;
    }//end getSize
    
    public int getNumberOfPushes(){
        return this.pushes;
    }//end getNumberOfPushes
}//end class
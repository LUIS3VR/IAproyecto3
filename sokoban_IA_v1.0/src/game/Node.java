package game;

/**
 *
 * @author eduardo
 */
public class Node implements Comparable<Node>{

    private char[][] state;
    private Node parent;
    private Actions action;
    private int depth;
    private Integer heuristic;
    private int push;

    public int getPush(){
        return push;
    }//end getPush
    
    public void setPush(){
       this.push = 1; 
    }//end setPush
    
    public int getDepth() {
        return depth;
    }//end getDepth

    public void setDepth(int depth) {
        this.depth = depth;
    }//end setDepth
    
    public void setState(char[][] state) {
        this.state = state;
    }//end setState

    public void setAction(Actions action) {
        this.action = action;
    }//end setAction

    public void setParent(Node parent) {
        this.parent = parent;
    }//end setParent
    
    public Node getParent() {
        return parent;
    }//end getParent

    public Actions getAction() {
        return action;
    }//end getAction

    public char[][] getState() {
        int size = this.state[0].length;
        char[][] returnState = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                returnState[i][j] = this.state[i][j];
            }
        }
        return returnState;
    }//end getState
    
    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (char[] row : state) {
            for (char cell : row) {
                value.append(cell);
            }
        }
        return value.toString();
    }//end toString

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            for (int i = 0; i < state.length; i++) {
                for (int j = 0; j < state.length; j++) {
                    if (state[i][j] != ((Node) obj).state[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }//end equals

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }//end hashCode

    @Override
    public int compareTo(Node o) {
        if (this.heuristic < o.heuristic) {
            return -1;
        } else if (this.heuristic > o.heuristic){
            return 1;
        }
        return 0;
    }//end compareTo

    public Integer getHeuristic() {
        return heuristic;
    }//end getgFunction

    public void setHeuristic(Integer heuristic) {
        this.heuristic = this.depth + heuristic;
    }//end setgFunction
    
    public String getStateRep(){
        StringBuilder value = new StringBuilder();
        for (char[] row : state) {
            for (char cell : row) {
                value.append(cell);
            }
            value.append('\n');
        }
        value.deleteCharAt(value.length()-1);
        return value.toString();
    }//end getStateRep
    
}//end class
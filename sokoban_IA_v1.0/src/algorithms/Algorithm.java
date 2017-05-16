package algorithms;

import java.util.HashSet;
import java.util.Stack;
import game.Node;
import game.Problem;

/**
 *
 * @author eduardo
 */
public abstract class Algorithm {
    
    protected HashSet explored;
    protected Stack path;
    protected Stack instructionSet;
    protected Stack states;
    protected Stack transitionsSet;
    protected Problem problem;
    protected int numberOfMoves;
    protected int pushes;
    protected int solutionNodeDepth;
    protected long timeElapsed;
    protected long memoryUsed;
    protected long memoryTotal;
    
    protected void feedPath(Node node){
        path.add(node.getAction());
        states.add(node.getStateRep());
        if (node.getParent()!=null) {
            feedPath(node.getParent());
        }
    }//end feedPath
    
    public Stack getSolution(){
        try {
            this.path.pop();
            while (!path.isEmpty()) {            
                instructionSet.add(path.pop());
            }
        } catch (Exception e) {
        }
        return this.instructionSet;
    }//end getSolution
    
    public Stack getTransitions(){
        try {
            this.states.pop();
            while (!states.isEmpty()) {                
                transitionsSet.add(states.pop());
            }
        }catch (Exception e) {
        }
        return this.transitionsSet;
    }//end getTransitions
    
    public int getNumberOfMoves(){
        return this.numberOfMoves;
    }//end getNumberOfMoves
    
    public void calculatePushes(Node node){
        pushes += node.getPush();
        if (node.getParent()!=null) {
            calculatePushes(node.getParent());
        }
    }//end getNumberOfPushes

    public int getNumberOfPushes(){
        return this.pushes;
    }//end getNumberOfPushes
    
    public int getSolutionNodeDepth(){
        return this.solutionNodeDepth;
    }//end getSolutionNodeDepth

    public long getTimeElapsed() {
        return timeElapsed;
    }//end getTimeElapsed

    public long getMemoryUsed() {
        return memoryUsed;
    }//end getMemoryUsed
    
    public int getExplorados(){
        return this.explored.size();
    }//end getExplorados

    public long getMemoryTotal() {
        return memoryTotal;
    }//end getMemoryTotal
    
}//end class
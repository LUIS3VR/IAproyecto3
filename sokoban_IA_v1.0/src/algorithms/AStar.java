package algorithms;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import game.Node;
import game.Problem;
import java.util.ArrayList;

/**
 *
 * @author eduardo
 */
public class AStar extends Algorithm{
    
    private final PriorityQueue<Node> frontier;
    
    public AStar(Problem problem){
        frontier = new PriorityQueue();
        explored = new HashSet();
        path = new Stack();
        instructionSet = new Stack();
        states = new Stack();
        transitionsSet = new Stack();
        this.problem = problem;
    }//end constructor
    
    public void search(){
        
        long startTime = System.currentTimeMillis();

        Node node = new Node();
        node.setState(problem.getInitialState());
        node.setDepth(0);
        node.setHeuristic(calculeHeuristic(node));
        
        Node nextNode;
        frontier.add(node);
        
        
        while (!frontier.isEmpty()) {

            node = frontier.poll();
            explored.add(node);
            
            if (problem.goalTest(node.getState())) {
                feedPath(node);
                calculatePushes(node);
                solutionNodeDepth = node.getDepth();
                numberOfMoves = path.size()-1;
                System.out.println("SOLUTION FOUND");
                break;
            }
            
            nextNode = problem.moveLeft(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeHeuristic(nextNode));
                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveUp(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeHeuristic(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveRight(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeHeuristic(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
            
            nextNode = problem.moveDown(node);
            if (nextNode!=null && !explored.contains(nextNode)) {
                nextNode.setParent(node);
                nextNode.setDepth(node.getDepth()+1);
                nextNode.setHeuristic(calculeHeuristic(nextNode));

                frontier.add(nextNode);
                explored.add(nextNode);
            }
        }
        if (this.frontier.isEmpty()) {
            System.out.println("SOLUTION NOT FOUND");
        }
        timeElapsed = System.currentTimeMillis() - startTime;
        memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    }//end searchWithManhattanDistance

    public int calculeHeuristic(Node node){
        int heuristic = 0;
        char[][] state = node.getState();
        int[] player = problem.findPlayerCoordinates(state);
        ArrayList<int[]> boxes = problem.findBoxesCoordinares(state);
        ArrayList<int[]> targets = problem.findTargetsCoordinares(state);
        
        //Calcular la distancia minima del agente a las cajas y agregarla a la suma
        double distanceToBoxes = getDist(player, boxes);
        heuristic += distanceToBoxes;
        
        //Calcular la distancia de las cajas a los objetivos y agregarla a la suma
        for(int[] box : boxes){
            double distanceToTargets = getDist(box, targets);
            heuristic += distanceToTargets;
        }
        
        return heuristic;
    }//end calculeManhattanDistance
    
    private static double getDist(int[] obj, ArrayList<int[]> conjunto) {
        double minDist = 1000000;

        //Para cada coordenada calcular la distancia con el método euclidiano
        for (int[] c : conjunto) {
            double dist= Math.sqrt((double) ((obj[0] - c[0]) * (obj[0]  - c[0]) + (obj[1]  - c[1]) * (obj[1]  - c[1])));
            if (dist < minDist) {
                minDist = dist;
            }
        }
        return minDist;
    }
    
    public int getFrontierSize(){
        return this.frontier.size();
    }//end getFrontierSize
    
}//end class AStar
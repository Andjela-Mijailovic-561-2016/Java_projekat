import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithm
{
    private final List<Path> edges;
    private List<Path> shortestPath=new ArrayList<Path>();
    private Set<Integer> settledNodes;
    private Set<Integer> unSettledNodes;
    private Map<Integer, Pair> predecessors;
    private Map<Integer, Integer> distance;
    
    public DijkstraAlgorithm(List<Path> graph)
    {
        this.edges = graph;
    }

    
    public void execute(int source) {
        settledNodes = new HashSet<Integer>();
        unSettledNodes = new HashSet<Integer>();
        distance = new HashMap<Integer, Integer>();
        predecessors = new HashMap<Integer, Pair>();
        
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            int node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(int node) {
        List<Integer> adjacentNodes = getNeighbors(node);
        
      if(adjacentNodes.size()>0) {  for (int target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)+ getDistance(node, target));
                predecessors.put(target,new Pair(node, getShortestDistance(node)+ getDistance(node, target)));
                unSettledNodes.add(target);
            }
        }}

    }

    private int getDistance(int node, int target) {
        for (Path edge : edges) {
            if (edge.getSource()==node && edge.getDestination()==target)
            {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Integer> getNeighbors(int node) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (Path edge : edges) {
            if (edge.getSource()==node && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private int getMinimum(Set<Integer> vertexes) {
        int minimum = Integer.MAX_VALUE;
        for (int vertex : vertexes) {
            if (minimum == Integer.MAX_VALUE) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
        
    }

    private boolean isSettled(int vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(int destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

 
    public List<Path> getPath(int target,int start) {
    	if(predecessors.get(target)== null) {
    		 shortestPath.add(new Path(start, start, 0));
    	    return shortestPath;}
        Pair pair = (Pair) predecessors.get(target);
        
        shortestPath.add(new Path(pair.getValue(), target, pair.getSource()));
        
        Integer c = target;
       
        while ( c != start)
        {
            c = pair.getValue();
            
            if(c==start)break;
            pair = (Pair) predecessors.get(c);
            shortestPath.add(new Path(pair.getValue(), c, pair.getSource()));
            
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
    }
        
       


package DSA.Graphs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;


enum TraversalType {
    BFS,
    DFS;
}

@Getter
@Setter
@Slf4j
public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(String label){
        adjVertices.put(new Vertex(label), new ArrayList<>());
        log.info("Added vertex: {}", label);
    }

    public void removeVertex(String label){
        //first remove the vertex from the values
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(vertices -> vertices.remove(v));
        //now remove the vertex from the key
        adjVertices.remove(v);
        log.info("Removed Vertex :{}", v.label);
    }

    public void addEdge(String label1, String label2, boolean dir){
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        if(!dir)
            adjVertices.get(v2).add(v1);
        log.info("Added edge: {} {}. Directed :{}", v1.label, v2.label, dir);
    }

    public void addEdge(String label1, String label2){
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
        log.info("Added edge: {} {}.UNDirected", v1.label, v2.label);
    }

    public void removeEdge(String label1, String label2){
        Vertex v1 = new Vertex( label1);
        Vertex v2 = new Vertex( label2);
        List<Vertex> ev1 = adjVertices.get(v1);
        List<Vertex> ev2 = adjVertices.get(v2);
        if(ev1 != null)
            ev1.remove(v2);
        if(ev2 != null)
            ev2.remove(v1);
        log.info("Removed Edge : {} {}", v1.label, v2.label);
    }

    public List<Vertex> getNeighbours(String label){
        return adjVertices.get(new Vertex(label));
    }

    public  void  printGraphInList() {
        adjVertices.keySet().stream().forEach(vertex -> {
            log.info("Node : {} -> {}", vertex.label, adjVertices.get(vertex)
                    .stream().map(ver -> ver.label).collect(Collectors.joining(", ")));
        });
    }

    public Set<Vertex> bfs(String root){
        Vertex rt = new Vertex(root);
        Set<Vertex> visited = new LinkedHashSet<>();
        Map<Vertex, Integer> distance = new HashMap<>();
        distance.put(rt, 0);
        Map<Vertex, Vertex> parent = new HashMap<>();
        parent.put(rt,rt);
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(rt);
        visited.add(rt);

        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            for(Vertex vi: getNeighbours(v.label)){
                if(!visited.contains(vi)){
                    //parent and distance
                    parent.put(vi, v);
                    distance.put(vi, distance.get(v)+1);//distance of vertex = distance of vertex + parent
                    visited.add(vi);
                    queue.add(vi);
                }
            }
        }
        distance.keySet().stream().forEach(vertex -> {
            log.info("Single Source Shortest Distance For Undirected Graph From :{} To :{} -> {}", root, vertex.label, distance.get(vertex));
        });

        for(Vertex vertex : visited){
            String des = vertex.label;
            List<String> path = new ArrayList<>();
            while(vertex.label != root){
                path.add(vertex.label);
                vertex = parent.get(vertex);
            }
            path.add(vertex.label);
            log.info("Single Source Shortest Path For Undirected Graph From :{} To :{} -> {}", root, des, path);
        }

        return visited;
    }


    public int getBfsSingleShortestDistance(String root, String target){
        Vertex vt = new Vertex(root);
        Vertex tt = new Vertex(target);
        Set<Vertex> visited = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Integer> distance = new HashMap<>();
        distance.put(vt, 0);
        queue.add(vt);
        visited.add(vt);
        while(!queue.isEmpty()){
            Vertex v =  queue.poll();
            for(Vertex vi : getNeighbours(v.label)){
                if (!visited.contains(vi)){
                    visited.add(vi);
                    distance.put(vi, distance.get(v)+1);
                    if(!vi.label.equals(target))
                        queue.add(vi);
                    else
                        queue.clear();
                }
            }
        }
        return distance.get(tt)!=null ? distance.get(tt) : -1;
    }


    public List<String> getBfsSingleShortestPath(String root, String target){
        Vertex rt = new Vertex(root);
        Set<Vertex> visited = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> parent = new HashMap<>();
        parent.put(rt,rt);
        visited.add(rt);
        queue.add(rt);
        while (!queue.isEmpty()){
            Vertex v = queue.poll();
            for(Vertex vi: adjVertices.get(v)){
                if(!visited.contains(vi)){
                    visited.add(vi);
                    parent.put(vi, v);
                    if(vi.label.equals(target))
                        queue.clear();
                    else
                        queue.add(vi);
                }
            }
        }
        Vertex vertex = new Vertex(target);
            List<String> path = new ArrayList<>();
            while(vertex.label != root){
                path.add(vertex.label);
                vertex = parent.get(vertex);
            }
            path.add(vertex.label);
            return path;
    }

    public boolean ifDirectPathExists(String src, String target){
        boolean res = bfs(src).stream().anyMatch(vertex -> vertex.label.equals(target));
        return res;
    }

    public Set<Vertex> dfs(String root) {
        Vertex rt = new Vertex(root);
        Set<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(rt);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : getAdjVertices().get(vertex)) {
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public void graphTraversal(String root, TraversalType traversalType){
        if(traversalType.equals(TraversalType.BFS))
            log.info("{} Traversal  From {} : {}",traversalType,root,   bfs(root)
                    .stream().map(ver -> ver.label).collect(Collectors.joining(", ")));
        else
            log.info("{} Traversal From {} : {}", traversalType,root,  dfs(root)
                    .stream().map(ver -> ver.label).collect(Collectors.joining(", ")));
    }
}

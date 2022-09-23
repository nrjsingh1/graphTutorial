package DSA.Graphs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.Collectors;


@Slf4j
@SpringBootTest
public class GraphGenTest {

    @Autowired
    private GraphGen graph;

    @Test
    public void printGraph(){
        int n=7;
        int[][] edges = {{0,1},{1,2},{2,3},{3,5},{5,6},{4,5},{0,4},{3,4}};

        graph = new GraphGen<>();
        for(int i=0; i<n ;i++)
            graph.addVertex(i);

        for(int [] ed : edges)
            graph.addEdge(ed[0], ed[1],false);

        graph.printGraphInList();
        log.info("DFS Visited {} ", graph.dfs(4));
    }
}
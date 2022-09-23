package DSA.Graphs.Problems;

import java.util.*;

public class ReconstructItinerary {
    static HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    static Stack<String> st = new Stack<>();

    public static void main(String[] args) {
        String src = "JFK";
        List<List<String>> tickets = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        //List.of(List.of("JFK","SFO"),List.of("JFK","ATL"),List.of("SFO","ATL"),List.of("ATL","JFK"),List.of("ATL","SFO"));
        List<String> res = new ArrayList<>();
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0), new PriorityQueue<>());
            }
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs(src);
        while (!st.isEmpty()) {
            res.add(st.pop());
        }
        System.out.println(res);
    }

    public static void dfs(String src) {
        PriorityQueue<String> pq = map.get(src);
        while (pq!=null && pq.size()>0) {
            String temp = pq.poll();
            dfs(temp);
        }
        st.push(src);
        return;
    }
}

package DSA.Graphs.Problems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KeysAndRooms {
    public static void main(String[] args) {
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n_rooms = rooms.size();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(0);
        visited.add(0);
        while(!stack.isEmpty()){
            int cur_room = stack.pop();
            List<Integer> r_keys = rooms.get(cur_room);
            for(int room : r_keys){
                if(!visited.contains(room)){
                    stack.push(room);
                    visited.add(room);
                }
            }
        }
        boolean res = true;
        for(int i=0; i<n_rooms; i++)
            res = res && visited.contains(i);
        return res;
    }
}

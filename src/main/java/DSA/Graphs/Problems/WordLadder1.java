package DSA.Graphs.Problems;

import java.util.*;

public class WordLadder1 {

    public static void main(String[] args) {
        String beginWord = "hog", endWord = "cog";

        List<String> wordList = List.of("cog");
        System.out.println(findSteps2(beginWord, endWord, wordList).split(" ").length);
        System.out.println(findSteps2(beginWord, endWord, wordList));
        for(int i =0;i<beginWord.length(); i++)
            System.out.println((int) beginWord.charAt(i));

        int bSum =0;
        for(int i =0;i<beginWord.length(); i++)
            bSum = bSum +  (int) beginWord.charAt(i);
        System.out.println(bSum);

        for(int i =0;i<endWord.length(); i++)
            System.out.println((int) endWord.charAt(i));

        int eSum =0;
        for(int i =0;i<endWord.length(); i++)
            eSum = eSum +  (int) endWord.charAt(i);
        System.out.println(eSum);
    }
    /*
    logic
    start node = bgword
    take words from list with single letter diff as neighbours
    put neighbours of start in queue
    poll queue and repeat
     keep counting the steps
    return steps
    */
    public static int findSteps(String bWord, String eWord, List<String> wList){
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(bWord);
        visited.add(bWord);
        for(int move =0; !queue.isEmpty(); move++){
            for(int i = 0; i<queue.size(); i++){
                String cur = queue.poll();
                if(!visited.contains(cur))
                    visited.add(cur);
                if(cur.equals(eWord))
                    return move+1;
                List<String> cur_nbrs = getNeighbours(cur, wList, visited);
                for(String cur_nbr : cur_nbrs){
                    if(!visited.contains(cur_nbr)){
                        queue.add(cur_nbr);
                    }
                }
            }
        }
        return 0;
    }

    public  static String findSteps2(String bWord, String eWord, List<String> wList){
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> path = new HashMap<>();
        queue.add(bWord);
        visited.add(bWord);
        path.put(bWord,bWord);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for(String str : getNeighbours(cur, wList, visited)){
                if(!visited.contains(str)){
                    queue.add(str);
                    visited.add(str);
                    path.put(str, path.get(cur)+" "+str);
                }
                if(str.equals(eWord))
                    return path.get(eWord);
            }
        }
        return "";
    }

    public static List<String> getNeighbours(String cur, List<String> wList, Set<String> visited){
        List<String> res = new ArrayList<>();
        char[] cur_c = cur.toCharArray();
        for(String str : wList){
            if(visited.contains(str))
                continue;
            int diffCount = 0;
            char[] str_c = str.toCharArray();
            for(int i =0;i<str_c.length; i++)
                if(str_c[i]!=cur_c[i])
                    diffCount++;
            if(diffCount==1)
                res.add(str);
        }
        return res;
    }
}

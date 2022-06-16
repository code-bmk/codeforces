/*

https://codeforces.com/contest/580/problem/C

*/

import java.io.*;
import java.util.*;
public class CFTemplate
{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer tokenizer=null;

    public static void main(String[] args) throws IOException
    {
        new CFTemplate().execute();
    }

    void debug(Object...os)
    {
        System.out.println(Arrays.deepToString(os));
    }

    int ni() throws IOException
    {
        return Integer.parseInt(ns());
    }

    long nl() throws IOException
    {
        return Long.parseLong(ns());
    }

    double nd() throws IOException
    {
        return Double.parseDouble(ns());
    }

    String ns() throws IOException
    {
        while (tokenizer == null || !tokenizer.hasMoreTokens())
            tokenizer = new StringTokenizer(br.readLine());
        return tokenizer.nextToken();
    }

    String nline() throws IOException
    {
        tokenizer=null;
        return br.readLine();
    }


    //Main Code starts Here
    int totalCases, testnum;

    int result;
    Set<Integer> visited;
    void execute() throws IOException
    {
        result = 0;
        visited = new HashSet<>();
        int n = ni();
        int m = ni();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = ni();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<n-1; i++){
            int parent = ni();
            int child = ni();
            map.computeIfAbsent(parent, k -> new ArrayList<>());
            map.computeIfAbsent(child, k -> new ArrayList<>());
            map.get(parent).add(child);
            map.get(child).add(parent);
        }
        System.out.println(solve(m, arr, map));
    }

    int solve(int m, int[] arr, Map<Integer, List<Integer>> map) throws IOException
    {
        dfs(1, map, 0, 0, arr, m);
        return result;
    }

    public void dfs(int node, Map<Integer, List<Integer>> map, int count, int max, int[] arr, int m){


        if(arr[node-1] == 1){
            count = count+1;
            if(count > max) max = count;
        } else {
            count = 0;
        }
        if(map.get(node).size() == 1 && visited.contains(map.get(node).get(0))){

            if(max <= m) result++;
            return;
        }
        for(int child : map.get(node)){
            if(!visited.contains(child)){
                visited.add(node);
                dfs(child, map, count, max, arr, m);
                visited.remove(node);
            }
        }

    }

}
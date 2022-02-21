import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Main{
    static int n, m, v;
    static Graph[] g;
    static StringBuilder sb;
    static boolean isvisit[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        g = new Graph[n+1];
        sb = new StringBuilder();
        isvisit = new boolean[n+1];

        for (int i = 0; i<=n; i++){
            g[i] = new Graph(i);
        }

        for (int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            g[n1].addGraph(g[n2]);
            g[n2].addGraph(g[n1]);
        }

        for (int i = 1; i<=n; i++){
            Collections.sort(g[i].subNode, (o1, o2) -> o1.num - o2.num);
        }

        DFS(v);
        sb.append("\n");
        BFS(v);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int idx){
        sb.append(idx + " ");
        isvisit[idx] = true;
        for (int i = 0; i<g[idx].subNode.size(); i++){
            if (isvisit[g[idx].subNode.get(i).num]) continue;
            DFS(g[idx].subNode.get(i).num);
        }
    }

    public static void BFS(int idx){
        Queue<Graph> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        q.add(g[idx]);
        visited[idx] = true;

        while (!q.isEmpty()){
            Graph graph = q.poll();
            sb.append(graph.num + " ");
            for (int i = 0; i<graph.subNode.size(); i++){
                if (visited[graph.subNode.get(i).num]) continue;
                visited[graph.subNode.get(i).num] = true;
                q.add(graph.subNode.get(i));
            }
        }
    }

    public static class Graph{
        int num;
        List<Graph> subNode;
        public Graph(int num){
            this.num = num;
            subNode = new ArrayList<>();
        }
        public void addGraph(Graph g){
            subNode.add(g);
        }
    }
}
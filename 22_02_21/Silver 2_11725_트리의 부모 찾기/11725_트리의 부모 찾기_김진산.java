import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    static Node[] nodes;
    static int n, parents[];
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); // 노드 수
        nodes = new Node[n + 1]; // 노드 들
        visited = new boolean[n+1]; // BFS 사용 방문 확인
        parents = new int[n+1]; // 각 인덱스의 부모를 나타내는 배열

        //노드 생성
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        //간선 입력력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            nodes[n1].addNode(nodes[n2]);
            nodes[n2].addNode(nodes[n1]);
        }
        //루트에서 부모 찾기
        nodes[1].search();
        //출력
        for (int i = 2; i<=n; i++){
            bw.write(parents[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        int num;
        List<Node> subNodes;

        public Node(int num) {
            this.num = num;
            subNodes = new ArrayList<>();
        }

        public void search() {
            //루트에서 모두 탐색 후 parent에 값 저장
            Queue<Node> queue = new LinkedList<>();
            visited[this.num] = true;
            queue.offer(nodes[this.num]);

            while (!queue.isEmpty()){
                Node node = queue.poll();
                for (int i = 0; i<node.subNodes.size(); i++){
                    int n = node.subNodes.get(i).num;
                    if (visited[n]) continue;
                    parents[n] = node.num;
                    visited[n] = true;
                    queue.add(node.subNodes.get(i));
                }
            }
        }

        public void addNode(Node node) {
            subNodes.add(node);
        }
    }
}
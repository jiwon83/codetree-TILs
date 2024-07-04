public class Main {
    static int MAX_N = 19;
    static int N;
    static boolean [] visit = new boolean[MAX_N+1]; 
    public static void main(String[] args) {
        N = 3;
        int totalCnt = 0;
        for(int node =1 ; node <=N; node++){
            visit[node] = true;
            totalCnt += recurDownTreeCnt(node, 1, N, node + " -> ");
            visit[node] = false;
        }
       
        System.out.println(totalCnt);
        
    }
    public static int recurDownTreeCnt(int curr, int start, int end, String route){
        // System.out.println(route);
        int left = 0;
        int right = 0;
        //left child
        for(int node = start; node < curr; node++){
            if(visit[node]) continue;
            visit[node] = true;
            left += recurDownTreeCnt(node, start, curr-1, route + node + " -> ");
            visit[node] = false;
        }
        // right child
        for(int node = curr+1; node <= end; node++){
            if(visit[node]) continue;
            visit[node] = true;
            right += recurDownTreeCnt(node, curr+1, end, route + node + " -> ");
            visit[node] = false;           
        }

        
        if(left == 0 && right == 0) return 1;
        return Math.max(1, left) * Math.max(1, right);

        

    }
}
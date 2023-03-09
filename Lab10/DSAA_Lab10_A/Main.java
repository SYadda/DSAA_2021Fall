import java.io.*;
import java.util.*;

class node {
    int value;
    ArrayList<Integer> son = new ArrayList<>();
    ArrayList<Integer> sonLength = new ArrayList<>();
    public node(int i){
        value = i;
    }
}

public class Main {
    public static int currMinNode, n, n1;
    public static long currMinLength;
    public static node[] allNode;
    public static long[] allMinLength;
    public static boolean[] isVisited;
    public static void main(String[] args) {
        QReader in = new QReader();
        n = in.nextInt();
        int m = in.nextInt();
        n1 = n;
        allNode = new node[n+1];
        allMinLength = new long[n+1];
        isVisited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            allNode[i] = new node(i);
            allMinLength[i] = 9223372036854775807L;
            isVisited[i] = false;
        }
        allMinLength[1] = 0;

        while (m-- > 0){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            allNode[u].son.add(v);
            allNode[u].sonLength.add(w);
        }

        go(1);
    }

    public static void go(int x){
        isVisited[x] = true;
        int sonSize = allNode[x].son.size();
        for (int i = 0; i < sonSize; i++) {
            if (  allMinLength[ allNode[x].son.get(i) ] > allMinLength[x] + allNode[x].sonLength.get(i)  ){
                allMinLength[ allNode[x].son.get(i) ] = allMinLength[x] + allNode[x].sonLength.get(i);
            }
        }

        currMinLength = 9223372036854775807L;
        for (int i = 1; i <= n; i++) {
            if ( ! isVisited[i] && currMinLength > allMinLength[i]){
                currMinLength = allMinLength[i];
                currMinNode = i;
            }
        }
        if (currMinNode == n){
            System.out.println(currMinLength);
        } else if (n1-- > 0){
            go(currMinNode);
        } else {
            System.out.println(-1);
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
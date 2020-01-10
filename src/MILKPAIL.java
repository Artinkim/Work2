import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MILKPAIL {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] can = new boolean[x+1][y+1];
		can[0][0] = true;
		for(int operationNum = 0; operationNum < k; operationNum++) {
			// if can[A][B] is true, then after at most operationNum operations,
			// it is possible to end with A units of milk in the size X bucket
			// and B units of milk in the size Y bucket.
			boolean[][] next = new boolean[x+1][y+1];
			for(int i = 0; i < can.length; i++) {
				for(int j = 0; j < can[i].length; j++) {
					if(!can[i][j]) continue;
					// we can always maintain the same state
					next[i][j] = true;
					// empty size X bucket
					next[0][j] = true;
					// fill size X bucket
					next[x][j] = true;
					// empty size Y bucket
					next[i][0] = true;
					// fill size Y bucket
					next[i][y] = true;
					// pour from size X bucket to size Y bucket
					int moveRight = Math.min(i, y - j);
					next[i-moveRight][j+moveRight] = true;
					// pour from size Y bucket to size X bucket
					int moveLeft = Math.min(x - i, j);
					next[i+moveLeft][j-moveLeft] = true;
				}
			}
			can = next;
		}
		int ret = Integer.MAX_VALUE;
		for(int i = 0; i < can.length; i++) {
			for(int j = 0; j < can[i].length; j++) {
				if(!can[i][j]) continue;
				ret = Math.min(ret, Math.abs(i+j-m));
			}
		}
		pw.println(ret);
		pw.close();
	}
}

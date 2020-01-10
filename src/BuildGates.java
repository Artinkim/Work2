import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair6 {
	int x;
	int y;

	Pair6(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BuildGates {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String[] str = br.readLine().split("");
		boolean[][] grid = new boolean[2 * N][2 * N];
		boolean[][] visited = new boolean[2 * N][2 * N];
		Queue<Pair6> queue = new LinkedList<Pair6>();
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		int x = N, y = N;
		grid[x][y] = true;
		for (int i = 0; i < N; i++) {
			if (str[i].equals("N")) {
				y++;
				grid[x][y] = true;
				y++;
				grid[x][y] = true;
				continue;
			}
			if (str[i].equals("E")) {
				x++;
				grid[x][y] = true;
				x++;
				grid[x][y] = true;
				continue;
			}
			if (str[i].equals("S")) {
				y--;
				grid[x][y] = true;
				y--;
				grid[x][y] = true;
				continue;
			}
			if (str[i].equals("W")) {
				x--;
				grid[x][y] = true;
				x--;
				grid[x][y] = true;
			}
		}
		int count = 0;
		Pair6 current;   
		int c = 0;
		for (int i = 0; i < N*2; i++) {
			for (int j = 0; j < N*2; j++) {
				if (grid[i][j])
					continue;
				if (visited[i][j])
					continue;
				count++;
				queue.add(new Pair6(i, j));
				visited[i][j] = true;
				while (!queue.isEmpty()) {
					current = queue.poll();
					for (int j2 = 0; j2 < 4; j2++) {
						if(current.x + dx[j2] > 2*N-1 || current.x + dx[j2] < 0)
							continue;
						if(current.y + dy[j2] > 2*N-1 || current.y + dy[j2] < 0)
							continue;
						if (grid[current.x + dx[j2]][current.y + dy[j2]])
							continue;
						if (visited[current.x + dx[j2]][current.y + dy[j2]])
							continue;
						queue.add(new Pair6(current.x + dx[j2],current.y + dy[j2]));
						visited[current.x + dx[j2]][current.y + dy[j2]] = true;
					}
				}
			}
		}
		pw.println(count-1);
		pw.close();
	}
}

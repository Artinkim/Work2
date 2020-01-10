import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class point3 {
	boolean cow, up, down, left, right;

	point3(boolean cow, boolean up, boolean down, boolean left, boolean right) {
		this.cow = cow;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

}

class point2 {
	int x;
	int y;

	point2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "(" +this.x + " " + this.y + ")";
	}
}

public class CrossTheRoad3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		point3[][] grid = new point3[N][N];
		point2[] cows = new point2[K];
		boolean[][] visited = new boolean[N][N];
		Queue<point2> queue = new LinkedList<point2>();
		int x1, y1, x2, y2;
		for (int n = 0; n < N; n++)
		{
			for (int n2 = 0; n2 < N; n2++)
				grid[n][n2] = new point3(false, false, false, false, false);
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			if (x1 == x2) {
				if (y1 > y2) {
					grid[x1][y1].down = true;
					grid[x2][y2].up = true;
					continue;
				}
				grid[x1][y1].up = true;
				grid[x2][y2].down = true;
			}
			if (y1 == y2) {
				if (x1 > x2) {
					grid[x1][y1].left = true;
					grid[x2][y2].right = true;
					continue;
				}
				grid[x1][y1].right = true;
				grid[x2][y2].left = true;
			}
		}
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
				System.out.print(grid[i][j].cow + " ");
			System.out.println();
		}
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			grid[x][y].cow = true;
			cows[i] = new point2(x, y);
			System.out.println(cows[i]);
		}
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
				System.out.print(grid[i][j].cow + " ");
			System.out.println();
		}
		point2 currentPoint;
		int totalCount = 0;
		int count = 0;
		for (int i = 0; i < K; i++) {
			queue.add(cows[i]);
			System.out.println();
			for (int n = 0; n < N; n++) {
				Arrays.fill(visited[n], false);
			}
			visited[cows[i].x][cows[i].y] = true;
			while (!queue.isEmpty()) {
				currentPoint = queue.poll();
				System.out.println(currentPoint);
				if (grid[currentPoint.x][currentPoint.y].cow) {
					System.out.println("cow");
					count++;
				}
				if (currentPoint.x < N - 1) {
					if (!visited[currentPoint.x + 1][currentPoint.y]) {
						if (!grid[currentPoint.x + 1][currentPoint.y].left) {
							visited[currentPoint.x + 1][currentPoint.y] = true;
							queue.add(new point2(currentPoint.x + 1, currentPoint.y));
						}
					}
				}
				if (currentPoint.y < N - 1) {
					if (!visited[currentPoint.x][currentPoint.y + 1]) {
						if (!grid[currentPoint.x][currentPoint.y + 1].down) {
							visited[currentPoint.x][currentPoint.y + 1] = true;
							queue.add(new point2(currentPoint.x, currentPoint.y + 1));
						}
					}
				}
				if (currentPoint.x != 0) {
					if (!visited[currentPoint.x - 1][currentPoint.y]) {
						if (!grid[currentPoint.x - 1][currentPoint.y].right) {
							visited[currentPoint.x - 1][currentPoint.y] = true;
							queue.add(new point2(currentPoint.x - 1, currentPoint.y));
						}
					}
				}
				if (currentPoint.y != 0) {
					if (!visited[currentPoint.x][currentPoint.y - 1]) {
						if (!grid[currentPoint.x][currentPoint.y - 1].up) {
							visited[currentPoint.x][currentPoint.y - 1] = true;
							queue.add(new point2(currentPoint.x, currentPoint.y - 1));
						}
					}
				}
			}
			System.out.println(K);
			System.out.println(count-1);
			totalCount += K - (count);
			count = 0;
			System.out.println(totalCount);
		}
		
		FileWriter fw = new FileWriter("countcross.out");
		fw.write(totalCount/2 + "");
		fw.close();
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class point {
	int x;
	int y;

	point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class multimoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		boolean[][] visited2 = new boolean[N][N];
		Queue<point> mainQueue = new LinkedList<point>();
		Queue<point> firstQueue = new LinkedList<point>();
		Queue<point> secondQueue = new LinkedList<point>();
		Queue<point> thirdQueue = new LinkedList<point>();
		ArrayList<Integer> surroundingColors = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				mainQueue.add(new point(i, j));
			}
		}
		int singleHighScore = 0;
		int teamHighScore = 0;
		int singleTempHighScore = 0;
		int teamTempHighScore = 0;
		int color = -1;
		while (!mainQueue.isEmpty()) {
			 singleTempHighScore = 0;
			 teamTempHighScore = 0;
			firstQueue.add(mainQueue.poll());
			while (!firstQueue.isEmpty()) {
				point currentPoint = firstQueue.poll();
				color = arr[currentPoint.x][currentPoint.y];
				singleTempHighScore++;
				if (currentPoint.x != 0) {
					if (!visited[currentPoint.x - 1][currentPoint.y]) {
						if (arr[currentPoint.x - 1][currentPoint.y] == color) {
							firstQueue.add(new point(currentPoint.x - 1, currentPoint.y));
							visited[currentPoint.x - 1][currentPoint.y] = true;
						} else {
							secondQueue.add(new point(currentPoint.x - 1, currentPoint.y));
							if (!surroundingColors.contains(arr[currentPoint.x - 1][currentPoint.y]))
								surroundingColors.add(arr[currentPoint.x - 1][currentPoint.y]);
						}
					}
				}
				if (currentPoint.y != 0) {
					if (!visited[currentPoint.x][currentPoint.y - 1]) {
						if (arr[currentPoint.x][currentPoint.y - 1] == color) {
							firstQueue.add(new point(currentPoint.x, currentPoint.y - 1));
							visited[currentPoint.x][currentPoint.y - 1] = true;
						} else {
							secondQueue.add(new point(currentPoint.x, currentPoint.y - 1));
							if (!surroundingColors.contains(arr[currentPoint.x][currentPoint.y - 1]))
								surroundingColors.add(arr[currentPoint.x][currentPoint.y - 1]);
						}
					}
				}
				if (currentPoint.x != N - 1) {
					if (!visited[currentPoint.x + 1][currentPoint.y]) {
						if (arr[currentPoint.x + 1][currentPoint.y] == color) {
							firstQueue.add(new point(currentPoint.x + 1, currentPoint.y));
							visited[currentPoint.x + 1][currentPoint.y] = true;
						} else {
							secondQueue.add(new point(currentPoint.x + 1, currentPoint.y));
							if (!surroundingColors.contains(arr[currentPoint.x + 1][currentPoint.y]))
								surroundingColors.add(arr[currentPoint.x + 1][currentPoint.y]);
						}
					}
				}
				if (currentPoint.y != N - 1) {
					if (!visited[currentPoint.x][currentPoint.y + 1]) {
						if (arr[currentPoint.x][currentPoint.y + 1] == color) {
							firstQueue.add(new point(currentPoint.x, currentPoint.y + 1));
							visited[currentPoint.x][currentPoint.y + 1] = true;
						} else {
							secondQueue.add(new point(currentPoint.x, currentPoint.y + 1));
							if (!surroundingColors.contains(arr[currentPoint.x][currentPoint.y + 1]))
								surroundingColors.add(arr[currentPoint.x][currentPoint.y + 1]);
						}
					}
				}
			}
			singleHighScore = Math.max(singleHighScore, singleTempHighScore);
			int tempCount = 0;
			int color2 = 0;
			int[] tempArr = new int[surroundingColors.size()];
			visited2 = visited;
			while (!secondQueue.isEmpty()) {
				tempCount = 0;
				thirdQueue.add(secondQueue.poll());
				while (!thirdQueue.isEmpty()) {
					point currentPoint = thirdQueue.poll();
					tempCount++;
					color2 = arr[currentPoint.x][currentPoint.y];
					if (currentPoint.x != 0) {
						if (!visited[currentPoint.x - 1][currentPoint.y]) {
							if (arr[currentPoint.x - 1][currentPoint.y] == color
									|| arr[currentPoint.x - 1][currentPoint.y] == color2) {
								thirdQueue.add(new point(currentPoint.x - 1, currentPoint.y));
								visited[currentPoint.x - 1][currentPoint.y] = true;
							}
						}
					}
					if (currentPoint.y != 0) {
						if (!visited[currentPoint.x][currentPoint.y - 1]) {
							if (arr[currentPoint.x][currentPoint.y - 1] == color
									|| arr[currentPoint.x][currentPoint.y - 1] == color2) {
								thirdQueue.add(new point(currentPoint.x, currentPoint.y - 1));
								visited[currentPoint.x][currentPoint.y - 1] = true;
							}
						}
					}
					if (currentPoint.x != N - 1) {
						if (!visited[currentPoint.x + 1][currentPoint.y]) {
							if (arr[currentPoint.x + 1][currentPoint.y] == color
									|| arr[currentPoint.x + 1][currentPoint.y] == color2) {
								firstQueue.add(new point(currentPoint.x + 1, currentPoint.y));
								visited[currentPoint.x + 1][currentPoint.y] = true;
							}
						}
					}
					if (currentPoint.y != N - 1) {
						if (!visited[currentPoint.x][currentPoint.y + 1]) {
							if (arr[currentPoint.x][currentPoint.y + 1] == color
									|| arr[currentPoint.x][currentPoint.y + 1] == color2) {
								firstQueue.add(new point(currentPoint.x, currentPoint.y + 1));
								visited[currentPoint.x][currentPoint.y + 1] = true;
							}
						}
					}
				}
				for (int n = 0; n < surroundingColors.size(); n++) {
					if (color2 == surroundingColors.get(n)) {
						tempArr[n] += tempCount;
						break;
					}
				}
			}
			visited = visited2;
			for (int n = 0; n < surroundingColors.size(); n++) {
				if (tempArr[n] > teamTempHighScore) {
					teamTempHighScore = tempArr[n];
				}
			}
			teamHighScore = Math.max(teamHighScore, teamTempHighScore+singleTempHighScore);
		}
		FileWriter fw = new FileWriter("multimoo.out");
		fw.write(singleHighScore-2+"\n");
		fw.write(teamHighScore-2+"");
		fw.close();
		
	}

}

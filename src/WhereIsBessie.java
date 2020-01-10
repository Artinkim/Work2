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

class pcl {
	point first;
	point second;

	pcl(point first, point second) {
		this.first = first;
		this.second = second;
	}
}

public class WhereIsBessie {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("where.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<point> queue = new LinkedList<point>();
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		ArrayList<pcl> pcls = new ArrayList<pcl>();
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = (int) str.charAt(j);
			}
		}
		int currentColor = -1;
		int color1 = -1;
		int color2 = -1;
		int color1Occurance = 1;
		int color2Occurance = 1;
		boolean inside = true;
		boolean PCL = true;
		int PCLCount = 0;
		int p = 0;
		boolean[][] visited;
		for (int k = N - 1; k >= 0; k--) {
			for (int l = N - 1; l >= 0; l--) {
				visited = new boolean[k+1][l+1];
				for (int i = 0; i < N - k; i++) {
					for (int j = 0; j < N - l; j++) {
						for (int i2 = 0; i2 < k+1; i2++) 
							Arrays.fill(visited[i2], false);
						color1 = -1;
						color2 = -1;
						color1Occurance = 1;
						color2Occurance = 1;
						PCL = true;
						System.out.println();
						for (int a = i; a <= i + k; a++) {
							System.out.println();
							for (int b = j; b <= j + l; b++) {
								System.out.print(arr[a][b] - 65);
								if (visited[a-i][b-j])
									continue;
								currentColor = arr[a][b];
								queue.add(new point(a, b));
								visited[a-i][b-j] = true;
								while (!queue.isEmpty()) {
									point currentPoint = queue.poll();
									for (int n = 0; n < 4; n++) {
										if (dx[n] + currentPoint.x > i + k || dx[n] + currentPoint.x < i)
											continue;
										if (dy[n] + currentPoint.y > j + l || dy[n] + currentPoint.y < j)
											continue;
										if (!visited[currentPoint.x + dx[n]-i][currentPoint.y + dy[n]-j]) {
											if (arr[currentPoint.x + dx[n]][currentPoint.y + dy[n]] == currentColor) {
												queue.add(new point(currentPoint.x + dx[n], currentPoint.y + dy[n]));
												visited[currentPoint.x + dx[n]-i][currentPoint.y + dy[n]-j] = true;
											}
										}
									}
								}
								if (currentColor == color1) {
									color1Occurance++;
									continue;
								}
								if (currentColor == color2) {
									color2Occurance++;
									continue;
								}
								if (color1 == -1) {
									color1 = currentColor;
									continue;
								}

								if (color2 == -1) {
									color2 = currentColor;
									continue;
								}
								PCL = false;
								break;
							}
						}
						if (!(color1Occurance > 1 && color2Occurance == 1)
								&& !(color2Occurance > 1 && color1Occurance == 1)) {
							PCL = false;
						}
						if (PCL) {
							inside = true;
							for (p = 0; p < pcls.size(); p++) {
								if (((pcls.get(p).first.x <= i && pcls.get(p).first.y <= j)
										&& (pcls.get(p).second.x >= (i + k) && pcls.get(p).second.y >= (j + l)))) {
									inside = false;									
									break;
								}
							}
							if (inside) {
								PCLCount++;
								pcls.add(new pcl(new point(i, j), new point((i + k), (j + l))));
							}

						}
					}
				}
			}
		}
		FileWriter fw = new FileWriter("where.out");
		fw.write(PCLCount + "");
		fw.close();
	}
}

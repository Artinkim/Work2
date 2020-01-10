import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int x;
	int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return this.x - o.x;
	}
}

public class FieldReduction {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
		FileWriter fw = new FileWriter("reduce.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Pair> arr = new ArrayList<Pair>();
		Pair[] pairsX = new Pair[N];
		Pair[] pairsY = new Pair[N];
		boolean[] band = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pairsX[i] = new Pair(Integer.parseInt(st.nextToken()), i);
			pairsY[i] = new Pair(Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(pairsX);
		Arrays.sort(pairsY);
		for (int i = 0; i < 3; i++) {
			if (!band[pairsX[i].y]) {
				arr.add(pairsX[i]);
				band[pairsX[i].y] = true;
			}
		}
		for (int i = N - 1; i > N - 4; i--) {
			if (!band[pairsX[i].y]) {
				arr.add(pairsX[i]);
				band[pairsX[i].y] = true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (!band[pairsX[i].y]) {
				arr.add(pairsY[i]);
				band[pairsX[i].y] = true;
			}
		}
		for (int i = N - 1; i > N - 4; i--) {
			if (!band[pairsX[i].y]) {
				arr.add(pairsY[i]);
				band[pairsX[i].y] = true;
			}
		}
		System.out.println(arr.size());
		int maxX, maxY, minX, minY;
		int area = Integer.MAX_VALUE;
		int l = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i + 1; j < arr.size(); j++) {
				for (int k = j + 1; k < arr.size(); k++) {
					maxX = 0;
					maxY = 0;
					minX = 0;
					minY = 0;
					l = 0;
					while (pairsX[l].y == arr.get(i).y || pairsX[l].y == arr.get(j).y || pairsX[l].y == arr.get(k).y) {
						l++;
					}
					minX = pairsX[l].x;

					l = N - 1;
					while (pairsX[l].y == arr.get(i).y || pairsX[l].y == arr.get(j).y || pairsX[l].y == arr.get(k).y) {
						l--;
					}
					maxX = pairsX[l].x;

					l = 0;
					while (pairsY[l].y == arr.get(i).y || pairsY[l].y == arr.get(j).y || pairsY[l].y == arr.get(k).y) {
						l++;
					}
					minY = pairsY[l].x;
					l = N - 1;
					while (pairsY[l].y == arr.get(i).y || pairsY[l].y == arr.get(j).y || pairsY[l].y == arr.get(k).y) {
						l--;
					}
					maxY = pairsY[l].x;
					area = Math.min(area, (maxX - minX) * (maxY - minY));
				}
			}
		}
		fw.write(area + "");
		fw.close();
	}
}

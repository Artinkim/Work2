import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

class POINT implements Comparable<POINT> {
	int pos;
	int type;
	int end;

	POINT(int position, int type, int end) {
		this.pos = position;
		this.type = type;
		this.end = end;
	}

	@Override
	public int compareTo(POINT o) {
		// TODO Auto-generated method stub
		if (this.pos == o.pos) {
			if (o.type > this.type) {
				return -1;
			}
			return 1;
		}
		return this.pos - o.pos;
	}
}

public class CrossTheRoad1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		POINT[] points = new POINT[2 * N + C];
		ArrayList<POINT> tempPoints = new ArrayList<POINT>();
		POINT[] ranges = new POINT[N*2];
		for (int i = 0; i < C; i++) {
			points[i] = new POINT(Integer.parseInt(br.readLine()), 1, -1);
		}
		int a;
		int b;
		for (int i = 0; i < N * 2; i += 2) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			points[i + C] = new POINT(a, 0, i);
			points[i + 1 + C] = new POINT(b, 2, i);
			ranges[i] = points[i + 1 + C];
			
		}
		int count = 0;
		Arrays.sort(points);
		for (int i = 0; i < points.length; i++) {
			if (points[i].type == 1) {
				if (tempPoints.size() > 0) {
					tempPoints.remove(0);
					count++;
				}
				continue;
			}
			if (points[i].type == 0) {
				tempPoints.add(ranges[points[i].end]);
				Collections.sort(tempPoints);
				continue;
			}
			if (points[i].type == 2) {
				tempPoints.remove(points[i]);
				continue;
			}
		}
		FileWriter fw = new FileWriter("helpcross.out");
		fw.write(count + "");
		fw.close();
	}
}

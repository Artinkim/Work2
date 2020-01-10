import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair3 {
	int x;
	int y;

	Pair3(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class ClosingTheFarm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		FileWriter fw = new FileWriter("closing.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] pairs = new ArrayList[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] band = new boolean[N];
		boolean[] band2 = new boolean[N];
		int temp1, temp2;
		for (int i = 0; i < N; i++) 
			pairs[i] = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			temp1 = Integer.parseInt(st.nextToken()) - 1;
			temp2 = Integer.parseInt(st.nextToken()) - 1;
			pairs[temp1].add(temp2);
			pairs[temp2].add(temp1);
		}
		int temp;
		int count = 0;
		int current;
		queue.add(0);
		band2[0] = true;
		while (!queue.isEmpty()) {
			current = queue.poll();
			for (int j = 0; j < pairs[current].size(); j++) {
					if (!band2[pairs[current].get(j)]) {
						queue.add(pairs[current].get(j));
						band2[pairs[current].get(j)] = true;
						count++;
				}
			}
		}
		if(count == N-1) {
			fw.write("YES\n");
		} else {
			fw.write("NO\n");
		}
		count = 0;
		Arrays.fill(band2, false);
		for (int i = 0; i < N-1; i++) {
			temp = Integer.parseInt(br.readLine()) - 1;
			band[temp] = true;
			for (int j = 0; j < N; j++) {
				if (!band[j]) {
					queue.add(j);
					band2[j] = true;
					break;
				}
			}
			while (!queue.isEmpty()) {
				current = queue.poll();
				for (int j = 0; j < pairs[current].size(); j++) {
					if (!band[pairs[current].get(j)]) {
						if (!band2[pairs[current].get(j)]) {
							queue.add(pairs[current].get(j));
							band2[pairs[current].get(j)] = true;
							count++;
						}
					}
				}
			}
			if (count == N-2-i) {
				fw.write("YES\n");
			} else {
				fw.write("NO\n");
			}
			System.out.println(N-i-2+" "+count);
			count = 0;
			Arrays.fill(band2, false);
		}
		fw.close();
	}
}

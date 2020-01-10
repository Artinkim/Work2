import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DanceShow {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> ts = new PriorityQueue<Integer>();
		int N = Integer.parseInt(st.nextToken());
		int TMax = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int k = N;
		int max = N;
		int min = 0;
		boolean works = true;
		while (min != max) {
			k = (min + max) / 2;
			for (int i = 0; i < k; i++) {
				ts.add(arr[i]);
			}
			for (int i = k; i < N; i++) {
				ts.add(ts.poll() + arr[i]);
			}
			works = true;
			for (int i = 0; i < k; i++) {
				if (ts.poll() > TMax) {
					works = false;
					break;
				} 
			}
			if(works) {
				max = k;
			} else {
				min = k + 1;
			}
			ts.clear();
		}
		FileWriter fw = new FileWriter("cowdance.out");
		fw.write(k + 1 + "");
		fw.close();
	}
}

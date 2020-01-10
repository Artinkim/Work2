import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

class pair implements Comparable<pair> {
	int cow;
	int count;

	pair(int cow, int count) {
		this.cow = cow;
		this.count = count;
	}

	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		return count - o.count;
	}
}

public class PairUp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeSet<pair> ts = new TreeSet<pair>();
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ts.add(new pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

		}
		pair first;
		pair last;
		int highScore = -1;
		while (!ts.isEmpty()) {
			first = ts.first();
			last =  ts.last();
			highScore = Math.max(first.count + last.count, highScore);
			if (first.cow - last.cow == 0) {
				ts.pollFirst();
				ts.pollLast();
				continue;
			}
			if (first.cow - last.cow > 0) {
				first.cow = first.cow - last.cow;
				ts.pollLast();
				continue;
			}
			if (first.cow - last.cow < 0) {
				last.cow = last.cow - first.cow;
				ts.pollFirst();
				continue;
			}
			
		}
		FileWriter fw = new FileWriter("pairup.out");
		fw.write(highScore + "");
		fw.close();
	}
}

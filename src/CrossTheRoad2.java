import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CrossTheRoad2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		boolean[] broken = new boolean[N];
		for(int i = 0;i<B;i++) {
			broken[Integer.parseInt(br.readLine())-1] = true;
		}
		int lowScore = K;
		int count = 0;
		for(int j = 0;j<K;j++) {
			if(broken[j])
				count++;
		}
		System.out.println(count);
		for(int i = 1;i<N-K+1;i++) {
			if(broken[i-1])
				count--;
			if(broken[i+K-1])
				count++;
			lowScore = Math.min(count, lowScore);
		}
		FileWriter fw = new FileWriter("maxcross.out");
		fw.write(lowScore+"");
		fw.close();
	}
}

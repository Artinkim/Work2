import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AngryCows {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int max = 500000000;
		int min = 1;
		int key = 0;
		int count = K;
		int j;
		while (max != min) {
			System.out.println(min + " " + max + " " + key);
			count = K;
			key = (max + min) / 2;
			for (int i = 0; i < N; i++) {
				for (j = i + 1; j < N; j++) {
					if (arr[j] - arr[i] > key * 2)
						break;
				}
				count--;
				i = j-1;
				if (count < 0)
					break;
			}
			if (count < 0) {
				min = key+1;
			} else {
				max = key;
			}
		}
		pw.println(min);
		pw.close();
	}
}

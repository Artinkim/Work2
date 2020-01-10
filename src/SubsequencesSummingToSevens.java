import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SubsequencesSummingToSevens {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		long count = 0;
		int length = 0;
		int temp = 0;
		for (int i = 0; i < N-length; i++) {
			count = arr[i];
			if(arr[i] % 7 == 0) 
				temp = 1;
			for (int j = i + 1; j < N; j++) {
				count += arr[j];
				if (count % 7 == 0) 
					temp = j-i+1;
			}
			length = Math.max(temp, length);
			temp = 0;
		}
		pw.println(length);
		pw.close();
	}
}

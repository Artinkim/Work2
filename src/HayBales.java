import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HayBales {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		FileWriter fw = new FileWriter("haybales.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int temp1;
		int temp2;
		for(int i = 0;i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			temp1 = Arrays.binarySearch(arr, Integer.parseInt(st.nextToken()));
			temp2 = Arrays.binarySearch(arr, Integer.parseInt(st.nextToken()));
			if(temp1 == temp2) {
				fw.write(0+"\n");
				continue;
			} 
			if(temp1<0)
				temp1 = Math.abs(temp1+1);
			if(temp2<0)
				temp2 = Math.abs(temp2+2);
			fw.write(temp2-temp1+1+"\n");
		}
		fw.close();
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair2 {
	int x;
	int y;

	Pair2(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class DiamondCollector {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		FileWriter fw = new FileWriter("diamond.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int temp;
		int count = 0;
		int totalCount = 0;
		Queue<Pair2> queue = new LinkedList<Pair2>();
		for (int i = 0; i < N; i++) {
			System.out.println(arr[i]);
			temp = Arrays.binarySearch(arr, arr[i] + K);
			if (temp < 0) {
				temp+=2;
				temp *= -1;
			} else {
				if (temp != N - 1) {
					while (arr[temp + 1] == arr[temp]) {
						temp++;
						if (temp == N - 1)
							break;
					}
				}
			}
			count = temp - i;
			if (count > totalCount) {
				totalCount = count;
				queue.clear();
				queue.add(new Pair2(i, temp));
				continue;
			}
			if (count == totalCount) {
				queue.add(new Pair2(i, temp));
			}
			
		}
		
		totalCount++;
		Pair2 current;
		int totalCount2 = 0;
		System.out.println(queue.size());
		for (int i = 0; i < queue.size(); i++) {
			current = queue.poll();
			int[] arr2 = new int[N - totalCount+1];
			for (int j = 0; j < current.x; j++) {
				arr2[j] = arr[j];
			}
			for (int j = current.y + 1; j < N; j++) {
				arr2[j - totalCount] = arr[j];
			}

			for (int j = 0; j < N - totalCount; j++) {
				temp = Arrays.binarySearch(arr2, arr2[j] + K);
				if (temp < 0) {
					temp+=2;
					temp *= -1;
				} else {
					if (temp != N - totalCount) {
						while (arr2[temp + 1] == arr2[temp]) {
							if (temp == N - totalCount)
								break;
							temp++;
						}
					}
				}
				
				count = temp - j;
				totalCount2 = Math.max(totalCount2, count);
			}
		}
		fw.write(totalCount + totalCount2 + 1 + "");
		fw.close();

	}
}

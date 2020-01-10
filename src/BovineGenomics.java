import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class BovineGenomics {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[M][2 * N];
		String str;
		for (int i = 0; i < 2 * N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[j][i] = (int) str.charAt(j);
			}
		}
		boolean duplicate;
		boolean mainDuplicate;
		int count = 0;
		for (int a = 0; a < M; a++) {
			for (int b = a + 1; b < M; b++) {
				for (int c = b + 1; c < M; c++) {
					mainDuplicate = false;
					for (int i = 0; i < N; i++) {
						duplicate = false;
						for (int j = 0; j < N; j++) {
							if (arr[a][i] == arr[a][N + j] && arr[b][i] == arr[b][N + j]
									&& arr[c][i] == arr[c][N + j]) {
								duplicate = true;
								break;
							}
						}
						if (duplicate) {
							mainDuplicate = true;
							break;
						}
					}
					if (!mainDuplicate) {
						count++;
					}
				}
			}
		}
		FileWriter fw = new FileWriter("cownomics.out");
		fw.write(count + "");
		fw.close();
	}
}

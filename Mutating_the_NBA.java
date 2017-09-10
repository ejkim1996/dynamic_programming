import java.io.*;
import java.util.*;

public class Mutating_the_NBA {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		HashMap<String, Integer> d = new HashMap<>();
		char E[] = {'_', 'G', 'C', 'A', 'T'};
		int gapScore = -1;
		String a = input.nextLine();
		String b = input.nextLine();
		String newA = "";
		String newB = "";
		
		int[][] DP = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < 5; i++) {
			d.put("" + E[0] + E[i], gapScore);
			d.put("" + E[i] + E[0], gapScore);
		}
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (E[i] == E[j]) {
					d.put("" + E[i] + E[j], 2);
				}
				else {
					d.put("" + E[i] + E[j], -1);
				}
			}
			
		}
		for (int i = 0; i < a.length() + 1; i++) {
			DP[i][0] = gapScore*i;
		}
		
		for (int i = 0; i < b.length() + 1; i++) {
			DP[0][i] = gapScore*i;
		}
		
		
		for (int i = 1; i < a.length() + 1; i++) {
			for (int j = 1; j < b.length() + 1; j++) {
				int temp = Math.max(DP[i-1][j-1] + d.get("" + a.charAt(i-1) + b.charAt(j-1)), DP[i][j-1] + d.get("" + E[0] + b.charAt(j-1)));
				DP[i][j] = Math.max(temp, DP[i-1][j] + d.get("" + a.charAt(i-1) + E[0]));
			}
		}
		
		int x = a.length();
		int y = b.length();
		
		while (x > 0 && y > 0) {
			if (DP[x][y] - d.get("" + a.charAt(x-1) + b.charAt(y-1)) == DP[x-1][y-1]) {
				newA = a.charAt(x-1) + newA;
				newB = b.charAt(y-1) + newB;
				x--;
				y--;
			}
			else if (DP[x][y] - gapScore == DP[x][y-1]) {
				newA = "_" + newA;
				newB = b.charAt(y-1) + newB;
				y--;
			}
			else if (DP[x][y] - gapScore == DP[x-1][y]) {
				newA = a.charAt(x-1) + newA;
				newB = "_" + newB;
				x--;
			}
		}
		
		if (y > 0) {
			while (y > 0) {
				newB = b.charAt(y-1) + newB;
				newA = "_" + newA;
				y--;
			}
		}
		else if (x > 0) {
			while (x > 0) {
				newA = a.charAt(x-1) + newA;
				newB = "_" + newB;
				x--;
			}
		}
		
		System.out.println(DP[a.length()][b.length()]);
		System.out.println(newA);
		System.out.println(newB);
		
		input.close();
	}
	

}

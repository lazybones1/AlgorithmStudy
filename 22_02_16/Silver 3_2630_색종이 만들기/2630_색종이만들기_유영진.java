package day0216;

import java.util.Scanner;

public class Main_2630_색종이만들기 {
	static int [][] arr;
	static int wNum;
	static int bNum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		fun(N, 0, 0);
		System.out.println(wNum);
		System.out.println(bNum);

	}
	public static void fun(int n , int x , int y) {

		if(isSame(n, x, y)) return;

		fun(n/2, x, y);
		fun(n/2, x, y+n/2);
		fun(n/2, x+n/2, y);
		fun(n/2, x+n/2, y+n/2);
	}
	
	public static boolean isSame(int n , int x , int y) {
		int val = arr[x][y];

		for (int i = x; i < x+n; i++) {
			for (int j = y; j < y+n; j++) {
				if(val != arr[i][j]) return false;
			}
		}
		if(val==1)bNum++; 
		else wNum++;
		return true;
	}

}

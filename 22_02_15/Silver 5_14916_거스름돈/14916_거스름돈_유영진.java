package day0215;

import java.util.Scanner;

public class Main_14916_°Å½º¸§µ· {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		
		while (true) {
			
			if(n==1) {
				System.out.println("-1");
				return;
			}		
			if(n==0) break;
			if (n % 5 == 0) {				
				cnt=n/5+cnt;
				break;
			}
			else {
				n -= 2;
				cnt++;
				continue;
			}
			
		}
		System.out.println(cnt);

	}

}

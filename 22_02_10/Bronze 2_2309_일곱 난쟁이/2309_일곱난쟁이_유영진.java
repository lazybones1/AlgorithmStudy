package day0210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main_2309_ÀÏ°ö³­ÀïÀÌ {

	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		ArrayList<Integer> arr = new ArrayList<>();
		int sum = 0;
		for(int i=0;i<9;i++) {
			int h =sc.nextInt();
			arr.add(h);
			sum+=h;
		}
		Collections.sort(arr);
		
		loop:
		for(int i=0;i<8;i++) {
			for(int j=i+1;j<9;j++) {
				if(sum-arr.get(i)-arr.get(j)==100) {
					arr.remove(i);
					arr.remove(j-1);
					break loop;
				}
			}
		}
		
		for(int i=0;i<7;i++) {
			System.out.println(arr.get(i));
		}

	}
	
	

}

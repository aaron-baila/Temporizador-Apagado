package com.aaron.Temporizado;

public class aaaa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		
		while (a > 0) {
			try {
				Thread.sleep(1000);
				
				System.out.println(a);
				a--;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

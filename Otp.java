package Food;

import java.util.Random;
import java.util.Scanner;


public class Otp {
	Scanner sc=new Scanner(System.in);
	private int otp1;
	public void Otp(int Otp1) {
		this.otp1=otp1;
	}
	public  void Get_otp() {
		Random r=new Random();
		otp1=1000+r.nextInt(9000);
		
	}
	public int Otp1() {
		return otp1;
	}

}

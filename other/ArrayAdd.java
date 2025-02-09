
import java.util.Scanner;

public class ArrayAdd {
	
	public static void main(String[] args){


		int[] ary = new int[5];
		int   size = 5;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please put a number here: ");

		for (int i = 0;; i++){

			int num = scan.nextInt();
			System.out.println("You wanna put this num in the array ? y/n");
			String answer = scan.next();
			if (answer.equals("y")){
				if (i < size){
					ary[i] = num;
					System.out.println("The num has added successfully!");
				} else{
					System.out.println("The array has already fulled, do you wanna expand it to put this num in it ? y/n ");
					String answer2 = scan.next();
					if (answer2.equals("y")) {
						int[] newary = new int[size + 5];
						size += 5;
						for (int j = 0; j < ary.length; j ++){
							newary[j] = ary[j];
						}
						ary = newary;
						ary[i] = num;
					} else {
						System.out.println("Skiped ... ");
					}

				}
			} else {
				System.out.println("Skiped ...");
				i--;
			}
				
		}
	}


}

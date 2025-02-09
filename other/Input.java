import java.util.Scanner;


public class Input {

	public static void main (String[] args){


		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		int age = scanner.nextInt();
		double salary = scanner.nextDouble();

		System.out.println("name" + name + "\nage" + age + "\nsalary" + salary);

	}
}
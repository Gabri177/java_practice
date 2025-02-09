public class Stars {
	public static void main (String[] args) {
		int HIGH = 10;
		for (int i = 1; i <= HIGH; i++){
			for (int k = HIGH - i; k >= 0; k --)
				System.out.print(" ");
			for (int j = 1; j <= 2 * i - 1; j ++ )
				if (j == 1 || j == 2 * i - 1 || i == HIGH)
					System.out.print("*");
				else
					System.out.print(" ");
			System.out.print("\n");
		}
	}
}
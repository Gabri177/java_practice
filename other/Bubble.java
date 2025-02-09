

public class Bubble {

	public static void main( String[] args){

		int[] ary = {34, 21, 25, 2, 643, 6, 89, 43, 63, 111};


		for (int i = 0; i < ary.length; i ++){

			for (int j = 0; j < ary.length - i - 1; j ++){

				if (ary[j] > ary[j + 1]){
					int temp = ary[j];
					ary[j] = ary[j + 1];
					ary[j + 1] = temp;
				} else {
					continue ;
				}

			}
		}

		System.out.println( "This is the result ... ");
		for (int i = 0; i < ary.length; i ++){ 

			System.out.println("  " + ary[i]);
		}
		return ;
	}
}
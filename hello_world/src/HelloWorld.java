import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("HelloWorld123");
        Scanner scan = new Scanner(System.in);

    }
}

class Grandpa {
    String name = "grandpa";
}

class Father extends Grandpa {
    String name = "father";

}

class Son extends Father {
    String name = "son";
}
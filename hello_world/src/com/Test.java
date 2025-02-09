package com;

class Grandpa {
    String name = "grandpa";
}

class Father extends Grandpa {
    String name = "father";
}

class Son extends Father {
    String name = "son";
}

public class Test {
    public static void main(String[] args) {
        Grandpa g = new Grandpa();
        Father f = new Father();
        Son s = new Son();

        System.out.println("Grandpa name: " + g.name); // 输出 grandpa
        System.out.println("Father name: " + f.name);  // 输出 father
        System.out.println("Son name: " + s.name);     // 输出 son

        // 父类引用指向子类对象
        Grandpa g2 = s;
        Father f2 = s;

        System.out.println("Grandpa reference to Son: " + g2.name); // 输出 grandpa
        System.out.println("Father reference to Son: " + f2.name);  // 输出 father
    }
}

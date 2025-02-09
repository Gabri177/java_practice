package com.yugao.internet;

import java.util.Scanner;

public class Loading {

    public static final String[] SPINNER = {
            "⠋\r", "⠙\r", "⠹\r", "⠸\r", "⠼\r", "⠴\r", "⠦\r", "⠧\r", "⠇\r", "⠏\r"
    };

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        int n = -1;
        while(true) {
            try {
                System.out.println("请输入数字1 显示Loading...");
                n = sc.nextInt();
            } catch(Exception e){
                e.printStackTrace();
            }
            if (n == 1) {
                System.out.println("Loading...");
                Loading.setTime(5000);
            }
        }
    }

    public static void setTime(int time) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        long endTime = startTime + time;

        while (System.currentTimeMillis() < endTime) {

            for (int i = 0; i < SPINNER.length; i++) {
                System.out.print(SPINNER[i]);
                Thread.sleep(100);
            }
        }
    }
}

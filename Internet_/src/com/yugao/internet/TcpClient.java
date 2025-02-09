package com.yugao.internet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {



        try (Socket socket = new Socket(InetAddress.getLocalHost(), 7777);
             OutputStream os = socket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

                new Thread(()-> {
                    try {
                        InputStream is = socket.getInputStream();
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = is.read(buf)) != -1) {
                            System.out.println(new String(buf, 0, len));
                        }
                    } catch (IOException e) {
                        System.out.println("InputSteam error");
                    }
                }).start();

//                File file = null;
//                FileInputStream fis = null;
//                try {
//                    file = new File("./Torrente.png");
//                    fis = new FileInputStream(file);
//                } catch (Exception e) {
//                    System.out.println("Fill not found !");
//                }
//                byte[] buf = new byte[1024];
//                int len;
//                while ((len = fis.read(buf)) != -1) {
//                    os.write(buf, 0, len);
//                }
//                System.out.println("图片发送完毕!");
//                fis.close();
//                os.close();
//                socket.close();

                String content = "";
                while ((content = scanner.nextLine()) != null) {
                    os.write(content.getBytes());
                }
                System.out.println("客户端退出，正在关闭资源...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

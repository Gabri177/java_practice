package com.yugao.internet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class TcpServer {

    public static void main(String[] args) throws IOException {
        // 创建服务器端口
        ServerSocket serverSocket = new ServerSocket(7777);
        // 用于存储每个连接的 Socket 以及其对应的 InputStream
        Hashtable<Socket, InputStream> sockets = new Hashtable<>();

        // 注册一个 shutdown hook，在程序退出时关闭所有资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook: closing resources...");
            try {
                if (!serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing server socket: " + e.getMessage());
            }
            // 关闭所有已接受的 Socket 及其 InputStream
            for (Socket s : sockets.keySet()) {
                try {
                    InputStream is = sockets.get(s);
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error closing input stream: " + e.getMessage());
                }
                try {
                    if (s != null && !s.isClosed()) {
                        s.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error closing socket: " + e.getMessage());
                }
            }
            System.out.println("All resources closed.");
        }));

        // 主循环：等待客户端连接和处理数据
        while (true) {
            try {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());
                    // 为每个客户端启动一个线程
                    new Thread(() -> {

                        try (InputStream socketInputStream = socket.getInputStream();
                             OutputStream socketOutputStream = socket.getOutputStream()) {
                            socketOutputStream.write("HTTP/1.1 200 OK\r\n 你已经链接成功\n".getBytes());

//                            File file = null;
//                            FileOutputStream fileOutputStream = null;
//                            try {
//                                file = new File("./Torrente3.png");
//                                if (!file.exists()) {
//                                    file.createNewFile();
//                                }
//                                fileOutputStream = new FileOutputStream(file);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
                            int readBytes;
                            byte[] buffer = new byte[1024];
//                            while ((readBytes = socketInputStream.read(buffer)) != -1) {
//                                fileOutputStream.write(buffer, 0, readBytes);
//                            }
//                            fileOutputStream.close();
//                            System.out.println("图片接受完毕");
                            while ((readBytes = socketInputStream.read(buffer)) != -1) {
                                String data = new String(buffer, 0, readBytes);
                                System.out.println(socket.getInetAddress().getHostName() + " : " + data);
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading from " + socket.getRemoteSocketAddress() + ": " + e.getMessage());
                        } finally {
                            try {
                                System.out.println("Closing connection from " + socket.getRemoteSocketAddress());
                                if (!socket.isClosed()) {
                                    socket.close();
                                    sockets.remove(socket);
                                }
                            } catch (IOException e) {
                                System.err.println("Error closing socket: " + e.getMessage());
                            }
                        }
                    }).start();
                }
            } catch (IOException e) {
                System.err.println("Error accepting input stream: " + e.getMessage());
            }
        }
    }
}

package com.yugao.internet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args) {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("localhost", 7777);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);
            socketChannel.configureBlocking(true);
            System.out.println("已连接到服务器 " + serverAddress);

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入消息，回车后发送 (输入 exit 退出):");
            while (true) {
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                socketChannel.write(writeBuffer);

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int bytesRead = socketChannel.read(readBuffer);
                if (bytesRead == -1) {
                    System.out.println("服务器关闭了连接");
                    break;
                }
                readBuffer.flip();
                byte[] data = new byte[bytesRead];
                readBuffer.get(data);
                System.out.println("来自服务器的回显: " + new String(data));
            }
            socketChannel.close();
            scanner.close();
            System.out.println("客户端已退出");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

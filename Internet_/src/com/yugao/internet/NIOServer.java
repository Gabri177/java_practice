package com.yugao.internet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) {
        try {

            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(7777));
            System.out.println("Server started on port 7777");
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                int readyFd = selector.select();
                if(readyFd == 0) continue;
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel1.accept();
                        if (socketChannel != null) {
                            socketChannel.configureBlocking(false);
                            System.out.println("Accepted connection" + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int byteRead = socketChannel.read(byteBuffer);
                        if (byteRead == -1) {
                            System.out.println("Server stopped" + socketChannel.getRemoteAddress());
                            socketChannel.close();
                        } else if (byteRead > 0) {
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteRead];
                            byteBuffer.get(bytes);
                            String message = new String(bytes);
                            System.out.println(message + "from " + socketChannel.getRemoteAddress());
                            byteBuffer.rewind();
                            socketChannel.write(byteBuffer);

                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

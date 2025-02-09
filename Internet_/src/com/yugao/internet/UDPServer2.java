package com.yugao.internet;

import java.io.IOException;
import java.net.*;

public class UDPServer2 {

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(9998);
        DatagramPacket packet = new DatagramPacket("hello this is a msg from server02".getBytes(),
                "hello this is a msg from server02".length(), InetAddress.getLocalHost(), 9999);
        socket.send(packet);
        System.out.println("Msg sent!!!");
        socket.close();

    }
}

package com.yugao.internet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer1 {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(9999);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        System.out.println("Receive data: " + new String(packet.getData()));
        socket.close();
    }
}

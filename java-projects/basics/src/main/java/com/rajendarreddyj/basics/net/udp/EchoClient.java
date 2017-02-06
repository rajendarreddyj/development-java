package com.rajendarreddyj.basics.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public EchoClient() {
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendEcho(final String msg) {
        DatagramPacket packet = null;
        try {
            this.buf = msg.getBytes();
            packet = new DatagramPacket(this.buf, this.buf.length, this.address, 4445);
            this.socket.send(packet);
            packet = new DatagramPacket(this.buf, this.buf.length);
            this.socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        this.socket.close();
    }
}

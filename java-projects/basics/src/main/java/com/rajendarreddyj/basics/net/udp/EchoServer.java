package com.rajendarreddyj.basics.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoServer extends Thread {

    protected DatagramSocket socket = null;
    protected boolean running;
    protected byte[] buf = new byte[256];

    public EchoServer() throws IOException {
        this.socket = new DatagramSocket(4445);
    }

    @Override
    public void run() {
        this.running = true;

        while (this.running) {
            try {

                DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length);
                this.socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(this.buf, this.buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.equals("end")) {
                    this.running = false;
                    continue;
                }
                this.socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                this.running = false;
            }
        }
        this.socket.close();
    }
}

package com.rajendarreddyj.basics.net.udp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UDPIntegrationTest {
    private EchoClient client;

    @Before
    public void setup() throws IOException {
        new EchoServer().start();
        this.client = new EchoClient();
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect1() {
        String echo = this.client.sendEcho("hello server");
        assertEquals("hello server", echo);
        echo = this.client.sendEcho("server is working");
        assertFalse(echo.equals("hello server"));
    }

    @After
    public void tearDown() {
        this.stopEchoServer();
        this.client.close();
    }

    private void stopEchoServer() {
        this.client.sendEcho("end");
    }
}

package com.rajendarreddy.servlet;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rajendarreddy
 *
 */
public class ReadListenerImpl implements ReadListener {
    private static final Logger logger = Logger.getAnonymousLogger();
    private ServletInputStream input = null;
    private HttpServletResponse res = null;
    private AsyncContext ac = null;
    private Queue<String> queue = new LinkedBlockingQueue<>();

    ReadListenerImpl(final ServletInputStream in, final HttpServletResponse r, final AsyncContext c) {
        this.input = in;
        this.res = r;
        this.ac = c;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ReadListener#onAllDataRead()
     */
    @Override
    public void onAllDataRead() throws IOException {
        logger.info("Data is all read");

        // now all data are read, set up a WriteListener to write
        ServletOutputStream output = this.res.getOutputStream();
        WriteListener writeListener = new WriteListenerImpl(output, this.queue, this.ac);
        output.setWriteListener(writeListener);

    }

    /* (non-Javadoc)
     * @see javax.servlet.ReadListener#onDataAvailable()
     */
    @Override
    public void onDataAvailable() throws IOException {
        logger.info("Data is available");

        StringBuilder sb = new StringBuilder();
        int len = -1;
        byte b[] = new byte[1024];
        while (this.input.isReady() && ((len = this.input.read(b)) != -1)) {
            String data = new String(b, 0, len);
            sb.append(data);
        }
        this.queue.add(sb.toString());

    }

    /* (non-Javadoc)
     * @see javax.servlet.ReadListener#onError(java.lang.Throwable)
     */
    @Override
    public void onError(final Throwable t) {
        this.ac.complete();
        t.printStackTrace();

    }

}

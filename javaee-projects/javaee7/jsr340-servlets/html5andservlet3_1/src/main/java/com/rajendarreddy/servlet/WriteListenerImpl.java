package com.rajendarreddy.servlet;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author rajendarreddy
 *
 */
public class WriteListenerImpl implements WriteListener {

    private ServletOutputStream output = null;
    private Queue<String> queue = null;
    private AsyncContext context = null;

    WriteListenerImpl(final ServletOutputStream sos, final Queue<String> q, final AsyncContext c) {
        this.output = sos;
        this.queue = q;
        this.context = c;
    }

    /* (non-Javadoc)
     * @see javax.servlet.WriteListener#onError(java.lang.Throwable)
     */
    @Override
    public void onError(final Throwable t) {
        this.context.complete();
        t.printStackTrace();
    }

    /* (non-Javadoc)
     * @see javax.servlet.WriteListener#onWritePossible()
     */
    @Override
    public void onWritePossible() throws IOException {
        while ((this.queue.peek() != null) && this.output.isReady()) {
            String data = this.queue.poll();
            this.output.print(data);
        }
        if (this.queue.peek() == null) {
            this.context.complete();
        }

    }

}

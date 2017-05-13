package com.rajendarreddy.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rajendarreddy
 *
 */
@WebServlet(name = "UploadServlet", urlPatterns = { "/UploadServlet" }, asyncSupported = true)
public class UploadServlet extends HttpServlet {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        AsyncContext context = request.startAsync();
        // set up async listener
        context.addListener(new AsyncListener() {
            @Override
            public void onComplete(final AsyncEvent event) throws IOException {
                event.getSuppliedResponse().getOutputStream().print("Complete");

            }

            @Override
            public void onError(final AsyncEvent event) {
                logger.info(event.getThrowable().toString());
            }

            @Override
            public void onStartAsync(final AsyncEvent event) {
            }

            @Override
            public void onTimeout(final AsyncEvent event) {
                logger.info("my asyncListener.onTimeout");
            }
        });
        ServletInputStream input = request.getInputStream();
        ReadListener readListener = new ReadListenerImpl(input, response, context);
        input.setReadListener(readListener);
    }
}

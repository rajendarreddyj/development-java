package com.rajendarreddyj.basics.filereadwrite;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rajendarreddy.jagapathi
 */
public class FileDownloader {
    // or newFixedThreadPool() to control the number of threads.
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public FileDownloader() {
        super();
    }

    public void downloadPdf(final URL targetUrl) throws IOException {
        final File destination = File.createTempFile("download-pdf-" + System.currentTimeMillis(), ".pdf");
        this.executor.submit(new DownloadCallable(targetUrl, destination));
    }

    private static final class DownloadCallable implements Callable<File> {

        private static final int BUFFER_SIZE = 1024 * 1024;

        private final URL targetUrl;
        private final File destination;

        public DownloadCallable(final URL targetUrl, final File destination) {
            this.targetUrl = targetUrl;
            Objects.requireNonNull(targetUrl);

            this.destination = destination;
            Objects.requireNonNull(destination);
        }

        @Override
        public File call() throws IOException {
            final URLConnection request = this.targetUrl.openConnection();
            try (final InputStream inputStream = request.getInputStream();
                    final FileOutputStream fileStream = new FileOutputStream(this.destination);
                    final BufferedOutputStream outputStream = new BufferedOutputStream(fileStream, BUFFER_SIZE);) {

                final byte[] data = new byte[10240];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, bytesRead);
                }
            }
            return this.destination;
        }
    }
}

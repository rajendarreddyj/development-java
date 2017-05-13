package com.rajendarreddyj.scheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.rajendarreddyj.scheduler.bean.ServerVariables;

/**
 * @author rajendarreddy
 */
public class TaskScheduler extends TimerTask {
    private static final Logger logger = Logger.getAnonymousLogger();
    private final String filePath;
    private final String fileExtension;
    private final String archiveFileExtension;
    private final String webServiceUrl;
    private String tempFilePath;
    private long filePointer;

    /**
     * @param filePath
     * @param fileExtension
     * @param archiveFileExtension
     * @param webServiceUrl
     */
    public TaskScheduler(final String filePath, final String fileExtension, final String archiveFileExtension, final String webServiceUrl) {
        super();
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.archiveFileExtension = archiveFileExtension;
        this.webServiceUrl = webServiceUrl;
    }

    /* (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        logger.info("Scheduler started @ " + new Date());
        // Read File
        File[] listOfFiles = this.finder(this.filePath, this.fileExtension);
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                this.tempFilePath = file.getAbsolutePath();
                // this.readFile(file);
                this.readFileWithPointer(file);
                this.renameFile(file);
                this.deleteTempFile(this.tempFilePath);

            }
        } else {
            logger.info("Correct File Path in properties");
        }
    }

    // HTTP POST request
    /**
     * @param url
     * @param serverVariablesList
     * @throws Exception
     */
    private void sendPost(final String url, final ServerVariables serverVariables) {
        int retry = 3;
        // Retry 3 times for Failure for Each Record Insertion
        for (int i = 0; i < retry; i++) {
            logger.info("Trying " + i);
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // add reuqest header
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes("");
                wr.flush();
                wr.close();

                int responseCode;
                responseCode = con.getResponseCode();

                if (responseCode == 200) {
                    break;
                }
                logger.info("\nSending 'GET' request to URL : " + url);
                logger.info("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                logger.info(response.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // print result

        }

    }

    /**
     * @param file
     */
    private void renameFile(final File file) {
        File file2 = new File(file.getAbsoluteFile() + this.archiveFileExtension);
        boolean success = file.renameTo(file2);
        if (success) {
            logger.info("Processing File Renamed");
        }
    }

    /**
     * @param dirName
     * @param extension
     * @return
     */
    private File[] finder(final String dirName, final String extension) {
        File dir = new File(dirName);

        return dir.listFiles((FilenameFilter) (dir1, filename) -> filename.endsWith(extension));

    }

    /**
     * @param file
     * @return
     */
    public void readFile(final File file) {
        BufferedReader br = null;
        String line = "";
        String lineSplitBy = "\\|";
        try {
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                ServerVariables serverVariables = new ServerVariables();
                // use pipe as separator
                String[] values = line.split(lineSplitBy);
                if (values.length > 0) {
                    serverVariables.setHttpReferer(values[0]);
                }
                if (values.length > 1) {
                    serverVariables.setHttpUserAgent(values[1]);
                }
                if (values.length > 2) {
                    serverVariables.setRemoteAddress(values[2]);
                }
                if (values.length > 3) {
                    serverVariables.setRemoteHost(values[3]);
                }
                if (values.length > 4) {
                    serverVariables.setRequestMethod(values[4]);
                }
                if (values.length > 5) {
                    serverVariables.setServerName(values[5]);
                }
                if (values.length > 6) {
                    serverVariables.setServerPort(values[6]);
                }
                if (values.length > 7) {
                    serverVariables.setServerSoftware(values[7]);
                }
                logger.info("Call WS");
                logger.info(serverVariables.toString());
                try {
                    // WS Call
                    this.sendPost(this.webServiceUrl, serverVariables);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param file
     * @return
     */
    private void readFileWithPointer(final File file) {
        String line = "";
        String lineSplitBy = "\\|";
        long len = file.length();

        logger.info("seeking position " + this.filePointer);
        try {
            // To Save and Retrieve pointer temp file is used.
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            this.filePointer = this.readPointerFromTempFile();
            if (this.filePointer < len) {
                raf.seek(this.filePointer);
            } else {
                raf.seek(0);
            }
            while ((line = raf.readLine()) != null) {
                ServerVariables serverVariables = new ServerVariables();
                // use pipe as separator
                String[] values = line.split(lineSplitBy);
                if (values.length > 0) {
                    serverVariables.setHttpReferer(values[0]);
                }
                if (values.length > 1) {
                    serverVariables.setHttpUserAgent(values[1]);
                }
                if (values.length > 2) {
                    serverVariables.setRemoteAddress(values[2]);
                }
                if (values.length > 3) {
                    serverVariables.setRemoteHost(values[3]);
                }
                if (values.length > 4) {
                    serverVariables.setRequestMethod(values[4]);
                }
                if (values.length > 5) {
                    serverVariables.setServerName(values[5]);
                }
                if (values.length > 6) {
                    serverVariables.setServerPort(values[6]);
                }
                if (values.length > 7) {
                    serverVariables.setServerSoftware(values[7]);
                }
                logger.info("Call WS ");
                logger.info(serverVariables.toString());
                try {
                    // WS Call
                    this.sendPost(this.webServiceUrl, serverVariables);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                this.filePointer = raf.getFilePointer();
                logger.info("Set Pointer " + raf.getFilePointer());
                this.createTempFileForPointer(raf.getFilePointer());
            }
            raf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * @param pointer
     */
    private void createTempFileForPointer(final long pointer) {
        BufferedWriter bw = null;
        try {

            // create a temp file
            File temp = new File(this.tempFilePath + ".tmp");
            temp.createNewFile();
            // write it
            bw = new BufferedWriter(new FileWriter(temp));
            bw.write(Long.toString(pointer));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }

        }
    }

    /**
     * @return
     */
    private long readPointerFromTempFile() {
        BufferedReader br = null;
        FileReader fr = null;
        long pointer = 0;
        try {
            fr = new FileReader(this.tempFilePath + ".tmp");
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                pointer = Long.parseLong(sCurrentLine.trim());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }
        return pointer;
    }

    /**
     * @param file
     */
    private void deleteTempFile(final String tempFilePath) {
        File file = new File(tempFilePath + ".tmp");
        boolean success = file.delete();
        if (success) {
            logger.info("Temp File Deleted");
        }
    }
}

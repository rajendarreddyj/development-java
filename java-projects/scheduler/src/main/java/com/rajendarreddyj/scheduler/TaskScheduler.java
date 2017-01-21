package com.rajendarreddyj.scheduler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import com.rajendarreddyj.scheduler.bean.ServerVariables;

/**
 * @author rajendarreddy
 *
 */
public class TaskScheduler extends TimerTask {

    private String filePath;
    private String fileExtension;
    private String archiveFileExtension;
    private String webServiceUrl;

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
        System.out.println("Scheduler started @ " + new Date());
        // Read File
        File[] listOfFiles = this.finder(this.filePath, this.fileExtension);

        for (File file : listOfFiles) {
            List<ServerVariables> serverVariablesList = this.readFile(file);
            System.out.println("Call WS");
            System.out.println(serverVariablesList.toString());
            try {
                // WS Call
                this.sendPost(this.webServiceUrl, serverVariablesList);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.renameFile(file);

        }
    }

    // HTTP POST request
    /**
     * @param url
     * @param serverVariablesList
     * @throws Exception
     */
    private void sendPost(final String url, final List<ServerVariables> serverVariablesList) {
        int retry = 3;
        for (ServerVariables serverVariables : serverVariablesList) {
            // Retry 3 times for Failure for Each Record Insertion
            for (int i = 0; i < retry; i++) {
                System.out.println("Trying " + i);
                try {
                    URL obj = new URL(url);
                    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

                    // add reuqest header
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                    String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

                    // Send post request
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                    int responseCode;
                    responseCode = con.getResponseCode();

                    if (responseCode == 200) {
                        break;
                    }
                    System.out.println("\nSending 'POST' request to URL : " + url);
                    System.out.println("Post parameters : " + urlParameters);
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    System.out.println(response.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // print result

            }

        }

    }

    /**
     * @param file
     */
    private void renameFile(final File file) {
        File file2 = new File(file.getAbsoluteFile() + this.archiveFileExtension);
        boolean success = file.renameTo(file2);
        if (success) {
            System.out.println("File Renamed");
        }
    }

    /**
     * @param dirName
     * @param extension
     * @return
     */
    private File[] finder(final String dirName, final String extension) {
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(final File dir, final String filename) {
                return filename.endsWith(extension);
            }
        });

    }

    /**
     * @param file
     * @return
     */
    private List<ServerVariables> readFile(final File file) {
        BufferedReader br = null;
        String line = "";
        String lineSplitBy = "\\|";
        List<ServerVariables> serverVariablesList = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(file));
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
                serverVariablesList.add(serverVariables);
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
        return serverVariablesList;
    }

}

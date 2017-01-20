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
import java.util.List;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import com.rajendarreddyj.scheduler.bean.ServerVariables;

/**
 * @author rajendarreddy
 *
 */
public class TaskScheduler extends TimerTask {

    private final String USER_AGENT = "Mozilla/5.0";

    private String filePath;
    private String fileExtension;
    private String webServiceUrl;

    public TaskScheduler(String filePath, String fileExtension, String webServiceUrl) {
        super();
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.webServiceUrl = webServiceUrl;
    }

    /* (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        // Read File
        File[] listOfFiles = this.finder(filePath, fileExtension);

        for (File file : listOfFiles) {
            List<ServerVariables> serverVariablesList = readFile(file);
            for(ServerVariables serverVariables:serverVariablesList) {
                try {
                    sendPost(webServiceUrl, serverVariables);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

 // HTTP POST request
    private void sendPost(String url, ServerVariables serverVariables) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private File[] finder(String dirName, String extension) {
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(extension);
            }
        });

    }

    private List<ServerVariables> readFile(File file) {
        BufferedReader br = null;
        String line = "";
        String lineSplitBy = "|";
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

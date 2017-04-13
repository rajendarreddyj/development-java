package com.rajendarreddyj.velocity.csv;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.rajendarreddyj.velocity.data.CsvData;
import com.rajendarreddyj.velocity.data.CsvDataFeed;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CSVGenerator {
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    static CsvDataFeed dataFeed;
    static {
        // prepare some static data
        dataFeed = new CsvDataFeed();
        List<CsvData> dataList = new ArrayList<>();
        CsvData data = new CsvData();
        data.setTransactionId(1);
        data.setAmount(new Integer(2312));
        data.setCurrency("SGD");
        data.setExecutionDate(dateFormat.format(new Date()));
        dataList.add(data);
        data = new CsvData();
        data.setTransactionId(2);
        data.setAmount(new Integer(1212));
        data.setCurrency("INR");
        data.setExecutionDate(dateFormat.format(new Date()));
        data.setStatus("Active");
        dataList.add(data);

        dataFeed.getCsvDataList().addAll(dataList);

    }

    public static void main(final String[] args) throws Exception {
        VelocityContext context = new VelocityContext();
        FileWriter writer = new FileWriter(new File("feed.csv"));

        // inject the objects to be used in template
        context.put("csvDataFeed", dataFeed);
        context.put("DELIMETER", ",");
        context.put("NEWLINE", "\n");

        Velocity.init();
        Template template = Velocity.getTemplate("src/main/resources/csv.vm");
        template.merge(context, writer);
        writer.flush();
        writer.close();

    }
}

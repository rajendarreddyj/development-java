package com.rajendarreddyj.velocity.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CsvDataFeed {
    List<CsvData> csvDataList = new ArrayList<>();

    /**
     * @return the csvDataList
     */
    public List<CsvData> getCsvDataList() {
        return this.csvDataList;
    }

    /**
     * @param csvDataList
     *            the csvDataList to set
     */
    public void setCsvDataList(final List<CsvData> csvDataList) {
        this.csvDataList = csvDataList;
    }

}

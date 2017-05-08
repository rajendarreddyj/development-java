package com.rajendarreddyj.spring.web.metric;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap;
    private ConcurrentMap<Integer, Integer> statusMetric;
    private ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> timeMap;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public MetricService() {
        super();
        this.metricMap = new ConcurrentHashMap<>();
        this.statusMetric = new ConcurrentHashMap<>();
        this.timeMap = new ConcurrentHashMap<>();
    }

    // API

    @Override
    public void increaseCount(final String request, final int status) {
        this.increaseMainMetric(request, status);
        this.increaseStatusMetric(status);
        this.updateTimeMap(status);
    }

    @Override
    public ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> getFullMetric() {
        return this.metricMap;
    }

    @Override
    public ConcurrentMap<Integer, Integer> getStatusMetric() {
        return this.statusMetric;
    }

    @Override
    public Object[][] getGraphData() {
        final int colCount = this.statusMetric.keySet().size() + 1;
        final Set<Integer> allStatus = this.statusMetric.keySet();
        final int rowCount = this.timeMap.keySet().size() + 1;

        final Object[][] result = new Object[rowCount][colCount];
        result[0][0] = "Time";

        int j = 1;
        for (final int status : allStatus) {
            result[0][j] = status;
            j++;
        }
        int i = 1;
        ConcurrentMap<Integer, Integer> tempMap;
        for (final Entry<String, ConcurrentHashMap<Integer, Integer>> entry : this.timeMap.entrySet()) {
            result[i][0] = entry.getKey();
            tempMap = entry.getValue();
            for (j = 1; j < colCount; j++) {
                result[i][j] = tempMap.get(result[0][j]);
                if (result[i][j] == null) {
                    result[i][j] = 0;
                }
            }
            i++;
        }

        return result;
    }

    // NON-API

    private void increaseMainMetric(final String request, final int status) {
        ConcurrentHashMap<Integer, Integer> statusMap = this.metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        this.metricMap.put(request, statusMap);
    }

    private void increaseStatusMetric(final int status) {
        final Integer statusCount = this.statusMetric.get(status);
        if (statusCount == null) {
            this.statusMetric.put(status, 1);
        } else {
            this.statusMetric.put(status, statusCount + 1);
        }
    }

    private void updateTimeMap(final int status) {
        final String time = dateFormat.format(new Date());
        ConcurrentHashMap<Integer, Integer> statusMap = this.timeMap.get(time);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        this.timeMap.put(time, statusMap);
    }

}

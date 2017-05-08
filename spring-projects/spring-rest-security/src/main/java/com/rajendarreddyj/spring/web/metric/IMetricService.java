package com.rajendarreddyj.spring.web.metric;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface IMetricService {

    void increaseCount(final String request, final int status);

    ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> getFullMetric();

    ConcurrentMap<Integer, Integer> getStatusMetric();

    Object[][] getGraphData();
}

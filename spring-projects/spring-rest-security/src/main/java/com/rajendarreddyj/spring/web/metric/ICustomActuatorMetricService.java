package com.rajendarreddyj.spring.web.metric;

public interface ICustomActuatorMetricService {

    void increaseCount(final int status);

    Object[][] getGraphData();
}

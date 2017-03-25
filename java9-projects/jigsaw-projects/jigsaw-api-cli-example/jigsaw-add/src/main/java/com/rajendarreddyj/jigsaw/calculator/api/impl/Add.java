package com.rajendarreddyj.jigsaw.calculator.api.impl;

import com.rajendarreddyj.jigsaw.calculator.api.Algorithm;

/**
 *
 * @author rajendarreddy
 */
public class Add implements Algorithm {

    @Override
    public Integer calculate(Integer input, Integer input2) {
        return input + input2;
    }
}

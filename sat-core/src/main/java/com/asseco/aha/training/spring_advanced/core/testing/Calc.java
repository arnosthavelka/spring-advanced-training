package com.asseco.aha.training.spring_advanced.core.testing;

import org.springframework.stereotype.Service;

@Service
public class Calc {

    public int add(int a, int b) {
        return a + b;
    }

}

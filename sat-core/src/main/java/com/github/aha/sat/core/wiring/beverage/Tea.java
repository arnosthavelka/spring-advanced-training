package com.github.aha.sat.core.wiring.beverage;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Tea implements HotBeverage {

    @Override
    public String getName() {
        return "Tea";
    }

}

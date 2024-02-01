package com.github.aha.sat.core.wiring.beverage;

import org.springframework.stereotype.Component;

@Component
public class Soda extends AbstractCarbonatedBeverage {

    @Override
    public String getName() {
        return "Soda";
    }

}

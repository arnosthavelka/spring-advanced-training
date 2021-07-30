package com.github.aha.sat.core.wiring.order;

import com.github.aha.sat.core.wiring.beverage.Beverage;

public interface BeverageOrder<T extends Beverage> {

	String takeOrder(T beverage);

}

package com.github.aha.sat.core.testing.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.aha.sat.core.testing.Calc;

@Configuration
//@ComponentScan(basePackageClasses = { Calc.class, BarbaraConfig.class })
@Import(Calc.class)
public class PackageConfig {

}

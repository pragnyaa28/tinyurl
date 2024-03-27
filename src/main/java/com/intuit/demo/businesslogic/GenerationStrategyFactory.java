package com.intuit.demo.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerationStrategyFactory {

    @Autowired
    Base62Strategy base62Strategy;

    @Autowired
    MDStrategy mdStrategy;

    public GenerationStrategy getGenerationStrategy(String generationStrategyType){
        return switch (generationStrategyType) {
            case "BASE62" -> base62Strategy;
            case "MD" -> mdStrategy;
            default -> base62Strategy;
        };
    }
}

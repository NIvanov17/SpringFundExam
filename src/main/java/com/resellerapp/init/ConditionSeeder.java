package com.resellerapp.init;

import com.resellerapp.service.ConditionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConditionSeeder implements CommandLineRunner {

    private final ConditionService conditionService;

    public ConditionSeeder(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @Override
    public void run(String... args) throws Exception {
       if(!conditionService.hasConditions()){
            this.conditionService.seedConditions();
       }
    }
}

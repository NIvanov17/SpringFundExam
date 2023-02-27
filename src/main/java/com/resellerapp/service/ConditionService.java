package com.resellerapp.service;

import com.resellerapp.model.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;


    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public boolean hasConditions() {
        if (this.conditionRepository.count() == 0) {
            return false;
        }
        return true;
    }


    public void seedConditions() {
        List<Condition> conditions = Arrays.stream(ConditionName.values())
                .map(Condition::new)
                .collect(Collectors.toList());

        this.conditionRepository.saveAll(conditions);
    }

    public Condition getConditionName(ConditionName conditionName) {
        return this.conditionRepository.findByName(conditionName);
    }
}
package com.resellerapp.repository;

import com.resellerapp.model.Condition;
import com.resellerapp.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition,Long> {
    Condition findByName(ConditionName conditionName);

}

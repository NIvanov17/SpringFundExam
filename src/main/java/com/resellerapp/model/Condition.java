package com.resellerapp.model;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionName name;

    private String description;


    public Condition() {

    }

    public Condition(ConditionName name) {
        this.name = name;
        this.setDescription(name.name());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConditionName getConditionName() {
        return name;
    }

    public void setConditionName(ConditionName conditionName) {
        this.name = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String conditionName) {

        String desc = "";

        switch (conditionName) {
            case "EXCELLENT":
                desc = "In perfect condition";
                break;
            case "GOOD":
                desc = "Some signs of wear and tear or minor defects";
                break;
            case "ACCEPTABLE":
                desc = "The item is fairly worn but continues to function properly";
                break;
        }
        this.description = desc;
    }
}

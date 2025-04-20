package com.sis.inventoryapp.engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RulesEngineV1 implements RulesEngine{

    private List<Rule> ruleList = new ArrayList<>();

    public void execute() {

    }
}

package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instanse = new Model();
    private final Map<String,Double> model;

    public Model() {
        model=new HashMap<>();
    }

    public static Model getInstanse() {
        return instanse;
    }

    public void add(double result){
        model.put("result", result);
    }

    public Map<String, Double> getResult(){
        return model;
    }

}

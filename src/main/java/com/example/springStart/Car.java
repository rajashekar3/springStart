package com.example.springStart;

import org.springframework.stereotype.Component;

@Component
public class Car {

    private final EngineInterface engine;

    public Car(EngineInterface engine) {
        this.engine = engine;
    }


    public void drive() {
        engine.start();
        System.out.println("Car is driving");
    }
}
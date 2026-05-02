package com.example.springStart;
import org.springframework.stereotype.Component;
@Component
public class PetrolEngine implements EngineInterface{
    @Override
    public void start(){
        System.out.println("petrol engine has started");
    }
}
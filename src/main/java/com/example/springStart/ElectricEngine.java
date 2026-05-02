package com.example.springStart;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public class ElectricEngine implements EngineInterface{
    @Override
    public void start(){
        System.out.println(" this is electric ");
    }

}

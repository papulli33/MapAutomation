package org.example.Scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class Scenario {
    public WebDriver driver;
    public JavascriptExecutor js;
    public String name;
    public boolean run() {
        try{
            boolean status = implementation();
            return status;
        }
        catch(Exception e){
            return false;
        }
    }

    abstract protected boolean implementation() throws InterruptedException;

}

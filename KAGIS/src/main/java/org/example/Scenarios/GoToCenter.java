package org.example.Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class GoToCenter extends Scenario {
    public GoToCenter(WebDriver driver)  {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.name = "Merkeze Git";
    }

    @Override
    protected boolean implementation() throws InterruptedException {

        Thread.sleep(2000);

        WebElement setCenterButton = driver.findElement(By.id("set-center"));
        WebElement goCenterButton = driver.findElement(By.id("go-center"));
        WebElement map = driver.findElement(By.id("map"));

        setCenterButton.click();

        Actions action = new Actions(driver);
        Thread.sleep(2000);
        action.moveToElement(map).moveByOffset(200,200).click();
        action.perform();
        Thread.sleep(1000);
        goCenterButton.click();

        Thread.sleep(2000);

        ArrayList<Double> lastClicked = (ArrayList<Double>)  js.executeScript("return window.API.getXYClicks()[0];");
        ArrayList<Double> centerPositionOfMap = (ArrayList<Double>)  js.executeScript("return window.API.getCenter();");

        if(lastClicked.get(0).equals( centerPositionOfMap.get(0)) && lastClicked.get(1).equals( centerPositionOfMap.get(1))){
            return true;
        }
        else{
            return false;
        }
    }
}

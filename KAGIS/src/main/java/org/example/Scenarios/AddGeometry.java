package org.example.Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class AddGeometry extends Scenario{
    public AddGeometry(WebDriver driver)  {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.name = "Nokta Geometrisi Ekle";
    }

    @Override
    protected boolean implementation() throws InterruptedException {

        driver.findElement(By.id("layers")).click();

        driver.findElement(By.cssSelector("body > app-root > app-map-page > div:nth-child(3) > div > button:nth-child(3)")).click();
        Thread.sleep(2000);

        WebElement parentElement = driver.findElement(By.xpath("//*[@id=\"layer-GeoJson Small-2\"]/div"));
        Thread.sleep(2000);
        WebElement childButtonElement = parentElement.findElement(By.tagName("button"));
        childButtonElement.click();

        Thread.sleep(4000);

        ArrayList<ArrayList<ArrayList<Long>>> geometryXY = (ArrayList<ArrayList<ArrayList<Long>>>) js.executeScript("return window.API.getActiveLayer().getSource().getFeatures().map(x=>x.getGeometry().getCoordinates());");
        driver.findElement(By.id("layers")).click();
        driver.findElement(By.id("reset-layers")).click();
        if (geometryXY.size() == 0) {
            System.out.println("Geometri bulunamadı.");
            return false;
        }
        for (int i = 0; i < geometryXY.size(); i++) {
            System.out.println("Nokta "+i+"=> X:"+geometryXY.get(i).get(0).get(0) +", Y:"+geometryXY.get(i).get(0).get(1));
        }
        return true;
    }
}

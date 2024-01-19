package org.example.Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

public class BasemapToFront extends Scenario{
    public BasemapToFront(WebDriver driver)  {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.name = "Altlık Haritayı Öne Getir";
    }
    @Override
    protected boolean implementation() throws InterruptedException{

        driver.findElement(By.id("layers")).click();

        Thread.sleep(2000);

        WebElement parentElement = driver.findElement(By.xpath("//*[@id=\"layer-Esri Topographic Layer-1\"]/div"));
        WebElement childButtonElement = parentElement.findElement(By.tagName("button"));
        childButtonElement.click();

        Thread.sleep(2000);

        String dataFromConsole = (String) js.executeScript("return window.API.getActiveLayer().values_.source.urls[0];");

        String expectedData = "https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}";

        Thread.sleep(3000);

        driver.findElement(By.id("reset-layers")).click();

        if (!dataFromConsole.equals(expectedData)) {
            System.out.println("Hata: Beklenen veri " + expectedData + ", ancak alınan veri " + dataFromConsole);
            return false;
        } else {
            return true;
        }
    }


}

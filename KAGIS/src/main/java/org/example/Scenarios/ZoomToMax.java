package org.example.Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class ZoomToMax extends Scenario {
    public ZoomToMax(WebDriver driver)  {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.name = "Maksimum Zoom Yap";
    }

    @Override
    protected boolean implementation() throws InterruptedException{
        Thread.sleep(2000);
        Long firstZoomLevel = (Long) js.executeScript("return window.API.getZoom();");

        WebElement zoomInButton =  driver.findElement(By.cssSelector("#map > div > div.ol-overlaycontainer-stopevent > div.ol-zoom.ol-unselectable.ol-control > button.ol-zoom-in"));
        WebElement zoomOutButton =  driver.findElement(By.cssSelector("#map > div > div.ol-overlaycontainer-stopevent > div.ol-zoom.ol-unselectable.ol-control > button.ol-zoom-out"));
        for (int i = 0; i < 10; i++) {
            zoomInButton.click();
            Thread.sleep(500);
        }
        Thread.sleep(4000);

        Long secondZoomLevel = (Long)  js.executeScript("return window.API.getZoom();");

        for (int i = 0; i < 10; i++) {
            zoomOutButton.click();
            Thread.sleep(500);
        }
        if (firstZoomLevel < secondZoomLevel) {
            return true;
        } else {
            System.out.println("Hata: İlk zoom seviyesi ikinci zoom seviyesinden büyük.");
            return false;
        }
    }
}

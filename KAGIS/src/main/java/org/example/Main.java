package org.example;

import org.example.Scenarios.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\sude.yildirim\\Desktop\\Notes\\KAGIS\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://dincer-ince.github.io/map-automation-poc/");


        Scenario[] scenarios = {
                new BasemapToFront(driver),
                new ZoomToMax(driver),
                new AddGeometry(driver),
                new GoToCenter(driver)
        };

        Scanner input = new Scanner(System.in);

        while(true){
            driver.manage().window().minimize();
            System.out.println("___________________________________________________________");
            System.out.println("Senaryo Seç");
            for (int i = 1; i < scenarios.length+1; i++) {
                System.out.println(i+": "+ scenarios[i-1].name);
            }
            int selection = input.nextInt()-1;
            if(selection <-1 || selection>= scenarios.length){
                System.out.println("Out of Range");
                continue;
            }

            System.out.println("Senaryo Başlatılıyor => " + scenarios[selection].name+"\n");
            driver.manage().window().maximize();
            Thread.sleep(250);
            boolean status = scenarios[selection].run();
            Thread.sleep(250);
            if(status){
                System.out.println(scenarios[selection].name+" ------->BAŞARILI");
            }
            else{
                System.out.println(scenarios[selection].name+" ------->BAŞARISIZ");
            }
        }

    }

}
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class practiceCalender {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();

        //initializing explicit wait
        WebDriverWait EX_Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //go to website
        driver.get("https://www.makemytrip.com/");
        //if it opens ad run the commented code


        EX_Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='webklipper-publisher-widget-container-notification-frame']")));
        // identify the frame
       WebElement frame = driver.findElement(By.xpath("//*[@id='webklipper-publisher-widget-container-notification-frame']"));
       driver.switchTo().frame(frame);
        EX_Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='wewidgeticon we_close']")));
       driver.findElement(By.xpath("//*[@class='wewidgeticon we_close']")).click();


       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //open calender
        driver.findElement(By.xpath("//*[@for='departure']")).click();
        WebElement date = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"));

        // split the text
        String[] text = date.getText().split(" ");
        String current_month = text[0];
        int current_year = Integer.parseInt(text[1]);
        System.out.println(current_year + current_month);
        int total_number_of_clicks = number_of_clicks(current_month,current_year, "October", 2024);


        //move to next month
        EX_Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Next Month']")));
        driver.findElement(By.xpath("//*[@aria-label='Next Month']")).click();

        for(int i =0;i < total_number_of_clicks;i++){
            EX_Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Next Month']")));
            driver.findElement(By.xpath("//*[@aria-label='Next Month']")).click();
        }






    }
    public static int number_of_clicks(String current_month, int current_year, String desired_month, int desired_year){
        List<String> months = Arrays.asList("January","February","March","April","MAY",
                "June","July","August","September","October","November","December");
        List<Integer> years = Arrays.asList(2024, 2025,2026,2027, 2028,2029,2030);


        // get the index of the current month
        int index_ofCurrent_month = 0;
        for(int i = 0; i < months.size() ; i++){
            if(months.get(i).equalsIgnoreCase(current_month)){
                index_ofCurrent_month = i;
                break;
            }
        }
        System.out.println("index of current month " + index_ofCurrent_month);



        //get in index of desired month
        int index_of_desired_month = 0;
        for(int i = 0; i < months.size() ; i++){
            if(months.get(i).equalsIgnoreCase(desired_month)){
                index_of_desired_month = i;
                break;
            }
        }
        System.out.println("index of desired month" +index_of_desired_month);

        //difference between Months
        int diff = index_ofCurrent_month - index_of_desired_month ;
        // remove the minus sign
        if (diff < 0 ){
            diff *= -1 ;
        }
        System.out.println("number of click in months "+diff);


        //index of current year

        int index_ofCurrent_year = 0;
        for(int i = 0; i < years.size() ; i++){
            if(years.get(i).equals(current_year)){
                index_ofCurrent_year = i;
                break;
            }
        }
        System.out.println("index of current year" +index_ofCurrent_year);

        int index_ofDesired_year = 0;
        for(int i = 0; i < years.size() ; i++){
            if(years.get(i).equals(desired_year)){
                index_ofDesired_year = i;
                break;
            }
        }
        System.out.println("index of desired year" +index_ofDesired_year);

        int diff_years = index_ofDesired_year - index_ofCurrent_year;
        if(diff_years < 0){
            diff_years *=-1;
        }
        System.out.println("difference of years " + diff_years);

        // total number of clicks

        int count_of_years =0;
        for (int i = 0 ; i < diff_years;i++){
            count_of_years += 12;
        }
        System.out.println( "count of years" +count_of_years);
        int total = diff+count_of_years;
        System.out.println("total number of clicks   " + total);
        return total;
    }

}

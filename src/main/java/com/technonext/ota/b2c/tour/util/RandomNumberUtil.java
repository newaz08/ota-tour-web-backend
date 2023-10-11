package com.technonext.ota.b2c.tour.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomNumberUtil {
    public static String generateInquiryNumber(){

        String dateMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        Random random = new Random();
        int min = 10000;
        int max = 99999;
        String randomNumber  = String.valueOf(random.nextInt(max - min + 1) + min);

        return  "T" + "PI" + dateMonthYear + randomNumber;
    }
}

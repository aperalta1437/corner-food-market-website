package com.cornerfoodmarketwebsite;

import java.util.Date;

public class ComcastInterviewAssignment {
    public static void main() {

    }

    public int getYearDayNumber(String strDate) {
        int firstDash;

        Date date = new Date(strDate);

        int monthNumber = date.getMonth();
        int year = date.getYear();
        int totalYearDays = 0;

        for (int index = 1; index < monthNumber; index ++) {
            switch (index) {
                case 1:
                    totalYearDays += 31;
                    break;
                case 2:
                    if ((year % 4) == 0) {
                        totalYearDays += 29;
                    } else {
                        totalYearDays += 28;
                    }
                    break;
                case 3:
                    totalYearDays += 31;
            }
        }

        return totalYearDays;
    }
}

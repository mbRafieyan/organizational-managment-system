package com.util;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import java.util.HashMap;
import java.util.Map;

public class Convertor {

    public static String persianCharacterToEnglish(String input) {

        if (input != null && !input.equals("")) {

            String resultString = "???";
            char[] inputArray = input.toCharArray();
            char character;
            int i = 0;
            while (i < inputArray.length) {
                int code = (int) inputArray[i];
                switch (code) {
                    case 1776:
                        character = (char) 48;
                        inputArray[i] = character;
                        break;
                    case 1777:
                        character = (char) 49;
                        inputArray[i] = character;
                        break;
                    case 1778:
                        character = (char) 50;
                        inputArray[i] = character;
                        break;
                    case 1779:
                        character = (char) 51;
                        inputArray[i] = character;
                        break;
                    case 1780:
                        character = (char) 52;
                        inputArray[i] = character;
                        break;
                    case 1781:
                        character = (char) 53;
                        inputArray[i] = character;
                        break;
                    case 1782:
                        character = (char) 54;
                        inputArray[i] = character;
                        break;
                    case 1783:
                        character = (char) 55;
                        inputArray[i] = character;
                        break;
                    case 1784:
                        character = (char) 56;
                        inputArray[i] = character;
                        break;
                    case 1785:
                        character = (char) 57;
                        inputArray[i] = character;
                        break;
                }
                i++;
            }
            resultString = new String(inputArray);
            return resultString;
        }
        return input;
    }

    public static String persianDateToGregorian(String date) {

        Map<String, Integer> dateMap = spliteDate(date);

        ULocale persianLocal = new ULocale("fa@calendar=persian");
        ULocale gregorianLocal = new ULocale("en@calendar=gregorian");

        Calendar persiancal = Calendar.getInstance(persianLocal);
        persiancal.clear();
        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        int month = dateMap.get("month");
        persiancal.set(dateMap.get("year"), --month, dateMap.get("day"), dateMap.get("hour"), dateMap.get("minute"), dateMap.get("second"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", gregorianLocal);
        return sdf.format(persiancal.getTime());
    }

    public static String gregorianDateToPersian(String date) {

        Map<String, Integer> dateMap = spliteDate(date);

        ULocale persianLocal = new ULocale("fa@calendar=persian");
        ULocale gregorianLocal = new ULocale("en@calendar=gregorian");

        Calendar gregorianCal = Calendar.getInstance(gregorianLocal);
        gregorianCal.setLenient(false);
        gregorianCal.clear();
        gregorianCal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        int month = dateMap.get("month");
        gregorianCal.set(dateMap.get("year"), --month, dateMap.get("day"), dateMap.get("hour"), dateMap.get("minute"), dateMap.get("second"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", persianLocal);
        return sdf.format(gregorianCal.getTime());
    }

    private static Map<String, Integer> spliteDate(String date) {

        String[] dateTimeArray = date.split(" ");
        String dateStr = dateTimeArray[0];
        String timeStr = dateTimeArray[1];

        String[] dateArray = dateStr.split("-");
        String[] timeArray = timeStr.split(":");

        Map<String, Integer> elementDateMap = new HashMap();

        elementDateMap.put("year", Integer.parseInt(dateArray[0]));
        elementDateMap.put("month", Integer.parseInt(dateArray[1]));
        elementDateMap.put("day", Integer.parseInt(dateArray[2]));
        elementDateMap.put("hour", Integer.parseInt(timeArray[0]));
        elementDateMap.put("minute", Integer.parseInt(timeArray[1]));
        elementDateMap.put("second", Integer.parseInt(timeArray[2]));

        return elementDateMap;
    }
}

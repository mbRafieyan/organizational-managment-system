package com.util;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convertor {

    public static String persianCharacterToEnglish(String input) {

        if (input != null && !input.equals("")  ) {
            // string used for recording results
            String resultString = "???";
            // input an_input_string to char array
            char[] inputArray = input.toCharArray();
            char character;
            int i = 0;
            while (i < inputArray.length) {
                int code = (int) inputArray[i];
                switch (code) {
                    case 1610:
                        character = (char) 1740;
                        inputArray[i] = character;
                        break;
                    case 1574:
                        character = (char) 1740;
                        inputArray[i] = character;
                        break;
                    case 1569:
                        character = (char) 1740;
                        inputArray[i] = character;
                        break;
                    case 1571:
                        character = (char) 1575;
                        inputArray[i] = character;
                        break;
                    case 1603:
                        character = (char) 1705;
                        inputArray[i] = character;
                        break;
                    case 48:
                        character = (char) 1632;
                        inputArray[i] = character;
                        break;
                    case 49:
                        character = (char) 1633;
                        inputArray[i] = character;
                        break;
                    case 50:
                        character = (char) 1634;
                        inputArray[i] = character;
                        break;
                    case 51:
                        character = (char) 1635;
                        inputArray[i] = character;
                        break;
                    case 52:
                        character = (char) 1636;
                        inputArray[i] = character;
                        break;
                    case 53:
                        character = (char) 1637;
                        inputArray[i] = character;
                        break;
                    case 54:
                        character = (char) 1638;
                        inputArray[i] = character;
                        break;
                    case 55:
                        character = (char) 1639;
                        inputArray[i] = character;
                        break;
                    case 56:
                        character = (char) 1640;
                        inputArray[i] = character;
                        break;
                    case 57:
                        character = (char) 1641;
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

    public static String persianDateToGregorian(String date){

        Map<String, Integer> dateMap = spliteDate(date);

        ULocale persianLocal = new ULocale("fa@calendar=persian");
        ULocale gregorianLocal = new ULocale("en@calendar=gregorian");

        Calendar persiancal = Calendar.getInstance(persianLocal);
        persiancal.clear();
        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        int month = dateMap.get("month");
        persiancal.set(dateMap.get("year"), --month, dateMap.get("day") , dateMap.get("hour"), dateMap.get("minute"), dateMap.get("second"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", gregorianLocal);
        return sdf.format(persiancal.getTime());
    }

    public static String gregorianDateToPersian(String date){

        Map<String, Integer> dateMap = spliteDate(date);

        ULocale persianLocal = new ULocale("fa@calendar=persian");
        ULocale gregorianLocal = new ULocale("en@calendar=gregorian");

        Calendar gregorianCal = Calendar.getInstance(gregorianLocal);
        gregorianCal.setLenient(false);
        gregorianCal.clear();
        gregorianCal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        int month = dateMap.get("month");
        gregorianCal.set(dateMap.get("year"), --month, dateMap.get("day") , dateMap.get("hour"), dateMap.get("minute"), dateMap.get("second"));
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", persianLocal);
        return sdf.format(gregorianCal.getTime());
    }

    private static Map<String, Integer> spliteDate(String date){

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

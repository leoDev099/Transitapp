package com.example.leonardo.transitapp.utils;

/**
 * Created by leonardo on 29/10/17.
 */
import com.example.leonardo.transitapp.model.Segment;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class AdapterDuties {

    public String getPriceAmount(Long amount){
        String amountString;
        amountString = Long.toString(amount);

        return amountString;
    }

    public String getPriceCurrency(String currency){
        String currencySymbol = null;
        String euro ="EUR";

        if(currency.equals(euro)){
            currencySymbol = "€";
        }
        return currencySymbol;
    }

    public List<String> getRouteTimeInfo(List<Segment> segments){

        int firstElementPos = 0;
        int lastSegmentPos;
        int lastStopPos;
        String initialDate = null;
        String finalDate = null;

        lastSegmentPos = segments.size();

        if(lastSegmentPos > 0){
            initialDate = segments.get(firstElementPos).getStops().get(firstElementPos).getDatetime();
            lastStopPos = segments.get(lastSegmentPos-1).getStops().size();
            if(lastStopPos > 0){            //to avoid nullPointerException
                finalDate = segments.get(lastSegmentPos-1).getStops().get(lastStopPos-1).getDatetime();
            }
        }

        return getHoursMinsTotal(initialDate, finalDate);

    }

    public List<String> getHoursMinsTotal(String strInitialDate, String strFinalDate){

        String strTotalTime;
        int hoursInBetween;
        int minsInBetween;
        String strInitialFormated;
        String strFinalFromated;
        List<String> dataTime = new ArrayList<String>();

        String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";

        DateTimeFormatter formatterInput = DateTimeFormat.forPattern(pattern);
        DateTime initialTime= formatterInput.parseDateTime(strInitialDate);
        DateTime finalTime= formatterInput.parseDateTime(strFinalDate);

        strInitialFormated = initialTime.toString("HH:mm");
        strFinalFromated = finalTime.toString("HH:mm");

        Hours hoursInBetweenUnformated = Hours.hoursBetween(initialTime,finalTime);
        Minutes minutesInBetweenUnfromated = Minutes.minutesBetween(initialTime,finalTime);

        hoursInBetween = hoursInBetweenUnformated.getHours();
        minsInBetween = minutesInBetweenUnfromated.getMinutes();

        strTotalTime = formatTotalOutput(hoursInBetween,minsInBetween);

        dataTime.add(strTotalTime);
        dataTime.add(strInitialFormated);
        dataTime.add(strFinalFromated);

        return dataTime;
    }

    public String formatTotalOutput(int hours, int mins){

        String strTotalTime;

        if(hours == 0 && mins == 0){      //if time < 1 -> aprox 1 min
            mins = 1;
            strTotalTime = Integer.toString(mins) + " min";
        }else if(hours == 0 && mins != 0){
            strTotalTime = (Integer.toString(mins)) + " mins";
        }else if (hours != 0 && mins == 0){
            strTotalTime = Integer.toString(hours) + "h";
        }else{
            strTotalTime = Integer.toString(hours)
                    + "h " + Integer.toString(mins) + "m";
        }

        return strTotalTime;
    }
}

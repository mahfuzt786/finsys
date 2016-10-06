package finsys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

    private static Date getCurrentDate() {
        return new Date();
    }

    public static String getTodaysDateInString(String dateFormat) {
        if (dateFormat == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(getCurrentDate());
        return currentDate;
    }

    public static boolean isDateBeforeToday(String date, String dateFormat) throws Exception {
        boolean isBefore = false;
        if (date == null || dateFormat == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        String currentDate = getTodaysDateInString(dateFormat);
        if (compareDate(dateFormat, date, currentDate) == -1) {
            isBefore = true;
        }
        return isBefore;
    }

    public static int compareDate(String dateFormat, String date1, String date2) throws Exception {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        Date firstDate = UtilDate.convertStringToDate(dateFormat, date1);
        Date secondDate = UtilDate.convertStringToDate(dateFormat, date2);
        int result = firstDate.compareTo(secondDate);
        return result;

    }

    public static Date convertStringToDate(String format, String dateString) throws ParseException {
        if ((format != null && format.trim().length() == 0) || (dateString != null && dateString.trim().length() == 0)) {
            throw new IllegalArgumentException("null arguments passed");
        }
        return new SimpleDateFormat(format).parse(dateString);
    }

    public static java.sql.Date convertStringToSqlDate(String format, String dateString) throws ParseException {
        if ((format != null && format.trim().length() == 0) || (dateString != null && dateString.trim().length() == 0)) {
            throw new IllegalArgumentException("null arguments passed");
        }
        return new java.sql.Date(new SimpleDateFormat(format).parse(dateString).getTime());
    }
    
   public String getFormattedDate(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        //October 1st, 2015 (Thu 02:06 PM GMT-12:00)
        //"MMMM d'st', yyyy (E hh:mm a z)"
        int day=cal.get(Calendar.DATE);

        switch (day % 10) {
            case 1:  
                return new SimpleDateFormat("MMMM d'st', yyyy (E hh:mm a)").format(date);
            case 2:  
                return new SimpleDateFormat("MMMM d'nd', yyyy (E hh:mm a)").format(date);
            case 3:  
                return new SimpleDateFormat("MMMM d'rd', yyyy (E hh:mm a)").format(date);
            default: 
                return new SimpleDateFormat("MMMM d'th', yyyy (E hh:mm a)").format(date);
        }        
   }
   
   public static String diffDays(String dateFormat, String date1, String date2) throws Exception {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        Date firstDate = UtilDate.convertStringToDate(dateFormat, date1);
        Date secondDate = UtilDate.convertStringToDate(dateFormat, date2);
        long diff = firstDate.getTime() - secondDate.getTime();
        String d="";
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        if(diffDays>0){
            d+=diffDays;
        }
        diff -= diffDays * (24 * 60 * 60 * 1000);
        return d;
    }
    
}

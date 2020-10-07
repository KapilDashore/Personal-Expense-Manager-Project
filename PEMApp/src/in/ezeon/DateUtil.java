
package in.ezeon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    public static final String MONTHS []={ "january","febuary","march","April","May","june","july","August","September","October","November","December"};
    public static Date StringToDate(String dateAsString){
        try {
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(dateAsString);
        } catch (ParseException ex) {
            ex.printStackTrace();
           return null;
        }
        
    }
     public static  String dateToString(Date date){
        
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);
       
        
    }
     
    public static String getYearAndMonth(Date date){
        SimpleDateFormat df= new SimpleDateFormat("yyyy,MM");//Ex.2016,01;2016,02...
        return df.format(date);
    }
     
    public static Integer getYear(Date date){
        SimpleDateFormat df= new SimpleDateFormat("yyyy");//Ex.2016,01;2016,02...
        return new Integer(df.format(date));
    }
        
    public static String getMonthName(Integer monthNo){
        return MONTHS [monthNo-1];
        
    
    }
}
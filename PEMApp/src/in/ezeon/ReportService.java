
package in.ezeon;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 * @author asus
 */
public class ReportService {
   private Repository repo = Repository.getRepository();
   
   public Map<String,Float> calculateMonthlyTotal(){
        Map<String,Float> m= new TreeMap();
         for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            String yearMonth=DateUtil.getYearAndMonth(expDate);
            if(m.containsKey(yearMonth)){
               Float total= m.get(yearMonth);
               total=total+exp.getAmount();
               m.put(yearMonth, total);
            }else{
                
                m.put(yearMonth,exp.getAmount());
            }
            
        } 
         return m;
        
   }
       
   public Map<Integer,Float> calculateyearlyTotal(){
        Map<Integer,Float> m= new TreeMap();
         for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            Integer year=DateUtil.getYear(expDate);
            //String yearMonth=DateUtil.getYearAndMonth(expDate);
            if(m.containsKey(year)){
                //when expense is already presentfor a year
               Float total= m.get(year);
               total=total+exp.getAmount();
               m.put(year, total);//new total:replace old total
            }else{
                //this year is not yet added so add here
                m.put(year,exp.getAmount());
            }
            
        } 
         return m;//return final result as map
        
   }
             
   public Map<String,Float> calculateCategoriedTotal(){
        Map<String,Float> m= new TreeMap();
         for(Expense exp : repo.expList){
           Long categoryId=exp.getCategoryId();
           String catName=this.getCategoryNameById(categoryId);
           
            //String yearMonth=DateUtil.getYearAndMonth(expDate);
            if(m.containsKey(catName)){
                //when expense is already presentfor a year
               Float total= m.get(catName);
               total=total+exp.getAmount();
               m.put(catName, total);//new total:replace old total
            }else{
                //this year is not yet added so add here
                m.put(catName,exp.getAmount());
            }
            
        } 
         return m;//return final result as map
        
   }
     String getCategoryNameById(Long categoryId) {
        
        for (Category c : repo.catList) {
            if(c.getCategoryId()==categoryId){
                return c.getName();
            }
        }
        return null;//no such  category id present
     }  
       
}
       
          

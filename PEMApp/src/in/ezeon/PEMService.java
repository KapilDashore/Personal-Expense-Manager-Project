
package in.ezeon;
import java.io.IOException;
import java.util.Date;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author asus
 */
public class PEMService {
    Repository repo= Repository.getRepository();
    ReportService reportService=new ReportService();
    
    private Scanner in = new Scanner(System.in);
    private int choice;
    
    PEMService(){
        prepareSampleData();//todo
        
    }
    
    public void showMenu(){
        while (true) {
            printMenu();
            switch(choice){
                case 1:
                    onAddCategory();
                    pressAnyKeyToContinue();
                    break;
                case 2:
                    onCategoryList();
                    pressAnyKeyToContinue();
                    break;
                case 3:
                    onExpenseEntry();
                    pressAnyKeyToContinue();
                   break;
                case 4:
                    onExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 5:
                    onMonthlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 6:
                    onYearlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 7:
                    onCategorizedExprenseList();
                    pressAnyKeyToContinue();
                    break;
                case 0:
                    onExit();
                    break;
            }
        }
    }
    
    
    public void printMenu(){
        System.out.println("-------PEM MENU--------");
        System.out.println("1. Add Category");
        System.out.println("2. Category List");
        System.out.println("3. Expense Entry");
        System.out.println("4. Expense List");
        System.out.println("5. MonthlyExpense List");
        System.out.println("6. Yearly Expense List");
        System.out.println("7. Categorized Expense List");
        System.out.println("0. Exit");
        System.out.println("-------------------------------");
        System.out.print("Enter your Choice: ");
        choice=in.nextInt();
    
    
    }
    public void pressAnyKeyToContinue(){
        try{
            System.out.println("press any key to continue...");
            System.in.read();
        } catch(IOException ex) {
          }
    }

     private void onAddCategory() {
        in.nextLine();
       System.out.print("Enter Category Name : ");
       String catName=in.nextLine();
       Category cat= new Category(catName);
       repo.catList.add(cat);
        System.out.println("success: Category Added");
    }

    private void onCategoryList() {
       System.out.println("Category Listeing");
       List<Category> clist=repo.catList;
       for(int i =0;i<clist.size();i++){
           Category c= clist.get(i);
           System.out.println((i+1)+" "+c.getName()+" "+c.getCategoryId());
       }
    }

    private void onExpenseEntry() {
        System.out.println("Expense Entry Listing...");
        onCategoryList();
        System.out.print("choose Category : ");
        int catChoice=in.nextInt();
        Category selectedCat= repo.catList.get(catChoice-1);
        
        System.out.print("Enter Amount :");
        float amount = in.nextFloat();
        
        
        System.out.print("Enter remark: ");
        in.nextLine();
        String remark=in.nextLine();
        
        System.out.print("Enter date(DD/MM/YYYY): ");
        String dateAsString=in.nextLine();
        Date date=DateUtil.StringToDate(dateAsString);
        
        Expense exp=new Expense();
        exp.setCategoryId(selectedCat.getCategoryId());
        exp.setAmount(amount);
        exp.setRemak(remark);
        exp.setDate(date);
         
         repo.expList.add(exp);
         System.out.println("success: Expense Added");
         
         
        
    }

    private void onExpenseList() {
        System.out.println("Expense Listing");
        List<Expense> expList=repo.expList;
        for(int i=0;i<expList.size();i++){
            Expense exp=expList.get(i);
            String catName=reportService.getCategoryNameById(exp.getCategoryId());
            String dateString=DateUtil.dateToString(exp.getDate());
            System.out.println((i+1)+", "+catName+", "+exp.getAmount()+", "+exp.getRemak()+", "+dateString);
        }
    }

    private void onMonthlyExpenseList() {
       System.out.println("Monthly Expense total");
       Map<String,Float> resultMap =reportService.calculateMonthlyTotal();
       Set<String> keys=resultMap.keySet();
       for (String yearMonth:keys){
           String[] arr= yearMonth.split(",");
           String year =arr[0];
           Integer monthNo= new Integer(arr[1]);
           String monthName=DateUtil.getMonthName(monthNo);
           System.out.println(year+"  "+monthName+" :"+resultMap.get(yearMonth));
       }
    }

    private void onYearlyExpenseList() {
        System.out.println("yearly Expense Total..");
       Map<Integer,Float>resultMap = reportService.calculateyearlyTotal();
       Set<Integer> years=resultMap.keySet();
       Float total = 0.F;
       for (Integer year : years){
           Float exp=resultMap.get(year);
           total=total+exp;
           System.out.println(year+" : "+resultMap.get(year));
       }
        System.out.println("----------------------------");
        System.out.println("TOtal Expenses(INR)"+total);
    }

    private void onCategorizedExprenseList() {
         System.out.println("Categori wise Expense  Listing");
         Map<String,Float> resultMap=reportService.calculateCategoriedTotal();
         Set<String>categories =resultMap.keySet();
         Float netTotal = 0.0F;
         for(String categoryName : categories){
             Float catWiseTotal=resultMap.get(categoryName);
             netTotal= netTotal+catWiseTotal;
             System.out.println(categoryName+" : "+resultMap.get(categoryName));
             
         }
            System.out.println("----------------------");
            System.out.println("net Total : "+ netTotal);
    }
    
    private void onExit() {
         System.exit(0);
    }

   public void prepareSampleData(){
       Category catParty= new Category("Party");
       delay();
       Category catShopping= new Category("Shopping");
       delay();
       Category catGift= new Category("Gift");
       
       repo.catList.add(catParty);
       repo.catList.add(catShopping);
       repo.catList.add(catGift);
       
       //jan-2016
       Expense e1=new Expense(catParty.getCategoryId(),1000.0F,DateUtil.StringToDate("01/01/2016"),"good");
       delay();
       //feb-2016
       Expense e2=new Expense(catShopping.getCategoryId(),2000.0F,DateUtil.StringToDate("01/02/2016"),"good");
       delay();
       
       Expense e3=new Expense(catGift.getCategoryId(),3000.0F,DateUtil.StringToDate("01/03/2016"),"good");
       delay();
       
       Expense e4=new Expense(catParty.getCategoryId(),3000.0F,DateUtil.StringToDate("01/04/2016"),"good");
       delay();
       
       Expense e5=new Expense(catShopping.getCategoryId(),3000.0F,DateUtil.StringToDate("01/05/2016"),"good");
       delay();
       
       Expense e6=new Expense(catParty.getCategoryId(),9000.0F,DateUtil.StringToDate("01/06/2016"),"good");
       delay();
       
       Expense e7=new Expense(catShopping.getCategoryId(),7000.0F,DateUtil.StringToDate("01/07/2016"),"good");
       delay();
       
       Expense e8=new Expense(catParty.getCategoryId(),8000.0F,DateUtil.StringToDate("01/08/2016"),"good");
       delay();
       
       Expense e9=new Expense(catGift.getCategoryId(),8000.0F,DateUtil.StringToDate("01/08/2016"),"good");
       delay();
       repo.expList.add(e1);
       repo.expList.add(e2);
       repo.expList.add(e3);
       repo.expList.add(e4);
       repo.expList.add(e5);
       repo.expList.add(e6);
       repo.expList.add(e7);
       repo.expList.add(e8);
       repo.expList.add(e9);
   }
       
       
        private void delay(){
           try{
               Thread.sleep(10);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            
               
        }
       
       
        
   }
    
  
}

   


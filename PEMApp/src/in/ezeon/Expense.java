
package in.ezeon;

import java.util.Date;



    
public class Expense {
   private  Long expenseId= System.currentTimeMillis();
   private Long categoryId;//fk
   private Float amount;
   private Date date;
   private String remak;
    String catName;
    
   
   

    public Expense(Long categoryId, Float amount, Date date, String remak) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.remak = remak;
    }

    Expense() {
       
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemak() {
        return remak;
    }

    public void setRemak(String remak) {
        this.remak = remak;
    }

    void setRemark(String remark) {
        
    }
   
   

}
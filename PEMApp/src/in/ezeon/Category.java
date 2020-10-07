
package in.ezeon;

public class Category {
        private long CategoryId=System.currentTimeMillis();
        private String name;
        
        public Category(String name){
            this.name =name;
        }
        
         public Category(Long categoryId,String name){
            this.CategoryId=CategoryId;
            this.name=name;
        }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}



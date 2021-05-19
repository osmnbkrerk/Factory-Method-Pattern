public class ChicagoStyleClamPizza extends Pizza {

     public ChicagoStyleClamPizza() {
        name = "Chicago Style Deep Dish Clam Pizza";
        dough = "Thin Crust Dough";
        sauce = "Chicago Special Pizza Sauce";
        toppings.add("Shredded Mozarella Cheese");
        toppings.add("1-2 Tablespoon Garlic Minced"); 
        toppings.add("Canned Clams"); 
        toppings.add("Parmesan cheese");        
}
  
    @Override
    public void cut(){
    System.out.println("Cutting the pizza into square slices");
    }
    
    @Override
    public void bake(){
        System.out.println("Bake for 30 minutes at 300");
    }
    
}

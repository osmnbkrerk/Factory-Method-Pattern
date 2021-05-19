public class ChicagoStylePepperoniPizza extends Pizza {


    public ChicagoStylePepperoniPizza() {
        name = "Chicago Style Deep Dish Pepperoni Pizza";
        dough = "Normal Dough";
        sauce = "Chicago's Special Tomato Sauce";
        toppings.add("1 teaspoon fresh oregano"); 
        toppings.add("18-20 slices pepperoni");
        toppings.add("12 ounces mozzarella cheese, grated");    
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

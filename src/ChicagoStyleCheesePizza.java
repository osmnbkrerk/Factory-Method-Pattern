public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozarella Cheese"); 
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

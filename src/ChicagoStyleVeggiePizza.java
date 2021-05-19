public class ChicagoStyleVeggiePizza extends Pizza {

     public ChicagoStyleVeggiePizza() {
        name = "Chicago Style Deep Dish Veggie Pizza";
        dough = "Thin Crust Dough";
        sauce = "Chicago's Special Vegan Sauce";
        toppings.add("ChicagoStyle Vegan pepperoni");
        toppings.add("ChicagoStyle Vegan Mozarella Cheese");    
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

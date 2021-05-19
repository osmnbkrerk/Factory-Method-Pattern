public class PizzaTestDrive {
    public static void main(String[] args){
    
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Osman ordered a "+ pizza.getName() + "\n");
        
        pizza = chicagoPizzaStore.orderPizza("pepperoni");
        System.out.println("Bora ordered a "+ pizza.getName() + "\n");
        
    }
}

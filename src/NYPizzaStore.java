public class NYPizzaStore extends PizzaStore {

    
@Override
protected Pizza createPizza(String item) {
if(item.equalsIgnoreCase("cheese")) return new NYStyleCheesePizza();
else if (item.equalsIgnoreCase("veggie")) return new NYStyleVeggiePizza();
else if (item.equalsIgnoreCase("clam")) return new NYStyleClamPizza();
else if (item.equalsIgnoreCase("pepperoni")) return new NYStylePepperoniPizza();
else return null;
}
}


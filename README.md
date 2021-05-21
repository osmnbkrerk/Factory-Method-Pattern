# Factory-Method-Pattern
Factory-Method-Pattern
Factory Method Tasarım Deseni 

Osman Bekir Erik
 

Factory Method Pattern, aynı arayüzü veya soyut sınıfı implement/extend etmiş factory nesnelerinin üretiminden sorumlu tasarım desenidir. Şimdi Design Patterns-O’Reilly(2014) kitabının 187. Sayfasındaki verilen pizza üzerinden sizlere bu konuyu anlatacağım. Öncelikle verilen örneğimizin sınıf diyagramına bakalım. 

 
<img alt="factory method uml diyagrami" src="https://github.com/osmnbkrerk/Factory-Method-Pattern/blob/main/resimler/diyagram1.png">
Netbeans’e sonradan eklenebilen bir plugin olan easyUML’i kullanarak hazırladığım bir diyagram.  Şimdi şu karışık görünen diyagramımızdaki sınıfların arasındaki ilişkilerden, fonksiyonlarından ve özelliklerden kısaca bahsedelim.



















Öncelikle Pizza isimli soyut sınıfımızı oluşturalım. 

import java.util.ArrayList;

public abstract class Pizza {

    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();
    
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
        System.out.println(" " + topping);
            }
        }

    public void bake(){
        System.out.println( "Bake for 25 minutes at 350" );
    }
    
    public void cut() {
        System.out.println( "Cutting the pizza into diagonal slices" );
    }

    public void box() {
        System.out.println( "Place pizza in official PizzaStore box" );
    }

    public String getName(){
        return name;
    }

} 

Pizza soyut sınıfının içerisinde prepare(), bake (), cut(), box() ve getName() fonksiyonlarımız bulunmakta. Fakat şunu da unutmayalım. Her pizzanın malzemeleri, pişirme derecesi, doğranma şekli veya paketlenmesi farklılık gösterebilir. Bunları daha sonra oluşturacağımız pizzalardaki soyut sınıftan gelen metotları extend ederek anlatacağım.

Oluşturacağımız pizzanın adını almak için getName() fonksiyonunu kullandık. Ve prepare() fonksiyonunda lezzetli pizzamızın hazırlanışına geçiyoruz. ArrayList ile pizzamızın üstüne ekstra malzeme eklemek istiyorsak; ki bu isteğe bağlı bir şey. Toppings adlı ArrayList oluşturduk. 











İyi hoş pizzamızı oluşturduk tamam da bunu biz nerede satacağız?  Üstte bahsettiğim üzere her pizzanın yapılış şekli farklıdır. Restorandan restorana değişiklik gösterebilir. Fakat bizim karnımız aç ve pizza yemek istiyorsak herhangi bir yerden sipariş etmek isteriz. Bu yüzden PizzaStore  isimli soyut bir sınıf açalım.

public abstract class PizzaStore {

public Pizza orderPizza(String type){
Pizza pizza;

pizza  =  createPizza(type);

pizza.prepare();
pizza.bake();
pizza.cut();
pizza.box();

return pizza;
}

protected abstract Pizza createPizza(String type);
}

Pizza tipindeki orderPizza adlı metodumuza yemek istediğimiz pizza tipini parametre olarak belirtiyoruz. createPizza adlı metodumuza parametre olarak aldığımız tipi yolluyoruz ve istediğimiz restoran bize onu Create ediyor. Pizzamız gereken işlemlerden geçerek bize paket halinde servis ediliyor.




Şimdi de gelelim hangi dükkandan yemek söyleyeceğimize. Pizzayı X lokantasından da yiyebiliriz Y lokantasından da. Bunlar tercih meselesidir. Oluşturacağımız sınıf PizzaStore’a extend etmelidir. Yani bu da demek oluyor ki oluşturacağımız her restoran varsayılan özelliklere, davranışlara sahip olmalı. 

public class NYPizzaStore extends PizzaStore {
    
@Override
protected Pizza createPizza(String item) {
if(item.equalsIgnoreCase( "cheese" )) return new NYStyleCheesePizza();
else if (item.equalsIgnoreCase( "veggie" )) return new NYStyleVeggiePizza();
else if (item.equalsIgnoreCase( "clam" )) return new NYStyleClamPizza();
else if (item.equalsIgnoreCase( "pepperoni" )) return new NYStylePepperoniPizza();
else return null;
}
	}

PizzaStore adlı soyut sınıftaki createPizza() soyut metodunu override ediyoruz. Çünkü oluşturacağımız restoran kendi usüllerine göre pizzayı hazırlayacak. Fakat burada bir kontrol yapmamız gerekiyor. Müşterimizin ne çeşit pizza istediğini bilmemiz gerekli. Ona göre NYStyle pizzamızı müşterimize göndereceğiz.









Şimdi gelelim NYStylePizzaStore isimli soyut sınıfımızın ürettiği pizzalara.  Oluşturduğumuz her restoran Pizza soyut sınıfından extend etmelidir çünkü içerdiği tüm özellikler Pizza isimli sınıfımızda saklı…

public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza" ;
        dough = "Thin Crust Dough" ;
        sauce = "Marinara Sauce" ;
        toppings.add( "Grated Reggiano Cheese" ); 
}
    }

NYStyleCheesePizza görüldüğü üzere Pizza soyut sınıfımıza extend ediyor. Kendi kurucu metodunda pizzanın ismini, hamur tipini, sosunu set ediyor ve ekstra malzemelerini  Arraylistimize ekliyor.

Bakalım bu NYStylePizzaStore isimli restoranımız başka hangi pizzaları üretiyor…

public class NYStyleClamPizza extends Pizza {
    
    public NYStyleClamPizza() {
        name =  "NY Style Sauce and Clam Pizza" ;
        dough =  "1 pound Fresh Prepared Pizza Dough" ;
        sauce =  "NY Special Pizza Sauce" ;
        toppings.add( "1 Tablespoon Chopped Garlic" ); 
        toppings.add( "Raw Clams" ); 
        toppings.add( "Parmesan cheese" );
        toppings.add( "3 ounces Pecorino Romano Cheese" );
}
	}

public class NYStylePepperoniPizza extends Pizza{
       
    public NYStylePepperoniPizza() {
        name =  "NY Style Sauce and Pepperoni Pizza" ;
        dough =  "1 pound Fresh Prepared Pizza Dough" ;
        sauce =  "1 can (8 oz each) Tomato Sauce-No Salt Added" ;
        toppings.add(  "1/4 cup grated Parmesan cheese" ) ; 
        toppings.add( "1/3 cup sliced pepperoni" );
        toppings.add( "1 cup shredded part-skim Mozzarella Cheese" );
}
	}

public class NYStyleVeggiePizza extends Pizza {
    
    public NYStyleVeggiePizza() {
        name =  "NY Style Sauce and Veggie Pizza" ;
        dough =  "Normal Crust Dough" ;
        sauce =  "Vegan Pizza Sauce" ;
        toppings.add( "Vegan Mozarella Cheese" ); 
}
    }





Başka bir restoran daha mı açsak acaba? Bu restoran sanki geleneksel pizza hazırlama yöntemlerini kullanıyor gibi. Bilmem fark ettiniz mi fakat bize daha farklı pişirme derecesiyle pizza hazırlayan, daha farklı şekiller ile pizzayı doğrayan farklı bir restoran lazım. Hadi o zaman ChicagoStylePizzaStore adlı sınıfımızı oluşturalım.


public class ChicagoPizzaStore extends PizzaStore {
    
@Override
protected Pizza createPizza(String item) {
if(item.equalsIgnoreCase( "cheese" )) return new ChicagoStyleCheesePizza();
else if (item.equalsIgnoreCase( "veggie" )) return new ChicagoStyleVeggiePizza();
else if (item.equalsIgnoreCase( "clam" )) return new ChicagoStyleClamPizza();
else if (item.equalsIgnoreCase( "pepperoni" )) return new ChicagoStylePepperoniPizza();
else return null;
	}
}

Bu restoran da aynı şekilde NYStylePizzaStore gibi daha doğrusu her pizzacının sahip olması gereken fonksiyona sahip. Mesela pizzayı oluşturmak gibi :)…

Peki nedir bu restoranı farklı kılan? Biraz ürettiği pizzalara da bakalım.

public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name =  "Chicago Style Deep Dish Cheese Pizza" ;
        dough =  "Extra Thick Dough" ;
        sauce =  "Plum Tomato Sauce" ;
        toppings.add( "Shredded Mozarella Cheese" ); 
}
  
    @Override
    public void cut(){
    System.out.println( "Cutting the pizza into square slices" );
    }
    
    @Override
    public void bake(){
        System.out.println( "Bake for 30 minutes at 300" );
    }
    
}

Sanki bir şeyler farklı. Yukarıda da söylediğim gibi pişirme yöntemi Override edilerek değiştirilmiş ve doğrama şekli de değiştirilmiş. NYStylePizzaStore klasik pizza dilimi şeklinde doğrar iken bu restoran kare şeklinde doğruyor. Ve ayrıca ismi, hamur tipi, sosu ve eklediği ekstra malzemeler de değişmiş. Kurucu metotta lezzetli Chicago usülü pizzamızın malzemelerini set ediyoruz.










 ChicagoStylePizzaStore adlı restoranımız başka hangi pizzaları üretiyor onlara da bir göz atalım.


public class ChicagoStyleClamPizza extends Pizza {

     public ChicagoStyleClamPizza() {
        name =  "Chicago Style Deep Dish Clam Pizza" ;
        dough =  "Thin Crust Dough" ;
        sauce =  "Chicago Special Pizza Sauce" ;
        toppings.add( "Shredded Mozarella Cheese" );
        toppings.add( "1-2 Tablespoon Garlic Minced" ); 
        toppings.add( "Canned Clams" ); 
        toppings.add( "Parmesan cheese" );        
}
  
    @Override
    public void cut(){
    System.out.println( "Cutting the pizza into square slices" );
    }
    
    @Override
    public void bake(){
        System.out.println( "Bake for 30 minutes at 300" );
    }
    
}


public class ChicagoStylePepperoniPizza extends Pizza {

    public ChicagoStylePepperoniPizza() {
        name = "Chicago Style Deep Dish Pepperoni Pizza" ;
        dough = "Normal Dough" ;
        sauce = "Chicago's Special Tomato Sauce" ;
        toppings.add( "1 teaspoon fresh oregano" ); 
        toppings.add( "18-20 slices pepperoni" );
        toppings.add( "12 ounces mozzarella cheese, grated" );    
}
  
    @Override
    public void cut(){
    System.out.println( "Cutting the pizza into square slices" );
    }
    
    @Override
    public void bake(){
        System.out.println( "Bake for 30 minutes at 300" );
    }
    
}









public class ChicagoStyleVeggiePizza extends Pizza {

     public ChicagoStyleVeggiePizza() {
        name = "Chicago Style Deep Dish Veggie Pizza" ;
        dough = "Thin Crust Dough" ;
        sauce = "Chicago's Special Vegan Sauce" ;
        toppings.add( "ChicagoStyle Vegan pepperoni" );
        toppings.add( "ChicagoStyle Vegan Mozarella Cheese" );    
}
  
    @Override
    public void cut(){
    System.out.println( "Cutting the pizza into square slices" );
    }
    
    @Override
    public void bake(){
        System.out.println( "Bake for 30 minutes at 300" );
    }
}


Evet sonuç olarak 2 rakip restoranı oluşturduktan sonra bunlardan lezzetli pizzalar söylemenin vakti geldi. O zaman bir test class oluşturalım.

public class PizzaTestDrive {
    public static void main(String[] args){
    
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        
        Pizza pizza = nyPizzaStore.orderPizza( "cheese" );
        System.out.println( "Osman ordered a " + pizza.getName() +  "\n" );
        
        pizza = chicagoPizzaStore.orderPizza( "pepperoni" );
        System.out.println( "Bora ordered a " + pizza.getName() +  "\n" );
        
    }
}

PizzaTestDrive adlı test class’ımızda NYPizzaStore’dan nyPizzaStore adlı nesne oluşturuyoruz. Ve aynı şekilde ChicagoPizzaStore’dan da chicagoPizzaStore adlı nesne oluşturuyoruz. Bunları oluşturmamızın sebebi farklı restoranlara erişip onlardan pizza siparişi vermek.

Pizza soyut sınıfımızdan pizza adlı nesneye daha önceden oluşturduğumuz nyPizzaStore adlı nesnedeki orderPizza fonksiyonundan gelen değeri döndürüyoruz.
Ve bu da demek oluyor ki NYPizzaStore’dan lezzetli bir peynirli pizza sipariş etmişiz.

Bora hocamız ise chicagoPizzaStore adlı restorandan pepperonili bir pizza siparişi veriyor.









Programımızın çıktısı ise şu şekilde; 

Preparing NY Style Sauce and Cheese Pizza
Tossing dough...
Adding sauce...
Adding toppings: 
 Grated Reggiano Cheese
Bake for 25 minutes at 350
Cutting the pizza into diagonal slices
Place pizza in official PizzaStore box
Osman ordered a NY Style Sauce and Cheese Pizza

Preparing Chicago Style Deep Dish Pepperoni Pizza
Tossing dough...
Adding sauce...
Adding toppings: 
 1 teaspoon fresh oregano
 18-20 slices pepperoni
 12 ounces mozzarella cheese, grated
Bake for 30 minutes at 300
Cutting the pizza into square slices
Place pizza in official PizzaStore box
Bora ordered a Chicago Style Deep Dish Pepperoni Pizza


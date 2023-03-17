class meal {
    String name;
    String[] ingredients;
}

public class Main {
    public static void main(String args[]) {
        meal newMeal = new meal();
        newMeal.name = "Beans and Rice";
        System.out.println("You added a new meal: " + newMeal.name);
    }
}

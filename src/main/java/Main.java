import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //Meal initialization
        meal[] meals;
        meals = new meal[21];

        Scanner input = new Scanner(System.in);

        //Opening Screen
        System.out.println
                ("Welcome to Unnamed Meal Plan App, Press A to add a meal, D to delete a meal, or L to list meals. ");

        String choice = input.nextLine();

        //Opening Screen Input
        if(choice.equals("A")){
            for(int i = 0; i <= 20; i++){
                System.out.println("Please add a meal name: ");
                String mealNameIn = input.nextLine();
                meals[i] = new meal(mealNameIn);
            }
        }

        //List Meals
        for(int i = 0; i <= 20; i++){
            System.out.println((i + 1) + ". " + meals[i].name + "\n");
        }
    }
}

class meal {
    public String name;

    meal(String name){
        this.name = name;
    }
}

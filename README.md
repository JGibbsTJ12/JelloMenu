# *JelloMenu*
A Desktop CRUD Meal Planning App, I wrote it in Java because that is the language I am most familiar with. I used Hibernate and JPA for the SQL API because it's a modern
database API that is simple and flexible enough for this implementation. I used SQLite for my database because I needed more than a data structure but I only needed enough power
to function alongside the app without using too much system resources. I used Swing for the UI for simplicity and customized it with the Flatleaf library.

## Functionality
The app is very user frienly and functions pretty simply, you start on this main menu

![](https://files.catbox.moe/2l66uj.png)

And navigating to the Edit Meal List menu lets you write notes that can be added automatically to add meal or add ingredient by highlighting.
Adding and ingredient or meal will bring up these other two menus where you can add the meals and their servings and each part of an ingredient.

![](https://files.catbox.moe/2l66uj.png)

The third menu lets you organize each week, the week is selected in the top left and you can make a new week or delete the selected week. Each week starts on a monday
From there, you can edit each day of the week and each of the 3 meals as well.

![](https://files.catbox.moe/46sswl.png)

Finally you can view the shopping list and your meal plan on the final menu.

![](https://files.catbox.moe/3t09ti.png)

## Installation
App is packaged with gradle, so run via Gradle as you would any other
SQLite is required for this app to work.

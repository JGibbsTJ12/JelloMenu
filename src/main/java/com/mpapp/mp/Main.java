package com.mpapp.mp;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        String totd = totdGen();

        //Home Screen Initialization
        FlatLightLaf.setup();
        JFrame f = new JFrame("Main Menu");
        f.setSize(600,300);
        JLabel l1 = new JLabel("Welcome to JelloMenu!");
        l1.setBounds(210, 0, 200, 25);
        JButton bEditMeals = new JButton("Edit Meal List");
        bEditMeals.setBounds(150, 50, 250, 25);
        JButton bSortPlan = new JButton("Organize Meal Plan");
        bSortPlan.setBounds(150, 80, 250, 25);
        JButton bViewList = new JButton("View Meal Plan and Shopping List");
        bViewList.setBounds(150, 110, 250, 25);
        JLabel l2 = new JLabel("Tip: " + totd);
        l2.setBounds(10, 230, 580, 25);
        f.add(l1); f.add(bEditMeals); f.add(bSortPlan); f.add(bViewList); f.add(l2);
        f.setLayout(null);
        f.setVisible(true);

        //Edit MealPlan.Meals Option
        bEditMeals.addActionListener(new ActionListener() {
            //Edit Meals Button to EditMealList class
            @Override
            public void actionPerformed(ActionEvent e) {
                EditMealList.EditList();
            }
        });

        bSortPlan.addActionListener(new ActionListener() {
            //Organize Meal Plan Button to OrganizePlan class
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizePlan.SortPlan();
            }
        });
        bViewList.addActionListener(new ActionListener() {
            //View Plan and List Button to View Plan class
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPlan.ViewList();
            }
        });
    }

    public static String totdGen(){
        /*This method generates the tip of the day and returns it for use in the UI component
        New strings can be added freely to the array without interrupting any functionality */
        String[] tips = {"Meal planning every week can save money and time!", "Leave notes to make it easier to plan."
                , "Try JelloPomo! My Pomodoro android app.", "Cooking in a clean workspace makes it so much easier!"
                , "Don't work yourself too hard, get some ready to eat meals every now and then."
                , "When adding a new meal you can select any part of the Notes and it will automatically fill into the name field."
        };
        int max = tips.length;
        int min = 0;
        Random random = new Random();
        int rand = random.nextInt(max - min) + min;
        return tips[rand];
    }
}

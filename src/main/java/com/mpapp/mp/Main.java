package com.mpapp.mp;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        String totd = "";

        //Home Screen Initialization
        FlatLightLaf.setup();
        JFrame f = new JFrame();
        JLabel l1 = new JLabel("Welcome to Unnamed Meal Plan App");
        l1.setBounds(175, 0, 200, 25);
        JButton bEditMeals = new JButton("Edit Meal List");
        bEditMeals.setBounds(175, 50, 200, 25);
        JButton bSortPlan = new JButton("Organize Meal Plan");
        bSortPlan.setBounds(175, 80, 200, 25);
        JButton bViewList = new JButton("View Shopping List");
        bViewList.setBounds(175, 110, 200, 25);
        JButton bViewHist = new JButton("View History");
        bViewHist.setBounds(175, 140, 200, 25);
        JLabel l2 = new JLabel("Tip: " + totd);
        l2.setBounds(100, 225, 200, 25);
        f.add(l1); f.add(bEditMeals); f.add(bSortPlan); f.add(bViewList); f.add(bViewHist); f.add(l2);
        f.setSize(600,300);
        f.setLayout(null);
        f.setVisible(true);

        //Edit MealPlan.Meals Option
        bEditMeals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditMealList.EditList();
                f.setVisible(false);
                f.dispose();
            }
        });
    }
}

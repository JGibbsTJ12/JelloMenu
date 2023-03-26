package com.mpapp.mp;

import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class EditMealList{
    public static void EditList() {
        //Window Initialization
        JFrame f = new JFrame();
        JTextArea tf = new JTextArea();
        JLabel l1 = new JLabel
                ("Use the Text Box for Notes");
        l1.setBounds(30, 5, 600, 25);
        tf.setBounds(0, 30, 600, 150);
        JButton bAddIng = new JButton("Add Ingredients");
        JButton bAddMeal = new JButton("Add Meal");
        bAddIng.setBounds(200, 200, 200, 25);
        bAddMeal.setBounds(200, 230, 200, 25);
        f.setSize(600, 300);
        f.add(tf);
        f.add(bAddIng);
        f.add(l1);
        f.add(bAddMeal);
        f.setLayout(null);
        f.setVisible(true);

        //Add Meal Button Functionality
        bAddMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf.getSelectedText() != "" | tf.getSelectedText() != null) {
                    addMeal(tf.getSelectedText());
                }
            }
        });

        //Add Ingredients Functionality
        bAddIng.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf.getSelectedText() != "" | tf.getSelectedText() != null) {
                    addIng(tf.getSelectedText());
                }
            }
        });
    }
    public static void addMeal(String m){
        //Add Meal Window
        JFrame amf = new JFrame();
        amf.setSize(300,200);
        JLabel mc = new JLabel("Configure Meal");
        mc.setBounds(5, 5, 100, 25);
        JLabel mn = new JLabel("Name:");
        JTextField mnf = new JTextField(m);
        mn.setBounds(10, 45, 100, 25);
        mnf.setBounds(110, 45, 100, 25);
        JLabel ms = new JLabel("Serving Count:");
        JTextField msf = new JTextField();
        ms.setBounds(10, 85, 100, 25);
        msf.setBounds(110, 85, 100, 25);
        JButton my = new JButton("Confirm");
        my.setBounds(75, 125, 100, 25);
        amf.add(mc); amf.add(mn); amf.add(mnf); amf.add(ms); amf.add(msf); amf.add(my);
        amf.setLayout(null);
        amf.setVisible(true);

        my.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                //Add Meal Functionality
                //DB Init
                final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                        .configure().build();
                Metadata metadata = new MetadataSources(ssr)
                        .addAnnotatedClass(Meals.class)
                        .addAnnotatedClass(MealIngJunc.class)
                        .getMetadataBuilder()
                        .build();
                SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                        .build();
                System.out.println("DB Init Successful");

                //Adding meal and meal id to meal table and junction table
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                    Meals m1 = new Meals();
                    MealIngJunc mij1 = new MealIngJunc();
                    m1.setName(mnf.getText());
                    m1.setServings(Integer.parseInt(msf.getText()));
                    session.persist(m1);
                    mij1.setMealid(m1.getMealid());
                    session.persist(mij1);
                    session.getTransaction().commit();
                    System.out.println("DB Save Success");
                    sessionFactory.close();
                    session.close();
                amf.setVisible(false);
                amf.dispose();
            }
        });
    }
    public static void addIng(String i){
        JFrame aif = new JFrame();
        aif.setSize(300,300);
        JLabel ic = new JLabel("Configure Ingredient");
        ic.setBounds(5, 5, 150, 25);
        JLabel in = new JLabel("Name:");
        JTextField inf = new JTextField(i);
        in.setBounds(10, 45, 100, 25);
        inf.setBounds(110, 45, 100, 25);
        JLabel is = new JLabel("Amount:");
        JTextField isf = new JTextField();
        is.setBounds(10, 85, 100, 25);
        isf.setBounds(110, 85, 100, 25);
        JLabel im = new JLabel("Measurement:");
        String[] meas = { "Grams", "Ounces", "ML", "Cups", "Tablespoons", "Teaspoons", "Count"};
        JComboBox icb = new JComboBox(meas);
        icb.setBounds(110, 125, 100, 25);
        im.setBounds(10, 125, 100, 25);
        JButton iy = new JButton("Confirm");
        iy.setBounds(75, 155, 100, 25);
        aif.add(ic); aif.add(in); aif.add(inf); aif.add(is);
        aif.add(isf); aif.add(iy); aif.add(icb); aif.add(im);
        aif.setLayout(null);
        aif.setVisible(true);
    }
}

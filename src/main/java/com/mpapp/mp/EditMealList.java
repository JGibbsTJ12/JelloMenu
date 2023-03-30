package com.mpapp.mp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
public class EditMealList{
    public static void EditList() {
        //Window Initialization
        JFrame f = new JFrame("Edit Meal List");
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
        JFrame amf = new JFrame("Add Meal");
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

                //Adding meal to meal table
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                    Meals m1 = new Meals();
                    m1.setName(mnf.getText());
                    m1.setServings(Integer.parseInt(msf.getText()));
                    session.persist(m1);
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
        //Generating List of Meals for Window
        //AddIngredient Functionality
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
        Session session = sessionFactory.openSession();
        List results = session.createQuery("SELECT name FROM Meals").list();
        sessionFactory.close();
        session.close();
        String[] mealList = new String[results.size()];
        for(int x = 0; x < results.size(); x++)
            mealList[x] = (String) results.get(x);

        //Window Init
        JFrame aif = new JFrame("Add Ingredients");
        aif.setSize(270,300);
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
        JLabel ime = new JLabel("Meal:");
        JComboBox iml = new JComboBox(mealList);
        iml.setBounds(110, 165, 100, 25);
        ime.setBounds(10, 165, 100, 25);
        JButton iy = new JButton("Confirm");
        iy.setBounds(75, 210, 100, 25);
        aif.add(ic); aif.add(in); aif.add(inf); aif.add(is);
        aif.add(isf); aif.add(iy); aif.add(icb); aif.add(im);
        aif.add(iml);
        aif.setLayout(null);
        aif.setVisible(true);

        iy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //DB Init
                final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                        .configure().build();
                Metadata metadata = new MetadataSources(ssr)
                        .addAnnotatedClass(Meals.class)
                        .addAnnotatedClass(MealIngJunc.class)
                        .addAnnotatedClass(Ingredients.class)
                        .getMetadataBuilder()
                        .build();
                SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                        .build();
                /*Adds Ingredient to Ingredient Table and Junctions
                Meal and Ingredient to Junction Table*/
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                    Ingredients i = new Ingredients();
                    MealIngJunc mij = new MealIngJunc();
                    i.setName(inf.getText());
                    i.setAmount(Integer.parseInt(isf.getText()));
                    i.setAmtmsmt(meas[icb.getSelectedIndex()]);
                    session.persist(i);
                    mij.setIngid(i.getId());
                    mij.setMealid(iml.getSelectedIndex() + 1);
                    session.persist(mij);
                    session.getTransaction().commit();
                    System.out.println("DB Save Success");
                    session.close();
                    sessionFactory.close();
            }
        });
    }
}

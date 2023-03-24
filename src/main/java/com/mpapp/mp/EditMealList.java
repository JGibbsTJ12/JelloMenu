package com.mpapp.mp;

import org.hibernate.AnnotationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class EditMealList{
    private static SessionFactory sessionFactory;
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

            }
        });
    }

    public static void addMeal(String m){
        //Add Meal Functionality
        JFrame amf = new JFrame();
        amf.setSize(300,200);
        JLabel mc = new JLabel("Configure Meal");
        mc.setBounds(5, 5, 100, 25);
        JLabel mn = new JLabel("Name:");
        JTextField mnf = new JTextField();
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
                final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                        .configure().build();
                try {
                    sessionFactory = new MetadataSources(ssr).buildMetadata().buildSessionFactory();
                    System.out.println("DB Init Successful");
                }
                catch (Exception e) {
                    StandardServiceRegistryBuilder.destroy(ssr);
                    System.out.println("DB Init Failed");
                }
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
}

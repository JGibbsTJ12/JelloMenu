package com.mpapp.mp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;

public class ViewPlan {
    public static void ViewList() {
        final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata = new MetadataSources(ssr)
                .addAnnotatedClass(Meals.class)
                .addAnnotatedClass(Ingredients.class)
                .addAnnotatedClass(Week.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();

        JFrame f = new JFrame("View Plan"); f.setSize(800, 400);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.setPreferredSize(new Dimension(200, 1000));
        p1.setPreferredSize(new Dimension(200, 1000));
        JScrollPane opp = new JScrollPane(p); opp.setBounds(10, 5, 385, 350);
        JScrollPane opp1 = new JScrollPane(p1); opp1.setBounds(395, 5, 385, 350);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createTitledBorder("Plan"));
        opp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp1.setBorder(BorderFactory.createTitledBorder("List"));
        String[] dL = {"Monday:", "Tuesday:", "Wednesday:", "Thursday:", "Friday:", "Saturday:", "Sunday:-"};
        String[] mL = {"Breakfast:", "Lunch:", "Dinner:"};
        for(int i = 0; i < 7; i++){
            p.add(new JLabel(dL[i])).setBounds(10, 10 + (i * 75), 75, 25);
            for(int a = 0; a < 3; a++){
                p.add(new JLabel(mL[a])).setBounds(10 + (a * 100), 35 + (i * 75), 100, 25);
            }
        }
        f.add(opp); f.add(opp1);
        p.setLayout(null);
        p1.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);
    }
}

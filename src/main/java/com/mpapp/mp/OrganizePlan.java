package com.mpapp.mp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrganizePlan {
    public static void SortPlan(){
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
        Session session = sessionFactory.openSession();
        List results = session.createQuery("SELECT name FROM Meals").list();
        sessionFactory.close();
        session.close();

        //Window Init
        JFrame f = new JFrame("Organize Meal Plan");
        JPanel p = new JPanel();
        f.setSize(600,300);
        JButton c = new JButton("Confirm");
        c.setBounds(475, 230, 100, 25);
        p.setBounds(0, 0, 400, 1000);
        JScrollPane opp = new JScrollPane(p);
        opp.setBounds(150, 10, 400, 210);
        opp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String[] days = {"Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"};
        JComboBox day1 = new JComboBox(days);
        day1.setBounds(5, 5, 125, 25);
        String[] meals1 = new String[results.size() + 1];
        String[] meals2 = new String[results.size() + 1];
        String[] meals3 = new String[results.size() + 1];
        meals1[0] = "Breakfast";
        meals2[0] = "Lunch";
        meals3[0] = "Dinner";
        for(int x = 1; x < results.size() + 1; x++)
            meals1[x] = (String) results.get(x - 1);
        for(int y = 1; y < results.size() + 1; y++)
            meals2[y] = (String) results.get(y - 1);
        for(int z = 1; z < results.size() + 1; z++)
            meals3[z] = (String) results.get(z - 1);
        JComboBox meal1 = new JComboBox(meals1);
        JComboBox meal2 = new JComboBox(meals2);
        JComboBox meal3 = new JComboBox(meals3);
        JTextArea mta = new JTextArea("Notes");
        mta.setBounds(5, 75, 380, 125);
        meal1.setBounds(5, 40, 90, 25);
        meal2.setBounds(105, 40, 90, 25);
        meal3.setBounds(205, 40, 90, 25);
        p.add(day1); p.add(meal1); p.add(meal2); p.add(meal3); p.add(mta);
        f.add(opp); f.add(c);
        p.setLayout(null);
        f.setLayout(null);
        f.setVisible(true);

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
